package wuxc.single.railwayparty.adapter;

import java.util.List;

import android.app.Activity;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import wuxc.single.railwayparty.R;
import wuxc.single.railwayparty.adapter.Bbs1Adapter.Callback;
import wuxc.single.railwayparty.cache.MessageCache;
import wuxc.single.railwayparty.model.MessageModel;

public class MessageAdapter extends ArrayAdapter<MessageModel> implements OnClickListener {
	private Callback mCallback;

	public MessageAdapter(Activity activity, List<MessageModel> imageAndTexts, ListView listView, Callback callback) {
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
		MessageModel imageAndText = getItem(position);

		// Inflate the views from XML
		View rowView = convertView;

		MessageCache viewCache;
		if (true) {
			LayoutInflater inflater = activity.getLayoutInflater();
			rowView = inflater.inflate(R.layout.wuxc_item_message, null);
			viewCache = new MessageCache(rowView);
			rowView.setTag(viewCache);

		}

		// Load the image and set it on the ImageView

		TextView texttime = viewCache.getTextTime();
		texttime.setText(imageAndText.getTime());

		TextView texttitle = viewCache.getTextTitle();
		texttitle.setText(imageAndText.getTitle());
		if (imageAndText.isRead()) {
			texttitle.setTextColor(Color.parseColor("#7d7d7d"));
		} else {
			texttitle.setTextColor(Color.parseColor("#000000"));
		}
		LinearLayout lin_all = viewCache.getlin_all();
		lin_all.setTag(position);
		lin_all.setOnClickListener(this);
		return rowView;
	}

}
