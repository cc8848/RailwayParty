package wuxc.single.railwayparty;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import wuxc.single.railwayparty.main.InformActivity;
import wuxc.single.railwayparty.main.PolicyActivity;
import wuxc.single.railwayparty.main.WebLearnActivity;
import android.view.ViewGroup;

public class MainFragment extends MainBaseFragment implements OnClickListener {
	private int screenwidth = 0;
	private ImageView ImageGobg;
	private RelativeLayout rel_main;
	private RelativeLayout main_top_bac;
	private LinearLayout rel_inform;
	private LinearLayout rel_policy;
	private LinearLayout rel_3;
	private LinearLayout rel_weblearn;
	private RelativeLayout rel_5;
	private RelativeLayout rel_6;
	private RelativeLayout rel_7;
	private RelativeLayout rel_8;
	private RelativeLayout rel_9;
	private RelativeLayout rel_10;
	private RelativeLayout rel_11;
	private RelativeLayout rel_12;
	private LinearLayout lin_four;
	private ImageView headimg;
	private TextView text_1;
	private TextView text_2;
	private TextView text_3;
	private TextView text_4;
	private TextView text_5;
	private TextView text_6;
	private TextView text_7;
	private TextView text_8;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.wuxc_fragment_main, container, false);
		screenwidth = getActivity().getWindow().getWindowManager().getDefaultDisplay().getWidth();
		initview(view);
		initheight(view);
		return view;
	}

	private void initview(View view) {
		// TODO Auto-generated method stub
		ImageGobg = (ImageView) view.findViewById(R.id.image_go_zb);
		rel_main = (RelativeLayout) view.findViewById(R.id.rel_main);
		main_top_bac = (RelativeLayout) view.findViewById(R.id.main_top_bac);
		rel_inform = (LinearLayout) view.findViewById(R.id.rel_inform);
		rel_policy = (LinearLayout) view.findViewById(R.id.rel_policy);
		rel_3 = (LinearLayout) view.findViewById(R.id.rel_3);
		rel_weblearn = (LinearLayout) view.findViewById(R.id.rel_weblearn);

		rel_5 = (RelativeLayout) view.findViewById(R.id.rel_5);
		rel_6 = (RelativeLayout) view.findViewById(R.id.rel_6);
		rel_7 = (RelativeLayout) view.findViewById(R.id.rel_7);
		rel_8 = (RelativeLayout) view.findViewById(R.id.rel_8);
		rel_9 = (RelativeLayout) view.findViewById(R.id.rel_9);
		rel_10 = (RelativeLayout) view.findViewById(R.id.rel_10);
		rel_11 = (RelativeLayout) view.findViewById(R.id.rel_11);
		rel_12 = (RelativeLayout) view.findViewById(R.id.rel_12);
		text_1 = (TextView) view.findViewById(R.id.text_1);
		text_2 = (TextView) view.findViewById(R.id.text_2);
		text_3 = (TextView) view.findViewById(R.id.text_3);
		text_4 = (TextView) view.findViewById(R.id.text_4);
		text_5 = (TextView) view.findViewById(R.id.text_5);
		text_6 = (TextView) view.findViewById(R.id.text_6);
		text_7 = (TextView) view.findViewById(R.id.text_7);
		text_8 = (TextView) view.findViewById(R.id.text_8);
		lin_four = (LinearLayout) view.findViewById(R.id.lin_four);
		headimg = (ImageView) view.findViewById(R.id.headimg);
		rel_inform.setOnClickListener(this);
		rel_policy.setOnClickListener(this);
		rel_3.setOnClickListener(this);
		rel_weblearn.setOnClickListener(this);
		rel_5.setOnClickListener(this);
		rel_6.setOnClickListener(this);
		rel_7.setOnClickListener(this);
		rel_8.setOnClickListener(this);
		showtext();
	}

	private void showtext() {
		// TODO Auto-generated method stub
		text_1.setVisibility(View.GONE);
		text_2.setVisibility(View.GONE);
		text_3.setVisibility(View.GONE);
		text_4.setVisibility(View.GONE);
		text_5.setVisibility(View.GONE);
		text_6.setVisibility(View.GONE);
		text_7.setVisibility(View.GONE);
		text_8.setVisibility(View.GONE);
	}

	private void initheight(View view) {
		// TODO Auto-generated method stub
		int height = (int) (screenwidth / 3.3);
		RelativeLayout.LayoutParams LayoutParams = (android.widget.RelativeLayout.LayoutParams) ImageGobg
				.getLayoutParams();
		LayoutParams.height = height;
		ImageGobg.setLayoutParams(LayoutParams);

		RelativeLayout.LayoutParams LayoutParams1 = (android.widget.RelativeLayout.LayoutParams) rel_main
				.getLayoutParams();
		LayoutParams1.bottomMargin = height;
		rel_main.setLayoutParams(LayoutParams1);
		height = (int) (screenwidth / 2.5);
		RelativeLayout.LayoutParams LayoutParams2 = (android.widget.RelativeLayout.LayoutParams) main_top_bac
				.getLayoutParams();
		LayoutParams2.height = height;
		main_top_bac.setLayoutParams(LayoutParams2);
		RelativeLayout.LayoutParams LayoutParams9 = (android.widget.RelativeLayout.LayoutParams) headimg
				.getLayoutParams();
		LayoutParams9.height = (int) (height / 2);
		LayoutParams9.width = (int) (height / 2);
		LayoutParams9.topMargin = height / 5;
		headimg.setLayoutParams(LayoutParams9);

		height = (int) (screenwidth / 2.1);
		RelativeLayout.LayoutParams LayoutParams3 = (android.widget.RelativeLayout.LayoutParams) lin_four
				.getLayoutParams();
		LayoutParams3.height = height;
		lin_four.setLayoutParams(LayoutParams3);

		height = (int) (screenwidth / 2.1 / 2) - 8;
		LinearLayout.LayoutParams LayoutParams4 = (android.widget.LinearLayout.LayoutParams) rel_9.getLayoutParams();
		LayoutParams4.height = height;
		LayoutParams4.width = height;
		rel_9.setLayoutParams(LayoutParams4);

		LinearLayout.LayoutParams LayoutParams5 = (android.widget.LinearLayout.LayoutParams) rel_10.getLayoutParams();
		LayoutParams5.height = height;
		LayoutParams5.width = height;
		rel_10.setLayoutParams(LayoutParams5);

		LinearLayout.LayoutParams LayoutParams6 = (android.widget.LinearLayout.LayoutParams) rel_11.getLayoutParams();
		LayoutParams6.height = height;
		LayoutParams6.width = height;
		rel_11.setLayoutParams(LayoutParams6);

		LinearLayout.LayoutParams LayoutParams7 = (android.widget.LinearLayout.LayoutParams) rel_12.getLayoutParams();
		LayoutParams7.height = height;
		LayoutParams7.width = height;
		rel_12.setLayoutParams(LayoutParams7);
	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();

		MainActivity.curFragmentTag = getString(R.string.str_main);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.rel_inform:
			Intent intent_inform = new Intent();
			intent_inform.setClass(getActivity(), InformActivity.class);
			startActivity(intent_inform);
			break;
		case R.id.rel_policy:
			Intent intent_rel_policy = new Intent();
			intent_rel_policy.setClass(getActivity(), PolicyActivity.class);
			startActivity(intent_rel_policy);
			break;
		case R.id.rel_3:

			break;
		case R.id.rel_weblearn:
			Intent intent_rel_weblearn = new Intent();
			intent_rel_weblearn.setClass(getActivity(), WebLearnActivity.class);
			startActivity(intent_rel_weblearn);
			break;
		case R.id.rel_5:

			break;
		case R.id.rel_6:

			break;
		case R.id.rel_7:

			break;
		case R.id.rel_8:

			break;

		default:
			break;
		}
	}

}
