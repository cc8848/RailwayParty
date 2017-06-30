package wuxc.single.railwayparty.main;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import wuxc.single.railwayparty.R;
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
		GetDataList();
		Showdetail();
	}

	private void Showdetail() {
		// TODO Auto-generated method stub
		int mark = 0;
		for (int i = 0; i < 20; i++) {
			ExamTopicModel examTopicModel = list.get(i);
			if (examTopicModel.getUserAnswer() == examTopicModel.getRightAnswer()) {
				mark += 1;
				btn[i].setBackgroundResource(R.drawable.shape_5_gery_small);
				btn[i].setTextColor(Color.parseColor("#000000"));
				btn[i].setText("第" +( i + 1 )+ "题");
			} else {
				btn[i].setBackgroundResource(R.drawable.shape_5_red);
				btn[i].setTextColor(Color.parseColor("#ffffff"));
				btn[i].setText("第" + (i + 1 )+ "题");
			}
		}
		Log.e("mark", mark + "");
		image_mark.setImageResource(imagemark[mark]);
		text_mark.setText(mark * 5 + "");

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
		Intent intent = new Intent();
		intent.setClass(getApplicationContext(), ExamDetailActivity.class);
		Bundle bundle = new Bundle();
		bundle.putInt("Number", i);

		intent.putExtras(bundle);
		startActivity(intent);

	}

}
