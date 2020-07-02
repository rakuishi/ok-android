package com.rakuishi.ok.presentation.repo

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.browser.customtabs.CustomTabsIntent
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.rakuishi.ok.R
import com.rakuishi.ok.data.Injector
import com.rakuishi.ok.util.RequestLiveData
import kotlinx.android.synthetic.main.fragment_list.*

class RepoFragment : Fragment() {

    private val viewModel: RepoViewModel by viewModels { Injector.provideRepoViewModelFactory() }
    private val adapter: RepoAdapter = RepoAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter.onItemClick =
            { CustomTabsIntent.Builder().build().launchUrl(requireContext(), Uri.parse(it.url)) }
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter

        swipeRefreshLayout.setOnRefreshListener { viewModel.requestRepos() }

        subscribeUi()
    }

    private fun subscribeUi() {
        viewModel.requestLiveData.observe(viewLifecycleOwner, Observer {
            when (it.state) {
                RequestLiveData.State.LOADING -> {
                    progressBar.visibility = View.VISIBLE
                }
                RequestLiveData.State.SUCCESS -> {
                    progressBar.visibility = View.GONE
                    swipeRefreshLayout.isRefreshing = false
                    adapter.submitList(it.data)
                }
                RequestLiveData.State.FAILURE -> {
                    progressBar.visibility = View.GONE
                    swipeRefreshLayout.isRefreshing = false
                    Toast.makeText(requireContext(), it.throwable?.message, Toast.LENGTH_SHORT)
                        .show()
                }
            }
        })
    }
}
