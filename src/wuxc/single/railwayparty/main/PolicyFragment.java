package wuxc.single.railwayparty.main;

import java.util.ArrayList;
import java.util.List;

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
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import wuxc.single.railwayparty.R;
import wuxc.single.railwayparty.adapter.PolicyAdapter;
import wuxc.single.railwayparty.adapter.PolicyAdapter.Callback;
import wuxc.single.railwayparty.internet.HttpGetData;
import wuxc.single.railwayparty.model.Clean1Model;
import wuxc.single.railwayparty.model.PolicyModel;
import wuxc.single.railwayparty.start.SpecialDetailActivity;
import wuxc.single.railwayparty.start.webview;

public class PolicyFragment extends Fragment
		implements Callback, OnTouchListener, OnClickListener, OnItemClickListener {
	private ListView ListData;
	List<PolicyModel> list = new ArrayList<PolicyModel>();
	private static PolicyAdapter mAdapter;
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

	private int screenwidth = 0;
	private String ticket = "";
	private String chn;
	private SharedPreferences PreUserInfo;// 存储个人信息
	private static final String GET_SUCCESS_RESULT = "success";
	private static final String GET_FAIL_RESULT = "fail";
	private static final int GET_DUE_DATA = 6;
	private int subClassify = 1;
	private int classify = 1;
	private TextView text_1;

	private TextView text_2;
	private SharedPreferences PreForLXYZXX;
	private SharedPreferences ItemNumber;
	private RelativeLayout main_top_bac;
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
					Editor edit = PreForLXYZXX.edit();
					edit.putBoolean("LXYZXX", true);
					edit.commit();
				} catch (Exception e) {
					// TODO: handle exception
				}
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
						Editor edit = PreForLXYZXX.edit();
						edit.putBoolean("LXYZXX", true);

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
						edit1.putInt("LXYZXXread", (PreForLXYZXX.getAll().size() - 1));
						Log.e("LXYZXXread", "" + (PreForLXYZXX.getAll().size() - 1));
						edit1.commit();
						// BuildFragment buildFragment = new
						// BuildFragment();
						// buildFragment.intnumber();
					}

				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} else if (Type.equals(GET_FAIL_RESULT)) {
			} else {
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
		}
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
		;
		if (arg == 1) {
			list.clear();
			// PolicyModel listinfo = new PolicyModel();
			// listinfo.setTime("2017-09-18");
			// listinfo.setTitle("学党章党规");
			// listinfo.setContent("着眼明确基本标准、树立行为规范、逐条逐句通读党章、为人民做表率。");
			// listinfo.setImageurl(headimg[0]);
			// listinfo.setHeadimgUrl("");
			// listinfo.setScreenwidth(screenwidth);
			// list.add(listinfo);
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
					PolicyModel listinfo = new PolicyModel();

					listinfo.setTime(json_data.getString("releaseDate"));
					listinfo.setTitle(json_data.getString("title"));
					listinfo.setId(json_data.getString("keyid"));
					// listinfo.setBackGround(json_data.getString("sacleImage"));
					listinfo.setContent(json_data.getString("summary"));
					listinfo.setSummary(json_data.getString("summary"));
					listinfo.setCont(true);
					// listinfo.setGuanzhu(json_data.getString("hot"));
					// listinfo.setZan("453");
					listinfo.setImageurl(R.drawable.logo);
					listinfo.setScreenwidth(screenwidth);
					// listinfo.setName(json_data.getString("title"));
					listinfo.setLabel(json_data.getString("title"));
					int classify = json_data.getInt("classify");
					if (classify == 1) {
						listinfo.setLabel("优秀职工");
					} else {
						listinfo.setLabel("最美工程师");
					}
					listinfo.setHeadimgUrl(json_data.getString("sacleImage"));
					// listinfo.setRead(true);
					listinfo.setRead(PreForLXYZXX.getBoolean(json_data.getString("keyid"), false));
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

	private void GetPager(String pager) {
		// TODO Auto-generated method stub
		try {
			JSONObject demoJson = new JSONObject(pager);

			totalPage = demoJson.getInt("totalPage");
			// int totalcount = 0;
			// totalcount = demoJson.getInt("totalRecord");
			// Log.e("totalcount", "" + totalcount);
			// Editor edit = ItemNumber.edit();
			// edit.putInt("LXYZXXtotal" + subClassify, totalcount);
			// edit.commit();
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
			view = inflater.inflate(R.layout.wuxc_a_only_list2, container, false);
			initview(view);
			screenwidth = getActivity().getWindow().getWindowManager().getDefaultDisplay().getWidth();
			initheight(view);
			setonclicklistener();
			setheadtextview();
			initcolor();
			// getdatalist(curPage);
			PreUserInfo = getActivity().getSharedPreferences("UserInfo", Context.MODE_PRIVATE);
			PreForLXYZXX = getActivity().getSharedPreferences("LXYZXX", Context.MODE_PRIVATE);
			ItemNumber = getActivity().getSharedPreferences("ItemNumber", Context.MODE_PRIVATE);

			ReadTicket();

			GetData();
			if (!PreForLXYZXX.getBoolean("LXYZXX", false)) {
				GetMyReadRecord();
			}
		}

		return view;

	}

	private void GetMyReadRecord() {
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
		ArrayValues.add(new BasicNameValuePair("accessRecordDto.classify", "lxyzxx"));
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

	private void initview(View view2) {
		// TODO Auto-generated method stub
		ListData = (ListView) view.findViewById(R.id.list_data);
		text_1 = (TextView) view.findViewById(R.id.text_1);
		text_2 = (TextView) view.findViewById(R.id.text_2);
		text_1.setOnClickListener(this);
		text_2.setOnClickListener(this);
		main_top_bac = (RelativeLayout) view.findViewById(R.id.main_top_bac);

	}

	private void initheight(View view) {
		// TODO Auto-generated method stub
		int height = (int) (screenwidth * 300 / 750);
		LinearLayout.LayoutParams LayoutParams2 = (android.widget.LinearLayout.LayoutParams) main_top_bac
				.getLayoutParams();
		LayoutParams2.height = height;
		main_top_bac.setLayoutParams(LayoutParams2);

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

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		// TODO Auto-generated method stub
		PolicyModel data = list.get(position - 1);
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
		ArrayValues.add(new BasicNameValuePair("chn", "lxyzxx"));
		chn = "lxyzxx";
		ArrayValues.add(new BasicNameValuePair("curPage", "" + curPage));
		ArrayValues.add(new BasicNameValuePair("pageSize", "" + pageSize));
		ArrayValues.add(new BasicNameValuePair("classify", "" + classify));
		ArrayValues.add(new BasicNameValuePair("subClassify", "" + subClassify));
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

	public void Set(int i) {
		classify = i;
		curPage = 1;
		GetData();
	}

	private void ReadTicket() {
		// TODO Auto-generated method stub
		ticket = PreUserInfo.getString("ticket", "");
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
		ListData.setPadding(0, -100, 0, 0);
		mAdapter = new PolicyAdapter(getActivity(), list, ListData, this);
		ListData.setAdapter(mAdapter);
		// Editor edit = PreForLXYZXX.edit();
		// edit.clear();
		// edit.commit();
		// Editor edit2 = PreForLXYZXX.edit();
		// edit2.putBoolean("LXYZXX", true);
		// for (int i = 0; i < list.size(); i++) {
		// PolicyModel info = list.get(i);
		// if (info.isRead()) {
		// edit2.putBoolean(info.getId(), true);
		// }
		// }
		// edit2.commit();
		// Editor edit1 = ItemNumber.edit();
		// edit1.putInt("LXYZXXread", (PreForLXYZXX.getAll().size() - 1));
		// edit1.commit();
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

	private void initcolor() {
		// TODO Auto-generated method stub
		clearcolor();
		text_1.setTextColor(Color.parseColor("#ffffff"));
		text_1.setBackgroundResource(R.drawable.shape18red);
	}

	private void clearcolor() {
		// TODO Auto-generated method stub
		text_1.setTextColor(Color.parseColor("#cc0502"));
		text_2.setTextColor(Color.parseColor("#cc0502"));
		text_1.setBackgroundResource(Color.parseColor("#00000000"));
		text_2.setBackgroundResource(Color.parseColor("#00000000"));
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.text_1:
			clearcolor();
			text_1.setTextColor(Color.parseColor("#ffffff"));
			text_1.setBackgroundResource(R.drawable.shape18red);
			subClassify = 1;
			GetData();
			break;
		case R.id.text_2:
			clearcolor();
			text_2.setTextColor(Color.parseColor("#ffffff"));
			text_2.setBackgroundResource(R.drawable.shape18red);
			subClassify = 2;
			GetData();
			// list.clear();
			// mAdapter.notifyDataSetChanged();
			// Intent intent = new Intent();
			// intent.setClass(getActivity(), PublishadviceActivity.class);
			// startActivity(intent);
			break;
		default:
			break;
		}
	}

	// TODO Auto-generated method stub
	@Override
	public void click(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.lin_all:
			// if ((Integer) v.getTag() == 0) {
			//
			// } else {
			PolicyModel data = list.get((Integer) v.getTag());

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
				Editor edit = PreForLXYZXX.edit();
				edit.putBoolean(data.getId(), true);
				edit.commit();
				data.setRead(true);
				mAdapter.notifyDataSetChanged();
				Editor edit1 = ItemNumber.edit();
				edit1.putInt("LXYZXXread", (PreForLXYZXX.getAll().size() - 1));
				Log.e("LXYZXXread", "" + (PreForLXYZXX.getAll().size() - 1));
				edit1.commit();
			}

			// }

			break;
		default:
			break;
		}
	}
}
