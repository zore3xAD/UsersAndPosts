package com.anton.dobrogorsky.usersposts.flow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.anton.dobrogorsky.usersposts.R
import com.anton.dobrogorsky.usersposts.flow.users.UserListFragment
import com.anton.dobrogorsky.usersposts.util.replaceFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        replaceFragment(UserListFragment.newInstance(), false)
    }
}