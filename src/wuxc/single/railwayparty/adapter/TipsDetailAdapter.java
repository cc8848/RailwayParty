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
import wuxc.single.railwayparty.cache.TipsDetailCache;

import wuxc.single.railwayparty.internet.URLcontainer;
import wuxc.single.railwayparty.model.TipsDetailModel;
import wuxc.single.railwayparty.start.ImageLoader600;

public class TipsDetailAdapter extends ArrayAdapter<TipsDetailModel> implements OnClickListener {
	private ListView listView;

	private String imageurl = "";
	private int screenwidth = 0;
	private Activity thisactivity;
	private Callback mCallback;
	public ImageLoader600 imageLoader;
	private static LayoutInflater inflater = null;
	private List<TipsDetailModel> imageAndTexts;

	public TipsDetailAdapter(Activity activity, List<TipsDetailModel> imageAndTexts, ListView listView,
			Callback callback) {
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
		TipsDetailModel imageAndText = getItem(position);

		// Inflate the views from XML
		View rowView = convertView;

		TipsDetailCache viewCache;

		if (imageAndText.getType() == 0) {
			LayoutInflater inflater = activity.getLayoutInflater();
			rowView = inflater.inflate(R.layout.wuxc_item_text, null);
			viewCache = new TipsDetailCache(rowView);
			rowView.setTag(viewCache);
		} else if (imageAndText.getType() == 1) {
			LayoutInflater inflater = activity.getLayoutInflater();
			rowView = inflater.inflate(R.layout.wuxc_item_pic, null);
			viewCache = new TipsDetailCache(rowView);
			rowView.setTag(viewCache);
		} else {
			LayoutInflater inflater = activity.getLayoutInflater();
			rowView = inflater.inflate(R.layout.wuxc_item_post, null);
			viewCache = new TipsDetailCache(rowView);
			rowView.setTag(viewCache);
		}

		// Load the image and set it on the ImageView
		if (!(imageAndText.getImageHeadimg().equals("") || imageAndText.getImageHeadimg() == null)) {
			viewCache.getimage_headimg().setTag(URLcontainer.urlip + "upload" + imageAndText.getImageHeadimg());

			try {

				imageLoader.DisplayImage(URLcontainer.urlip + "upload" + imageAndText.getImageHeadimg(), activity,
						viewCache.getimage_headimg(), 0);
			} catch (Exception e) {
				// TODO: handle exception
			} catch (OutOfMemoryError e) {
				// TODO: handle exception
			}
		}
		if (!(imageAndText.getImage().equals("") || imageAndText.getImage() == null)) {

			viewCache.getimage_pic().setTag(URLcontainer.urlip + "upload" + imageAndText.getImage());
			ViewGroup.LayoutParams lp = viewCache.getimage_pic().getLayoutParams();

			lp.height = LayoutParams.WRAP_CONTENT;
			viewCache.getimage_pic().setLayoutParams(lp);

			viewCache.getimage_pic().setMaxHeight(3000);
			try {

				imageLoader.DisplayImage(URLcontainer.urlip + "upload" + imageAndText.getImage(), activity,
						viewCache.getimage_pic(), 0);
			} catch (Exception e) {
				// TODO: handle exception
			} catch (OutOfMemoryError e) {
				// TODO: handle exception
			}
		}
		// }
		TextView text_name = viewCache.gettext_nickname();
		text_name.setText(imageAndText.getNickName());
		TextView text_content = viewCache.gettext_content();
		text_content.setText(imageAndText.getContent());
		TextView text_detail = viewCache.gettext_detail();
		text_detail.setText("    " + imageAndText.getDetail());
		TextView text_time = viewCache.gettext_time();
		text_time.setText(imageAndText.getTime());
		LinearLayout lin_all = viewCache.getlin_all();
		lin_all.setTag(position);
		lin_all.setOnClickListener(this);

		return rowView;
	}

}
