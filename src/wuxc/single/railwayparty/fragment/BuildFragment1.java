package wuxc.single.railwayparty.fragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import wuxc.single.railwayparty.R;
import wuxc.single.railwayparty.adapter.BuildAdapter4;
import wuxc.single.railwayparty.adapter.BuildAdapter4.Callback;
import wuxc.single.railwayparty.internet.HttpGetData;
import wuxc.single.railwayparty.model.BuildModel;
import wuxc.single.railwayparty.start.SpecialDetailActivity;
import wuxc.single.railwayparty.start.webview;

public class BuildFragment1 extends Fragment
		implements OnTouchListener, Callback, OnClickListener, OnItemClickListener {
	private ListView ListData;
	List<BuildModel> list = new ArrayList<BuildModel>();
	private static BuildAdapter4 mAdapter;
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
	private String ticket = "";
	private String chn;

	private SharedPreferences PreUserInfo;// 存储个人信息

	private static final String GET_SUCCESS_RESULT = "success";
	private static final String GET_FAIL_RESULT = "fail";
	private static final int GET_DUE_DATA = 6;

	private TextView text_1;
	private TextView text_2;
	private TextView text_3;
	private TextView text_4;
	private TextView text_5;
	private TextView text_6;
	private TextView text_7;
	public String fileClassify = "";

	public Fragment Fragment1;
	private int recLen = 60;

	private SharedPreferences PreForZDWJ;
	private SharedPreferences ItemNumber;
	Timer timer = new Timer();

	public Handler uiHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case GET_DUE_DATA:
				GetDataDueData(msg.obj);

				break;
			case 66:
				GetRecord(msg.obj);
				try {
					Editor edit = PreForZDWJ.edit();
					edit.putBoolean("ZDWJ", true);
					edit.commit();
				} catch (Exception e) {
					// TODO: handle exception
				}
				break;
			case 8:
				curPage = 1;
				fileClassify = "1";
				GetData();
				break;
			default:
				break;
			}
		}

	};

	private void GetRecord(Object obj) {

		// TODO Auto-generated method stub
		String Type = null;
		String Data = null;

		try {
			JSONObject demoJson = new JSONObject(obj.toString());
			Type = demoJson.getString("type");

			Data = demoJson.getString("datas");
			if (Type.equals(GET_SUCCESS_RESULT)) {

				JSONArray jArray = null;
				try {
					jArray = new JSONArray(Data);
					JSONObject json_data = null;
					if (jArray.length() == 0) {
						// / Toast.makeText(getActivity(), "无数据",
						// Toast.LENGTH_SHORT).show();

					} else {
						Editor edit = PreForZDWJ.edit();
						edit.putBoolean("ZDWJ", true);

						for (int i = 0; i < jArray.length(); i++) {

							try {
								json_data = jArray.getJSONObject(i);
								json_data = json_data.getJSONObject("data");
								String keyid = json_data.getString("busKey");
								edit.putBoolean(keyid, true);
							} catch (Exception e) {
								// TODO: handle exception

							}

						}
						edit.commit();
						Editor edit1 = ItemNumber.edit();
						edit1.putInt("ZDWJread", (PreForZDWJ.getAll().size() - 1));
						Log.e("ZDWJread", "" + (PreForZDWJ.getAll().size() - 1));
						edit1.commit();
					}

				} catch (JSONException e) {
					e.printStackTrace();
				}

			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	protected void GetDataDueData(Object obj) {
		Log.e("1111", "22222");
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

	private void GetDataList(String data, int arg) {
		Log.e("1111", "33333");
		if (arg == 1) {
			Log.e("1111", "3344");
			list.clear();
		}
		JSONArray jArray = null;
		try {
			jArray = new JSONArray(data);
			JSONObject json_data = null;
			if (jArray.length() == 0) {
				// / Toast.makeText(getActivity(), "无数据",
				// Toast.LENGTH_SHORT).show();

			} else {
				for (int i = 0; i < jArray.length(); i++) {
					json_data = jArray.getJSONObject(i);
					Log.e("json_data", "" + json_data);
					// JSONObject jsonObject = json_data.getJSONObject("data");
					BuildModel listinfo = new BuildModel();
					try {
						listinfo.setBi(json_data.getInt("iszengread"));
					} catch (Exception e) {
						// TODO: handle exception
						listinfo.setBi(0);
					}
					String date = getdate(json_data.getString("releaseDate"));
					listinfo.setTime(date);
					listinfo.setTitle(json_data.getString("title"));
					listinfo.setId(json_data.getString("keyid"));
					// listinfo.setBackGround(json_data.getString("sacleImage"));
					listinfo.setContent(json_data.getString("summary"));
					listinfo.setSummary(json_data.getString("summary"));
					listinfo.setCont(true);
					listinfo.setGuanzhu("231");
					listinfo.setZan("453");
					listinfo.setImageurl(R.drawable.logo);
					listinfo.setHeadimgUrl(json_data.getString("sacleImage"));
					listinfo.setRead(PreForZDWJ.getBoolean(json_data.getString("keyid"), false));
					try {
						listinfo.setLink(json_data.getString("otherLinks"));
						if (json_data.getString("summary").equals("") || json_data.getString("summary") == null
								|| json_data.getString("summary").equals("null")) {
							listinfo.setContent(json_data.getString("source"));
							listinfo.setCont(false);
						}

					} catch (Exception e) {
						// TODO: handle exception
					}
					list.add(listinfo);

				}
			}

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (arg == 1) {
			go();
		} else {
			mAdapter.notifyDataSetChanged();
		}

	}

	private String getdate(String string) {
		// TODO Auto-generated method stub
		String result = "07-28";
		try {
			String[] bStrings = string.split("-");
			result = bStrings[1] + "-" + bStrings[2];
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

	private void GetPager(String pager) {
		// TODO Auto-generated method stub
		try {
			Log.e("pager", pager);
			JSONObject demoJson = new JSONObject(pager);

			totalPage = demoJson.getInt("totalPage");
			if (fileClassify.equals("")) {
				int totalcount = 0;
				totalcount = demoJson.getInt("totalRecord");
				Log.e("totalcount", "" + totalcount);
				Editor edit = ItemNumber.edit();
				edit.putInt("ZDWJtotal", totalcount);
				edit.commit();
			}

			// BuildFragment buildFragment = new BuildFragment();
			// buildFragment.intnumber();
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
			view = inflater.inflate(R.layout.wuxc_fragment_build_3, container, false);
			initview(view);
			setonclicklistener();
			setheadtextview();
			Fragment1 = this;
			// getdatalist(curPage);
			PreUserInfo = getActivity().getSharedPreferences("UserInfo", Context.MODE_PRIVATE);
			PreForZDWJ = getActivity().getSharedPreferences("ZDWJ", Context.MODE_PRIVATE);
			ItemNumber = getActivity().getSharedPreferences("ItemNumber", Context.MODE_PRIVATE);

			ReadTicket();

			GetData();
			if (!PreForZDWJ.getBoolean("ZDWJ", false)) {
				GetMyReadRecord();
			}
		}

		return view;

	}

	private void GetMyReadRecord() {
		// TODO Auto-generated method stub
		final ArrayList ArrayValues = new ArrayList();
		ArrayValues.add(new BasicNameValuePair("ticket", "" + ticket));
		ArrayValues.add(new BasicNameValuePair("accessRecordDto.classify", "zdwj"));
		ArrayValues.add(new BasicNameValuePair("curPage", "" + 1));
		ArrayValues.add(new BasicNameValuePair("pageSize", "" + 100000));
		ArrayValues.add(new BasicNameValuePair("accessRecordDto.bigClassify", "channel"));

		new Thread(new Runnable() { // 开启线程上传文件
			@Override
			public void run() {
				String DueData = "";
				DueData = HttpGetData.GetData("api/pubshare/accessRecord/getListJsonData", ArrayValues);
				Message msg = new Message();
				msg.obj = DueData;
				msg.what = 66;
				uiHandler.sendMessage(msg);
			}
		}).start();

	}

	TimerTask task = new TimerTask() {
		@Override
		public void run() {
			recLen++;
			Message message = new Message();
			message.what = 1;
			handler.sendMessage(message);
		}
	};
	final Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 1:

				if (recLen < 0) {
					timer.cancel();

				}
				try {
					int sys = 0;
					sys = PreUserInfo.getInt("sys", 0);

					if (sys == 0) {

					} else {
						fileClassify = "" + sys;
						curPage = 1;
						GetData();
					}
					// starttimedelay();
				} catch (Exception e) {
					// TODO: handle exceptioni

				}
				break;
			}
		}
	};

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		// TODO Auto-generated method stub
		BuildModel data = list.get(position - 1);
		if (true) {
			Intent intent = new Intent();
			intent.setClass(getActivity(), SpecialDetailActivity.class);
			Bundle bundle = new Bundle();
			bundle.putString("Title", data.getTitle());
			bundle.putString("Time", data.getTime());
			bundle.putString("detail", data.getContent());
			bundle.putString("chn", chn);
			bundle.putString("Id", data.getId());
			intent.putExtras(bundle);
			startActivity(intent);
		}
	}

	private void GetData() {
		// TODO Auto-generated method stub
		final ArrayList ArrayValues = new ArrayList();
		ArrayValues.add(new BasicNameValuePair("ticket", "" + ticket));
		ArrayValues.add(new BasicNameValuePair("chn", "zdwj"));
		chn = "zdwj";
		ArrayValues.add(new BasicNameValuePair("curPage", "" + curPage));
		ArrayValues.add(new BasicNameValuePair("pageSize", "" + pageSize));
		ArrayValues.add(new BasicNameValuePair("classify", "" + fileClassify));

		new Thread(new Runnable() { // 开启线程上传文件
			@Override
			public void run() {
				String DueData = "";
				DueData = HttpGetData.GetData("api/cms/channel/channleListData", ArrayValues);
				Message msg = new Message();
				msg.obj = DueData;
				msg.what = GET_DUE_DATA;
				uiHandler.sendMessage(msg);
			}
		}).start();

	}

	private void ReadTicket() {
		// TODO Auto-generated method stub
		ticket = PreUserInfo.getString("ticket", "");

	}

	private void initview(View view2) {
		// TODO Auto-generated method stub
		ListData = (ListView) view.findViewById(R.id.list_data);
		text_1 = (TextView) view.findViewById(R.id.text_1);
		text_2 = (TextView) view.findViewById(R.id.text_2);
		text_3 = (TextView) view.findViewById(R.id.text_3);

		text_1.setOnClickListener(this);
		text_2.setOnClickListener(this);
		text_3.setOnClickListener(this);

		text_4 = (TextView) view.findViewById(R.id.text_4);
		text_5 = (TextView) view.findViewById(R.id.text_5);

		text_4.setOnClickListener(this);
		text_5.setOnClickListener(this);
		text_6 = (TextView) view.findViewById(R.id.text_6);
		text_7 = (TextView) view.findViewById(R.id.text_7);

		text_6.setOnClickListener(this);
		text_7.setOnClickListener(this);
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
				GetData();
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
					GetData();
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

	protected void go() {
		ListData = (ListView) view.findViewById(R.id.list_data);
		ListData.setPadding(0, -100, 0, 0);

		mAdapter = new BuildAdapter4(getActivity(), list, ListData, this);

		ListData.setAdapter(mAdapter);

	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

	}

	@Override
	public void onStart() {
		super.onStart();
		try {
			if (view != null) {
				timer.schedule(task, 1000, 1000); // timeTask
				Log.e("onStart", "onStart-time_begin");
			}
			Log.e("onStart", "onStart");
		} catch (Exception e) {
			// TODO: handle exception
		}

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

		Log.e("onStop", "onStop");
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		Log.e("onDestroyView", "onDestroyView");
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		timer.cancel();
		Log.e("onDestroy", "onDestroy");
	}

	@Override
	public void onDetach() {
		super.onDetach();
	}

	public void setdata(int position) {

		// TODO Auto-generated method stub
		switch (position) {

		case 1:
			new Thread(new Runnable() { // 开启线程上传文件
				@Override
				public void run() {
					String DueData = "";

					Message msg = new Message();
					msg.obj = DueData;
					msg.what = 8;
					uiHandler.sendMessage(msg);
				}
			}).start();

			break;
		case 2:
			// text_1=(TextView) view.findViewById(R.id.text_1);
			this.text_1.setText("ee");

			curPage = 1;
			fileClassify = "5";
			GetData();
			break;
		case 3:

			curPage = 1;
			fileClassify = "4";
			GetData();
			break;
		case 4:

			curPage = 1;
			fileClassify = "2";
			GetData();
			break;
		case 5:

			curPage = 1;
			fileClassify = "3";
			GetData();
			break;
		case 6:

			curPage = 1;
			fileClassify = "6";
			GetData();
			break;
		case 7:

			curPage = 1;
			fileClassify = "7";
			GetData();
			break;
		default:
			break;
		}

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {

		case R.id.text_1:
			clearcolor();
			text_1.setTextColor(Color.parseColor(getString(R.color.main_color)));

			curPage = 1;
			fileClassify = "1";
			GetData();
			break;
		case R.id.text_2:
			clearcolor();
			text_2.setTextColor(Color.parseColor(getString(R.color.main_color)));

			curPage = 1;
			fileClassify = "5";
			GetData();
			break;
		case R.id.text_3:
			clearcolor();
			text_3.setTextColor(Color.parseColor(getString(R.color.main_color)));

			curPage = 1;
			fileClassify = "4";
			GetData();
			break;
		case R.id.text_4:
			clearcolor();
			text_4.setTextColor(Color.parseColor(getString(R.color.main_color)));

			curPage = 1;
			fileClassify = "2";
			GetData();
			break;
		case R.id.text_5:
			clearcolor();
			text_5.setTextColor(Color.parseColor(getString(R.color.main_color)));

			curPage = 1;
			fileClassify = "3";
			GetData();
			break;
		case R.id.text_6:
			clearcolor();
			text_6.setTextColor(Color.parseColor(getString(R.color.main_color)));

			curPage = 1;
			fileClassify = "6";
			GetData();
			break;
		case R.id.text_7:
			clearcolor();
			text_7.setTextColor(Color.parseColor(getString(R.color.main_color)));

			curPage = 1;
			fileClassify = "7";
			GetData();
			break;
		default:
			break;
		}
	}

	private void clearcolor() {
		// TODO Auto-generated method stub
		text_1.setTextColor(Color.parseColor("#000000"));
		text_2.setTextColor(Color.parseColor("#000000"));
		text_3.setTextColor(Color.parseColor("#000000"));
		text_4.setTextColor(Color.parseColor("#000000"));
		text_5.setTextColor(Color.parseColor("#000000"));
		text_6.setTextColor(Color.parseColor("#000000"));
		text_7.setTextColor(Color.parseColor("#000000"));
	}

	@Override
	public void click(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.lin_all:
			BuildModel data = list.get((Integer) v.getTag());

			Intent intent = new Intent();
			intent.setClass(getActivity(), SpecialDetailActivity.class);
			Bundle bundle = new Bundle();
			bundle.putString("Title", data.getTitle());
			bundle.putString("Time", data.getTime());
			bundle.putString("detail", data.getContent());
			bundle.putString("chn", chn);
			bundle.putString("Id", data.getId());
			bundle.putBoolean("read", data.isRead());
			intent.putExtras(bundle);
			startActivity(intent);
			if (!data.isRead()) {
				Editor edit = PreForZDWJ.edit();
				edit.putBoolean(data.getId(), true);
				edit.commit();
				data.setRead(true);
				mAdapter.notifyDataSetChanged();
				Editor edit1 = ItemNumber.edit();
				edit1.putInt("ZDWJread", (PreForZDWJ.getAll().size() - 1));
				Log.e("ZDWJread", "" + (PreForZDWJ.getAll().size() - 1));
				edit1.commit();
			}

			// Toast.makeText(getActivity(), "删除第" + + "条",
			// Toast.LENGTH_SHORT).show();
			break;
		default:
			break;
		}
	}
}
