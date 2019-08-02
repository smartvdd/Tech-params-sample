package vdd.smart.techparamssample.ram

import android.app.ActivityManager
import android.content.Context
import android.os.Process
import vdd.smart.techparamssample.utils.InformationUnit

fun Context.getRAMSnapshot(): RAMSnapshot {
    val memoryInfo = ActivityManager.MemoryInfo()
    val activityManager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager

    activityManager.getMemoryInfo(memoryInfo)
    val totalSystemMemory = InformationUnit.BYTE.toMB(memoryInfo.totalMem)
    val freeSystemMemory = InformationUnit.BYTE.toMB(memoryInfo.availMem)
    val usedSystemMemory = InformationUnit.BYTE.toMB(memoryInfo.totalMem - memoryInfo.availMem)

    val processInfo = activityManager.getProcessMemoryInfo(arrayOf(Process.myPid()).toIntArray())
    val usedProcessMemory = InformationUnit.KB.toMB(processInfo.map { it.totalPss }.sum().toLong())

    return RAMSnapshot(freeSystemMemory.toInt(),
        usedSystemMemory.toInt(),
        totalSystemMemory.toInt(),
        usedProcessMemory.toInt())
}