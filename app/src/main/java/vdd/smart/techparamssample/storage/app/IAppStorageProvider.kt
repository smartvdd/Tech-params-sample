package vdd.smart.techparamssample.storage.app

interface IAppStorageProvider {

    fun getAppStorage(appStorageListener: AppStorageListener)

}