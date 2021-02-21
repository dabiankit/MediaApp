package com.example.mediaapplication.base

import android.content.Context
import android.os.Bundle
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

abstract class BaseActivity : AppCompatActivity(){

    lateinit var context : Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    fun addFragment(fragment: Fragment, @IdRes frameId: Int, backstack: Boolean) {
        // AppConstants.checkNotNull(fragment);
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.add(frameId, fragment, fragment.javaClass.simpleName)
        if (backstack)
            fragmentTransaction.addToBackStack(fragment.tag)
        fragmentTransaction.commitAllowingStateLoss()
    }

    fun replaceFragment(fragment: Fragment, @IdRes frameId: Int, backstack: Boolean) {
        //AppConstants.checkNotNull(fragment);
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(frameId, fragment, fragment.javaClass.simpleName)
        if (backstack)
            fragmentTransaction.addToBackStack(fragment.tag)
        fragmentTransaction.commitAllowingStateLoss()
    }

    fun popFragment() {
        if (supportFragmentManager.backStackEntryCount > 1) {
            supportFragmentManager.popBackStack()
        } else {
            finish()
        }
    }

    fun hideFragment(fragment: Fragment) {
        //AppConstants.checkNotNull(fragment);
        supportFragmentManager.beginTransaction()
            .hide(fragment)
            .commitAllowingStateLoss()
    }

    fun showFragment(fragment: Fragment) {
        //AppConstants.checkNotNull(fragment);
        supportFragmentManager.beginTransaction()
            .show(fragment)
            .commitAllowingStateLoss()
    }

    fun removeFragment(fragment: Fragment) {
        // AppConstants.checkNotNull(fragment);
        supportFragmentManager.beginTransaction()
            .remove(fragment)
            .commitAllowingStateLoss()
    }

    protected fun backStackCount(): Int {
        //AppConstants.checkNotNull(fragment);
        return supportFragmentManager.backStackEntryCount
    }


    override fun onStart() {
        super.onStart()
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
    }
}