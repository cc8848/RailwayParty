package wuxc.single.railwayparty.branch;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import wuxc.single.railwayparty.R;
import wuxc.single.railwayparty.layout.RoundImageView;

public class PartyMembershipActivity extends Activity implements OnClickListener {
	private RoundImageView image_headimg;
	private TextView text_name;
	private TextView text_party_name;
	private TextView text_party_address;
	private TextView text_party_time;
	private Button btn_ok;
	private String Str_name = "李煜新";
	private String Str_party_name = "中铁一局第一党支部";
	private String Str_party_address = "陕西省西安市雁塔北路1号";
	private String Str_party_time = "2017年5月";
	private SharedPreferences PreUserInfo;// 存储个人信息

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.wuxc_activity_party_membership);
		ImageView image_back = (ImageView) findViewById(R.id.image_back);
		image_back.setOnClickListener(this);
		image_headimg = (RoundImageView) findViewById(R.id.image_headimg);
		text_name = (TextView) findViewById(R.id.text_name);
		text_party_name = (TextView) findViewById(R.id.text_party_name);
		text_party_address = (TextView) findViewById(R.id.text_party_address);
		text_party_time = (TextView) findViewById(R.id.text_party_time);
		btn_ok = (Button) findViewById(R.id.btn_ok);
		btn_ok.setOnClickListener(this);
		PreUserInfo = getSharedPreferences("UserInfo", Context.MODE_PRIVATE);
		ReadTicket();
		settext();
	}

	private void ReadTicket() {
		// TODO Auto-generated method stub
		Str_name = PreUserInfo.getString("userName", "");
		Str_party_name = PreUserInfo.getString("deptName", "");
		Str_party_address = PreUserInfo.getString("address", "");
		Str_party_time = PreUserInfo.getString("pInTime", "");
	}

	private void settext() {
		// TODO Auto-generated method stub
		text_name.setText(Str_name);
		text_party_name.setText("党组织名称：" + Str_party_name);
		text_party_address.setText("党组织地址：" + Str_party_address);
		text_party_time.setText("转入本组织时间：" + Str_party_time);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.image_back:
			finish();
			break;
		case R.id.btn_ok:
			Intent intent_membership = new Intent();
			intent_membership.setClass(getApplicationContext(), PartyMembershipTransActivity.class);
			startActivity(intent_membership);
			break;
		default:
			break;
		}
	}
}
