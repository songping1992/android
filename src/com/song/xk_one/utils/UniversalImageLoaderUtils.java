package com.song.xk_one.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.ImageLoadingProgressListener;
import com.nostra13.universalimageloader.core.process.BitmapProcessor;
import com.song.xk_one.R;

public class UniversalImageLoaderUtils {

	private static ImageLoader imageLoader = ImageLoader.getInstance();
	// 下载中显示图片
	private static final int IMAGEONLOADING = R.drawable.ic_launcher;
	// URI为空是显示图片
	private static final int IMAGEFOREMPTYURI = R.drawable.ic_launcher;
	// 图片下载失败显示图片
	private static final int IMAGEONFAIL = R.drawable.ic_launcher;

	/**
	 * 初始化UniversalImageLoader 在application 中初始化一次就行
	 */
	@SuppressWarnings("deprecation")
	public static void initUniversalImageLoader(Context context) {
		ImageLoaderConfiguration config = null;

		config = new ImageLoaderConfiguration.Builder(context).threadPriority(Thread.NORM_PRIORITY - 2).denyCacheImageMultipleSizesInMemory().discCacheFileNameGenerator(new Md5FileNameGenerator())
				.tasksProcessingOrder(QueueProcessingType.LIFO).build();
		ImageLoader.getInstance().init(config);
	}

	private static DisplayImageOptions.Builder setDisplayImageOptions(final int roundPixels) {
		DisplayImageOptions.Builder builder = new DisplayImageOptions.Builder();
		// 设置图片在下载期间显示的图片
		builder.showImageOnLoading(IMAGEONLOADING);
		// 设置图片Uri为空或是错误的时候显示的图片
		builder.showImageForEmptyUri(IMAGEFOREMPTYURI);
		// 设置图片加载/解码过程中错误时候显示的图片
		builder.showImageOnFail(IMAGEONFAIL);
		// 设置图片的解码类型
		builder.bitmapConfig(Bitmap.Config.RGB_565);
		// 设置图片以如何的编码方式显示
		builder.imageScaleType(ImageScaleType.IN_SAMPLE_INT);
		// 设置下载的图片是否缓存在内存中
		builder.cacheInMemory(true);
		// 设置下载的图片是否缓存在SD卡中
		builder.cacheOnDisk(true);
		// 设置圆角图片
		if (roundPixels != 0)
			builder.preProcessor(new BitmapProcessor() {

				@Override
				public Bitmap process(Bitmap bitmap) {

					Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Config.ARGB_8888);
					Canvas canvas = new Canvas(output);
					final int color = 0xff424242;
					final Paint paint = new Paint();
					final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
					final RectF rectF = new RectF(rect);
					final float roundPx = roundPixels;
					paint.setAntiAlias(true);
					canvas.drawARGB(0, 0, 0, 0);
					paint.setColor(color);
					canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
					paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
					canvas.drawBitmap(bitmap, rect, rect, paint);
					return output;
				}
			});
		return builder;
	}

	/**
	 * 加载bitmap
	 */
	public static void loadImage(String url, ImageView imageView, Drawable drawable) {
		imageLoader.loadImage(url, new ImageLoadingListener() {

			@Override
			public void onLoadingStarted(String arg0, View arg1) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onLoadingFailed(String arg0, View arg1, FailReason arg2) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onLoadingComplete(String arg0, View arg1, Bitmap arg2) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onLoadingCancelled(String arg0, View arg1) {
				// TODO Auto-generated method stub

			}
		});
	}

	/**
	 * 加载图片
	 */
	public static void displayImage(String url, ImageView imageView) {
		if (url == null || imageView == null) {
			return;
		}
		imageLoader.displayImage(url.trim(), imageView, setDisplayImageOptions(0).build());
	}

	/**
	 * 加载圆角图片
	 */
	public static void displayImage(String url, ImageView imageView, int roundPixels) {
		if (url == null || imageView == null) {
			return;
		}
		imageLoader.displayImage(url.trim(), imageView, setDisplayImageOptions(roundPixels).build());
	}

	/**
	 * 加载图片带进度条(view)
	 */
	public static void displayImage(String url, ImageView imageView, final View loadingView) {
		if (url == null || imageView == null || loadingView == null) {
			return;
		}
		imageLoader.displayImage(url.trim(), imageView, setDisplayImageOptions(0).build(), new ImageLoadingListener() {

			@Override
			public void onLoadingStarted(String imageUri, View view) {
				loadingView.setVisibility(View.VISIBLE);
			}

			@Override
			public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
				loadingView.setVisibility(View.GONE);
			}

			@Override
			public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
				loadingView.setVisibility(View.GONE);
			}

			@Override
			public void onLoadingCancelled(String imageUri, View view) {
				loadingView.setVisibility(View.GONE);
			}
		});
	}

	/**
	 * 加载圆角图片带进度条(view)
	 */
	public static void displayImage(String url, ImageView imageView, int roundPixels, final View loadingView) {
		if (url == null || imageView == null || loadingView == null) {
			return;
		}
		imageLoader.displayImage(url.trim(), imageView, setDisplayImageOptions(roundPixels).build(), new ImageLoadingListener() {

			@Override
			public void onLoadingStarted(String imageUri, View view) {
				loadingView.setVisibility(View.VISIBLE);
			}

			@Override
			public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
				loadingView.setVisibility(View.GONE);
			}

			@Override
			public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
				loadingView.setVisibility(View.GONE);
			}

			@Override
			public void onLoadingCancelled(String imageUri, View view) {
				loadingView.setVisibility(View.GONE);
			}
		});
	}

	/**
	 * 加载图片带进度条(ProgressDialog)
	 */
	public static void displayImage(String url, ImageView imageView, final ProgressDialog dialog) {
		if (url == null || imageView == null || dialog == null) {
			return;
		}
		imageLoader.displayImage(url.trim(), imageView, setDisplayImageOptions(0).build(), new ImageLoadingListener() {

			@Override
			public void onLoadingStarted(String imageUri, View view) {
				dialog.show();
			}

			@Override
			public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
				dialog.dismiss();
			}

			@Override
			public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
				dialog.dismiss();
			}

			@Override
			public void onLoadingCancelled(String imageUri, View view) {
				dialog.dismiss();
			}
		}, new ImageLoadingProgressListener() {

			@Override
			public void onProgressUpdate(String imageUri, View view, int current, int total) {
				dialog.setProgress(Math.round(100.0f * current / total));
			}
		});
	}

	/**
	 * 加载圆角图片带进度条(ProgressDialog)
	 */
	public static void displayImage(String url, ImageView imageView, int roundPixels, final ProgressDialog dialog) {
		if (url == null || imageView == null || dialog == null) {
			return;
		}
		imageLoader.displayImage(url.trim(), imageView, setDisplayImageOptions(roundPixels).build(), new ImageLoadingListener() {

			@Override
			public void onLoadingStarted(String imageUri, View view) {
				dialog.show();
			}

			@Override
			public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
				dialog.dismiss();
			}

			@Override
			public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
				dialog.dismiss();
			}

			@Override
			public void onLoadingCancelled(String imageUri, View view) {
				dialog.dismiss();
			}
		}, new ImageLoadingProgressListener() {

			@Override
			public void onProgressUpdate(String imageUri, View view, int current, int total) {
				// 更新进度条进度
				dialog.setProgress(Math.round(100.0f * current / total));
			}
		});
	}

	/**
	 * 清除内存缓存
	 */
	public static void clearMemoryCache() {
		imageLoader.clearMemoryCache();
	}

	/**
	 * 清除硬盘缓存
	 */
	public static void clearDiscCache() {
		imageLoader.clearDiskCache();
	}

	/**
	 * 停止加载
	 */
	public static void stopImageLoading() {
		imageLoader.stop();
	}
}
