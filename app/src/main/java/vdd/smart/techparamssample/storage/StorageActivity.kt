package vdd.smart.techparamssample.storage

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.storage_activtiy.*
import vdd.smart.techparamssample.R

class StorageActivity : AppCompatActivity(), LayoutContainer {

    override val containerView: View
        get() = window.decorView.rootView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.storage_activtiy)
        val storageProvider = StorageProvider(this)
        storageInfoButton.setOnClickListener {
            storageProvider.getStorageSnapshot(object : StorageSnapshotListener {
                override fun onStorageReady(storageSnapshot: StorageSnapshot) {
                    this@StorageActivity.runOnUiThread {
                        storageInfoTextView.text = storageSnapshot.toString()
                    }
                }
            })
        }
    }
}