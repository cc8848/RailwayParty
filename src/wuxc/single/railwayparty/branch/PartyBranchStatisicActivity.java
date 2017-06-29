package wuxc.single.railwayparty.branch;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import wuxc.single.railwayparty.R;

public class PartyBranchStatisicActivity extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.wuxc_activity_branch_statisic);
		ImageView image_back = (ImageView) findViewById(R.id.image_back);
		image_back.setOnClickListener(this);

		LinearLayout lin_1 = (LinearLayout) findViewById(R.id.lin_1);
		lin_1.setOnClickListener(this);
		LinearLayout lin_2 = (LinearLayout) findViewById(R.id.lin_2);
		lin_2.setOnClickListener(this);
		LinearLayout lin_3 = (LinearLayout) findViewById(R.id.lin_3);
		lin_3.setOnClickListener(this);
		LinearLayout lin_4 = (LinearLayout) findViewById(R.id.lin_4);
		lin_4.setOnClickListener(this);
		TextView text_1 = (TextView) findViewById(R.id.text_1);
		TextView text_2 = (TextView) findViewById(R.id.text_2);
		TextView text_3 = (TextView) findViewById(R.id.text_3);
		TextView text_4 = (TextView) findViewById(R.id.text_4);
		String time = GetCurrentTime();
		text_1.setText(time);
		text_2.setText(time);
		text_3.setText(time);
		text_4.setText(time);
	}

	public static String GetCurrentTime() {
		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String time = dateFormat.format(now);
		Calendar c = Calendar.getInstance();
		return time;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.image_back:
			finish();
			break;
		case R.id.lin_1:
			Intent intent = new Intent();
			intent.setClass(getApplicationContext(), Statisticsfornation.class);
			startActivity(intent);
			break;
		case R.id.lin_2:
			Intent intent1 = new Intent();
			intent1.setClass(getApplicationContext(), Statisticsforsex.class);
			startActivity(intent1);
			break;
		case R.id.lin_3:
			Intent intent2 = new Intent();
			intent2.setClass(getApplicationContext(), Statisticsforage.class);
			startActivity(intent2);
			break;
		case R.id.lin_4:
			Intent intent3 = new Intent();
			intent3.setClass(getApplicationContext(), Statisticsforlevel.class);
			startActivity(intent3);
			break;
		default:
			break;
		}
	}
}
