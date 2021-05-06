package vdd.smart.techparamssample.storage

import android.content.Context
import android.os.Build
import android.os.Environment
import android.os.StatFs
import vdd.smart.techparamssample.storage.app.*
import vdd.smart.techparamssample.utils.InformationUnit

class StorageProvider(private val context: Context) {

    private val appStorageProvider: IAppStorageProvider by lazy {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            OreoAppStorageProvider(context)
        } else {
            BaseAppStorageProvider(context)
        }
    }

    fun getStorageSnapshot(storageSnapshotListener: StorageSnapshotListener) {
        val stat = StatFs(Environment.getDataDirectory().path)
        appStorageProvider.getAppStorage(object : AppStorageListener {
            override fun onAppStorageReady(appStorageSnapshot: AppStorageSnapshot) {
                val totalStorageSize = InformationUnit.BYTE.toMB(stat.totalBytes)
                val freeStorageSize = InformationUnit.BYTE.toMB(stat.freeBytes)
                val availableStorageSize = InformationUnit.BYTE.toMB(stat.availableBytes)
                val storageSnapshot =
                    StorageSnapshot(
                        totalStorageSize,
                        freeStorageSize,
                        availableStorageSize,
                        appStorageSnapshot
                    )
                storageSnapshotListener.onStorageReady(storageSnapshot)
            }
        })
    }

}