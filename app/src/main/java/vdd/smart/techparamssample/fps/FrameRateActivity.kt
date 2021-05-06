package vdd.smart.techparamssample.fps

import android.animation.ValueAnimator
import android.os.Bundle
import android.text.format.DateUtils
import android.view.Choreographer
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.lottie.LottieAnimationView
import com.airbnb.lottie.LottieDrawable
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.frame_rate_activity.*
import vdd.smart.techparamssample.R
import java.util.*

class FrameRateActivity : AppCompatActivity(), LayoutContainer {

    override val containerView: View
        get() = window.decorView.rootView

    private val frameRateCalculator: FrameRateCalculator by lazy { FrameRateCalculator(DateUtils.SECOND_IN_MILLIS / windowManager.defaultDisplay.refreshRate) }
    private val frameRateListener = object : FrameRateCalculator.FrameRateListener {
        override fun onFrame(droppedCount: Int) {
            frameRateCounter.text = droppedCount.toString()
        }
    }
    private val animationViewsQueue: Queue<LottieAnimationView> = ArrayDeque()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.frame_rate_activity)
        addMoreAnimationView.setOnClickListener {
            addAnimationView()
        }
        removeAnimationView.setOnClickListener {
            removeAnimationView()
        }
    }

    private fun addAnimationView() {
        val animationView = LottieAnimationView(this).apply {
            setAnimation(R.raw.smile)
            repeatCount = ValueAnimator.INFINITE
            repeatMode = LottieDrawable.RESTART
        }
        val rootView = findViewById<ViewGroup>(android.R.id.content)
        rootView.addView(animationView)
        animationViewsQueue.add(animationView)
        animationView.playAnimation()
    }

    private fun removeAnimationView() {
        val animationView = animationViewsQueue.poll() ?: return
        animationView.run {
            this.pauseAnimation()
            this.clearAnimation()
        }
        val rootView = findViewById<ViewGroup>(android.R.id.content)
        rootView.removeView(animationView)
    }

    override fun onResume() {
        super.onResume()
        frameRateCalculator.start(Choreographer.getInstance(), frameRateListener)
    }

    override fun onPause() {
        frameRateCalculator.stop(Choreographer.getInstance())
        super.onPause()
    }
}