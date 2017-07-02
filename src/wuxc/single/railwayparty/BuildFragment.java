package wuxc.single.railwayparty;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import wuxc.single.railwayparty.fragment.BuildFragment1;
import wuxc.single.railwayparty.fragment.BuildFragment2;
import wuxc.single.railwayparty.fragment.BuildFragment3;
import wuxc.single.railwayparty.fragment.BuildFragment4;
import wuxc.single.railwayparty.fragment.BuildFragment5;
import wuxc.single.railwayparty.fragment.BuildFragment6;
import wuxc.single.railwayparty.layout.Childviewpaper;
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

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.wuxc_fragment_build, container, false);
		screenwidth = getActivity().getWindow().getWindowManager().getDefaultDisplay().getWidth();
		initview(view);
		initheight(view);
		initcolor();
		ViewPaper = (Childviewpaper) view.findViewById(R.id.viewPager);
		Fragments.clear();// Çå¿Õlist
		initfragment();// list ×°Ìîfragment
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
		return view;
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
	}

	private void initheight(View view) {
		// TODO Auto-generated method stub
		int height = (int) (screenwidth / 1.9);
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
			break;
		case R.id.rel_2:
			clearcolor();
			text_2.setTextColor(Color.parseColor("#cc0502"));
			text_below_2.setBackgroundColor(Color.parseColor("#cc0502"));
			ViewPaper.setCurrentItem(1);
			break;
		case R.id.rel_3:
			clearcolor();
			text_3.setTextColor(Color.parseColor("#cc0502"));
			text_below_3.setBackgroundColor(Color.parseColor("#cc0502"));
			ViewPaper.setCurrentItem(2);
			break;
		case R.id.rel_4:
			clearcolor();
			text_4.setTextColor(Color.parseColor("#cc0502"));
			text_below_4.setBackgroundColor(Color.parseColor("#cc0502"));
			ViewPaper.setCurrentItem(3);
			break;
		case R.id.rel_5:
			clearcolor();
			text_5.setTextColor(Color.parseColor("#cc0502"));
			text_below_5.setBackgroundColor(Color.parseColor("#cc0502"));
			ViewPaper.setCurrentItem(4);
			break;
		case R.id.rel_6:
			clearcolor();
			text_6.setTextColor(Color.parseColor("#cc0502"));
			text_below_6.setBackgroundColor(Color.parseColor("#cc0502"));
			ViewPaper.setCurrentItem(5);
			break;

		default:
			break;
		}
	}

}
