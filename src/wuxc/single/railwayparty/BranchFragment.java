package wuxc.single.railwayparty;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.view.ViewGroup;

public class BranchFragment extends MainBaseFragment implements OnClickListener {
	private int screenwidth = 0;
	private float scale = 0;
	private float scalepx = 0;
	private float dp = 0;
	private LinearLayout lin_1;
	private LinearLayout lin_2;
	private LinearLayout lin_3;
	private LinearLayout rel_1;
	private LinearLayout rel_2;
	private LinearLayout rel_3;
	private LinearLayout rel_4;
	private LinearLayout rel_5;
	private LinearLayout rel_6;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.wuxc_fragment_branch, container, false);
		initview(view);
		initheight(view);
		return view;
	}

	private void initheight(View view) {
		// TODO Auto-generated method stub
		int rel_width = (int) ((screenwidth - 80 * scalepx) / 3);
//		Log.e("screenwidth", "" + screenwidth);
//		Log.e("rel_width", "" + rel_width);
		int rel_height = (int) (rel_width / 1.73 * 2);
		int margin = (int) ((rel_height + 6 * scalepx) * 3 / 4);
		RelativeLayout.LayoutParams LayoutParams = (android.widget.RelativeLayout.LayoutParams) lin_2.getLayoutParams();
		LayoutParams.topMargin = margin;
		lin_2.setLayoutParams(LayoutParams);

		RelativeLayout.LayoutParams LayoutParams2 = (android.widget.RelativeLayout.LayoutParams) lin_3
				.getLayoutParams();
		LayoutParams2.topMargin = margin * 2;
		lin_3.setLayoutParams(LayoutParams2);

		LinearLayout.LayoutParams LayoutParams3 = (android.widget.LinearLayout.LayoutParams) rel_1.getLayoutParams();
		LayoutParams3.height = rel_height;
		LayoutParams3.width = rel_width;
		rel_1.setLayoutParams(LayoutParams3);

		LinearLayout.LayoutParams LayoutParams4 = (android.widget.LinearLayout.LayoutParams) rel_2.getLayoutParams();
		LayoutParams4.height = rel_height;
		LayoutParams4.width = rel_width;
		rel_2.setLayoutParams(LayoutParams4);

		LinearLayout.LayoutParams LayoutParams5 = (android.widget.LinearLayout.LayoutParams) rel_3.getLayoutParams();
		LayoutParams5.height = rel_height;
		LayoutParams5.width = rel_width;
		rel_3.setLayoutParams(LayoutParams5);

		LinearLayout.LayoutParams LayoutParams6 = (android.widget.LinearLayout.LayoutParams) rel_4.getLayoutParams();
		LayoutParams6.height = rel_height;
		LayoutParams6.width = rel_width;
		rel_4.setLayoutParams(LayoutParams6);

		LinearLayout.LayoutParams LayoutParams7 = (android.widget.LinearLayout.LayoutParams) rel_5.getLayoutParams();
		LayoutParams7.height = rel_height;
		LayoutParams7.width = rel_width;
		rel_5.setLayoutParams(LayoutParams7);

		LinearLayout.LayoutParams LayoutParams8 = (android.widget.LinearLayout.LayoutParams) rel_6.getLayoutParams();
		LayoutParams8.height = rel_height;
		LayoutParams8.width = rel_width;
		rel_6.setLayoutParams(LayoutParams8);
	}

	private void initview(View view) {
		// TODO Auto-generated method stub
		screenwidth = getActivity().getWindow().getWindowManager().getDefaultDisplay().getWidth();
		DisplayMetrics mMetrics = new DisplayMetrics();
		getActivity().getWindowManager().getDefaultDisplay().getMetrics(mMetrics);
		scale = getActivity().getResources().getDisplayMetrics().density;
		// Log.e("mMetrics", mMetrics.toString() + "scale=" + scale + "0.5f"
		// +
		// 0.5f);
		dp = screenwidth / scale + 0.5f;
		scalepx = screenwidth / dp;
		lin_1 = (LinearLayout) view.findViewById(R.id.lin_1);
		lin_2 = (LinearLayout) view.findViewById(R.id.lin_2);
		lin_3 = (LinearLayout) view.findViewById(R.id.lin_3);
		rel_1 = (LinearLayout) view.findViewById(R.id.rel_1);
		rel_2 = (LinearLayout) view.findViewById(R.id.rel_2);
		rel_3 = (LinearLayout) view.findViewById(R.id.rel_3);
		rel_4 = (LinearLayout) view.findViewById(R.id.rel_4);
		rel_5 = (LinearLayout) view.findViewById(R.id.rel_5);
		rel_6 = (LinearLayout) view.findViewById(R.id.rel_6);
	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();

		MainActivity.curFragmentTag = getString(R.string.str_branch);

	}

	@Override
	public void onClick(View v) {
	}

}
