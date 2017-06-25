package wuxc.single.railwayparty.my;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import wuxc.single.railwayparty.R;

public class NewpwdActivity extends Activity implements OnClickListener {
	private EditText edit_old;
	private EditText edit_new;
	private EditText edit_new1;
	private Button btn_ok;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.wuxc_activity_newpwd);
		ImageView image_back = (ImageView) findViewById(R.id.image_back);
		image_back.setOnClickListener(this);
		edit_old = (EditText) findViewById(R.id.edit_old);
		edit_new = (EditText) findViewById(R.id.edit_new);
		edit_new1 = (EditText) findViewById(R.id.edit_new1);
		btn_ok = (Button) findViewById(R.id.btn_ok);
		btn_ok.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.image_back:
			finish();
			break;
		case R.id.btn_ok:
			String old = edit_old.getText().toString();
			String newpwd = edit_new.getText().toString();
			String newpwd1 = edit_new1.getText().toString();
			if (old.equals("") || old == null) {
				Toast.makeText(getApplicationContext(), "请输入旧密码", Toast.LENGTH_SHORT).show();
			} else if (newpwd.equals("") || newpwd == null) {
				Toast.makeText(getApplicationContext(), "请输入新密码", Toast.LENGTH_SHORT).show();

			} else if (newpwd1.equals("") || newpwd1 == null) {
				Toast.makeText(getApplicationContext(), "请确认新密码", Toast.LENGTH_SHORT).show();

			} else if (newpwd1.equals(newpwd)) {
				Toast.makeText(getApplicationContext(), "两次输入不一致", Toast.LENGTH_SHORT).show();

			} else {
				Toast.makeText(getApplicationContext(), "正在修改", Toast.LENGTH_SHORT).show();

			}
			break;
		default:
			break;
		}
	}
}
