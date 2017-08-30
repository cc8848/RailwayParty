package wuxc.single.railwayparty.other;

import java.util.ArrayList;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import wuxc.single.railwayparty.R;
import wuxc.single.railwayparty.internet.HttpGetData;

public class ResetPwdActivity extends Activity implements OnClickListener {
	private EditText edit_idnumber;
	private EditText edit_code;
	private EditText edit_new;
	private EditText edit_new1;
	private TextView text_code;
	private Button btn_ok;
	private String idnumber = "";
	private int type = 0;
	private static final String GET_SUCCESS_RESULT = "success";
	public Handler uiHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			// case GET_DUE_DATA:
			// GetDataDueData(msg.obj);
			// break;
			// case GET_LOGININ_RESULT_DATA:
			// GetDataDetailFromLoginResultData(msg.obj);
			// break;
			case 12:
				GetDataDetailFromLoginResultDatacode(msg.obj);
				break;
			case 13:
				GetDataDetail(msg.obj);
				break;
			default:
				break;
			}
		}

	};

	private void GetDataDetail(Object obj) {

		// TODO Auto-generated method stub
		String Type = null;
		String Data = null;

		try {
			JSONObject demoJson = new JSONObject(obj.toString());
			Type = demoJson.getString("type");

			if (Type.equals(GET_SUCCESS_RESULT)) {
				// Data = demoJson.getString("data");
				Toast.makeText(getApplicationContext(), "重置成功", Toast.LENGTH_SHORT).show();
				// GetDetailData(Data);
				finish();
			} else if (Type.equals("mobileCodeNull")) {
				Toast.makeText(getApplicationContext(), "验证码不能为空", Toast.LENGTH_SHORT).show();

			} else if (Type.equals("typeNull")) {
				Toast.makeText(getApplicationContext(), "类型不能为空", Toast.LENGTH_SHORT).show();

			} else if (Type.equals("passwordNull")) {
				Toast.makeText(getApplicationContext(), "密码不能为空", Toast.LENGTH_SHORT).show();

			} else if (Type.equals("userUniqueIdNull")) {
				Toast.makeText(getApplicationContext(), "用户唯一标识不能为空", Toast.LENGTH_SHORT).show();

			} else if (Type.equals("noUser")) {
				Toast.makeText(getApplicationContext(), "用户不存在", Toast.LENGTH_SHORT).show();

			} else if (Type.equals("mobileCodeError")) {
				Toast.makeText(getApplicationContext(), "验证码不正确", Toast.LENGTH_SHORT).show();

			} else {
				Toast.makeText(getApplicationContext(), "重置失败", Toast.LENGTH_SHORT).show();
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	protected void GetDataDetailFromLoginResultDatacode(Object obj) {

		// TODO Auto-generated method stub
		String Type = null;
		String Data = null;

		try {
			JSONObject demoJson = new JSONObject(obj.toString());
			Type = demoJson.getString("type");

			if (Type.equals(GET_SUCCESS_RESULT)) {
				// Data = demoJson.getString("data");
				Toast.makeText(getApplicationContext(), "发送成功", Toast.LENGTH_SHORT).show();
				// GetDetailData(Data);
				// finish();
			} else if (Type.equals("notNull")) {
				Toast.makeText(getApplicationContext(), "手机号为空", Toast.LENGTH_SHORT).show();

			} else if (Type.equals("mobileUsed")) {
				Toast.makeText(getApplicationContext(), "手机号已被使用", Toast.LENGTH_SHORT).show();

			} else if (Type.equals("userUniqueIdNull")) {
				Toast.makeText(getApplicationContext(), "唯一标识不可为空", Toast.LENGTH_SHORT).show();

			} else if (Type.equals("noUser")) {
				Toast.makeText(getApplicationContext(), "用户不存在", Toast.LENGTH_SHORT).show();

			} else if (Type.equals("typeNull")) {
				Toast.makeText(getApplicationContext(), "类型不可为空", Toast.LENGTH_SHORT).show();

			} else if (Type.equals("templateNameNull")) {
				Toast.makeText(getApplicationContext(), "短信模板不可为空", Toast.LENGTH_SHORT).show();

			} else {
				Toast.makeText(getApplicationContext(), "获取失败", Toast.LENGTH_SHORT).show();
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
		setContentView(R.layout.wuxc_activity_reset_pwd);
		ImageView image_back = (ImageView) findViewById(R.id.image_back);
		image_back.setOnClickListener(this);
		edit_idnumber = (EditText) findViewById(R.id.edit_idnumber);
		edit_code = (EditText) findViewById(R.id.edit_code);
		edit_new = (EditText) findViewById(R.id.edit_new);
		edit_new1 = (EditText) findViewById(R.id.edit_new1);
		text_code = (TextView) findViewById(R.id.text_code);
		btn_ok = (Button) findViewById(R.id.btn_ok);
		btn_ok.setOnClickListener(this);
		text_code.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.image_back:
			finish();
			break;
		case R.id.text_code:
			idnumber = edit_idnumber.getText().toString();
			if (idnumber.equals("") || idnumber == null) {
				Toast.makeText(getApplicationContext(), "唯一标识不可为空", Toast.LENGTH_SHORT).show();
			} else {
				String[] temp = idnumber.split("");
				if (temp.length == 12) {
					type = 3;
					sendcode(type);
					// } else if (temp.length == 19) {
					// type = 2;
					// sendcode(type);
				} else {
					Toast.makeText(getApplicationContext(), "请输入11手机号", Toast.LENGTH_SHORT).show();

				}
			}
			break;
		case R.id.btn_ok:
			idnumber = edit_idnumber.getText().toString();
			String newpwd = edit_new.getText().toString();
			String newpwd1 = edit_new1.getText().toString();
			if (idnumber.equals("") || idnumber == null) {
				Toast.makeText(getApplicationContext(), "唯一标识不可为空", Toast.LENGTH_SHORT).show();
			} else {
				String[] temp = idnumber.split("");
				if (temp.length == 12) {
					type = 3;
					if (edit_code.getText().toString() == null || edit_code.getText().toString().equals("")) {
						Toast.makeText(getApplicationContext(), "验证码不可为空", Toast.LENGTH_SHORT).show();
					} else if (newpwd.equals("") || newpwd == null) {
						Toast.makeText(getApplicationContext(), "请输入新密码", Toast.LENGTH_SHORT).show();

					} else if (newpwd1.equals("") || newpwd1 == null) {
						Toast.makeText(getApplicationContext(), "请确认新密码", Toast.LENGTH_SHORT).show();

					} else if (!newpwd.equals(newpwd1)) {
						Toast.makeText(getApplicationContext(), "两次输入不一致", Toast.LENGTH_SHORT).show();

					} else {
						resetdata(type);
					}
					// } else if (temp.length == 19) {
					// type = 2;
					// if (edit_code.getText().toString() == null ||
					// edit_code.getText().toString().equals("")) {
					// Toast.makeText(getApplicationContext(), "验证码不可为空",
					// Toast.LENGTH_SHORT).show();
					// } else if (newpwd.equals("") || newpwd == null) {
					// Toast.makeText(getApplicationContext(), "请输入新密码",
					// Toast.LENGTH_SHORT).show();
					//
					// } else if (newpwd1.equals("") || newpwd1 == null) {
					// Toast.makeText(getApplicationContext(), "请确认新密码",
					// Toast.LENGTH_SHORT).show();
					//
					// } else if (!newpwd.equals(newpwd1)) {
					// Toast.makeText(getApplicationContext(), "两次输入不一致",
					// Toast.LENGTH_SHORT).show();
					//
					// } else {
					// resetdata(type);
					// }
				} else {
					Toast.makeText(getApplicationContext(), "请输入11手机号", Toast.LENGTH_SHORT).show();
				}
			}
			break;
		default:
			break;
		}
	}

	private void resetdata(int type) {
		// TODO Auto-generated method stub
		final ArrayList ArrayValues = new ArrayList();
		ArrayValues.add(new BasicNameValuePair("userUniqueId", "" + edit_idnumber.getText().toString()));
		ArrayValues.add(new BasicNameValuePair("type", "" + type));
		ArrayValues.add(new BasicNameValuePair("mobileCode", edit_code.getText().toString()));
		ArrayValues.add(new BasicNameValuePair("password", edit_new.getText().toString()));
		new Thread(new Runnable() { // 开启线程上传文件
			@Override
			public void run() {
				String DueData = "";
				DueData = HttpGetData.GetData("api/member/resetPassword", ArrayValues);
				Message msg = new Message();
				msg.obj = DueData;
				msg.what = 13;
				uiHandler.sendMessage(msg);
			}
		}).start();
	}

	private void sendcode(int type) {
		// TODO Auto-generated method stub
		final ArrayList ArrayValues = new ArrayList();
		ArrayValues.add(new BasicNameValuePair("userUniqueId", "" + edit_idnumber.getText().toString()));
		ArrayValues.add(new BasicNameValuePair("type", "" + type));
		ArrayValues.add(new BasicNameValuePair("templateName", "modifyPwd"));
		new Thread(new Runnable() { // 开启线程上传文件
			@Override
			public void run() {
				String DueData = "";
				DueData = HttpGetData.GetData("api/member/sendMobileCodeToUser", ArrayValues);
				Message msg = new Message();
				msg.obj = DueData;
				msg.what = 12;
				uiHandler.sendMessage(msg);
			}
		}).start();
	}

}
