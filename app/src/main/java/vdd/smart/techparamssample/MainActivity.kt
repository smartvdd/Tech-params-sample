package vdd.smart.techparamssample

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.activity_main.*
import vdd.smart.techparamssample.battery.BatteryActivity
import vdd.smart.techparamssample.bundle.BundleActivity
import vdd.smart.techparamssample.crash.native.NativeCrashActivity
import vdd.smart.techparamssample.crash.vm.VMCrashActivity
import vdd.smart.techparamssample.fps.FrameRateActivity
import vdd.smart.techparamssample.ram.RAMActivity
import vdd.smart.techparamssample.storage.StorageActivity
import vdd.smart.techparamssample.threads.ThreadsActivity

class MainActivity : AppCompatActivity(), LayoutContainer {

    override val containerView: View
        get() = window.decorView.rootView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initOnClickListeners()
    }

    private fun initOnClickListeners() {
        vmCrashButton.setOnClickListener {
            startActivity(Intent(this, VMCrashActivity::class.java))
        }
        nativeCrashButton.setOnClickListener {
            startActivity(Intent(this, NativeCrashActivity::class.java))
        }
        threadsButton.setOnClickListener {
            startActivity(Intent(this, ThreadsActivity::class.java))
        }
        ramButton.setOnClickListener {
            startActivity(Intent(this, RAMActivity::class.java))
        }
        storageButton.setOnClickListener {
            startActivity(Intent(this, StorageActivity::class.java))
        }
        batteryButton.setOnClickListener {
            startActivity(Intent(this, BatteryActivity::class.java))
        }
        frameRateButton.setOnClickListener {
            startActivity(Intent(this, FrameRateActivity::class.java))
        }
        bundleButton.setOnClickListener {
            startActivity(Intent(this, BundleActivity::class.java))
        }
    }
}
