package com.song.xk_one.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.WriterException;
import com.song.xk_one.R;
import com.songping.zxing.encoding.EncodingHandler;

public class ErWeiMaActivity extends BaseActivity implements OnClickListener {
	private ImageView imageView;
	private TextView resultTextView;

	@Override
	public void setView() {
		setContentView(R.layout.activity_erweima);
	}

	@Override
	public void initView() {
		findViewById(R.id.erweima_backBtnId).setOnClickListener(this);
		findViewById(R.id.erweima_sys_btnid).setOnClickListener(this);
		findViewById(R.id.erweima_sc_btnid).setOnClickListener(this);
		imageView = (ImageView) findViewById(R.id.erweima_ivid);
		resultTextView = (TextView) findViewById(R.id.erweima_result_tvid);
	}

	@Override
	public void initData() {

	}

	/**
	 * button点击事件
	 */
	@Override
	public void onClick(View v) {
		int key = v.getId();
		switch (key) {
		case R.id.erweima_backBtnId:
			ActFinder.finishActivity(ErWeiMaActivity.this);
			break;
		case R.id.erweima_sys_btnid:
			// 打开扫描界面扫描条形码或二维码
			Intent openCameraIntent = new Intent(ErWeiMaActivity.this, CaptureActivity.class);
			startActivityForResult(openCameraIntent, 0);
			break;

		case R.id.erweima_sc_btnid:
			try {
				String contentString = "123456";
				if (!contentString.equals("")) {
					// 根据字符串生成二维码图片并显示在界面上，第二个参数为图片的大小（350*350）
					Bitmap qrCodeBitmap = EncodingHandler.createQRCode(contentString, 350);
					imageView.setImageBitmap(qrCodeBitmap);
				} else {
					Toast.makeText(ErWeiMaActivity.this, "Text can not be empty", Toast.LENGTH_SHORT).show();
				}

			} catch (WriterException e) {
				e.printStackTrace();
			}
			break;
		default:
			break;
		}
	}

	/**
	 * 回退键监听事件
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			ActFinder.finishActivity(ErWeiMaActivity.this);
		}
		return false;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		// 处理扫描结果（在界面上显示）
		if (resultCode == RESULT_OK) {
			Bundle bundle = data.getExtras();
			String scanResult = bundle.getString("result");
			resultTextView.setText(scanResult);

			// Intent intent = new Intent();
			// intent.setAction("android.intent.action.VIEW");
			// Uri content_url = Uri.parse(scanResult);
			// intent.setData(content_url);
			// startActivity(intent);
		}
	}

}
