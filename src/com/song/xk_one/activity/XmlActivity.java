package com.song.xk_one.activity;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParser;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.text.TextUtils;
import android.util.Xml;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.song.xk_one.R;
import com.song.xk_one.XListView.XListView;
import com.song.xk_one.XListView.XListView.IXListViewListener;
import com.song.xk_one.adapter.OneAdapterS;
import com.song.xk_one.bean.OschinaBean;
import com.song.xk_one.bean.TweetBean;
import com.song.xk_one.utils.L;
import com.song.xk_one.utils.URLS;
import com.song.xk_one.utils.VolleyUtils;
import com.song.xk_one.utils.VolleyUtils.VolleyResponse;

@SuppressLint("SimpleDateFormat")
public class XmlActivity extends BaseActivity implements OnItemClickListener, OnClickListener, IXListViewListener {
	private String TAG = XmlActivity.class.getSimpleName();
	private String url = URLS.text_url_index;
	private VolleyUtils vUtils;
	private XListView mLv;
	private OneAdapterS adapter;
	private int PAGE_SIZE = 1;
	private ArrayList<TweetBean> tweetBeans = new ArrayList<TweetBean>();
	private ProgressDialog mDialog;

	@Override
	public void initView() {
		mLv = (XListView) findViewById(R.id.one_lvId);
		findViewById(R.id.one_backBtnId).setOnClickListener(this);
		mLv.setOnItemClickListener(this);
		// 设置上拉刷新可用
		mLv.setPullLoadEnable(true);
		// 设置下拉刷新可用
		mLv.setPullRefreshEnable(true);
		mLv.setXListViewListener(this);
	}

	@Override
	public void initData() {

		mDialog = new ProgressDialog(XmlActivity.this);
		mDialog.setMessage("正在加载中...");
		vUtils = VolleyUtils.getInstance(XmlActivity.this);
		adapter = new OneAdapterS(XmlActivity.this, tweetBeans, R.layout.activity_one_item);
		getData();
	}

	/**
	 * 进入页面获取数据
	 */
	public void getData() {
		L.v(TAG, "--http 开始", "");
		String urlStr = url + PAGE_SIZE + URLS.text_url_pagesize;
		L.v(TAG, "--url", urlStr);
		mDialog.show();
		vUtils.doGetRequest(urlStr, new VolleyResponse() {

			@Override
			public void getResponse(String response) {
				mDialog.dismiss();
				L.v(TAG, "--data", response);
				OschinaBean bean = new OschinaBean();
				byte[] data = response.getBytes();
				XmlPullParser parser = Xml.newPullParser();
				try {
					InputStream im = new ByteArrayInputStream(data);
					parser.setInput(im, "UTF-8");
					bean.XMLParseResponse(parser);
					tweetBeans.addAll(bean.tweets);
					// adapter.bindData(tweetBeans);
					mLv.setAdapter(adapter);
					adapter.notifyDataSetChanged();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * 刷新时获取数据；0：下拉，1：上拉
	 */
	public void getRefreshData(int flag) {
		if (flag == 0) {
			PAGE_SIZE = 1;
			String urlStr = url + PAGE_SIZE + URLS.text_url_pagesize;

			vUtils.doGetRequest(urlStr, new VolleyResponse() {

				@Override
				public void getResponse(String response) {
					tweetBeans.clear();
					onLoad();
					OschinaBean bean = new OschinaBean();
					byte[] data = response.getBytes();
					XmlPullParser parser = Xml.newPullParser();
					try {
						InputStream im = new ByteArrayInputStream(data);
						parser.setInput(im, "UTF-8");
						bean.XMLParseResponse(parser);
						tweetBeans.addAll(bean.tweets);
						L.v(TAG, "--data", bean.toString());
						mLv.setAdapter(adapter);
						adapter.notifyDataSetChanged();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		} else if (flag == 1) {
			PAGE_SIZE++;
			String urlStr = url + PAGE_SIZE + URLS.text_url_pagesize;
			L.v(TAG, "--urlstr", urlStr);
			vUtils.doGetRequest(urlStr, new VolleyResponse() {

				@Override
				public void getResponse(String response) {
					onLoad();
					if (!TextUtils.isEmpty(response)) {
						OschinaBean bean = new OschinaBean();
						byte[] data = response.getBytes();
						XmlPullParser parser = Xml.newPullParser();
						try {
							InputStream im = new ByteArrayInputStream(data);
							parser.setInput(im, "UTF-8");
							bean.XMLParseResponse(parser);
							// List<TweetBean> tweetBeans2 = new
							// ArrayList<TweetBean>();
							// tweetBeans2 = bean.tweets;

							tweetBeans.addAll(bean.tweets);
							L.v(TAG, "--size", tweetBeans.size());
							// pter.bindData(tweetBeans);
							adapter.notifyDataSetChanged();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}

				}
			});
		}
	}

	@Override
	public void onClick(View v) {
		int key = v.getId();
		switch (key) {
		case R.id.one_backBtnId:
			ActFinder.finishActivity(XmlActivity.this);
			break;

		default:
			break;
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			ActFinder.finishActivity(XmlActivity.this);
		}
		return false;
	}

	/**
	 * 上拉刷新
	 */
	@Override
	public void onRefresh() {
		getRefreshData(0);
	}

	/**
	 * 下拉加载
	 */
	@Override
	public void onLoadMore() {
		getRefreshData(1);
	}

	private void onLoad() {
		mLv.stopRefresh();
		mLv.stopLoadMore();
		mLv.setRefreshTime(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(System.currentTimeMillis()));
	}

	/**
	 * lv点击事件
	 * 
	 * @param parent
	 * @param view
	 * @param position
	 * @param id
	 */
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setView() {
		setContentView(R.layout.activity_one);
	}
}
