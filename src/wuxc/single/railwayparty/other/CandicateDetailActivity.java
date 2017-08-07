package wuxc.single.railwayparty.other;

import java.util.ArrayList;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import wuxc.single.railwayparty.R;
import wuxc.single.railwayparty.internet.HttpGetData;
import wuxc.single.railwayparty.layout.RoundImageView;

public class CandicateDetailActivity extends Activity implements OnClickListener {
	private RoundImageView RoundHeadimg;
	private ImageView ImageBack;
	private ImageView ImageSymbol;
	private ImageView ImageSelected;
	private TextView TextName;
	private TextView TextPartyMember;
	private TextView TextNumber;
	private TextView TextDetail;
	private String Name;
	private String Number;
	private String Id = "";
	private String remark = "";
	private String ticket="";
	private SharedPreferences PreUserInfo;// 存储个人信息
	private SharedPreferences PreALLChannel;// 存储所用频道信息
	private static final String GET_SUCCESS_RESULT = "success";
	private static final String GET_FAIL_RESULT = "fail";
	private static final int GET_DUE_DATA = 6;
	public Handler uiHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case GET_DUE_DATA:
				GetDataDueData(msg.obj);
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
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.candicate_detail_activity);
		initview();
		setonclick();
		Intent intent = this.getIntent(); // 获取已有的intent对象
		Bundle bundle = intent.getExtras(); // 获取intent里面的bundle对象
		Name = bundle.getString("Name");
		Number = bundle.getString("Number");
		TextName.setText(Name);
		Id = bundle.getString("Id");
		remark = bundle.getString("remark");
		TextNumber.setText("当前票数：" + Number);
		TextDetail.setText(remark);
		PreUserInfo = getSharedPreferences("UserInfo", Context.MODE_PRIVATE);
		PreALLChannel = getSharedPreferences("ALLChannel", Context.MODE_PRIVATE);
		ReadTicket();
	}

	protected void GetDataDueData(Object obj) {

		// TODO Auto-generated method stub
		String Type = null;
		String Data = null;
		String pager = null;
		try {
			JSONObject demoJson = new JSONObject(obj.toString());
			Type = demoJson.getString("type");

			if (Type.equals(GET_SUCCESS_RESULT)) {
				Toast.makeText(getApplicationContext(), "投票成功", Toast.LENGTH_SHORT).show();

			} else if (Type.equals(GET_FAIL_RESULT)) {
				Toast.makeText(getApplicationContext(), "投票失败", Toast.LENGTH_SHORT).show();
			} else if (Type.equals("voteExpire")) {
				Toast.makeText(getApplicationContext(), "投票过期", Toast.LENGTH_SHORT).show();
			} else if (Type.equals("voted")) {
				Toast.makeText(getApplicationContext(), "重复投票", Toast.LENGTH_SHORT).show();
			} else {
				Toast.makeText(getApplicationContext(), "投票失败", Toast.LENGTH_SHORT).show();
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private void ReadTicket() {
		// TODO Auto-generated method stub
		ticket = PreUserInfo.getString("ticket", "");

	}

	private void initview() {
		// TODO Auto-generated method stub
		RoundHeadimg = (RoundImageView) findViewById(R.id.round_headimg);
		ImageBack = (ImageView) findViewById(R.id.image_back);
		ImageSymbol = (ImageView) findViewById(R.id.image_symbol);
		ImageSelected = (ImageView) findViewById(R.id.image_selected);
		TextName = (TextView) findViewById(R.id.text_name);
		TextPartyMember = (TextView) findViewById(R.id.text_party_member);
		TextNumber = (TextView) findViewById(R.id.text_number);
		TextDetail = (TextView) findViewById(R.id.text_detail);
	}

	private void setonclick() {
		// TODO Auto-generated method stub
		ImageBack.setOnClickListener(this);
		ImageSelected.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.image_back:
			finish();
			break;
		case R.id.image_selected:
			Toast.makeText(getApplicationContext(), "正在确认", Toast.LENGTH_SHORT).show();
			final ArrayList ArrayValues = new ArrayList();
			ArrayValues.add(new BasicNameValuePair("ticket", "" + ticket));
			ArrayValues.add(new BasicNameValuePair("voteItemId", "" + Id));
			new Thread(new Runnable() { // 开启线程上传文件
				@Override
				public void run() {
					String DueData = "";
					DueData = HttpGetData.GetData("api/cms/vote/vote", ArrayValues);
					Message msg = new Message();
					msg.obj = DueData;
					msg.what = GET_DUE_DATA;
					uiHandler.sendMessage(msg);
				}
			}).start();

			break;
		default:
			break;
		}
	}

}
