package wuxc.single.railwayparty.main;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import wuxc.single.railwayparty.R;
import wuxc.single.railwayparty.model.ExamTopicModel;

public class ExamDetailActivity extends Activity implements OnClickListener {
	private TextView text_topic_main;
	private Button btn_last;
	private Button btn_next;
	private int[] er = { R.drawable.er1, R.drawable.er2, R.drawable.er3, R.drawable.er4, R.drawable.er5, R.drawable.er6,
			R.drawable.er7, R.drawable.er8, R.drawable.er9, R.drawable.er10, R.drawable.er11, R.drawable.er12,
			R.drawable.er13, R.drawable.er14, R.drawable.er15, R.drawable.er16, R.drawable.er17, R.drawable.er18,
			R.drawable.er19, R.drawable.er20 };
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
	private TextView text_topic_detail;
	private ImageView image_number;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.wuxc_activity_exam_result_detail);
		ImageView image_back = (ImageView) findViewById(R.id.image_back);
		image_back.setOnClickListener(this);
		text_topic_main = (TextView) findViewById(R.id.text_topic_main);
		btn_last = (Button) findViewById(R.id.btn_last);
		btn_next = (Button) findViewById(R.id.btn_next);
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
		btn_last.setOnClickListener(this);
		btn_next.setOnClickListener(this);
		lin_a.setOnClickListener(this);
		lin_b.setOnClickListener(this);
		lin_c.setOnClickListener(this);
		lin_d.setOnClickListener(this);
		Intent intent = this.getIntent(); // 获取已有的intent对象
		Bundle bundle = intent.getExtras(); // 获取intent里面的bundle对象
		Number = bundle.getInt("Number");
		text_topic_detail = (TextView) findViewById(R.id.text_topic_detail);
		image_number = (ImageView) findViewById(R.id.image_number);
		if (Number == 1) {
			btn_last.setVisibility(View.GONE);
		} else {
			btn_last.setVisibility(View.VISIBLE);
		}
		if (Number == 20) {
			btn_next.setVisibility(View.GONE);
		} else {
			btn_next.setVisibility(View.VISIBLE);
		}
		GetDataList();
		showTop();
		showtopic();
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
		text_topic_detail.setText(examTopicModel.getDetail());
		SpannableStringBuilder style = new SpannableStringBuilder(examTopicModel.getDetail());
		style.setSpan(new ForegroundColorSpan(Color.parseColor(getString(R.color.brown))), 0, 3,
				Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		text_topic_detail.setText(style);
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
		if (examTopicModel.getRightAnswer() == 0) {

		} else if (examTopicModel.getRightAnswer() == 1) {
			topic_a.setTextColor(Color.parseColor(getString(R.color.brown)));
		} else if (examTopicModel.getRightAnswer() == 2) {
			topic_b.setTextColor(Color.parseColor(getString(R.color.brown)));
		} else if (examTopicModel.getRightAnswer() == 3) {
			topic_c.setTextColor(Color.parseColor(getString(R.color.brown)));
		} else if (examTopicModel.getRightAnswer() == 4) {
			topic_d.setTextColor(Color.parseColor(getString(R.color.brown)));
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
			examTopicModel.setUserAnswer((int) (Math.random() * 4 + 1));
			examTopicModel.setRightAnswer((int) (Math.random() * 4 + 1));
			examTopicModel.setDetail("解析：开展“两学一做”，针对不同群众党员实际情况，提出具体要求。那么，在窗口单位和服务行业中，重点落实党员挂牌上岗、亮明身份制度");
			list.add(examTopicModel);
		}
	}

	private void showTop() {
		// TODO Auto-generated method stub
		image_number.setImageResource(er[Number - 1]);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.image_back:
			finish();
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
					btn_next.setVisibility(View.GONE);
				} else {
					btn_next.setVisibility(View.VISIBLE);
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
						btn_next.setVisibility(View.GONE);
					} else {
						btn_next.setVisibility(View.VISIBLE);
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
