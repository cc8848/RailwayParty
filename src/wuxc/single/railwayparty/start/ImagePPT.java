package wuxc.single.railwayparty.start;

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
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import wuxc.single.railwayparty.R;
import wuxc.single.railwayparty.adapter.imagePPTAdapter;
import wuxc.single.railwayparty.adapter.imagePPTAdapter.Callback;
import wuxc.single.railwayparty.internet.HttpGetData;
import wuxc.single.railwayparty.internet.URLcontainer;
import wuxc.single.railwayparty.model.imagePPTModel;

public class ImagePPT extends Activity implements OnTouchListener, Callback, OnClickListener, OnItemClickListener {
	private ListView ListData;
	List<imagePPTModel> list = new ArrayList<imagePPTModel>();
	private static imagePPTAdapter mAdapter;
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
	private boolean[] read = { false, false, true, true, true, true, true, true, true, true, true, false, false, true,
			true, true, true, true, true, true, true, true, false, false, true, true, true, true, true, true, true,
			true, true, false, false, true, true, true, true, true, true, true, true, true, false, false, true, true,
			true, true, true, true, true, true, true };
	private int[] headimg = { R.drawable.ppt1, R.drawable.ppt2, R.drawable.ppt3, R.drawable.ppt4, R.drawable.ppt5,
			R.drawable.ppt6, R.drawable.ppt7, R.drawable.ppt8, R.drawable.ppt9, R.drawable.ppt10, R.drawable.ppt11,
			R.drawable.ppt12, R.drawable.ppt13, R.drawable.ppt14, R.drawable.ppt15, R.drawable.ppt16, R.drawable.ppt17,
			R.drawable.ppt18, R.drawable.ppt19, R.drawable.ppt20, R.drawable.ppt21, R.drawable.ppt22, R.drawable.ppt23,
			R.drawable.ppt24, R.drawable.ppt25, R.drawable.ppt26, R.drawable.ppt27, R.drawable.ppt28,
			R.drawable.ppt29 };
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
	private int screenwidth = 0;
	private TextView text_title;
	private TextView text_time;
	private ImageView image_back;
	private String detail = "此次专项检查的范围是招用农民工较多的建筑、制造、采矿、餐饮和其他中小型劳动密集型企业以及个体经济组织。检查内容包括：非公企业与劳动者签订劳动合同情况；按照工资支付有关规定支付职工工资情况；遵守最低工资规定及依法支付加班工资情况；依法参加社会保险和缴纳社会保险费情况；遵守禁止使用童工规定以及女职工和未成年工特殊劳动保护规定情况；其他遵守劳动保障法律法规的情况。";
	private String Id = "";
	private String Name;
	private String Title;
	private String Time;
	private int classify = 0;
	private String filePath = "";
	private LinearLayout lin_fujian;
	private int recLen = 60;
	private String cover = "";
	Timer timer = new Timer();
	private boolean read3 = false;
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
		// String Type = null;
		String Data = null;
		// String pager = null;
		try {
			JSONObject demoJson = new JSONObject(obj.toString());
			String Type = demoJson.getString("type");

			Data = demoJson.getString("data");
			if (Type.equals(GET_SUCCESS_RESULT)) {

				GetDataList(Data, curPage);
			} else if (Type.equals(GET_FAIL_RESULT)) {
				// Toast.makeText(getApplicationContext(), "服务器数据失败",
				// Toast.LENGTH_SHORT).show();
			} else {
				// Toast.makeText(getApplicationContext(), "数据格式校验失败",
				// Toast.LENGTH_SHORT).show();
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private void GetDataList(String data, int arg) {
		// TODO Auto-generated method stub
		try {
			JSONObject demoJson = new JSONObject(data);

			classify = demoJson.getInt("fileClassify");
			JSONArray jArray = null;

			jArray = new JSONArray(demoJson.getString("videoFile"));
			for (int i = 0; i < jArray.length(); i++) {
				try {
					JSONObject demoJson1 = jArray.getJSONObject(i);
					String temp = demoJson1.getString("filePath");
					Log.e("temp", temp);
					String bStrings = demoJson1.getString("ext");
					if (bStrings.equals("ppt") || bStrings.equals("pptx") || bStrings.equals("PPT")
							|| bStrings.equals("PPTX")) {
						filePath = URLcontainer.urlip + "upload" + demoJson1.getString("filePath");
					} else if (bStrings.equals("png") || bStrings.equals("jpg") || bStrings.equals("JPG")
							|| bStrings.equals("PNG") || bStrings.equals("JPEG") || bStrings.equals("jpeg")) {
						imagePPTModel listinfo = new imagePPTModel();
						listinfo.setTime("2017-08-30");
						listinfo.setTitle("杭州地区地铁项目");
						listinfo.setContent("着眼明确基本标准、树立行为规范、逐条逐句通读党章、为人民做表率。");
						listinfo.setGuanzhu("231");
						listinfo.setZan("453");
						listinfo.setRead(true);
						listinfo.setImageurl(0);

						listinfo.setHeadimgUrl(temp);

						listinfo.setWidth(screenwidth);
						Log.e("temp", temp + screenwidth);
						list.add(listinfo);
					}

				} catch (Exception e) {
					// TODO: handle exception
					filePath = "";
				}
			}

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
		}
		if (filePath.equals("") || filePath == null) {
			lin_fujian.setVisibility(view.GONE);
		} else {
			lin_fujian.setVisibility(view.VISIBLE);
		}
		Log.e("temp", filePath);
		go();
	}

	private String getdate(String string) {
		// TODO Auto-generated method stub
		String result = "07-28";
		try {
			String[] bStrings = string.split("-");
			result = bStrings + "-" + bStrings[2];
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
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
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.wuxc_imageppt);
		Intent intent = this.getIntent(); // 获取已有的intent对象
		Bundle bundle = intent.getExtras(); // 获取intent里面的bundle对象
		Name = bundle.getString("Name");
		Title = bundle.getString("Title");
		Time = bundle.getString("Time");
		Id = bundle.getString("Id");

		chn = bundle.getString("chn");
		try {
			detail = bundle.getString("detail");
			ticket = bundle.getString("ticket");
			cover = bundle.getString("cover");
			read3 = bundle.getBoolean("read");
		} catch (Exception e) {
			// TODO: handle exception
		}
		initview();
		screenwidth = getWindow().getWindowManager().getDefaultDisplay().getWidth();
		setonclicklistener();
		setheadtextview();

		// getdatalist(curPage);
		PreUserInfo = getApplicationContext().getSharedPreferences("UserInfo", Context.MODE_PRIVATE);
		ReadTicket();
		GetData();
		if (!read3) {
			record();
		}
		timer.schedule(task, 1000, 1000); // timeTask
	}

	private void record() {
		// TODO Auto-generated method stub
		final ArrayList ArrayValues = new ArrayList();

		ArrayValues.add(new BasicNameValuePair("ticket", "" + ticket));

		ArrayValues.add(new BasicNameValuePair("accessRecordDto.classify", chn));

		ArrayValues.add(new BasicNameValuePair("accessRecordDto.busKey", "" + Id));
		ArrayValues.add(new BasicNameValuePair("accessRecordDto.bigClassify", "channel"));
		ArrayValues.add(new BasicNameValuePair("accessRecordDto.title", "" + Title));

		new Thread(new Runnable() { // 开启线程上传文件
			@Override
			public void run() {
				String DueData = "";
				DueData = HttpGetData.GetData("api/pubshare/accessRecord/save", ArrayValues);

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
			}
		}
	};

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		timer.cancel();
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		// TODO Auto-generated method stub
		// Toast.makeText(getApplicationContext(), "点击第" + position + "条",
		// Toast.LENGTH_SHORT).show();

		imagePPTModel data = list.get(position - 1);
		if (true) {
			Intent intent = new Intent();
			intent.setClass(getApplicationContext(), SpecialDetailActivity.class);
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
			intent.setClass(getApplicationContext(), webview.class);
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
		ArrayValues.add(new BasicNameValuePair("chn", "wsdx"));

		ArrayValues.add(new BasicNameValuePair("datakey", "" + Id));

		new Thread(new Runnable() { // 开启线程上传文件
			@Override
			public void run() {
				String DueData = "";
				DueData = HttpGetData.GetData("api/cms/channel/channleContentData", ArrayValues);
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

	private void initview() {
		// TODO Auto-generated method stub
		ListData = (ListView) findViewById(R.id.list_data);
		text_title = (TextView) findViewById(R.id.text_title);
		text_time = (TextView) findViewById(R.id.text_time);
		image_back = (ImageView) findViewById(R.id.image_back);
		lin_fujian = (LinearLayout) findViewById(R.id.lin_fujian);
		lin_fujian.setOnClickListener(this);
		image_back.setOnClickListener(this);
		text_time.setText(Time);
		text_title.setText(Title);
	}

	private void setonclicklistener() {
		// TODO Auto-generated method stub
		ListData.setOnItemClickListener(this);
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// // TODO Auto-generated method stub
		// float tempY = event.getY();
		// float tempyfoot = event.getY();
		// firstItemIndex = ListData.getFirstVisiblePosition();
		// lastItemIndex = ListData.getLastVisiblePosition();
		// // Toast.makeText(getApplicationContext(), " lastItemIndex" +
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
		// Toast.makeText(getApplicationContext(), "正在刷新",
		// Toast.LENGTH_SHORT).show();
		// getdatalist(curPage);
		// }
		// int temp = 1;
		// temp = (lastItemIndex) % pageSize;
		// // temp = 0;
		// if (temp == 0 && (startYfoot - tempyfoot > 400)) {
		// curPage++;
		// if (curPage > totalPage) {
		// Toast.makeText(getApplicationContext(), " 没有更多了",
		// Toast.LENGTH_SHORT).show();
		// // // listinfoagain();
		// } else {
		// getdatalist(curPage);
		// Toast.makeText(getApplicationContext(), "正在加载下一页",
		// Toast.LENGTH_SHORT).show();
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
		return false;
	}

	// @Override
	// public void onItemClick(AdapterView<?> parent, View view, int position,
	// long id) {
	// // TODO Auto-generated method stub
	// // recommendModel data = list.get(position - 1);
	// // Intent intent = new Intent();
	// // intent.setClass(getApplicationContext(), SpecialDetailActivity.class);
	// // Bundle bundle = new Bundle();
	// // bundle.putString("Title", data.getTitle());
	// // bundle.putString("detail", data.getDetail());
	// // bundle.putString("Time", data.getTime());
	// // bundle.putString("Name", "名字");
	// // intent.putExtras(bundle);
	// // startActivity(intent);
	// // Toast.makeText(getApplicationContext(), "点击第" + position + "条" +
	// "item",
	// // Toast.LENGTH_SHORT).show();
	// Intent intent = new Intent();
	// intent.setClass(getApplicationContext(), DetailActivity.class);
	// Bundle bundle = new Bundle();
	// bundle.putInt("source", R.drawable.detail);
	// bundle.putInt("height", 3048);
	// bundle.putInt("width", 750);
	// intent.putExtras(bundle);
	// startActivity(intent);
	// }

	private void setheadtextview() {
		headTextView = new TextView(getApplicationContext());
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

				imagePPTModel listinfo = new imagePPTModel();
				listinfo.setTime("2017-08-30");
				listinfo.setTitle("杭州地区地铁项目");
				listinfo.setContent("着眼明确基本标准、树立行为规范、逐条逐句通读党章、为人民做表率。");
				listinfo.setGuanzhu("231");
				listinfo.setZan("453");
				listinfo.setRead(read[i]);
				listinfo.setImageurl(headimg[i]);
				if (i % 2 == 0) {
					listinfo.setHeadimgUrl("/2017/08/07/894263289180196864.png");
				} else {
					listinfo.setHeadimgUrl("/2017/08/07/894265226881536000.png");

				}

				listinfo.setWidth(screenwidth);
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
		mAdapter = new imagePPTAdapter(this, list, ListData, this);
		ListData.setAdapter(mAdapter);
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
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if ((keyCode == KeyEvent.KEYCODE_BACK)) {
			if (true) {
				sendpost();
				finish();

				final ArrayList ArrayValues = new ArrayList();
				// ArrayValues.add(new BasicNameValuePair("ticket",
				// ticket));
				// ArrayValues.add(new BasicNameValuePair("applyType",
				// "" + 2));
				// ArrayValues.add(new BasicNameValuePair("helpSType",
				// "" + type));
				// ArrayValues.add(new BasicNameValuePair("modelSign",
				// "KNDY_APPLY"));
				// ArrayValues.add(new BasicNameValuePair("curPage", ""
				// + curPage));
				// ArrayValues.add(new BasicNameValuePair("mobile", "" +
				// text_phone.getText().toString()));
				// final ArrayList ArrayValues = new ArrayList();
				ArrayValues.add(new BasicNameValuePair("ticket", "" + ticket));
				// chn = GetChannelByKey.GetSign(PreALLChannel, "职工之家");
				// ArrayValues.add(new BasicNameValuePair("chn",
				// "dyq"));
				// chn = "dyq";
				ArrayValues.add(new BasicNameValuePair("learnRecordDto.title", Title));
				ArrayValues.add(new BasicNameValuePair("learnRecordDto.timeLength", "" + (recLen / 60)));
				ArrayValues.add(new BasicNameValuePair("learnRecordDto.cover", "" + cover));
				ArrayValues.add(new BasicNameValuePair("learnRecordDto.content", "" + detail));
				// ArrayValues.add(new BasicNameValuePair("classify", ""
				// +
				// classify));

				new Thread(new Runnable() { // 开启线程上传文件
					@Override
					public void run() {
						String DueData = "";
						DueData = HttpGetData.GetData("api/pb/learnRecord/save", ArrayValues);
						Message msg = new Message();
						msg.obj = DueData;
						msg.what = 17;
						uiHandler.sendMessage(msg);
					}
				}).start();

			}
			return false;
		} else {
			return super.onKeyDown(keyCode, event);
		}

	}

	private void sendpost() {
		// TODO Auto-generated method stub
		final ArrayList ArrayValues = new ArrayList();
		ArrayValues.add(new BasicNameValuePair("userScoreDto.inOut", "1"));
		ArrayValues.add(new BasicNameValuePair("userScoreDto.classify", "specialActivity"));
		ArrayValues.add(new BasicNameValuePair("userScoreDto.amount", "2"));
		ArrayValues.add(new BasicNameValuePair("userScoreDto.reason", "网上党校学习《" + Title+"》"));
		ArrayValues.add(new BasicNameValuePair("ticket", ticket));
		new Thread(new Runnable() { // 开启线程上传文件
			@Override
			public void run() {
				HttpGetData.GetData("api/console/userScore/save", ArrayValues);

			}
		}).start();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.image_back:
			sendpost();
			finish();

			final ArrayList ArrayValues = new ArrayList();
			// ArrayValues.add(new BasicNameValuePair("ticket",
			// ticket));
			// ArrayValues.add(new BasicNameValuePair("applyType",
			// "" + 2));
			// ArrayValues.add(new BasicNameValuePair("helpSType",
			// "" + type));
			// ArrayValues.add(new BasicNameValuePair("modelSign",
			// "KNDY_APPLY"));
			// ArrayValues.add(new BasicNameValuePair("curPage", ""
			// + curPage));
			// ArrayValues.add(new BasicNameValuePair("mobile", "" +
			// text_phone.getText().toString()));
			// final ArrayList ArrayValues = new ArrayList();
			ArrayValues.add(new BasicNameValuePair("ticket", "" + ticket));
			// chn = GetChannelByKey.GetSign(PreALLChannel, "职工之家");
			// ArrayValues.add(new BasicNameValuePair("chn",
			// "dyq"));
			// chn = "dyq";
			ArrayValues.add(new BasicNameValuePair("learnRecordDto.title", Title));
			ArrayValues.add(new BasicNameValuePair("learnRecordDto.timeLength", "" + (recLen / 60)));
			ArrayValues.add(new BasicNameValuePair("learnRecordDto.cover", "" + cover));
			ArrayValues.add(new BasicNameValuePair("learnRecordDto.content", "" + detail));
			// ArrayValues.add(new BasicNameValuePair("classify", ""
			// +
			// classify));

			new Thread(new Runnable() { // 开启线程上传文件
				@Override
				public void run() {
					String DueData = "";
					DueData = HttpGetData.GetData("api/pb/learnRecord/save", ArrayValues);
					// Message msg = new Message();
					// msg.obj = DueData;
					// msg.what = 17;
					// uiHandler.sendMessage(msg);
				}
			}).start();

			break;
		case R.id.lin_fujian:
			// finish();
			if (true) {

				Intent intent = new Intent();
				intent.setAction("android.intent.action.VIEW");
				Uri content_url = Uri.parse(filePath);
				intent.setData(content_url);
				startActivity(intent);

			}

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
			imagePPTModel data = list.get((Integer) v.getTag());

			Intent intent = new Intent();
			intent.setClass(getApplicationContext(), StandardImageXML.class);
			Bundle bundle = new Bundle();
			bundle.putString("url", data.getHeadimgUrl());
			bundle.putInt("inturl", data.getImageurl());

			intent.putExtras(bundle);
			startActivity(intent);
			// Toast.makeText(getApplicationContext(), "删除第" + (Integer)
			// v.getTag() + "条",
			// Toast.LENGTH_SHORT).show();
			break;
		default:
			break;
		}
	}
}
