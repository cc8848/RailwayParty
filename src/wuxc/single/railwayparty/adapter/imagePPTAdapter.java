package wuxc.single.railwayparty.adapter;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewPager.LayoutParams;
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
import wuxc.single.railwayparty.cache.imagePPTCache;

import wuxc.single.railwayparty.internet.URLcontainer;
import wuxc.single.railwayparty.model.imagePPTModel;
import wuxc.single.railwayparty.start.ImageLoader600;

public class imagePPTAdapter extends ArrayAdapter<imagePPTModel> implements OnClickListener {
	private ListView listView;

	private String imageurl = "";
	private int screenwidth = 0;
	private Activity thisactivity;
	private Callback mCallback;
	public ImageLoader600 imageLoader;
	private static LayoutInflater inflater = null;
	private List<imagePPTModel> imageAndTexts;

	public imagePPTAdapter(Activity activity, List<imagePPTModel> imageAndTexts, ListView listView, Callback callback) {
		super(activity, 0, imageAndTexts);
		this.listView = listView;
		this.thisactivity = activity;
		this.imageAndTexts = imageAndTexts;
		mCallback = callback;
		inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		imageLoader = new ImageLoader600(activity.getApplicationContext());
	}

	public interface Callback {
		public void click(View v);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		mCallback.click(v);
	}

	public int getCount() {
		return imageAndTexts.size();
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		Activity activity = (Activity) getContext();
		imagePPTModel imageAndText = getItem(position);

		// Inflate the views from XML
		View rowView = convertView;

		imagePPTCache viewCache;
		if (rowView == null) {
			LayoutInflater inflater = activity.getLayoutInflater();
			rowView = inflater.inflate(R.layout.wuxc_item_imageppt, null);
			viewCache = new imagePPTCache(rowView);
			rowView.setTag(viewCache);
		} else {
			LayoutInflater inflater = activity.getLayoutInflater();
			rowView = inflater.inflate(R.layout.wuxc_item_imageppt, null);
			viewCache = new imagePPTCache(rowView);
			rowView.setTag(viewCache);
		}

		// Load the image and set it on the ImageView
		String imageUrl = imageAndText.getHeadimgUrl();
		ImageView imageView = viewCache.getheadimg();
		if (!(imageAndText.getHeadimgUrl().equals("") || imageAndText.getHeadimgUrl() == null)) {
			imageView.setTag(URLcontainer.urlip + "upload" + imageUrl);

			try {

				imageLoader.DisplayImage(URLcontainer.urlip + "upload" + imageAndText.getHeadimgUrl(), activity,
						imageView, imageAndText.getImageurl());
			} catch (Exception e) {
				// TODO: handle exception
			} catch (OutOfMemoryError e) {
				// TODO: handle exception
			}
		}

		ViewGroup.LayoutParams lp = imageView.getLayoutParams();

		lp.height = LayoutParams.WRAP_CONTENT;
		imageView.setLayoutParams(lp);
		imageView.setMaxHeight(3000);
		LinearLayout lin_all = viewCache.getlin_all();
		lin_all.setTag(position);
		lin_all.setOnClickListener(this);

		return rowView;
	}

}
