package vdd.smart.techparamssample.crash.native

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import cn.onlinecache.breakpad.BreakpadManager
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.native_crash_activity.*
import vdd.smart.techparamssample.R
import java.io.File

class NativeCrashActivity : AppCompatActivity(), LayoutContainer {

    override val containerView: View
        get() = window.decorView.rootView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.native_crash_activity)
        nativeCrashButton.setOnClickListener {
            BreakpadManager.testNativeCrash()
        }
        clearDumpsDirButton.setOnClickListener {
            getNativeCrashesCacheDir().listFiles()?.forEach(File::delete)
            updateNativeCrashesCount()
        }
        updateNativeCrashesCount()
    }

    private fun updateNativeCrashesCount() {
        nativeCrashCounter.text = "${getNativeCrashesCacheDir().listFiles()?.size ?: 0}"
    }
}