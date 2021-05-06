package androidx.fragment.app

import android.os.Bundle

/**
 * Tries to find saved [FragmentState] in bundle using 'android:support:fragments' key.
 */
fun Bundle.getFragmentsStateList(): List<FragmentBundle>? =
    runCatching {
        val fragmentManagerState =
            getParcelable("android:support:fragments") as? FragmentManagerState
                ?: return emptyList()
        fragmentManagerState.mActive.filter {
            it.mSavedFragmentState != null
        }.map { fragmentState ->
            FragmentBundle(fragmentState.mClassName, fragmentState.mSavedFragmentState)
        }
    }.fold({
        it
    }, { null })