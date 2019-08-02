package vdd.smart.techparamssample.crash.native

import android.content.Context
import android.os.Environment
import java.io.File

private var providedCacheDir: File? = null

fun Context.getNativeCrashesCacheDir(): File {
    return File(provideCacheDir(), "nCrash")
}

private fun Context.provideCacheDir(): File? {
    if (providedCacheDir != null) {
        return providedCacheDir
    }
    if (Environment.MEDIA_MOUNTED == Environment.getExternalStorageState()) {
        providedCacheDir = try {
            this.externalCacheDir
        } catch (t: Throwable) {
            null
        }
    }
    if (providedCacheDir == null) {
        providedCacheDir = this.cacheDir
    }
    return providedCacheDir
}