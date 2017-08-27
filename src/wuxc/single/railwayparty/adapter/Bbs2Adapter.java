package wuxc.single.railwayparty.adapter;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
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
import wuxc.single.railwayparty.cache.Bbs2Cache;
import wuxc.single.railwayparty.internet.URLcontainer;
import wuxc.single.railwayparty.model.Bbs2Model;
import wuxc.single.railwayparty.start.ImageLoader600;

public class Bbs2Adapter extends ArrayAdapter<Bbs2Model> implements OnClickListener {
	private ListView listView;

	private String imageurl = "";
	private int screenwidth = 0;
	private Activity thisactivity;
	private Callback mCallback;
	public ImageLoader600 imageLoader;
	private static LayoutInflater inflater = null;

	public Bbs2Adapter(Activity activity, List<Bbs2Model> imageAndTexts, ListView listView, Callback callback) {
		super(activity, 0, imageAndTexts);
		this.listView = listView;
		this.thisactivity = activity;

		mCallback = callback;
		inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		imageLoader = new ImageLoader600(activity.getApplicationContext());

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
		Bbs2Model imageAndText = getItem(position);

		// Inflate the views from XML
		View rowView = convertView;

		Bbs2Cache viewCache;
		if (rowView == null) {
			LayoutInflater inflater = activity.getLayoutInflater();
			rowView = inflater.inflate(R.layout.wuxc_item_bbs_2, null);
			viewCache = new Bbs2Cache(rowView);
			rowView.setTag(viewCache);
		} else {
			LayoutInflater inflater = activity.getLayoutInflater();
			rowView = inflater.inflate(R.layout.wuxc_item_bbs_2, null);
			viewCache = new Bbs2Cache(rowView);
			rowView.setTag(viewCache);
		}

		// Load the image and set it on the ImageView
		// String imageUrl = imageAndText.getHeadimgUrl();
		ImageView imageView = viewCache.getheadimg();
		if (!(imageAndText.getHeadimgUrl().equals("") || imageAndText.getHeadimgUrl() == null)) {

			viewCache.getheadimg().setTag(URLcontainer.urlip + "upload" + imageAndText.getHeadimgUrl());

			try {

				imageLoader.DisplayImage(URLcontainer.urlip + "upload" + imageAndText.getHeadimgUrl(), activity,
						viewCache.getheadimg(), 0);
			} catch (Exception e) {
				// TODO: handle exception
			} catch (OutOfMemoryError e) {
				// TODO: handle exception
			}
		}
		TextView texttime = viewCache.gettextTime();
		texttime.setText(imageAndText.getTime());

		TextView textcontent = viewCache.gettextContent();
		textcontent.setText(imageAndText.getSummary());

		TextView texttitle = viewCache.gettextTitle();
		texttitle.setText(imageAndText.getTitle());
		if (imageAndText.isRead()) {
			texttitle.setTextColor(Color.parseColor("#7d7d7d"));
		} else {
			texttitle.setTextColor(Color.parseColor("#000000"));
		}
		LinearLayout lin_all = viewCache.getlin_all();
		lin_all.setTag(position);
		lin_all.setOnClickListener(this);
		TextView textlabel = viewCache.gettextLabel();
		textlabel.setText(imageAndText.getLabel());
		LinearLayout.LayoutParams LayoutParams2 = (android.widget.LinearLayout.LayoutParams) imageView
				.getLayoutParams();
		LayoutParams2.height = imageAndText.getWidth() * 371 / 554;
		imageView.setLayoutParams(LayoutParams2);
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

	// @Override
	// public void onClick(View v) {
	// // TODO Auto-generated method stub
	//
	// }
}
