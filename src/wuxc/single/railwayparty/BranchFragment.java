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
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import wuxc.single.railwayparty.branch.PartyAssistantActivity;
import wuxc.single.railwayparty.branch.PartyBranchGroupActivity;
import wuxc.single.railwayparty.branch.PartyBranchStatisicActivity;
import wuxc.single.railwayparty.branch.PartyMembershipActivity;
import wuxc.single.railwayparty.branch.PartyMoneyActivity;
import wuxc.single.railwayparty.branch.PartyOrgActivity;
import wuxc.single.railwayparty.internet.HttpGetData;
import wuxc.single.railwayparty.other.SearchActivity;
import android.view.ViewGroup;

public class BranchFragment extends MainBaseFragment implements OnClickListener {
	private int screenwidth = 0;
	private float scale = 0;
	private float scalepx = 0;
	private float dp = 0;
	private LinearLayout lin_1;
	private LinearLayout lin_2;
	private LinearLayout lin_3;
	private LinearLayout rel_party_membership;
	private LinearLayout rel_myparty_money;
	private LinearLayout rel_assistant;
	private LinearLayout rel_org;
	private LinearLayout rel_statisic;
	private LinearLayout rel_group;
	private String ticket ;
	private String loginId;
	private String sex;
	private String sessionId;
	private String username;
	private static final int GET_LOGININ_RESULT_DATA = 1;
	private SharedPreferences PreUserInfo;// 存储个人信息
	public static String id = "0";
	public static String name = "党支部";
	public static String description = "为人民服务";
	public static String time = "2017-07-30";
	private LinearLayout lin_main_top;
	private Handler uiHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case GET_LOGININ_RESULT_DATA:
				GetDataDetailFromLoginResultData(msg.obj);
				break;
			case 3:
				// go();
				break;
			default:
				break;
			}
		}

	};

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.wuxc_fragment_branch, container, false);
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
		ReadTicket();
		final ArrayList ArrayValues = new ArrayList();
		ArrayValues.add(new BasicNameValuePair("ticket", "" + ticket));
		ArrayValues.add(new BasicNameValuePair("deptId", sessionId));
		new Thread(new Runnable() { // 开启线程上传文件
			@Override
			public void run() {
				String LoginResultData = "";
				LoginResultData = HttpGetData.GetData("api/pb/chatGroup/getByDept", ArrayValues);
				Message msg = new Message();
				msg.obj = LoginResultData;
				msg.what = GET_LOGININ_RESULT_DATA;
				uiHandler.sendMessage(msg);
			}
		}).start();
		return view;
	}

	protected void GetDataDetailFromLoginResultData(Object obj) {

		// TODO Auto-generated method stub
		String Type = null;
		String Data = null;

		try {
			JSONObject demoJson = new JSONObject(obj.toString());
			Type = demoJson.getString("type");

			if (Type.equals("success")) {
				Data = demoJson.getString("data");
				// Toast.makeText(getApplicationContext(), "登陆成功",
				// Toast.LENGTH_SHORT).show();
				demoJson = new JSONObject(Data);
				id = demoJson.getString("keyid");
				name = demoJson.getString("name");
				time = demoJson.getString("createTime");
				description = demoJson.getString("description");
				// finish();
			} else if (Type.equals("accountPwdError")) {
				// Toast.makeText(getApplicationContext(), "用户名和密码不匹配",
				// Toast.LENGTH_SHORT).show();

			} else if (Type.equals("userLocked")) {
				// Toast.makeText(getApplicationContext(), "账号被禁用",
				// Toast.LENGTH_SHORT).show();

			} else {
				// Toast.makeText(getApplicationContext(), "登陆失败",
				// Toast.LENGTH_SHORT).show();
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private void ReadTicket() {
		// TODO Auto-generated method stub
		ticket = PreUserInfo.getString("ticket", "");
		// userPhoto = PreUserInfo.getString("userPhoto", "");
		sessionId = PreUserInfo.getString("deptId", "");
	}

	private void initheight(View view) {
		// TODO Auto-generated method stub
		int rel_width = (int) ((screenwidth - 80 * scalepx) / 3);
		// Log.e("screenwidth", "" + screenwidth);
		// Log.e("rel_width", "" + rel_width);
		int rel_height = (int) (rel_width / 1.73 * 2);
		int margin = (int) ((rel_height + 6 * scalepx) * 3 / 4);
		RelativeLayout.LayoutParams LayoutParams = (android.widget.RelativeLayout.LayoutParams) lin_2.getLayoutParams();
		LayoutParams.topMargin = margin;
		lin_2.setLayoutParams(LayoutParams);

		RelativeLayout.LayoutParams LayoutParams2 = (android.widget.RelativeLayout.LayoutParams) lin_3
				.getLayoutParams();
		LayoutParams2.topMargin = margin * 2;
		lin_3.setLayoutParams(LayoutParams2);

		LinearLayout.LayoutParams LayoutParams3 = (android.widget.LinearLayout.LayoutParams) rel_party_membership
				.getLayoutParams();
		LayoutParams3.height = rel_height;
		LayoutParams3.width = rel_width;
		rel_party_membership.setLayoutParams(LayoutParams3);

		LinearLayout.LayoutParams LayoutParams4 = (android.widget.LinearLayout.LayoutParams) rel_myparty_money
				.getLayoutParams();
		LayoutParams4.height = rel_height;
		LayoutParams4.width = rel_width;
		rel_myparty_money.setLayoutParams(LayoutParams4);

		LinearLayout.LayoutParams LayoutParams5 = (android.widget.LinearLayout.LayoutParams) rel_assistant
				.getLayoutParams();
		LayoutParams5.height = rel_height;
		LayoutParams5.width = rel_width;
		rel_assistant.setLayoutParams(LayoutParams5);

		LinearLayout.LayoutParams LayoutParams6 = (android.widget.LinearLayout.LayoutParams) rel_org.getLayoutParams();
		LayoutParams6.height = rel_height;
		LayoutParams6.width = rel_width;
		rel_org.setLayoutParams(LayoutParams6);

		LinearLayout.LayoutParams LayoutParams7 = (android.widget.LinearLayout.LayoutParams) rel_statisic
				.getLayoutParams();
		LayoutParams7.height = rel_height;
		LayoutParams7.width = rel_width;
		rel_statisic.setLayoutParams(LayoutParams7);

		LinearLayout.LayoutParams LayoutParams8 = (android.widget.LinearLayout.LayoutParams) rel_group
				.getLayoutParams();
		LayoutParams8.height = rel_height;
		LayoutParams8.width = rel_width;
		rel_group.setLayoutParams(LayoutParams8);
		LinearLayout.LayoutParams LayoutParams9 = (android.widget.LinearLayout.LayoutParams) lin_main_top
				.getLayoutParams();
		LayoutParams9.height = screenwidth * 400 / 900;
		LayoutParams9.width = screenwidth;
		lin_main_top.setLayoutParams(LayoutParams9);

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
		rel_party_membership = (LinearLayout) view.findViewById(R.id.rel_party_membership);
		rel_myparty_money = (LinearLayout) view.findViewById(R.id.rel_myparty_money);
		rel_assistant = (LinearLayout) view.findViewById(R.id.rel_assistant);
		rel_org = (LinearLayout) view.findViewById(R.id.rel_org);
		rel_statisic = (LinearLayout) view.findViewById(R.id.rel_statisic);
		rel_group = (LinearLayout) view.findViewById(R.id.rel_group);
		rel_party_membership.setOnClickListener(this);
		rel_myparty_money.setOnClickListener(this);
		rel_assistant.setOnClickListener(this);
		rel_org.setOnClickListener(this);
		rel_statisic.setOnClickListener(this);
		rel_group.setOnClickListener(this);
		lin_main_top = (LinearLayout) view.findViewById(R.id.lin_main_top);
	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();

		MainActivity.curFragmentTag = getString(R.string.str_branch);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.rel_party_membership:
			Intent intent_membership = new Intent();
			intent_membership.setClass(getActivity(), PartyMembershipActivity.class);
			startActivity(intent_membership);
			break;
		case R.id.rel_myparty_money:
			Intent intent_myparty_money = new Intent();
			intent_myparty_money.setClass(getActivity(), PartyMoneyActivity.class);
			startActivity(intent_myparty_money);
			break;
		case R.id.rel_assistant:
			Intent intent_assistant = new Intent();
			intent_assistant.setClass(getActivity(), PartyAssistantActivity.class);
			startActivity(intent_assistant);
			break;
		case R.id.rel_org:
			Intent intent_org = new Intent();
			intent_org.setClass(getActivity(), PartyOrgActivity.class);
			startActivity(intent_org);
			break;
		case R.id.rel_statisic:
			Intent intent_statisic = new Intent();
			intent_statisic.setClass(getActivity(), PartyBranchStatisicActivity.class);
			startActivity(intent_statisic);
			break;
		case R.id.rel_group:
			Intent intent_group = new Intent();
			intent_group.setClass(getActivity(), PartyBranchGroupActivity.class);
			startActivity(intent_group);
			break;

		default:
			break;
		}
	}

}
