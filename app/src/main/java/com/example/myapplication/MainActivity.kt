package com.example.myapplication

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.content.pm.ShortcutInfoCompat
import androidx.core.content.pm.ShortcutManagerCompat
import androidx.core.graphics.drawable.IconCompat

import androidx.fragment.app.FragmentTransaction
import androidx.viewbinding.ViewBinding
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var dialogHeight : Int = 0
    lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.vImageWeb.setOnClickListener {
            val intent = Intent(this, WebViewTest ::class.java)
            startActivity(intent)
        }
        binding.vToImage.setOnClickListener {
            val intent = Intent(this, SharedPreferencesDemo ::class.java)
            startActivity(intent)
        }

        binding.withDraw.setOnClickListener {
            val intent = Intent(this, ActivityAdapterDemo ::class.java)
            intent.action = Intent.ACTION_VIEW
            startActivity(intent)
        }

        //动态方式添加一
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1) {
            val shortScan = ShortcutInfoCompat.Builder(this, "first_page")//唯一标识id
                .setShortLabel(getString(R.string.first_fragment_label))//短标签
                .setIcon(IconCompat.createWithResource(this, R.mipmap.ic_first_page))//图标
                //跳转的目标，定义Activity
                .setIntent(Intent(Intent.ACTION_MAIN, null, this, MainActivity::class.java))
                .build()
            //执行添加操作
            ShortcutManagerCompat.addDynamicShortcuts(this, mutableListOf(shortScan))

            Toast.makeText(this, "open first page", Toast.LENGTH_SHORT).show()
        }

    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        // 获取dialog的高度
        dialogHeight = binding.vllSize.measuredHeight
        // 获取dialog的高度
        Log.d( "MainActivity" ,"height = $dialogHeight")
    }

    override fun onResume() {
        super.onResume()
            binding.vMore.setOnClickListener {
            val dialog  = DialogMore().newInstance()
                .setDialogHeight(dialogHeight)
            val ft: FragmentTransaction =
                supportFragmentManager.beginTransaction()
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            dialog.show(ft, "DialogMore")
        }
        binding.vTransfer.setOnClickListener {
            val modalBottomSheet = ModalBottomSheet()
            modalBottomSheet.show(supportFragmentManager, ModalBottomSheet.TAG)
        }
    }
}