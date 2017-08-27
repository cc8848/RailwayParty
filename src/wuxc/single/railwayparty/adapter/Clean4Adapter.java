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
import wuxc.single.railwayparty.cache.Clean4Cache;
import wuxc.single.railwayparty.internet.ImageLoader;
import wuxc.single.railwayparty.internet.ImageLoader.ImageCallback;
import wuxc.single.railwayparty.internet.URLcontainer;
import wuxc.single.railwayparty.model.Clean4Model;
import wuxc.single.railwayparty.start.ImageLoader120;;

public class Clean4Adapter extends ArrayAdapter<Clean4Model> implements OnClickListener {
	private ListView listView;

	private String imageurl = "";
	private int screenwidth = 0;
	private Activity thisactivity;
	private Callback mCallback;
	public ImageLoader120 imageLoader;
	private static LayoutInflater inflater = null;

	public Clean4Adapter(Activity activity, List<Clean4Model> imageAndTexts, ListView listView, Callback callback) {
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

		// Inflate the views from XML
		View rowView = convertView;
		Clean4Cache viewCache;
		if (true) {
			LayoutInflater inflater = activity.getLayoutInflater();

			rowView = inflater.inflate(R.layout.wuxc_item_clean_4, null);

			viewCache = new Clean4Cache(rowView);
			rowView.setTag(viewCache);

		}
		Clean4Model imageAndText = getItem(position);

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
		LinearLayout lin_all = viewCache.getlin_all();
		lin_all.setTag(position);
		lin_all.setOnClickListener(this);

		return rowView;
	}

}
