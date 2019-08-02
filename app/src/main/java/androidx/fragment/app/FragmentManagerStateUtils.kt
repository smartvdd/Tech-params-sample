package androidx.fragment.app

import android.os.Bundle

/**
 * Tries to find saved [FragmentState] in bundle using 'android:support:fragments' key.
 */
fun Bundle.getFragmentsStateList(): List<FragmentBundle>? {
	try {
		val fragmentManagerState: FragmentManagerState? = getParcelable("android:support:fragments")
		val active = fragmentManagerState?.mActive ?: return emptyList()

		return active.filter {
			it.mSavedFragmentState != null
		}.map { fragmentState ->
			FragmentBundle(fragmentState.mClassName, fragmentState.mSavedFragmentState)
		}
	} catch (throwable: Throwable) {
		return null
	}

}