package vdd.smart.techparamssample.battery

import android.content.Intent
import android.os.BatteryManager

data class BatteryStatus(
    val percentage: Float,
    val voltage: Float,
    val temperature: Float,
    val isCharging: Boolean
) {

    companion object {

        fun fromIntent(intent: Intent): BatteryStatus {
            val level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, Int.MIN_VALUE)
            val scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, Int.MIN_VALUE)
            val percentage = if (level == Int.MIN_VALUE || scale == Int.MIN_VALUE) {
                -1f
            } else {
                level * 100 / scale.toFloat()
            }

            val voltage = intent.getIntExtra(BatteryManager.EXTRA_VOLTAGE, Int.MIN_VALUE) / 1000f
            val temperature =
                intent.getIntExtra(BatteryManager.EXTRA_TEMPERATURE, Int.MIN_VALUE) / 10f
            val status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, Int.MIN_VALUE)
            val isCharging = status == BatteryManager.BATTERY_STATUS_CHARGING
            return BatteryStatus(percentage, voltage, temperature, isCharging)
        }

    }

    override fun toString(): String = "Battery percentage: $percentage %\n" +
            "Battery voltage: $voltage V\n" +
            "Battery temperature: $temperature C\n" +
            "Battery is charging: $isCharging"
}