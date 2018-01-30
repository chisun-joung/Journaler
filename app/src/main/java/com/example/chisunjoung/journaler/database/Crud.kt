package com.example.chisunjoung.journaler.database

import kotlin.reflect.KClass

/**
 * Created by chisunjoung on 2017. 12. 28..
 */
interface Crud<T> where T : DbModel {

    companion object {
        val BROADCAST_ACTION = "com.journaler.broadcast.crud"
        val BROADCAST_EXTRAS_KEY_CRUD_OPERATION_RESULT = "crud_result"
    }

    fun insert(what: T) : Boolean
    fun insert(what: Collection<T>): Boolean
    fun update(what: T) : Boolean
    fun update(what: Collection<T>) : Boolean
    /**
     * Returns the number of deleted items.
     */
    fun delete(what: T): Boolean

    /**
     * Returns the number of deleted items.
     */
    fun delete(what: Collection<T>): Boolean

    /**
     * Returns the list of items.
     */
    fun select(args: Pair<String,String>, clazz: KClass<DbModel>): List<T>

    /**
     * Returns the list of items.
     */
    fun select(args: Collection<Pair<String, String>>, clazz: KClass<DbModel>): List<T>

    /**
     * Returns the list of items.
     */
    //fun selectAll(): List<T>
}