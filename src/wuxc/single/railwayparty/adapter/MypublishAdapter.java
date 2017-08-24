package wuxc.single.railwayparty.adapter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import wuxc.single.railwayparty.R;
import wuxc.single.railwayparty.cache.MypublishCache;
import wuxc.single.railwayparty.internet.ImageLoader;
import wuxc.single.railwayparty.model.MypublishModel;
import wuxc.single.railwayparty.start.ImageLoader120;;

public class MypublishAdapter extends ArrayAdapter<MypublishModel> implements OnClickListener {
	private ListView listView;
	private ImageLoader ImageLoader;
	private String imageurl = "";
	private int screenwidth = 0;
	private Activity thisactivity;
	private Callback mCallback;
	public ImageLoader120 imageLoader;
	private static LayoutInflater inflater = null;

	public MypublishAdapter(Activity activity, List<MypublishModel> imageAndTexts, ListView listView,
			Callback callback) {
		super(activity, 0, imageAndTexts);
		this.listView = listView;
		this.thisactivity = activity;
		ImageLoader = new ImageLoader();
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
		MypublishCache viewCache;
		if (true) {
			LayoutInflater inflater = activity.getLayoutInflater();

			rowView = inflater.inflate(R.layout.wuxc_item_mypublish, null);

			viewCache = new MypublishCache(rowView);
			rowView.setTag(viewCache);

		}
		MypublishModel imageAndText = getItem(position);

		// Load the image and set it on the ImageView

		TextView TextTitle = viewCache.getTextTitle();
		TextTitle.setText("" + imageAndText.getTitle());

		TextView texttime = viewCache.getTextTime();
		texttime.setText("" + imageAndText.getTime());

		TextView textdetail = viewCache.getTextDetail();
		textdetail.setText("" + imageAndText.getDetail());

		TextView textlabel = viewCache.getTextLabel();
		textlabel.setText("" + imageAndText.getLabel());

		TextView textnumber = viewCache.getTextNumber();
		textnumber.setText("" + (imageAndText.getNumber() - position));
		ImageView imagedelete = viewCache.getImagedelete();
		imagedelete.setTag(position);
		imagedelete.setOnClickListener(this);
		return rowView;
	}

}
