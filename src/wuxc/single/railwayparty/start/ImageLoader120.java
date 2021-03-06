package wuxc.single.railwayparty.start;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Collections;
import java.util.Map;
import java.util.Stack;
import java.util.WeakHashMap;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.util.Log;
import android.widget.ImageView;
import wuxc.single.railwayparty.R;

public class ImageLoader120 {

	MemoryCache memoryCache = new MemoryCache();
	FileCache fileCache;
	private Map<ImageView, String> imageViews = Collections.synchronizedMap(new WeakHashMap<ImageView, String>());

	public ImageLoader120(Context context) {
		// Make the background thead low priority. This way it will not affect
		// the UI performance
		photoLoaderThread.setPriority(Thread.NORM_PRIORITY - 1);

		fileCache = new FileCache(context);
	}

	int stub_id = R.drawable.logo;

	public void DisplayImage(String url, Activity activity, ImageView imageView, int inturl) {

		imageViews.put(imageView, url);
		Bitmap bitmap = memoryCache.get(url);
		if (bitmap != null) {
			Log.e("�ڴ�����", "�ڴ�����");
			imageView.setImageBitmap(bitmap);
		} else {
			
			String userName = getBitName(url);
			String temppath = Environment.getExternalStorageDirectory() + "/chat/" + userName + ".png";
			Bitmap bm1 = null;
			bm1 = getBitmapByPath(temppath);
			if (bm1 == null) {
				Log.e("����ͼƬ+imageloader", "����ͼƬ+imageloader" + userName);
				queuePhoto(url, activity, imageView);
				imageView.setImageResource(stub_id);
			} else {
				try {
					int width = bm1.getWidth();
					int height = bm1.getHeight();
					Log.e("width", "" + width);
					Log.e("width", "" + width);
					int clr1 = bm1.getPixel(0, 0);
					int clr2 = bm1.getPixel(width - 1, 0);
					int clr3 = bm1.getPixel(0, height - 1);
					int clr4 = bm1.getPixel(width - 1, height - 1);
					int black = -16777216;

					if (clr1 == black || clr2 == black || clr3 == black || clr4 == black) {

						Log.e("�ж����ݼ���ͼƬ+imageloader", "����ͼƬ+imageloader" + userName);
						queuePhoto(url, activity, imageView);
						imageView.setImageResource(stub_id);
					} else {
						if (isWifiActive(activity)) {
							Log.e("�����¼���ͼƬ+imageloader", "����ͼƬ+imageloader" + userName);
							queuePhoto(url, activity, imageView);
							imageView.setImageBitmap(bm1);
						} else {
							Log.e("����SDͼƬ+imageloader", "����ͼƬ+imageloader" + userName);
							memoryCache.put(url, bm1);
							imageView.setImageBitmap(bm1);
						}
					}

				} catch (Exception e) {
					// TODO: handle exception

					Log.e("�쳣�����ͼƬ+imageloader", "����ͼƬ+imageloader" + userName);
					queuePhoto(url, activity, imageView);
					imageView.setImageResource(stub_id);
				}

			}

		}
	}
	// �жϵ�ǰ�Ƿ�ʹ�õ��� WIFI����
		public static boolean isWifiActive(Context icontext) {
			Context context = icontext.getApplicationContext();
			ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo[] info;
			if (connectivity != null) {
				info = connectivity.getAllNetworkInfo();
				if (info != null) {
					for (int i = 0; i < info.length; i++) {
						if (info[i].getTypeName().equals("WIFI") && info[i].isConnected()) {
							return true;
						}
					}
				}
			}
			return false;
		}

	public Bitmap getBitmapByPath(String fileName) {
		// String myJpgPath =
		// Environment.getExternalStorageDirectory()+"pepper/" + fileName;
		BitmapFactory.Options options = new BitmapFactory.Options();
		// options.inSampleSize = 12;
		Bitmap bm = BitmapFactory.decodeFile(fileName, options);
		return bm;
	}

	private String getBitName(String imageUrl) {
		// TODO Auto-generated method stub
		String[] temp = imageUrl.split("");
		String result = "";
		for (int i = 0; i < temp.length; i++) {
			if (temp[i].equals("/") || temp[i].equals(".")) {
				temp[i] = "";
			}
			result = result + temp[i];
		}
		return result + "120";
	}

	private void queuePhoto(String url, Activity activity, ImageView imageView) {
		// This ImageView may be used for other images before. So there may be
		// some old tasks in the queue. We need to discard them.
		photosQueue.Clean(imageView);
		PhotoToLoad p = new PhotoToLoad(url, imageView);
		synchronized (photosQueue.photosToLoad) {
			photosQueue.photosToLoad.push(p);
			photosQueue.photosToLoad.notifyAll();
		}

		// start thread if it's not started yet
		if (photoLoaderThread.getState() == Thread.State.NEW)
			photoLoaderThread.start();
	}

	private Bitmap getBitmap(String url) {
		File f = fileCache.getFile(url);

		// from SD cache
		Bitmap b = decodeFile(f);
		if (b != null) {
			try {

				Log.e("bͼƬ���ݲ�Ϊ��", "bͼƬ���ݲ�Ϊ��");
				String userName = getBitName(url);
				saveMyBitmap(userName, b);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NullPointerException e) {
				// TODO: handle exception
				Log.e("NullPointerException", "NullPointerException saveMyBitmap");
			}
			return b;
		}
		// from web
		try {
			Bitmap bitmap = null;
			URL imageUrl = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) imageUrl.openConnection();
			conn.setConnectTimeout(30000);
			conn.setReadTimeout(30000);
			InputStream is = conn.getInputStream();
			OutputStream os = new FileOutputStream(f);
			Utils.CopyStream(is, os);
			os.close();
			bitmap = decodeFile(f);
			try {
				if (bitmap == null) {
					Log.e("ͼƬ����Ϊ��", "ͼƬ����Ϊ��");

				} else {
					Log.e("ͼƬ���ݲ�Ϊ��", "ͼƬ���ݲ�Ϊ��");
				}
				String userName = getBitName(url);
				saveMyBitmap(userName, bitmap);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NullPointerException e) {
				// TODO: handle exception
				Log.e("NullPointerException", "NullPointerException saveMyBitmap");
			}
			return bitmap;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public void saveMyBitmap(String bitName, Bitmap mBitmap) throws IOException {
		String path = Environment.getExternalStorageDirectory() + "/chat/";
		String myJpgPath = Environment.getExternalStorageDirectory() + "/chat/" + bitName + ".png";
		File tmp = new File(path);
		if (!tmp.exists()) {
			tmp.mkdir();
		}
		File f = new File(myJpgPath);
		f.createNewFile();
		FileOutputStream fOut = null;
		try {
			fOut = new FileOutputStream(f);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		mBitmap.compress(Bitmap.CompressFormat.PNG, 100, fOut);
		try {
			fOut.flush();
			fOut.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// decodes image and scales it to reduce memory consumption
	private Bitmap decodeFile(File f) {
		try {
			// decode image size
			BitmapFactory.Options o = new BitmapFactory.Options();
			o.inJustDecodeBounds = true;
			BitmapFactory.decodeStream(new FileInputStream(f), null, o);

			// Find the correct scale value. It should be the power of 2.
			final int REQUIRED_SIZE = 120;
			int width_tmp = o.outWidth, height_tmp = o.outHeight;
			int scale = 1;
			while (true) {
				if (width_tmp / 2 < REQUIRED_SIZE || height_tmp / 2 < REQUIRED_SIZE)
					break;
				width_tmp /= 2;
				height_tmp /= 2;
				scale *= 2;
			}

			// decode with inSampleSize
			BitmapFactory.Options o2 = new BitmapFactory.Options();
			o2.inSampleSize = scale;
			return BitmapFactory.decodeStream(new FileInputStream(f), null, o2);
		} catch (FileNotFoundException e) {
		} catch (OutOfMemoryError e) {
			// TODO: handle exception
			Log.e("OutOfMemoryError", "OutOfMemoryError of Catch");
		}
		return null;
	}

	// Task for the queue
	private class PhotoToLoad {
		public String url;
		public ImageView imageView;

		public PhotoToLoad(String u, ImageView i) {
			url = u;
			imageView = i;
		}
	}

	PhotosQueue photosQueue = new PhotosQueue();

	public void stopThread() {
		photoLoaderThread.interrupt();
	}

	// stores list of photos to download
	class PhotosQueue {
		private Stack<PhotoToLoad> photosToLoad = new Stack<PhotoToLoad>();

		// removes all instances of this ImageView
		public void Clean(ImageView image) {
			for (int j = 0; j < photosToLoad.size();) {
				if (photosToLoad.get(j).imageView == image)
					photosToLoad.remove(j);
				else
					++j;
			}
		}
	}

	class PhotosLoader extends Thread {
		public void run() {
			try {
				while (true) {
					// thread waits until there are any images to load in the
					// queue
					if (photosQueue.photosToLoad.size() == 0)
						synchronized (photosQueue.photosToLoad) {
							photosQueue.photosToLoad.wait();
						}
					if (photosQueue.photosToLoad.size() != 0) {
						PhotoToLoad photoToLoad;
						synchronized (photosQueue.photosToLoad) {
							photoToLoad = photosQueue.photosToLoad.pop();
						}
						Bitmap bmp = getBitmap(photoToLoad.url);
						memoryCache.put(photoToLoad.url, bmp);
						String tag = imageViews.get(photoToLoad.imageView);
						if (tag != null && tag.equals(photoToLoad.url)) {
							BitmapDisplayer bd = new BitmapDisplayer(bmp, photoToLoad.imageView);
							Activity a = (Activity) photoToLoad.imageView.getContext();
							a.runOnUiThread(bd);
						}
					}
					if (Thread.interrupted())
						break;
				}
			} catch (InterruptedException e) {
				// allow thread to exit
			}
		}
	}

	PhotosLoader photoLoaderThread = new PhotosLoader();

	// Used to display bitmap in the UI thread
	class BitmapDisplayer implements Runnable {
		Bitmap bitmap;
		ImageView imageView;

		public BitmapDisplayer(Bitmap b, ImageView i) {
			bitmap = b;
			imageView = i;
		}

		public void run() {
			if (bitmap != null)
				imageView.setImageBitmap(bitmap);
			else
				imageView.setImageResource(stub_id);
		}
	}

	public void clearCache() {
		memoryCache.clear();
		fileCache.clear();
	}

}
