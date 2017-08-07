package wuxc.single.railwayparty.adapter;

import java.util.List;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import wuxc.single.railwayparty.R;
import wuxc.single.railwayparty.cache.CreditsCache;
import wuxc.single.railwayparty.model.CreditsModel;

public class CreditsAdapter extends ArrayAdapter<CreditsModel> implements OnClickListener {

	public CreditsAdapter(Activity activity, List<CreditsModel> imageAndTexts, ListView listView) {
		super(activity, 0, imageAndTexts);

	}

	public interface Callback {
		public void click(View v);
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		Activity activity = (Activity) getContext();
		CreditsModel imageAndText = getItem(position);

		// Inflate the views from XML
		View rowView = convertView;

		CreditsCache viewCache;
		if (true) {
			LayoutInflater inflater = activity.getLayoutInflater();
			rowView = inflater.inflate(R.layout.wuxc_item_credits, null);
			viewCache = new CreditsCache(rowView);
			rowView.setTag(viewCache);
		} else {
			viewCache = (CreditsCache) rowView.getTag();
		}

		// Load the image and set it on the ImageView

		TextView texttime = viewCache.getTextTime();
		texttime.setText(imageAndText.getTime());

		TextView texttitle = viewCache.getTextTitle();
		texttitle.setText(imageAndText.getTitle());

		TextView textdetail = viewCache.getTextDetail();
		textdetail.setText(imageAndText.getTime());

		TextView textnumber = viewCache.getTextNumber();
		textnumber.setText(imageAndText.getNumber());
		return rowView;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

	}
}
