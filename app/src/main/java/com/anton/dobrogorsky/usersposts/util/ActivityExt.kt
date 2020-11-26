package com.anton.dobrogorsky.usersposts.util

import androidx.fragment.app.Fragment
import com.anton.dobrogorsky.usersposts.R
import com.anton.dobrogorsky.usersposts.flow.MainActivity

val Fragment.mainActivity: MainActivity?
    get() = activity as? MainActivity

fun MainActivity.replaceFragment(fragment: Fragment, addToBackStack: Boolean = true) {
    val fragmentTransaction = supportFragmentManager.beginTransaction()
        .replace(R.id.main_fragment_container, fragment, fragment.tag)
    if (addToBackStack) fragmentTransaction.addToBackStack(fragment.tag)
    fragmentTransaction.commitAllowingStateLoss()
}