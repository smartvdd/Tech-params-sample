package vdd.smart.techparamssample.storage.app

import android.content.Context
import android.content.pm.IPackageStatsObserver
import android.content.pm.PackageStats
import vdd.smart.techparamssample.utils.InformationUnit

class BaseAppStorageProvider(private val context: Context) : IAppStorageProvider {

    override fun getAppStorage(appStorageListener: AppStorageListener) {
        val packageManager = context.packageManager
        val getPackageSizeInfo = packageManager::class.java.getMethod(
            "getPackageSizeInfo",
            String::class.java,
            IPackageStatsObserver::class.java
        )
        getPackageSizeInfo.invoke(packageManager, context.packageName, PackageStatsObserver(appStorageListener))
    }

    private class PackageStatsObserver(private val appStorageListener: AppStorageListener) :
        IPackageStatsObserver.Stub() {
        override fun onGetStatsCompleted(packageStats: PackageStats, succeeded: Boolean) {
            val codeSize = InformationUnit.BYTE.toMB(packageStats.codeSize)
            val cacheSize = InformationUnit.BYTE.toMB(packageStats.cacheSize)
            val dataSize = InformationUnit.BYTE.toMB(packageStats.dataSize)
            appStorageListener.onAppStorageReady(AppStorageSnapshot(codeSize, cacheSize, dataSize))
        }
    }

}