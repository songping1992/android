package com.song.xk_one.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.song.xk_one.R;

public class ActFinder {

	public ActFinder() {
		super();
	}

	public static void getOutActivity(Context context) {
		((Activity) context).overridePendingTransition(R.anim.slide_in_from_left, R.anim.slide_out_to_right);
	}

	public static void getInActivity(Context context) {
		((Activity) context).overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
	}

	public static void finishActivity(Activity activity) {
		activity.finish();
		getOutActivity(activity);
	}

	public static void startOneActivity(Context context) {
		Intent intent = new Intent(context, XmlActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(intent);
		getInActivity(context);
	}

	public static void startPinyinActivity(Context context) {
		Intent intent = new Intent(context, PinyinActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(intent);
		getInActivity(context);
	}

	public static void startFileActivity(Context context) {
		Intent intent = new Intent(context, FileActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(intent);
		getInActivity(context);
	}

	public static void startViewPagerActivity(Context context) {
		Intent intent = new Intent(context, ViewPagerActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(intent);
		getInActivity(context);
	}

	public static void startErWeiMaActivity(Context context) {
		Intent intent = new Intent(context, ErWeiMaActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(intent);
		getInActivity(context);
	}

	public static void startThemeActivity(Context context) {
		Intent intent = new Intent(context, ThemeActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(intent);
		getInActivity(context);
	}

}
