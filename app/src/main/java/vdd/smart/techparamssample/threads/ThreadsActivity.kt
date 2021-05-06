package vdd.smart.techparamssample.threads

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.threads_activity.*
import vdd.smart.techparamssample.R

class ThreadsActivity : AppCompatActivity(), LayoutContainer {

    override val containerView: View
        get() = window.decorView.rootView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.threads_activity)
        threadsInfoButton.setOnClickListener {
            val threads = Thread.getAllStackTraces()
            threadsCountTextView.text = String.format("Threads count: %s", threads.size)
            val info =
                threads.map { "${it.key.name} ${it.key.state}" }.joinToString(separator = ",\n")
            threadsInfoTextView.text = info
        }
    }
}