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
import wuxc.single.railwayparty.layout.Childviewpaper;

public class guidePageActivity extends FragmentActivity {
	private Childviewpaper ViewPaper;
	public List<Fragment> Fragments = new ArrayList<Fragment>();
	private FragmentManager FragmentManager;
	private int NumberPicture = 3;
	private ImageView image_dot1;
	private ImageView image_dot2;
	private ImageView image_dot3;
	private int page = 0;
	private SharedPreferences PreGuidePage;// 用于确定是否是第一次登录

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.wuxc_activity_guidepage);
		ViewPaper = (Childviewpaper) findViewById(R.id.viewPager);
		Fragments.clear();// 清空list
		initfragment();// list 装填fragment
		FragmentManager = getSupportFragmentManager();
		ViewPaper.setOffscreenPageLimit(NumberPicture);
		ViewPaper.setOnPageChangeListener(new MyOnPageChangeListener());
		ViewPaper.setAdapter(new MyPagerAdapter());
		PreGuidePage = getSharedPreferences("GuidePage", Context.MODE_PRIVATE);
		image_dot1 = (ImageView) findViewById(R.id.image_dot1);
		image_dot2 = (ImageView) findViewById(R.id.image_dot2);
		image_dot3 = (ImageView) findViewById(R.id.image_dot3);

		setdot(0);
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
		Fragments.add(new GuideFragment1());
		Fragments.add(new GuideFragment2());
		Fragments.add(new GuideFragment3());

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
			go_next_page();
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
		}

		@Override
		public void onPageScrollStateChanged(int arg0) {
		}
	}

}
