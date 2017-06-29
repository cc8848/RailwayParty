package wuxc.single.railwayparty.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;
import android.view.View.OnClickListener;
import wuxc.single.railwayparty.R;

public class ExamFragment extends Fragment implements OnClickListener {
	private View view;// ª∫¥ÊFragment view

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		if (null != view) {
			ViewGroup parent = (ViewGroup) view.getParent();
			if (null != parent) {
				parent.removeView(view);
			}
		} else {
			view = inflater.inflate(R.layout.wuxc_fragment_exam, container, false);
			// initview(view);

		}

		return view;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

	}

}
