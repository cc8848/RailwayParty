package wuxc.single.railwayparty.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.support.v4.app.Fragment;
import android.view.View.OnClickListener;
import wuxc.single.railwayparty.R;

public class ExamFragment extends Fragment implements OnClickListener {
	private View view;// ª∫¥ÊFragment view
	private Button btn_exam;
	private TextView text_exam;

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
		}

		return view;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_exam:
			Intent intent = new Intent();
			intent.setClass(getActivity(), TestActivity.class);
			startActivity(intent);
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
