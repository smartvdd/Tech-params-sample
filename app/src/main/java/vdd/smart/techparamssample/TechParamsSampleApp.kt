package vdd.smart.techparamssample

import android.app.Application
import cn.onlinecache.breakpad.BreakpadManager
import vdd.smart.techparamssample.crash.native.getNativeCrashesCacheDir
import vdd.smart.techparamssample.crash.vm.OwnExceptionHandler

class TechParamsSampleApp : Application() {

    override fun onCreate() {
        super.onCreate()
        OwnExceptionHandler()
        val dumpsDir = getNativeCrashesCacheDir()
        if (!dumpsDir.exists()) {
            dumpsDir.mkdirs()
        }
        BreakpadManager.setup(dumpsDir)
    }

}