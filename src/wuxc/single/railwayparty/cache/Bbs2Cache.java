package wuxc.single.railwayparty.cache;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import wuxc.single.railwayparty.R;

public class Bbs2Cache {
	private View baseView;
	private ImageView headimg;
	private TextView textTitle;
	private TextView textTime;
	private TextView textContent;
	private TextView textLabel;

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

	public TextView gettextLabel() {
		if (textLabel == null) {
			textLabel = (TextView) baseView.findViewById(R.id.text_label);
		}
		return textLabel;
	}

	public ImageView getheadimg() {
		if (headimg == null) {
			headimg = (ImageView) baseView.findViewById(R.id.headimg);
		}
		return headimg;
	}

	public Bbs2Cache(View baseView) {
		this.baseView = baseView;
	}
}
