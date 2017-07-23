package wuxc.single.railwayparty.main;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import wuxc.single.railwayparty.R;
import wuxc.single.railwayparty.internet.HttpGetData;
import wuxc.single.railwayparty.model.ExamTopicModel;

public class ExamResultActivity extends Activity implements OnClickListener {

	private TextView text_title;
	private ImageView image_mark;
	private TextView text_mark;
	private Button btn_1;
	private Button btn_2;
	private Button btn_3;
	private Button btn_4;
	private Button btn_5;
	private Button btn_6;
	private Button btn_7;
	private Button btn_8;
	private Button btn_9;
	private Button btn_10;
	private Button btn_11;
	private Button btn_12;
	private Button btn_13;
	private Button btn_14;
	private Button btn_15;
	private Button btn_16;
	private Button btn_17;
	private Button btn_18;
	private Button btn_19;
	private Button btn_20;
	private Button[] btn = { btn_1, btn_2, btn_3, btn_4, btn_5, btn_6, btn_7, btn_8, btn_9, btn_10, btn_11, btn_12,
			btn_13, btn_14, btn_15, btn_16, btn_17, btn_18, btn_19, btn_20 };
	private List<ExamTopicModel> list = new ArrayList<ExamTopicModel>();
	private int[] imagemark = { R.drawable.mark0, R.drawable.mark05, R.drawable.mark10, R.drawable.mark15,
			R.drawable.mark20, R.drawable.mark25, R.drawable.mark30, R.drawable.mark35, R.drawable.mark40,
			R.drawable.mark45, R.drawable.mark50, R.drawable.mark55, R.drawable.mark60, R.drawable.mark65,
			R.drawable.mark70, R.drawable.mark75, R.drawable.mark80, R.drawable.mark85, R.drawable.mark90,
			R.drawable.mark95, R.drawable.mark100 };
	private String Title;
	private String Id;
	private int ticket = 0;
	private static final String GET_SUCCESS_RESULT = "success";
	private static final String GET_FAIL_RESULT = "fail";
	private static final int GET_DUE_DATA = 6;
	private int topicnumber = 2;
	private String Answer = "";
	private int score = 0;
	private int[] user;
	private String answer = "";

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
		setContentView(R.layout.wuxc_activity_exam_result);
		ImageView image_back = (ImageView) findViewById(R.id.image_back);
		image_back.setOnClickListener(this);
		text_title = (TextView) findViewById(R.id.text_title);
		image_mark = (ImageView) findViewById(R.id.image_mark);
		text_mark = (TextView) findViewById(R.id.text_mark);
		btn[0] = (Button) findViewById(R.id.btn_1);
		btn[1] = (Button) findViewById(R.id.btn_2);
		btn[2] = (Button) findViewById(R.id.btn_3);
		btn[3] = (Button) findViewById(R.id.btn_4);
		btn[4] = (Button) findViewById(R.id.btn_5);
		btn[5] = (Button) findViewById(R.id.btn_6);
		btn[6] = (Button) findViewById(R.id.btn_7);
		btn[7] = (Button) findViewById(R.id.btn_8);
		btn[8] = (Button) findViewById(R.id.btn_9);
		btn[9] = (Button) findViewById(R.id.btn_10);
		btn[10] = (Button) findViewById(R.id.btn_11);
		btn[11] = (Button) findViewById(R.id.btn_12);
		btn[12] = (Button) findViewById(R.id.btn_13);
		btn[13] = (Button) findViewById(R.id.btn_14);
		btn[14] = (Button) findViewById(R.id.btn_15);
		btn[15] = (Button) findViewById(R.id.btn_16);
		btn[16] = (Button) findViewById(R.id.btn_17);
		btn[17] = (Button) findViewById(R.id.btn_18);
		btn[18] = (Button) findViewById(R.id.btn_19);
		btn[19] = (Button) findViewById(R.id.btn_20);
		btn[0].setOnClickListener(this);
		btn[1].setOnClickListener(this);
		btn[2].setOnClickListener(this);
		btn[3].setOnClickListener(this);
		btn[4].setOnClickListener(this);
		btn[5].setOnClickListener(this);
		btn[6].setOnClickListener(this);
		btn[7].setOnClickListener(this);
		btn[8].setOnClickListener(this);
		btn[9].setOnClickListener(this);
		btn[10].setOnClickListener(this);
		btn[11].setOnClickListener(this);
		btn[12].setOnClickListener(this);
		btn[13].setOnClickListener(this);
		btn[14].setOnClickListener(this);
		btn[15].setOnClickListener(this);
		btn[16].setOnClickListener(this);
		btn[17].setOnClickListener(this);
		btn[18].setOnClickListener(this);
		btn[19].setOnClickListener(this);
		// GetDataList();
		Showdetail();
		try {
			Intent intent = this.getIntent(); // 获取已有的intent对象
			Bundle bundle = intent.getExtras(); // 获取intent里面的bundle对象

			Title = bundle.getString("Title");
			Id = bundle.getString("keyid");
			ticket = bundle.getInt("ticket");
			user = bundle.getIntArray("user");
			score = bundle.getInt("score");
			answer = bundle.getString("anwser");
		} catch (Exception e) {
			// TODO: handle exception
		}
		text_title.setText(Title);
		GetData();
	}

	private void GetData() {
		// TODO Auto-generated method stub

		// TODO Auto-generated method stub
		final ArrayList ArrayValues = new ArrayList();
		ArrayValues.add(new BasicNameValuePair("ticket", "" + ticket));
		ArrayValues.add(new BasicNameValuePair("datakey", "" + Id));
		new Thread(new Runnable() { // 开启线程上传文件
			@Override
			public void run() {
				String DueData = "";
				DueData = HttpGetData.GetData("api/pubshare/testPaper/getPaper", ArrayValues);
				Message msg = new Message();
				msg.obj = DueData;
				msg.what = GET_DUE_DATA;
				uiHandler.sendMessage(msg);
			}
		}).start();

	}

	protected void GetDataDueData(Object obj) {

		// TODO Auto-generated method stub
		String Type = null;
		String Data = null;
		String pager = null;
		try {
			JSONObject demoJson = new JSONObject(obj.toString());
			Type = demoJson.getString("type");

			Data = demoJson.getString("data");
			if (Type.equals(GET_SUCCESS_RESULT)) {

				GetDataList(Data);
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

	private void GetDataList(String data) {

		try {

			JSONObject json_data = null;

			json_data = new JSONObject(data);

			String Subs = json_data.getString("subs");
			getdata(Subs);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void getdata(String subs) {
		// TODO Auto-generated method stub
		list.clear();
		JSONArray jArray = null;
		try {
			jArray = new JSONArray(subs);
			JSONObject json_data = null;
			if (jArray.length() == 0) {
				Toast.makeText(getApplicationContext(), "无数据", Toast.LENGTH_SHORT).show();

			} else {
				for (int i = 0; i < jArray.length(); i++) {
					json_data = jArray.getJSONObject(i);

					// json_data = json_data.getJSONObject("data");
					ExamTopicModel listinfo = new ExamTopicModel();

					listinfo.setId(json_data.getString("keyid"));
					listinfo.setTopic((i + 1) + "、" + json_data.getString("title"));
					listinfo.setScore(json_data.getInt("score"));
					listinfo.setDetail(json_data.getString("analysis"));
					JSONArray jArray1 = new JSONArray(json_data.getString("subs"));
					listinfo.setAString("");
					listinfo.setBString("");
					listinfo.setCString("");
					listinfo.setDString("");
					listinfo.setAid("");
					listinfo.setBid("");
					listinfo.setCid("");
					listinfo.setDid("");
					String anwser = "";
					anwser = json_data.getString("answer");
					listinfo.setRightAnswer(0);

					if (anwser.equals("A")) {
						listinfo.setRightAnswer(1);
						listinfo.setUserAnswer(1);
					} else if (anwser.equals("B")) {
						listinfo.setRightAnswer(2);
						listinfo.setUserAnswer(2);
					} else if (anwser.equals("C")) {
						listinfo.setRightAnswer(3);
						listinfo.setUserAnswer(3);
					} else if (anwser.equals("D")) {
						listinfo.setRightAnswer(4);
						listinfo.setUserAnswer(4);
					}
					listinfo.setUserAnswer(user[i]);
					for (int j = 0; j < jArray1.length(); j++) {
						JSONObject json_data1 = jArray1.getJSONObject(j);
						if (j == 0) {
							listinfo.setAString(json_data1.getString("code") + "、" + json_data1.getString("content"));
							listinfo.setAid(json_data1.getString("keyid"));
						} else if (j == 1) {
							listinfo.setBString(json_data1.getString("code") + "、" + json_data1.getString("content"));
							listinfo.setBid(json_data1.getString("keyid"));
						} else if (j == 2) {
							listinfo.setCString(json_data1.getString("code") + "、" + json_data1.getString("content"));
							listinfo.setCid(json_data1.getString("keyid"));
						} else if (j == 3) {
							listinfo.setDString(json_data1.getString("code") + "、" + json_data1.getString("content"));
							listinfo.setDid(json_data1.getString("keyid"));
						}
					}
					list.add(listinfo);

				}
			}

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		topicnumber = list.size();
		for (int j = 0; j < list.size(); j++) {
			// Log.e("answer", answer);
			ExamTopicModel examTopicModel = list.get(j);
			String answer = getuseranswer(examTopicModel.getId());
			Log.e("answer", answer + "wuxc");
			if (answer.equals("A")) {

				examTopicModel.setUserAnswer(1);
			} else if (answer.equals("B")) {

				examTopicModel.setUserAnswer(2);
			} else if (answer.equals("C")) {

				examTopicModel.setUserAnswer(3);
			} else if (answer.equals("D")) {

				examTopicModel.setUserAnswer(4);
			}
		}
		Showdetail();
	}

	private String getuseranswer(String aid) {
		// TODO Auto-generated method stub
		String temp = "";
		try {

			JSONObject jsonObject = new JSONObject(answer);
			Log.e("jsonObject", ""+jsonObject);
			try {
				temp = jsonObject.getString(aid);
			} catch (Exception e) {
				// TODO: handle exception
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		return temp;
	}

	private void Showdetail() {
		// TODO Auto-generated method stub
		int mark = 0;
		for (int i = 0; i < 20; i++) {
			btn[i].setBackgroundResource(R.drawable.shape_5_gery_small);
			btn[i].setTextColor(Color.parseColor("#000000"));
			btn[i].setText("第" + (i + 1) + "题");
		}
		for (int i = 0; i < list.size(); i++) {
			ExamTopicModel examTopicModel = list.get(i);
			if (examTopicModel.getUserAnswer() == examTopicModel.getRightAnswer()) {
				mark += 1;
				btn[i].setBackgroundResource(R.drawable.shape_5_gery_small);
				btn[i].setTextColor(Color.parseColor("#000000"));
				btn[i].setText("第" + (i + 1) + "题");
			} else {
				btn[i].setBackgroundResource(R.drawable.shape_5_red);
				btn[i].setTextColor(Color.parseColor("#ffffff"));
				btn[i].setText("第" + (i + 1) + "题");
			}
		}
		Log.e("mark", mark + "");
		int temp = score / 5;
		image_mark.setImageResource(imagemark[temp]);
		text_mark.setText(score + "");

	}

	private void GetDataList() {
		// TODO Auto-generated method stub
		list.clear();
		for (int i = 0; i < 20; i++) {
			ExamTopicModel examTopicModel = new ExamTopicModel();
			examTopicModel.setAString("A、党员设岗定责和承诺践诺制度");
			examTopicModel.setBString("B、党员示范岗和党员责任区制度");
			examTopicModel.setCString("C、党员挂牌上岗、亮明身份制度");
			examTopicModel.setDString("D、党员到社区报到、直接联系服务群众制度");
			examTopicModel.setTopic(i + "【单选题】开展“两学一做”，针对不同群众党员实际情况，提出具体要求。那么，在窗口单位和服务行业中，重点落实（  ）。");
			examTopicModel.setUserAnswer((int) (Math.random() * 4 + 1));
			examTopicModel.setRightAnswer((int) (Math.random() * 4 + 1));
			list.add(examTopicModel);
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.image_back:
			finish();
			break;
		case R.id.btn_1:
			ShowDetail(1);
			break;
		case R.id.btn_2:
			ShowDetail(2);
			break;
		case R.id.btn_3:
			ShowDetail(3);
			break;
		case R.id.btn_4:
			ShowDetail(4);
			break;
		case R.id.btn_5:
			ShowDetail(5);
			break;
		case R.id.btn_6:
			ShowDetail(6);
			break;
		case R.id.btn_7:
			ShowDetail(7);
			break;
		case R.id.btn_8:
			ShowDetail(8);
			break;
		case R.id.btn_9:
			ShowDetail(9);
			break;
		case R.id.btn_10:
			ShowDetail(10);
			break;
		case R.id.btn_11:
			ShowDetail(11);
			break;
		case R.id.btn_12:
			ShowDetail(12);
			break;
		case R.id.btn_13:
			ShowDetail(13);
			break;
		case R.id.btn_14:
			ShowDetail(14);
			break;
		case R.id.btn_15:
			ShowDetail(15);
			break;
		case R.id.btn_16:
			ShowDetail(16);
			break;
		case R.id.btn_17:
			ShowDetail(17);
			break;
		case R.id.btn_18:
			ShowDetail(18);
			break;
		case R.id.btn_19:
			ShowDetail(19);
			break;
		case R.id.btn_20:
			ShowDetail(20);
			break;

		default:
			break;
		}

	}

	private void ShowDetail(int i) {
		// TODO Auto-generated method stub
		if (i > topicnumber) {
			Toast.makeText(getApplicationContext(), "无本题数据", Toast.LENGTH_SHORT).show();

		} else {
			Intent intent = new Intent();
			intent.setClass(getApplicationContext(), ExamDetailActivity.class);
			Bundle bundle = new Bundle();
			bundle.putInt("Number", i);
			bundle.putString("keyid", Id);
			bundle.putInt("ticket", ticket);
			for (int j = 0; j < list.size(); j++) {

				ExamTopicModel examTopicModel = list.get(j);
				user[j] = examTopicModel.getUserAnswer();
			}
			bundle.putIntArray("user", user);
			intent.putExtras(bundle);
			startActivity(intent);
		}

	}

}
