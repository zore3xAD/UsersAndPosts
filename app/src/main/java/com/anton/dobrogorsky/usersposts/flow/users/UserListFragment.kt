package com.anton.dobrogorsky.usersposts.flow.users

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.anton.dobrogorsky.usersposts.databinding.UserListFragmentBinding
import com.anton.dobrogorsky.usersposts.flow.posts.UserPostsFragment
import com.anton.dobrogorsky.usersposts.util.mainActivity
import com.anton.dobrogorsky.usersposts.util.replaceFragment
import org.koin.android.viewmodel.ext.android.viewModel

class UserListFragment : Fragment() {

    companion object {
        fun newInstance() = UserListFragment()
    }

    private val viewModel: UserListViewModel by viewModel()

    private lateinit var binding: UserListFragmentBinding

    private lateinit var userAdapter: UserAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = UserListFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        userAdapter = UserAdapter {user ->
            mainActivity?.replaceFragment(UserPostsFragment.newInstance(user.id, user.username))
        }
        binding.userList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = userAdapter
        }
        viewModel.userList.observe(this, { users ->
            userAdapter.userList = users
        })

    }

}