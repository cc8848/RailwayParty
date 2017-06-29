package wuxc.single.railwayparty.cache;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import wuxc.single.railwayparty.R;

public class FileCache {

	private View baseView;
	private TextView TextTime;

	private TextView TextTitle;
	private TextView TextDetail;
	private TextView Textfrom;

	public FileCache(View baseView) {
		this.baseView = baseView;
	}

	public TextView getTextfrom() {
		if (Textfrom == null) {
			Textfrom = (TextView) baseView.findViewById(R.id.text_from);
		}
		return Textfrom;
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
