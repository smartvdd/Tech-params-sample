package vdd.smart.techparamssample.storage.app

import android.annotation.TargetApi
import android.app.usage.StorageStatsManager
import android.content.Context
import android.os.Binder
import android.os.Build
import android.os.storage.StorageManager
import vdd.smart.techparamssample.utils.InformationUnit

@TargetApi(Build.VERSION_CODES.O)
class OreoAppStorageProvider(private val context: Context) : IAppStorageProvider {

    override fun getAppStorage(appStorageListener: AppStorageListener) {
        val storageStatsManager =
            context.getSystemService(Context.STORAGE_STATS_SERVICE) as? StorageStatsManager ?: run {
                appStorageListener.onAppStorageReady(AppStorageSnapshot(0, 0, 0))
                return
            }

        val storageStats = storageStatsManager.queryStatsForPackage(
            StorageManager.UUID_DEFAULT,
            context.packageName,
            Binder.getCallingUserHandle()
        )
        val codeSize = InformationUnit.BYTE.toMB(storageStats.appBytes)
        val cacheSize = InformationUnit.BYTE.toMB(storageStats.cacheBytes)
        val dataSize = InformationUnit.BYTE.toMB(storageStats.dataBytes)
        appStorageListener.onAppStorageReady(AppStorageSnapshot(codeSize, cacheSize, dataSize))
    }
}