package wuxc.single.railwayparty.other;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import wuxc.single.railwayparty.R;
import wuxc.single.railwayparty.layout.RoundImageView;

public class CandicateCache {

	private View baseView;
	private RoundImageView RoundImageview;
	private TextView TextNumber;
	private TextView TextName;
	private LinearLayout LinScale;
	private LinearLayout lin_all;

	public LinearLayout getlin_all() {
		if (lin_all == null) {
			lin_all = (LinearLayout) baseView.findViewById(R.id.lin_all);
		}
		return lin_all;
	}

	public CandicateCache(View baseView) {
		this.baseView = baseView;
	}

	public RoundImageView getRoundImageview() {
		if (RoundImageview == null) {
			RoundImageview = (RoundImageView) baseView.findViewById(R.id.round_headimg);
		}
		return RoundImageview;
	}

	public LinearLayout getLinScale() {
		if (LinScale == null) {
			LinScale = (LinearLayout) baseView.findViewById(R.id.lin_scale);
		}
		return LinScale;
	}

	public TextView getTextName() {
		if (TextName == null) {
			TextName = (TextView) baseView.findViewById(R.id.text_name);
		}
		return TextName;
	}

	public TextView getTextNumber() {
		if (TextNumber == null) {
			TextNumber = (TextView) baseView.findViewById(R.id.text_number);
		}
		return TextNumber;
	}

}
