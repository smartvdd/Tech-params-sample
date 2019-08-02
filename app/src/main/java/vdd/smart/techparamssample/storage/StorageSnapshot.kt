package vdd.smart.techparamssample.storage

import vdd.smart.techparamssample.storage.app.AppStorageSnapshot

data class StorageSnapshot(
    val totalStorageSize: Long,
    val freeStorageSize: Long,
    val availableStorageSize: Long,
    val appStorageSnapshot: AppStorageSnapshot
) {

    override fun toString(): String = "Total storage size: $totalStorageSize Mb\n" +
            "Free storage size: $freeStorageSize Mb\n" +
            "Available storage size: $availableStorageSize Mb\n" +
            "$appStorageSnapshot"
}