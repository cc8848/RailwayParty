package wuxc.single.railwayparty.branch;

import android.app.Activity;
 
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import wuxc.single.railwayparty.R;

public class FragmentApplyAssistant extends Fragment implements OnClickListener {

	private View view;// ª∫¥ÊFragment view

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		if (null != view) {
			ViewGroup parent = (ViewGroup) view.getParent();
			if (null != parent) {
				parent.removeView(view);
			}
		} else {
			view = inflater.inflate(R.layout.wuxc_fragment_apply_assistant, container, false);
			initview(view);
			setonclicklistener();

		}

		return view;

	}

	private void setonclicklistener() {
		// TODO Auto-generated method stub

	}

	private void initview(View view2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

	}

}
