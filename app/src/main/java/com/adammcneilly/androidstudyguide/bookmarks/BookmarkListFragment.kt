package com.adammcneilly.androidstudyguide.bookmarks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.adammcneilly.androidstudyguide.databinding.FragmentBookmarkListBinding

class BookmarkListFragment : Fragment() {
    private lateinit var binding: FragmentBookmarkListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBookmarkListBinding.inflate(inflater, container, false)
        return binding.root
    }
}
