package wuxc.single.railwayparty.start;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import wuxc.single.railwayparty.MainActivity;
import wuxc.single.railwayparty.R;
import wuxc.single.railwayparty.fragment.GuideFragment1;
import wuxc.single.railwayparty.fragment.GuideFragment2;
import wuxc.single.railwayparty.fragment.GuideFragment3;
import wuxc.single.railwayparty.fragment.image1;
import wuxc.single.railwayparty.fragment.image10;
import wuxc.single.railwayparty.fragment.image2;
import wuxc.single.railwayparty.fragment.image3;
import wuxc.single.railwayparty.fragment.image4;
import wuxc.single.railwayparty.fragment.image5;
import wuxc.single.railwayparty.fragment.image6;
import wuxc.single.railwayparty.fragment.image7;
import wuxc.single.railwayparty.fragment.image8;
import wuxc.single.railwayparty.fragment.image9;
import wuxc.single.railwayparty.layout.Childviewpaper;

public class imageshow extends FragmentActivity {
	private Childviewpaper ViewPaper;
	public List<Fragment> Fragments = new ArrayList<Fragment>();
	private FragmentManager FragmentManager;
	private int NumberPicture = 10;
	private ImageView image_dot1;
	private ImageView image_dot2;
	private ImageView image_dot3;
	public static int page = 0;
	public static int bigpage = 0;
	private SharedPreferences PreGuidePage;// 用于确定是否是第一次登录
	private MyPagerAdapter adapter = new MyPagerAdapter();
	private int thispage = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.wuxc_activity_imageshow);
		ViewPaper = (Childviewpaper) findViewById(R.id.viewPager);
		Fragments.clear();// 清空list
		initfragment();// list 装填fragment
		Intent intent = this.getIntent(); // 获取已有的intent对象
		Bundle bundle = intent.getExtras(); // 获取intent里面的bundle对象
		NumberPicture = bundle.getInt("number");
		try {
			thispage = bundle.getInt("page");
		} catch (Exception e) {
			// TODO: handle exception
			thispage = 0;
		}
		FragmentManager = getSupportFragmentManager();
		ViewPaper.setOffscreenPageLimit(NumberPicture);
		ViewPaper.setOnPageChangeListener(new MyOnPageChangeListener());
		ViewPaper.setAdapter(adapter);
		PreGuidePage = getSharedPreferences("GuidePage", Context.MODE_PRIVATE);
		image_dot1 = (ImageView) findViewById(R.id.image_dot1);
		image_dot2 = (ImageView) findViewById(R.id.image_dot2);
		image_dot3 = (ImageView) findViewById(R.id.image_dot3);
		if (thispage != 0 && thispage < 10) {

			ViewPaper.setCurrentItem(thispage);
		}
		// setdot(0);
	}

	private void go_next_page() {
		if (page == 2) {
			Timer timer = new Timer();
			timer.schedule(new TimerTask() {

				@Override
				public void run() {

					if (page == 2) {
						Intent intent = new Intent();
						intent.setClass(getApplicationContext(), startLogoActivity.class);
						startActivity(intent);
						finish();
						Editor edit = PreGuidePage.edit();
						edit.putInt("GuidePage", 1);
						edit.commit();

					}

				}

			}, 1000);
		}

	}

	private void setdot(int i) {
		// TODO Auto-generated method stub
		init_dot();
		switch (i) {
		case 0:
			image_dot1.setImageResource(R.drawable.dott);
			break;
		case 1:
			image_dot2.setImageResource(R.drawable.dott);
			break;
		case 2:
			image_dot3.setImageResource(R.drawable.dott);
			break;
		default:
			break;
		}
	}

	private void init_dot() {
		// TODO Auto-generated method stub
		image_dot1.setImageResource(R.drawable.doth);
		image_dot2.setImageResource(R.drawable.doth);
		image_dot3.setImageResource(R.drawable.doth);
	}

	private void initfragment() {
		// TODO Auto-generated method stub

		Fragments.add(new image1());
		Fragments.add(new image2());
		Fragments.add(new image3());
		Fragments.add(new image4());
		Fragments.add(new image5());
		Fragments.add(new image6());
		Fragments.add(new image7());
		Fragments.add(new image8());
		Fragments.add(new image9());
		Fragments.add(new image10());

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
			setdot(arg0);
			page = arg0;
			// go_next_page();
			int temp = page + 1;
			temp = temp % 10;
			int big = temp / 10;
			if (temp < NumberPicture && big > bigpage) {
				if (temp == 0) {
					bigpage++;
					Fragments.add(new image1());
					Fragments.add(new image2());
					Fragments.add(new image3());
					Fragments.add(new image4());
					Fragments.add(new image5());
					Fragments.add(new image6());
					Fragments.add(new image7());
					Fragments.add(new image8());
					Fragments.add(new image9());
					Fragments.add(new image10());
					NumberPicture = NumberPicture + 10;
					ViewPaper.setOffscreenPageLimit(NumberPicture);
					adapter.notifyDataSetChanged();
				}
			}

		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
		}

		@Override
		public void onPageScrollStateChanged(int arg0) {
		}
	}

}
