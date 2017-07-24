package wuxc.single.railwayparty.my;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import wuxc.single.railwayparty.R;
import wuxc.single.railwayparty.other.LoginActivity;

public class SettingActivity extends Activity implements OnClickListener {
	private LinearLayout lin_newpwd;
	private LinearLayout lin_message;
	private LinearLayout lin_about;
	private LinearLayout lin_version;
	private LinearLayout lin_myinfo;
	private LinearLayout lin_advice;
	private Button btn_exit;

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
			Toast.makeText(getApplicationContext(), "已是最新版本", Toast.LENGTH_SHORT).show();

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
			Intent intent_top_bac = new Intent();
			intent_top_bac.setClass(getApplicationContext(), LoginActivity.class);
			startActivity(intent_top_bac);
			break;
		default:
			break;
		}
	}
}
