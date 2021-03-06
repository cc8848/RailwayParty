package wuxc.single.railwayparty;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.railwayparty.picfragment.p1;
import com.example.railwayparty.picfragment.p10;
import com.example.railwayparty.picfragment.p2;
import com.example.railwayparty.picfragment.p3;
import com.example.railwayparty.picfragment.p4;
import com.example.railwayparty.picfragment.p5;
import com.example.railwayparty.picfragment.p6;
import com.example.railwayparty.picfragment.p7;
import com.example.railwayparty.picfragment.p8;
import com.example.railwayparty.picfragment.p9;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import wuxc.single.railwayparty.MainFragment.MyOnPageChangeListener;
import wuxc.single.railwayparty.MainFragment.RequestTimerTask;
import wuxc.single.railwayparty.branch.PartyAssistantActivity;
import wuxc.single.railwayparty.branch.PartyBranchGroupActivity;
import wuxc.single.railwayparty.branch.PartyBranchStatisicActivity;
import wuxc.single.railwayparty.branch.PartyMembershipActivity;
import wuxc.single.railwayparty.branch.PartyMoneyActivity;
import wuxc.single.railwayparty.branch.PartyOrgActivity;
import wuxc.single.railwayparty.internet.HttpGetData;
import wuxc.single.railwayparty.layout.Childviewpaper;
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
	private String ticket;
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
	private View view;// 缓存Fragment view
	private Childviewpaper ViewPaper;
	public List<Fragment> Fragments = new ArrayList<Fragment>();
	private FragmentManager FragmentManager;
	private int NumberPicture = 0;
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
		if (null != view) {
			ViewGroup parent = (ViewGroup) view.getParent();
			if (null != parent) {
				parent.removeView(view);
			}
		} else {
			view = inflater.inflate(R.layout.wuxc_fragment_branch, container, false);
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
			NumberPicture = PreUserInfo.getInt("p_number", 0);
			if (NumberPicture > 10) {
				NumberPicture = 10;
			}
			ViewPaper = (Childviewpaper) view.findViewById(R.id.viewPager);
			Fragments.clear();// 清空list
			initfragment();// list 装填fragment
			FragmentManager = getActivity().getSupportFragmentManager();
			ViewPaper.setOffscreenPageLimit(NumberPicture);
			ViewPaper.setOnPageChangeListener(new MyOnPageChangeListener());
			ViewPaper.setAdapter(new MyPagerAdapter());
		}
		return view;
	}

	private int recLen = 0;
	Timer timer = new Timer();

	private void timego() {
		// TODO Auto-generated method stub
		timer.schedule(new RequestTimerTask(), 3000, 3000); // timeTask

	}

	class RequestTimerTask extends TimerTask {
		public void run() {
			Log.d("mainfragmnet", "timer on schedule" + recLen);
			recLen++;
			Message message = new Message();
			message.what = 1;
			handler.sendMessage(message);

		}
	}

	final Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 1:

				if (recLen < 0) {
					timer.cancel();

				}
				try {
					int cur = recLen % NumberPicture;
					ViewPaper.setCurrentItem(cur);
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}
	};

	@Override
	public void onDestroyView() {
		// TODO Auto-generated method stub
		super.onDestroyView();
		try {
			Log.e("mainfragmetn", "onDestroyView");
			timer.cancel();

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		if (view != null) {

			timer = new Timer();
			timego();
		}
	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		try {
			Log.e("mainfragmetn", "onDestroy");
			timer.cancel();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Override
	public void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		try {
			Log.e("mainfragmetn", "onDestroy");
			timer.cancel();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		try {
			Log.e("mainfragmetn", "onDestroy");
			timer.cancel();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private void initfragment() {
		// TODO Auto-generated method stub
		Fragments.add(new p1());
		Fragments.add(new p2());
		Fragments.add(new p3());
		Fragments.add(new p4());
		Fragments.add(new p5());
		Fragments.add(new p6());
		Fragments.add(new p7());
		Fragments.add(new p8());
		Fragments.add(new p9());
		Fragments.add(new p10());
	}

	private class MyPagerAdapter extends PagerAdapter {
		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

		@Override
		public int getCount() {
			return NumberPicture;
		}

		@Override
		public void destroyItem(View container, int position, Object object) {
			((ViewPager) container).removeView(Fragments.get(position).getView());
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			Fragment fragment = Fragments.get(position);
			if (!fragment.isAdded()) {
				FragmentTransaction ft = FragmentManager.beginTransaction();
				ft.add(fragment, fragment.getClass().getSimpleName());
				ft.commit();
				FragmentManager.executePendingTransactions();
			}

			if (fragment.getView().getParent() == null) {
				container.addView(fragment.getView());
			}
			return fragment.getView();
		}
	};

	public class MyOnPageChangeListener implements OnPageChangeListener {
		@Override
		public void onPageSelected(int arg0) {

		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
		}

		@Override
		public void onPageScrollStateChanged(int arg0) {
		}
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
