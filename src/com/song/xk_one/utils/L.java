package com.song.xk_one.utils;

import android.annotation.SuppressLint;
import android.util.Log;

import com.song.xk_one.CustomerAppcation;

/**
 * 打印log日志 可关闭,e红色 w橙色 i绿色 d蓝色 v黑色
 */

@SuppressLint("DefaultLocale")
public class L {

	// !!! NOTE !!!
	// TODO: set it true when build for release version
	public final static boolean mode_for_release = CustomerAppcation.isRelease;
	public final static boolean server_switch = true;
	public final static String TAG = "songping";

	public static void v(String tag, String msg) {
		if (!mode_for_release)
			Log.v(tag, msg);
	}

	public static void v(String tag, String type, String msg) {
		if (!mode_for_release) {
			String des = String.format("[%s][%s]%s", tag, type, msg);
			Log.v(TAG, des);
		}
	}

	public static void v(String tag, String type, String msg, String msg1) {
		if (!mode_for_release) {
			String des = String.format("[%s][%s]%s%s", tag, type, msg, msg1);
			Log.v(TAG, des);
		}
	}

	public static void v(String tag, String type, int msg) {
		if (!mode_for_release) {
			String des = String.format("[%s][%s]%d", tag, type, msg);
			Log.v(TAG, des);
		}
	}

	public static void v(String tag, String type, boolean msg) {
		if (!mode_for_release) {
			String des = String.format("[%s][%s]%s", tag, type, msg ? "true" : "false");
			Log.v(TAG, des);
		}
	}

	public static void i(String tag, String type, String msg) {
		if (!mode_for_release) {
			String des = String.format("[%s][%s]%s", tag, type, msg);
			Log.i(TAG, des);
		}
	}

	@SuppressLint("DefaultLocale")
	public static void i(String tag, String type, int msg) {
		if (!mode_for_release) {
			String des = String.format("[%s][%s]%d", tag, type, msg);
			Log.v(TAG, des);
		}
	}

	public static void i(String tag, String msg) {
		i(tag, "", msg);
	}

	public static void i(String tag, String type, boolean msg) {
		if (!mode_for_release) {
			String des = String.format("[%s][%s]%s", tag, type, msg ? "true" : "false");
			Log.v(TAG, des);
		}
	}

	public static void e(String tag, String type, String msg) {
		if (!mode_for_release) {
			String des = String.format("[%s][%s]%s", tag, type, msg);
			Log.e(TAG, des);
		}
	}

	@SuppressLint("DefaultLocale")
	public static void e(String tag, String type, int msg) {
		if (!mode_for_release) {
			String des = String.format("[%s][%s]%d", tag, type, msg);
			Log.e(TAG, des);
		}
	}

	@SuppressLint("DefaultLocale")
	public static void e(String tag, String type, boolean msg) {
		if (!mode_for_release) {
			String des = String.format("[%s][%s]%d", tag, type, msg ? "true" : "false");
			Log.e(TAG, des);
		}
	}
}
