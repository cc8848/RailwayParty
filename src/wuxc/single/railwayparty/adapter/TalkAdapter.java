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
import android.widget.ListView;
import android.widget.TextView;
import wuxc.single.railwayparty.R;
import wuxc.single.railwayparty.cache.TalkCache;

import wuxc.single.railwayparty.internet.URLcontainer;
import wuxc.single.railwayparty.model.Bbs1Model;
import wuxc.single.railwayparty.model.TalkModel;
import wuxc.single.railwayparty.start.ImageLoader;;

public class TalkAdapter extends ArrayAdapter<TalkModel> {
	private ListView listView;

	private String imageurl = "";
	private int screenwidth = 0;
	private Activity thisactivity;
	public ImageLoader imageLoader;
	private static LayoutInflater inflater = null;
	private List<Bbs1Model> imageAndTexts;

	public TalkAdapter(Activity activity, List<TalkModel> imageAndTexts, ListView listView) {
		super(activity, 0, imageAndTexts);
		this.listView = listView;
		this.thisactivity = activity;
		inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		imageLoader = new ImageLoader(activity.getApplicationContext());

	}

	public View getView(int position, View convertView, ViewGroup parent) {
		Activity activity = (Activity) getContext();
		TalkModel imageAndText = getItem(position);
		// Inflate the views from XML
		View rowView = convertView;
		TalkCache viewCache;

		LayoutInflater inflater = activity.getLayoutInflater();
		if (imageAndText.isMy()) {
			if (imageAndText.isPic()) {
				rowView = inflater.inflate(R.layout.wuxc_item_talk_my_pic, null);

			} else {
				rowView = inflater.inflate(R.layout.wuxc_item_talk_my, null);

			}
		} else {
			if (imageAndText.isPic()) {
				rowView = inflater.inflate(R.layout.wuxc_item_talk_other_pic, null);

			} else {
				rowView = inflater.inflate(R.layout.wuxc_item_talk_other, null);

			}
		}

		viewCache = new TalkCache(rowView);
		rowView.setTag(viewCache);

		viewCache.getImageHeadimg().setTag(URLcontainer.urlip + "upload" + imageAndText.getImageUrl());

		try {

			imageLoader.DisplayImage(URLcontainer.urlip + "upload" + imageAndText.getImageUrl(), activity,
					viewCache.getImageHeadimg(), 0);
		} catch (Exception e) {
			// TODO: handle exception
		} catch (OutOfMemoryError e) {
			// TODO: handle exception
		}
		viewCache.getimage().setTag(URLcontainer.urlip + "upload" + imageAndText.getImage());

		try {

			imageLoader.DisplayImage(URLcontainer.urlip + "upload" + imageAndText.getImage(), activity,
					viewCache.getimage(), 0);
		} catch (Exception e) {
			// TODO: handle exception
		} catch (OutOfMemoryError e) {
			// TODO: handle exception
		}
		TextView Textname = viewCache.getTextName();
		Textname.setText("" + imageAndText.getName());

		TextView textdetail = viewCache.getTextDetail();
		textdetail.setText("" + imageAndText.getDetail());
		return rowView;
	}

}
