package wuxc.single.railwayparty;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends FragmentActivity implements OnClickListener {
	public static String curFragmentTag;
	public BuildFragment buildFragment;
	public BranchFragment branchFragment;
	public MainFragment mainFragment;
	public BbsFragment bbsFragment;
	public MyFragment myFragment;
	private RelativeLayout RelativeBuild, RelativeBranch, RelativeBbs, RelativeMy;
	private ImageView ImageMain;
	private FragmentManager mFragmentManager;
	private FragmentTransaction mFragmentTransaction;
	private int page = 0;
	private SharedPreferences FragmentPage;
	private TextView text_gobg;
	private int screenwidth = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.wuxc_activity_main);
		initview();
	}

	private void initview() {
		// TODO Auto-generated method stub
		screenwidth = getWindow().getWindowManager().getDefaultDisplay().getWidth();

		FragmentPage = getSharedPreferences("fragmentinfo", MODE_PRIVATE);
		RelativeBuild = (RelativeLayout) findViewById(R.id.relative_build);
		RelativeBranch = (RelativeLayout) findViewById(R.id.relative_branch);
		RelativeBbs = (RelativeLayout) findViewById(R.id.relative_bbs);
		RelativeMy = (RelativeLayout) findViewById(R.id.relative_my);
		ImageMain = (ImageView) findViewById(R.id.image_main);
		RelativeBbs.setOnClickListener(this);
		RelativeBuild.setOnClickListener(this);
		RelativeBranch.setOnClickListener(this);
		RelativeMy.setOnClickListener(this);
		ImageMain.setOnClickListener(this);
		text_gobg = (TextView) findViewById(R.id.text_gobg);
		text_gobg.setOnClickListener(this);
		int height = (int) (screenwidth / 3.3);
		RelativeLayout.LayoutParams LayoutParams = (android.widget.RelativeLayout.LayoutParams) text_gobg
				.getLayoutParams();
		LayoutParams.height = height;
		text_gobg.setLayoutParams(LayoutParams);
		mFragmentManager = getSupportFragmentManager();
		read();
		if (page == 1) {
			setTabSelection(getString(R.string.str_build));
		} else if (page == 2) {
			setTabSelection(getString(R.string.str_branch));
		} else if (page == 3) {
			setTabSelection(getString(R.string.str_main));
		} else if (page == 4) {
			setTabSelection(getString(R.string.str_bbs));
		} else if (page == 5) {
			setTabSelection(getString(R.string.str_my));
		} else if (page == 0) {
			// setTabSelection(getString(R.string.str_record));
			setCurrentFragment();
			// setTabSelection(getString(R.string.str_recommend));
			// setTabSelection(getString(R.string.str_record));
		}
	}

	private void read() {
		// TODO Auto-generated method stub
		page = FragmentPage.getInt("fragment", 0);
	}

	private void write(int i) {
		// TODO Auto-generated method stub
		Editor edit = FragmentPage.edit();
		edit.putInt("fragment", i);
		edit.commit();
		if (i == 3) {
			text_gobg.setVisibility(View.VISIBLE);
		} else {
			text_gobg.setVisibility(View.GONE);
		}
	}

	private void setCurrentFragment() {
		clearSelection();
		mFragmentTransaction = mFragmentManager.beginTransaction();

		write(3);
		if (mainFragment == null) {
			mainFragment = new MainFragment();
			mFragmentTransaction.add(R.id.content, mainFragment, getString(R.string.str_main));
			commitTransactions();
		}
		curFragmentTag = getString(R.string.str_main);
	}

	public void setTabSelection(String tag) {

		clearSelection();
		mFragmentTransaction = mFragmentManager.beginTransaction();
		if (TextUtils.equals(tag, getString(R.string.str_build))) {

			if (buildFragment == null) {
				buildFragment = new BuildFragment();
			}

		} else if (TextUtils.equals(tag, getString(R.string.str_branch))) {

			if (branchFragment == null) {
				branchFragment = new BranchFragment();
			}

		} else if (TextUtils.equals(tag, getString(R.string.str_main))) {

			if (mainFragment == null) {
				mainFragment = new MainFragment();
			}
		} else if (TextUtils.equals(tag, getString(R.string.str_bbs))) {

			if (bbsFragment == null) {
				bbsFragment = new BbsFragment();
			}
		} else if (TextUtils.equals(tag, getString(R.string.str_my))) {

			if (myFragment == null) {
				myFragment = new MyFragment();
			}
		}

		switchFragment(tag);

	}

	public void switchFragment(String tag) {
		if (TextUtils.equals(tag, curFragmentTag)) {
			return;
		}

		if (curFragmentTag != null) {
			detachFragment(getFragment(curFragmentTag));

		}
		attachFragment(R.id.content, getFragment(tag), tag);
		curFragmentTag = tag;
		commitTransactions();
	}

	private void detachFragment(Fragment f) {

		if (f != null && !f.isDetached()) {
			ensureTransaction();
			mFragmentTransaction.detach(f);
		}
	}

	private FragmentTransaction ensureTransaction() {
		if (mFragmentTransaction == null) {
			mFragmentTransaction = mFragmentManager.beginTransaction();
			mFragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);

		}
		return mFragmentTransaction;

	}

	private void attachFragment(int layout, Fragment f, String tag) {
		if (f != null) {
			if (f.isDetached()) {
				ensureTransaction();
				mFragmentTransaction.attach(f);

			} else if (!f.isAdded()) {
				ensureTransaction();
				mFragmentTransaction.add(layout, f, tag);
			}
		}
	}

	private void commitTransactions() {
		if (mFragmentTransaction != null && !mFragmentTransaction.isEmpty()) {
			mFragmentTransaction.commit();
			mFragmentTransaction = null;
		}
	}

	private Fragment getFragment(String tag) {

		Fragment f = mFragmentManager.findFragmentByTag(tag);

		if (f == null) {
			f = MainBaseFragment.newInstance(getApplicationContext(), tag);
		}
		return f;

	}

	private void clearSelection() {
		RelativeBuild.setBackgroundColor(Color.parseColor("#cc0502"));
		RelativeBranch.setBackgroundColor(Color.parseColor("#cc0502"));
		RelativeMy.setBackgroundColor(Color.parseColor("#cc0502"));
		RelativeBbs.setBackgroundColor(Color.parseColor("#cc0502"));

	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		write(0);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.relative_build:
			write(1);
			setTabSelection(getString(R.string.str_build));
			RelativeBuild.setBackgroundColor(Color.parseColor("#a80402"));
			break;
		case R.id.relative_branch:
			write(2);
			setTabSelection(getString(R.string.str_branch));
			RelativeBranch.setBackgroundColor(Color.parseColor("#a80402"));
			break;
		case R.id.text_gobg:
			write(2);
			setTabSelection(getString(R.string.str_branch));
			RelativeBranch.setBackgroundColor(Color.parseColor("#a80402"));
			break;
		case R.id.image_main:
			write(3);
			setTabSelection(getString(R.string.str_main));
			break;
		case R.id.relative_bbs:
			write(4);
			setTabSelection(getString(R.string.str_bbs));
			RelativeBbs.setBackgroundColor(Color.parseColor("#a80402"));
			break;
		case R.id.relative_my:
			write(5);
			setTabSelection(getString(R.string.str_my));
			RelativeMy.setBackgroundColor(Color.parseColor("#a80402"));
			break;
		default:
			break;
		}
	}

}
