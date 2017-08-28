package wuxc.single.railwayparty.start;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;
import wuxc.single.railwayparty.MainActivity;
import wuxc.single.railwayparty.R;
import wuxc.single.railwayparty.internet.HttpGetData;

public class startLogoActivity extends Activity {
	private ImageView image_logo;
	private ImageView image_top;
	private LinearLayout lin_top;
	private int screenwidth = 0;
	private SharedPreferences PreGuidePage;// 用于确定是否是第一次登录
	private SharedPreferences PreAccount;// 存储用户名和密码，用于自动登录
	private SharedPreferences PreUserInfo;// 存储个人信息
	private SharedPreferences PreLogin;
	private int GuidePage = 0;// 引导页显示标志字
	private String userName = "";
	private String password;
	private static final int GET_LOGININ_RESULT_DATA = 1;
	private static final String GET_SUCCESS_RESULT = "success";
	private String userPhoto;
	private String address;
	private String ticket;
	private String loginId;
	private String sex;
	private String sessionId;
	private String username;
	private boolean autoLogin = false;
	private Handler uiHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case GET_LOGININ_RESULT_DATA:
				GetDataDetailFromLoginResultData(msg.obj);
				break;
			case 66:
				GetData(msg.obj);
				break;
			case 3:
				go();
				break;
			default:
				break;
			}
		}

	};

	protected void GetData(Object obj) {

		// TODO Auto-generated method stub
		String Type = null;

		try {
			JSONObject demoJson = new JSONObject(obj.toString());
			Type = demoJson.getString("type");

			if (Type.equals(GET_SUCCESS_RESULT)) {
				Editor edit = PreLogin.edit();
				edit.putBoolean(getTimeByCalendar() + userName, true);
				Log.e("PreLogin", "" + true);
				edit.commit();
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.wuxc_activity_startlogo);
		image_logo = (ImageView) findViewById(R.id.image_logo);
		image_top = (ImageView) findViewById(R.id.image_top);
		lin_top = (LinearLayout) findViewById(R.id.lin_top);
		screenwidth = getWindow().getWindowManager().getDefaultDisplay().getWidth();
		int width = (int) (screenwidth * 0.9);
		RelativeLayout.LayoutParams LayoutParams = (android.widget.RelativeLayout.LayoutParams) lin_top
				.getLayoutParams();
		LayoutParams.width = width;
		lin_top.setLayoutParams(LayoutParams);

		LinearLayout.LayoutParams LayoutParams1 = (android.widget.LinearLayout.LayoutParams) image_top
				.getLayoutParams();
		LayoutParams1.width = (int) (screenwidth * 0.7);
		LayoutParams1.height = (int) (width / 6.4);
		image_top.setLayoutParams(LayoutParams1);

		RelativeLayout.LayoutParams LayoutParams2 = (android.widget.RelativeLayout.LayoutParams) image_logo
				.getLayoutParams();
		LayoutParams2.height = (int) (screenwidth * 0.4);
		LayoutParams2.width = (int) (screenwidth * 0.4);
		image_logo.setLayoutParams(LayoutParams2);
		PreUserInfo = getSharedPreferences("UserInfo", Context.MODE_PRIVATE);
		PreAccount = getSharedPreferences("Account", Context.MODE_PRIVATE);
		PreGuidePage = getSharedPreferences("GuidePage", Context.MODE_PRIVATE);
		PreLogin = getSharedPreferences("Login", Context.MODE_PRIVATE);
		GuidePage = PreGuidePage.getInt("GuidePage", 0);
		starttimedelay();

	}

	private void go() {
		// TODO Auto-generated method stub
		if (GuidePage == 0) {
			Intent intent = new Intent();
			intent.setClass(this, guidePageActivity.class);
			startActivity(intent);
			finish();
		} else {
			userName = PreAccount.getString("LoginId", "");
			password = PreAccount.getString("pwd", "");
			autoLogin = PreAccount.getBoolean("autoLogin", false);
			if (userName.equals("") || password.equals("")) {
				Intent intent = new Intent();
				intent.setClass(this, MainActivity.class);
				startActivity(intent);
				finish();
			} else if (!autoLogin) {
				Intent intent = new Intent();
				intent.setClass(this, MainActivity.class);
				startActivity(intent);
				finish();
				Editor edit = PreUserInfo.edit();
				edit.clear();
				edit.commit();
			} else {

				Toast.makeText(getApplicationContext(), "自动登录", Toast.LENGTH_SHORT).show();
				final ArrayList ArrayValues = new ArrayList();
				ArrayValues.add(new BasicNameValuePair("login_id", userName));
				ArrayValues.add(new BasicNameValuePair("pwd", password));
				new Thread(new Runnable() { // 开启线程上传文件
					@Override
					public void run() {
						String LoginResultData = "";
						LoginResultData = HttpGetData.GetData("api/member/login", ArrayValues);
						Message msg = new Message();
						msg.obj = LoginResultData;
						msg.what = GET_LOGININ_RESULT_DATA;
						uiHandler.sendMessage(msg);
					}
				}).start();

			}
		}
	}

	private void starttimedelay() {
		// 原因：不延时的话list会滑到顶部
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {

			@Override
			public void run() {

				Message msg1 = new Message();
				msg1.what = 3;
				uiHandler.sendMessage(msg1);

			}

		}, 2000);
	}

	public void GetDataDetailFromLoginResultData(Object obj) {

		// TODO Auto-generated method stub
		String Type = null;
		String Data = null;
		Intent intent = new Intent();
		intent.setClass(this, MainActivity.class);
		startActivity(intent);
		finish();
		try {
			JSONObject demoJson = new JSONObject(obj.toString());
			Type = demoJson.getString("type");

			if (Type.equals(GET_SUCCESS_RESULT)) {
				Data = demoJson.getString("data");
				Toast.makeText(getApplicationContext(), "登陆成功", Toast.LENGTH_SHORT).show();
				GetDetailData(Data);
				finish();
			} else if (Type.equals("accountPwdError")) {
				Toast.makeText(getApplicationContext(), "用户名和密码不匹配", Toast.LENGTH_SHORT).show();

			} else if (Type.equals("userLocked")) {
				Toast.makeText(getApplicationContext(), "账号被禁用", Toast.LENGTH_SHORT).show();

			} else {
				Toast.makeText(getApplicationContext(), "登陆失败", Toast.LENGTH_SHORT).show();
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private void GetDetailData(String data) {
		// TODO Auto-generated method stub
		try {
			JSONObject demoJson = new JSONObject(data);

			userPhoto = demoJson.getString("userPhoto");
			address = demoJson.getString("address");
			ticket = demoJson.getString("ticket");
			loginId = demoJson.getString("loginId");
			sex = demoJson.getString("sex");
			sessionId = demoJson.getString("sessionId");
			username = demoJson.getString("username");
			WriteAccount();
			WriteUserInfo();
			Log.e("getTimeByCalendar() + userName", getTimeByCalendar() + userName);
			
			if (!PreLogin.getBoolean(getTimeByCalendar() + userName, false)) {
				final ArrayList ArrayValues = new ArrayList();
				ArrayValues.add(new BasicNameValuePair("userScoreDto.inOut", "1"));
				ArrayValues.add(new BasicNameValuePair("userScoreDto.classify", "userSign"));
				ArrayValues.add(new BasicNameValuePair("userScoreDto.amount", "2"));
				ArrayValues.add(new BasicNameValuePair("userScoreDto.reason", "每日首次登录"));
				ArrayValues.add(new BasicNameValuePair("ticket", ticket));
				new Thread(new Runnable() { // 开启线程上传文件
					@Override
					public void run() {
						String LoginResultData = "";
						LoginResultData = HttpGetData.GetData("api/console/userScore/save", ArrayValues);
						Message msg = new Message();
						msg.obj = LoginResultData;
						msg.what = 66;
						uiHandler.sendMessage(msg);
					}
				}).start();
			}
			// GetAllData();

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public String getTimeByCalendar() {
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);// 获取年份
		int month = cal.get(Calendar.MONTH);// 获取月份
		int day = cal.get(Calendar.DAY_OF_MONTH);// 获取日
		String Mon = "";
		String Day = "";
		month++;
		if (month < 10) {
			Mon = "0" + month;
		}else {
			Mon = "" + month;
		}
		if (day < 10) {
			Day = "0" + day;
		}else {
			Day=day+"";
		}
		Log.e("getTimeByCalendar", year + "-" + Mon + "-" + Day);
		return year + "-" + Mon + "-" + Day;
	}

	private void WriteUserInfo() {
		// TODO Auto-generated method stub
		Editor edit = PreUserInfo.edit();
		edit.putString("userPhoto", userPhoto);
		edit.putString("address", address);
		edit.putString("ticket", ticket);
		edit.putString("sex", sex);
		edit.putString("loginId", loginId);
		edit.putString("sessionId", sessionId);
		edit.putString("sex", sex);
		edit.commit();
	}

	private void WriteAccount() {
		// TODO Auto-generated method stub
		if (true) {
			Editor edit = PreAccount.edit();
			edit.putString("LoginId", userName);
			edit.putString("pwd", password);
			edit.commit();
			// } else {
			// Editor edit = PreAccount.edit();
			// edit.putString("LoginId", null);
			// edit.putString("pwd", null);
			// edit.commit();
		}

	}

}
