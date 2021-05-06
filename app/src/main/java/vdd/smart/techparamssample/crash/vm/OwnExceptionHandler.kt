package vdd.smart.techparamssample.crash.vm

import android.util.Log

class OwnExceptionHandler : Thread.UncaughtExceptionHandler {

    private var previousHandler: Thread.UncaughtExceptionHandler? =
        Thread.getDefaultUncaughtExceptionHandler()

    init {
        Thread.setDefaultUncaughtExceptionHandler(this)
    }

    override fun uncaughtException(t: Thread, e: Throwable) {
        Log.i(
            "OwnExceptionHandler",
            "ALARM. OUR APP ALMOST CRASHED. CRASH STACKTRACE: ${Log.getStackTraceString(e)}"
        )
        previousHandler?.uncaughtException(t, e)
    }

}