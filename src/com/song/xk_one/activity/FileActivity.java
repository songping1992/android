package com.song.xk_one.activity;

import android.os.Environment;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.song.xk_one.R;
import com.song.xk_one.utils.FileUtils;
import com.song.xk_one.utils.L;

public class FileActivity extends BaseActivity implements OnClickListener {
	private String TAG = FileActivity.class.getSimpleName();
	private String wenjian = "所谓好友体系，是指谁是谁的好友的关系体系。环信提供好友体系，但不是必须使用的，仅在需要控制消息发送权限时才启动。比如对一个企业内部移动办公APP来说,因为企业内部同事是彼此认识的，那么此APP可能是不需要消息发送权限控制的。即任何人都可以给任何人发消息。但一个交友类的APP就必须要控制只有我的好友才能给我发消息，不是我的好友的人需要向我发送加好友邀请，我批准后才能给我发消息。这种情况下，就需要启用环信提供的好友体系。";
	private String pathStr = "";

	@Override
	public void initView() {
		findViewById(R.id.file_backBtnId).setOnClickListener(this);
		findViewById(R.id.file_zhuanBtnId).setOnClickListener(this);
		findViewById(R.id.file_isCzBtnId).setOnClickListener(this);
		findViewById(R.id.file_delete_btnid).setOnClickListener(this);

	}

	@Override
	public void initData() {
		L.v(TAG, "--path", getExternalCacheDir().toString());// sd卡上的路径：/storage/sdcard0/Android/data/com.song.xk_one/cache
		L.v(TAG, "--path2", getCacheDir().toString());// 手机内存中的路径/data/data/com.song.xk_one/cache
		L.v(TAG, "--publicPath", Environment.getExternalStoragePublicDirectory("song").toString());
	}

	public void writeToCard() {
		pathStr = FileUtils.toWriteFile(FileActivity.this, "songping.txt");
		FileUtils.writeFile(pathStr, wenjian);
	}

	public boolean isCz() {
		boolean flag = FileUtils.isFileExist(pathStr);
		Toast.makeText(FileActivity.this, flag + "", Toast.LENGTH_LONG).show();
		return flag;
	}

	@Override
	public void onClick(View v) {
		int key = v.getId();
		switch (key) {
		case R.id.file_backBtnId:
			ActFinder.finishActivity(FileActivity.this);
			break;
		case R.id.file_zhuanBtnId:
			writeToCard();
			break;
		case R.id.file_isCzBtnId:
			isCz();
			break;
		case R.id.file_delete_btnid:
			if (isCz()) {
				boolean flag = FileUtils.deleteFile(pathStr);
				Toast.makeText(FileActivity.this, flag + "", Toast.LENGTH_LONG).show();
			} else {
				Toast.makeText(FileActivity.this, "文件", Toast.LENGTH_LONG).show();
			}
			break;
		default:
			break;
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			ActFinder.finishActivity(FileActivity.this);
		}
		return false;
	}

	@Override
	public void setView() {
		setContentView(R.layout.activity_file);
	}

}
