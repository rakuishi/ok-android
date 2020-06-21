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
import com.rakuishi.ok.data.Injector
import kotlinx.android.synthetic.main.fragment_list.*

class FeedFragment : Fragment() {

    private val viewModel: FeedViewModel by viewModels { Injector.provideFeedViewModelFactory() }
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

        subscribeUi()
    }

    private fun subscribeUi() {
        viewModel.feeds.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })

        viewModel.isLoading.observe(viewLifecycleOwner, Observer {
            progressBar.visibility = if (it) View.VISIBLE else View.GONE
        })

        viewModel.throwable.observe(viewLifecycleOwner, Observer {
            Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
        })
    }
}