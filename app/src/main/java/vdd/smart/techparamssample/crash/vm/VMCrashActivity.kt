package vdd.smart.techparamssample.crash.vm

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.vm_crash_activity.*
import vdd.smart.techparamssample.R

class VMCrashActivity : AppCompatActivity(), LayoutContainer {

    override val containerView: View
        get() = window.decorView.rootView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.vm_crash_activity)
        runtimeExceptionButton.setOnClickListener {
            throw RuntimeException()
        }
        illegalStateExceptionButton.setOnClickListener {
            throw IllegalStateException()
        }
        nullPointerExceptionButton.setOnClickListener {
            throw NullPointerException()
        }
    }
}