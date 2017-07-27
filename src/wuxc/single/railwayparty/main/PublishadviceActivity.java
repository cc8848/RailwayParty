package wuxc.single.railwayparty.main;

import java.io.File;
import java.util.ArrayList;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import wuxc.single.railwayparty.R;
import wuxc.single.railwayparty.internet.HttpGetData;

public class PublishadviceActivity extends FragmentActivity implements OnClickListener {
	private EditText edit_name;
	private EditText edit_content;
	private Button btn_ok;
	private int ticket = 0;
	private String chn;
	private String userPhoto;
	private String LoginId;
	private SharedPreferences PreUserInfo;// 存储个人信息
	private SharedPreferences PreALLChannel;// 存储所用频道信息
	private static final String GET_SUCCESS_RESULT = "success";
	private static final String GET_FAIL_RESULT = "fail";
	private static final int GET_DUE_DATA = 6;
	private TextView TextArticle;
	private TextView TextVideo;
	private int type = 2;
	private LinearLayout lin_select;
	private LinearLayout lin_center;
	private TextView text_one;
	private TextView text_two;
	private TextView text_label;
	private TextView text_load;
	private int classify = 0;
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

	protected void GetDataDueData(Object obj) {
		text_load.setVisibility(View.GONE);
		// TODO Auto-generated method stub
		String Type = null;
		String Data = null;
		String pager = null;
		try {
			JSONObject demoJson = new JSONObject(obj.toString());
			Type = demoJson.getString("type");
			// pager = demoJson.getString("pager");
			// Data = demoJson.getString("datas");
			if (Type.equals(GET_SUCCESS_RESULT)) {
				Toast.makeText(getApplicationContext(), "成功", Toast.LENGTH_SHORT).show();

			} else if (Type.equals(GET_FAIL_RESULT)) {
				Toast.makeText(getApplicationContext(), "服务器数据失败", Toast.LENGTH_SHORT).show();
			} else {
				Toast.makeText(getApplicationContext(), "数据格式校验失败", Toast.LENGTH_SHORT).show();
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.wuxc_activity_publishtips2);
		ImageView image_back = (ImageView) findViewById(R.id.image_back);
		image_back.setOnClickListener(this);
		lin_select = (LinearLayout) findViewById(R.id.lin_select);
		lin_center = (LinearLayout) findViewById(R.id.lin_center);
		text_one = (TextView) findViewById(R.id.text_one);
		text_two = (TextView) findViewById(R.id.text_two);
		text_label = (TextView) findViewById(R.id.text_label);
		text_load = (TextView) findViewById(R.id.text_load);
		edit_name = (EditText) findViewById(R.id.edit_name);
		lin_select.setOnClickListener(this);
		lin_center.setOnClickListener(this);
		text_one.setOnClickListener(this);
		text_two.setOnClickListener(this);
		text_label.setOnClickListener(this);
		text_load.setOnClickListener(this);
		edit_content = (EditText) findViewById(R.id.edit_content);
		btn_ok = (Button) findViewById(R.id.btn_ok);
		btn_ok.setOnClickListener(this);
		PreUserInfo = getSharedPreferences("UserInfo", Context.MODE_PRIVATE);
		ReadTicket();
		TextView text_upload = (TextView) findViewById(R.id.text_upload);
		text_upload.setOnClickListener(this);
		// GetData();
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
		// ArrayValues.add(new BasicNameValuePair("modelSign", "xinde"));
		// ArrayValues.add(new BasicNameValuePair("par_keyid",
		// "886571396132638720"));
		ArrayValues.add(new BasicNameValuePair("suggestionsDto.content", "" + edit_content.getText().toString()));
		// ArrayValues.add(new BasicNameValuePair("xinde.hstate", "3"));
		// ArrayValues.add(new BasicNameValuePair("suggestionsDto.content", "" +
		// edit_content.getText().toString()));

		new Thread(new Runnable() { // 开启线程上传文件
			@Override
			public void run() {
				String DueData = "";
				DueData = HttpGetData.GetData("api/pubshare/suggestions/save", ArrayValues);
				Message msg = new Message();
				msg.obj = DueData;
				msg.what = GET_DUE_DATA;
				uiHandler.sendMessage(msg);
			}
		}).start();

	}

	private void ReadTicket() {
		// TODO Auto-generated method stub
		ticket = PreUserInfo.getInt("ticket", 0);
		userPhoto = PreUserInfo.getString("userPhoto", "");
		LoginId = PreUserInfo.getString("userName", "");
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.lin_select:
			lin_select.setVisibility(View.GONE);
			break;
		case R.id.lin_center:
			lin_select.setVisibility(View.VISIBLE);
			break;
		case R.id.text_one:
			lin_select.setVisibility(View.GONE);
			classify = 1;
			text_label.setText("合理化建议");
			break;
		case R.id.text_two:
			lin_select.setVisibility(View.GONE);
			classify = 2;
			text_label.setText("党员权益维护");
			break;
		case R.id.text_label:
			lin_select.setVisibility(View.VISIBLE);
			break;
		case R.id.image_back:
			finish();
			break;
		case R.id.btn_ok:

			if (classify == 0) {
				Toast.makeText(getApplicationContext(), "请选择分类", 0).show();

			} else if (edit_name.getText().toString().equals("") || edit_name.getText().toString() == null) {
				Toast.makeText(getApplicationContext(), "请输入标题", 0).show();

			} else if (edit_content.getText().toString().equals("") || edit_content.getText().toString() == null) {
				Toast.makeText(getApplicationContext(), "请输入内容", 0).show();

			} else {
				text_load.setVisibility(View.VISIBLE);
				GetData();
			}

			break;
		case R.id.text_upload:
			Intent intent = null;
			// if (Build.VERSION.SDK_INT < 19) {
			intent = new Intent(Intent.ACTION_GET_CONTENT);
			intent.setType("*/*");
			intent.addCategory(Intent.CATEGORY_OPENABLE);
			// } else {
			// intent = new Intent(Intent.ACTION_PICK,
			// android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
			// }
			startActivityForResult(intent, 0);
			break;
		default:
			break;
		}
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (data == null)
			return;
		Bundle bundle = data.getExtras();
		switch (requestCode) {

		case 0:
			// 发送选择的文件
			if (data != null) {
				Uri uri = data.getData();
				if (uri != null) {
					Toast.makeText(getApplicationContext(), "正在上传", 0).show();

					GetFile(uri);
				}
			}

			break;
		default:
			break;
		}
	}

	/**
	 * 获取文件
	 * 
	 * @param uri
	 */
	private void GetFile(Uri uri) {
		String filePath = null;
		if ("content".equalsIgnoreCase(uri.getScheme())) {
			String[] projection = { "_data" };
			Cursor cursor = null;

			try {
				cursor = getApplicationContext().getContentResolver().query(uri, projection, null, null, null);
				int column_index = cursor.getColumnIndexOrThrow("_data");
				if (cursor.moveToFirst()) {
					filePath = cursor.getString(column_index);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if ("file".equalsIgnoreCase(uri.getScheme())) {
			filePath = uri.getPath();
		}
		File file = new File(filePath);
		if (file == null || !file.exists()) {

			Toast.makeText(getApplicationContext(), "文件不存在", 0).show();
			return;
		}
		if (file.length() > 20 * 1024 * 1024) {

			Toast.makeText(getApplicationContext(), "文件不能大于20M", 0).show();
			return;
		}
	}
}
