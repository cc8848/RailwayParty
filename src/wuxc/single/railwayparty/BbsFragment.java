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
import android.widget.LinearLayout;
import android.widget.TextView;
import wuxc.single.railwayparty.fragment.BbsFragment1;
import wuxc.single.railwayparty.fragment.BbsFragment2;
import wuxc.single.railwayparty.fragment.BbsFragment3;
import wuxc.single.railwayparty.fragment.BbsFragment4;
import wuxc.single.railwayparty.fragment.BbsFragment5;
import wuxc.single.railwayparty.layout.Childviewpaper;
import wuxc.single.railwayparty.other.SearchActivity;

public class BbsFragment extends MainBaseFragment implements OnClickListener {
	private LinearLayout lin_1;
	private ImageView image_1;
	private TextView text_1;
	private LinearLayout lin_2;
	private ImageView image_2;
	private TextView text_2;
	private LinearLayout lin_3;
	private ImageView image_3;
	private TextView text_3;
	private LinearLayout lin_4;
	private ImageView image_4;
	private TextView text_4;
	private LinearLayout lin_5;
	private ImageView image_5;
	private TextView text_5;
	private Childviewpaper ViewPaper;
	public List<Fragment> Fragments = new ArrayList<Fragment>();
	private FragmentManager FragmentManager;
	private int NumberPicture = 5;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.wuxc_fragment_bbs, container, false);
		initview(view);
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
		Fragments.add(new BbsFragment1());
		Fragments.add(new BbsFragment2());
		Fragments.add(new BbsFragment3());
		Fragments.add(new BbsFragment4());
		Fragments.add(new BbsFragment5());

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
				image_1.setImageResource(R.drawable.icon01_dyq);
				text_1.setTextColor(Color.parseColor("#ffffff"));
				break;
			case 1:
				clearcolor();
				image_2.setImageResource(R.drawable.icon02_dyq);
				text_2.setTextColor(Color.parseColor("#ffffff"));
				break;
			case 2:
				clearcolor();
				image_3.setImageResource(R.drawable.icon03_dyq);
				text_3.setTextColor(Color.parseColor("#ffffff"));
				break;
			case 3:
				clearcolor();
				image_4.setImageResource(R.drawable.icon04_dyq);
				text_4.setTextColor(Color.parseColor("#ffffff"));
				break;
			case 4:
				clearcolor();
				image_5.setImageResource(R.drawable.icon05_dyq);
				text_5.setTextColor(Color.parseColor("#ffffff"));
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
		image_1.setImageResource(R.drawable.icon01_dyq);
		text_1.setTextColor(Color.parseColor("#ffffff"));

	}

	private void clearcolor() {
		// TODO Auto-generated method stub
		image_1.setImageResource(R.drawable.icon1_dyq);
		text_1.setTextColor(Color.parseColor("#80ffffff"));
		image_2.setImageResource(R.drawable.icon2_dyq);
		text_2.setTextColor(Color.parseColor("#80ffffff"));
		image_3.setImageResource(R.drawable.icon3_dyq);
		text_3.setTextColor(Color.parseColor("#80ffffff"));
		image_4.setImageResource(R.drawable.icon4_dyq);
		text_4.setTextColor(Color.parseColor("#80ffffff"));
		image_5.setImageResource(R.drawable.icon5_dyq);
		text_5.setTextColor(Color.parseColor("#80ffffff"));
	}

	private void initview(View view) {
		// TODO Auto-generated method stub
		lin_1 = (LinearLayout) view.findViewById(R.id.lin_1);
		image_1 = (ImageView) view.findViewById(R.id.image_1);
		text_1 = (TextView) view.findViewById(R.id.text_1);
		lin_2 = (LinearLayout) view.findViewById(R.id.lin_2);
		image_2 = (ImageView) view.findViewById(R.id.image_2);
		text_2 = (TextView) view.findViewById(R.id.text_2);
		lin_3 = (LinearLayout) view.findViewById(R.id.lin_3);
		image_3 = (ImageView) view.findViewById(R.id.image_3);
		text_3 = (TextView) view.findViewById(R.id.text_3);
		lin_4 = (LinearLayout) view.findViewById(R.id.lin_4);
		image_4 = (ImageView) view.findViewById(R.id.image_4);
		text_4 = (TextView) view.findViewById(R.id.text_4);
		lin_5 = (LinearLayout) view.findViewById(R.id.lin_5);
		image_5 = (ImageView) view.findViewById(R.id.image_5);
		text_5 = (TextView) view.findViewById(R.id.text_5);
		lin_1.setOnClickListener(this);
		lin_2.setOnClickListener(this);
		lin_3.setOnClickListener(this);
		lin_4.setOnClickListener(this);
		lin_5.setOnClickListener(this);
	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();

		MainActivity.curFragmentTag = getString(R.string.str_bbs);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.lin_1:
			clearcolor();
			image_1.setImageResource(R.drawable.icon01_dyq);
			text_1.setTextColor(Color.parseColor("#ffffff"));
			ViewPaper.setCurrentItem(0);
			break;
		case R.id.lin_2:
			clearcolor();
			image_2.setImageResource(R.drawable.icon02_dyq);
			text_2.setTextColor(Color.parseColor("#ffffff"));
			ViewPaper.setCurrentItem(1);
			break;
		case R.id.lin_3:
			clearcolor();
			image_3.setImageResource(R.drawable.icon03_dyq);
			text_3.setTextColor(Color.parseColor("#ffffff"));
			ViewPaper.setCurrentItem(2);
			break;
		case R.id.lin_4:
			clearcolor();
			image_4.setImageResource(R.drawable.icon04_dyq);
			text_4.setTextColor(Color.parseColor("#ffffff"));
			ViewPaper.setCurrentItem(3);
			break;
		case R.id.lin_5:
			clearcolor();
			image_5.setImageResource(R.drawable.icon05_dyq);
			text_5.setTextColor(Color.parseColor("#ffffff"));
			ViewPaper.setCurrentItem(4);
			break;
		default:
			break;
		}
	}

}
