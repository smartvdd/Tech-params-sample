package vdd.smart.techparamssample.ram

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.ram_activity.*
import vdd.smart.techparamssample.R

class RAMActivity : AppCompatActivity(), LayoutContainer {

    override val containerView: View
        get() = window.decorView.rootView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ram_activity)
        ramInfoButton.setOnClickListener {
            ramInfoTextView.text = this@RAMActivity.getRAMSnapshot().toString()
        }
    }

}