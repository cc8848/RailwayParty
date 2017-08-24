package wuxc.single.railwayparty;

import java.util.ArrayList;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import wuxc.single.railwayparty.internet.HttpGetData;
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
	private String ticket = "";
	private TextView text_warning;
	private View view = null;
	private static final int GET_VERSION_RESULT = 5;
	private int t1 = 0;
	private int t2 = 0;
	private int t3 = 0;
	private int t4 = 0;
	private int t5 = 0;
	private int t6 = 0;
	private int t7 = 0;
	private int t8 = 0;
	private Handler uiHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {

			case GET_VERSION_RESULT:
				GetDataDetailFromVersion(msg.obj);
				break;
			default:
				break;
			}
		}
	};

	protected void GetDataDetailFromVersion(Object obj) {
		// TODO Auto-generated method stub

		// TODO Auto-generated method stub
		String data = null;

		try {
			JSONObject demoJson = new JSONObject(obj.toString());

			data = demoJson.getString("data");
			demoJson = new JSONObject(data);
			t1 = demoJson.getInt("tzgg");
			t2 = demoJson.getInt("lxyzxx");
			t3 = demoJson.getInt("djkh");
			t4 = demoJson.getInt("wsdx");
			t5 = demoJson.getInt("lzsx");
			t6 = demoJson.getInt("xxtb");
			t7 = demoJson.getInt("jszn");
			t8 = demoJson.getInt("qnxf");
			intnumber();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	private void intnumber() {
		// TODO Auto-generated method stub
		text_1.setVisibility(View.GONE);
		text_2.setVisibility(View.GONE);
		text_3.setVisibility(View.GONE);
		text_4.setVisibility(View.GONE);
		text_5.setVisibility(View.GONE);
		text_6.setVisibility(View.GONE);
		text_7.setVisibility(View.GONE);
		text_8.setVisibility(View.GONE);
		if (t1 != 0) {
			text_1.setVisibility(View.VISIBLE);
			text_1.setText("" + t1);
		}
		if (t2 != 0) {
			text_2.setVisibility(View.VISIBLE);
			text_2.setText("" + t2);
		}
		if (t3 != 0) {
			text_3.setVisibility(View.VISIBLE);
			text_3.setText("" + t3);
		}
		if (t4 != 0) {
			text_4.setVisibility(View.VISIBLE);
			text_4.setText("" + t4);
		}
		if (t5 != 0) {
			text_5.setVisibility(View.VISIBLE);
			text_5.setText("" + t5);
		}
		if (t6 != 0) {
			text_6.setVisibility(View.VISIBLE);
			text_6.setText("" + t6);
		}
		if (t7 != 0) {
			text_7.setVisibility(View.VISIBLE);
			text_7.setText("" + t7);
		}
		if (t8 != 0) {
			text_8.setVisibility(View.VISIBLE);
			text_8.setText("" + t8);
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		if (null != view) {
			ViewGroup parent = (ViewGroup) view.getParent();
			if (null != parent) {
				parent.removeView(view);
			}
		} else {
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
			ticket = PreUserInfo.getString("ticket", "");
			final ArrayList ArrayValues = new ArrayList();
			ArrayValues.add(new BasicNameValuePair("ticket", "" + ticket));
			ArrayValues.add(new BasicNameValuePair("chns", "tzgg,lxyzxx,djkh,wsdx,lzsx,xxtb,jszn,qnxf"));
			new Thread(new Runnable() { // 开启线程上传文件
				@Override
				public void run() {
					String LoginResultData = "";
					LoginResultData = HttpGetData.GetData("api/cms/accessRecord/getUnReadStatics", ArrayValues);
					Message msg = new Message();
					msg.obj = LoginResultData;
					msg.what = GET_VERSION_RESULT;
					uiHandler.sendMessage(msg);
				}
			}).start();
		}
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
		int height = (int) (screenwidth / 3);
		RelativeLayout.LayoutParams LayoutParams = (android.widget.RelativeLayout.LayoutParams) ImageGobg
				.getLayoutParams();
		LayoutParams.height = height;
		ImageGobg.setLayoutParams(LayoutParams);

		RelativeLayout.LayoutParams LayoutParams1 = (android.widget.RelativeLayout.LayoutParams) rel_main
				.getLayoutParams();
		LayoutParams1.bottomMargin = height;
		rel_main.setLayoutParams(LayoutParams1);
		height = (int) (screenwidth * 338 / 750);
		RelativeLayout.LayoutParams LayoutParams2 = (android.widget.RelativeLayout.LayoutParams) main_top_bac
				.getLayoutParams();
		LayoutParams2.height = height;
		main_top_bac.setLayoutParams(LayoutParams2);
		RelativeLayout.LayoutParams LayoutParams9 = (android.widget.RelativeLayout.LayoutParams) headimg
				.getLayoutParams();
		LayoutParams9.height = (int) (height / 2.5);
		LayoutParams9.width = (int) (height / 2.5);
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
			ticket = PreUserInfo.getString("ticket", "");
			if (!ticket.equals("")) {
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
			if (ticket.equals("")) {
				Intent intent_top_bac = new Intent();
				intent_top_bac.setClass(getActivity(), LoginActivity.class);
				startActivity(intent_top_bac);
				t1 = 0;
				intnumber();
				break;
			}
			Intent intent_inform = new Intent();
			intent_inform.setClass(getActivity(), InformActivity.class);
			startActivity(intent_inform);
			t1 = 0;
			intnumber();
			break;
		case R.id.rel_policy:
			if (ticket.equals("")) {
				Intent intent_top_bac = new Intent();
				intent_top_bac.setClass(getActivity(), LoginActivity.class);
				startActivity(intent_top_bac);
				t2 = 0;
				intnumber();
				break;
			}
			Intent intent_rel_policy = new Intent();
			intent_rel_policy.setClass(getActivity(), PolicyActivity.class);
			startActivity(intent_rel_policy);
			t2 = 0;
			intnumber();
			break;
		case R.id.rel_party_manage:
			if (ticket.equals("")) {
				Intent intent_top_bac = new Intent();
				intent_top_bac.setClass(getActivity(), LoginActivity.class);
				startActivity(intent_top_bac);
				t3 = 0;
				intnumber();
				break;
			}
			Intent intent_rel_party_manage = new Intent();
			intent_rel_party_manage.setClass(getActivity(), PartyManageActivity.class);
			startActivity(intent_rel_party_manage);
			t3 = 0;
			intnumber();
			break;
		case R.id.rel_weblearn:
			t4 = 0;
			intnumber();
			if (ticket.equals("")) {
				Intent intent_top_bac = new Intent();
				intent_top_bac.setClass(getActivity(), LoginActivity.class);
				startActivity(intent_top_bac);
				break;
			}
			Intent intent_rel_weblearn = new Intent();
			intent_rel_weblearn.setClass(getActivity(), WebLearnActivity.class);
			startActivity(intent_rel_weblearn);
			break;
		case R.id.rel_clean:
			t5 = 0;
			intnumber();
			if (ticket.equals("")) {
				Intent intent_top_bac = new Intent();
				intent_top_bac.setClass(getActivity(), LoginActivity.class);
				startActivity(intent_top_bac);
				break;
			}
			Intent intent_rel_clean = new Intent();
			intent_rel_clean.setClass(getActivity(), CleanActivity.class);
			startActivity(intent_rel_clean);
			break;
		case R.id.rel_disciplinary:
			t6 = 0;
			intnumber();
			if (ticket.equals("")) {
				Intent intent_top_bac = new Intent();
				intent_top_bac.setClass(getActivity(), LoginActivity.class);
				startActivity(intent_top_bac);
				break;
			}
			Intent intent_rel_disciplinary = new Intent();
			intent_rel_disciplinary.setClass(getActivity(), DisciplinaryActivity.class);
			startActivity(intent_rel_disciplinary);
			break;
		case R.id.rel_member:
			t7 = 0;
			intnumber();
			if (ticket.equals("")) {
				Intent intent_top_bac = new Intent();
				intent_top_bac.setClass(getActivity(), LoginActivity.class);
				startActivity(intent_top_bac);
				break;
			}
			Intent intent_rel_member = new Intent();
			intent_rel_member.setClass(getActivity(), MemberActivity.class);
			startActivity(intent_rel_member);
			break;
		case R.id.rel_flag:
			t8 = 0;
			intnumber();
			if (ticket.equals("")) {
				Intent intent_top_bac = new Intent();
				intent_top_bac.setClass(getActivity(), LoginActivity.class);
				startActivity(intent_top_bac);
				break;
			}
			Intent intent_rel_flag = new Intent();
			intent_rel_flag.setClass(getActivity(), FlagActivity.class);
			startActivity(intent_rel_flag);
			break;
		case R.id.main_top_bac:
			if (ticket.equals("")) {
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
