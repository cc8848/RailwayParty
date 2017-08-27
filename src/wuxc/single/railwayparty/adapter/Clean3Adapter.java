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
import wuxc.single.railwayparty.cache.Clean3Cache;
import wuxc.single.railwayparty.internet.URLcontainer;
import wuxc.single.railwayparty.model.Clean3Model;
import wuxc.single.railwayparty.start.ImageLoader120;

public class Clean3Adapter extends ArrayAdapter<Clean3Model> implements OnClickListener {
	private ListView listView;
	public ImageLoader120 imageLoader;
	private static LayoutInflater inflater = null;
	private String imageurl = "";
	private int screenwidth = 0;
	private Activity thisactivity;
	private Callback mCallback;
	public Clean3Adapter(Activity activity, List<Clean3Model> imageAndTexts, ListView listView, Callback callback) {
		super(activity, 0, imageAndTexts);
		this.listView = listView;
		this.thisactivity = activity;
		inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		imageLoader = new ImageLoader120(activity.getApplicationContext());

		mCallback = callback;
	}

	public interface Callback {
		public void click(View v);
	}
@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		mCallback.click(v);
	}


	public View getView(int position, View convertView, ViewGroup parent) {
		Activity activity = (Activity) getContext();
		Clean3Model imageAndText = getItem(position);

		// Inflate the views from XML
		View rowView = convertView;

		Clean3Cache viewCache;
		if (true) {
			LayoutInflater inflater = activity.getLayoutInflater();
			rowView = inflater.inflate(R.layout.wuxc_item_clean_3, null);
			viewCache = new Clean3Cache(rowView);
			rowView.setTag(viewCache);
		} 

		// Load the image and set it on the ImageView
	 
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

		TextView textguanzhu = viewCache.gettextGuanzhu();
		textguanzhu.setText(imageAndText.getGuanzhu());

		TextView textzan = viewCache.gettextZan();
		textzan.setText(imageAndText.getZan());

		TextView textname = viewCache.gettextName();
		textname.setText(imageAndText.getName());
		LinearLayout lin_all = viewCache.getlin_all();
		lin_all.setTag(position);
		lin_all.setOnClickListener(this);
		if (imageAndText.isRead()) {
			textname.setTextColor(Color.parseColor("#7d7d7d"));
		} else {
			textname.setTextColor(Color.parseColor("#000000"));
		}
		TextView textlabel = viewCache.gettextPltextLabel();
		textlabel.setText("【" + imageAndText.getLabel() + "】");
		return rowView;
	}

//	public Bitmap getBitmapByPath(String fileName) {
//		// String myJpgPath =
//		// Environment.getExternalStorageDirectory()+"pepper/" + fileName;
//		BitmapFactory.Options options = new BitmapFactory.Options();
//		// options.inSampleSize = 12;
//		Bitmap bm = BitmapFactory.decodeFile(fileName, options);
//		return bm;
//	}
//
//	private String getBitName(String imageUrl) {
//		// TODO Auto-generated method stub
//		String[] temp = imageUrl.split("");
//		String result = "";
//		for (int i = 0; i < temp.length; i++) {
//			if (temp[i].equals("/") || temp[i].equals(".")) {
//				temp[i] = "";
//			}
//			result = result + temp[i];
//		}
//		return result;
//	}
//
//	public void saveMyBitmap(String bitName, Bitmap mBitmap) throws IOException {
//		String path = Environment.getExternalStorageDirectory() + "/chat/";
//		String myJpgPath = Environment.getExternalStorageDirectory() + "/chat/" + bitName + ".png";
//		File tmp = new File(path);
//		if (!tmp.exists()) {
//			tmp.mkdir();
//		}
//		File f = new File(myJpgPath);
//		f.createNewFile();
//		FileOutputStream fOut = null;
//		try {
//			fOut = new FileOutputStream(f);
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}
//		mBitmap.compress(Bitmap.CompressFormat.PNG, 100, fOut);
//		try {
//			fOut.flush();
//			fOut.close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//
//	public Bitmap cutBmp(Bitmap bmp) {
//		Bitmap result;
//		int w = bmp.getWidth();// 输入长方形宽
//		int h = bmp.getHeight();// 输入长方形高
//		int nw;// 输出正方形宽
//		result = Bitmap.createBitmap(bmp, 15 * w / 100, 15 * h / 100, 7 * w / 10, 7 * h / 10);
//		// }
//		return result;
//	}
//
//	@Override
//	public void onClick(View v) {
//		// TODO Auto-generated method stub
//
//	}
}
