package wuxc.single.railwayparty.adapter;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import wuxc.single.railwayparty.R;
import wuxc.single.railwayparty.cache.TalkCache;
import wuxc.single.railwayparty.internet.URLcontainer;
import wuxc.single.railwayparty.model.Bbs1Model;
import wuxc.single.railwayparty.model.TalkModel;
import wuxc.single.railwayparty.start.ImageLoaderChat;;

public class TalkAdapter extends ArrayAdapter<TalkModel> implements OnClickListener {
	private ListView listView;

	private String imageurl = "";
	private int screenwidth = 0;
	private Activity thisactivity;
	public ImageLoaderChat imageLoader;
	private static LayoutInflater inflater = null;
	private List<Bbs1Model> imageAndTexts;
	private Callback mCallback;

	public TalkAdapter(Activity activity, List<TalkModel> imageAndTexts, ListView listView, Callback callback) {
		super(activity, 0, imageAndTexts);
		this.listView = listView;
		this.thisactivity = activity;
		inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		imageLoader = new ImageLoaderChat(activity.getApplicationContext());
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
		if (!(imageAndText.getImage().equals("") || imageAndText.getImage() == null)) {

			viewCache.getimage().setTag(URLcontainer.urlip + "upload" + imageAndText.getImage());

			try {

				imageLoader.DisplayImage(URLcontainer.urlip + "upload" + imageAndText.getImage(), activity,
						viewCache.getimage(), 0);
			} catch (Exception e) {
				// TODO: handle exception
			} catch (OutOfMemoryError e) {
				// TODO: handle exception
			}
		}
		TextView Textname = viewCache.getTextName();
		Textname.setText("" + imageAndText.getName());
		LinearLayout lin_all = viewCache.getlin_all();
		lin_all.setTag(position);
		lin_all.setOnClickListener(this);
		TextView textdetail = viewCache.getTextDetail();
		textdetail.setText("" + imageAndText.getDetail());
		return rowView;
	}

}
