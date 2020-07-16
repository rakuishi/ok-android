package com.rakuishi.ok.presentation.feed

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
import com.rakuishi.ok.util.RequestLiveData
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_list.*

@AndroidEntryPoint
class FeedFragment : Fragment() {

    private val viewModel: FeedViewModel by viewModels()
    private val adapter: FeedAdapter = FeedAdapter()

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
            { CustomTabsIntent.Builder().build().launchUrl(requireContext(), Uri.parse(it.link)) }
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter

        swipeRefreshLayout.setOnRefreshListener { viewModel.requestFeeds() }

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