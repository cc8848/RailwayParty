package wuxc.single.railwayparty.adapter;

import java.util.List;

import android.app.Activity;
import android.graphics.Color;
import android.text.TextPaint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import wuxc.single.railwayparty.R;
import wuxc.single.railwayparty.cache.ArtCache;
import wuxc.single.railwayparty.model.ArtModel;

public class ArtAdapter extends ArrayAdapter<ArtModel> implements OnClickListener {

	private String imageurl = "";
	private int screenwidth = 0;

	private Callback mCallback;

	public ArtAdapter(Activity activity, List<ArtModel> imageAndTexts, Callback callback) {
		super(activity, 0, imageAndTexts);

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

		// Inflate the views from XML
		View rowView = convertView;
		ArtCache viewCache;
		if (rowView == null) {
			LayoutInflater inflater = activity.getLayoutInflater();

			rowView = inflater.inflate(R.layout.wuxc_item_art, null);

			viewCache = new ArtCache(rowView);
			rowView.setTag(viewCache);
		} else {
			LayoutInflater inflater = activity.getLayoutInflater();

			rowView = inflater.inflate(R.layout.wuxc_item_art, null);

			viewCache = new ArtCache(rowView);
			rowView.setTag(viewCache);
		}
		ArtModel imageAndText = getItem(position);

		// Load the image and set it on the ImageView

		TextView TextTitle = viewCache.getTextTitle();
		TextTitle.setText("" + imageAndText.getTitle());
		ImageView imageView = viewCache.getImageHeadimg();
		LinearLayout lin_all = viewCache.getlin_all();
		lin_all.setTag(position);
		lin_all.setOnClickListener(this);
		if (imageAndText.getColor() == 1) {
			imageView.setImageResource(R.drawable.sg);
			TextTitle.setTextColor(Color.parseColor("#ec6564"));
		} else {
			imageView.setImageResource(R.drawable.qh);
			TextTitle.setTextColor(Color.parseColor("#7bd1d2"));

		}
		if (!imageAndText.isRead()) {
			TextPaint tp = TextTitle.getPaint();
			tp.setFakeBoldText(true);
		} else {
			TextPaint tp = TextTitle.getPaint();
			tp.setFakeBoldText(false);
		}
		return rowView;
	}

}
