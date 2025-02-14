package com.wakeup.drainage

import android.content.Context
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Vibrator
import android.view.Window
import android.view.WindowManager
import android.widget.ImageView
import androidx.activity.ComponentActivity
import com.bumptech.glide.Glide
import android.view.View
import android.widget.TextView
import kotlin.system.exitProcess

class Drainaging : ComponentActivity() {
    // 定时器控制
    private var switchHandler = Handler(Looper.getMainLooper())
    private lateinit var switchRunnable: Runnable
    private var gifImageView1: ImageView ?=null
    private var gifImageView2: ImageView ?=null
    private var tipText1: TextView ?=null
    private var tipText2: TextView ?=null
    private var mediaPlayer: MediaPlayer ?=null
    private var vibrator:Vibrator ?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 界面设置
        requestWindowFeature(Window.FEATURE_NO_TITLE) // 移除系统标题栏
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        ) //去除状态栏 启用全屏模式
        setContentView(R.layout.activity_drainaging) // 绑定布局文件

        //加载gif
        gifImageView1 = findViewById(R.id.gif1)
        gifImageView2 = findViewById(R.id.gif2)
        //设置文本聚焦滚动
        tipText1 = findViewById(R.id.tipText1)
        tipText1!!.isSelected = true
        tipText2 = findViewById(R.id.tipText2)
        tipText2!!.isSelected = true

        // 初始加载第一组内容
        loadFirstContent()

        //播放音频
        mediaPlayer = MediaPlayer.create(this, R.raw.wk_drainage)
        mediaPlayer!!.start() // 自动处理 prepare()
        mediaPlayer!!.isLooping = true

        //振动
        vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        // 检查设备是否支持震动
        if (vibrator!!.hasVibrator()) {
            // 震动 10 秒（10000 毫秒）
            vibrator!!.vibrate(10000)
        }

        // 启动定时任务
        setupTimers()



    }

    private fun loadFirstContent() {
        // 加载第一个GIF（带淡入效果）
        Glide.with(this)//绑定当前this activity的生命周期
            .asGif()//作为动画加载
            .load(R.drawable.img_drainage_autodrain)//载入图片资源 支持放入网络资源例如https://profile-avatar.csdnimg.cn/01e53ae91427413bbfd3b40eda21365f_jeffmf.jpg!1
            .into(gifImageView1!!)//加载到对应控件上
            //其他函数
            //.placeholder(R.drawable.img_loading) // 占位图用于load图片资源未加载出来前占位
            //.error(R.drawable.error) // 用于永久性加载错误时显示图片 或load为空且没有fallback设置时
            //.fallback(R.drawable.fallback) //当.load为空时调用 例如没有用户头像时显示默认头像
            //.transform(new CircleCrop())// 裁剪为圆形 //new RoundedCorners(200)//圆角
            //.transition(DrawableTransitionOptions.withCrossFade()) // 使用淡入淡出效果
            //.override(300, 200) // 将图片调整为 300x200 像素px
            //.fitCenter() // 等比例缩放，确保整个图片适应 ImageView
            //.centerCrop() // 裁剪并填充 ImageView
            //.scale(0.5f) // 将图片缩放至原始大小的 50%
            //.thumbnail(Glide.with(fragment)//在加载load前优先显示缩略图
                //.load(thumbnailUrl))  // 缩略图的 UR
                //.override(thumbnailUrl))  // 缩略图的 大小设置

        tipText1!!.visibility = View.VISIBLE

    }

    private fun setupTimers() {
        // 5秒后切换第二组内容
        switchRunnable = Runnable {
            switchToSecondContent()

            // 再5秒后退出
            switchHandler.postDelayed({
                finishAffinity() // 退出应用
                exitProcess(0)   // 确保完全退出
            }, 5000)
        }
        switchHandler.postDelayed(switchRunnable, 5000)
    }

    private fun switchToSecondContent() {
        // GIF切换动画
        gifImageView1!!.animate()
            .alpha(0f)
            .setDuration(200)
            .withEndAction {
                gifImageView1!!.visibility = View.GONE
                gifImageView2!!.visibility = View.VISIBLE
                Glide.with(this)
                    .asGif()
                    .load(R.drawable.img_drainage_tip)
                    .into(gifImageView2!!)

                gifImageView2!!.alpha = 0f
                gifImageView2!!.animate().alpha(1f).duration = 200
            }

        // 文字切换
        tipText1!!.animate()
            .alpha(0f)
            .setDuration(200)
            .withEndAction {
                tipText1!!.visibility = View.GONE
                tipText2!!.visibility = View.VISIBLE
                tipText2!!.alpha = 0f
                tipText2!!.animate().alpha(1f).duration = 1000
            }

    }

    override fun onDestroy() {
        // 防止内存泄漏
        switchHandler.removeCallbacks(switchRunnable)
        Glide.with(this).clear(gifImageView1!!)
        Glide.with(this).clear(gifImageView2!!)
        mediaPlayer!!.stop()
        mediaPlayer!!.release()
        vibrator!!.cancel()
        super.onDestroy()
    }
}