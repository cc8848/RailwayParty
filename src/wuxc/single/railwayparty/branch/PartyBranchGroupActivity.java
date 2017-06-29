package wuxc.single.railwayparty.branch;

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
import wuxc.single.railwayparty.my.FragmentMyLearn;
import wuxc.single.railwayparty.my.FragmentTest;

public class PartyBranchGroupActivity extends FragmentActivity implements OnClickListener {
	private TextView text_1;

	private TextView text_2;

	private TextView text_3;

	private TextView text_line_1;
	private TextView text_line_2;
	private TextView text_line_3;
	private Childviewpaper ViewPaper;
	public List<Fragment> Fragments = new ArrayList<Fragment>();
	private FragmentManager FragmentManager;
	private int NumberPicture = 3;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.wuxc_activity_branch_group);
		ImageView image_back = (ImageView) findViewById(R.id.image_back);
		image_back.setOnClickListener(this);
		initview();

		initcolor();
		ViewPaper = (Childviewpaper) findViewById(R.id.viewPager);
		Fragments.clear();// Çå¿Õlist
		initfragment();// list ×°Ìîfragment
		FragmentManager = getSupportFragmentManager();
		ViewPaper.setOffscreenPageLimit(NumberPicture);
		ViewPaper.setOnPageChangeListener(new MyOnPageChangeListener());
		ViewPaper.setAdapter(new MyPagerAdapter());
	}

	private void initfragment() {
		// TODO Auto-generated method stub
		Fragments.add(new FragmentPartygrouptalk());
		Fragments.add(new FragmentPartygroupmember());
		Fragments.add(new FragmentPartygroupfile());

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
				text_line_1.setBackgroundColor(Color.parseColor("#cc0502"));
				break;
			case 1:

				clearcolor();
				text_2.setTextColor(Color.parseColor("#cc0502"));
				text_line_2.setBackgroundColor(Color.parseColor("#cc0502"));

				break;
			case 2:

				clearcolor();
				text_3.setTextColor(Color.parseColor("#cc0502"));
				text_line_3.setBackgroundColor(Color.parseColor("#cc0502"));

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
		text_1.setTextColor(Color.parseColor("#cc0502"));
		text_line_1.setBackgroundColor(Color.parseColor("#cc0502"));
	}

	private void clearcolor() {
		// TODO Auto-generated method stub
		text_1.setTextColor(Color.parseColor("#000000"));
		text_2.setTextColor(Color.parseColor("#000000"));
		text_3.setTextColor(Color.parseColor("#000000"));
		text_line_1.setBackgroundColor(Color.parseColor("#00000000"));
		text_line_2.setBackgroundColor(Color.parseColor("#00000000"));
		text_line_3.setBackgroundColor(Color.parseColor("#00000000"));

	}

	private void initview() {
		// TODO Auto-generated method stub
		text_1 = (TextView) findViewById(R.id.text_1);
		text_2 = (TextView) findViewById(R.id.text_2);
		text_3 = (TextView) findViewById(R.id.text_3);
		text_line_1 = (TextView) findViewById(R.id.text_line_1);
		text_line_2 = (TextView) findViewById(R.id.text_line_2);
		text_line_3 = (TextView) findViewById(R.id.text_line_3);
		text_1.setOnClickListener(this);
		text_2.setOnClickListener(this);
		text_3.setOnClickListener(this);
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
			text_1.setTextColor(Color.parseColor("#cc0502"));
			text_line_1.setBackgroundColor(Color.parseColor("#cc0502"));
			ViewPaper.setCurrentItem(0);
			break;
		case R.id.text_2:
			clearcolor();
			text_2.setTextColor(Color.parseColor("#cc0502"));
			text_line_2.setBackgroundColor(Color.parseColor("#cc0502"));
			ViewPaper.setCurrentItem(1);
			break;
		case R.id.text_3:
			clearcolor();
			text_3.setTextColor(Color.parseColor("#cc0502"));
			text_line_3.setBackgroundColor(Color.parseColor("#cc0502"));
			ViewPaper.setCurrentItem(2);
			break;
		default:
			break;
		}
	}
}
