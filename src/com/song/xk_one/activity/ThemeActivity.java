package com.song.xk_one.activity;

import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.song.xk_one.R;
import com.song.xk_one.theme.PreferenceHelper;

public class ThemeActivity extends BaseActivity implements OnClickListener {
	private String TAG = ThemeActivity.class.getSimpleName();

	@Override
	public void setView() {
		setContentView(R.layout.activity_title);
	}

	@Override
	public void initView() {

		findViewById(R.id.tt_backBtnId).setOnClickListener(this);
		findViewById(R.id.theme_one_btnid).setOnClickListener(this);
		findViewById(R.id.theme_two_btnid).setOnClickListener(this);
		findViewById(R.id.theme_three_btnid).setOnClickListener(this);

	}

	@Override
	public void initData() {

	}

	@Override
	public void onClick(View v) {
		int key = v.getId();
		switch (key) {
		case R.id.tt_backBtnId:
			ActFinder.finishActivity(ThemeActivity.this);
			break;
		case R.id.theme_one_btnid:
			Toast.makeText(ThemeActivity.this, "主题1", Toast.LENGTH_LONG).show();
			com.song.xk_one.theme.PreferenceHelper.setTheme(ThemeActivity.this, R.style.AppTheme_Default);
			reload();
			break;
		case R.id.theme_two_btnid:
			Toast.makeText(ThemeActivity.this, "主题2", Toast.LENGTH_LONG).show();
			PreferenceHelper.setTheme(ThemeActivity.this, R.style.AppTheme_Another);
			reload();
			break;
		case R.id.theme_three_btnid:
			Toast.makeText(ThemeActivity.this, "主题3", Toast.LENGTH_LONG).show();
			PreferenceHelper.setTheme(ThemeActivity.this, R.style.AppTheme_three);
			reload();
			break;

		default:
			break;
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			ActFinder.finishActivity(ThemeActivity.this);
		}
		return false;
	}

}
