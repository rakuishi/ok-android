package com.rakuishi.ok.util

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomnavigation.BottomNavigationView

fun BottomNavigationView.setup(
    fragmentManager: FragmentManager,
    fragments: Array<Fragment>,
    containerId: Int
) {
    if (menu.size() != fragments.size) {
        throw IllegalStateException(
            "menu.size(%d) is not same as fragments.size(%d).".format(
                menu.size(), fragments.size
            )
        )
    }

    val tags: ArrayList<String> = arrayListOf()
    val firstItemId = menu.getItem(0).itemId
    val firstTag = getFragmentTag(menu.getItem(0).itemId)
    var selectedTag = firstTag

    for (i in 0 until menu.size()) {
        val fragment = fragments[i]
        val tag = getFragmentTag(menu.getItem(i).itemId)
        tags.add(tag)

        fragmentManager.beginTransaction()
            .add(containerId, fragment, tag)
            .commitNow()

        if (tag == firstTag) {
            fragmentManager.beginTransaction()
                .attach(fragment)
                .commitNow()
        } else {
            fragmentManager.beginTransaction()
                .detach(fragment)
                .commitNow()
        }
    }

    // When a navigation item is selected
    setOnNavigationItemSelectedListener { item ->
        val newlySelectedItemTag =
            getFragmentTag(item.itemId)

        if (fragmentManager.isStateSaved || selectedTag == newlySelectedItemTag) {
            false
        } else {
            // Pop everything above the first fragment (the "fixed start destination")
            fragmentManager.popBackStack(firstTag, FragmentManager.POP_BACK_STACK_INCLUSIVE)

            if (firstTag != newlySelectedItemTag) {
                val selectedFragment = fragmentManager.findFragmentByTag(newlySelectedItemTag)!!
                fragmentManager.beginTransaction()
                    .attach(selectedFragment)
                    .apply {
                        tags.forEach {
                            if (it != newlySelectedItemTag) {
                                detach(fragmentManager.findFragmentByTag(firstTag)!!)
                            }
                        }
                    }
                    .addToBackStack(firstTag)
                    .setReorderingAllowed(true)
                    .commit()
            }

            selectedTag = newlySelectedItemTag

            true
        }
    }

    fragmentManager.addOnBackStackChangedListener {
        if (firstTag != selectedTag && !fragmentManager.isOnBackStack(firstTag)) {
            selectedItemId = firstItemId
        }
    }
}

private fun FragmentManager.isOnBackStack(backStackName: String): Boolean {
    val backStackCount = backStackEntryCount
    for (index in 0 until backStackCount) {
        if (getBackStackEntryAt(index).name == backStackName) {
            return true
        }
    }
    return false
}

private fun getFragmentTag(itemId: Int) = "#$itemId"