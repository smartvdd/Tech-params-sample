package vdd.smart.techparamssample.bundle

import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.getFragmentsStateList
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.bundle_activity.*
import vdd.smart.techparamssample.R

class BundleActivity : AppCompatActivity(), LayoutContainer {
    override val containerView: View
        get() = window.decorView.rootView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.bundle_activity)
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        /**
         * [onSaveInstanceState] is better place to track [Bundle] size
         *
         * In this sample [onRestoreInstanceState] uses for UI example only
         */
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val activityBundleInfo = "Activity bundle size: ${savedInstanceState.getSizeInBytes()} bytes.\n"
        val fragmentsBundleInfo =
            savedInstanceState.getFragmentsStateList()
                ?.joinToString(separator = ",\n") { "${it.name} ${it.bundle.getSizeInBytes()} bytes" }
        bundleInfoTextView.text = activityBundleInfo.plus(fragmentsBundleInfo)
    }


}