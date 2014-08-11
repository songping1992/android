package com.song.xk_one.activity;

import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.song.xk_one.R;
import com.song.xk_one.utils.L;
import com.song.xk_one.utils.PinyinUtilsTwo;

public class PinyinActivity extends BaseActivity implements OnClickListener {
	private String TAG = PinyinActivity.class.getSimpleName();
	private TextView tv;
	private EditText et;

	// @Override
	// protected void onCreate(Bundle savedInstanceState) {
	// super.onCreate(savedInstanceState);
	// requestWindowFeature(Window.FEATURE_NO_TITLE);
	// setContentView(R.layout.activity_pinyin);
	// initView();
	// initData();
	// }

	@Override
	public void initView() {
		findViewById(R.id.pinyin_backBtnId).setOnClickListener(this);
		findViewById(R.id.pinyin_zhuanhuanBtnId).setOnClickListener(this);
		tv = (TextView) findViewById(R.id.pinyin_tvId);
		et = (EditText) findViewById(R.id.pinyin_etId);
	}

	@Override
	public void initData() {
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			ActFinder.finishActivity(PinyinActivity.this);
		}
		return false;
	}

	@Override
	public void onClick(View v) {
		int key = v.getId();
		switch (key) {
		case R.id.pinyin_backBtnId:
			ActFinder.finishActivity(PinyinActivity.this);
			break;
		case R.id.pinyin_zhuanhuanBtnId:
			String pinStr = et.getText().toString().trim();
			if (!TextUtils.isEmpty(pinStr)) {
				try {
					String string = PinyinUtilsTwo.getPinyinStr(pinStr);
					tv.setText(string);
				} catch (NullPointerException e) {
					L.v(TAG, "--data", "--异常");
					Toast.makeText(PinyinActivity.this, "用户名不符合规矩", Toast.LENGTH_LONG).show();
				}
			}
			break;
		default:
			break;
		}
	}

	@Override
	public void setView() {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_pinyin);
	}

}
