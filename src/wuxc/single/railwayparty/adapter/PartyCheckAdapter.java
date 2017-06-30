package wuxc.single.railwayparty.adapter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
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
import wuxc.single.railwayparty.cache.PartyCheckCache;
import wuxc.single.railwayparty.internet.ImageLoader;
import wuxc.single.railwayparty.internet.ImageLoader.ImageCallback;
import wuxc.single.railwayparty.internet.URLcontainer;
import wuxc.single.railwayparty.model.PartyCheckModel;

public class PartyCheckAdapter extends ArrayAdapter<PartyCheckModel> implements OnClickListener {
	private ListView listView;
	private ImageLoader ImageLoader;
	private String imageurl = "";
	private int screenwidth = 0;
	private Activity thisactivity;

	public PartyCheckAdapter(Activity activity, List<PartyCheckModel> imageAndTexts, ListView listView) {
		super(activity, 0, imageAndTexts);
		this.listView = listView;
		this.thisactivity = activity;
		ImageLoader = new ImageLoader();

	}

	public interface Callback {
		public void click(View v);
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		Activity activity = (Activity) getContext();
		PartyCheckModel imageAndText = getItem(position);

		// Inflate the views from XML
		View rowView = convertView;

		PartyCheckCache viewCache;
		if (rowView == null) {
			LayoutInflater inflater = activity.getLayoutInflater();
			rowView = inflater.inflate(R.layout.wuxc_item_partycheck, null);
			viewCache = new PartyCheckCache(rowView);
			rowView.setTag(viewCache);
		} else {
			viewCache = (PartyCheckCache) rowView.getTag();
		}

		// Load the image and set it on the ImageView

		TextView texttime = viewCache.gettextTime();
		texttime.setText(imageAndText.getTime());

		TextView texttitle = viewCache.gettextTitle();
		texttitle.setText(imageAndText.getTitle());

		return rowView;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

	}
}
