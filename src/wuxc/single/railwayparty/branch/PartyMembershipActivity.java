package wuxc.single.railwayparty.branch;

import java.util.ArrayList;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import wuxc.single.railwayparty.R;
import wuxc.single.railwayparty.internet.GetBitmapFromServer;
import wuxc.single.railwayparty.internet.HttpGetData;
import wuxc.single.railwayparty.internet.URLcontainer;
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
	private String ticket = "";
	private String myid = "";
	private String userPhoto;
	private TextView text_username;
	private TextView text_sign;
	private RoundImageView round_headimg;
	private final static int GET_USER_HEAD_IMAGE = 6;
	private Handler uiHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {

			case 7:
				GetDataDueData(msg.obj);
				break;
			case GET_USER_HEAD_IMAGE:
				ShowHeadImage(msg.obj);
				break;
			default:
				break;

			}
		}

	};

	protected void ShowHeadImage(Object obj) {
		// TODO Auto-generated method stub
		if (!(obj == null)) {
			try {
				Bitmap HeadImage = (Bitmap) obj;
				round_headimg.setImageBitmap(HeadImage);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}

	protected void GetDataDueData(Object obj) {

		// TODO Auto-generated method stub
		// String Type = null;
		String Data = null;
		// String pager = null;
		try {
			JSONObject demoJson = new JSONObject(obj.toString());
			String Type = demoJson.getString("type");

			Data = demoJson.getString("data");
			if (Type.equals("success")) {

				JSONObject json = new JSONObject(Data);
				Str_party_address = json.getString("address");
				Str_party_time= json.getString("operateTime");
			} else {
				// Toast.makeText(getApplicationContext(), "数据格式校验失败",
				// Toast.LENGTH_SHORT).show();
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
		}
		settext();
	}

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
		round_headimg = (RoundImageView) findViewById(R.id.image_headimg);
		ReadTicket();
		settext();
		GetData();
		GetHeadPic();
		ImageView imagesearch = (ImageView) findViewById(R.id.image_search);
		imagesearch.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent_membership = new Intent();
				intent_membership.setClass(getApplicationContext(), PartySearch.class);
				startActivity(intent_membership);
			}
		});
	}

	private void GetHeadPic() {
		// TODO Auto-generated method stub
		new Thread(new Runnable() { // 开启线程上传文件
			@Override
			public void run() {
				Bitmap HeadImage = null;
				HeadImage = GetBitmapFromServer
						.getBitmapFromServer(URLcontainer.urlip + URLcontainer.GetFile + userPhoto);
				Message msg = new Message();
				msg.what = GET_USER_HEAD_IMAGE;
				msg.obj = HeadImage;
				uiHandler.sendMessage(msg);
			}
		}).start();
	}

	private void ReadTicket() {
		// TODO Auto-generated method stub
		Str_name = PreUserInfo.getString("userName", "");
		Str_party_name = PreUserInfo.getString("deptName", "");
		Str_party_address = PreUserInfo.getString("address", "");
		Str_party_time = PreUserInfo.getString("pInTime", "");
		ticket = PreUserInfo.getString("ticket", "");
		myid = PreUserInfo.getString("deptId", "");
		userPhoto = PreUserInfo.getString("userPhoto", null);
	}

	private void settext() {
		// TODO Auto-generated method stub
		text_name.setText(Str_name);
		text_party_name.setText("党组织名称：" + Str_party_name);
		text_party_address.setText("党组织地址：" + Str_party_address);
		text_party_time.setText("转入本组织时间：" + Str_party_time);
		Editor edit = PreUserInfo.edit();
		edit.putString("detailtime", Str_party_time);
		edit.putString("detailaddress", Str_party_address);
		edit.commit();
	}

	private void GetData() {
		// TODO Auto-generated method stub

		// TODO Auto-generated method stub
		final ArrayList ArrayValues = new ArrayList();
		// ArrayValues.add(new BasicNameValuePair("ticket", ticket));
		// ArrayValues.add(new BasicNameValuePair("applyType", "" + 2));
		// ArrayValues.add(new BasicNameValuePair("helpSType", "" + type));
		// ArrayValues.add(new BasicNameValuePair("modelSign", "KNDY_APPLY"));
		// ArrayValues.add(new BasicNameValuePair("curPage", "" + curPage));
		// ArrayValues.add(new BasicNameValuePair("pageSize", "" + pageSize));
		// final ArrayList ArrayValues = new ArrayList();
		ArrayValues.add(new BasicNameValuePair("ticket", "" + ticket));
		// chn = GetChannelByKey.GetSign(PreALLChannel, "职工之家");

		ArrayValues.add(new BasicNameValuePair("datakey", "" + myid));

		new Thread(new Runnable() { // 开启线程上传文件
			@Override
			public void run() {
				String DueData = "";
				DueData = HttpGetData.GetData("api/pb/relationChange/getParty", ArrayValues);
				Message msg = new Message();
				msg.obj = DueData;
				msg.what = 7;
				uiHandler.sendMessage(msg);
			}
		}).start();

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
