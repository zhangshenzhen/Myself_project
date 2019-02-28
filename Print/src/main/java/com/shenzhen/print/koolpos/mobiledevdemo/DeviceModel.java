package com.shenzhen.print.koolpos.mobiledevdemo;

import android.os.Build;

public class DeviceModel {

	public static boolean isPOSMobile() {
		return isP8000() || isN900() || isA8() || isI9000s() || isP2000();
	}

	public static boolean isPOSPAD() {
		return isKool10() || isKool11();
	}

	public static boolean isPOS() {
		return isPOSPAD() || isPOSMobile();
	}

	/**
	 * 客显
	 */
	public static boolean isKoolPad() {
		return Build.MODEL.contains("kool-desktop");
	}

	public static boolean isKool10() {
		return Build.MODEL.contains("koolpos-par10");
	}

	/**
	 * 酷11
	 */
	public static boolean isKool11() {
		return Build.MODEL.contains("KoolRegister")
				|| Build.MODEL.contains("Kool11");
	}

	public static boolean isP8000() {
		return Build.MODEL.equals("P8000") || Build.MODEL.equals("P8000S");
	}

	public static boolean isP2000() {
		return Build.MODEL.equals("P2000");
	}

	public static boolean isN900() {
		return Build.MODEL.equals("N900");
	}

	public static boolean isA8() {
		return Build.MODEL.equals("APOS A8");
	}

	public static boolean isI9000s() {
		return Build.MODEL.equals("SQ27");
	}
}
