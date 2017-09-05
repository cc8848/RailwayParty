package wuxc.single.railwayparty.my;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import wuxc.single.railwayparty.R;
import wuxc.single.railwayparty.layout.Childviewpaper;

public class MypublishActivity extends FragmentActivity implements OnClickListener {
	private TextView text_1;

	private TextView text_2;
	private Childviewpaper ViewPaper;
	public List<Fragment> Fragments = new ArrayList<Fragment>();
	private FragmentManager FragmentManager;
	private int NumberPicture = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.wuxc_activity_mypublish_change);
		ImageView image_back = (ImageView) findViewById(R.id.image_back);
		image_back.setOnClickListener(this);
		initview();

		initcolor();
		ViewPaper = (Childviewpaper) findViewById(R.id.viewPager);
		Fragments.clear();// 清空list
		initfragment();// list 装填fragment
		FragmentManager = getSupportFragmentManager();
		ViewPaper.setOffscreenPageLimit(NumberPicture);
		ViewPaper.setOnPageChangeListener(new MyOnPageChangeListener());
		ViewPaper.setAdapter(new MyPagerAdapter());
	}

	private void initfragment() {
		// TODO Auto-generated method stub
		Fragments.add(new MyPublishFragment());
		// Fragments.add(new FragmentMyLearn());

	}

	private class MyPagerAdapter extends PagerAdapter {
		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

		@Override
		public int getCount() {
			return NumberPicture;
		}

		@Override
		public void destroyItem(View container, int position, Object object) {
			((ViewPager) container).removeView(Fragments.get(position).getView());
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			Fragment fragment = Fragments.get(position);
			if (!fragment.isAdded()) {
				FragmentTransaction ft = FragmentManager.beginTransaction();
				ft.add(fragment, fragment.getClass().getSimpleName());
				ft.commit();
				FragmentManager.executePendingTransactions();
			}

			if (fragment.getView().getParent() == null) {
				container.addView(fragment.getView());
			}
			return fragment.getView();
		}
	};

	public class MyOnPageChangeListener implements OnPageChangeListener {
		@Override
		public void onPageSelected(int arg0) {
			switch (arg0) {
			case 0:
				clearcolor();
				text_1.setTextColor(Color.parseColor("#ffffff"));
				text_1.setBackgroundResource(R.drawable.shape18red);
				break;
			case 1:
				clearcolor();
				text_2.setTextColor(Color.parseColor("#ffffff"));
				text_2.setBackgroundResource(R.drawable.shape18red);
				break;

			default:
				break;
			}
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
		}

		@Override
		public void onPageScrollStateChanged(int arg0) {
		}
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

	private void initview() {
		// TODO Auto-generated method stub
		text_1 = (TextView) findViewById(R.id.text_1);
		text_2 = (TextView) findViewById(R.id.text_2);

		text_1.setOnClickListener(this);
		text_2.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.image_back:
			finish();
			break;
		case R.id.text_1:
			clearcolor();
			text_1.setTextColor(Color.parseColor("#ffffff"));
			text_1.setBackgroundResource(R.drawable.shape18red);
			ViewPaper.setCurrentItem(0);
			break;
		case R.id.text_2:
			clearcolor();
			text_2.setTextColor(Color.parseColor("#ffffff"));
			text_2.setBackgroundResource(R.drawable.shape18red);
			ViewPaper.setCurrentItem(1);
			break;
		default:
			break;
		}
	}
}

// package wuxc.single.railwayparty.my;
//
// import java.util.ArrayList;
// import java.util.List;
//
// import org.apache.http.message.BasicNameValuePair;
// import org.json.JSONArray;
// import org.json.JSONException;
// import org.json.JSONObject;
//
// import android.app.Activity;
// import android.content.Context;
// import android.content.SharedPreferences;
// import android.graphics.Typeface;
// import android.os.Bundle;
// import android.os.Handler;
// import android.os.Message;
// import android.util.Log;
// import android.view.Gravity;
// import android.view.MotionEvent;
// import android.view.View;
// import android.view.View.OnClickListener;
// import android.view.View.OnTouchListener;
// import android.widget.AdapterView;
// import android.widget.AdapterView.OnItemClickListener;
// import android.widget.ImageView;
// import android.widget.ListView;
// import android.widget.TextView;
// import android.widget.Toast;
// import wuxc.single.railwayparty.R;
// import wuxc.single.railwayparty.adapter.MypublishAdapter;
// import wuxc.single.railwayparty.adapter.MypublishAdapter.Callback;
// import wuxc.single.railwayparty.internet.HttpGetData;
// import wuxc.single.railwayparty.model.MypublishModel;
// import wuxc.single.railwayparty.model.MypublishModel;
//
// public class MypublishActivity extends Activity
// implements OnClickListener, Callback, OnTouchListener, OnItemClickListener {
// private ListView ListData;
// List<MypublishModel> list = new ArrayList<MypublishModel>();
// private static MypublishAdapter mAdapter;
// private int firstItemIndex = 0;
// private int lastItemIndex = 0;
// private float startY = 0;
// private float startYfoot = 0;
// private boolean isRecored;
// private boolean isRecoredfoot;
// private int pageSize = 10;
// private int totalPage = 10;
// private int curPage = 1;
// private final static int RATIO = 2;
// private TextView headTextView = null;
// private boolean[] read = { false, false, false, true, true, true, true, true,
// true, true, true, true, true, true,
// true, true, true, true, true, true };
// private String ticket="";
// private String chn;
// private String userPhoto;
// private String LoginId;
// private SharedPreferences PreUserInfo;// 存储个人信息
// private SharedPreferences PreALLChannel;// 存储所用频道信息
// private static final String GET_SUCCESS_RESULT = "success";
// private static final String GET_FAIL_RESULT = "fail";
// private static final int GET_DUE_DATA = 6;
// private TextView TextArticle;
// private TextView TextVideo;
// private int type = 2;
// private String classify = "";
// public Handler uiHandler = new Handler() {
// @Override
// public void handleMessage(Message msg) {
// switch (msg.what) {
// case GET_DUE_DATA:
// GetDataDueData(msg.obj);
// break;
// default:
// break;
// }
// }
// };
//
// protected void GetDataDueData(Object obj) {
//
// // TODO Auto-generated method stub
// String Type = null;
// String Data = null;
// String pager = null;
// try {
// JSONObject demoJson = new JSONObject(obj.toString());
// Type = demoJson.getString("type");
// // pager = demoJson.getString("pager");
// Data = demoJson.getString("datas");
// if (Type.equals(GET_SUCCESS_RESULT)) {
// GetPager(Data);
// GetDataList(Data, curPage);
// } else if (Type.equals(GET_FAIL_RESULT)) {
// Toast.makeText(getApplicationContext(), "服务器数据失败",
// Toast.LENGTH_SHORT).show();
// } else {
// Toast.makeText(getApplicationContext(), "数据格式校验失败",
// Toast.LENGTH_SHORT).show();
// }
// } catch (JSONException e) {
// // TODO Auto-generated catch block
// e.printStackTrace();
// } catch (Exception e) {
// // TODO: handle exception
// }
// }
//
// private void GetDataList(String data, int arg) {
// ;
// if (arg == 1) {
// list.clear();
// }
// JSONArray jArray = null;
// try {
// jArray = new JSONArray(data);
// JSONObject json_data = null;
// if (jArray.length() == 0) {
// // / Toast.makeText(getApplicationContext(), "无数据",
// // Toast.LENGTH_SHORT).show();
//
// } else {
// for (int i = 0; i < jArray.length(); i++) {
// json_data = jArray.getJSONObject(i);
// Log.e("json_data", "" + json_data);
// // json_data = json_data.getJSONObject("data");
// MypublishModel listinfo = new MypublishModel();
// listinfo.setTime(json_data.getString("createtime"));
// listinfo.setTitle("跳动的音符");
// listinfo.setNumber(jArray.length());
// if (json_data.getString("classify").equals("1")) {
// listinfo.setLabel("党内活动");
//
// } else if (json_data.getString("classify").equals("2")) {
// listinfo.setLabel("党员教育");
// } else {
// listinfo.setLabel("党员生活");
// }
// listinfo.setDetail(json_data.getString("content"));
//
// list.add(listinfo);
//
// }
// }
//
// } catch (JSONException e) {
// // TODO Auto-generated catch block
// e.printStackTrace();
// }
//
// if (arg == 1) {
// go();
// } else {
// mAdapter.notifyDataSetChanged();
// }
//
// }
//
// private void GetPager(String pager) {
// // TODO Auto-generated method stub
// try {
// JSONObject demoJson = new JSONObject(pager);
//
// totalPage = demoJson.getInt("totalPage");
//
// } catch (JSONException e) {
// // TODO Auto-generated catch block
// e.printStackTrace();
// } catch (Exception e) {
// // TODO: handle exception
// }
// }
//
// @Override
// protected void onCreate(Bundle savedInstanceState) {
// // TODO Auto-generated method stub
// super.onCreate(savedInstanceState);
// setContentView(R.layout.wuxc_activity_mypublish);
// ImageView image_back = (ImageView) findViewById(R.id.image_back);
// image_back.setOnClickListener(this);
// initview();
// setonclicklistener();
// setheadtextview();
// // getdatalist(curPage);
// PreUserInfo = getApplicationContext().getSharedPreferences("UserInfo",
// Context.MODE_PRIVATE);
// ReadTicket();
// GetData();
// }
//
// private void setheadtextview() {
// headTextView = new TextView(getApplicationContext());
// headTextView.setGravity(Gravity.CENTER);
// headTextView.setMinHeight(100);
// headTextView.setText("正在刷新...");
// headTextView.setTypeface(Typeface.DEFAULT_BOLD);
// headTextView.setTextSize(15);
// headTextView.invalidate();
// ListData.addHeaderView(headTextView, null, false);
// ListData.setPadding(0, -100, 0, 0);
// ListData.setOnTouchListener(this);
// }
//
// private void getdatalist(int arg) {
// if (arg == 1) {
// list.clear();
// }
// // TODO Auto-generated method stub
//
// try {
//
// for (int i = 0; i < 10; i++) {
//
// MypublishModel listinfo = new MypublishModel();
// listinfo.setTime("2017-09-10");
// listinfo.setTitle("跳动的音符");
// listinfo.setNumber(50);
// listinfo.setLabel("文化教育");
// listinfo.setDetail(
// "与其烦恼，不如顺其自然。人生，总让人无语，笑的时候，不一定开心，不要炫耀;在没人欣赏自己才能的时候，不要气馁;路走不通时，路旁边还有路，无需解释时，沉默就是金。，有些人、有些事，有些风景，一旦入心，即便霎那，也是永恒。人生，由我不由天，幸福，由心不由境!当疲惫的时候，那就停下脚步，每当困惑的时候，那就停下脚步，梳理纷乱的思绪，驱走迷茫再上路;每当痛苦的时候，那就停下脚步，心不静，幸福就来不了!");
//
// list.add(listinfo);
//
// }
// } catch (Exception e) {
// // TODO Auto-generated catch block
// e.printStackTrace();
// }
// if (arg == 1) {
// go();
// } else {
// mAdapter.notifyDataSetChanged();
// }
//
// }
//
// protected void go() {
// ListData.setPadding(0, -100, 0, 0);
// mAdapter = new MypublishAdapter(this, list, ListData, this);
// ListData.setAdapter(mAdapter);
// }
//
// private void setonclicklistener() {
// // TODO Auto-generated method stub
// ListData.setOnItemClickListener(this);
// }
//
// private void GetData() {
// // TODO Auto-generated method stub
//
// // TODO Auto-generated method stub
// final ArrayList ArrayValues = new ArrayList();
// // ArrayValues.add(new BasicNameValuePair("ticket", ticket));
// // ArrayValues.add(new BasicNameValuePair("applyType", "" + 2));
// // ArrayValues.add(new BasicNameValuePair("helpSType", "" + type));
// // ArrayValues.add(new BasicNameValuePair("modelSign", "KNDY_APPLY"));
// // ArrayValues.add(new BasicNameValuePair("curPage", "" + curPage));
// // ArrayValues.add(new BasicNameValuePair("pageSize", "" + pageSize));
// // final ArrayList ArrayValues = new ArrayList();
// ArrayValues.add(new BasicNameValuePair("ticket", "" + ticket));
// // chn = GetChannelByKey.GetSign(PreALLChannel, "职工之家");
// // ArrayValues.add(new BasicNameValuePair("chn", "dyq"));
// // chn = "dyq";
// ArrayValues.add(new BasicNameValuePair("curPage", "" + curPage));
// ArrayValues.add(new BasicNameValuePair("pageSize", "" + pageSize));
// // ArrayValues.add(new BasicNameValuePair("classify", "" + classify));
//
// new Thread(new Runnable() { // 开启线程上传文件
// @Override
// public void run() {
// String DueData = "";
// DueData = HttpGetData.GetData("api/pb/tiezi/getListJsonData", ArrayValues);
// Message msg = new Message();
// msg.obj = DueData;
// msg.what = GET_DUE_DATA;
// uiHandler.sendMessage(msg);
// }
// }).start();
//
// }
//
// private void ReadTicket() {
// // TODO Auto-generated method stub
// ticket = PreUserInfo.getString("ticket", "");
// userPhoto = PreUserInfo.getString("userPhoto", "");
// LoginId = PreUserInfo.getString("userName", "");
// }
//
// // private void initview(View view2) {
// // // TODO Auto-generated method stub
// // ListData = (ListView) view.findViewById(R.id.list_data);
// // }
// //
// // private void setonclicklistener() {
// // // TODO Auto-generated method stub
// // ListData.setOnItemClickListener(this);
// // }
//
// @Override
// public boolean onTouch(View v, MotionEvent event) {
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
// Toast.makeText(getApplicationContext(), "正在刷新", Toast.LENGTH_SHORT).show();
// GetData();
// }
// int temp = 1;
// temp = (lastItemIndex) % pageSize;
// // temp = 0;
// if (temp == 0 && (startYfoot - tempyfoot > 400)) {
// curPage++;
// if (curPage > totalPage) {
// Toast.makeText(getApplicationContext(), " 没有更多了", Toast.LENGTH_SHORT).show();
// // // listinfoagain();
// } else {
// GetData();
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
// ListData.setPadding(0, -100, 0, (int) ((startYfoot - tempyfoot) / RATIO));
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
// public void onItemClick(AdapterView<?> parent, View view, int position, long
// id) {
// // TODO Auto-generated method stub
// // recommendModel data = list.get(position - 1);
// // Intent intent = new Intent();
// // intent.setClass(getApplicationContext(),
// // SpecialDetailActivity.class);
// // Bundle bundle = new Bundle();
// // bundle.putString("Title", data.getTitle());
// // bundle.putString("detail", data.getDetail());
// // bundle.putString("Time", data.getTime());
// // bundle.putString("Name", "名字");
// // intent.putExtras(bundle);
// // startActivity(intent);
// // Toast.makeText(getApplicationContext(), "点击第" + position + "条" +
// // "item", Toast.LENGTH_SHORT).show();
// }
//
// private void initview() {
// // TODO Auto-generated method stub
// ListData = (ListView) findViewById(R.id.list_data);
// }
//
// @Override
// public void onClick(View v) {
// // TODO Auto-generated method stub
// switch (v.getId()) {
// case R.id.image_back:
// finish();
// break;
//
// default:
// break;
// }
// }
//
// @Override
// public void click(View v) {
// // TODO Auto-generated method stub
// switch (v.getId()) {
// case R.id.image_delete:
// Toast.makeText(getApplicationContext(), "删除第" + (Integer) v.getTag() + "条",
// Toast.LENGTH_SHORT).show();
// break;
// default:
// break;
// }
// }
//
// }
