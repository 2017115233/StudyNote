package com.wakeup.drainage

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity


class MainActivity : ComponentActivity() {
    private var btn: Button?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 界面设置
        requestWindowFeature(Window.FEATURE_NO_TITLE) // 移除系统标题栏
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        ) //去除状态栏 启用全屏模式
        setContentView(R.layout.activity_main) // 绑定布局文件


        btn = findViewById(R.id.btn_startDrainage)
        btn!!.isSelected = true
    }


    fun informationButtonClick(view: View) {
        // 跳转到 InformationActivity
        startActivity(Intent(this, InformationActivity::class.java))
    }

    fun drainagingButtonClick(view: View) {
        // 跳转到 Drainaging
        startActivity(Intent(this, Drainaging::class.java))
    }
}

