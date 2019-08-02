package vdd.smart.techparamssample.ram

data class RAMSnapshot(
    val freeSystemMemoryInMb: Int,
    val usedSystemMemoryInMb: Int,
    val totalSystemMemoryInMb: Int,
    val usedProcessMemoryInMb: Int
) {
    override fun toString(): String = "Total device RAM: $totalSystemMemoryInMb Mb\n" +
            "Used device RAM: $usedSystemMemoryInMb Mb\n" +
            "Free device RAM: $freeSystemMemoryInMb Mb\n" +
            "Used RAM by app: $usedProcessMemoryInMb Mb"

}