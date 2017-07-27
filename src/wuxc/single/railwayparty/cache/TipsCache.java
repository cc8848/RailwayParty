package wuxc.single.railwayparty.cache;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import wuxc.single.railwayparty.R;
import wuxc.single.railwayparty.layout.RoundImageView;

public class TipsCache {
	private View baseView;
	private RoundImageView headimg;

	private TextView textTime;
	private TextView textContent;
	private TextView textGuanzhu;
	private TextView textZan;
	private TextView textName;
	private TextView textPl;
	private TextView textLabel;
	private LinearLayout lin_all;

	public LinearLayout getlin_all() {
		if (lin_all == null) {
			lin_all = (LinearLayout) baseView.findViewById(R.id.lin_all);
		}
		return lin_all;
	}

	public TextView gettextPl() {
		if (textPl == null) {
			textPl = (TextView) baseView.findViewById(R.id.text_pl);
		}
		return textPl;
	}

	public TextView gettextPltextLabel() {
		if (textLabel == null) {
			textLabel = (TextView) baseView.findViewById(R.id.text_label);
		}
		return textLabel;
	}

	public TextView gettextName() {
		if (textName == null) {
			textName = (TextView) baseView.findViewById(R.id.text_name);
		}
		return textName;
	}

	public TextView gettextContent() {
		if (textContent == null) {
			textContent = (TextView) baseView.findViewById(R.id.text_content);
		}
		return textContent;
	}

	public TextView gettextGuanzhu() {
		if (textGuanzhu == null) {
			textGuanzhu = (TextView) baseView.findViewById(R.id.text_guanzhu);
		}
		return textGuanzhu;
	}

	public TextView gettextTime() {
		if (textTime == null) {
			textTime = (TextView) baseView.findViewById(R.id.text_time);
		}
		return textTime;
	}

	public TextView gettextZan() {
		if (textZan == null) {
			textZan = (TextView) baseView.findViewById(R.id.text_zan);
		}
		return textZan;
	}

	public RoundImageView getheadimg() {
		if (headimg == null) {
			headimg = (RoundImageView) baseView.findViewById(R.id.round_headimg);
		}
		return headimg;
	}

	public TipsCache(View baseView) {
		this.baseView = baseView;
	}
}
