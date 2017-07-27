package wuxc.single.railwayparty.cache;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import wuxc.single.railwayparty.R;

public class MemberCache2 {

	private View baseView;

	private ImageView ImageHeadimg;
	private TextView TextTitle;
	private TextView TextName;
	private TextView text_mark;

	public TextView gettext_mark() {
		if (text_mark == null) {
			text_mark = (TextView) baseView.findViewById(R.id.text_mark);
		}
		return text_mark;
	}

	private LinearLayout lin_all;

	public LinearLayout getlin_all() {
		if (lin_all == null) {
			lin_all = (LinearLayout) baseView.findViewById(R.id.lin_all);
		}
		return lin_all;
	}

	public MemberCache2(View baseView) {
		this.baseView = baseView;
	}

	public TextView getTextName() {
		if (TextName == null) {
			TextName = (TextView) baseView.findViewById(R.id.text_name);
		}
		return TextName;
	}

	public TextView getTextTitle() {
		if (TextTitle == null) {
			TextTitle = (TextView) baseView.findViewById(R.id.text_title);
		}
		return TextTitle;
	}

	public ImageView getImageHeadimg() {
		if (ImageHeadimg == null) {
			ImageHeadimg = (ImageView) baseView.findViewById(R.id.image_headimg);
		}
		return ImageHeadimg;
	}

}
