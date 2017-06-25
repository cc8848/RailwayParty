package wuxc.single.railwayparty.my;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import wuxc.single.railwayparty.R;

public class MyResumeActivity extends Activity implements OnClickListener {
	private String Str_text_name = "李超";
	private String Str_text_sex = "男";
	private String Str_text_id_number = "622331196011146661";
	private String Str_text_motto = "点击输入您的宣言";
	private String Str_text_phone = "点击绑定电话号码";
	private String Str_text_level = "本科";
	private String Str_text_party_time = "2014-10-01";
	private String Str_text_party_age = "1年";
	private String Str_text_branch = "五公司第一党支部";
	private String Str_text_statue = "政工干部";
	private String Str_text_partymoney_endtime = "2017-10-09";
	private TextView text_ok;
	private TextView text_name;
	private TextView text_sex;
	private TextView text_id_number;
	private TextView text_motto;
	private TextView text_phone;
	private TextView text_level;
	private TextView text_party_time;
	private TextView text_party_age;
	private TextView text_branch;
	private TextView text_statue;
	private TextView text_partymoney_endtime;
	private SharedPreferences PreUserInfo;// 存储个人信息

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.wuxc_activity_myresume);
		PreUserInfo = getSharedPreferences("UserInfo", Context.MODE_PRIVATE);

		ImageView image_back = (ImageView) findViewById(R.id.image_back);
		image_back.setOnClickListener(this);
		text_ok = (TextView) findViewById(R.id.text_ok);
		text_ok.setOnClickListener(this);
		text_name = (TextView) findViewById(R.id.text_name);
		text_sex = (TextView) findViewById(R.id.text_sex);
		text_id_number = (TextView) findViewById(R.id.text_id_number);
		text_motto = (TextView) findViewById(R.id.text_motto);
		text_phone = (TextView) findViewById(R.id.text_phone);
		text_level = (TextView) findViewById(R.id.text_level);
		text_party_time = (TextView) findViewById(R.id.text_party_time);
		text_party_age = (TextView) findViewById(R.id.text_party_age);
		text_branch = (TextView) findViewById(R.id.text_branch);
		text_statue = (TextView) findViewById(R.id.text_statue);
		text_partymoney_endtime = (TextView) findViewById(R.id.text_partymoney_endtime);
		text_motto.setOnClickListener(this);
		text_phone.setOnClickListener(this);
		settext();
	}

	private void settext() {
		// TODO Auto-generated method stub
		text_name.setText(Str_text_name);
		text_sex.setText(Str_text_sex);
		text_id_number.setText(Str_text_id_number);
		text_motto.setText(Str_text_motto);
		text_phone.setText(Str_text_phone);
		text_level.setText(Str_text_level);
		text_party_time.setText(Str_text_party_time);
		text_party_age.setText(Str_text_party_age);
		text_branch.setText(Str_text_branch);
		text_statue.setText(Str_text_statue);
		text_partymoney_endtime.setText(Str_text_partymoney_endtime);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (data == null)
			return;
		Bundle bundle = data.getExtras();
		switch (requestCode) {

		case 1:
			if (!(data == null)) {
				ReadSign();
				settext();
			}

			break;
		case 2:
			if (!(data == null)) {
				ReadPhone();
				settext();
			}

			break;
		default:
			break;

		}
		super.onActivityResult(requestCode, resultCode, data);
	}

	private void ReadSign() {
		// TODO Auto-generated method stub
		Str_text_motto = PreUserInfo.getString("Sign", "");

	}

	private void ReadPhone() {
		// TODO Auto-generated method stub

		Str_text_phone = PreUserInfo.getString("PhoneNumber", "");
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.image_back:
			finish();
			break;
		case R.id.text_motto:
			Intent intent_sign = new Intent();
			intent_sign.setClass(getApplicationContext(), mottoActivity.class);
			startActivityForResult(intent_sign, 1);
			break;
		case R.id.text_phone:
			Intent intent_phone = new Intent();
			intent_phone.setClass(getApplicationContext(), phoneActivity.class);
			startActivityForResult(intent_phone, 2);
			break;
		default:
			break;
		}
	}
}
