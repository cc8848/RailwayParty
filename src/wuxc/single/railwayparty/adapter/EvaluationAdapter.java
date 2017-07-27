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
import wuxc.single.railwayparty.cache.EvaluationCache;
import wuxc.single.railwayparty.model.EvaluationModel;

public class EvaluationAdapter extends ArrayAdapter<EvaluationModel> implements OnClickListener {

	public EvaluationAdapter(Activity activity, List<EvaluationModel> imageAndTexts, ListView listView) {
		super(activity, 0, imageAndTexts);

	}

	public interface Callback {
		public void click(View v);
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		Activity activity = (Activity) getContext();
		EvaluationModel imageAndText = getItem(position);

		// Inflate the views from XML
		View rowView = convertView;

		EvaluationCache viewCache;
		if (true) {
			LayoutInflater inflater = activity.getLayoutInflater();
			rowView = inflater.inflate(R.layout.wuxc_item_evaluation, null);
			viewCache = new EvaluationCache(rowView);
			rowView.setTag(viewCache);
		} else {
			viewCache = (EvaluationCache) rowView.getTag();
		}

		// Load the image and set it on the ImageView

		TextView texttime = viewCache.getTextTime();
		texttime.setText(imageAndText.getTime());

		TextView texttitle = viewCache.getTextTitle();
		texttitle.setText(imageAndText.getTitle());

		TextView textdetail = viewCache.getTextDetail();
		textdetail.setText(imageAndText.getDetail());

		return rowView;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

	}
}
