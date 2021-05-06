package cn.onlinecache.breakpad

import android.util.Log

object NativeBreakpad {
    private const val TAG = "NativeBreakpad"
    private var loadBreakpadSuccess = false

    init {
        runCatching {
            System.loadLibrary("breakpad")
        }.fold({
            loadBreakpadSuccess = true
        }, {
            loadBreakpadSuccess = false
            Log.e(TAG, "fail to load breakpad")
        })
    }

    /**
     * init breakpad
     *
     * @param dumpFileDir the directory of dump file
     * @return true: init success  false: init fail
     */
    fun init(dumpFileDir: String): Boolean =
        if (dumpFileDir.isEmpty()) {
            Log.e(TAG, "dumpFileDir can not be empty")
            false
        } else loadBreakpadSuccess &&
                nativeInit(dumpFileDir) == 0

    private external fun nativeInit(dumpFileDir: String?): Int

    /**
     * don't use this method in your production app!!
     */
    fun testNativeCrash(): Int =
        if (loadBreakpadSuccess) {
            Log.d(TAG, "test native crash .......................")
            nativeTestCrash()
        } else -1

    private external fun nativeTestCrash(): Int

}