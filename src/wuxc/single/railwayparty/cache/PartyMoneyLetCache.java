package wuxc.single.railwayparty.cache;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import wuxc.single.railwayparty.R;

public class PartyMoneyLetCache {

	private View baseView;

	private TextView TextTitle;

	public PartyMoneyLetCache(View baseView) {
		this.baseView = baseView;
	}

	public TextView getTextTitle() {
		if (TextTitle == null) {
			TextTitle = (TextView) baseView.findViewById(R.id.text_title);
		}
		return TextTitle;
	}

}
