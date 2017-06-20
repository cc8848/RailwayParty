package wuxc.single.railwayparty.fragment;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import wuxc.single.railwayparty.R;
import wuxc.single.railwayparty.adapter.SchoolAdapter;
import wuxc.single.railwayparty.layout.dialogselecttwo;
import wuxc.single.railwayparty.model.SchoolModel;

public class BuildFragment6 extends Fragment implements OnTouchListener, OnClickListener, OnItemClickListener {
	private LinearLayout lin_title;
	private TextView text_title;
	private TextView text_1;
	private TextView text_2;
	private TextView text_3;
	private ListView ListData;
	List<SchoolModel> list = new ArrayList<SchoolModel>();
	private static SchoolAdapter mAdapter;
	private int firstItemIndex = 0;
	private int lastItemIndex = 0;
	private float startY = 0;
	private float startYfoot = 0;
	private boolean isRecored;
	private boolean isRecoredfoot;
	private int pageSize = 10;
	private int totalPage = 10;
	private int curPage = 1;
	private final static int RATIO = 2;
	private TextView headTextView = null;
	private View view;// 缓存Fragment view
	private boolean[] read = { false, false, true, true, true, true, true, true, true, true, true };
	private int[] headimg = { R.drawable.ppt, R.drawable.ppt, R.drawable.video, R.drawable.video, R.drawable.video,
			R.drawable.ppt, R.drawable.ppt, R.drawable.ppt, R.drawable.video, R.drawable.video, R.drawable.ppt };
	private int[] type = { R.drawable.ppt_bg, R.drawable.ppt_bg, R.drawable.video_bg, R.drawable.video_bg,
			R.drawable.video_bg, R.drawable.ppt_bg, R.drawable.ppt_bg, R.drawable.ppt_bg, R.drawable.video_bg,
			R.drawable.video_bg, R.drawable.ppt };
	private boolean[] bi = { true, true, false, false, false, false, false, false, false, false, false, false, false,
			false, false, false, false, false, false, false };

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
			view = inflater.inflate(R.layout.wuxc_fragment_build_6, container, false);
			lin_title = (LinearLayout) view.findViewById(R.id.lin_title);
			text_title = (TextView) view.findViewById(R.id.text_title);
			text_1 = (TextView) view.findViewById(R.id.text_1);
			text_2 = (TextView) view.findViewById(R.id.text_2);
			text_3 = (TextView) view.findViewById(R.id.text_3);
			lin_title.setOnClickListener(this);
			text_1.setOnClickListener(this);
			text_2.setOnClickListener(this);
			text_3.setOnClickListener(this);
			clearcolor();
			initview(view);
			setonclicklistener();
			setheadtextview();

			getdatalist(curPage);
		}

		return view;
	}

	private void initview(View view2) {
		// TODO Auto-generated method stub
		ListData = (ListView) view.findViewById(R.id.list_data);
	}

	private void setonclicklistener() {
		// TODO Auto-generated method stub
		ListData.setOnItemClickListener(this);
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		float tempY = event.getY();
		float tempyfoot = event.getY();
		firstItemIndex = ListData.getFirstVisiblePosition();
		lastItemIndex = ListData.getLastVisiblePosition();
		// Toast.makeText(getActivity(), " lastItemIndex" +
		// lastItemIndex, Toast.LENGTH_SHORT).show();
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
		case MotionEvent.ACTION_MOVE:
			if (!isRecored && (firstItemIndex == 0)) {
				isRecored = true;
				startY = tempY;
			}
			int temp = 1;
			temp = (lastItemIndex) % pageSize;
			if (!isRecoredfoot && (temp == 0)) {
				isRecoredfoot = true;
				startYfoot = tempyfoot;
			}
			break;
		case MotionEvent.ACTION_UP:
		case MotionEvent.ACTION_CANCEL:
			isRecored = false;
			isRecoredfoot = false;
			break;

		default:
			break;
		}

		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			break;
		case MotionEvent.ACTION_UP:
		case MotionEvent.ACTION_CANCEL:
			ListData.setPadding(0, 0, 0, 0);
			if (tempY - startY < 400) {
				ListData.setPadding(0, -100, 0, 0);
			} else {
				curPage = 1;
				Toast.makeText(getActivity(), "正在刷新", Toast.LENGTH_SHORT).show();
				getdatalist(pageSize);
			}
			int temp = 1;
			temp = (lastItemIndex) % pageSize;
			// temp = 0;
			if (temp == 0 && (startYfoot - tempyfoot > 400)) {
				curPage++;
				if (curPage > totalPage) {
					Toast.makeText(getActivity(), " 没有更多了", Toast.LENGTH_SHORT).show();
					// // listinfoagain();
				} else {
					getdatalist(pageSize);
					Toast.makeText(getActivity(), "正在加载下一页", Toast.LENGTH_SHORT).show();
				}

			} else {

			}
			break;
		case MotionEvent.ACTION_MOVE:
			if (isRecored && tempY > startY) {
				ListData.setPadding(0, (int) ((tempY - startY) / RATIO - 100), 0, 0);
			}
			if (isRecoredfoot && startYfoot > tempyfoot) {
				// footTextView.setVisibility(View.VISIBLE);
				ListData.setPadding(0, -100, 0, (int) ((startYfoot - tempyfoot) / RATIO));
			}
			break;

		default:
			break;
		}
		return false;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		// TODO Auto-generated method stub
		// SchoolModel data = list.get(position - 1);
		// Intent intent = new Intent();
		// intent.setClass(getActivity(), SpecialDetailActivity.class);
		// Bundle bundle = new Bundle();
		// bundle.putString("Title", data.getTitle());
		// bundle.putString("detail", data.getDetail());
		// bundle.putString("Time", data.getTime());
		// bundle.putString("Name", "名字");
		// intent.putExtras(bundle);
		// startActivity(intent);
		Toast.makeText(getActivity(), "点击第" + position + "条" + "item", Toast.LENGTH_SHORT).show();
	}

	private void setheadtextview() {
		headTextView = new TextView(getActivity());
		headTextView.setGravity(Gravity.CENTER);
		headTextView.setMinHeight(100);
		headTextView.setText("正在刷新...");
		headTextView.setTypeface(Typeface.DEFAULT_BOLD);
		headTextView.setTextSize(15);
		headTextView.invalidate();
		ListData.addHeaderView(headTextView, null, false);
		ListData.setPadding(0, -100, 0, 0);
		ListData.setOnTouchListener(this);
	}

	private void getdatalist(int arg) {
		if (arg == 1) {
			list.clear();
		}
		// TODO Auto-generated method stub

		try {

			for (int i = 0; i < 10; i++) {

				SchoolModel listinfo = new SchoolModel();

				listinfo.setTitle("杭州地区地铁项目");
				listinfo.setContent("着眼明确基本标准、树立行为规范、逐条逐句通读党章、为人民做表率。");
				listinfo.setNumber(342);
				listinfo.setRead(read[i]);
				listinfo.setImageurl(headimg[i]);
				listinfo.setBi(bi[i]);
				listinfo.setType(type[i]);
				listinfo.setHeadimgUrl("");
				list.add(listinfo);

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (arg == 1) {
			go();
		} else {
			mAdapter.notifyDataSetChanged();
		}

	}

	protected void go() {
		ListData.setPadding(0, -100, 0, 0);
		mAdapter = new SchoolAdapter(getActivity(), list, ListData);
		ListData.setAdapter(mAdapter);
	}

	private void clearcolor() {
		// TODO Auto-generated method stub
		text_1.setTextColor(Color.parseColor("#000000"));
		text_2.setTextColor(Color.parseColor("#000000"));
		text_3.setTextColor(Color.parseColor("#000000"));
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

	}

	@Override
	public void onStart() {
		super.onStart();
	}

	@Override
	public void onResume() {
		super.onResume();
	}

	@Override
	public void onPause() {
		super.onPause();
	}

	@Override
	public void onStop() {
		super.onStop();
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}

	@Override
	public void onDetach() {
		super.onDetach();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.lin_title:
			selecttype();
			break;
		case R.id.text_1:
			clearcolor();
			text_1.setTextColor(Color.parseColor(getString(R.color.main_color)));
			break;
		case R.id.text_2:
			clearcolor();
			text_2.setTextColor(Color.parseColor(getString(R.color.main_color)));
			break;
		case R.id.text_3:
			clearcolor();
			text_3.setTextColor(Color.parseColor(getString(R.color.main_color)));
			break;
		default:
			break;
		}
	}

	private void selecttype() {

		dialogselecttwo.Builder builder = new dialogselecttwo.Builder(getActivity());
		builder.setMessage("选择学员类型");
		builder.setTitle("提示");
		builder.setPositiveButton("政工干部培训", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
				text_title.setText("政工干部\n培训");

			}

		});

		builder.setNegativeButton("党员培训", new android.content.DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
				text_title.setText("党员培训");

			}
		});

		builder.create().show();

	}
}
