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

public class AdviceActivity extends Activity implements OnClickListener {
	private EditText edit_advice;
	private Button btn_ok;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.wuxc_activity_advice);
		ImageView image_back = (ImageView) findViewById(R.id.image_back);
		image_back.setOnClickListener(this);
		edit_advice = (EditText) findViewById(R.id.edit_advice);
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

			String advice = edit_advice.getText().toString();
			if (advice.equals("") || advice == null) {
				Toast.makeText(getApplicationContext(), "请输入内容", Toast.LENGTH_SHORT).show();

			} else {
				Toast.makeText(getApplicationContext(), "正在提交", Toast.LENGTH_SHORT).show();

			}
			break;
		default:
			break;
		}
	}
}
