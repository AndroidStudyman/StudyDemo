package com.example.myapplication

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.webkit.*
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityWebViewTestBinding

class WebViewTest : AppCompatActivity() {

    private lateinit var binding: ActivityWebViewTestBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWebViewTestBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initWeb()
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK && binding.vWebView.canGoBack()) {
            binding.vWebView.goBack()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

    override fun onDestroy() {
        binding.vWebView.loadDataWithBaseURL(null, "", "text/html", "utf-8", null)
        binding.vWebView.clearHistory()

        binding.vWebView.destroy()
        super.onDestroy()
    }

    private fun initWeb() {
        //声明WebSettings子类
        val setting = binding.vWebView.settings
       //如果访问的页面中要与Javascript交互，则WebView必须设置支持Javascript
        setting.javaScriptEnabled = true
        // 若加载的 html 里有JS 在执行动画等操作，会造成资源浪费（CPU、电量）
        // 在 onStop 和 onResume 里分别把 setJavaScriptEnabled() 给设置成 false 和 true 即可
       // 设置自适应屏幕，两者合用
        setting.useWideViewPort = true //将图片调整到适合WebView的大小
        setting.loadWithOverviewMode = true // 缩放至屏幕的大小

        //缩放操作
        setting.setSupportZoom(true) //支持缩放，默认为true。是下面那个的前提。
        setting.builtInZoomControls = true //设置内置的缩放控件。若为false，则该WebView不可缩放
        setting.displayZoomControls = false //隐藏原生的缩放控件

        //其他细节操作
        setting.cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK //关闭WebView中缓存
        setting.allowFileAccess = true //设置可以访问文件
        setting.javaScriptCanOpenWindowsAutomatically = true //支持通过JS打开新窗口
        setting.loadsImagesAutomatically = true //支持自动加载图片
        setting.defaultTextEncodingName = "utf-8" //设置编码格式

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

            override fun shouldInterceptRequest(
                view: WebView?,
                request: WebResourceRequest?
            ): WebResourceResponse? {
                return super.shouldInterceptRequest(view, request)
            }
        }
        loadWeb()
    }

    private fun loadWeb(){
        binding.vWebView.loadUrl("https://flutter.cn/")
    }


}