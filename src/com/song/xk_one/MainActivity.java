package com.song.xk_one;

import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.igexin.sdk.PushManager;
import com.song.xk_one.activity.ActFinder;
import com.song.xk_one.activity.BaseActivity;

public class MainActivity extends BaseActivity implements OnItemClickListener {
	// private String TAG = MainActivity.class.getSimpleName();
	private int[] draws = { R.drawable.apps_beiwang, R.drawable.apps_chakan, R.drawable.apps_diaocha, R.drawable.apps_gonggao, R.drawable.apps_qiandao, R.drawable.apps_set, R.drawable.apps_tixing,
			R.drawable.apps_yiban };
	private GridView gv;
	private GvAdapter adapter;
	private String[] strings = { "拼音", "XML", "写文件", "viewpager", "二维码", "主题", "1", "1" };

	@Override
	public void initView() {
		gv = (GridView) findViewById(R.id.main_gvId);
		gv.setOnItemClickListener(this);
	}

	@Override
	public void initData() {
		PushManager.getInstance().initialize(this.getApplicationContext());
		adapter = new GvAdapter();
		gv.setAdapter(adapter);
		adapter.notifyDataSetChanged();
	}

	/**
	 * adapter
	 */
	public class GvAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return draws.length;
		}

		@Override
		public Object getItem(int position) {
			return draws[position];
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder = null;
			if (convertView == null) {
				holder = new ViewHolder();
				convertView = View.inflate(MainActivity.this, R.layout.activity_main_item, null);
				holder.iv = (ImageView) convertView.findViewById(R.id.main_item_ivId);
				holder.tv = (TextView) convertView.findViewById(R.id.main_item_tvId);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			holder.iv.setImageResource(draws[position]);
			holder.tv.setText(strings[position]);
			return convertView;
		}
	}

	public class ViewHolder {
		private ImageView iv;
		private TextView tv;
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			AppManager.getAppManager().AppExit(MainActivity.this);
		}
		return false;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		switch (position) {
		case 0:// 汉字转拼音
			ActFinder.startPinyinActivity(MainActivity.this);
			break;
		case 1:// xml解析
			ActFinder.startOneActivity(MainActivity.this);
			break;
		case 2:// 文件读写
			ActFinder.startFileActivity(MainActivity.this);
			break;
		case 3:
			// viewpager滑动
			ActFinder.startViewPagerActivity(MainActivity.this);
			break;
		case 4:
			// 二维码
			ActFinder.startErWeiMaActivity(MainActivity.this);
			break;
		case 5:
			// 主题切换
			ActFinder.startThemeActivity(MainActivity.this);
			break;
		default:
			break;
		}
	}

	@Override
	public void setView() {
		setContentView(R.layout.activity_main);
	}
}
