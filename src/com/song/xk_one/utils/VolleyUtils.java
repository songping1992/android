package com.song.xk_one.utils;

import java.util.Map;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.widget.ImageView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageLoader.ImageListener;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.song.xk_one.R;

public class VolleyUtils {
	private static VolleyUtils volleyUtils;
	private static RequestQueue mQueue;

	/**
	 * 设置加载图片过程中显示的图片ID
	 */
	public static final int DEFAULTIMAGERESID = R.drawable.ic_launcher;

	/**
	 * 设置加载图片失败后显示的图片ID
	 */
	public static final int ERRORIMAGERESID = R.drawable.ic_launcher;

	private VolleyUtils(Context context) {

	}

	public interface VolleyResponse {
		public void getResponse(String response);
	}

	public static VolleyUtils getInstance(Context context) {
		if (null == volleyUtils) {
			volleyUtils = new VolleyUtils(context);
		}
		if (null == mQueue) {
			mQueue = Volley.newRequestQueue(context);
		}

		return volleyUtils;
	}

	/**
	 * get请求
	 * 
	 * @param url
	 *            请求地址
	 * @param volleyResponse
	 *            将返回的数据回调
	 */
	public void doGetRequest(String url, final VolleyResponse volleyResponse) {
		mQueue.add(new StringRequest(Method.GET, url, new Listener<String>() {

			@Override
			public void onResponse(String response) {
				volleyResponse.getResponse(response);
			}
		}, new ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError arg0) {
				// TODO Auto-generated method stub

			}
		}));
	}

	/**
	 * post请求
	 * 
	 * @param url
	 *            请求地址
	 * @param volleyResponse
	 *            将返回的数据回调
	 * @param params
	 *            post请求参数
	 */
	public void doPostRequest(String url, final VolleyResponse volleyResponse,
			final Map<String, String> params) {
		mQueue.add(new StringRequest(Method.POST, url, new Listener<String>() {

			@Override
			public void onResponse(String response) {
				volleyResponse.getResponse(response);
			}
		}, new ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError arg0) {
				// TODO Auto-generated method stub

			}
		}) {

			@Override
			protected Map<String, String> getParams() throws AuthFailureError {
				return params;
			}
		});
	}

	/**
	 * 下载图片 通过ImageRequest
	 * 
	 * @param url
	 *            请求地址
	 * @param maxHeight
	 *            图片最大高度
	 * @param maxWidth
	 *            图片最大宽度
	 */
	public void setImageByRequest(String url, final ImageView imageView,
			int maxWidth, int maxHeight) {
		mQueue.add(new ImageRequest(url, new Listener<Bitmap>() {

			@Override
			public void onResponse(Bitmap response) {
				imageView.setImageBitmap(response);
			}
		}, maxWidth, maxHeight, Config.ARGB_8888, new ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError arg0) {
				// 设置加载失败图片
				imageView.setImageResource(R.drawable.ic_launcher);
			}
		}));
	}

	/**
	 * 下载图片 通过ImageLoader
	 * 
	 * @param url
	 *            请求地址
	 */
	public void setImageByLoader(String url, ImageView imageView) {
		ImageLoader loader = new ImageLoader(mQueue,
				ImageLruCache.getInstance());

		ImageListener listener = ImageLoader.getImageListener(imageView,
				DEFAULTIMAGERESID, ERRORIMAGERESID);

		loader.get(url, listener);
	}

	/**
	 * 下载图片 通过ImageLoader
	 * 
	 * @param url
	 *            请求地址
	 * @param maxHeight
	 *            图片最大高度
	 * @param maxWidth
	 *            图片最大宽度
	 */
	public void setImageByLoader(String url, ImageView imageView, int maxWidth,
			int maxHeight) {
		ImageLoader loader = new ImageLoader(mQueue,
				ImageLruCache.getInstance());

		ImageListener listener = ImageLoader.getImageListener(imageView,
				DEFAULTIMAGERESID, ERRORIMAGERESID);

		loader.get(url, listener, maxWidth, maxHeight);
	}

	/**
	 * 下载图片 通过NetworkImageView
	 * 
	 * @param url
	 *            请求地址
	 */
	public void setNetworkImage(String url, NetworkImageView imageView) {
		ImageLoader loader = new ImageLoader(mQueue,
				ImageLruCache.getInstance());

		imageView.setImageUrl(url, loader);

		imageView.setDefaultImageResId(DEFAULTIMAGERESID);
		imageView.setErrorImageResId(ERRORIMAGERESID);
	}
}
