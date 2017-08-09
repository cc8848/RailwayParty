package wuxc.single.railwayparty.cache;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import wuxc.single.railwayparty.R;
import wuxc.single.railwayparty.layout.RoundImageView;

public class Bbs1Cache {
	private View baseView;
	private RoundImageView headimg;
	private TextView textTitle;
	private TextView textTime;
	private TextView textContent;
	private TextView textGuanzhu;
	private TextView textZan;
	private TextView textName;
	private TextView textPl;
	private TextView textLabel;
	private LinearLayout lin_all;
	private ImageView image_1;

	public ImageView getimage_1() {
		if (image_1 == null) {
			image_1 = (ImageView) baseView.findViewById(R.id.image_1);
		}
		return image_1;
	}

	private ImageView image_2;

	public ImageView getimage_2() {
		if (image_2 == null) {
			image_2 = (ImageView) baseView.findViewById(R.id.image_2);
		}
		return image_2;
	}

	private ImageView image_3;

	public ImageView getimage_3() {
		if (image_3 == null) {
			image_3 = (ImageView) baseView.findViewById(R.id.image_3);
		}
		return image_3;
	}

	private ImageView image_4;

	public ImageView getimage_4() {
		if (image_4 == null) {
			image_4 = (ImageView) baseView.findViewById(R.id.image_4);
		}
		return image_4;
	}

	private ImageView image_5;

	public ImageView getimage_5() {
		if (image_5 == null) {
			image_5 = (ImageView) baseView.findViewById(R.id.image_5);
		}
		return image_5;
	}

	private ImageView image_6;

	public ImageView getimage_6() {
		if (image_6 == null) {
			image_6 = (ImageView) baseView.findViewById(R.id.image_6);
		}
		return image_6;
	}

	private ImageView image_7;

	public ImageView getimage_7() {
		if (image_7 == null) {
			image_7 = (ImageView) baseView.findViewById(R.id.image_7);
		}
		return image_7;
	}

	private ImageView image_8;

	public ImageView getimage_8() {
		if (image_8 == null) {
			image_8 = (ImageView) baseView.findViewById(R.id.image_8);
		}
		return image_8;
	}

	private ImageView image_9;

	public ImageView getimage_9() {
		if (image_9 == null) {
			image_9 = (ImageView) baseView.findViewById(R.id.image_9);
		}
		return image_9;
	}

	public LinearLayout getlin_all() {
		if (lin_all == null) {
			lin_all = (LinearLayout) baseView.findViewById(R.id.lin_all);
		}
		return lin_all;
	}

	public TextView gettextPltextLabel() {
		if (textLabel == null) {
			textLabel = (TextView) baseView.findViewById(R.id.text_label);
		}
		return textLabel;
	}

	public TextView gettextPl() {
		if (textPl == null) {
			textPl = (TextView) baseView.findViewById(R.id.text_pl);
		}
		return textPl;
	}

	public TextView gettextName() {
		if (textName == null) {
			textName = (TextView) baseView.findViewById(R.id.text_name);
		}
		return textName;
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

	public RoundImageView getheadimg() {
		if (headimg == null) {
			headimg = (RoundImageView) baseView.findViewById(R.id.round_headimg);
		}
		return headimg;
	}

	public Bbs1Cache(View baseView) {
		this.baseView = baseView;
	}
}
