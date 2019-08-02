package vdd.smart.techparamssample.storage.app

data class AppStorageSnapshot(
    val codeSize: Long,
    val cacheSize: Long,
    val dataSize: Long
) {

    override fun toString(): String = "Code size: $codeSize Mb\n" +
            "Cache size: $cacheSize Mb\n" +
            "Data size: $dataSize Mb"
}