package wuxc.single.railwayparty.branch;

import java.io.File;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import wuxc.single.railwayparty.R;
import wuxc.single.railwayparty.internet.HttpGetData;
import wuxc.single.railwayparty.internet.URLcontainer;
import wuxc.single.railwayparty.internet.UpLoadFile;

public class FragmentApplyAssistant extends Fragment implements OnClickListener {

	private View view;// 缓存Fragment view
	private EditText edit_name;
	private EditText edit_id;
	private EditText edit_phone;
	private EditText edit_content;
	private TextView text_load;
	private Button btn_ok;
	private String ticket = "";
	private String chn;
	private String userPhoto;
	private String LoginId;
	private SharedPreferences PreUserInfo;// 存储个人信息
	private String attachment_ext = "";
	private String attachment_scalePath = "";
	private String attachment_classify = "";
	private String attachment_fileName = "";
	private String attachment_par_keyid = "";
	private String attachment_size = "";
	private String attachment_filePath = "";
	private String attachment_pathType = "";
	private String attachment_key = "";
	private RelativeLayout rel_file;
	private TextView text_filename;
	private TextView text_dele;
	private static final String GET_SUCCESS_RESULT = "success";
	private static final String GET_FAIL_RESULT = "fail";
	private static final int GET_DUE_DATA = 6;
	private int number = 0;
	public Handler uiHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 14:
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
				Toast.makeText(getActivity(), "文件上传成功", 0).show();

				GetDetailDataAttachment(fileInfo);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			// Toast.makeText(getActivity(), "", 0).show();
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

	protected void GetDataDueData(Object obj) {

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
				Toast.makeText(getActivity(), "申请成功", Toast.LENGTH_SHORT).show();
				text_load.setVisibility(View.GONE);
				showfile();
				edit_content.setText("");
				edit_name.setText("");
				edit_id.setText("");
				edit_phone.setText("");
			} else if (Type.equals(GET_FAIL_RESULT)) {
				Toast.makeText(getActivity(), "服务器数据失败", Toast.LENGTH_SHORT).show();
			} else {
				Toast.makeText(getActivity(), "数据格式校验失败", Toast.LENGTH_SHORT).show();
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		if (null != view) {
			ViewGroup parent = (ViewGroup) view.getParent();
			if (null != parent) {
				parent.removeView(view);
			}
		} else {
			view = inflater.inflate(R.layout.wuxc_fragment_apply_assistant, container, false);
			initview(view);
			setonclicklistener();
			PreUserInfo = getActivity().getSharedPreferences("UserInfo", Context.MODE_PRIVATE);
			ReadTicket();
			TextView text_upload = (TextView) view.findViewById(R.id.text_upload);
			text_upload.setOnClickListener(this);
			rel_file = (RelativeLayout) view.findViewById(R.id.rel_file);

			text_filename = (TextView) view.findViewById(R.id.text_filename);

			text_dele = (TextView) view.findViewById(R.id.text_dele);
			text_dele.setOnClickListener(this);
			edit_name = (EditText) view.findViewById(R.id.edit_name);

			edit_id = (EditText) view.findViewById(R.id.edit_id);

			edit_phone = (EditText) view.findViewById(R.id.edit_phone);

			edit_content = (EditText) view.findViewById(R.id.edit_content);

			text_load = (TextView) view.findViewById(R.id.text_load);

			btn_ok = (Button) view.findViewById(R.id.btn_ok);
			btn_ok.setOnClickListener(this);
			text_load.setOnClickListener(this);
			showfile();
		}

		return view;

	}

	private void showfile() {
		// TODO Auto-generated method stub
		if (number == 0) {
			rel_file.setVisibility(view.GONE);
		} else {
			rel_file.setVisibility(view.VISIBLE);
			text_filename.setText(attachment_fileName);
		}
	}

	private void ReadTicket() {
		// TODO Auto-generated method stub
		ticket = PreUserInfo.getString("ticket", "");
		userPhoto = PreUserInfo.getString("userPhoto", "");
		LoginId = PreUserInfo.getString("deptName", "");
	}

	private void setonclicklistener() {
		// TODO Auto-generated method stub

	}

	private void initview(View view2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
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
		case R.id.btn_ok:
			String name = edit_name.getText().toString();
			String id = edit_id.getText().toString();
			String content = edit_content.getText().toString();
			String phone = edit_phone.getText().toString();
			if (name.equals("") || name == null) {
				Toast.makeText(getActivity(), "申请人不可为空", 0).show();

			} else if (id.equals("") || id == null) {
				Toast.makeText(getActivity(), "身份证号不可为空", 0).show();

			} else if (content.equals("") || content == null) {
				Toast.makeText(getActivity(), "申请理由不可为空", 0).show();

			} else if (phone.equals("") || phone == null) {
				Toast.makeText(getActivity(), "电话号码不可为空", 0).show();

			} else {
				text_load.setVisibility(view.VISIBLE);
				GetData();
			}
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
		ArrayValues.add(new BasicNameValuePair("modelSign", "kndy_apply"));
		ArrayValues.add(new BasicNameValuePair("par_keyid", "887450836228247552"));
		ArrayValues.add(new BasicNameValuePair("kndy_apply.name", "" + edit_name.getText().toString()));
		ArrayValues.add(new BasicNameValuePair("kndy_apply.telphone", "" + edit_phone.getText().toString()));
		ArrayValues.add(new BasicNameValuePair("kndy_apply.idCardNo", "" + edit_id.getText().toString()));
		ArrayValues.add(new BasicNameValuePair("kndy_apply.hstate", "0"));
		ArrayValues.add(new BasicNameValuePair("kndy_apply.orgName", LoginId));
		ArrayValues.add(new BasicNameValuePair("kndy_apply.content", "" + edit_content.getText().toString()));
		if (attachment_filePath.equals("")||attachment_filePath==null) {
			
		}else {
			ArrayValues.add(new BasicNameValuePair("attacement.operateFlag", "1"));
			ArrayValues.add(new BasicNameValuePair("attacement.ext", attachment_ext));

			ArrayValues.add(new BasicNameValuePair("attacement.scalePath", attachment_scalePath));

			ArrayValues.add(new BasicNameValuePair("attacement.classify", "attachFile"));
			ArrayValues.add(new BasicNameValuePair("attacement.fileName", attachment_fileName));
			ArrayValues.add(new BasicNameValuePair("attacement.par_keyid", attachment_par_keyid));
			ArrayValues.add(new BasicNameValuePair("attacement.size", attachment_size));
			ArrayValues.add(new BasicNameValuePair("attacement.filePath", attachment_filePath));

			ArrayValues.add(new BasicNameValuePair("attacement.pathType", attachment_pathType));
			ArrayValues.add(new BasicNameValuePair("attacement.key", attachment_key));

		}
		
		new Thread(new Runnable() { // 开启线程上传文件
			@Override
			public void run() {
				String DueData = "";
				DueData = HttpGetData.GetData("api/form/formContent/saveData", ArrayValues);
				Message msg = new Message();
				msg.obj = DueData;
				msg.what = 14;
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
			if (data != null) {
				Uri uri = data.getData();
				if (uri != null) {
					// Toast.makeText(getActivity(), "正在上传",
					// 0).show();

					final File file = GetFile(uri);
					Log.e("getName", file.getName());
					attachment_fileName = file.getName();
					text_load.setVisibility(View.VISIBLE);
					if (!(file == null)) {
						new Thread(new Runnable() { // 开启线程上传文件
							@Override
							public void run() {
								String UpLoadResult = UpLoadFile.uploadFile(file,
										URLcontainer.urlip + "console/form/formfileUpload/uploadSignle", "xinde",
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
				cursor = getActivity().getContentResolver().query(uri, projection, null, null, null);
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

			Toast.makeText(getActivity(), "文件不存在", 0).show();
			return file;
		}
		if (file.length() > 20 * 1024 * 1024) {

			Toast.makeText(getActivity(), "文件不能大于20M", 0).show();
			return null;
		}
		return file;
	}
}
