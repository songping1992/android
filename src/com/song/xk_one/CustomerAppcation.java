package com.song.xk_one;

import android.app.Application;

import com.igexin.sdk.PushManager;
import com.song.xk_one.utils.UniversalImageLoaderUtils;

public class CustomerAppcation extends Application {

	public static boolean isRelease = false;// 设置打印日志 ，为true的时候为关闭
	public static final String TAG = CustomerAppcation.class.getSimpleName();

	@Override
	public void onCreate() {
		super.onCreate();
		PushManager.getInstance().initialize(this.getApplicationContext());
		UniversalImageLoaderUtils.initUniversalImageLoader(getApplicationContext());
	}
}
