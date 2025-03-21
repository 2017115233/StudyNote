package com.wakeup.appstyle

import android.content.Intent
import android.content.pm.ResolveInfo
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat


class MainActivity : AppCompatActivity()
{
    companion object {
        const val TAG = "AppStyle"
    }

    //App Style
    private var style = AppStyle.entries.first()
    //背景view
    private lateinit var appListBGView: LinearLayout
    //App List
    private var appList:List<ResolveInfo> = emptyList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //获取已安装app信息
        initAppList()

        //初始化菜单背景
        initAppListBGView()

    }

    private fun initAppList() {
        val pm = packageManager
        val mainIntent = Intent(Intent.ACTION_MAIN,null)
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER)
        appList = pm.queryIntentActivities(mainIntent,0)
        Log.d(TAG,"$appList")
    }

    private fun initAppListBGView() {
        appListBGView = findViewById(R.id.container)
        appListBGView.background = ContextCompat.getDrawable(this, style.bgResId)
    }

}
