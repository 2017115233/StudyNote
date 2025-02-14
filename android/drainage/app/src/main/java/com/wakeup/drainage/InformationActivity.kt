package com.wakeup.drainage

import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.activity.ComponentActivity


class InformationActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 界面设置
        requestWindowFeature(Window.FEATURE_NO_TITLE) // 移除系统标题栏
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        ) //去除状态栏 启用全屏模式
        setContentView(R.layout.activity_information) // 绑定布局文件
    }


}