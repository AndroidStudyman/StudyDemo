package com.example.myapplication

import android.annotation.SuppressLint
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.webkit.*
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.databinding.ActivityWebViewTestBinding

class WebViewTest : AppCompatActivity() {

    lateinit var binding: ActivityWebViewTestBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWebViewTestBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initWeb()
    }

    @SuppressLint("SetJavaScriptEnabled")
    fun initWeb() {
        val setting = binding.vWebView.settings
        setting.javaScriptEnabled = true
        setting.javaScriptCanOpenWindowsAutomatically = true
        binding.vWebView.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(view: WebView?, newProgress: Int) {
                Log.i("onPageStarted", "页面加载")
                super.onProgressChanged(view, newProgress)
            }

            override fun onShowFileChooser(
                webView: WebView?,
                filePathCallback: ValueCallback<Array<Uri>>?,
                fileChooserParams: FileChooserParams?
            ): Boolean {
                Log.i("onShowFileChooser", "文件处理")
                return true
            }
        }
        binding.vWebView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?
            ): Boolean {
                Log.i("shouldOverrideUrl", "页面处理：" + view!!.url)
                return super.shouldOverrideUrlLoading(view, request)
            }
        }
        loadWeb()
    }

    private fun loadWeb(){
        binding.vWebView.loadUrl("https://flutter.cn/")
    }


}