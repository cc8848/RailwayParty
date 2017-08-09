package wuxc.single.railwayparty.cache;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import wuxc.single.railwayparty.R;
import wuxc.single.railwayparty.layout.RoundImageView;

public class TipsDetailCache {
	private View baseView;
	private LinearLayout lin_all;

	public LinearLayout getlin_all() {
		if (lin_all == null) {
			lin_all = (LinearLayout) baseView.findViewById(R.id.lin_all);
		}
		return lin_all;
	}

	private ImageView image_headimg;

	public ImageView getimage_headimg() {
		if (image_headimg == null) {
			image_headimg = (ImageView) baseView.findViewById(R.id.image_headimg);
		}
		return image_headimg;
	}

	private ImageView image_pic;

	public ImageView getimage_pic() {
		if (image_pic == null) {
			image_pic = (ImageView) baseView.findViewById(R.id.image_pic);
		}
		return image_pic;
	}

	private TextView text_detail;

	public TextView gettext_detail() {
		if (text_detail == null) {
			text_detail = (TextView) baseView.findViewById(R.id.text_detail);
		}
		return text_detail;
	}

	private TextView text_time;

	public TextView gettext_time() {
		if (text_time == null) {
			text_time = (TextView) baseView.findViewById(R.id.text_time);
		}
		return text_time;
	}

	private TextView text_content;

	public TextView gettext_content() {
		if (text_content == null) {
			text_content = (TextView) baseView.findViewById(R.id.text_content);
		}
		return text_content;
	}

	private TextView text_nickname;

	public TextView gettext_nickname() {
		if (text_nickname == null) {
			text_nickname = (TextView) baseView.findViewById(R.id.text_nickname);
		}
		return text_nickname;
	}

	public TipsDetailCache(View baseView) {
		this.baseView = baseView;
	}
}
