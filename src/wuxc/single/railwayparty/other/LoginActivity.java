package wuxc.single.railwayparty.other;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import wuxc.single.railwayparty.R;

public class LoginActivity extends Activity implements OnClickListener {
	private EditText edit_username;
	private EditText edit_password;
	private Button btn_ok;
	private LinearLayout lin_remeber;
	private LinearLayout lin_forget;
	private ImageView image_selected;
	private String userName = "";
	private String password = "";
	private boolean remeber = true;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.wuxc_activity_login);
		ImageView image_back = (ImageView) findViewById(R.id.image_back);
		image_back.setOnClickListener(this);
		edit_username = (EditText) findViewById(R.id.edit_username);
		edit_password = (EditText) findViewById(R.id.edit_password);
		btn_ok = (Button) findViewById(R.id.btn_ok);
		lin_remeber = (LinearLayout) findViewById(R.id.lin_remeber);
		lin_forget = (LinearLayout) findViewById(R.id.lin_forget);
		image_selected = (ImageView) findViewById(R.id.image_selected);
		btn_ok.setOnClickListener(this);
		lin_remeber.setOnClickListener(this);
		lin_forget.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.image_back:
			finish();
			break;
		case R.id.btn_ok:
			userName = edit_username.getText().toString();
			password = edit_password.getText().toString();
			if (userName.equals("") || userName == null) {
				Toast.makeText(getApplicationContext(), "请输入用户名", Toast.LENGTH_SHORT).show();
			} else if (password.equals("") || password == null) {
				Toast.makeText(getApplicationContext(), "请输入密码", Toast.LENGTH_SHORT).show();
			} else {
				Toast.makeText(getApplicationContext(), "正在登陆", Toast.LENGTH_SHORT).show();
			}
			break;
		case R.id.lin_remeber:
			remeber = !remeber;
			if (remeber) {
				image_selected.setImageResource(R.drawable.noselected);
			} else {
				image_selected.setImageResource(R.drawable.selected);
			}
			break;
		case R.id.lin_forget:
			Intent intent = new Intent();
			intent.setClass(getApplicationContext(), ResetPwdActivity.class);
			startActivity(intent);
			break;

		default:
			break;
		}
	}

}
