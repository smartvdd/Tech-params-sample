package cn.onlinecache.breakpad;

import java.io.File;

public class BreakpadManager {
	private static boolean mWasSetup;

	
	public static void setup(File file) {
		if (mWasSetup) {
			return;
		}
		
		try {
			NativeBreakpad.init(file.getAbsolutePath());
			mWasSetup = true;
		} catch(Throwable e) {

		}
	}
	
	public static void testNativeCrash() {
		NativeBreakpad.testNativeCrash();
	}
}
