package wuxc.single.railwayparty.my;

import java.util.ArrayList;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import wuxc.single.railwayparty.MainActivity;
import wuxc.single.railwayparty.R;
import wuxc.single.railwayparty.internet.APPVersion;
import wuxc.single.railwayparty.internet.HttpGetData;
import wuxc.single.railwayparty.internet.URLcontainer;
import wuxc.single.railwayparty.layout.dialogtwo;
import wuxc.single.railwayparty.other.LoginActivity;
import wuxc.single.railwayparty.other.LoginActivity2;

public class SettingActivity extends Activity implements OnClickListener {
	private LinearLayout lin_newpwd;
	private LinearLayout lin_message;
	private LinearLayout lin_about;
	private LinearLayout lin_version;
	private LinearLayout lin_myinfo;
	private LinearLayout lin_advice;
	private Button btn_exit;
	private boolean IsExit = true;
	private MainActivity MainActivity;
	private String ticket="";
	private SharedPreferences PreUserInfo;// 存储个人信息
	private static final int GET_VERSION_RESULT = 1;

	private LinearLayout lin_changepassword;

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
		String versionId = null;
		String versionNum = null;
		String versionPath = null;

		try {
			JSONObject demoJson = new JSONObject(obj.toString());
			versionId = demoJson.getString("versionId");
			versionNum = demoJson.getString("versionNum");
			versionPath = demoJson.getString("versionPath");
			if (versionId.equals(APPVersion.APPVersion)) {
				Toast.makeText(getApplicationContext(), "已是最新版本", Toast.LENGTH_SHORT).show();
			} else {
				showAlertDialog(versionNum, versionPath);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public void showAlertDialog(String versionNum, final String versionPath) {

		dialogtwo.Builder builder = new dialogtwo.Builder(this);
		builder.setMessage("是否更新新版本？\n" + "版本号：" + versionNum);
		builder.setTitle("版本更新");
		builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
				Intent intent = new Intent();
				intent.setAction("android.intent.action.VIEW");
				String path = URLcontainer.urlip + URLcontainer.GetFile + versionPath;
				Uri content_url = Uri.parse(path);
				intent.setData(content_url);
				startActivity(intent);

			}
		});

		builder.setNegativeButton("取消", new android.content.DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});

		builder.create().show();

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.wuxc_activity_setting);
		ImageView image_back = (ImageView) findViewById(R.id.image_back);
		image_back.setOnClickListener(this);
		lin_newpwd = (LinearLayout) findViewById(R.id.lin_newpwd);
		lin_message = (LinearLayout) findViewById(R.id.lin_message);
		lin_about = (LinearLayout) findViewById(R.id.lin_about);
		lin_version = (LinearLayout) findViewById(R.id.lin_version);
		lin_myinfo = (LinearLayout) findViewById(R.id.lin_myinfo);
		lin_advice = (LinearLayout) findViewById(R.id.lin_advice);
		lin_newpwd.setOnClickListener(this);
		lin_message.setOnClickListener(this);
		lin_about.setOnClickListener(this);
		lin_version.setOnClickListener(this);
		lin_myinfo.setOnClickListener(this);
		lin_advice.setOnClickListener(this);
		btn_exit = (Button) findViewById(R.id.btn_exit);
		btn_exit.setOnClickListener(this);
		PreUserInfo = getSharedPreferences("UserInfo", Context.MODE_PRIVATE);
		ticket = PreUserInfo.getString("ticket", "");
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.image_back:
			finish();
			break;
		case R.id.lin_newpwd:
			Intent intent_newpwd = new Intent();
			intent_newpwd.setClass(getApplicationContext(), NewpwdActivity.class);
			startActivity(intent_newpwd);
			break;
		case R.id.lin_message:
			Intent intent_message = new Intent();
			intent_message.setClass(getApplicationContext(), MessageActivity.class);
			startActivity(intent_message);
			break;
		case R.id.lin_about:
			Intent intent_about = new Intent();
			intent_about.setClass(getApplicationContext(), AboutActivity.class);
			startActivity(intent_about);
			break;
		case R.id.lin_version:
			// Toast.makeText(getApplicationContext(), "已是最新版本",
			// Toast.LENGTH_SHORT).show();
			final ArrayList ArrayValues = new ArrayList();
			ArrayValues.add(new BasicNameValuePair("ticket", "" + ticket));
			new Thread(new Runnable() { // 开启线程上传文件
				@Override
				public void run() {
					String LoginResultData = "";
					LoginResultData = HttpGetData.GetData("api/pubshare/sysVersion/getLatestVersion", ArrayValues);
					Message msg = new Message();
					msg.obj = LoginResultData;
					msg.what = GET_VERSION_RESULT;
					uiHandler.sendMessage(msg);
				}
			}).start();
			// IsGetNewVersion = false;
			break;
		case R.id.lin_myinfo:
			Intent intent_myinfo = new Intent();
			intent_myinfo.setClass(getApplicationContext(), MyResumeActivity.class);
			startActivity(intent_myinfo);
			break;
		case R.id.lin_advice:
			Intent intent_advice = new Intent();
			intent_advice.setClass(getApplicationContext(), AdviceActivity.class);
			startActivity(intent_advice);
			break;
		case R.id.btn_exit:
			if (IsExit) {
				IsExit = false;
				Intent intent_test = new Intent();
				intent_test.setClass(getApplicationContext(), LoginActivity2.class);
				startActivity(intent_test);
				finish();
				MainActivity.activity.finish();
				// WriteAccount();
			}
			break;
		default:
			break;
		}
	}
}
