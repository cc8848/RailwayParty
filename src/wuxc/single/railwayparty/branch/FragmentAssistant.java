package wuxc.single.railwayparty.branch;

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
import wuxc.single.railwayparty.adapter.AssistantAdapter;
import wuxc.single.railwayparty.adapter.AssistantAdapter.Callback;
import wuxc.single.railwayparty.internet.HttpGetData;
import wuxc.single.railwayparty.model.AssistantModel;
import wuxc.single.railwayparty.start.SpecialDetailActivity;
import wuxc.single.railwayparty.start.webview;

public class FragmentAssistant extends Fragment
		implements Callback, OnTouchListener, OnClickListener, OnItemClickListener {
	private ListView ListData;
	List<AssistantModel> list = new ArrayList<AssistantModel>();
	private static AssistantAdapter mAdapter;
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
	private SharedPreferences PreForJZXX;
	private SharedPreferences ItemNumber;
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
					Editor edit = PreForJZXX.edit();
					edit.putBoolean("JZXX", true);
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
						Editor edit = PreForJZXX.edit();
						edit.putBoolean("JZXX", true);

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
						edit1.putInt("JZXXread", (PreForJZXX.getAll().size() - 1));
						Log.e("JZXXread", "" + (PreForJZXX.getAll().size() - 1));
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
					// json_data = json_data.getJSONObject("data");
					AssistantModel listinfo = new AssistantModel();

					// listinfo.setTime(json_data.getString("createTime"));
					// listinfo.setTitle(json_data.getString("title"));
					listinfo.setHeadimgUrl(json_data.getString("sacleImage"));
					// listinfo.setContent(json_data.getString("yearMonth"));
					listinfo.setId(json_data.getString("keyid"));
					listinfo.setCont(true);
					listinfo.setTime(json_data.getString("releaseDate"));
					listinfo.setTitle(json_data.getString("title"));
					listinfo.setContent(json_data.getString("summary"));
					listinfo.setSummary(json_data.getString("summary"));
					listinfo.setGuanzhu("231");
					listinfo.setZan("453");
					listinfo.setImageurl(R.drawable.logo);
					// listinfo.setHeadimgUrl("");
					listinfo.setRead(PreForJZXX.getBoolean(json_data.getString("keyid"), false));

					try {
						listinfo.setLink(json_data.getString("otherLinks"));
						if (json_data.getString("content").equals("") || json_data.getString("content") == null
								|| json_data.getString("content").equals("null")) {
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
			int totalcount = 0;
			totalcount = demoJson.getInt("totalRecord");
			Log.e("totalcount", "" + totalcount);
			Editor edit = ItemNumber.edit();
			edit.putInt("JZXXtotal", totalcount);
			edit.commit();
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
			view = inflater.inflate(R.layout.wuxc_fragment_party_assistant_info, container, false);
			initview(view);
			setonclicklistener();
			setheadtextview();
			PreUserInfo = getActivity().getSharedPreferences("UserInfo", Context.MODE_PRIVATE);
			PreForJZXX = getActivity().getSharedPreferences("JZXX", Context.MODE_PRIVATE);
			ItemNumber = getActivity().getSharedPreferences("ItemNumber", Context.MODE_PRIVATE);

			ReadTicket();

			GetData();
			if (!PreForJZXX.getBoolean("JZXX", false)) {
				GetMyReadRecord();
			}
			// getdatalist(curPage);
		}

		return view;

	}

	private void GetMyReadRecord() {
		// TODO Auto-generated method stub
		final ArrayList ArrayValues = new ArrayList();
		ArrayValues.add(new BasicNameValuePair("ticket", "" + ticket));
		ArrayValues.add(new BasicNameValuePair("accessRecordDto.classify", "jzxx"));
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
	}

	private void setonclicklistener() {
		// TODO Auto-generated method stub
		ListData.setOnItemClickListener(this);
	}

	// @Override
	// public boolean onTouch(View v, MotionEvent event) {
	// // TODO Auto-generated method stub
	// float tempY = event.getY();
	// float tempyfoot = event.getY();
	// firstItemIndex = ListData.getFirstVisiblePosition();
	// lastItemIndex = ListData.getLastVisiblePosition();
	// // Toast.makeText(getActivity(), " lastItemIndex" +
	// // lastItemIndex, Toast.LENGTH_SHORT).show();
	// switch (event.getAction()) {
	// case MotionEvent.ACTION_DOWN:
	// case MotionEvent.ACTION_MOVE:
	// if (!isRecored && (firstItemIndex == 0)) {
	// isRecored = true;
	// startY = tempY;
	// }
	// int temp = 1;
	// temp = (lastItemIndex) % pageSize;
	// if (!isRecoredfoot && (temp == 0)) {
	// isRecoredfoot = true;
	// startYfoot = tempyfoot;
	// }
	// break;
	// case MotionEvent.ACTION_UP:
	// case MotionEvent.ACTION_CANCEL:
	// isRecored = false;
	// isRecoredfoot = false;
	// break;
	//
	// default:
	// break;
	// }
	//
	// switch (event.getAction()) {
	// case MotionEvent.ACTION_DOWN:
	// break;
	// case MotionEvent.ACTION_UP:
	// case MotionEvent.ACTION_CANCEL:
	// ListData.setPadding(0, 0, 0, 0);
	// if (tempY - startY < 400) {
	// ListData.setPadding(0, -100, 0, 0);
	// } else {
	// curPage = 1;
	// Toast.makeText(getActivity(), "正在刷新", Toast.LENGTH_SHORT).show();
	// getdatalist(pageSize);
	// }
	// int temp = 1;
	// temp = (lastItemIndex) % pageSize;
	// // temp = 0;
	// if (temp == 0 && (startYfoot - tempyfoot > 400)) {
	// curPage++;
	// if (curPage > totalPage) {
	// Toast.makeText(getActivity(), " 没有更多了", Toast.LENGTH_SHORT).show();
	// // // listinfoagain();
	// } else {
	// getdatalist(pageSize);
	// Toast.makeText(getActivity(), "正在加载下一页", Toast.LENGTH_SHORT).show();
	// }
	//
	// } else {
	//
	// }
	// break;
	// case MotionEvent.ACTION_MOVE:
	// if (isRecored && tempY > startY) {
	// ListData.setPadding(0, (int) ((tempY - startY) / RATIO - 100), 0, 0);
	// }
	// if (isRecoredfoot && startYfoot > tempyfoot) {
	// // footTextView.setVisibility(View.VISIBLE);
	// ListData.setPadding(0, -100, 0, (int) ((startYfoot - tempyfoot) /
	// RATIO));
	// }
	// break;
	//
	// default:
	// break;
	// }
	// return false;
	// }
	//
	// @Override
	// public void onItemClick(AdapterView<?> parent, View view, int position,
	// long id) {
	// // TODO Auto-generated method stub
	// // recommendModel data = list.get(position - 1);
	// // Intent intent = new Intent();
	// // intent.setClass(getActivity(), SpecialDetailActivity.class);
	// // Bundle bundle = new Bundle();
	// // bundle.putString("Title", data.getTitle());
	// // bundle.putString("detail", data.getDetail());
	// // bundle.putString("Time", data.getTime());
	// // bundle.putString("Name", "名字");
	// // intent.putExtras(bundle);
	// // startActivity(intent);
	//// Toast.makeText(getActivity(), "点击第" + position + "条" + "item",
	// Toast.LENGTH_SHORT).show();
	// Intent intent = new Intent();
	// intent.setClass(getActivity(), DetailActivity.class);
	// Bundle bundle = new Bundle();
	// bundle.putInt("source", R.drawable.detail1);
	// bundle.putInt("height", 2940);
	// bundle.putInt("width", 750);
	// intent.putExtras(bundle);
	// startActivity(intent);}
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		// TODO Auto-generated method stub
		AssistantModel data = list.get(position - 1);
		if (data.isCont()) {
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
		} else {
			Intent intent = new Intent();
			intent.setClass(getActivity(), webview.class);
			Bundle bundle = new Bundle();
			bundle.putString("url", data.getLink());
			// // bundle.putString("Time", "2016-11-23");
			// // bundle.putString("Name", "小李");
			// // bundle.putString("PageTitle", "收藏详情");
			// // bundle.putString("Detail",
			// //
			// "中国共产主义青年团，简称共青团，原名中国社会主义青年团，是中国共产党领导的一个由信仰共产主义的中国青年组成的群众性组织。共青团中央委员会受中共中央委员会领导，共青团的地方各级组织受同级党的委员会领导，同时受共青团上级组织领导。1922年5月，团的第一次代表大会在广州举行，正式成立中国社会主义青年团，1925年1月26日改称中国共产主义青年团。1959年5月4日共青团中央颁布共青团团徽。");
			intent.putExtras(bundle);
			startActivity(intent);
		}
	}

	@Override
	public void click(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.lin_all:
			AssistantModel data = list.get((Integer) v.getTag());

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
				Editor edit = PreForJZXX.edit();
				edit.putBoolean(data.getId(), true);
				edit.commit();
				data.setRead(true);
				mAdapter.notifyDataSetChanged();
				Editor edit1 = ItemNumber.edit();
				edit1.putInt("JZXXread", (PreForJZXX.getAll().size() - 1));
				Log.e("JZXXread", "" + (PreForJZXX.getAll().size() - 1));
				edit1.commit();
			}

			break;
		default:
			break;
		}
	}

	private void GetData() {
		final ArrayList ArrayValues = new ArrayList();
		ArrayValues.add(new BasicNameValuePair("ticket", "" + ticket));
		// chn = GetChannelByKey.GetSign(PreALLChannel, "职工之家");
		ArrayValues.add(new BasicNameValuePair("chn", "jzxx"));
		chn = "dyq";
		ArrayValues.add(new BasicNameValuePair("curPage", "" + curPage));
		ArrayValues.add(new BasicNameValuePair("pageSize", "" + pageSize));
		ArrayValues.add(new BasicNameValuePair("classify", ""));

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
		ListData.setPadding(0, -100, 0, 0);
		mAdapter = new AssistantAdapter(getActivity(), list, ListData, this);
		ListData.setAdapter(mAdapter);
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

		default:
			break;
		}
	}
}
