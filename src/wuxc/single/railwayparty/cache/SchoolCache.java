package wuxc.single.railwayparty.cache;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import wuxc.single.railwayparty.R;

public class SchoolCache {
	private View baseView;
	private ImageView headimg;
	private ImageView imagetype;
	private TextView textTitle;

	private TextView textContent;
	private TextView textnumber;
	private ImageView imagebi;
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

	public TextView gettextnumber() {
		if (textnumber == null) {
			textnumber = (TextView) baseView.findViewById(R.id.text_number);
		}
		return textnumber;
	}

	public ImageView getheadimg() {
		if (headimg == null) {
			headimg = (ImageView) baseView.findViewById(R.id.image_pic);
		}
		return headimg;
	}

	public ImageView getimagebi() {
		if (imagebi == null) {
			imagebi = (ImageView) baseView.findViewById(R.id.image_bi);
		}
		return imagebi;
	}

	public ImageView getimagetype() {
		if (imagetype == null) {
			imagetype = (ImageView) baseView.findViewById(R.id.image_type);
		}
		return imagetype;
	}

	public SchoolCache(View baseView) {
		this.baseView = baseView;
	}
}
