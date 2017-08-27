package wuxc.single.railwayparty.adapter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import wuxc.single.railwayparty.R;
import wuxc.single.railwayparty.cache.Flag3Cache;
import wuxc.single.railwayparty.internet.ImageLoader;
import wuxc.single.railwayparty.internet.ImageLoader.ImageCallback;
import wuxc.single.railwayparty.internet.URLcontainer;
import wuxc.single.railwayparty.model.Flag3Model;
import wuxc.single.railwayparty.start.ImageLoader120;;

public class Flag3Adapter extends ArrayAdapter<Flag3Model> implements OnClickListener {
	private ListView listView;
	public ImageLoader120 imageLoader;
	private static LayoutInflater inflater = null;
	private String imageurl = "";
	private int screenwidth = 0;
	private Activity thisactivity;
	private Callback mCallback;

	public Flag3Adapter(Activity activity, List<Flag3Model> imageAndTexts, ListView listView, Callback callback) {
		super(activity, 0, imageAndTexts);
		this.listView = listView;
		this.thisactivity = activity;
		inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		imageLoader = new ImageLoader120(activity.getApplicationContext());

		mCallback = callback;

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		mCallback.click(v);
	}

	public interface Callback {
		public void click(View v);
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		Activity activity = (Activity) getContext();

		// Inflate the views from XML
		View rowView = convertView;
		Flag3Cache viewCache;
		if (true) {
			LayoutInflater inflater = activity.getLayoutInflater();

			rowView = inflater.inflate(R.layout.wuxc_item_flag_1, null);

			viewCache = new Flag3Cache(rowView);
			rowView.setTag(viewCache);

		}
		Flag3Model imageAndText = getItem(position);

		// Load the image and set it on the ImageView

		if (!(imageAndText.getImageUrl().equals("") || imageAndText.getImageUrl() == null)) {

			viewCache.getImageHeadimg().setTag(URLcontainer.urlip + "upload" + imageAndText.getImageUrl());

			try {

				imageLoader.DisplayImage(URLcontainer.urlip + "upload" + imageAndText.getImageUrl(), activity,
						viewCache.getImageHeadimg(), 0);
			} catch (Exception e) {
				// TODO: handle exception
			} catch (OutOfMemoryError e) {
				// TODO: handle exception
			}
		}
		TextView TextTitle = viewCache.getTextTitle();
		TextTitle.setText("" + imageAndText.getTitle());
		if (imageAndText.isRead()) {
			TextTitle.setTextColor(Color.parseColor("#7d7d7d"));
		} else {
			TextTitle.setTextColor(Color.parseColor("#000000"));
		}
		TextView texttime = viewCache.getTextTime();
		texttime.setText("" + imageAndText.getTime());
		TextView textdetail = viewCache.getTextDetail();
		textdetail.setText("" + imageAndText.getSummary());
		LinearLayout lin_all = viewCache.getlin_all();
		lin_all.setTag(position);
		lin_all.setOnClickListener(this);
		return rowView;
	}

	// public Bitmap getBitmapByPath(String fileName) {
	// // String myJpgPath =
	// // Environment.getExternalStorageDirectory()+"pepper/" + fileName;
	// BitmapFactory.Options options = new BitmapFactory.Options();
	// // options.inSampleSize = 12;
	// Bitmap bm = BitmapFactory.decodeFile(fileName, options);
	// return bm;
	// }
	//
	// private String getBitName(String imageUrl) {
	// // TODO Auto-generated method stub
	// String[] temp = imageUrl.split("");
	// String result = "";
	// for (int i = 0; i < temp.length; i++) {
	// if (temp[i].equals("/") || temp[i].equals(".")) {
	// temp[i] = "";
	// }
	// result = result + temp[i];
	// }
	// return result;
	// }
	//
	// public void saveMyBitmap(String bitName, Bitmap mBitmap) throws
	// IOException {
	// String path = Environment.getExternalStorageDirectory() + "/chat/";
	// String myJpgPath = Environment.getExternalStorageDirectory() + "/chat/" +
	// bitName + ".png";
	// File tmp = new File(path);
	// if (!tmp.exists()) {
	// tmp.mkdir();
	// }
	// File f = new File(myJpgPath);
	// f.createNewFile();
	// FileOutputStream fOut = null;
	// try {
	// fOut = new FileOutputStream(f);
	// } catch (FileNotFoundException e) {
	// e.printStackTrace();
	// }
	// mBitmap.compress(Bitmap.CompressFormat.PNG, 100, fOut);
	// try {
	// fOut.flush();
	// fOut.close();
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// }
	//
	// public Bitmap cutBmp(Bitmap bmp) {
	// Bitmap result;
	// int w = bmp.getWidth();// 输入长方形宽
	// int h = bmp.getHeight();// 输入长方形高
	// int nw;// 输出正方形宽
	// result = Bitmap.createBitmap(bmp, 15 * w / 100, 15 * h / 100, 7 * w / 10,
	// 7 * h / 10);
	// // }
	// return result;
	// }
}
