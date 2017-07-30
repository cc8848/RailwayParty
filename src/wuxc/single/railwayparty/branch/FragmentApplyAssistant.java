package wuxc.single.railwayparty.branch;

import java.io.File;
import java.util.ArrayList;

import org.apache.http.message.BasicNameValuePair;

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
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import wuxc.single.railwayparty.R;
import wuxc.single.railwayparty.internet.HttpGetData;

public class FragmentApplyAssistant extends Fragment implements OnClickListener {

	private View view;// 缓存Fragment view
	private EditText edit_name;
	private EditText edit_id;
	private EditText edit_phone;
	private EditText edit_content;
	private TextView text_load;
	private Button btn_ok;
	private int ticket = 0;
	private String chn;
	private String userPhoto;
	private String LoginId;
	private SharedPreferences PreUserInfo;// 存储个人信息
	public Handler uiHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 14:
				text_load.setVisibility(view.GONE);
				break;
			default:
				break;
			}
		}
	};

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
			edit_name = (EditText) view.findViewById(R.id.edit_name);

			edit_id = (EditText) view.findViewById(R.id.edit_id);

			edit_phone = (EditText) view.findViewById(R.id.edit_phone);

			edit_content = (EditText) view.findViewById(R.id.edit_content);

			text_load = (TextView) view.findViewById(R.id.text_load);

			btn_ok = (Button) view.findViewById(R.id.btn_ok);
			btn_ok.setOnClickListener(this);
			text_load.setOnClickListener(this);

		}

		return view;

	}

	private void ReadTicket() {
		// TODO Auto-generated method stub
		ticket = PreUserInfo.getInt("ticket", 0);
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
			intent.setType("*/*");
			intent.addCategory(Intent.CATEGORY_OPENABLE);
			// } else {
			// intent = new Intent(Intent.ACTION_PICK,
			// android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
			// }
			startActivityForResult(intent, 0);
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
		ArrayValues.add(new BasicNameValuePair("xinde.content", "" + edit_content.getText().toString()));

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
			// 发送选择的文件
			if (data != null) {
				Uri uri = data.getData();
				if (uri != null) {
					Toast.makeText(getActivity(), "正在上传", 0).show();

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
			return;
		}
		if (file.length() > 20 * 1024 * 1024) {

			Toast.makeText(getActivity(), "文件不能大于20M", 0).show();
			return;
		}

	}
}
