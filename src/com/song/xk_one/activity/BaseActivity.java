package com.song.xk_one.activity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;
import android.view.Window;
import android.widget.Toast;

import com.song.xk_one.AppManager;
import com.song.xk_one.R;
import com.song.xk_one.theme.PreferenceHelper;
import com.song.xk_one.utils.L;

public abstract class BaseActivity extends FragmentActivity {
	public int mTheme = R.style.AppTheme_Default;
	private static final String TAG = BaseActivity.class.getSimpleName();
	/**
	 * 全局context, 子类Activity使用context
	 */
	public Context mContext;

	public Activity mActivity;

	private IntentFilter intentFilter;
	private BroadcastReceiver br;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		if (savedInstanceState == null) {
			mTheme = PreferenceHelper.getTheme(this);
		} else {
			mTheme = savedInstanceState.getInt("theme");
		}
		setTheme(mTheme);

		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setView();

		mContext = getApplicationContext();
		mActivity = BaseActivity.this;
		AppManager.getAppManager().addActivity(this);
		register();
		initView();
		initData();

	}

	public abstract void setView();

	public abstract void initView();

	public abstract void initData();

	@Override
	public abstract boolean onKeyDown(int keyCode, KeyEvent event);

	protected void finishActivity() {
		AppManager.getAppManager().finishActivity(this);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (br != null) {
			unregisterReceiver(br);
		}
		AppManager.getAppManager().finishActivity(this);
	}

	public void AppExit(Context context) {
		L.v(TAG, "AppExit", "---START--");
		AppManager.getAppManager().AppExit(context);
	}

	private void register() {
		if (intentFilter == null) {
			intentFilter = new IntentFilter();
			intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
		}

		if (br == null) {
			br = new BroadcastReceiver() {

				@Override
				public void onReceive(Context context, Intent intent) {
					ConnectivityManager manager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
					NetworkInfo wifiInfo = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
					NetworkInfo mobileInfo = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
					if (!wifiInfo.isConnected() && !mobileInfo.isConnected()) {
						Toast.makeText(context, "无网络访问", Toast.LENGTH_LONG).show();
					}
				};

			};

			registerReceiver(br, intentFilter);
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		if (mTheme != PreferenceHelper.getTheme(this)) {
			reload();
		}
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putInt("theme", mTheme);
	}

	protected void reload() {
		Intent intent = getIntent();
		overridePendingTransition(0, 0);
		intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
		finish();
		overridePendingTransition(0, 0);
		startActivity(intent);
	}

}
