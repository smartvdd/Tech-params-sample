package cn.onlinecache.breakpad

import java.io.File

object BreakpadManager {

    private var wasSetup = false

    fun setup(file: File) {
        if (wasSetup) {
            return
        }
        runCatching {
            NativeBreakpad.init(file.absolutePath)
            wasSetup = true
        }
    }

    fun testNativeCrash() {
        NativeBreakpad.testNativeCrash()
    }
}