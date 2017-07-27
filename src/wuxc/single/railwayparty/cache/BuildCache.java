package wuxc.single.railwayparty.cache;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import wuxc.single.railwayparty.R;

public class BuildCache {
	private View baseView;
	private ImageView headimg;
	private TextView textTitle;
	private TextView textTime;
	private TextView textContent;
	private TextView textGuanzhu;
	private TextView textZan;
	private ImageView imagebi;

	public ImageView getimagebi() {
		if (imagebi == null) {
			imagebi = (ImageView) baseView.findViewById(R.id.image_bi);
		}
		return imagebi;
	}

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

	public ImageView getheadimg() {
		if (headimg == null) {
			headimg = (ImageView) baseView.findViewById(R.id.image_pic);
		}
		return headimg;
	}

	public BuildCache(View baseView) {
		this.baseView = baseView;
	}
}
