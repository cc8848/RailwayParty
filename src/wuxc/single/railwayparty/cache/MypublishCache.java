package wuxc.single.railwayparty.cache;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import wuxc.single.railwayparty.R;

public class MypublishCache {

	private View baseView;
	private TextView TextTime;
	private TextView TextTitle;
	private TextView TextDetail;
	private TextView TextLabel;
	private TextView TextNumber;
	private ImageView Imagedelete;

	public MypublishCache(View baseView) {
		this.baseView = baseView;
	}

	public ImageView getImagedelete() {
		if (Imagedelete == null) {
			Imagedelete = (ImageView) baseView.findViewById(R.id.image_delete);
		}
		return Imagedelete;
	}

	public TextView getTextNumber() {
		if (TextNumber == null) {
			TextNumber = (TextView) baseView.findViewById(R.id.text_number);
		}
		return TextNumber;
	}

	public TextView getTextLabel() {
		if (TextLabel == null) {
			TextLabel = (TextView) baseView.findViewById(R.id.text_label);
		}
		return TextLabel;
	}

	public TextView getTextDetail() {
		if (TextDetail == null) {
			TextDetail = (TextView) baseView.findViewById(R.id.text_detail);
		}
		return TextDetail;
	}

	public TextView getTextTitle() {
		if (TextTitle == null) {
			TextTitle = (TextView) baseView.findViewById(R.id.text_title);
		}
		return TextTitle;
	}

	public TextView getTextTime() {
		if (TextTime == null) {
			TextTime = (TextView) baseView.findViewById(R.id.text_time);
		}
		return TextTime;
	}

}
