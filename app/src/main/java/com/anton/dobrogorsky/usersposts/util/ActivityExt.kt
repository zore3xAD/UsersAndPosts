package com.anton.dobrogorsky.usersposts.util

import androidx.fragment.app.Fragment
import com.anton.dobrogorsky.usersposts.R
import com.anton.dobrogorsky.usersposts.flow.MainActivity

fun MainActivity.replaceFragment(fragment: Fragment) {
    supportFragmentManager.beginTransaction()
        .replace(R.id.main_fragment_container, fragment, fragment.tag)
        .commitAllowingStateLoss()
}