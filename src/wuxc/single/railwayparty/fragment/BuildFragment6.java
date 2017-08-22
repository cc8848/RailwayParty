package wuxc.single.railwayparty.fragment;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
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
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import wuxc.single.railwayparty.R;
import wuxc.single.railwayparty.adapter.SchoolAdapter;
import wuxc.single.railwayparty.adapter.SchoolAdapter.Callback;
import wuxc.single.railwayparty.detail.DetailActivity;
import wuxc.single.railwayparty.internet.HttpGetData;
import wuxc.single.railwayparty.layout.dialogselecttwo;
import wuxc.single.railwayparty.model.BuildModel;
import wuxc.single.railwayparty.model.SchoolModel;
import wuxc.single.railwayparty.model.SchoolModel;
import wuxc.single.railwayparty.start.ImagePPT;
import wuxc.single.railwayparty.start.SpecialDetailActivity;
import wuxc.single.railwayparty.start.webview;
import wuxc.single.railwayparty.start.wsdxActivity;

public class BuildFragment6 extends Fragment
		implements OnTouchListener, Callback, OnClickListener, OnItemClickListener {
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
	private String ticket = "";
	private String chn;
	private String userPhoto;
	private String LoginId;
	private SharedPreferences PreUserInfo;// 存储个人信息
	private SharedPreferences PreALLChannel;// 存储所用频道信息
	private static final String GET_SUCCESS_RESULT = "success";
	private static final String GET_FAIL_RESULT = "fail";
	private static final int GET_DUE_DATA = 6;
	private TextView TextArticle;
	private TextView TextVideo;
	private String searchChannelText = "政工干部培训";
	private String fileClassify = "";
	private String classify = "";
	private String Type = "";
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

	protected void GetDataDueData(Object obj) {

		// TODO Auto-generated method stub
		String Type = null;
		String Data = null;
		String pager = null;
		try {
			JSONObject demoJson = new JSONObject(obj.toString());
			Type = demoJson.getString("type");
			// pager = demoJson.getString("pager");
			Data = demoJson.getString("datas");
			if (Type.equals(GET_SUCCESS_RESULT)) {
				GetPager(Data);
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
					// JSONObject jsonObject = json_data.getJSONObject("data");
					SchoolModel listinfo = new SchoolModel();

					listinfo.setTime(json_data.getString("releaseDate"));
					listinfo.setTitle(json_data.getString("title"));
					listinfo.setId(json_data.getString("keyid"));
					// listinfo.setBackGround(json_data.getString("sacleImage"));
					listinfo.setContent(json_data.getString("summary"));
					listinfo.setSummary(json_data.getString("summary"));
					listinfo.setCont(true);
					listinfo.setGuanzhu("231");
					listinfo.setZan("453");
					listinfo.setFileClassify(json_data.getInt("fileClassify"));
					listinfo.setNumber(json_data.getInt("browser"));
					listinfo.setImageurl(headimg[i]);
					listinfo.setHeadimgUrl(json_data.getString("sacleImage"));
					listinfo.setRead(true);
					try {
						listinfo.setLink(json_data.getString("otherLinks"));
						if (!(json_data.getString("otherLinks").equals("") || json_data.getString("otherLinks") == null
								|| json_data.getString("otherLinks").equals("null"))) {
							// listinfo.setContent(json_data.getString("source"));
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

			PreUserInfo = getActivity().getSharedPreferences("UserInfo", Context.MODE_PRIVATE);
			ReadTicket();
			GetData();
		}

		return view;
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
		SchoolModel data = list.get(position - 1);
		if (data.isCont()) {
			Intent intent = new Intent();
			intent.setClass(getActivity(), wsdxActivity.class);
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
		// chn = GetChannelByKey.GetSign(PreALLChannel,
		// "职工之家");searchChannelText
		ArrayValues.add(new BasicNameValuePair("chn", "wsdx"));
		chn = "wsdx";
		ArrayValues.add(new BasicNameValuePair("searchChannelText", searchChannelText + Type));
		ArrayValues.add(new BasicNameValuePair("curPage", "" + curPage));
		ArrayValues.add(new BasicNameValuePair("pageSize", "" + pageSize));
		ArrayValues.add(new BasicNameValuePair("fileClassify", "" + fileClassify));
		ArrayValues.add(new BasicNameValuePair("classify", "" + classify));
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
		userPhoto = PreUserInfo.getString("userPhoto", "");
		LoginId = PreUserInfo.getString("userName", "");
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
		mAdapter = new SchoolAdapter(getActivity(), list, ListData, this);
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
			Type = "PPT";
			curPage = 1;
			fileClassify = "1";
			GetData();
			break;
		case R.id.text_2:
			clearcolor();
			text_2.setTextColor(Color.parseColor(getString(R.color.main_color)));
			Type = "音频";
			curPage = 1;
			fileClassify = "2";
			GetData();
			break;
		case R.id.text_3:
			clearcolor();
			text_3.setTextColor(Color.parseColor(getString(R.color.main_color)));
			Type = "视频";
			curPage = 1;
			fileClassify = "3";
			GetData();
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
				searchChannelText = "政工干部培训";
				curPage = 1;
				classify = "1";
				GetData();
			}

		});

		builder.setNegativeButton("党员培训", new android.content.DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
				curPage = 1;
				text_title.setText("党员培训");
				searchChannelText = "党员培训";
				classify = "2";
				GetData();
			}
		});

		builder.create().show();

	}

	@Override
	public void click(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.lin_all:
			// SchoolModel data = list.get((Integer) v.getTag());
			// if (data.isCont()) {
			// Intent intent = new Intent();
			// intent.setClass(getActivity(), SpecialDetailActivity.class);
			// Bundle bundle = new Bundle();
			// bundle.putString("Title", data.getTitle());
			// bundle.putString("Time", data.getTime());
			// bundle.putString("detail", data.getContent());
			// bundle.putString("chn", chn);
			// bundle.putString("Id", data.getId());
			// intent.putExtras(bundle);
			// startActivity(intent);
			// } else {
			// Intent intent = new Intent();
			// intent.setClass(getActivity(), webview.class);
			// Bundle bundle = new Bundle();
			// bundle.putString("url", data.getLink());
			// // // bundle.putString("Time", "2016-11-23");
			// // // bundle.putString("Name", "小李");
			// // // bundle.putString("PageTitle", "收藏详情");
			// // // bundle.putString("Detail",
			// // //
			// //
			// "中国共产主义青年团，简称共青团，原名中国社会主义青年团，是中国共产党领导的一个由信仰共产主义的中国青年组成的群众性组织。共青团中央委员会受中共中央委员会领导，共青团的地方各级组织受同级党的委员会领导，同时受共青团上级组织领导。1922年5月，团的第一次代表大会在广州举行，正式成立中国社会主义青年团，1925年1月26日改称中国共产主义青年团。1959年5月4日共青团中央颁布共青团团徽。");
			// intent.putExtras(bundle);
			// startActivity(intent);
			// // Toast.makeText(getActivity(), "删除第" + + "条",
			// // Toast.LENGTH_SHORT).show();
			// }
			SchoolModel data = list.get((Integer) v.getTag());

			if (data.isCont()) {
				if (data.getFileClassify() == 1) {
					Intent intent = new Intent();
					intent.setClass(getActivity(), ImagePPT.class);
					Bundle bundle = new Bundle();
					bundle.putString("Title", data.getTitle());
					bundle.putString("Time", data.getTime());
					bundle.putString("detail", data.getContent());
					bundle.putString("chn", chn);
					bundle.putString("Id", data.getId());
					bundle.putString("cover", data.getHeadimgUrl());
					bundle.putString("ticket", ticket);
					intent.putExtras(bundle);
					startActivity(intent);
				} else {
					Intent intent = new Intent();
					intent.setClass(getActivity(), wsdxActivity.class);
					Bundle bundle = new Bundle();
					bundle.putString("Title", data.getTitle());
					bundle.putString("Time", data.getTime());
					bundle.putString("detail", data.getContent());
					bundle.putString("chn", chn);
					bundle.putString("Id", data.getId());
					bundle.putString("cover", data.getHeadimgUrl());
					bundle.putString("ticket", ticket);
					intent.putExtras(bundle);
					startActivity(intent);
				}

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
				bundle.putString("Title", data.getTitle());
				bundle.putString("Time", data.getTime());
				bundle.putString("detail", data.getContent());
				bundle.putString("chn", chn);
				bundle.putString("Id", data.getId());
				bundle.putString("cover", data.getHeadimgUrl());
				bundle.putString("ticket", ticket);
				intent.putExtras(bundle);
				startActivity(intent);
			}
			break;
		default:
			break;
		}
	}
}
