package com.ttp.example.pycotestapp.utils;

import java.io.File;
import java.util.HashMap;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;
import android.widget.ImageView;

public class ImageLoader {
	private HashMap<String, Bitmap> mImagesCached = new HashMap<String, Bitmap>();

	private File mCacheDir;

	private static ImageLoader instance = null;

	public static ImageLoader getInstance(Context context) {
		if (instance == null) {
			instance = new ImageLoader(context);
		}

		return instance;
	}

	private ImageLoader(Context context) {
		// Find the dir to save cached images
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			mCacheDir = new File(Environment.getExternalStorageDirectory(),
					"image_cache");
		} else {
			mCacheDir = context.getCacheDir();
		}

		if (!mCacheDir.exists()) {
			mCacheDir.mkdirs();
		}
	}

	public void clearCache() {
		// clear memory cache
		mImagesCached.clear();

		// clear SD cache
		File[] files = mCacheDir.listFiles();
		for (File f : files) {
			f.delete();
		}
	}

	public void displayImage(String url, ImageView imageView, int width,
			int height) {
		Log.d("imageUrl", url);
		if (mImagesCached.get(url) != null) {
			imageView.setImageBitmap(mImagesCached.get(url));
		} else {
			imageView.setTag(url);
			new AsyncImageLoader(imageView, url, width, height).execute();
		}
	}

	public void displayImage(String url, ImageView imageView, int width,
			int height, OnImageLoadedListener lister) {
		Log.d("imageUrl", url);
		if (mImagesCached.get(url) != null) {
			imageView.setImageBitmap(mImagesCached.get(url));
			if (lister != null) {
				lister.onLoaded(mImagesCached.get(url));
			}
		} else {
			imageView.setTag(url);
			new AsyncImageLoader(imageView, url, width, height, lister)
					.execute();
		}
	}

	public void displayImage(String url, ImageView imageView) {
		Log.d("imageUrl", url);
		if (mImagesCached.get(url) != null) {
			imageView.setImageBitmap(mImagesCached.get(url));
		} else {
			imageView.setTag(url);
			new AsyncImageLoader(imageView, url).execute();
		}
	}

	public void displayImage(String url, ImageView imageView,
			OnImageLoadedListener lister) {
		Log.d("imageUrl", url);
		if (mImagesCached.get(url) != null) {
			imageView.setImageBitmap(mImagesCached.get(url));
			if (lister != null) {
				lister.onLoaded(mImagesCached.get(url));
			}
		} else {
			imageView.setTag(url);
			new AsyncImageLoader(imageView, url, lister).execute();
		}
	}

	private class AsyncImageLoader extends AsyncTask<Void, Void, Bitmap> {
		private ImageView imageView;
		private String imageUrl;
		private String tag;
		private OnImageLoadedListener listener;

		private int width = -1, height = -1;

		public AsyncImageLoader(ImageView iv, String url) {
			this.imageView = iv;
			this.imageUrl = url;
			this.tag = iv.getTag().toString();
		}

		public AsyncImageLoader(ImageView iv, String url, int w, int h) {
			this.imageView = iv;
			this.imageUrl = url;
			this.tag = iv.getTag().toString();
			this.width = w;
			this.height = h;
		}

		public AsyncImageLoader(ImageView iv, String url,
				OnImageLoadedListener l) {
			this.imageView = iv;
			this.imageUrl = url;
			this.tag = iv.getTag().toString();
			this.listener = l;
		}

		public AsyncImageLoader(ImageView iv, String url, int w, int h,
				OnImageLoadedListener l) {
			this.imageView = iv;
			this.imageUrl = url;
			this.tag = iv.getTag().toString();
			this.width = w;
			this.height = h;
			this.listener = l;
		}

		@Override
		protected Bitmap doInBackground(Void... params) {
			Bitmap b = null;
			if (width < 0 || height < 0) {
				b = ImageUtils.getBitmapImageFromUrl(imageUrl);
			} else {
				b = ImageUtils.getBitmapImageFromUrl(imageUrl, width, height);
			}
			if (b != null && mImagesCached.get(imageUrl) == null) {
				mImagesCached.put(imageUrl, b);
			}
			return b;
		}

		@Override
		protected void onPostExecute(Bitmap result) {
			super.onPostExecute(result);
			if (imageView.getTag().toString().equals(tag) && result != null) {
				imageView.setImageBitmap(result);
				if (listener != null) {
					listener.onLoaded(result);
				}
			} else {
				if (listener != null) {
					listener.onLoaded(null);
				}
			}
		}
	}

	public interface OnImageLoadedListener {
		public void onLoaded(Bitmap b);
	}
}
