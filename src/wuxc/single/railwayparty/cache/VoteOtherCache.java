package wuxc.single.railwayparty.cache;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import wuxc.single.railwayparty.R;

public class VoteOtherCache {

	private View baseView;
	private TextView TextTime;
	private ImageView ImageHeadimg;
	private TextView TextTitle;
	private LinearLayout lin_all;

	public LinearLayout getlin_all() {
		if (lin_all == null) {
			lin_all = (LinearLayout) baseView.findViewById(R.id.lin_all);
		}
		return lin_all;
	}

	public VoteOtherCache(View baseView) {
		this.baseView = baseView;
	}

	public TextView getTextTitle() {
		if (TextTitle == null) {
			TextTitle = (TextView) baseView.findViewById(R.id.text_title);
		}
		return TextTitle;
	}

	public ImageView getImageHeadimg() {
		if (ImageHeadimg == null) {
			ImageHeadimg = (ImageView) baseView.findViewById(R.id.headimg);
		}
		return ImageHeadimg;
	}

	public TextView getTextTime() {
		if (TextTime == null) {
			TextTime = (TextView) baseView.findViewById(R.id.text_time);
		}
		return TextTime;
	}

}
