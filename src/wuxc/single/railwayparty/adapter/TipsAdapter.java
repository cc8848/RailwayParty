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
import wuxc.single.railwayparty.cache.TipsCache;
 
import wuxc.single.railwayparty.layout.RoundImageView;
import wuxc.single.railwayparty.internet.URLcontainer;
import wuxc.single.railwayparty.model.TipsModel;
import wuxc.single.railwayparty.start.ImageLoader;

public class TipsAdapter extends ArrayAdapter<TipsModel> implements OnClickListener {
	private ListView listView;

	private String imageurl = "";
	private int screenwidth = 0;
	private Activity thisactivity;
	private Callback mCallback;
	public ImageLoader imageLoader;
	private static LayoutInflater inflater = null;

	public TipsAdapter(Activity activity, List<TipsModel> imageAndTexts, ListView listView, Callback callback) {
		super(activity, 0, imageAndTexts);
		this.listView = listView;
		this.thisactivity = activity;

		mCallback = callback;
		inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		imageLoader = new ImageLoader(activity.getApplicationContext());
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
		TipsModel imageAndText = getItem(position);

		// Inflate the views from XML
		View rowView = convertView;

		TipsCache viewCache;
		if (true) {
			LayoutInflater inflater = activity.getLayoutInflater();
			rowView = inflater.inflate(R.layout.wuxc_item_tips, null);
			viewCache = new TipsCache(rowView);
			rowView.setTag(viewCache);
		} else {
			LayoutInflater inflater = activity.getLayoutInflater();
			rowView = inflater.inflate(R.layout.wuxc_item_tips, null);
			viewCache = new TipsCache(rowView);
			rowView.setTag(viewCache);
		}

		// Load the image and set it on the ImageView
		viewCache.getheadimg().setTag(URLcontainer.urlip + "upload" + imageAndText.getHeadimgUrl());

		try {

			imageLoader.DisplayImage(URLcontainer.urlip + "upload" + imageAndText.getHeadimgUrl(), activity,
					viewCache.getheadimg(), 0);
		} catch (Exception e) {
			// TODO: handle exception
		} catch (OutOfMemoryError e) {
			// TODO: handle exception
		}
		if (!imageAndText.getImage1().equals("")) {
			viewCache.getimage_1().setTag(URLcontainer.urlip + "upload" + imageAndText.getImage1());

			try {

				imageLoader.DisplayImage(URLcontainer.urlip + "upload" + imageAndText.getImage1(), activity,
						viewCache.getimage_1(), 0);
			} catch (Exception e) {
				// TODO: handle exception
			} catch (OutOfMemoryError e) {
				// TODO: handle exception
			}
			LinearLayout.LayoutParams LayoutParams2 = (android.widget.LinearLayout.LayoutParams) viewCache.getimage_1()
					.getLayoutParams();
			LayoutParams2.height = imageAndText.getWidth() / 3;
			LayoutParams2.width = imageAndText.getWidth() / 3;
			viewCache.getimage_1().setLayoutParams(LayoutParams2);
		} else {
			viewCache.getimage_1().setVisibility(View.GONE);
		}
		if (!imageAndText.getImage2().equals("")) {
			viewCache.getimage_2().setTag(URLcontainer.urlip + "upload" + imageAndText.getImage2());

			try {

				imageLoader.DisplayImage(URLcontainer.urlip + "upload" + imageAndText.getImage2(), activity,
						viewCache.getimage_2(), 0);
			} catch (Exception e) {
				// TODO: handle exception
			} catch (OutOfMemoryError e) {
				// TODO: handle exception
			}
			LinearLayout.LayoutParams LayoutParams2 = (android.widget.LinearLayout.LayoutParams) viewCache.getimage_2()
					.getLayoutParams();
			LayoutParams2.height = imageAndText.getWidth() / 3;
			LayoutParams2.width = imageAndText.getWidth() / 3;
			viewCache.getimage_2().setLayoutParams(LayoutParams2);
		} else {
			viewCache.getimage_2().setVisibility(View.GONE);
		}
		if (!imageAndText.getImage3().equals("")) {
			viewCache.getimage_3().setTag(URLcontainer.urlip + "upload" + imageAndText.getImage3());

			try {

				imageLoader.DisplayImage(URLcontainer.urlip + "upload" + imageAndText.getImage3(), activity,
						viewCache.getimage_3(), 0);
			} catch (Exception e) {
				// TODO: handle exception
			} catch (OutOfMemoryError e) {
				// TODO: handle exception
			}
			LinearLayout.LayoutParams LayoutParams2 = (android.widget.LinearLayout.LayoutParams) viewCache.getimage_3()
					.getLayoutParams();
			LayoutParams2.height = imageAndText.getWidth() / 3;
			LayoutParams2.width = imageAndText.getWidth() / 3;
			viewCache.getimage_3().setLayoutParams(LayoutParams2);
		} else {
			viewCache.getimage_3().setVisibility(View.GONE);
		}
		if (!imageAndText.getImage4().equals("")) {
			viewCache.getimage_4().setTag(URLcontainer.urlip + "upload" + imageAndText.getImage4());

			try {

				imageLoader.DisplayImage(URLcontainer.urlip + "upload" + imageAndText.getImage4(), activity,
						viewCache.getimage_4(), 0);
			} catch (Exception e) {
				// TODO: handle exception
			} catch (OutOfMemoryError e) {
				// TODO: handle exception
			}
			LinearLayout.LayoutParams LayoutParams2 = (android.widget.LinearLayout.LayoutParams) viewCache.getimage_4()
					.getLayoutParams();
			LayoutParams2.height = imageAndText.getWidth() / 3;
			LayoutParams2.width = imageAndText.getWidth() / 3;
			viewCache.getimage_4().setLayoutParams(LayoutParams2);
		} else {
			viewCache.getimage_4().setVisibility(View.GONE);
		}
		if (!imageAndText.getImage5().equals("")) {
			viewCache.getimage_5().setTag(URLcontainer.urlip + "upload" + imageAndText.getImage5());

			try {

				imageLoader.DisplayImage(URLcontainer.urlip + "upload" + imageAndText.getImage5(), activity,
						viewCache.getimage_5(), 0);
			} catch (Exception e) {
				// TODO: handle exception
			} catch (OutOfMemoryError e) {
				// TODO: handle exception
			}
			LinearLayout.LayoutParams LayoutParams2 = (android.widget.LinearLayout.LayoutParams) viewCache.getimage_5()
					.getLayoutParams();
			LayoutParams2.height = imageAndText.getWidth() / 3;
			LayoutParams2.width = imageAndText.getWidth() / 3;
			viewCache.getimage_5().setLayoutParams(LayoutParams2);
		} else {
			viewCache.getimage_5().setVisibility(View.GONE);
		}
		if (!imageAndText.getImage6().equals("")) {
			viewCache.getimage_6().setTag(URLcontainer.urlip + "upload" + imageAndText.getImage6());

			try {

				imageLoader.DisplayImage(URLcontainer.urlip + "upload" + imageAndText.getImage6(), activity,
						viewCache.getimage_6(), 0);
			} catch (Exception e) {
				// TODO: handle exception
			} catch (OutOfMemoryError e) {
				// TODO: handle exception
			}
			LinearLayout.LayoutParams LayoutParams2 = (android.widget.LinearLayout.LayoutParams) viewCache.getimage_6()
					.getLayoutParams();
			LayoutParams2.height = imageAndText.getWidth() / 3;
			LayoutParams2.width = imageAndText.getWidth() / 3;
			viewCache.getimage_6().setLayoutParams(LayoutParams2);
		} else {
			viewCache.getimage_6().setVisibility(View.GONE);
		}
		if (!imageAndText.getImage7().equals("")) {
			viewCache.getimage_7().setTag(URLcontainer.urlip + "upload" + imageAndText.getImage7());

			try {

				imageLoader.DisplayImage(URLcontainer.urlip + "upload" + imageAndText.getImage7(), activity,
						viewCache.getimage_7(), 0);
			} catch (Exception e) {
				// TODO: handle exception
			} catch (OutOfMemoryError e) {
				// TODO: handle exception
			}
			LinearLayout.LayoutParams LayoutParams2 = (android.widget.LinearLayout.LayoutParams) viewCache.getimage_7()
					.getLayoutParams();
			LayoutParams2.height = imageAndText.getWidth() / 3;
			LayoutParams2.width = imageAndText.getWidth() / 3;
			viewCache.getimage_7().setLayoutParams(LayoutParams2);
		} else {
			viewCache.getimage_7().setVisibility(View.GONE);
		}
		if (!imageAndText.getImage8().equals("")) {
			viewCache.getimage_8().setTag(URLcontainer.urlip + "upload" + imageAndText.getImage8());

			try {

				imageLoader.DisplayImage(URLcontainer.urlip + "upload" + imageAndText.getImage8(), activity,
						viewCache.getimage_8(), 0);
			} catch (Exception e) {
				// TODO: handle exception
			} catch (OutOfMemoryError e) {
				// TODO: handle exception
			}
			LinearLayout.LayoutParams LayoutParams2 = (android.widget.LinearLayout.LayoutParams) viewCache.getimage_8()
					.getLayoutParams();
			LayoutParams2.height = imageAndText.getWidth() / 3;
			LayoutParams2.width = imageAndText.getWidth() / 3;
			viewCache.getimage_8().setLayoutParams(LayoutParams2);
		} else {
			viewCache.getimage_8().setVisibility(View.GONE);
		}
		if (!imageAndText.getImage9().equals("")) {
			viewCache.getimage_9().setTag(URLcontainer.urlip + "upload" + imageAndText.getImage9());

			try {

				imageLoader.DisplayImage(URLcontainer.urlip + "upload" + imageAndText.getImage9(), activity,
						viewCache.getimage_9(), 0);
			} catch (Exception e) {
				// TODO: handle exception
			} catch (OutOfMemoryError e) {
				// TODO: handle exception
			}
			LinearLayout.LayoutParams LayoutParams2 = (android.widget.LinearLayout.LayoutParams) viewCache.getimage_9()
					.getLayoutParams();
			LayoutParams2.height = imageAndText.getWidth() / 3;
			LayoutParams2.width = imageAndText.getWidth() / 3;
			viewCache.getimage_9().setLayoutParams(LayoutParams2);
		} else {
			viewCache.getimage_9().setVisibility(View.GONE);
		}

		TextView texttime = viewCache.gettextTime();
		texttime.setText(imageAndText.getTime());

		TextView textcontent = viewCache.gettextContent();
		textcontent.setText(imageAndText.getContent());

		TextView textguanzhu = viewCache.gettextGuanzhu();
		textguanzhu.setText(imageAndText.getGuanzhu());

		TextView textzan = viewCache.gettextZan();
		textzan.setText(imageAndText.getZan());

		TextView textpl = viewCache.gettextPl();
		textpl.setText(imageAndText.getPl());
		TextView texttlt = viewCache.gettext_title();
		texttlt.setText(imageAndText.getTitle());
		TextView textname = viewCache.gettextName();
		textname.setText(imageAndText.getName());
		LinearLayout lin_all = viewCache.getlin_all();
		lin_all.setTag(position);
		lin_all.setOnClickListener(this);
		TextView textlabel = viewCache.gettextPltextLabel();
		textlabel.setText("" + imageAndText.getLabel() + "");
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
