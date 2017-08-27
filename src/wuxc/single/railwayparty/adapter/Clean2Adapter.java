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
import wuxc.single.railwayparty.cache.Clean2Cache;
import wuxc.single.railwayparty.internet.ImageLoader;
import wuxc.single.railwayparty.internet.ImageLoader.ImageCallback;
import wuxc.single.railwayparty.layout.RoundImageView;
import wuxc.single.railwayparty.internet.URLcontainer;
import wuxc.single.railwayparty.model.Clean2Model;
import wuxc.single.railwayparty.start.ImageLoader600;

public class Clean2Adapter extends ArrayAdapter<Clean2Model> implements OnClickListener {
	private ListView listView;

	private String imageurl = "";
	private int screenwidth = 0;
	private Activity thisactivity;
	private Callback mCallback;
	public ImageLoader600 imageLoader;
	private static LayoutInflater inflater = null;

	public Clean2Adapter(Activity activity, List<Clean2Model> imageAndTexts, ListView listView, Callback callback) {
		super(activity, 0, imageAndTexts);
		this.listView = listView;
		this.thisactivity = activity;
		inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		imageLoader = new ImageLoader600(activity.getApplicationContext());

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
		Clean2Model imageAndText = getItem(position);

		// Inflate the views from XML
		View rowView = convertView;

		Clean2Cache viewCache;
		if (true) {
			LayoutInflater inflater = activity.getLayoutInflater();
			rowView = inflater.inflate(R.layout.wuxc_item_clean_2, null);
			viewCache = new Clean2Cache(rowView);
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

}
