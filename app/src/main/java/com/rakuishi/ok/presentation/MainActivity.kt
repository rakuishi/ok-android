package com.rakuishi.ok.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.rakuishi.ok.R
import com.rakuishi.ok.presentation.feed.FeedFragment
import com.rakuishi.ok.presentation.gist.GistFragment
import com.rakuishi.ok.presentation.repo.RepoFragment
import com.rakuishi.ok.util.setup
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            setupBottomNavigationBar()
        } // Else, need to wait for onRestoreInstanceState
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        // Now that BottomNavigationBar has restored its instance state
        // and its selectedItemId, we can proceed with setting up the
        // BottomNavigationBar with Navigation
        setupBottomNavigationBar()
    }

    private fun setupBottomNavigationBar() {
        val fragments = arrayOf(FeedFragment(), RepoFragment(), GistFragment())
        bottomNavigationView
            .setup(supportFragmentManager, fragments, R.id.container)
            .observe(this, Observer {
                updateActionBarTitle(it)
            })
    }

    private fun updateActionBarTitle(position: Int) {
        supportActionBar?.setTitle(
            when (position) {
                0 -> R.string.feed_title
                1 -> R.string.repo_title
                else -> R.string.gist_title
            }
        )
    }
}