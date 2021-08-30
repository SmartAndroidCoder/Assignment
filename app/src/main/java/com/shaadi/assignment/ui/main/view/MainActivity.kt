package com.shaadi.assignment.ui.main.view

import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import com.shaadi.assignment.R
import com.shaadi.assignment.data.model.NavigateViewState
import com.shaadi.assignment.data.model.ProcessLoadingState
import com.shaadi.assignment.databinding.ActivityMainBinding
import com.shaadi.assignment.ui.BaseActivity
import com.shaadi.assignment.ui.main.adapter.UserMatchAdapter
import com.shaadi.assignment.ui.main.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private val mainViewModel: MainViewModel by viewModels()
    private lateinit var adapter: UserMatchAdapter
    private lateinit var binding: ActivityMainBinding
    override val rootViewId = R.id.rootViewMainActivity

    override fun init() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupRecyclerViewUI()
    }

    override fun initObservers() {
        mainViewModel.loading.observe(this, { loading(it) })
        mainViewModel.navigate.observe(this, { navigate(it) })
//        mainViewModel.fetchUsers(10)
    }

    override fun initDestroy() {
        mainViewModel.cancelJobs()
    }

    private fun showError(errMsg: String) {
        Snackbar.make(binding.rootViewMainActivity, errMsg, BaseTransientBottomBar.LENGTH_SHORT)
            .show()
    }

    private fun setupRecyclerViewUI() {
        adapter = UserMatchAdapter(object : UserMatchAdapter.UserMatchAdapterListener {
            override fun updateMatchStatus(email: String, status: Int) {
                mainViewModel.updateMatchStatus(email, status)
            }
        })
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = this@MainActivity.adapter
        }
    }

    private fun loading(state: ProcessLoadingState) {
        when (state) {
            ProcessLoadingState.HideLoading -> {
                binding.progressBarMain.visibility = View.GONE
            }
            ProcessLoadingState.ShowLoading -> {
                binding.progressBarMain.visibility = View.VISIBLE
            }
        }
    }

    private fun navigate(state: NavigateViewState) {
        when (state) {
            is NavigateViewState.UserListSuccess -> adapter.submitList(state.users)
            is NavigateViewState.ErrorUserMessage -> {
                when {
                    state.errMsg != null -> showError(state.errMsg)
                    state.errMsgId != null -> showError(getString(state.errMsgId))
                    else -> showError(getString(R.string.generic_error_alert_dialog_title))
                }
            }
            is NavigateViewState.UpdateErrorMessage -> {
                when {
                    state.errMsg != null -> showError(state.errMsg)
                    state.errMsgId != null -> showError(getString(state.errMsgId))
                    else -> showError(getString(R.string.generic_error_alert_dialog_title))
                }
            }
            is NavigateViewState.UpdateUserSuccess -> mainViewModel.fetchUsers(10)
        }
    }

}
