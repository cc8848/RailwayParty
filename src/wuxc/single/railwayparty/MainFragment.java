package wuxc.single.railwayparty;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import wuxc.single.railwayparty.main.CleanActivity;
import wuxc.single.railwayparty.main.DisciplinaryActivity;
import wuxc.single.railwayparty.main.FlagActivity;
import wuxc.single.railwayparty.main.InformActivity;
import wuxc.single.railwayparty.main.MemberActivity;
import wuxc.single.railwayparty.main.PartyManageActivity;
import wuxc.single.railwayparty.main.PolicyActivity;
import wuxc.single.railwayparty.main.WebLearnActivity;
import wuxc.single.railwayparty.other.LoginActivity;
import wuxc.single.railwayparty.other.SearchActivity;
import android.view.ViewGroup;

public class MainFragment extends MainBaseFragment implements OnClickListener {
	private int screenwidth = 0;
	private ImageView ImageGobg;
	private RelativeLayout rel_main;
	private RelativeLayout main_top_bac;
	private LinearLayout rel_inform;
	private LinearLayout rel_policy;
	private LinearLayout rel_party_manage;
	private LinearLayout rel_weblearn;
	private RelativeLayout rel_clean;
	private RelativeLayout rel_disciplinary;
	private RelativeLayout rel_member;
	private RelativeLayout rel_flag;
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
	private SharedPreferences PreUserInfo;// 存储个人信息
	private int ticket = 0;
	private TextView text_warning;
	private View view = null;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		view = inflater.inflate(R.layout.wuxc_fragment_main, container, false);
		screenwidth = getActivity().getWindow().getWindowManager().getDefaultDisplay().getWidth();
		initview(view);
		initheight(view);
		ImageView image_search = (ImageView) view.findViewById(R.id.image_search);
		image_search.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(getActivity(), SearchActivity.class);
				startActivity(intent);
			}
		});
		PreUserInfo = getActivity().getSharedPreferences("UserInfo", Context.MODE_PRIVATE);
		ticket = PreUserInfo.getInt("ticket", 0);
		return view;
	}

	private void initview(View view) {
		// TODO Auto-generated method stub

		ImageGobg = (ImageView) view.findViewById(R.id.image_go_zb);
		rel_main = (RelativeLayout) view.findViewById(R.id.rel_main);
		main_top_bac = (RelativeLayout) view.findViewById(R.id.main_top_bac);
		rel_inform = (LinearLayout) view.findViewById(R.id.rel_inform);
		rel_policy = (LinearLayout) view.findViewById(R.id.rel_policy);
		rel_party_manage = (LinearLayout) view.findViewById(R.id.rel_party_manage);
		rel_weblearn = (LinearLayout) view.findViewById(R.id.rel_weblearn);

		rel_clean = (RelativeLayout) view.findViewById(R.id.rel_clean);
		rel_disciplinary = (RelativeLayout) view.findViewById(R.id.rel_disciplinary);
		rel_member = (RelativeLayout) view.findViewById(R.id.rel_member);
		rel_flag = (RelativeLayout) view.findViewById(R.id.rel_flag);
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
		text_warning = (TextView) view.findViewById(R.id.text_warning);

		rel_inform.setOnClickListener(this);
		rel_policy.setOnClickListener(this);
		rel_party_manage.setOnClickListener(this);
		rel_weblearn.setOnClickListener(this);
		rel_clean.setOnClickListener(this);
		rel_disciplinary.setOnClickListener(this);
		rel_member.setOnClickListener(this);
		rel_flag.setOnClickListener(this);
		main_top_bac.setOnClickListener(this);
		showtext();
	}

	private void showtext() {
		// TODO Auto-generated method stub
		// text_1.setVisibility(View.GONE);
		// text_2.setVisibility(View.GONE);
		// text_3.setVisibility(View.GONE);
		// text_4.setVisibility(View.GONE);
		// text_5.setVisibility(View.GONE);
		// text_6.setVisibility(View.GONE);
		// text_7.setVisibility(View.GONE);
		// text_8.setVisibility(View.GONE);
	}

	private void initheight(View view) {
		// TODO Auto-generated method stub
		int height = (int) (screenwidth / 3);
		RelativeLayout.LayoutParams LayoutParams = (android.widget.RelativeLayout.LayoutParams) ImageGobg
				.getLayoutParams();
		LayoutParams.height = height;
		ImageGobg.setLayoutParams(LayoutParams);

		RelativeLayout.LayoutParams LayoutParams1 = (android.widget.RelativeLayout.LayoutParams) rel_main
				.getLayoutParams();
		LayoutParams1.bottomMargin = height;
		rel_main.setLayoutParams(LayoutParams1);
		height = (int) (screenwidth / 2.2);
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

		height = (int) (screenwidth / 2.6);
		RelativeLayout.LayoutParams LayoutParams3 = (android.widget.RelativeLayout.LayoutParams) lin_four
				.getLayoutParams();
		LayoutParams3.height = height;
		lin_four.setLayoutParams(LayoutParams3);

		height = (int) (screenwidth / 2.6 / 2) - 8;
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
		if (view != null) {
			ticket = PreUserInfo.getInt("ticket", 0);
			if (ticket != 0) {
				headimg.setVisibility(View.GONE);
				text_warning.setVisibility(View.GONE);
			} else {
				headimg.setVisibility(View.VISIBLE);
				text_warning.setVisibility(View.VISIBLE);
			}
		}

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
		case R.id.rel_party_manage:
			Intent intent_rel_party_manage = new Intent();
			intent_rel_party_manage.setClass(getActivity(), PartyManageActivity.class);
			startActivity(intent_rel_party_manage);
			break;
		case R.id.rel_weblearn:
			Intent intent_rel_weblearn = new Intent();
			intent_rel_weblearn.setClass(getActivity(), WebLearnActivity.class);
			startActivity(intent_rel_weblearn);
			break;
		case R.id.rel_clean:
			Intent intent_rel_clean = new Intent();
			intent_rel_clean.setClass(getActivity(), CleanActivity.class);
			startActivity(intent_rel_clean);
			break;
		case R.id.rel_disciplinary:
			Intent intent_rel_disciplinary = new Intent();
			intent_rel_disciplinary.setClass(getActivity(), DisciplinaryActivity.class);
			startActivity(intent_rel_disciplinary);
			break;
		case R.id.rel_member:
			Intent intent_rel_member = new Intent();
			intent_rel_member.setClass(getActivity(), MemberActivity.class);
			startActivity(intent_rel_member);
			break;
		case R.id.rel_flag:
			Intent intent_rel_flag = new Intent();
			intent_rel_flag.setClass(getActivity(), FlagActivity.class);
			startActivity(intent_rel_flag);
			break;
		case R.id.main_top_bac:
			if (ticket == 0) {
				Intent intent_top_bac = new Intent();
				intent_top_bac.setClass(getActivity(), LoginActivity.class);
				startActivity(intent_top_bac);
			}

			break;
		default:
			break;
		}
	}

}
