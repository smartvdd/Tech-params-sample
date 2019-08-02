package vdd.smart.techparamssample.storage

interface StorageSnapshotListener {

    fun onStorageReady(storageSnapshot: StorageSnapshot)

}