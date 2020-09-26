package dev.baires.exercise.google

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import androidx.fragment.app.Fragment
import dev.baires.exercise.R

class GoogleFragment : Fragment() {

    lateinit var url: String

    override fun onAttach(context: Context) {
        super.onAttach(context)
        url = context.resources.getString(R.string.webview_url)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_google, container, false)
        val webView: WebView = view.findViewById(R.id.webView)
        webView.settings.javaScriptEnabled = true
        webView.loadUrl(url)
        return view
    }

}