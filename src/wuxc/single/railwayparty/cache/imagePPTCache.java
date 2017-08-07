package wuxc.single.railwayparty.cache;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import wuxc.single.railwayparty.R;

public class imagePPTCache {
	private View baseView;
	private ImageView headimg;

	private LinearLayout lin_all;

	public LinearLayout getlin_all() {
		if (lin_all == null) {
			lin_all = (LinearLayout) baseView.findViewById(R.id.lin_all);
		}
		return lin_all;
	}

	public ImageView getheadimg() {
		if (headimg == null) {
			headimg = (ImageView) baseView.findViewById(R.id.image_pic);
		}
		return headimg;
	}

	public imagePPTCache(View baseView) {
		this.baseView = baseView;
	}
}
