package wuxc.single.railwayparty.cache;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import wuxc.single.railwayparty.R;

public class MycollectCache {

	private View baseView;
	private TextView TextTime;
	private ImageView ImageHeadimg;
	private TextView TextTitle;
	private ImageView Imagedelete;

	public MycollectCache(View baseView) {
		this.baseView = baseView;
	}

	public ImageView getImagedelete() {
		if (Imagedelete == null) {
			Imagedelete = (ImageView) baseView.findViewById(R.id.image_delete);
		}
		return Imagedelete;
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
