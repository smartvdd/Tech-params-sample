package vdd.smart.techparamssample.storage.app

interface AppStorageListener {

    fun onAppStorageReady(appStorageSnapshot: AppStorageSnapshot)

}