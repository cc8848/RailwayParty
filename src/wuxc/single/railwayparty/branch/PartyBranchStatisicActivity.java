package wuxc.single.railwayparty.branch;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import wuxc.single.railwayparty.R;
import wuxc.single.railwayparty.internet.HttpGetData;

public class PartyBranchStatisicActivity extends Activity implements OnClickListener {
	private int ticket = 0;

	private SharedPreferences PreUserInfo;// 存储个人信息
	public Handler uiHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 1:
				if (true) {
					Intent intent = new Intent();
					intent.setClass(getApplicationContext(), Statisticsfornation.class);
					Bundle bundle = new Bundle();
					bundle.putString("data", msg.obj.toString());
					intent.putExtras(bundle);
					startActivity(intent);
				}
				break;
			case 2:

				if (true) {
					Intent intent = new Intent();
					intent.setClass(getApplicationContext(), Statisticsforsex.class);
					Bundle bundle = new Bundle();
					bundle.putString("data", msg.obj.toString());
					intent.putExtras(bundle);
					startActivity(intent);
				}
				break;
			case 3:

				if (true) {
					Intent intent = new Intent();
					intent.setClass(getApplicationContext(), Statisticsforage.class);
					Bundle bundle = new Bundle();
					bundle.putString("data", msg.obj.toString());
					intent.putExtras(bundle);
					startActivity(intent);
				}
				break;
			case 4:

				if (true) {
					Intent intent = new Intent();
					intent.setClass(getApplicationContext(), Statisticsforlevel.class);
					Bundle bundle = new Bundle();
					bundle.putString("data", msg.obj.toString());
					intent.putExtras(bundle);
					startActivity(intent);
				}
				break;
			default:
				break;
			}
		}
	};

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
		PreUserInfo = getSharedPreferences("UserInfo", Context.MODE_PRIVATE);
		ticket = PreUserInfo.getInt("ticket", 0);
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
			if (true) {

				final ArrayList ArrayValues = new ArrayList();
				// ArrayValues.add(new BasicNameValuePair("ticket", ticket));
				// ArrayValues.add(new BasicNameValuePair("applyType", "" + 2));
				// ArrayValues.add(new BasicNameValuePair("helpSType", "" +
				// type));
				// ArrayValues.add(new BasicNameValuePair("modelSign",
				// "KNDY_APPLY"));
				// ArrayValues.add(new BasicNameValuePair("curPage", "" +
				// curPage));
				// ArrayValues.add(new BasicNameValuePair("pageSize", "" +
				// pageSize));
				// final ArrayList ArrayValues = new ArrayList();
				ArrayValues.add(new BasicNameValuePair("ticket", "" + ticket));
				// chn = GetChannelByKey.GetSign(PreALLChannel, "职工之家");
				// ArrayValues.add(new BasicNameValuePair("chn", "dyq"));
				// chn = "dyq";
				// ArrayValues.add(new BasicNameValuePair("curPage", "" +
				// curPage));
				// ArrayValues.add(new BasicNameValuePair("pageSize", "" +
				// pageSize));
				// ArrayValues.add(new BasicNameValuePair("classify", "" +
				// classify));

				new Thread(new Runnable() { // 开启线程上传文件
					@Override
					public void run() {
						String DueData = "";
						DueData = HttpGetData.GetData("api/pb/statics/getNation", ArrayValues);
						Message msg = new Message();
						msg.obj = DueData;
						msg.what = 1;
						uiHandler.sendMessage(msg);
					}
				}).start();
			}
			break;
		case R.id.lin_2:
			if (true) {

				final ArrayList ArrayValues = new ArrayList();
				// ArrayValues.add(new BasicNameValuePair("ticket", ticket));
				// ArrayValues.add(new BasicNameValuePair("applyType", "" + 2));
				// ArrayValues.add(new BasicNameValuePair("helpSType", "" +
				// type));
				// ArrayValues.add(new BasicNameValuePair("modelSign",
				// "KNDY_APPLY"));
				// ArrayValues.add(new BasicNameValuePair("curPage", "" +
				// curPage));
				// ArrayValues.add(new BasicNameValuePair("pageSize", "" +
				// pageSize));
				// final ArrayList ArrayValues = new ArrayList();
				ArrayValues.add(new BasicNameValuePair("ticket", "" + ticket));
				// chn = GetChannelByKey.GetSign(PreALLChannel, "职工之家");
				// ArrayValues.add(new BasicNameValuePair("chn", "dyq"));
				// chn = "dyq";
				// ArrayValues.add(new BasicNameValuePair("curPage", "" +
				// curPage));
				// ArrayValues.add(new BasicNameValuePair("pageSize", "" +
				// pageSize));
				// ArrayValues.add(new BasicNameValuePair("classify", "" +
				// classify));

				new Thread(new Runnable() { // 开启线程上传文件
					@Override
					public void run() {
						String DueData = "";
						DueData = HttpGetData.GetData("api/pb/statics/getSex", ArrayValues);
						Message msg = new Message();
						msg.obj = DueData;
						msg.what = 2;
						uiHandler.sendMessage(msg);
					}
				}).start();
			}

			break;
		case R.id.lin_3:
			if (true) {

				final ArrayList ArrayValues = new ArrayList();
				// ArrayValues.add(new BasicNameValuePair("ticket", ticket));
				// ArrayValues.add(new BasicNameValuePair("applyType", "" + 2));
				// ArrayValues.add(new BasicNameValuePair("helpSType", "" +
				// type));
				// ArrayValues.add(new BasicNameValuePair("modelSign",
				// "KNDY_APPLY"));
				// ArrayValues.add(new BasicNameValuePair("curPage", "" +
				// curPage));
				// ArrayValues.add(new BasicNameValuePair("pageSize", "" +
				// pageSize));
				// final ArrayList ArrayValues = new ArrayList();
				ArrayValues.add(new BasicNameValuePair("ticket", "" + ticket));
				// chn = GetChannelByKey.GetSign(PreALLChannel, "职工之家");
				// ArrayValues.add(new BasicNameValuePair("chn", "dyq"));
				// chn = "dyq";
				// ArrayValues.add(new BasicNameValuePair("curPage", "" +
				// curPage));
				// ArrayValues.add(new BasicNameValuePair("pageSize", "" +
				// pageSize));
				// ArrayValues.add(new BasicNameValuePair("classify", "" +
				// classify));

				new Thread(new Runnable() { // 开启线程上传文件
					@Override
					public void run() {
						String DueData = "";
						DueData = HttpGetData.GetData("api/pb/statics/getAge", ArrayValues);
						Message msg = new Message();
						msg.obj = DueData;
						msg.what = 3;
						uiHandler.sendMessage(msg);
					}
				}).start();
			}

			break;
		case R.id.lin_4:
			if (true) {

				final ArrayList ArrayValues = new ArrayList();
				// ArrayValues.add(new BasicNameValuePair("ticket", ticket));
				// ArrayValues.add(new BasicNameValuePair("applyType", "" + 2));
				// ArrayValues.add(new BasicNameValuePair("helpSType", "" +
				// type));
				// ArrayValues.add(new BasicNameValuePair("modelSign",
				// "KNDY_APPLY"));
				// ArrayValues.add(new BasicNameValuePair("curPage", "" +
				// curPage));
				// ArrayValues.add(new BasicNameValuePair("pageSize", "" +
				// pageSize));
				// final ArrayList ArrayValues = new ArrayList();
				ArrayValues.add(new BasicNameValuePair("ticket", "" + ticket));
				// chn = GetChannelByKey.GetSign(PreALLChannel, "职工之家");
				// ArrayValues.add(new BasicNameValuePair("chn", "dyq"));
				// chn = "dyq";
				// ArrayValues.add(new BasicNameValuePair("curPage", "" +
				// curPage));
				// ArrayValues.add(new BasicNameValuePair("pageSize", "" +
				// pageSize));
				// ArrayValues.add(new BasicNameValuePair("classify", "" +
				// classify));

				new Thread(new Runnable() { // 开启线程上传文件
					@Override
					public void run() {
						String DueData = "";
						DueData = HttpGetData.GetData("api/pb/statics/getEducation", ArrayValues);
						Message msg = new Message();
						msg.obj = DueData;
						msg.what = 4;
						uiHandler.sendMessage(msg);
					}
				}).start();
			}

			break;
		default:
			break;
		}
	}
}
