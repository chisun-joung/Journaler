package com.example.chisunjoung.journaler.service

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log
import com.example.chisunjoung.journaler.api.BackendServiceHeaderMap
import com.example.chisunjoung.journaler.api.JournalerBackendService
import com.example.chisunjoung.journaler.api.TokenManager
import com.example.chisunjoung.journaler.api.UserLoginRequest
import com.example.chisunjoung.journaler.database.Content
import com.example.chisunjoung.journaler.execution.TaskExecutor
import com.example.chisunjoung.journaler.model.Note
import com.example.chisunjoung.journaler.model.Todo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by chisunjoung on 2018. 1. 10..
 */
class MainService: Service(), DataSynchronization {
    private val tag = "Main service"
    private var binder = getServiceBinder()
    private var executor = TaskExecutor.getInstance(1)

    override fun onCreate() {
        super.onCreate()
        Log.v(tag, "[ ON CREATE ]")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId:
    Int): Int {
        Log.v(tag, "[ ON START COMMAND ]")
        synchronize()
        return Service.START_STICKY
    }

    override fun onBind(p0: Intent?): IBinder {
        Log.v(tag, "[ ON BIND ]")
        return binder
    }

    override fun onUnbind(intent: Intent?): Boolean {
        val result = super.onUnbind(intent)
        Log.v(tag, "[ ON UNBIND ]")
        return result
    }

    override fun onDestroy() {
        synchronize()
        super.onDestroy()
        Log.v(tag, "[ ON DESTROY ]")
    }

    override fun onLowMemory() {
        super.onLowMemory()
        Log.w(tag, "[ ON LOW MEMORY ]")
    }

    override fun synchronize() {
        executor.execute {
            Log.i(tag, "Synchronizing data [ START ]")
            // For now we will only simulate this operation!
            //Thread.sleep(3000)
            var headers = BackendServiceHeaderMap.obtain()
            val service = JournalerBackendService.obtain()
            val credentials = UserLoginRequest("username","password")
            val tokenResponse = service.login(headers, credentials).execute()
            if (tokenResponse.isSuccessful) {
                val token = tokenResponse.body()
                token?.let {
                    TokenManager.currentToken = token
                    headers = BackendServiceHeaderMap.obtain(true)
                    fetchNotes(service, headers)
                    fetchTodos(service, headers)

                }
            }
            Log.i(tag, "Synchronizing data [ END ]")
        }
    }

    private fun fetchNotes(
            service: JournalerBackendService, headers: Map<String,
                    String>
    ) {
        service
                .getNotes(headers)
                .enqueue(
                        object : Callback<List<Note>> {
                            override fun onResponse(
                                    call: Call<List<Note>>?, response: Response<List<Note>>?
                            ) {
                                response?.let {
                                    if (response.isSuccessful) {
                                        val notes = response.body()
                                        notes?.let {
                                            Content.NOTE.insert(notes)
                                        }
                                    }
                                }
                            }

                            override fun onFailure(call:
                                                   Call<List<Note>>?, t: Throwable?) {
                                Log.e(tag, "We couldn't fetch notes.")
                            }
                        }
                )
    }

    /**
     * Fetches TODOs asynchronously.
     * Pay attention on enqueue() method
     */
    private fun fetchTodos(
            service: JournalerBackendService, headers: Map<String,
                    String>
    ) {
        service
                .getTodos(headers)
                .enqueue(
                        object : Callback<List<Todo>> {
                            override fun onResponse(
                                    call: Call<List<Todo>>?, response:
                                    Response<List<Todo>>?
                            ) {
                                response?.let {
                                    if (response.isSuccessful) {
                                        val todos = response.body()
                                        todos?.let {
                                            Content.TODO.insert(todos)
                                        }
                                    }
                                }
                            }

                            override fun onFailure(call:
                                                   Call<List<Todo>>?, t: Throwable?) {
                                Log.e(tag, "We couldn't fetch notes.")
                            }
                        }
                )
    }
    private fun getServiceBinder():MainServiceBinder = MainServiceBinder()

    inner class MainServiceBinder : Binder() {
        fun getService(): MainService = this@MainService
    }
}