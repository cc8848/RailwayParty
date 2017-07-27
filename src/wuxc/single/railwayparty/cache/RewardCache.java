package wuxc.single.railwayparty.cache;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import wuxc.single.railwayparty.R;

public class RewardCache {
	private View baseView;
	private ImageView headimg;
	private TextView textTitle;
	private TextView textTime;
	private LinearLayout lin_all;

	public LinearLayout getlin_all() {
		if (lin_all == null) {
			lin_all = (LinearLayout) baseView.findViewById(R.id.lin_all);
		}
		return lin_all;
	}

	public TextView gettextTitle() {
		if (textTitle == null) {
			textTitle = (TextView) baseView.findViewById(R.id.text_title);
		}
		return textTitle;
	}

	public TextView gettextTime() {
		if (textTime == null) {
			textTime = (TextView) baseView.findViewById(R.id.text_time);
		}
		return textTime;
	}

	public ImageView getheadimg() {
		if (headimg == null) {
			headimg = (ImageView) baseView.findViewById(R.id.image_headimg);
		}
		return headimg;
	}

	public RewardCache(View baseView) {
		this.baseView = baseView;
	}
}
