package wuxc.single.railwayparty;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Color;
import android.os.Bundle;
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
import android.widget.TextView;
import wuxc.single.railwayparty.fragment.BbsFragment1;
import wuxc.single.railwayparty.fragment.BbsFragment2;
import wuxc.single.railwayparty.fragment.BbsFragment3;
import wuxc.single.railwayparty.fragment.BbsFragment4;
import wuxc.single.railwayparty.fragment.BbsFragment5;
import wuxc.single.railwayparty.layout.Childviewpaper;
import wuxc.single.railwayparty.main.PublishDYQActivity;
import wuxc.single.railwayparty.main.PublishTipsDYQActivity;
import wuxc.single.railwayparty.main.PublishTipsYIJANActivity;
import wuxc.single.railwayparty.main.PublishadviceActivity;
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
	private ImageView image_edit;
	private int page = 1;
	private View view;// »º´æFragment view
	private TextView text_number_1;
	private TextView text_number_2;
	private TextView text_number_3;
	private TextView text_number_4;
	private int t1 = 0;
	private int t2 = 0;
	private int t3 = 0;
	private int t4 = 0;
	private SharedPreferences ItemNumber;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		if (null != view) {
			ViewGroup parent = (ViewGroup) view.getParent();
			if (null != parent) {
				parent.removeView(view);
			}
		} else {
			view = inflater.inflate(R.layout.wuxc_fragment_bbs, container, false);
			ItemNumber = getActivity().getSharedPreferences("ItemNumber", Context.MODE_PRIVATE);
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
		}
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
				text_1.setTextColor(Color.parseColor("#cc0502"));
				image_edit.setVisibility(View.VISIBLE);
				page = 1;
				break;
			case 1:
				clearcolor();
				image_2.setImageResource(R.drawable.icon02_dyq);
				text_2.setTextColor(Color.parseColor("#cc0502"));
				page = 2;
				image_edit.setVisibility(View.GONE);
				break;
			case 2:
				clearcolor();
				image_3.setImageResource(R.drawable.icon03_dyq);
				text_3.setTextColor(Color.parseColor("#cc0502"));
				page = 3;
				image_edit.setVisibility(View.GONE);
				break;
			case 3:
				clearcolor();
				image_4.setImageResource(R.drawable.icon04_dyq);
				text_4.setTextColor(Color.parseColor("#cc0502"));
				page = 4;
				image_edit.setVisibility(View.VISIBLE);
				break;
			case 4:
				clearcolor();
				image_5.setImageResource(R.drawable.icon05_dyq);
				page = 5;
				text_5.setTextColor(Color.parseColor("#cc0502"));
				image_edit.setVisibility(View.GONE);
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
		text_1.setTextColor(Color.parseColor("#cc0502"));

	}

	private void clearcolor() {
		// TODO Auto-generated method stub
		image_1.setImageResource(R.drawable.icon1_dyq);
		text_1.setTextColor(Color.parseColor("#D0D0D0"));
		image_2.setImageResource(R.drawable.icon2_dyq);
		text_2.setTextColor(Color.parseColor("#D0D0D0"));
		image_3.setImageResource(R.drawable.icon3_dyq);
		text_3.setTextColor(Color.parseColor("#D0D0D0"));
		image_4.setImageResource(R.drawable.icon4_dyq);
		text_4.setTextColor(Color.parseColor("#D0D0D0"));
		image_5.setImageResource(R.drawable.icon5_dyq);
		text_5.setTextColor(Color.parseColor("#D0D0D0"));
	}

	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		if (view != null) {
			intnumber();
		}
	}

	private void intnumber() {
		ItemNumber = getActivity().getSharedPreferences("ItemNumber", Context.MODE_PRIVATE);
		// t1 = ItemNumber.getInt("DYQtotal", 100) -
		// ItemNumber.getInt("DYQread", 0);
		// t2 = ItemNumber.getInt("DQLHtotal", 100) -
		// ItemNumber.getInt("DQLHread", 0);
		// t3 = ItemNumber.getInt("DSYYJtotal", 100) -
		// ItemNumber.getInt("DSYYJread", 0);
		// t4 = ItemNumber.getInt("DYYJtotal", 100) -
		// ItemNumber.getInt("DYYJread", 0);
		t1 = ItemNumber.getInt("DYQapi", 0) - ItemNumber.getInt("DYQposition", 0);
		t2 = ItemNumber.getInt("DQLHapi", 0) - ItemNumber.getInt("DQLHposition", 0);
		t3 = ItemNumber.getInt("DSYYJapi", 0) - ItemNumber.getInt("DSYYJposition", 0);
		t4 = ItemNumber.getInt("DYYJapi", 0) - ItemNumber.getInt("DYYJposition", 0);
		text_number_1.setVisibility(View.GONE);
		text_number_2.setVisibility(View.GONE);
		text_number_3.setVisibility(View.GONE);
		text_number_4.setVisibility(View.GONE);
		if (t1 > 0) {
			text_number_1.setVisibility(View.VISIBLE);

			if (t1 > 99) {
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

	}

	private void initview(View view) {
		// TODO Auto-generated method stub
		text_number_1 = (TextView) view.findViewById(R.id.text_number_1);

		text_number_2 = (TextView) view.findViewById(R.id.text_number_2);

		text_number_3 = (TextView) view.findViewById(R.id.text_number_3);

		text_number_4 = (TextView) view.findViewById(R.id.text_number_4);

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
		image_edit = (ImageView) view.findViewById(R.id.image_edit);
		image_edit.setOnClickListener(this);
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
			text_1.setTextColor(Color.parseColor("#cc0502"));
			ViewPaper.setCurrentItem(0);
			image_edit.setVisibility(View.VISIBLE);
			page = 1;
			if (true) {
				Editor edit1 = ItemNumber.edit();
				edit1.putInt("DYQread", ItemNumber.getInt("DYQtotal", 0));
				edit1.putInt("DYQposition", ItemNumber.getInt("DYQapi", 0));
				edit1.commit();
			}

			intnumber();
			break;
		case R.id.lin_2:
			clearcolor();
			image_2.setImageResource(R.drawable.icon02_dyq);
			text_2.setTextColor(Color.parseColor("#cc0502"));
			page = 2;
			image_edit.setVisibility(View.GONE);
			ViewPaper.setCurrentItem(1);
			intnumber();
			break;
		case R.id.lin_3:
			clearcolor();
			image_3.setImageResource(R.drawable.icon03_dyq);
			page = 3;
			text_3.setTextColor(Color.parseColor("#cc0502"));
			image_edit.setVisibility(View.GONE);
			ViewPaper.setCurrentItem(2);
			intnumber();
			break;
		case R.id.lin_4:
			clearcolor();
			image_4.setImageResource(R.drawable.icon04_dyq);
			text_4.setTextColor(Color.parseColor("#cc0502"));
			page = 4;
			ViewPaper.setCurrentItem(3);
			image_edit.setVisibility(View.VISIBLE);
			if (true) {
				Editor edit1 = ItemNumber.edit();
				edit1.putInt("DYYJread", ItemNumber.getInt("DYYJtotal", 0));
				edit1.putInt("DYYJposition", ItemNumber.getInt("DYYJapi", 0));
				edit1.commit();
			}
			intnumber();
			break;
		case R.id.lin_5:
			clearcolor();
			image_5.setImageResource(R.drawable.icon05_dyq);
			page = 5;
			image_edit.setVisibility(View.GONE);
			text_5.setTextColor(Color.parseColor("#cc0502"));
			ViewPaper.setCurrentItem(4);
			intnumber();
			break;
		case R.id.image_edit:
			if (page == 4) {
				Intent intent = new Intent();
				intent.setClass(getActivity(), PublishTipsYIJANActivity.class);
				startActivity(intent);
			} else if (page == 1) {
				Intent intent = new Intent();
				intent.setClass(getActivity(), PublishTipsDYQActivity.class);
				startActivity(intent);
			}
			break;
		default:
			break;
		}
	}

}
