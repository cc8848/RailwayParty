package wuxc.single.railwayparty.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import wuxc.single.railwayparty.R;
import wuxc.single.railwayparty.model.ExamTopicModel;

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
	private int Number = 1;
	private int screenwidth = 0;
	private float scale = 0;
	private float scalepx = 0;
	private float dp = 0;
	private int recLen = 0;

	Timer timer = new Timer();

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
		GetDataList();
		showTop();
		showtopic();
		text_time.setText("00:00");
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
			recLen++;
			Message message = new Message();
			message.what = 1;
			handler.sendMessage(message);
		}
	};

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
//		timer.cancel();
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
				if (Number == 20) {
					btn_next.setText("提交");
				} else {
					btn_next.setText("下一题");
				}
				showtopic();
				showTop();
			}
			break;
		case R.id.btn_next:
			if (Number == 20) {
				Toast.makeText(getApplicationContext(), "您的答案已提交", Toast.LENGTH_SHORT).show();
			} else {
				ExamTopicModel examTopicModelnext = list.get(Number - 1);
				if (examTopicModelnext.getUserAnswer() != 0) {
					Number++;
					if (Number == 20) {
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
