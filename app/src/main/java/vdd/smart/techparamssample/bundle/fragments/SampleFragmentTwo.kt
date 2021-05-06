package vdd.smart.techparamssample.bundle.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import vdd.smart.techparamssample.R


class SampleFragmentTwo : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.two_fragment, container, true)

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(
            "2",
            "123456789123456789123456789123456789123456789123456789123456789123456789123456789123456789"
        )
    }
}