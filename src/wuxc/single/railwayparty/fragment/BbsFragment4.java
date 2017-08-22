package wuxc.single.railwayparty.fragment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
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
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import wuxc.single.railwayparty.R;
import wuxc.single.railwayparty.adapter.Bbs4Adapter;
import wuxc.single.railwayparty.adapter.Bbs4Adapter.Callback;
import wuxc.single.railwayparty.detail.DetailActivity;
import wuxc.single.railwayparty.internet.HttpGetData;
import wuxc.single.railwayparty.main.PublishTipsActivity;
import wuxc.single.railwayparty.main.PublishadviceActivity;
import wuxc.single.railwayparty.main.TipsDetailActivity;
import wuxc.single.railwayparty.model.Bbs4Model;
import wuxc.single.railwayparty.model.BuildModel;
import wuxc.single.railwayparty.model.Bbs4Model;
import wuxc.single.railwayparty.start.SpecialDetailActivity;
import wuxc.single.railwayparty.start.webview;

public class BbsFragment4 extends Fragment implements OnTouchListener, Callback, OnClickListener, OnItemClickListener {
	private ListView ListData;
	List<Bbs4Model> list = new ArrayList<Bbs4Model>();
	private static Bbs4Adapter mAdapter;
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
	private int[] headimg = { R.drawable.p234, R.drawable.p234, R.drawable.p234, R.drawable.p234, R.drawable.p234,
			R.drawable.p234, R.drawable.p234, R.drawable.p234, R.drawable.p234, R.drawable.p234, R.drawable.p234 };

	private TextView text_1;

	private TextView text_2;
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
	private int type = 2;
	private String URL = "api/pb/yijian/getAllListJsonData";
	private int number = 0;

	private String[] photo = { "", "", "", "", "", "", "", "", "", "" };
	private int screenwidth = 0;
	private float scale = 0;
	private float scalepx = 0;
	private float dp = 0;
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
					Bbs4Model listinfo = new Bbs4Model();

					Date date = null;
					SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					try {
						date = formatter.parse(json_data.getString("createtime"));
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					Date now = new Date();
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");// 可以方便地修改日期格式
					long time = now.getTime() - date.getTime();
					time = time / 1000;
					String thistime = json_data.getString("createtime");
					if (time < 60) {
						thistime = "刚刚";
					} else if (time < 120) {
						thistime = "一分钟前";
					} else if (time < 300) {
						thistime = "五分钟前";
					} else if (time < 1800) {
						thistime = "半小时前";
					} else if (time < 3600) {
						thistime = "一小时前";
					} else if (time < 3600 * 24) {
						thistime = "一天前";
					} else if (time < 3600 * 24 * 2) {
						thistime = "两天前";
					} else if (time < 3600 * 24 * 3) {
						thistime = "三天前";
					} else if (time < 3600 * 24 * 4) {
						thistime = "四天前";
					} else if (time < 3600 * 24 * 5) {
						thistime = "五天前";
					} else if (time < 3600 * 24 * 6) {
						thistime = "六天前";
					} else if (time < 3600 * 24 * 7) {
						thistime = "一周前";
					} else {
						thistime = "更久";
					}
					// System.out.println(date.g);
					listinfo.setTime(thistime);

					listinfo.setId(json_data.getString("keyid"));
					// listinfo.setBackGround(json_data.getString("sacleImage"));

					listinfo.setSummary(json_data.getString("summary"));
					listinfo.setCont(true);
					listinfo.setGuanzhu(json_data.getString("browser"));
					listinfo.setZan(json_data.getString("hot"));
					listinfo.setImageurl(headimg[i]);
					listinfo.setLabel("合理化建议");
					listinfo.setHeadimgUrl("");
					if (json_data.getInt("classify") == 1) {
						listinfo.setLabel("合理化建议");
					} else if (json_data.getInt("classify") == 2) {
						listinfo.setLabel("党员权益维护");
					} else if (json_data.getInt("classify") == 3) {
						listinfo.setLabel("党员生活");
					} else {
						listinfo.setLabel("其他");
					}
					listinfo.setContent(json_data.getString("content"));
					listinfo.setZan(json_data.getString("createtime"));
					// listinfo.setGuanzhu("4532");
					listinfo.setTitle(json_data.getString("title"));
					listinfo.setName(json_data.getString("userName"));
					listinfo.setHeadimgUrl(json_data.getString("userPhoto"));
					listinfo.setRead(true);

					listinfo.setImage1("");
					listinfo.setImage2("");
					listinfo.setImage3("");
					listinfo.setImage4("");
					listinfo.setImage5("");
					listinfo.setImage6("");
					listinfo.setImage7("");
					listinfo.setImage8("");
					listinfo.setImage9("");
					if (true) {
						JSONArray jArray1 = null;
						number = 0;
						try {
							listinfo.setImageList(json_data.getString("imageList"));
							jArray1 = new JSONArray(json_data.getString("imageList"));
							JSONObject json_data1 = null;
							for (int j = 0; j < jArray1.length(); j++) {
								json_data1 = jArray1.getJSONObject(j);
								photo[j] = json_data1.getString("filePath");
								Log.e("photo", photo[j]);

								if (j == 0) {
									listinfo.setImage1(photo[j]);
								} else if (j == 1) {
									listinfo.setImage2(photo[j]);
								} else if (j == 2) {
									listinfo.setImage3(photo[j]);
								} else if (j == 3) {
									listinfo.setImage4(photo[j]);
								} else if (j == 4) {
									listinfo.setImage5(photo[j]);
								} else if (j == 5) {
									listinfo.setImage6(photo[j]);
								} else if (j == 6) {
									listinfo.setImage7(photo[j]);
								} else if (j == 7) {
									listinfo.setImage8(photo[j]);
								} else if (j == 8) {
									listinfo.setImage9(photo[j]);
								}
								number++;
							}
						} catch (Exception e) {
							// TODO: handle exception
						}
					}
					if (json_data.getString("todown").equals("null")) {
						listinfo.setPl("0");
					} else {
						listinfo.setPl(json_data.getString("todown"));
					}
					if (json_data.getString("toup").equals("null")) {
						listinfo.setZan("0");
					} else {
						listinfo.setZan(json_data.getString("toup"));
					}
					if (json_data.getString("browser").equals("null")) {
						listinfo.setGuanzhu("0");
					} else {
						listinfo.setGuanzhu(json_data.getString("browser"));
					}
					listinfo.setWidth((int) (screenwidth - 120 * scalepx));
					listinfo.setPhoto(photo);
					listinfo.setNumber(number);
					listinfo.setSummary(json_data.getString("createtime"));
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
			view = inflater.inflate(R.layout.wuxc_fragment_bbs_4, container, false);
			screenwidth = getActivity().getWindow().getWindowManager().getDefaultDisplay().getWidth();
			DisplayMetrics mMetrics = new DisplayMetrics();
			getActivity().getWindowManager().getDefaultDisplay().getMetrics(mMetrics);
			scale = getActivity().getResources().getDisplayMetrics().density;
			// Log.e("mMetrics", mMetrics.toString() + "scale=" + scale + "0.5f"
			// +
			// 0.5f);
			dp = screenwidth / scale + 0.5f;
			scalepx = screenwidth / dp;
			initview(view);
			setonclicklistener();
			setheadtextview();
			initcolor();
			// getdatalist(curPage);
			PreUserInfo = getActivity().getSharedPreferences("UserInfo", Context.MODE_PRIVATE);
			ReadTicket();
			GetData();
		}

		return view;

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

	private void initview(View view2) {
		// TODO Auto-generated method stub
		ListData = (ListView) view.findViewById(R.id.list_data);
		text_1 = (TextView) view.findViewById(R.id.text_1);
		text_2 = (TextView) view.findViewById(R.id.text_2);
		text_1.setOnClickListener(this);
		text_2.setOnClickListener(this);
	}

	private void setonclicklistener() {
		// TODO Auto-generated method stub
		ListData.setOnItemClickListener(this);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		// TODO Auto-generated method stub
		Bbs4Model data = list.get(position - 1);
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
		// chn = GetChannelByKey.GetSign(PreALLChannel, "职工之家");
		ArrayValues.add(new BasicNameValuePair("chn", "dyyj"));
		chn = "dyyj";
		ArrayValues.add(new BasicNameValuePair("curPage", "" + curPage));
		ArrayValues.add(new BasicNameValuePair("pageSize", "" + pageSize));
		// ArrayValues.add(new BasicNameValuePair("classify", "" + classify));

		new Thread(new Runnable() { // 开启线程上传文件
			@Override
			public void run() {
				String DueData = "";
				DueData = HttpGetData.GetData(URL, ArrayValues);
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
	// // Toast.makeText(getActivity(), "点击第" + position + "条" + "item",
	// // Toast.LENGTH_SHORT).show();
	// Intent intent = new Intent();
	// intent.setClass(getActivity(), DetailActivity.class);
	// Bundle bundle = new Bundle();
	// bundle.putInt("source", R.drawable.detail7);
	// bundle.putInt("height", 1198);
	// bundle.putInt("width", 750);
	// intent.putExtras(bundle);
	// startActivity(intent);
	// }

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

			for (int i = 0; i < 1; i++) {

				Bbs4Model listinfo = new Bbs4Model();
				listinfo.setTime("2017-09-18");
				listinfo.setTitle("学党章党规");
				listinfo.setContent("着眼明确基本标准、树立行为规范、逐条逐句通读党章、为人民做表率。");
				listinfo.setImageurl(headimg[i]);
				listinfo.setLabel("合理化建议");
				listinfo.setHeadimgUrl("");
				listinfo.setName("琳琳");
				listinfo.setZan("123");
				listinfo.setGuanzhu("4532");
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
		mAdapter = new Bbs4Adapter(getActivity(), list, ListData, this);
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
		if (view != null) {
			curPage = 1;
			GetData();
		}
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
		case R.id.text_1:
			clearcolor();
			text_1.setTextColor(Color.parseColor("#ffffff"));
			text_1.setBackgroundResource(R.drawable.shape18red);
			curPage = 1;
			URL = "api/pb/yijian/getAllListJsonData";
			GetData();
			break;
		case R.id.text_2:
			clearcolor();
			text_2.setTextColor(Color.parseColor("#ffffff"));
			text_2.setBackgroundResource(R.drawable.shape18red);
			curPage = 1;
			URL = "api/pb/yijian/getListJsonData";
			GetData();

			break;
		default:
			break;
		}
	}

	@Override
	public void click(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.lin_all:
			Bbs4Model data = list.get((Integer) v.getTag());

			Intent intent = new Intent();
			intent.setClass(getActivity(), TipsDetailActivity.class);
			Bundle bundle = new Bundle();
			bundle.putString("Title", data.getTitle());
			bundle.putString("Time", data.getSummary());
			bundle.putString("detail", data.getContent());
			bundle.putString("chn", "chn");
			bundle.putString("Id", data.getId());
			bundle.putInt("number", data.getNumber());
			bundle.putStringArray("photo", data.getPhoto());
			for (int i = 0; i < data.getPhoto().length; i++) {
				Log.e("data.getPhoto()", data.getPhoto()[i]);
			}
			bundle.putString("modelSign", "dyyj");
			bundle.putString("imagelist", data.getImageList());
			bundle.putString("url", "api/cms/common/getChannelCommentData");
			bundle.putString("curl", "api/cms/common/saveChannelComment2");
			intent.putExtras(bundle);
			startActivity(intent);

			// Toast.makeText(getActivity(), "删除第" + + "条",
			// Toast.LENGTH_SHORT).show();
			break;
		default:
			break;
		}
	}
}
