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
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import wuxc.single.railwayparty.R;
import wuxc.single.railwayparty.internet.HttpGetData;
import wuxc.single.railwayparty.internet.URLcontainer;
import wuxc.single.railwayparty.internet.UpLoadFile;

public class ReportOutnameActivity extends FragmentActivity implements OnClickListener {
	private String ticket = "";
	private SharedPreferences PreUserInfo;// 存储个人信息
	private String attachment_ext;
	private String attachment_scalePath;
	private String attachment_classify;
	private String attachment_fileName;
	private String attachment_par_keyid;
	private String attachment_size;
	private String attachment_filePath;
	private String attachment_pathType;
	private String attachment_key;
	private TextView text_load;
	private String classify;
	private String informer = "匿名";
	private String tel = "110";
	private String idCardNo = "110";
	private String beInformer;
	private String duty;
	private String unit;
	private String title;
	private String content;
	private EditText edit_name;
	private EditText edit_phone;
	private EditText edit_idnumber;
	private EditText edit_beinform;
	private EditText edit_unit;
	private EditText edit_duty;
	private EditText edit_title;
	private EditText edit_content;
	private Button btn_ok;
	private RelativeLayout rel_file;
	private TextView text_filename;
	private TextView text_dele;
	private int number = 0;
	private Handler uiHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 8:
				GetDataDueData(msg.obj);
				break;
			case 1:
				GetDataAttachment(msg.obj);
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
		setContentView(R.layout.wuxc_activity_report_outname);
		ImageView image_back = (ImageView) findViewById(R.id.image_back);
		image_back.setOnClickListener(this);
		TextView text_upload = (TextView) findViewById(R.id.text_upload);
		text_upload.setOnClickListener(this);
		rel_file = (RelativeLayout) findViewById(R.id.rel_file);

		text_filename = (TextView) findViewById(R.id.text_filename);

		text_dele = (TextView) findViewById(R.id.text_dele);
		text_dele.setOnClickListener(this);
		PreUserInfo = getSharedPreferences("UserInfo", Context.MODE_PRIVATE);
		ReadTicket();
		text_load = (TextView) findViewById(R.id.text_load);
		text_load.setOnClickListener(this);
		text_load.setVisibility(View.GONE);
		edit_name = (EditText) findViewById(R.id.edit_name);
		edit_phone = (EditText) findViewById(R.id.edit_phone);
		edit_idnumber = (EditText) findViewById(R.id.edit_idnumber);
		edit_beinform = (EditText) findViewById(R.id.edit_beinform);
		edit_unit = (EditText) findViewById(R.id.edit_unit);
		edit_duty = (EditText) findViewById(R.id.edit_duty);
		edit_title = (EditText) findViewById(R.id.edit_title);
		edit_content = (EditText) findViewById(R.id.edit_content);
		btn_ok = (Button) findViewById(R.id.btn_ok);
		btn_ok.setOnClickListener(this);
		showfile();
	}

	private void showfile() {
		// TODO Auto-generated method stub
		if (number == 0) {
			rel_file.setVisibility(View.GONE);
		} else {
			rel_file.setVisibility(View.VISIBLE);
			text_filename.setText(attachment_fileName);
		}
	}

	protected void GetDataDueData(Object obj) {

		// TODO Auto-generated method stub
		text_load.setVisibility(View.GONE);
		String Type = null;
		String Data = null;
		String pager = null;
		try {
			JSONObject demoJson = new JSONObject(obj.toString());
			Type = demoJson.getString("type");
			// pager = demoJson.getString("pager");
			// Data = demoJson.getString("datas");
			if (Type.equals("success")) {
				Toast.makeText(getApplicationContext(), "举报成功", Toast.LENGTH_SHORT).show();
				finish();
			} else if (Type.equals("fail")) {
				// Toast.makeText(getApplicationContext(), "服务器数据失败",
				// Toast.LENGTH_SHORT).show();
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
	}

	protected void GetDataAttachment(Object obj) {
		text_load.setVisibility(View.GONE);

		// TODO Auto-generated method stub
		String state = null;
		String fileInfo = null;
		try {
			JSONObject demoJson = new JSONObject(obj.toString());
			state = demoJson.getString("state");
			fileInfo = demoJson.getString("fileInfo");
			if (state.equals("1")) {
				Toast.makeText(getApplicationContext(), "文件上传成功", 0).show();

				GetDetailDataAttachment(fileInfo);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			// Toast.makeText(getApplicationContext(), "", 0).show();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private void GetDetailDataAttachment(String fileInfo) {
		// TODO Auto-generated method stub
		try {
			JSONObject demoJson = new JSONObject(fileInfo);

			attachment_ext = demoJson.getString("ext");
			attachment_classify = demoJson.getString("classify");
			// attachment_fileName = demoJson.getString("fileName");
			attachment_filePath = demoJson.getString("filePath");
			attachment_key = demoJson.getString("key");
			attachment_par_keyid = demoJson.getString("par_keyid");
			attachment_pathType = demoJson.getString("pathType");
			attachment_scalePath = demoJson.getString("scalePath");
			attachment_size = demoJson.getString("size");
			number = 1;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
		}
		showfile();
	}

	private void ReadTicket() {
		// TODO Auto-generated method stub
		ticket = PreUserInfo.getString("ticket", "");

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.image_back:
			finish();
			break;
		case R.id.text_upload:
			Intent intent = null;
			// if (Build.VERSION.SDK_INT < 19) {
			intent = new Intent(Intent.ACTION_GET_CONTENT);
			intent.setType("file/*");
			intent.addCategory(Intent.CATEGORY_OPENABLE);
			// } else {
			// intent = new Intent(Intent.ACTION_PICK,
			// android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
			// }
			startActivityForResult(intent, 0);
			break;
		case R.id.btn_ok:
			// informer = edit_name.getText().toString();
			// tel = edit_phone.getText().toString();
			// idCardNo = edit_idnumber.getText().toString();
			beInformer = edit_beinform.getText().toString();
			unit = edit_unit.getText().toString();
			duty = edit_duty.getText().toString();
			title = edit_title.getText().toString();
			content = edit_content.getText().toString();
			if (informer.equals("") || informer == null) {
				Toast.makeText(getApplicationContext(), "举报人姓名不可为空", 0).show();
			} else if (tel.equals("") || informer == null) {
				Toast.makeText(getApplicationContext(), "举报人电话不可为空", 0).show();
			} else if (idCardNo.equals("") || informer == null) {
				Toast.makeText(getApplicationContext(), "举报人身份证号不可为空", 0).show();
			} else if (beInformer.equals("") || informer == null) {
				Toast.makeText(getApplicationContext(), "被举报人姓名不可为空", 0).show();
			} else if (unit.equals("") || informer == null) {
				Toast.makeText(getApplicationContext(), "被举报人单位不可为空", 0).show();
			} else if (duty.equals("") || informer == null) {
				Toast.makeText(getApplicationContext(), "被举报人职位不可为空", 0).show();
			} else if (title.equals("") || informer == null) {
				Toast.makeText(getApplicationContext(), "举报标题不可为空", 0).show();
			} else if (content.equals("") || informer == null) {
				Toast.makeText(getApplicationContext(), "举报内容不可为空", 0).show();
			} else {
				text_load.setVisibility(View.VISIBLE);
				GetData();
			}
			break;
		case R.id.text_dele:
			number = 0;
			attachment_ext = "";
			attachment_scalePath = "";
			attachment_classify = "";
			attachment_fileName = "";
			attachment_par_keyid = "";
			attachment_size = "";
			attachment_filePath = "";
			attachment_pathType = "";
			attachment_key = "";
			showfile();
			break;
		default:
			break;
		}
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
		ArrayValues.add(new BasicNameValuePair("informDto.classify", "1"));
		ArrayValues.add(new BasicNameValuePair("informDto.informer", informer));
		ArrayValues.add(new BasicNameValuePair("informDto.tel", tel));
		ArrayValues.add(new BasicNameValuePair("informDto.idCardNo", idCardNo));
		ArrayValues.add(new BasicNameValuePair("informDto.beInformer", beInformer));
		ArrayValues.add(new BasicNameValuePair("informDto.duty", duty));
		ArrayValues.add(new BasicNameValuePair("informDto.unit", unit));
		ArrayValues.add(new BasicNameValuePair("informDto.title", title));
		ArrayValues.add(new BasicNameValuePair("informDto.content", content));
		ArrayValues.add(new BasicNameValuePair("attacement.operateFlag", "1"));
		ArrayValues.add(new BasicNameValuePair("attacement.ext", attachment_ext));
		ArrayValues.add(new BasicNameValuePair("attacement.scalePath", attachment_scalePath));
		ArrayValues.add(new BasicNameValuePair("attacement.classify", "main"));
		ArrayValues.add(new BasicNameValuePair("attacement.fileName", attachment_fileName));
		ArrayValues.add(new BasicNameValuePair("attacement.par_keyid", attachment_par_keyid));
		ArrayValues.add(new BasicNameValuePair("attacement.size", attachment_size));
		ArrayValues.add(new BasicNameValuePair("attacement.filePath", attachment_filePath));
		ArrayValues.add(new BasicNameValuePair("attacement.pathType", attachment_pathType));
		ArrayValues.add(new BasicNameValuePair("attacement.key", attachment_key));

		new Thread(new Runnable() { // 开启线程上传文件
			@Override
			public void run() {
				String DueData = "";
				DueData = HttpGetData.GetData("api/pb/inform/save", ArrayValues);
				Message msg = new Message();
				msg.obj = DueData;
				msg.what = 8;
				uiHandler.sendMessage(msg);
			}
		}).start();

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
					final File file = GetFile(uri);
					Log.e("getName", file.getName());
					attachment_fileName = file.getName();
					text_load.setVisibility(View.VISIBLE);
					if (!(file == null)) {
						new Thread(new Runnable() { // 开启线程上传文件
							@Override
							public void run() {
								String UpLoadResult = UpLoadFile.uploadFile(file,
										URLcontainer.urlip + "console/pb/informfileUpload/uploadMultiple", "attachment",
										"" + ticket);
								Message msg = new Message();
								msg.what = 1;
								msg.obj = UpLoadResult;
								uiHandler.sendMessage(msg);
							}
						}).start();
					}

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
	private File GetFile(Uri uri) {
		String filePath = null;
		if ("content".equalsIgnoreCase(uri.getScheme())) {
			String[] projection = { "_data" };
			Cursor cursor = null;

			try {
				cursor = getContentResolver().query(uri, projection, null, null, null);
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
			return file;
		}
		if (file.length() > 20 * 1024 * 1024) {

			Toast.makeText(getApplicationContext(), "文件不能大于20M", 0).show();
			return null;
		}
		return file;
	}
}
