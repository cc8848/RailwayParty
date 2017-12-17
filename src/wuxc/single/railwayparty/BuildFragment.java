package wuxc.single.railwayparty;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.railwayparty.picfragment.b1;
import com.example.railwayparty.picfragment.b10;
import com.example.railwayparty.picfragment.b2;
import com.example.railwayparty.picfragment.b3;
import com.example.railwayparty.picfragment.b4;
import com.example.railwayparty.picfragment.b5;
import com.example.railwayparty.picfragment.b6;
import com.example.railwayparty.picfragment.b7;
import com.example.railwayparty.picfragment.b8;
import com.example.railwayparty.picfragment.b9;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Color;
import android.location.Geocoder;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import wuxc.single.railwayparty.MainFragment.RequestTimerTask;
import wuxc.single.railwayparty.fragment.BuildFragment1;
import wuxc.single.railwayparty.fragment.BuildFragment2;
import wuxc.single.railwayparty.fragment.BuildFragment3;
import wuxc.single.railwayparty.fragment.BuildFragment4;
import wuxc.single.railwayparty.fragment.BuildFragment5;
import wuxc.single.railwayparty.fragment.BuildFragment6;
import wuxc.single.railwayparty.internet.APPVersion;
import wuxc.single.railwayparty.internet.GetUnreadNumber;
import wuxc.single.railwayparty.internet.HttpGetData;
import wuxc.single.railwayparty.layout.Childviewpaper;
import wuxc.single.railwayparty.main.WebLearnActivity;
import wuxc.single.railwayparty.main.ZDJYW;
import wuxc.single.railwayparty.main.ZDYFC;
import wuxc.single.railwayparty.main.ZSZRD;
import wuxc.single.railwayparty.main.ZTZGG;
import wuxc.single.railwayparty.main.ZZDWJ;
import wuxc.single.railwayparty.other.LoginActivity;
import wuxc.single.railwayparty.other.SearchActivity;
import wuxc.single.railwayparty.start.guidePageActivity.MyOnPageChangeListener;

public class BuildFragment extends MainBaseFragment implements OnClickListener {
	private int screenwidth = 0;
	private RelativeLayout main_top_bac;
	private RelativeLayout rel_1;
	private RelativeLayout rel_2;
	private RelativeLayout rel_3;
	private RelativeLayout rel_4;
	private RelativeLayout rel_5;
	private RelativeLayout rel_6;
	private TextView text_1;
	private TextView text_number_1;
	private TextView text_below_1;
	private TextView text_2;
	private TextView text_number_2;
	private TextView text_below_2;
	private TextView text_3;
	private TextView text_number_3;
	private TextView text_below_3;
	private TextView text_4;
	private TextView text_number_4;
	private TextView text_below_4;
	private TextView text_5;
	private TextView text_number_5;
	private TextView text_below_5;
	private TextView text_6;
	private TextView text_number_6;
	private TextView text_below_6;
	private Childviewpaper ViewPaper;
	public List<Fragment> Fragments = new ArrayList<Fragment>();
	private FragmentManager FragmentManager;
	private int NumberPicture = 6;
	private boolean doubleclick1 = false;
	private boolean doubleclick2 = false;
	private boolean doubleclick3 = false;
	private boolean doubleclick4 = false;
	private boolean doubleclick5 = false;
	private boolean doubleclick6 = false;
	private LinearLayout lin_select;
	private TextView btn_1;
	private TextView btn_2;
	private TextView btn_3;
	private TextView btn_4;
	private TextView btn_5;
	private TextView btn_6;
	private TextView btn_7;
	public int sys = 0;
	private int t1 = 0;
	private int t2 = 0;
	private int t3 = 0;
	private int t4 = 0;
	private int t5 = 0;
	private int t6 = 0;
	private SharedPreferences PreUserInfo;// 存储个人信息
	private SharedPreferences ItemNumber;
	private static final int GET_VERSION_RESULT = 5;
	private Childviewpaper ViewPaper1;
	public List<Fragment> Fragments1 = new ArrayList<Fragment>();
	private FragmentManager FragmentManager1;
	private int NumberPicture1 = 0;
	private View view;// 缓存Fragment view
	// private Handler uiHandler = new Handler() {
	// @Override
	// public void handleMessage(Message msg) {
	// switch (msg.what) {
	//
	// case GET_VERSION_RESULT:
	// GetDataDetailFromVersion(msg.obj);
	// break;
	// default:
	// break;
	// }
	// }
	// };

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		if (null != view) {
			ViewGroup parent = (ViewGroup) view.getParent();
			if (null != parent) {
				parent.removeView(view);
			}
		} else {
			view = inflater.inflate(R.layout.wuxc_fragment_build, container, false);
			screenwidth = getActivity().getWindow().getWindowManager().getDefaultDisplay().getWidth();
			initview(view);
			initheight(view);
			PreUserInfo = getActivity().getSharedPreferences("UserInfo", Context.MODE_PRIVATE);
			ItemNumber = getActivity().getSharedPreferences("ItemNumber", Context.MODE_PRIVATE);

			initcolor();
			ViewPaper = (Childviewpaper) view.findViewById(R.id.viewPager);
			Fragments.clear();// 清空list
			initfragment();// list 装填fragment
			FragmentManager = getActivity().getSupportFragmentManager();
			ViewPaper.setOffscreenPageLimit(NumberPicture);
			ViewPaper.setOnPageChangeListener(new MyOnPageChangeListener());
			ViewPaper.setAdapter(new MyPagerAdapter());
			ImageView image_search = (ImageView) view.findViewById(R.id.image_search);
			image_search.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent intent = new Intent();
					intent.setClass(getActivity(), SearchActivity.class);
					startActivity(intent);
				}
			});
			String ticket = PreUserInfo.getString("ticket", "");
			NumberPicture1 = PreUserInfo.getInt("b_number", 0);
			if (NumberPicture1 > 10) {
				NumberPicture1 = 10;
			}
			ViewPaper1 = (Childviewpaper) view.findViewById(R.id.viewPager1);
			Fragments1.clear();// 清空list
			initfragment1();// list 装填fragment
			FragmentManager1 = getActivity().getSupportFragmentManager();
			ViewPaper1.setOffscreenPageLimit(NumberPicture1);
			ViewPaper1.setOnPageChangeListener(new MyOnPageChangeListener1());
			ViewPaper1.setAdapter(new MyPagerAdapter1());
			// final ArrayList ArrayValues = new ArrayList();
			// ArrayValues.add(new BasicNameValuePair("ticket", "" + ticket));
			// ArrayValues.add(new BasicNameValuePair("chns",
			// "zdwj,djyw,tzgg,szrd,dyfc,wsdx"));
			// new Thread(new Runnable() { // 开启线程上传文件
			// @Override
			// public void run() {
			// String LoginResultData = "";
			// LoginResultData =
			// HttpGetData.GetData("api/cms/accessRecord/getUnReadStatics",
			// ArrayValues);
			// Message msg = new Message();
			// msg.obj = LoginResultData;
			// msg.what = GET_VERSION_RESULT;
			// uiHandler.sendMessage(msg);
			// }
			// }).start();
		}
		return view;
	}

	private int recLen = 0;
	Timer timer = new Timer();

	private void timego() {
		// TODO Auto-generated method stub
		timer.schedule(new RequestTimerTask(), 3000, 3000); // timeTask

	}

	class RequestTimerTask extends TimerTask {
		public void run() {
			Log.d("mainfragmnet", "timer on schedule"+recLen);
			recLen++;
			Message message = new Message();
			message.what = 1;
			handler.sendMessage(message);

		}
	}

	final Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 1:

				if (recLen < 0) {
					timer.cancel();

				}
				try {
					int cur = recLen % NumberPicture1;
					ViewPaper1.setCurrentItem(cur);
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}
	};@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		try {
			Log.e("mainfragmetn", "onDestroy");
			timer.cancel();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Override
	public void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		try {
			Log.e("mainfragmetn", "onDestroy");
			timer.cancel();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	@Override
	public void onDestroyView() {
		// TODO Auto-generated method stub
		super.onDestroyView();
		try {
			Log.e("mainfragmetn", "onDestroyView");
			timer.cancel();

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		try {
			Log.e("mainfragmetn", "onDestroy");
			timer.cancel();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private void initfragment1() {
		// TODO Auto-generated method stub
		Fragments1.add(new b1());
		Fragments1.add(new b2());
		Fragments1.add(new b3());
		Fragments1.add(new b4());
		Fragments1.add(new b5());
		Fragments1.add(new b6());
		Fragments1.add(new b7());
		Fragments1.add(new b8());
		Fragments1.add(new b9());
		Fragments1.add(new b10());
	}private class MyPagerAdapter1 extends PagerAdapter {
		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

		@Override
		public int getCount() {
			return NumberPicture1;
		}

		@Override
		public void destroyItem(View container, int position, Object object) {
			((ViewPager) container).removeView(Fragments1.get(position).getView());
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			Fragment fragment = Fragments1.get(position);
			if (!fragment.isAdded()) {
				FragmentTransaction ft = FragmentManager1.beginTransaction();
				ft.add(fragment, fragment.getClass().getSimpleName());
				ft.commit();
				FragmentManager1.executePendingTransactions();
			}

			if (fragment.getView().getParent() == null) {
				container.addView(fragment.getView());
			}
			return fragment.getView();
		}
	};

	public class MyOnPageChangeListener1 implements OnPageChangeListener {
		@Override
		public void onPageSelected(int arg0) {

		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
		}

		@Override
		public void onPageScrollStateChanged(int arg0) {
		}
	}
	// protected void GetDataDetailFromVersion(Object obj) {
	// // TODO Auto-generated method stub
	//
	// // TODO Auto-generated method stub
	// String data = null;
	//
	// try {
	// JSONObject demoJson = new JSONObject(obj.toString());
	//
	// data = demoJson.getString("data");
	// demoJson = new JSONObject(data);
	// t1 = demoJson.getInt("zdwj");
	// t2 = demoJson.getInt("djyw");
	// t3 = demoJson.getInt("tzgg");
	// t4 = demoJson.getInt("szrd");
	// t5 = demoJson.getInt("dyfc");
	// t6 = demoJson.getInt("wsdx");
	// intnumber();
	// } catch (JSONException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// } catch (Exception e) {
	// // TODO: handle exception
	// }
	//
	// }

	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		if (view != null) {
			intnumber();
			timer = new Timer();
			timego();
		}
	}

	private void initfragment() {
		// TODO Auto-generated method stub
		Fragments.add(new BuildFragment1());
		Fragments.add(new BuildFragment2());
		Fragments.add(new BuildFragment3());
		Fragments.add(new BuildFragment4());
		Fragments.add(new BuildFragment5());
		Fragments.add(new BuildFragment6());

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
				text_1.setTextColor(Color.parseColor("#cc0502"));
				text_below_1.setBackgroundColor(Color.parseColor("#cc0502"));

				break;
			case 1:
				clearcolor();
				text_2.setTextColor(Color.parseColor("#cc0502"));
				text_below_2.setBackgroundColor(Color.parseColor("#cc0502"));

				break;
			case 2:
				clearcolor();
				text_3.setTextColor(Color.parseColor("#cc0502"));
				text_below_3.setBackgroundColor(Color.parseColor("#cc0502"));

				break;
			case 3:
				clearcolor();
				text_4.setTextColor(Color.parseColor("#cc0502"));
				text_below_4.setBackgroundColor(Color.parseColor("#cc0502"));

				break;
			case 4:
				clearcolor();
				text_5.setTextColor(Color.parseColor("#cc0502"));
				text_below_5.setBackgroundColor(Color.parseColor("#cc0502"));

				break;
			case 5:
				clearcolor();
				text_6.setTextColor(Color.parseColor("#cc0502"));
				text_below_6.setBackgroundColor(Color.parseColor("#cc0502"));

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

	private void intnumber() {
		ItemNumber = getActivity().getSharedPreferences("ItemNumber", Context.MODE_PRIVATE);
		// t1 = ItemNumber.getInt("ZDWJtotal", 100) -
		// ItemNumber.getInt("ZDWJread", 0);
		// t2 = ItemNumber.getInt("DJYWtotal", 100) -
		// ItemNumber.getInt("DJYWread", 0);
		// t3 = ItemNumber.getInt("TZGGtotal", 100) -
		// ItemNumber.getInt("TZGGread", 0);
		// t4 = ItemNumber.getInt("SZRDtotal", 100) -
		// ItemNumber.getInt("SZRDread", 0);
		// Log.e("SZRDtotal", " ItemNumber" + ItemNumber.getInt("SZRDtotal",
		// 100));
		// Log.e("SZRDread", " ItemNumber" + ItemNumber.getInt("SZRDread",
		// 100));
		// t5 = ItemNumber.getInt("DYFCtotal", 100) -
		// ItemNumber.getInt("DYFCread", 0);
		// t6 = ItemNumber.getInt("WSDXtotal", 100) -
		// ItemNumber.getInt("WSDXread", 0);
		t1 = ItemNumber.getInt("ZDWJapi", 0) - ItemNumber.getInt("ZDWJposition", 0);
		t2 = ItemNumber.getInt("DJYWapi", 0) - ItemNumber.getInt("DJYWposition", 0);
		t3 = ItemNumber.getInt("TZGGapi", 0) - ItemNumber.getInt("TZGGposition", 0);
		t4 = ItemNumber.getInt("SZRDapi", 0) - ItemNumber.getInt("SZRDposition", 0);
		Log.e("DJYWapi", " ItemNumber" + ItemNumber.getInt("DJYWapi", 0));
		Log.e("DJYWapi", " ItemNumber" + ItemNumber.getInt("DJYWapi", 0));
		t5 = ItemNumber.getInt("DYFCapi", 0) - ItemNumber.getInt("DYFCposition", 0);
		t6 = ItemNumber.getInt("WSDXapi", 0) - ItemNumber.getInt("WSDXposition", 0);

		text_number_1.setVisibility(View.GONE);
		text_number_2.setVisibility(View.GONE);
		text_number_3.setVisibility(View.GONE);
		text_number_4.setVisibility(View.GONE);
		text_number_5.setVisibility(View.GONE);
		text_number_6.setVisibility(View.GONE);
		if (t1 > 0) {
			text_number_1.setVisibility(View.VISIBLE);

			if (t1 >= 100) {
				text_number_1.setText("");
				text_number_1.setBackgroundResource(R.drawable.morethan100);

			} else {
				text_number_1.setText("" + t1);
				text_number_1.setBackgroundResource(R.drawable.tag);
			}
		}
		if (t2 > 0) {
			text_number_2.setVisibility(View.VISIBLE);
			if (t2 > 99) {
				text_number_2.setText("");
				text_number_2.setBackgroundResource(R.drawable.morethan100);

			} else {
				text_number_2.setText("" + t2);
				text_number_2.setBackgroundResource(R.drawable.tag);
			}

		}
		if (t3 > 0) {
			text_number_3.setVisibility(View.VISIBLE);

			if (t3 > 99) {
				text_number_3.setText("");
				text_number_3.setBackgroundResource(R.drawable.morethan100);

			} else {
				text_number_3.setText("" + t3);
				text_number_3.setBackgroundResource(R.drawable.tag);
			}
		}
		if (t4 > 0) {
			text_number_4.setVisibility(View.VISIBLE);

			if (t4 > 99) {
				text_number_4.setText("");
				text_number_4.setBackgroundResource(R.drawable.morethan100);

			} else {
				text_number_4.setText("" + t4);
				text_number_4.setBackgroundResource(R.drawable.tag);
			}
		}
		if (t5 > 0) {
			text_number_5.setVisibility(View.VISIBLE);

			if (t5 > 99) {
				text_number_5.setText("");
				text_number_5.setBackgroundResource(R.drawable.morethan100);

			} else {
				text_number_5.setText("" + t5);
				text_number_5.setBackgroundResource(R.drawable.tag);
			}
		}
		if (t6 > 0) {
			text_number_6.setVisibility(View.VISIBLE);

			if (t6 > 99) {
				text_number_6.setText("");
				text_number_6.setBackgroundResource(R.drawable.morethan100);

			} else {
				text_number_6.setText("" + t6);
				text_number_6.setBackgroundResource(R.drawable.tag);
			}
		}
	}

	private void initcolor() {
		// TODO Auto-generated method stub
		clearcolor();
		text_1.setTextColor(Color.parseColor("#cc0502"));
		text_below_1.setBackgroundColor(Color.parseColor("#cc0502"));
		text_number_1.setVisibility(View.GONE);
		text_number_2.setVisibility(View.GONE);
		text_number_3.setVisibility(View.GONE);
		text_number_4.setVisibility(View.GONE);
		text_number_5.setVisibility(View.GONE);
		text_number_6.setVisibility(View.GONE);
	}

	private void clearcolor() {
		// TODO Auto-generated method stub
		text_1.setTextColor(Color.parseColor("#7d7d7d"));
		text_2.setTextColor(Color.parseColor("#7d7d7d"));
		text_3.setTextColor(Color.parseColor("#7d7d7d"));
		text_4.setTextColor(Color.parseColor("#7d7d7d"));
		text_5.setTextColor(Color.parseColor("#7d7d7d"));
		text_6.setTextColor(Color.parseColor("#7d7d7d"));
		text_below_1.setBackgroundColor(Color.parseColor("#00ffffff"));
		text_below_2.setBackgroundColor(Color.parseColor("#00ffffff"));
		text_below_3.setBackgroundColor(Color.parseColor("#00ffffff"));
		text_below_4.setBackgroundColor(Color.parseColor("#00ffffff"));
		text_below_5.setBackgroundColor(Color.parseColor("#00ffffff"));
		text_below_6.setBackgroundColor(Color.parseColor("#00ffffff"));
	}

	private void initview(View view) {
		// TODO Auto-generated method stub
		main_top_bac = (RelativeLayout) view.findViewById(R.id.main_top_bac);
		rel_1 = (RelativeLayout) view.findViewById(R.id.rel_1);
		rel_2 = (RelativeLayout) view.findViewById(R.id.rel_2);
		rel_3 = (RelativeLayout) view.findViewById(R.id.rel_3);
		rel_4 = (RelativeLayout) view.findViewById(R.id.rel_4);
		rel_5 = (RelativeLayout) view.findViewById(R.id.rel_5);
		rel_6 = (RelativeLayout) view.findViewById(R.id.rel_6);
		rel_1.setOnClickListener(this);
		rel_2.setOnClickListener(this);
		rel_3.setOnClickListener(this);
		rel_4.setOnClickListener(this);
		rel_5.setOnClickListener(this);
		rel_6.setOnClickListener(this);
		text_1 = (TextView) view.findViewById(R.id.text_1);
		text_number_1 = (TextView) view.findViewById(R.id.text_number_1);
		text_below_1 = (TextView) view.findViewById(R.id.text_below_1);

		text_2 = (TextView) view.findViewById(R.id.text_2);
		text_number_2 = (TextView) view.findViewById(R.id.text_number_2);
		text_below_2 = (TextView) view.findViewById(R.id.text_below_2);

		text_3 = (TextView) view.findViewById(R.id.text_3);
		text_number_3 = (TextView) view.findViewById(R.id.text_number_3);
		text_below_3 = (TextView) view.findViewById(R.id.text_below_3);

		text_4 = (TextView) view.findViewById(R.id.text_4);
		text_number_4 = (TextView) view.findViewById(R.id.text_number_4);
		text_below_4 = (TextView) view.findViewById(R.id.text_below_4);

		text_5 = (TextView) view.findViewById(R.id.text_5);
		text_number_5 = (TextView) view.findViewById(R.id.text_number_5);
		text_below_5 = (TextView) view.findViewById(R.id.text_below_5);

		text_6 = (TextView) view.findViewById(R.id.text_6);
		text_number_6 = (TextView) view.findViewById(R.id.text_number_6);
		text_below_6 = (TextView) view.findViewById(R.id.text_below_6);

		lin_select = (LinearLayout) view.findViewById(R.id.lin_select);
		btn_1 = (TextView) view.findViewById(R.id.btn_1);
		btn_2 = (TextView) view.findViewById(R.id.btn_2);
		btn_3 = (TextView) view.findViewById(R.id.btn_3);
		btn_4 = (TextView) view.findViewById(R.id.btn_4);
		btn_5 = (TextView) view.findViewById(R.id.btn_5);
		btn_6 = (TextView) view.findViewById(R.id.btn_6);
		btn_7 = (TextView) view.findViewById(R.id.btn_7);
		btn_1.setOnClickListener(this);
		btn_2.setOnClickListener(this);
		btn_3.setOnClickListener(this);
		btn_4.setOnClickListener(this);
		btn_5.setOnClickListener(this);
		btn_6.setOnClickListener(this);
		btn_7.setOnClickListener(this);
		lin_select.setOnClickListener(this);
		lin_select.setVisibility(View.GONE);
	}

	private void initheight(View view) {
		// TODO Auto-generated method stub
		int height = (int) (screenwidth * 300 / 750);
		RelativeLayout.LayoutParams LayoutParams2 = (android.widget.RelativeLayout.LayoutParams) main_top_bac
				.getLayoutParams();
		LayoutParams2.height = height;
		main_top_bac.setLayoutParams(LayoutParams2);

	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();

		MainActivity.curFragmentTag = getString(R.string.str_build);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.rel_1:
			clearcolor();
			text_1.setTextColor(Color.parseColor("#cc0502"));
			text_below_1.setBackgroundColor(Color.parseColor("#cc0502"));
			ViewPaper.setCurrentItem(0);
			lin_select.setVisibility(View.VISIBLE);
			t1 = 0;
			intnumber();
			// if (doubleclick1) {
			// Intent intent_rel_weblearn = new Intent();
			// intent_rel_weblearn.setClass(getActivity(), ZZDWJ.class);
			// startActivity(intent_rel_weblearn);
			// }
			// doubleclick1 = true;
			// starttimedelay1();
			break;
		case R.id.rel_2:
			clearcolor();
			text_2.setTextColor(Color.parseColor("#cc0502"));
			text_below_2.setBackgroundColor(Color.parseColor("#cc0502"));
			ViewPaper.setCurrentItem(1);
			lin_select.setVisibility(View.GONE);
			t2 = 0;
			intnumber();
			// if (doubleclick2) {
			// Intent intent_rel_weblearn = new Intent();
			// intent_rel_weblearn.setClass(getActivity(), ZDJYW.class);
			// startActivity(intent_rel_weblearn);
			// }
			// doubleclick2 = true;
			// starttimedelay2();
			break;
		case R.id.rel_3:
			clearcolor();
			text_3.setTextColor(Color.parseColor("#cc0502"));
			text_below_3.setBackgroundColor(Color.parseColor("#cc0502"));
			ViewPaper.setCurrentItem(2);
			lin_select.setVisibility(View.GONE);
			t3 = 0;
			intnumber();
			// if (doubleclick3) {lin_select.setVisibility(View.GONE);
			// Intent intent_rel_weblearn = new Intent();
			// intent_rel_weblearn.setClass(getActivity(), ZTZGG.class);
			// startActivity(intent_rel_weblearn);
			// }
			// doubleclick3 = true;
			// starttimedelay3();
			break;
		case R.id.rel_4:
			clearcolor();
			text_4.setTextColor(Color.parseColor("#cc0502"));
			text_below_4.setBackgroundColor(Color.parseColor("#cc0502"));
			ViewPaper.setCurrentItem(3);
			lin_select.setVisibility(View.GONE);
			t4 = 0;
			intnumber();
			// if (doubleclick4) {lin_select.setVisibility(View.GONE);
			// Intent intent_rel_weblearn = new Intent();
			// intent_rel_weblearn.setClass(getActivity(), ZSZRD.class);
			// startActivity(intent_rel_weblearn);
			// }
			// doubleclick4 = true;
			// starttimedelay4();
			break;
		case R.id.rel_5:
			clearcolor();
			text_5.setTextColor(Color.parseColor("#cc0502"));
			text_below_5.setBackgroundColor(Color.parseColor("#cc0502"));
			ViewPaper.setCurrentItem(4);
			lin_select.setVisibility(View.GONE);
			t5 = 0;
			intnumber();
			// if (doubleclick5) {
			// Intent intent_rel_weblearn = new Intent();
			// intent_rel_weblearn.setClass(getActivity(), ZDYFC.class);
			// startActivity(intent_rel_weblearn);
			// }
			// doubleclick5 = true;
			// starttimedelay5();
			break;
		case R.id.rel_6:
			clearcolor();
			text_6.setTextColor(Color.parseColor("#cc0502"));
			text_below_6.setBackgroundColor(Color.parseColor("#cc0502"));
			ViewPaper.setCurrentItem(5);
			lin_select.setVisibility(View.GONE);
			if (doubleclick6) {
				Intent intent_rel_weblearn = new Intent();
				intent_rel_weblearn.setClass(getActivity(), WebLearnActivity.class);
				startActivity(intent_rel_weblearn);
			}
			doubleclick6 = true;
			starttimedelay6();
			t6 = 0;
			intnumber();
			break;
		case R.id.btn_1:
			if (true) {
				// BuildFragment1 buildFragment1 = new BuildFragment1();
				// buildFragment1.setdata(1);
				lin_select.setVisibility(View.GONE);

				Editor edit = PreUserInfo.edit();
				edit.putInt("sys", 1);
				edit.commit();
				starttimedelay();
			}

			break;
		case R.id.btn_2:
			if (true) {
				lin_select.setVisibility(View.GONE);

				Editor edit = PreUserInfo.edit();
				edit.putInt("sys", 5);
				edit.commit();
				starttimedelay();
			}
			break;
		case R.id.btn_3:
			if (true) {
				lin_select.setVisibility(View.GONE);

				Editor edit = PreUserInfo.edit();
				edit.putInt("sys", 4);
				edit.commit();
				starttimedelay();
			}
			break;
		case R.id.btn_4:
			if (true) {
				lin_select.setVisibility(View.GONE);

				Editor edit = PreUserInfo.edit();
				edit.putInt("sys", 2);
				edit.commit();
				starttimedelay();
			}
			break;
		case R.id.btn_5:
			if (true) {
				lin_select.setVisibility(View.GONE);

				Editor edit = PreUserInfo.edit();
				edit.putInt("sys", 3);
				edit.commit();
				starttimedelay();
			}
			break;
		case R.id.btn_6:
			if (true) {
				lin_select.setVisibility(View.GONE);

				Editor edit = PreUserInfo.edit();
				edit.putInt("sys", 6);
				edit.commit();
				starttimedelay();
			}
			break;
		case R.id.btn_7:
			if (true) {
				lin_select.setVisibility(View.GONE);

				Editor edit = PreUserInfo.edit();
				edit.putInt("sys", 7);
				edit.commit();
				starttimedelay();
			}
			break;
		case R.id.lin_select:
			if (true) {

				lin_select.setVisibility(View.GONE);
			}
			break;
		default:
			break;
		}
	}

	// private void starttimedelay1() {
	// // 原因：不延时的话list会滑到顶部
	// Timer timer = new Timer();
	// timer.schedule(new TimerTask() {
	//
	// @Override
	// public void run() {
	// doubleclick1 = false;
	// }
	//
	// }, 300);
	// }

	private void starttimedelay() {
		// 原因：不延时的话list会滑到顶部
		final Timer timer = new Timer();
		timer.schedule(new TimerTask() {

			@Override
			public void run() {

				try {
					Editor edit = PreUserInfo.edit();
					edit.putInt("sys", 0);
					edit.commit();
					timer.cancel();
				} catch (Exception e) {
					// TODO: handle exceptioni

				}

			}

		}, 3000);
	}

	// private void starttimedelay2() {
	// // 原因：不延时的话list会滑到顶部
	// Timer timer = new Timer();
	// timer.schedule(new TimerTask() {
	//
	// @Override
	// public void run() {
	// doubleclick2 = false;
	// }
	//
	// }, 300);
	// }

	// private void starttimedelay3() {
	// // 原因：不延时的话list会滑到顶部
	// Timer timer = new Timer();
	// timer.schedule(new TimerTask() {
	//
	// @Override
	// public void run() {
	// doubleclick3 = false;
	// }
	//
	// }, 300);
	// }

	// private void starttimedelay4() {
	// // 原因：不延时的话list会滑到顶部
	// Timer timer = new Timer();
	// timer.schedule(new TimerTask() {
	//
	// @Override
	// public void run() {
	// doubleclick4 = false;
	// }
	//
	// }, 300);
	// }

	// private void starttimedelay5() {
	// // 原因：不延时的话list会滑到顶部
	// Timer timer = new Timer();
	// timer.schedule(new TimerTask() {
	//
	// @Override
	// public void run() {
	// doubleclick5 = false;
	// }
	//
	// }, 300);
	// }

	private void starttimedelay6() {
		// 原因：不延时的话list会滑到顶部
		final Timer timer = new Timer();
		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				doubleclick6 = false;
				timer.cancel();
			}

		}, 300);
	}
}
