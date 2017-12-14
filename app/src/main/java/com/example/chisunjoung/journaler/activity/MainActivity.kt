package com.example.chisunjoung.journaler.activity


import android.os.Bundle
import com.example.chisunjoung.journaler.R
import com.example.chisunjoung.journaler.fragment.ItemsFragment
/**
 * Created by chisunjoung on 12/12/2017.
 */


class MainActivity : BaseActivity() {
    override val tag = "Main activity"
    override fun getLayout() = R.layout.activity_main
    override fun getActivityTitle() = R.string.app_name

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val fragment = ItemsFragment()
        supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_container, fragment)
                .commit()
    }


}