package wuxc.single.railwayparty.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

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
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import wuxc.single.railwayparty.R;
import wuxc.single.railwayparty.internet.HttpGetData;
import wuxc.single.railwayparty.model.ExamTopicModel;
import wuxc.single.railwayparty.model.MemberModel;

public class ExamActivity extends Activity implements OnClickListener {
	private TextView text_title;
	private TextView text_name;
	private TextView text_time;
	private TextView text_topic_main;
	private Button btn_last;
	private Button btn_next;
	private LinearLayout lin_1;
	private LinearLayout lin_2;
	private ImageView exam_top_1;
	private ImageView exam_top_2;
	private ImageView exam_top_3;
	private ImageView exam_top_4;
	private ImageView exam_top_5;
	private ImageView exam_top_6;
	private ImageView exam_top_7;
	private ImageView exam_top_8;
	private ImageView exam_top_9;
	private ImageView exam_top_10;
	private ImageView exam_top_11;
	private ImageView exam_top_12;
	private ImageView exam_top_13;
	private ImageView exam_top_14;
	private ImageView exam_top_15;
	private ImageView exam_top_16;
	private ImageView exam_top_17;
	private ImageView exam_top_18;
	private ImageView exam_top_19;
	private ImageView exam_top_20;
	private ImageView[] exam_top = { exam_top_1, exam_top_2, exam_top_3, exam_top_4, exam_top_5, exam_top_6, exam_top_7,
			exam_top_8, exam_top_9, exam_top_10, exam_top_11, exam_top_12, exam_top_13, exam_top_14, exam_top_15,
			exam_top_16, exam_top_17, exam_top_18, exam_top_19, exam_top_20 };
	private int[] er = { R.drawable.er1, R.drawable.er2, R.drawable.er3, R.drawable.er4, R.drawable.er5, R.drawable.er6,
			R.drawable.er7, R.drawable.er8, R.drawable.er9, R.drawable.er10, R.drawable.er11, R.drawable.er12,
			R.drawable.er13, R.drawable.er14, R.drawable.er15, R.drawable.er16, R.drawable.er17, R.drawable.er18,
			R.drawable.er19, R.drawable.er20 };
	private int[] white = { R.drawable.er1, R.drawable.w2, R.drawable.w3, R.drawable.w4, R.drawable.w5, R.drawable.w6,
			R.drawable.w7, R.drawable.w8, R.drawable.w9, R.drawable.w10, R.drawable.w11, R.drawable.w12, R.drawable.w13,
			R.drawable.w14, R.drawable.w15, R.drawable.w16, R.drawable.w17, R.drawable.w18, R.drawable.w19,
			R.drawable.w20 };
	private LinearLayout lin_a;
	private ImageView image_a;
	private TextView topic_a;
	private LinearLayout lin_b;
	private ImageView image_b;
	private TextView topic_b;
	private LinearLayout lin_c;
	private ImageView image_c;
	private TextView topic_c;
	private LinearLayout lin_d;
	private ImageView image_d;
	private TextView topic_d;
	private List<ExamTopicModel> list = new ArrayList<ExamTopicModel>();
	private List<ExamTopicModel> listall = new ArrayList<ExamTopicModel>();
	private int Number = 1;
	private int screenwidth = 0;
	private float scale = 0;
	private float scalepx = 0;
	private float dp = 0;
	private int recLen = 600;
	private int timelength = 60;
	Timer timer = new Timer();
	private String Title;
	private String Id;
	private int ticket = 0;
	private static final String GET_SUCCESS_RESULT = "success";
	private static final String GET_FAIL_RESULT = "fail";
	private static final int GET_DUE_DATA = 6;
	private int topicnumber = 2;
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
		setContentView(R.layout.wuxc_activity_exam);
		ImageView image_back = (ImageView) findViewById(R.id.image_back);
		image_back.setOnClickListener(this);
		screenwidth = getWindow().getWindowManager().getDefaultDisplay().getWidth();
		DisplayMetrics mMetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(mMetrics);
		scale = getResources().getDisplayMetrics().density;
		// Log.e("mMetrics", mMetrics.toString() + "scale=" + scale + "0.5f"
		// +
		// 0.5f);
		dp = screenwidth / scale + 0.5f;
		scalepx = screenwidth / dp;
		text_title = (TextView) findViewById(R.id.text_title);
		text_name = (TextView) findViewById(R.id.text_name);
		text_time = (TextView) findViewById(R.id.text_time);
		text_topic_main = (TextView) findViewById(R.id.text_topic_main);
		btn_last = (Button) findViewById(R.id.btn_last);
		btn_next = (Button) findViewById(R.id.btn_next);
		lin_1 = (LinearLayout) findViewById(R.id.lin_1);
		lin_2 = (LinearLayout) findViewById(R.id.lin_2);
		lin_a = (LinearLayout) findViewById(R.id.lin_a);
		image_a = (ImageView) findViewById(R.id.image_a);
		topic_a = (TextView) findViewById(R.id.topic_a);
		lin_b = (LinearLayout) findViewById(R.id.lin_b);
		image_b = (ImageView) findViewById(R.id.image_b);
		topic_b = (TextView) findViewById(R.id.topic_b);
		lin_c = (LinearLayout) findViewById(R.id.lin_c);
		image_c = (ImageView) findViewById(R.id.image_c);
		topic_c = (TextView) findViewById(R.id.topic_c);
		lin_d = (LinearLayout) findViewById(R.id.lin_d);
		image_d = (ImageView) findViewById(R.id.image_d);
		topic_d = (TextView) findViewById(R.id.topic_d);
		exam_top[0] = (ImageView) findViewById(R.id.exam_top_1);
		exam_top[1] = (ImageView) findViewById(R.id.exam_top_2);
		exam_top[2] = (ImageView) findViewById(R.id.exam_top_3);
		exam_top[3] = (ImageView) findViewById(R.id.exam_top_4);
		exam_top[4] = (ImageView) findViewById(R.id.exam_top_5);
		exam_top[5] = (ImageView) findViewById(R.id.exam_top_6);
		exam_top[6] = (ImageView) findViewById(R.id.exam_top_7);
		exam_top[7] = (ImageView) findViewById(R.id.exam_top_8);
		exam_top[8] = (ImageView) findViewById(R.id.exam_top_9);
		exam_top[9] = (ImageView) findViewById(R.id.exam_top_10);
		exam_top[10] = (ImageView) findViewById(R.id.exam_top_11);
		exam_top[11] = (ImageView) findViewById(R.id.exam_top_12);
		exam_top[12] = (ImageView) findViewById(R.id.exam_top_13);
		exam_top[13] = (ImageView) findViewById(R.id.exam_top_14);
		exam_top[14] = (ImageView) findViewById(R.id.exam_top_15);
		exam_top[15] = (ImageView) findViewById(R.id.exam_top_16);
		exam_top[16] = (ImageView) findViewById(R.id.exam_top_17);
		exam_top[17] = (ImageView) findViewById(R.id.exam_top_18);
		exam_top[18] = (ImageView) findViewById(R.id.exam_top_19);
		exam_top[19] = (ImageView) findViewById(R.id.exam_top_20);
		btn_last.setOnClickListener(this);
		btn_next.setOnClickListener(this);
		lin_a.setOnClickListener(this);
		lin_b.setOnClickListener(this);
		lin_c.setOnClickListener(this);
		lin_d.setOnClickListener(this);
		initheight();
		// GetDataList();
		// showTop();
		// showtopic();
		Intent intent = this.getIntent(); // 获取已有的intent对象
		Bundle bundle = intent.getExtras(); // 获取intent里面的bundle对象

		Title = bundle.getString("Title");
		Id = bundle.getString("keyid");
		ticket = bundle.getInt("ticket");
		text_title.setText(Title);
		text_time.setText("60:00");
		GetData();
		if (Number == 1) {
			btn_last.setVisibility(View.GONE);
		} else {
			btn_last.setVisibility(View.VISIBLE);
		}
		timer.schedule(task, 1000, 1000); // timeTask
	}

	final Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 1:
				settime();
				if (recLen < 0) {
					timer.cancel();
					text_time.setVisibility(View.GONE);
				}
			}
		}
	};

	TimerTask task = new TimerTask() {
		@Override
		public void run() {
			recLen--;
			Message message = new Message();
			message.what = 1;
			handler.sendMessage(message);
		}
	};

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
			timelength = json_data.getInt("timeLength");
//			recLen = timelength * 60;
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
					listinfo.setTopic(json_data.getString("title"));
					listinfo.setScore(json_data.getInt("score"));
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
					listinfo.setUserAnswer(0);
					if (anwser.equals("A")) {
						listinfo.setRightAnswer(1);
					} else if (anwser.equals("B")) {
						listinfo.setRightAnswer(2);
					} else if (anwser.equals("C")) {
						listinfo.setRightAnswer(3);
					} else if (anwser.equals("D")) {
						listinfo.setRightAnswer(4);
					}
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
					listall.add(listinfo);

				}
			}

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (listall.size() <= 20) {
			list = listall;
		} else {
			for (int i = 0; i < 20; i++) {
				int number = (int) (Math.random() * (listall.size()));
				ExamTopicModel listinfo = listall.get(number);
				list.add(listinfo);
				listall.remove(number);
			}
		}
		topicnumber = list.size();
		showTop();
		showtopic();
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
			examTopicModel.setUserAnswer(0);
			list.add(examTopicModel);
		}
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

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		// timer.cancel();
	}

	protected void settime() {
		// TODO Auto-generated method stub
		int minute = recLen / 60;
		int second = recLen - 60 * minute;
		String str_minute = "";
		String str_second = "";
		if (minute < 10) {
			str_minute = "0" + minute;
		} else {
			str_minute = "" + minute;
		}

		if (second < 10) {
			str_second = "0" + second;
		} else {
			str_second = "" + second;
		}
		text_time.setText(str_minute + ":" + str_second);
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		timer.cancel();
	}

	private void initheight() {
		// TODO Auto-generated method stub
		LinearLayout.LayoutParams LayoutParams = (android.widget.LinearLayout.LayoutParams) lin_1.getLayoutParams();
		LayoutParams.height = (int) (screenwidth / 20 + 20 * scalepx);
		lin_1.setLayoutParams(LayoutParams);

		LinearLayout.LayoutParams LayoutParams2 = (android.widget.LinearLayout.LayoutParams) lin_2.getLayoutParams();
		LayoutParams2.height = (int) (screenwidth / 20 + 20 * scalepx);
		lin_2.setLayoutParams(LayoutParams2);
		for (int i = 0; i < 20; i++) {
			LinearLayout.LayoutParams LayoutParams3 = (android.widget.LinearLayout.LayoutParams) exam_top[i]
					.getLayoutParams();
			LayoutParams3.height = (int) (screenwidth / 20);
			LayoutParams3.width = (int) (screenwidth / 20);
			exam_top[i].setLayoutParams(LayoutParams3);
		}
	}

	private void showTop() {
		// TODO Auto-generated method stub
		for (int i = 0; i < Number; i++) {
			exam_top[i].setImageResource(er[i]);
		}
		for (int i = Number; i < 20; i++) {
			exam_top[i].setImageResource(white[i]);
		}
	}

	private void showtopic() {
		ExamTopicModel examTopicModel = list.get(Number - 1);
		if (examTopicModel.getAid().equals("")) {
			lin_a.setVisibility(View.GONE);
		} else {
			lin_a.setVisibility(View.VISIBLE);
		}
		if (examTopicModel.getBid().equals("")) {
			lin_b.setVisibility(View.GONE);
		} else {
			lin_b.setVisibility(View.VISIBLE);
		}
		if (examTopicModel.getCid().equals("")) {
			lin_c.setVisibility(View.GONE);
		} else {
			lin_c.setVisibility(View.VISIBLE);
		}
		if (examTopicModel.getDid().equals("")) {
			lin_d.setVisibility(View.GONE);
		} else {
			lin_d.setVisibility(View.VISIBLE);
		}
		topic_a.setText(examTopicModel.getAString());
		topic_b.setText(examTopicModel.getBString());
		topic_c.setText(examTopicModel.getCString());
		topic_d.setText(examTopicModel.getDString());

		topic_a.setTextColor(Color.parseColor("#000000"));
		topic_b.setTextColor(Color.parseColor("#000000"));
		topic_c.setTextColor(Color.parseColor("#000000"));
		topic_d.setTextColor(Color.parseColor("#000000"));
		text_topic_main.setText(examTopicModel.getTopic());
		image_a.setImageResource(R.drawable.icon_radio1);
		image_b.setImageResource(R.drawable.icon_radio1);
		image_c.setImageResource(R.drawable.icon_radio1);
		image_d.setImageResource(R.drawable.icon_radio1);
		if (examTopicModel.getUserAnswer() == 0) {

		} else if (examTopicModel.getUserAnswer() == 1) {
			image_a.setImageResource(R.drawable.icon_radio);
			topic_a.setTextColor(Color.parseColor("#cc0502"));
		} else if (examTopicModel.getUserAnswer() == 2) {
			image_b.setImageResource(R.drawable.icon_radio);
			topic_b.setTextColor(Color.parseColor("#cc0502"));
		} else if (examTopicModel.getUserAnswer() == 3) {
			image_c.setImageResource(R.drawable.icon_radio);
			topic_c.setTextColor(Color.parseColor("#cc0502"));
		} else if (examTopicModel.getUserAnswer() == 4) {
			image_d.setImageResource(R.drawable.icon_radio);
			topic_d.setTextColor(Color.parseColor("#cc0502"));
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.image_back:
			finish();
			break;
		case R.id.lin_a:
			ExamTopicModel examTopicModela = list.get(Number - 1);
			examTopicModela.setUserAnswer(1);
			showtopic();
			break;
		case R.id.lin_b:
			ExamTopicModel examTopicModelb = list.get(Number - 1);
			examTopicModelb.setUserAnswer(2);
			showtopic();
			break;
		case R.id.lin_c:
			ExamTopicModel examTopicModelc = list.get(Number - 1);
			examTopicModelc.setUserAnswer(3);
			showtopic();
			break;
		case R.id.lin_d:
			ExamTopicModel examTopicModeld = list.get(Number - 1);
			examTopicModeld.setUserAnswer(4);
			showtopic();
			break;
		case R.id.btn_last:
			if (Number == 1) {

			} else {
				Number--;
				if (Number == 1) {
					btn_last.setVisibility(View.GONE);
				} else {
					btn_last.setVisibility(View.VISIBLE);
				}
				if (Number == topicnumber) {
					btn_next.setText("提交");
				} else {
					btn_next.setText("下一题");
				}
				showtopic();
				showTop();
			}
			break;
		case R.id.btn_next:
			if (Number == topicnumber) {
				ExamTopicModel examTopicModelnext = list.get(Number - 1);

				if (examTopicModelnext.getUserAnswer() != 0) {
					if (recLen <= 0) {
						Toast.makeText(getApplicationContext(), "超时！答案无法提交", Toast.LENGTH_SHORT).show();

					} else {
						int totalsccore = 0;
						JSONObject anwser = new JSONObject();
						JSONObject right = new JSONObject();
						int[] user = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
						for (int i = 0; i < list.size(); i++) {
							ExamTopicModel examTopicModel = list.get(i);
							String userAanswer = "";
							if (examTopicModel.getUserAnswer() == 1) {
								userAanswer = "A";
							} else if (examTopicModel.getUserAnswer() == 2) {
								userAanswer = "B";
							} else if (examTopicModel.getUserAnswer() == 3) {
								userAanswer = "C";
							} else if (examTopicModel.getUserAnswer() == 4) {
								userAanswer = "D";
							}
							user[i] = examTopicModel.getUserAnswer();
							String rightAanswer = "";
							if (examTopicModel.getRightAnswer() == 1) {
								rightAanswer = "A";
							} else if (examTopicModel.getRightAnswer() == 2) {
								rightAanswer = "B";
							} else if (examTopicModel.getRightAnswer() == 3) {
								rightAanswer = "C";
							} else if (examTopicModel.getRightAnswer() == 4) {
								rightAanswer = "D";
							}
							try {
								right.put(examTopicModel.getId(), rightAanswer);
							} catch (JSONException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							try {
								anwser.put(examTopicModel.getId(), userAanswer);
							} catch (JSONException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							if (examTopicModel.getRightAnswer() == examTopicModel.getUserAnswer()) {
								totalsccore = totalsccore + examTopicModel.getScore();
							} else {
								totalsccore = totalsccore + 0;
							}
							Log.e("examTopicModel.getRightAnswer()", "" + examTopicModel.getRightAnswer());
							Log.e("examTopicModel.getUserAnswer()", "" + examTopicModel.getUserAnswer());
						}
						final ArrayList ArrayValues = new ArrayList();
						ArrayValues.add(new BasicNameValuePair("ticket", "" + ticket));
						ArrayValues.add(new BasicNameValuePair("examResultDto.par_keyid", "" + Id));
						ArrayValues.add(new BasicNameValuePair("examResultDto.score", "" + totalsccore));
						ArrayValues.add(new BasicNameValuePair("examResultDto.answer", "" + anwser));
						Log.e("anwser", "" + anwser);
						new Thread(new Runnable() { // 开启线程上传文件
							@Override
							public void run() {
								String DueData = "";
								DueData = HttpGetData.GetData("api/pubshare/examResult/save", ArrayValues);
								// Message msg = new Message();
								// msg.obj = DueData;
								// msg.what = GET_DUE_DATA;
								// uiHandler.sendMessage(msg);
							}
						}).start();
						finish();
						Intent intent1 = new Intent();
						intent1.setClass(getApplicationContext(), ExamResultActivity.class);
						Bundle bundle = new Bundle();
						bundle.putInt("score", totalsccore);
						Log.e("totalsccore", "" + totalsccore);
						bundle.putString("Title", Title);
						bundle.putString("keyid", Id);
						bundle.putString("anwser", anwser.toString());
						bundle.putInt("ticket", ticket);
						bundle.putIntArray("user", user);
						intent1.putExtras(bundle);
						startActivity(intent1);
					}
				} else {
					Toast.makeText(getApplicationContext(), "本题尚未作答", Toast.LENGTH_SHORT).show();
				}
			} else if (Number >= list.size()) {
				Toast.makeText(getApplicationContext(), "试题个数错误", Toast.LENGTH_SHORT).show();

			} else {
				ExamTopicModel examTopicModelnext = list.get(Number - 1);
				if (examTopicModelnext.getUserAnswer() != 0) {
					Number++;
					if (Number == topicnumber) {
						btn_next.setText("提交");
					} else {
						btn_next.setText("下一题");
					}
					if (Number == 1) {
						btn_last.setVisibility(View.GONE);
					} else {
						btn_last.setVisibility(View.VISIBLE);
					}
					showtopic();
					showTop();
				} else {
					Toast.makeText(getApplicationContext(), "本题尚未作答", Toast.LENGTH_SHORT).show();
				}

			}
			break;
		default:
			break;
		}
	}

}
