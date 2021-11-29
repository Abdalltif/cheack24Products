package com.abdalltif.check24challenge.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.databinding.DataBindingUtil
import com.abdalltif.check24challenge.R
import com.abdalltif.check24challenge.databinding.FragmentWebviewBinding

class WebviewFragment : Fragment(R.layout.fragment_webview) {
    private lateinit var binding: FragmentWebviewBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = DataBindingUtil.setContentView<FragmentWebviewBinding>(requireActivity(),
            R.layout.fragment_webview).apply {
            this.lifecycleOwner = requireActivity()
        }

        setupWebView()

        return view
    }

    private fun setupWebView() {
        binding.webview.webViewClient = WebViewClient()
        binding.webview.loadUrl("http://m.check24.de/rechtliche-hinweise?deviceoutput=app")
    }

}