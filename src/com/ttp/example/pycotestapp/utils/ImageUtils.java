package com.ttp.example.pycotestapp.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;

public final class ImageUtils {

	@SuppressWarnings("deprecation")
	public static Drawable getImageFromUrl(String iconUrl) {
		try {
			HttpGet httpRequest = null;
			httpRequest = new HttpGet(iconUrl);

			// String usernamePassword = UtilConstant.USERNAME + ":" +
			// UtilConstant.PASSWORD;
			// byte []authentication =
			// Base64.encodeBytesToBytes(usernamePassword.getBytes());
			// httpRequest.addHeader("Authorization", "Basic " + new
			// String(authentication));

			HttpClient httpclient = new DefaultHttpClient();
			HttpResponse response = (HttpResponse) httpclient
					.execute(httpRequest);
			HttpEntity entity = response.getEntity();
			BufferedHttpEntity bufHttpEntity = new BufferedHttpEntity(entity);
			InputStream instream = bufHttpEntity.getContent();

			Bitmap icon = BitmapFactory.decodeStream(instream);
			if (icon == null) {
				return null;
			}
			return new BitmapDrawable(icon);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Bitmap rotateBitmap(Bitmap in, int degree) {
		if (degree != 0) {
			Matrix matrix = new Matrix();
			matrix.preRotate(degree);
			return Bitmap.createBitmap(in, 0, 0, in.getWidth(), in.getHeight(),
					matrix, true);
		} else {
			return in;
		}

	}

	public static Bitmap getBitmapImageFromUrl(String iconUrl,
			int expectedWidth, int expectedHeight) {
		try {
			HttpGet httpRequest = null;
			httpRequest = new HttpGet(iconUrl);

			// String usernamePassword = UtilConstant.USERNAME + ":" +
			// UtilConstant.PASSWORD;
			// byte []authentication =
			// Base64.encodeBytesToBytes(usernamePassword.getBytes());
			// httpRequest.addHeader("Authorization", "Basic " + new
			// String(authentication));

			HttpClient httpclient = new DefaultHttpClient();
			HttpResponse response = (HttpResponse) httpclient
					.execute(httpRequest);
			HttpEntity entity = response.getEntity();
			BufferedHttpEntity bufHttpEntity = new BufferedHttpEntity(entity);
			InputStream instream = bufHttpEntity.getContent();

			BitmapFactory.Options options = new BitmapFactory.Options();
			options.inJustDecodeBounds = true;

			BitmapFactory.decodeStream(instream, null, options);

			options.inSampleSize = calculateInSampleSize(options,
					expectedWidth, expectedHeight);
			options.inJustDecodeBounds = false;
			InputStream instream2 = bufHttpEntity.getContent();

			return BitmapFactory.decodeStream(instream2, null, options);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Bitmap getBitmapImageFromUrl(String iconUrl) {
		try {
			HttpGet httpRequest = null;
			httpRequest = new HttpGet(iconUrl);

			// String usernamePassword = UtilConstant.USERNAME + ":" +
			// UtilConstant.PASSWORD;
			// byte []authentication =
			// Base64.encodeBytesToBytes(usernamePassword.getBytes());
			// httpRequest.addHeader("Authorization", "Basic " + new
			// String(authentication));

			HttpClient httpclient = new DefaultHttpClient();
			HttpResponse response = (HttpResponse) httpclient
					.execute(httpRequest);
			HttpEntity entity = response.getEntity();
			BufferedHttpEntity bufHttpEntity = new BufferedHttpEntity(entity);
			InputStream instream = bufHttpEntity.getContent();

			Bitmap icon = BitmapFactory.decodeStream(instream);
			System.gc();
			return icon;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Bitmap getBitmapImageFromUrlWithResize(String iconUrl,
			int width, int height) {
		try {
			HttpGet httpRequest = null;
			httpRequest = new HttpGet(iconUrl);

			// String usernamePassword = UtilConstant.USERNAME + ":" +
			// UtilConstant.PASSWORD;
			// byte []authentication =
			// Base64.encodeBytesToBytes(usernamePassword.getBytes());
			// httpRequest.addHeader("Authorization", "Basic " + new
			// String(authentication));

			HttpClient httpclient = new DefaultHttpClient();
			HttpResponse response = (HttpResponse) httpclient
					.execute(httpRequest);
			HttpEntity entity = response.getEntity();
			BufferedHttpEntity bufHttpEntity = new BufferedHttpEntity(entity);
			InputStream instream = bufHttpEntity.getContent();

			Bitmap icon = BitmapFactory.decodeStream(instream);
			icon = resizeBitmap(icon, width, height);
			System.gc();
			return icon;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("deprecation")
	public static Drawable getImageFromUrlWithResize(String iconUrl, int width,
			int height) {
		try {
			HttpGet httpRequest = null;
			httpRequest = new HttpGet(iconUrl);
			HttpClient httpclient = new DefaultHttpClient();
			HttpResponse response = (HttpResponse) httpclient
					.execute(httpRequest);
			HttpEntity entity = response.getEntity();
			BufferedHttpEntity bufHttpEntity = new BufferedHttpEntity(entity);
			InputStream instream = bufHttpEntity.getContent();

			Bitmap icon = BitmapFactory.decodeStream(instream);
			icon = resizeBitmap(icon, width, height);
			return new BitmapDrawable(icon);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	private static Bitmap resizeBitmap(Bitmap originalBitmap, int newwidth,
			int newheight) {
		int width = originalBitmap.getWidth();
		int height = originalBitmap.getHeight();
		float scaleWidth = ((float) newwidth / width);
		float scaleHeight = ((float) newheight / height);

		Matrix matrix = new Matrix();
		matrix.postScale(scaleWidth, scaleHeight);

		Bitmap resizedBitmap = Bitmap.createScaledBitmap(originalBitmap,
				newwidth, newheight, false);
		return resizedBitmap;
	}

	public static Bitmap decodeFile(String filePath)
			throws FileNotFoundException {
		File file = new File(filePath);
		// Decode image size
		BitmapFactory.Options option1 = new BitmapFactory.Options();
		option1.inJustDecodeBounds = true;
		BitmapFactory.decodeStream(new FileInputStream(file), null, option1);

		// The new size we want to scale to
		final int REQUIRED_SIZE = 100;

		// Find the correct scale value. It should be the power of 2.
		int width_tmp = option1.outWidth;
		int height_tmp = option1.outHeight;
		int scale = 1;
		while (true) {
			if (width_tmp / 2 < REQUIRED_SIZE || height_tmp / 2 < REQUIRED_SIZE)
				break;

			width_tmp /= 2;
			height_tmp /= 2;
			scale *= 2;
		}

		// Decode with inSampleSize
		BitmapFactory.Options option2 = new BitmapFactory.Options();
		option2.inSampleSize = scale;
		return BitmapFactory.decodeStream(new FileInputStream(file), null,
				option2);

	}

	public static int calculateInSampleSize(BitmapFactory.Options options,
			int reqWidth, int reqHeight) {
		// Raw height and width of image
		// final int height = options.outHeight;
		// final int width = options.outWidth;
		// int inSampleSize = 1;
		//
		// if (height > reqHeight || width > reqWidth) {
		// float heightScale = (float) height / (float) reqHeight;
		// float widthScale = (float) width / (float) reqWidth;
		// if (heightScale > widthScale) {
		// inSampleSize = Math.round(heightScale);
		// } else {
		// inSampleSize = Math.round(widthScale);
		// }
		// }
		// return inSampleSize;

		// Raw height and width of image
		final int height = options.outHeight;
		final int width = options.outWidth;
		int inSampleSize = 1;

		if (height > reqHeight || width > reqWidth) {

			final int halfHeight = height / 2;
			final int halfWidth = width / 2;

			// Calculate the largest inSampleSize value that is a power of 2 and
			// keeps both
			// height and width larger than the requested height and width.
			while ((halfHeight / inSampleSize) > reqHeight
					&& (halfWidth / inSampleSize) > reqWidth) {
				inSampleSize *= 2;
			}
		}

		return inSampleSize;
	}

	public static final Bitmap getBitmapFromUri(Context context, Uri uri) {
		InputStream is1 = null;
		Bitmap bitmap = null;
		try {
			is1 = context.getContentResolver().openInputStream(uri);
			BitmapFactory.Options bfOptions = new BitmapFactory.Options();
			bfOptions.inJustDecodeBounds = false;
			bitmap = BitmapFactory.decodeStream(is1, new Rect(0, 0, 0, 0),
					bfOptions);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				is1.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return bitmap;
	}

	public static final Bitmap getBitmapFromUri(Context context, Uri uri,
			int reqWidth, int reqHeight) {
		InputStream is1 = null;
		InputStream is2 = null;
		Bitmap bitmap = null;
		try {
			is1 = context.getContentResolver().openInputStream(uri);
			BitmapFactory.Options bfOptions = new BitmapFactory.Options();
			bfOptions.inJustDecodeBounds = true;
			BitmapFactory.decodeStream(is1, new Rect(0, 0, 0, 0), bfOptions);
			bfOptions.inSampleSize = ImageUtils.calculateInSampleSize(
					bfOptions, reqWidth, reqHeight);
			bfOptions.inJustDecodeBounds = false;
			is2 = context.getContentResolver().openInputStream(uri);
			bitmap = BitmapFactory.decodeStream(is2, new Rect(0, 0, 0, 0),
					bfOptions);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				is1.close();
				is2.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return bitmap;
	}

	public static Bitmap getRoundedCornerBitmap(Bitmap bitmap) {
		Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
				bitmap.getHeight(), Config.ARGB_8888);
		Canvas canvas = new Canvas(output);

		final int color = 0xff424242;
		final Paint paint = new Paint();
		final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
		final RectF rectF = new RectF(rect);
		final float roundPx = 12;

		paint.setAntiAlias(true);
		canvas.drawARGB(0, 0, 0, 0);
		paint.setColor(color);
		canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
		canvas.drawBitmap(bitmap, rect, rect, paint);

		return output;
	}

	public static Bitmap getRoundedRectBitmap(Bitmap bitmap, int pixels) {
		Bitmap result = Bitmap.createBitmap(bitmap.getWidth(),
				bitmap.getHeight(), Bitmap.Config.ARGB_8888);
		Canvas canvas = new Canvas(result);

		final int color = 0xff424242;
		final Paint paint = new Paint();
		final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
		final RectF rectF = new RectF(rect);
		final float roundPx = pixels;

		paint.setAntiAlias(true);
		canvas.drawARGB(0, 0, 0, 0);
		paint.setColor(color);
		canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
		canvas.drawBitmap(bitmap, rect, rect, paint);

		return result;
	}
}
