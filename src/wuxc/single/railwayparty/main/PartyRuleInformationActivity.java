package wuxc.single.railwayparty.main;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import wuxc.single.railwayparty.R;

public class PartyRuleInformationActivity extends Activity implements OnClickListener {
	private LinearLayout lin_a;
	private ImageView image_a;
	private Button btn_last;
	private Button btn_next;
	private boolean agree = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.wuxc_activity_party_rule_inform);
		ImageView image_back = (ImageView) findViewById(R.id.image_back);
		image_back.setOnClickListener(this);
		lin_a = (LinearLayout) findViewById(R.id.lin_a);
		image_a = (ImageView) findViewById(R.id.image_a);
		btn_last = (Button) findViewById(R.id.btn_last);
		btn_next = (Button) findViewById(R.id.btn_next);
		lin_a.setOnClickListener(this);
		image_a.setOnClickListener(this);
		btn_last.setOnClickListener(this);
		btn_next.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.image_back:
			finish();
			break;
		case R.id.lin_a:
			agree = !agree;
			if (agree) {
				image_a.setImageResource(R.drawable.icon_radio);
			} else {
				image_a.setImageResource(R.drawable.icon_radio1);
			}
			break;
		case R.id.btn_last:
			if (agree) {
				Intent intent = new Intent();
				intent.setClass(getApplicationContext(), ReportInnameActivity.class);
				startActivity(intent);
				finish();
			} else {
				Toast.makeText(getApplicationContext(), "请同意以上条款", Toast.LENGTH_SHORT).show();
			}
			break;
		case R.id.btn_next:
			if (agree) {
				Intent intent = new Intent();
				intent.setClass(getApplicationContext(), ReportOutnameActivity.class);
				startActivity(intent);
				finish();
			} else {
				Toast.makeText(getApplicationContext(), "请同意以上条款", Toast.LENGTH_SHORT).show();
			}
			break;
		default:
			break;
		}
	}

}
