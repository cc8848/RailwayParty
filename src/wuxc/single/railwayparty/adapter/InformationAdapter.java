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
import android.widget.RelativeLayout;
import android.widget.TextView;
import wuxc.single.railwayparty.R;
import wuxc.single.railwayparty.cache.InformationCache;
import wuxc.single.railwayparty.internet.ImageLoader;
import wuxc.single.railwayparty.internet.ImageLoader.ImageCallback;
import wuxc.single.railwayparty.internet.URLcontainer;
import wuxc.single.railwayparty.model.InformationModel;
import wuxc.single.railwayparty.start.ImageLoader120;

public class InformationAdapter extends ArrayAdapter<InformationModel> implements OnClickListener {
	private ListView listView;
	public ImageLoader120 imageLoader;
	private static LayoutInflater inflater = null;
	private String imageurl = "";
	private int screenwidth = 0;
	private Activity thisactivity;
	private Callback mCallback;

	public InformationAdapter(Activity activity, List<InformationModel> imageAndTexts, ListView listView,
			Callback callback) {
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
		InformationModel imageAndText = getItem(position);

		// Inflate the views from XML
		View rowView = convertView;

		InformationCache viewCache;
		if (true) {
			LayoutInflater inflater = activity.getLayoutInflater();
			if (position == 0) {
				rowView = inflater.inflate(R.layout.wuxc_item_information2, null);
			} else {
				rowView = inflater.inflate(R.layout.wuxc_item_information, null);
			}

			viewCache = new InformationCache(rowView);
			rowView.setTag(viewCache);

		}

		// Load the image and set it on the ImageView
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
		if (position == 0) {
			imageView.setImageResource(R.drawable.ztc);
			LinearLayout.LayoutParams LayoutParams = (android.widget.LinearLayout.LayoutParams) imageView
					.getLayoutParams();
			LayoutParams.height = imageAndText.getWidth() * 237 / 1125;
			imageView.setLayoutParams(LayoutParams);

		}
		TextView texttime = viewCache.gettextTime();
		texttime.setText(imageAndText.getTime());
		LinearLayout lin_all = viewCache.getlin_all();
		lin_all.setTag(position);
		lin_all.setOnClickListener(this);
		TextView textcontent = viewCache.gettextContent();
		textcontent.setText(imageAndText.getSummary());

		TextView texttitle = viewCache.gettextTitle();
		texttitle.setText(imageAndText.getTitle());
		if (imageAndText.isRead()) {
			texttitle.setTextColor(Color.parseColor("#7d7d7d"));
		} else {
			texttitle.setTextColor(Color.parseColor("#000000"));
		}
		return rowView;
	}

}
