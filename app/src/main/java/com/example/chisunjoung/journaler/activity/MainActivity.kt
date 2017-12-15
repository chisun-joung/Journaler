package com.example.chisunjoung.journaler.activity


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.util.Log
import android.view.MenuItem
import com.example.chisunjoung.journaler.R
import com.example.chisunjoung.journaler.fragment.ItemsFragment
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Created by chisunjoung on 12/12/2017.
 */


class MainActivity : BaseActivity() {
    override val tag = "Main activity"
    override fun getLayout() = R.layout.activity_main
    override fun getActivityTitle() = R.string.app_name

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       pager.adapter = ViewPagerAdapter(supportFragmentManager)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.drawing_menu -> {
                Log.v(tag, "Main menu.")
                return true
            }
            R.id.options_menu -> {
                Log.v(tag, "Options menu.")
                return true
            }
            else -> return super.onOptionsItemSelected(item)

        }

    }

    private class ViewPagerAdapter(manager: FragmentManager) : FragmentStatePagerAdapter(manager) {
        override fun getItem(position: Int): Fragment {
            return ItemsFragment()
        }

        override fun getCount(): Int {
            return 5
        }
    }


}