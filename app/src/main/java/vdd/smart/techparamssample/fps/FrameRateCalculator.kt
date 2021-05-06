package vdd.smart.techparamssample.fps

import android.view.Choreographer
import java.util.concurrent.TimeUnit
import kotlin.math.roundToLong

class FrameRateCalculator constructor(private val deviceRefreshRateMillis: Float) :
    Choreographer.FrameCallback {

    private companion object {
        const val UNSET_TIME = -1L
    }

    private var lastFrameTimeNanos: Long = UNSET_TIME

    private var frameListener: FrameRateListener? = null

    fun start(choreographer: Choreographer, listener: FrameRateListener? = null) {
        frameListener = listener
        choreographer.postFrameCallback(this)
    }

    fun stop(choreographer: Choreographer) {
        choreographer.removeFrameCallback(this)
        frameListener = null
    }

    override fun doFrame(frameTimeNanos: Long) {
        if (lastFrameTimeNanos == UNSET_TIME) {
            Choreographer.getInstance().postFrameCallback(this)
            lastFrameTimeNanos = frameTimeNanos
            return
        }

        frameListener?.onFrame(
            droppedCount(
                lastFrameTimeNanos,
                frameTimeNanos,
                deviceRefreshRateMillis
            )
        )

        lastFrameTimeNanos = frameTimeNanos
        Choreographer.getInstance().postFrameCallback(this)
    }

    private fun droppedCount(start: Long, end: Long, devRefreshRate: Float): Int {
        var count = 0
        val diffNs = end - start

        val diffMs = TimeUnit.MILLISECONDS.convert(diffNs, TimeUnit.NANOSECONDS)
        val dev = devRefreshRate.roundToLong()
        if (diffMs > dev) {
            val droppedCount = diffMs / dev
            count = droppedCount.toInt()
        }

        return count
    }

    interface FrameRateListener {
        fun onFrame(droppedCount: Int)
    }
}