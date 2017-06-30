package wuxc.single.railwayparty.cache;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import wuxc.single.railwayparty.R;

public class InformationCache {
	private View baseView;
	private ImageView headimg;
	private TextView textTitle;
	private TextView textTime;
	private TextView textContent;

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

	public TextView gettextTime() {
		if (textTime == null) {
			textTime = (TextView) baseView.findViewById(R.id.text_time);
		}
		return textTime;
	}

	public ImageView getheadimg() {
		if (headimg == null) {
			headimg = (ImageView) baseView.findViewById(R.id.image_pic);
		}
		return headimg;
	}

	public InformationCache(View baseView) {
		this.baseView = baseView;
	}
}
