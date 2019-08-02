package vdd.smart.techparamssample.battery

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.battery_activity.*
import vdd.smart.techparamssample.R


class BatteryActivity : AppCompatActivity(), LayoutContainer {

    override val containerView: View
        get() = window.decorView.rootView

    private val batteryChangeReceiver: BatteryChangeReceiver = BatteryChangeReceiver(::onBatteryChanged)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.battery_activity)

        /**
         * Use this if need to get battery status immediately
         */
        registerReceiver(null, IntentFilter(Intent.ACTION_BATTERY_CHANGED))?.let {
            onBatteryChanged(it)
        }

        registerReceiver(batteryChangeReceiver, IntentFilter(Intent.ACTION_BATTERY_CHANGED))
    }

    private fun onBatteryChanged(intent: Intent) {
        batteryInfoTextView.text = BatteryStatus.fromIntent(intent).toString()
    }

    override fun onDestroy() {
        unregisterReceiver(batteryChangeReceiver)
        super.onDestroy()
    }

    private class BatteryChangeReceiver(private val onBatteryChanged: (Intent) -> Unit) : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            onBatteryChanged.invoke(intent)
        }
    }
}