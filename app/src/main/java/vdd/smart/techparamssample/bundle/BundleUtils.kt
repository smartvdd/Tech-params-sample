package vdd.smart.techparamssample.bundle

import android.os.Bundle
import android.os.Parcel

fun Bundle.getSizeInBytes(): Int {
    val parcel = Parcel.obtain()
    return try {
        parcel.writeValue(this)
        parcel.dataSize()
    } finally {
        parcel.recycle()
    }
}