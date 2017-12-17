package wuxc.single.railwayparty.main;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import wuxc.single.railwayparty.R;
import wuxc.single.railwayparty.branch.FragmentPartygroupfile;
import wuxc.single.railwayparty.branch.FragmentPartygroupmember;
import wuxc.single.railwayparty.layout.Childviewpaper;

public class PolicyActivity extends FragmentActivity implements OnClickListener {
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
	private LinearLayout lin_select;
	private LinearLayout lin_center;
	private TextView text_one;
	private TextView text_two;
	private TextView text_three;
	private Button btn_no;
	private Button btn_yes;
	private ImageView image_more;
	private TextView text_title;
	private String title = "两学一做";
	private String temp = "两学一做";
	private ImageView image_edit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.wuxc_activity_policy);
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
		image_edit.setVisibility(View.GONE);
	}

	private void initfragment() {
		// TODO Auto-generated method stub
		Fragments.add(new PolicyFragment());
		Fragments.add(new TipsFragment());
		Fragments.add(new ExamFragment());

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
				text_line_1.setBackgroundColor(Color.parseColor("#ffffff"));
				image_edit.setVisibility(View.GONE);
				break;
			case 1:
				clearcolor();
				text_2.setTextColor(Color.parseColor("#ffffff"));
				text_line_2.setBackgroundColor(Color.parseColor("#ffffff"));
				image_edit.setVisibility(View.VISIBLE);
				break;
			case 2:
				clearcolor();
				text_3.setTextColor(Color.parseColor("#ffffff"));
				text_line_3.setBackgroundColor(Color.parseColor("#ffffff"));
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
		text_1.setTextColor(Color.parseColor("#ffffff"));
		text_line_1.setBackgroundColor(Color.parseColor("#ffffff"));
	}

	private void clearcolor() {
		// TODO Auto-generated method stub
		text_1.setTextColor(Color.parseColor("#50ffffff"));
		text_2.setTextColor(Color.parseColor("#50ffffff"));
		text_3.setTextColor(Color.parseColor("#50ffffff"));
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

		lin_select = (LinearLayout) findViewById(R.id.lin_select);
		lin_center = (LinearLayout) findViewById(R.id.lin_center);
		text_one = (TextView) findViewById(R.id.text_one);
		text_two = (TextView) findViewById(R.id.text_two);
		text_three = (TextView) findViewById(R.id.text_three);
		btn_no = (Button) findViewById(R.id.btn_no);
		btn_yes = (Button) findViewById(R.id.btn_yes);
		image_more = (ImageView) findViewById(R.id.image_more);
		text_title = (TextView) findViewById(R.id.text_title);
		lin_select.setVisibility(View.GONE);
		lin_select.setOnClickListener(this);
		lin_center.setOnClickListener(this);
		text_one.setOnClickListener(this);
		text_two.setOnClickListener(this);
		text_three.setOnClickListener(this);
		btn_no.setOnClickListener(this);
		btn_yes.setOnClickListener(this);
		image_more.setOnClickListener(this);
		clearselect();
		text_one.setBackgroundResource(R.drawable.shape20line);
		image_edit = (ImageView) findViewById(R.id.image_edit);
		image_edit.setOnClickListener(this);
	}

	private void clearselect() {
		// TODO Auto-generated method stub
		text_one.setBackgroundColor(Color.parseColor("#00000000"));
		text_two.setBackgroundColor(Color.parseColor("#00000000"));
		text_three.setBackgroundColor(Color.parseColor("#00000000"));
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
			text_line_1.setBackgroundColor(Color.parseColor("#ffffff"));
			ViewPaper.setCurrentItem(0);
			image_edit.setVisibility(View.GONE);
			break;
		case R.id.text_2:
			clearcolor();
			text_2.setTextColor(Color.parseColor("#ffffff"));
			text_line_2.setBackgroundColor(Color.parseColor("#ffffff"));
			ViewPaper.setCurrentItem(1);
			image_edit.setVisibility(View.VISIBLE);
			break;
		case R.id.text_3:
			clearcolor();
			text_3.setTextColor(Color.parseColor("#ffffff"));
			text_line_3.setBackgroundColor(Color.parseColor("#ffffff"));
			ViewPaper.setCurrentItem(2);
			image_edit.setVisibility(View.GONE);
			break;
		case R.id.image_more:
			lin_select.setVisibility(View.VISIBLE);
			break;
		case R.id.lin_select:
			lin_select.setVisibility(View.GONE);
			break;
		case R.id.lin_center:
			break;
		case R.id.text_one:
			clearselect();
			text_one.setBackgroundResource(R.drawable.shape20line);
			temp = "两学一做";
			if (true) {
				PolicyFragment policyFragment = new PolicyFragment();
				policyFragment.Set(1);
			}
			break;
		case R.id.text_two:
			clearselect();
			text_two.setBackgroundResource(R.drawable.shape20line);
			temp = "十九大专题";
			if (true) {
				PolicyFragment policyFragment = new PolicyFragment();
				policyFragment.Set(2);
			}
			break;
		case R.id.text_three:
			clearselect();
			text_three.setBackgroundResource(R.drawable.shape20line);
			temp = "三严三实";
			if (true) {
				PolicyFragment policyFragment = new PolicyFragment();
				policyFragment.Set(3);
			}

			break;
		case R.id.btn_no:
			lin_select.setVisibility(View.GONE);
			temp = title;
			break;
		case R.id.btn_yes:
			lin_select.setVisibility(View.GONE);
			title = temp;
			text_title.setText(title);
			text_1.setText(title);
			if (title.equals("两学一做")) {

			} else {
				finish();
				Intent intent = new Intent();
				intent.setClass(getApplicationContext(), PolicyActivity2.class);
				startActivity(intent);
			}

			break;
		case R.id.image_edit:
			Intent intent = new Intent();
			intent.setClass(getApplicationContext(), PublishTipsActivity.class);
			startActivity(intent);
			break;
		default:
			break;
		}
	}
}
