package com.anton.dobrogorsky.usersposts.flow.posts

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.anton.dobrogorsky.usersposts.R
import com.anton.dobrogorsky.usersposts.databinding.UserPostsFragmentBinding
import org.koin.android.viewmodel.ext.android.viewModel

class UserPostsFragment : Fragment() {

    companion object {
        private val TAG = UserPostsFragment::class.java.simpleName
        private val ARG_USER_ID = "user_id_arg"
        private val ARG_USER_NAME = "user_name_arg"

        fun newInstance() = UserPostsFragment()

        fun newInstance(userId: Int, username: String) = UserPostsFragment().apply {
            arguments = Bundle().apply {
                putInt(ARG_USER_ID, userId)
                putString(ARG_USER_NAME, username)
            }
        }
    }

    private lateinit var binding: UserPostsFragmentBinding
    private val viewModel: UserPostsViewModel by viewModel()

    private lateinit var userPostsAdapter: UserPostsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = UserPostsFragmentBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        userPostsAdapter = UserPostsAdapter()
        binding.userPostsList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = userPostsAdapter
        }
        viewModel.userPosts.observe(this) { userPosts ->
            viewModel.loadCommentsForPosts()
            userPostsAdapter.userPostList = userPosts
        }
        viewModel.updateCommentsPostPosition.observe(this) { postPosition ->
            userPostsAdapter.updatePost(postPosition)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_toolbar, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.sort -> {
                userPostsAdapter.sortList(ascending = item.isChecked)
                if (item.isChecked) item.setIcon(R.drawable.ic_sort_white)
                else item.setIcon(R.drawable.ic_sort_black)
                item.isChecked = !item.isChecked
                return true
            }
            else -> return false
        }
    }

    override fun onResume() {
        super.onResume()
        arguments?.let { bundle ->
            val userId = bundle.getInt(ARG_USER_ID, -1)
            if (userId != -1) viewModel.fetchUserPosts(userId)

            val username = bundle.getString(ARG_USER_NAME, "")
            binding.userInfo.text = context?.getString(R.string.post_of_user, username)
        }
    }

}