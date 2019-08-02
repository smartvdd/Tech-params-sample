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
    private val frameRateCallback = object : FrameRateCalculator.FrameRateCallback {
        override fun onFrame(droppedCount: Int) {
            frameRateCounter.text = droppedCount.toString()
        }
    }
    private val animationViews: Queue<LottieAnimationView> = ArrayDeque()

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
        val container = findViewById<ViewGroup>(android.R.id.content)
        val animationView = LottieAnimationView(this).apply {
            setAnimation(R.raw.smile)
        }
        container.addView(animationView)
        animationViews.add(animationView)
        animationView.repeatCount = ValueAnimator.INFINITE;
        animationView.repeatMode = LottieDrawable.RESTART;
        animationView.playAnimation()
    }

    private fun removeAnimationView() {
        animationViews.poll()?.let {
            val container = findViewById<ViewGroup>(android.R.id.content)
            it.pauseAnimation()
            container.removeView(it)
        }
    }

    override fun onResume() {
        super.onResume()
        frameRateCalculator.apply {
            callback = frameRateCallback
            start(Choreographer.getInstance())
        }
    }

    override fun onPause() {
        super.onPause()
        frameRateCalculator.apply {
            callback = null
            stop(Choreographer.getInstance())
        }
    }
}