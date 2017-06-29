package wuxc.single.railwayparty.cache;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import wuxc.single.railwayparty.R;

public class TalkCache {

	private View baseView;
	private TextView TextName;
	private ImageView ImageHeadimg;
	private TextView TextDetail;

	public TalkCache(View baseView) {
		this.baseView = baseView;
	}

	public TextView getTextDetail() {
		if (TextDetail == null) {
			TextDetail = (TextView) baseView.findViewById(R.id.text_detail);
		}
		return TextDetail;
	}

	public ImageView getImageHeadimg() {
		if (ImageHeadimg == null) {
			ImageHeadimg = (ImageView) baseView.findViewById(R.id.image_headimg);
		}
		return ImageHeadimg;
	}

	public TextView getTextName() {
		if (TextName == null) {
			TextName = (TextView) baseView.findViewById(R.id.text_name);
		}
		return TextName;
	}

}
