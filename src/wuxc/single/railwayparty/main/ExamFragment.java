package wuxc.single.railwayparty.main;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v4.app.Fragment;
import android.view.View.OnClickListener;
import wuxc.single.railwayparty.R;
import wuxc.single.railwayparty.internet.HttpGetData;
import wuxc.single.railwayparty.model.MemberModel;

public class ExamFragment extends Fragment implements OnClickListener {
	private View view;// 缓存Fragment view
	private Button btn_exam;
	private TextView text_exam;
	List<MemberModel> list = new ArrayList<MemberModel>();
	private int pageSize = 10;
	private int totalPage = 10;
	private int curPage = 1;
	private final static int RATIO = 2;
	private TextView headTextView = null;
	private boolean[] read = { false, false, false, true, true, true, true, true, true, true, true, true, true, true,
			true, true, true, true, true, true };
	private SharedPreferences PreUserInfo;// 存储个人信息
	private int ticket;
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
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		if (null != view) {
			ViewGroup parent = (ViewGroup) view.getParent();
			if (null != parent) {
				parent.removeView(view);
			}
		} else {
			view = inflater.inflate(R.layout.wuxc_fragment_exam, container, false);
			// initview(view);
			btn_exam = (Button) view.findViewById(R.id.btn_exam);
			text_exam = (TextView) view.findViewById(R.id.text_exam);
			btn_exam.setOnClickListener(this);
			text_exam.setOnClickListener(this);
			PreUserInfo = getActivity().getSharedPreferences("UserInfo", Context.MODE_PRIVATE);
			// PreALLChannel = getSharedPreferences("ALLChannel",
			// Context.MODE_PRIVATE);
			// ReadTicket();
			ticket = PreUserInfo.getInt("ticket", 0);
			GetData();
		}

		return view;
	}

	protected void GetDataDueData(Object obj) {

		// TODO Auto-generated method stub
		String Type = null;
		String Data = null;
		String pager = null;
		try {
			JSONObject demoJson = new JSONObject(obj.toString());
			Type = demoJson.getString("type");
			pager = demoJson.getString("pager");
			Data = demoJson.getString("datas");
			if (Type.equals(GET_SUCCESS_RESULT)) {
				GetPager(pager);
				GetDataList(Data, curPage);
			} else if (Type.equals(GET_FAIL_RESULT)) {
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

	private void GetDataList(String data, int arg) {
		;
		if (arg == 1) {
			list.clear();
		}
		JSONArray jArray = null;
		try {
			jArray = new JSONArray(data);
			JSONObject json_data = null;
			if (jArray.length() == 0) {
				// / Toast.makeText(getApplicationContext(), "无数据",
				// Toast.LENGTH_SHORT).show();

			} else {
				for (int i = 0; i < jArray.length(); i++) {
					json_data = jArray.getJSONObject(i);

					json_data = json_data.getJSONObject("data");
					MemberModel listinfo = new MemberModel();
					listinfo.setImageUrl("");
					listinfo.setId(json_data.getString("keyid"));
					listinfo.setTime(json_data.getString("createTime"));
					listinfo.setTitle("上传时间:" + json_data.getString("createTime"));
					listinfo.setName(json_data.getString("title"));
					list.add(listinfo);

				}
			}

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void GetPager(String pager) {
		// TODO Auto-generated method stub
		try {
			JSONObject demoJson = new JSONObject(pager);

			totalPage = demoJson.getInt("totalPage");

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private void GetData() {
		// TODO Auto-generated method stub

		// TODO Auto-generated method stub
		final ArrayList ArrayValues = new ArrayList();
		ArrayValues.add(new BasicNameValuePair("ticket", "" + ticket));

		ArrayValues.add(new BasicNameValuePair("curPage", "" + curPage));
		ArrayValues.add(new BasicNameValuePair("pageSize", "" + pageSize));
		new Thread(new Runnable() { // 开启线程上传文件
			@Override
			public void run() {
				String DueData = "";
				DueData = HttpGetData.GetData("api/pubshare/testPaper/getListJsonData", ArrayValues);
				Message msg = new Message();
				msg.obj = DueData;
				msg.what = GET_DUE_DATA;
				uiHandler.sendMessage(msg);
			}
		}).start();

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_exam:
			// Intent intent = new Intent();
			// intent.setClass(getActivity(), TestActivity.class);
			// startActivity(intent);
			try {
				MemberModel data = list.get(0);
				Intent intent = new Intent();
				Bundle bundle = new Bundle();
				bundle.putString("Title", data.getName());
				bundle.putString("keyid", data.getId());
				bundle.putInt("ticket", ticket);
				intent.putExtras(bundle);
				intent.setClass(getActivity(), ExamActivity.class);
				startActivity(intent);

			} catch (Exception e) {
				// TODO: handle exception
				Toast.makeText(getActivity(), "暂无考试", Toast.LENGTH_SHORT).show();

			}

			break;
		case R.id.text_exam:
			Intent intent1 = new Intent();
			intent1.setClass(getActivity(), TestAnswerActivity.class);
			startActivity(intent1);
			break;

		default:
			break;
		}
	}

}
