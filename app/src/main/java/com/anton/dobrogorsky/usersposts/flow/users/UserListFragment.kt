package com.anton.dobrogorsky.usersposts.flow.users

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.LinearLayout
import android.widget.Toolbar
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.anton.dobrogorsky.usersposts.R
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
        setHasOptionsMenu(true)
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
        viewModel.userList.observe(this) { users ->
            userAdapter.userList = users
        }


    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_toolbar, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.sort -> {
                userAdapter.sortList(ascending = item.isChecked)
                if (item.isChecked) item.setIcon(R.drawable.ic_sort_white)
                else item.setIcon(R.drawable.ic_sort_black)
                item.isChecked = !item.isChecked
                return true
            }
            else -> return false
        }
    }
}