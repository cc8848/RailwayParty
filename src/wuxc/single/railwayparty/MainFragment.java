package wuxc.single.railwayparty;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.railwayparty.picfragment.f1;
import com.example.railwayparty.picfragment.f10;
import com.example.railwayparty.picfragment.f2;
import com.example.railwayparty.picfragment.f3;
import com.example.railwayparty.picfragment.f4;
import com.example.railwayparty.picfragment.f5;
import com.example.railwayparty.picfragment.f6;
import com.example.railwayparty.picfragment.f7;
import com.example.railwayparty.picfragment.f8;
import com.example.railwayparty.picfragment.f9;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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
import android.widget.RelativeLayout;
import android.widget.TextView;
import wuxc.single.railwayparty.fragment.GuideFragment1;
import wuxc.single.railwayparty.fragment.GuideFragment2;
import wuxc.single.railwayparty.fragment.GuideFragment3;
import wuxc.single.railwayparty.internet.HttpGetData;
import wuxc.single.railwayparty.layout.Childviewpaper;
import wuxc.single.railwayparty.main.CleanActivity;
import wuxc.single.railwayparty.main.DisciplinaryActivity;
import wuxc.single.railwayparty.main.FlagActivity;
import wuxc.single.railwayparty.main.InformActivity;
import wuxc.single.railwayparty.main.MemberActivity;
import wuxc.single.railwayparty.main.PartyManageActivity;
import wuxc.single.railwayparty.main.PolicyActivity;
import wuxc.single.railwayparty.main.WebLearnActivity;
import wuxc.single.railwayparty.other.LoginActivity;
import wuxc.single.railwayparty.other.SearchActivity;

public class MainFragment extends MainBaseFragment implements OnClickListener {
	private int screenwidth = 0;
	private ImageView ImageGobg;
	private RelativeLayout rel_main;
	private RelativeLayout main_top_bac;
	private LinearLayout rel_inform;
	private LinearLayout rel_policy;
	private LinearLayout rel_party_manage;
	private LinearLayout rel_weblearn;
	private RelativeLayout rel_clean;
	private RelativeLayout rel_disciplinary;
	private RelativeLayout rel_member;
	private RelativeLayout rel_flag;
	private RelativeLayout rel_9;
	private RelativeLayout rel_10;
	private RelativeLayout rel_11;
	private RelativeLayout rel_12;
	private LinearLayout lin_four;
	private ImageView headimg;
	private TextView text_1;
	private TextView text_2;
	private TextView text_3;
	private TextView text_4;
	private TextView text_5;
	private TextView text_6;
	private TextView text_7;
	private TextView text_8;
	private SharedPreferences PreUserInfo;// 存储个人信息
	private String ticket = "";
	private TextView text_warning;
	private View view = null;
	private static final int GET_VERSION_RESULT = 5;
	private int t1 = 0;
	private int t2 = 0;
	private int t3 = 0;
	private int t4 = 0;
	private int t5 = 0;
	private int t6 = 0;
	private int t7 = 0;
	private int t8 = 0;
	private SharedPreferences ItemNumber;
	private Childviewpaper ViewPaper;
	public List<Fragment> Fragments = new ArrayList<Fragment>();
	private FragmentManager FragmentManager;
	private int NumberPicture = 0;
	private Handler uiHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {

			case GET_VERSION_RESULT:
				GetDataDetailFromVersion(msg.obj);
				break;
			default:
				break;
			}
		}
	};

	protected void GetDataDetailFromVersion(Object obj) {
		// TODO Auto-generated method stub

		// TODO Auto-generated method stub
		String data = null;

		try {
			JSONObject demoJson = new JSONObject(obj.toString());

			data = demoJson.getString("data");
			demoJson = new JSONObject(data);
			Editor edit = ItemNumber.edit();
			edit.putInt("ZDWJtotal", demoJson.getInt("zdwj_total"));
			edit.putInt("DJYWtotal", demoJson.getInt("djyw_total"));
			edit.putInt("TZGGtotal", demoJson.getInt("tzggd_total"));
			edit.putInt("SZRDtotal", demoJson.getInt("szrd_total"));
			edit.putInt("DYFCtotal", demoJson.getInt("dyfc_total"));
			edit.putInt("WSDXtotal", demoJson.getInt("wsdx_total"));
			edit.putInt("LXYZXXtotal1", demoJson.getInt("lxyzxx_total"));
			edit.putInt("DJKHtotal", demoJson.getInt("djkh_total"));
			edit.putInt("DNJCtotal", demoJson.getInt("dnjc_total"));
			edit.putInt("DWGKtotal", demoJson.getInt("dwgk_total"));
			edit.putInt("LZSXtotal", demoJson.getInt("lzsx_total"));
			edit.putInt("YASJLBtotal", demoJson.getInt("yasjlb_total"));
			edit.putInt("QLKMtotal", demoJson.getInt("qlkm_total"));
			edit.putInt("XXJLtotal", demoJson.getInt("xxjl_total"));
			edit.putInt("DJFGtotal", demoJson.getInt("djfg_total"));
			edit.putInt("XXTBtotal", demoJson.getInt("xxtb_total"));
			edit.putInt("JSZNtotal", demoJson.getInt("jszn_total"));
			edit.putInt("WHZNtotal", demoJson.getInt("whzn_total"));
			edit.putInt("CYZNtotal", demoJson.getInt("cyzn_total"));
			edit.putInt("JYZNtotal", demoJson.getInt("jyzn_total"));
			edit.putInt("QTXStotal", demoJson.getInt("qtxs_total"));
			edit.putInt("QWXtotal", demoJson.getInt("qwx_total"));
			edit.putInt("QNXFtotal", demoJson.getInt("qnxf_total"));
			edit.putInt("LXYZXXtotal2", 0);
			edit.putInt("JZXXtotal", demoJson.getInt("jzxx_total"));
			edit.putInt("DYQtotal", demoJson.getInt("dyq_total"));
			edit.putInt("DQLHtotal", demoJson.getInt("dqlh_total"));
			edit.putInt("DSYYJtotal", demoJson.getInt("dsyyj_total"));
			edit.putInt("DYYJtotal", demoJson.getInt("dyyj_total"));
			edit.putInt("ZDWJapi", demoJson.getInt("zdwj"));
			edit.putInt("DJYWapi", demoJson.getInt("djyw"));
			edit.putInt("TZGGapi", demoJson.getInt("tzggd"));
			edit.putInt("SZRDapi", demoJson.getInt("szrd"));
			edit.putInt("DYFCapi", demoJson.getInt("dyfc"));
			edit.putInt("WSDXapi", demoJson.getInt("wsdx"));
			edit.putInt("LXYZXXapi1", demoJson.getInt("lxyzxx"));
			edit.putInt("DJKHapi", demoJson.getInt("djkh"));
			edit.putInt("DNJCapi", demoJson.getInt("dnjc"));
			edit.putInt("DWGKapi", demoJson.getInt("dwgk"));
			edit.putInt("LZSXapi", demoJson.getInt("lzsx"));
			edit.putInt("YASJLBapi", demoJson.getInt("yasjlb"));
			edit.putInt("QLKMapi", demoJson.getInt("qlkm"));
			edit.putInt("XXJLapi", demoJson.getInt("xxjl"));
			edit.putInt("DJFGapi", demoJson.getInt("djfg"));
			edit.putInt("XXTBapi", demoJson.getInt("xxtb"));
			edit.putInt("JSZNapi", demoJson.getInt("jszn"));
			edit.putInt("WHZNapi", demoJson.getInt("whzn"));
			edit.putInt("CYZNapi", demoJson.getInt("cyzn"));
			edit.putInt("JYZNapi", demoJson.getInt("jyzn"));
			edit.putInt("QTXSapi", demoJson.getInt("qtxs"));
			edit.putInt("QWXapi", demoJson.getInt("qwx"));
			edit.putInt("QNXFapi", demoJson.getInt("qnxf"));
			edit.putInt("JZXXapi", demoJson.getInt("jzxx"));
			edit.putInt("DYQapi", demoJson.getInt("dyq"));
			edit.putInt("DQLHapi", demoJson.getInt("dqlh"));
			edit.putInt("DSYYJapi", demoJson.getInt("dsyyj"));
			edit.putInt("DYYJapi", demoJson.getInt("dyyj"));
			edit.commit();
			intnumber();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
		}

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
		// TODO Auto-generated method stub
		ItemNumber = getActivity().getSharedPreferences("ItemNumber", Context.MODE_PRIVATE);
		// t1 = ItemNumber.getInt("TZGGtotal", 100) -
		// ItemNumber.getInt("TZGGread", 0);
		// t2 = ItemNumber.getInt("LXYZXXtotal" + 2, 100) +
		// ItemNumber.getInt("LXYZXXtotal" + 1, 100)
		// - ItemNumber.getInt("LXYZXXread", 0);
		// t3 = ItemNumber.getInt("DJKHtotal", 100) -
		// ItemNumber.getInt("DJKHread", 0)
		// + ItemNumber.getInt("DNJCtotal", 100) - ItemNumber.getInt("DNJCread",
		// 0)
		// + ItemNumber.getInt("DWGKtotal", 100) - ItemNumber.getInt("DWGKread",
		// 0);
		// t4 = ItemNumber.getInt("WSDXtotal", 100) -
		// ItemNumber.getInt("WSDXread", 0);
		// t5 = ItemNumber.getInt("LZSXtotal", 100) -
		// ItemNumber.getInt("LZSXread", 0)
		// + ItemNumber.getInt("YASJLBtotal", 100) -
		// ItemNumber.getInt("YASJLBread", 0)
		// + ItemNumber.getInt("QLKMtotal", 100) - ItemNumber.getInt("QLKMread",
		// 0)
		// + ItemNumber.getInt("XXJLtotal", 100) - ItemNumber.getInt("XXJLread",
		// 0);
		// t6 = ItemNumber.getInt("DJFGtotal", 100) -
		// ItemNumber.getInt("DJFGread", 0)
		// + ItemNumber.getInt("XXTBtotal", 100) - ItemNumber.getInt("XXTBread",
		// 0);
		// t7 = ItemNumber.getInt("JSZNtotal", 100) -
		// ItemNumber.getInt("JSZNread", 0)
		// + ItemNumber.getInt("WHZNtotal", 100) - ItemNumber.getInt("WHZNread",
		// 0)
		// + ItemNumber.getInt("CYZNtotal", 100) - ItemNumber.getInt("CYZNread",
		// 0)
		// + ItemNumber.getInt("JYZNtotal", 100) - ItemNumber.getInt("JYZNread",
		// 0);
		// t8 = ItemNumber.getInt("QTXStotal", 100) -
		// ItemNumber.getInt("QTXSread", 0) + ItemNumber.getInt("QWXtotal", 100)
		// - ItemNumber.getInt("QWXread", 0) + ItemNumber.getInt("QNXFtotal",
		// 100)
		// - ItemNumber.getInt("QNXFread", 0);
		t1 = ItemNumber.getInt("TZGGapi", 0) - ItemNumber.getInt("TZGGposition", 0);
		t2 = ItemNumber.getInt("LXYZXXapi" + 2, 0) + ItemNumber.getInt("LXYZXXapi" + 1, 0)
				- ItemNumber.getInt("LXYZXXposition", 0);
		t3 = ItemNumber.getInt("DJKHapi", 0) - ItemNumber.getInt("DJKHposition", 0) + ItemNumber.getInt("DNJCapi", 0)
				- ItemNumber.getInt("DNJCposition", 0) + ItemNumber.getInt("DWGKapi", 0)
				- ItemNumber.getInt("DWGKposition", 0);
		t4 = ItemNumber.getInt("WSDXapi", 0) - ItemNumber.getInt("WSDXposition", 0);
		t5 = ItemNumber.getInt("LZSXapi", 0) - ItemNumber.getInt("LZSXposition", 0) + ItemNumber.getInt("YASJLBapi", 0)
				- ItemNumber.getInt("YASJLBposition", 0) + ItemNumber.getInt("QLKMapi", 0)
				- ItemNumber.getInt("QLKMposition", 0) + ItemNumber.getInt("XXJLapi", 0)
				- ItemNumber.getInt("XXJLposition", 0);
		t6 = ItemNumber.getInt("DJFGapi", 0) - ItemNumber.getInt("DJFGposition", 0) + ItemNumber.getInt("XXTBapi", 0)
				- ItemNumber.getInt("XXTBposition", 0);
		t7 = ItemNumber.getInt("JSZNapi", 0) - ItemNumber.getInt("JSZNposition", 0) + ItemNumber.getInt("WHZNapi", 0)
				- ItemNumber.getInt("WHZNposition", 0) + ItemNumber.getInt("CYZNapi", 0)
				- ItemNumber.getInt("CYZNposition", 0) + ItemNumber.getInt("JYZNapi", 0)
				- ItemNumber.getInt("JYZNposition", 0);
		t8 = ItemNumber.getInt("QTXSapi", 0) - ItemNumber.getInt("QTXSposition", 0) + ItemNumber.getInt("QWXapi", 0)
				- ItemNumber.getInt("QWXposition", 0) + ItemNumber.getInt("QNXFapi", 0)
				- ItemNumber.getInt("QNXFposition", 0);
		text_1.setVisibility(View.GONE);
		text_2.setVisibility(View.GONE);
		text_3.setVisibility(View.GONE);
		text_4.setVisibility(View.GONE);
		text_5.setVisibility(View.GONE);
		text_6.setVisibility(View.GONE);
		text_7.setVisibility(View.GONE);
		text_8.setVisibility(View.GONE);
		if (t1 > 0) {
			text_1.setVisibility(View.VISIBLE);

			if (t1 >= 100) {
				text_1.setText("···");
				text_1.setBackgroundResource(R.drawable.morethan100);

			} else {
				text_1.setText("" + t1);
				text_1.setBackgroundResource(R.drawable.tag);
			}
		}
		if (t2 > 0) {
			text_2.setVisibility(View.VISIBLE);
			if (t2 > 99) {
				text_2.setText("···");
				text_2.setBackgroundResource(R.drawable.morethan100);

			} else {
				text_2.setText("" + t2);
				text_2.setBackgroundResource(R.drawable.tag);
			}

		}
		if (t3 > 0) {
			text_3.setVisibility(View.VISIBLE);

			if (t3 > 99) {
				text_3.setText("···");
				text_3.setBackgroundResource(R.drawable.morethan100);

			} else {
				text_3.setText("" + t3);
				text_3.setBackgroundResource(R.drawable.tag);
			}
		}
		if (t4 > 0) {
			text_4.setVisibility(View.VISIBLE);

			if (t4 > 99) {
				text_4.setText("···");
				text_4.setBackgroundResource(R.drawable.morethan100);

			} else {
				text_4.setText("" + t4);
				text_4.setBackgroundResource(R.drawable.tag);
			}
		}
		if (t5 > 0) {
			text_5.setVisibility(View.VISIBLE);

			if (t5 > 99) {
				text_5.setText("···");
				text_5.setBackgroundResource(R.drawable.morethan100);

			} else {
				text_5.setText("" + t5);
				text_5.setBackgroundResource(R.drawable.tag);
			}
		}
		if (t6 > 0) {
			text_6.setVisibility(View.VISIBLE);

			if (t6 > 99) {
				text_6.setText("···");
				text_6.setBackgroundResource(R.drawable.morethan100);

			} else {
				text_6.setText("" + t6);
				text_6.setBackgroundResource(R.drawable.tag);
			}
		}
		if (t7 > 0) {
			text_7.setVisibility(View.VISIBLE);
			if (t7 > 99) {
				text_7.setText("···");
				text_7.setBackgroundResource(R.drawable.morethan100);

			} else {
				text_7.setText("" + t7);
				text_7.setBackgroundResource(R.drawable.tag);
			}

		}
		if (t8 > 0) {
			text_8.setVisibility(View.VISIBLE);
			if (t8 > 99) {
				text_8.setText("···");
				text_8.setBackgroundResource(R.drawable.morethan100);

			} else {
				text_8.setText("" + t8);
				text_8.setBackgroundResource(R.drawable.tag);
			}

		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		if (null != view) {
			ViewGroup parent = (ViewGroup) view.getParent();
			if (null != parent) {
				parent.removeView(view);
			}
		} else {
			view = inflater.inflate(R.layout.wuxc_fragment_main, container, false);
			screenwidth = getActivity().getWindow().getWindowManager().getDefaultDisplay().getWidth();
			initview(view);
			initheight(view);
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
			PreUserInfo = getActivity().getSharedPreferences("UserInfo", Context.MODE_PRIVATE);

			ItemNumber = getActivity().getSharedPreferences("ItemNumber", Context.MODE_PRIVATE);
			ticket = PreUserInfo.getString("ticket", "");
			final ArrayList ArrayValues = new ArrayList();
			ArrayValues.add(new BasicNameValuePair("ticket", "" + ticket));
			ArrayValues.add(new BasicNameValuePair("chns",
					"jzxx,dyq,dqlh,dsyyj,dyyj,zdwj,djyw,tzggd,szrd,dyfc,wsdx,lxyzxx,djkh,dnjc,dwgk,lzsx,yasjlb,qlkm,xxjl,djfg,xxtb,jszn,whzn,cyzn,jyzn,qnxf,qtxs,qwx"));
			new Thread(new Runnable() { // 开启线程上传文件
				@Override
				public void run() {
					String LoginResultData = "";
					LoginResultData = HttpGetData.GetData("api/cms/accessRecord/getUnReadStatics", ArrayValues);
					Message msg = new Message();
					msg.obj = LoginResultData;
					msg.what = GET_VERSION_RESULT;
					uiHandler.sendMessage(msg);
				}
			}).start();
			NumberPicture = PreUserInfo.getInt("f_number", 0);
			if (NumberPicture > 10) {
				NumberPicture = 10;
			}
			ViewPaper = (Childviewpaper) view.findViewById(R.id.viewPager);
			Fragments.clear();// 清空list
			initfragment();// list 装填fragment
			FragmentManager = getActivity().getSupportFragmentManager();
			ViewPaper.setOffscreenPageLimit(NumberPicture);
			ViewPaper.setOnPageChangeListener(new MyOnPageChangeListener());
			ViewPaper.setAdapter(new MyPagerAdapter());
		}
		return view;
	}

	private void initfragment() {
		// TODO Auto-generated method stub
		Fragments.add(new f1());
		Fragments.add(new f2());
		Fragments.add(new f3());
		Fragments.add(new f4());
		Fragments.add(new f5());
		Fragments.add(new f6());
		Fragments.add(new f7());
		Fragments.add(new f8());
		Fragments.add(new f9());
		Fragments.add(new f10());
	}

	private void initview(View view) {
		// TODO Auto-generated method stub

		ImageGobg = (ImageView) view.findViewById(R.id.image_go_zb);
		rel_main = (RelativeLayout) view.findViewById(R.id.rel_main);
		main_top_bac = (RelativeLayout) view.findViewById(R.id.main_top_bac);
		rel_inform = (LinearLayout) view.findViewById(R.id.rel_inform);
		rel_policy = (LinearLayout) view.findViewById(R.id.rel_policy);
		rel_party_manage = (LinearLayout) view.findViewById(R.id.rel_party_manage);
		rel_weblearn = (LinearLayout) view.findViewById(R.id.rel_weblearn);

		rel_clean = (RelativeLayout) view.findViewById(R.id.rel_clean);
		rel_disciplinary = (RelativeLayout) view.findViewById(R.id.rel_disciplinary);
		rel_member = (RelativeLayout) view.findViewById(R.id.rel_member);
		rel_flag = (RelativeLayout) view.findViewById(R.id.rel_flag);
		rel_9 = (RelativeLayout) view.findViewById(R.id.rel_9);
		rel_10 = (RelativeLayout) view.findViewById(R.id.rel_10);
		rel_11 = (RelativeLayout) view.findViewById(R.id.rel_11);
		rel_12 = (RelativeLayout) view.findViewById(R.id.rel_12);
		text_1 = (TextView) view.findViewById(R.id.text_1);
		text_2 = (TextView) view.findViewById(R.id.text_2);
		text_3 = (TextView) view.findViewById(R.id.text_3);
		text_4 = (TextView) view.findViewById(R.id.text_4);
		text_5 = (TextView) view.findViewById(R.id.text_5);
		text_6 = (TextView) view.findViewById(R.id.text_6);
		text_7 = (TextView) view.findViewById(R.id.text_7);
		text_8 = (TextView) view.findViewById(R.id.text_8);
		lin_four = (LinearLayout) view.findViewById(R.id.lin_four);
		headimg = (ImageView) view.findViewById(R.id.headimg);
		text_warning = (TextView) view.findViewById(R.id.text_warning);

		rel_inform.setOnClickListener(this);
		rel_policy.setOnClickListener(this);
		rel_party_manage.setOnClickListener(this);
		rel_weblearn.setOnClickListener(this);
		rel_clean.setOnClickListener(this);
		rel_disciplinary.setOnClickListener(this);
		rel_member.setOnClickListener(this);
		rel_flag.setOnClickListener(this);
		main_top_bac.setOnClickListener(this);
		showtext();
	}

	private void showtext() {
		// TODO Auto-generated method stub
		text_1.setVisibility(View.GONE);
		text_2.setVisibility(View.GONE);
		text_3.setVisibility(View.GONE);
		text_4.setVisibility(View.GONE);
		text_5.setVisibility(View.GONE);
		text_6.setVisibility(View.GONE);
		text_7.setVisibility(View.GONE);
		text_8.setVisibility(View.GONE);
	}

	private void initheight(View view) {
		// TODO Auto-generated method stub
		int height = (int) (screenwidth / 3);
		RelativeLayout.LayoutParams LayoutParams = (android.widget.RelativeLayout.LayoutParams) ImageGobg
				.getLayoutParams();
		LayoutParams.height = height;
		ImageGobg.setLayoutParams(LayoutParams);

		RelativeLayout.LayoutParams LayoutParams1 = (android.widget.RelativeLayout.LayoutParams) rel_main
				.getLayoutParams();
		LayoutParams1.bottomMargin = height;
		rel_main.setLayoutParams(LayoutParams1);
		height = (int) (screenwidth * 338 / 750);
		RelativeLayout.LayoutParams LayoutParams2 = (android.widget.RelativeLayout.LayoutParams) main_top_bac
				.getLayoutParams();
		LayoutParams2.height = height;
		main_top_bac.setLayoutParams(LayoutParams2);
		RelativeLayout.LayoutParams LayoutParams9 = (android.widget.RelativeLayout.LayoutParams) headimg
				.getLayoutParams();
		LayoutParams9.height = (int) (height / 2.5);
		LayoutParams9.width = (int) (height / 2.5);
		LayoutParams9.topMargin = height / 5;
		headimg.setLayoutParams(LayoutParams9);

		height = (int) (screenwidth / 2.6);
		RelativeLayout.LayoutParams LayoutParams3 = (android.widget.RelativeLayout.LayoutParams) lin_four
				.getLayoutParams();
		LayoutParams3.height = height;
		lin_four.setLayoutParams(LayoutParams3);

		height = (int) (screenwidth / 2.6 / 2) - 8;
		LinearLayout.LayoutParams LayoutParams4 = (android.widget.LinearLayout.LayoutParams) rel_9.getLayoutParams();
		LayoutParams4.height = height;
		LayoutParams4.width = height;
		rel_9.setLayoutParams(LayoutParams4);

		LinearLayout.LayoutParams LayoutParams5 = (android.widget.LinearLayout.LayoutParams) rel_10.getLayoutParams();
		LayoutParams5.height = height;
		LayoutParams5.width = height;
		rel_10.setLayoutParams(LayoutParams5);

		LinearLayout.LayoutParams LayoutParams6 = (android.widget.LinearLayout.LayoutParams) rel_11.getLayoutParams();
		LayoutParams6.height = height;
		LayoutParams6.width = height;
		rel_11.setLayoutParams(LayoutParams6);

		LinearLayout.LayoutParams LayoutParams7 = (android.widget.LinearLayout.LayoutParams) rel_12.getLayoutParams();
		LayoutParams7.height = height;
		LayoutParams7.width = height;
		rel_12.setLayoutParams(LayoutParams7);
	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();

		MainActivity.curFragmentTag = getString(R.string.str_main);
		if (view != null) {
			ticket = PreUserInfo.getString("ticket", "");
			if (!ticket.equals("")) {
				headimg.setVisibility(View.GONE);
				text_warning.setVisibility(View.GONE);
			} else {
				headimg.setVisibility(View.VISIBLE);
				text_warning.setVisibility(View.VISIBLE);
			}
		}

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.rel_inform:
			if (ticket.equals("")) {
				Intent intent_top_bac = new Intent();
				intent_top_bac.setClass(getActivity(), LoginActivity.class);
				startActivity(intent_top_bac);
				t1 = 0;
				intnumber();
				break;
			}
			Intent intent_inform = new Intent();
			intent_inform.setClass(getActivity(), InformActivity.class);
			startActivity(intent_inform);
			t1 = 0;
			intnumber();
			break;
		case R.id.rel_policy:
			if (ticket.equals("")) {
				Intent intent_top_bac = new Intent();
				intent_top_bac.setClass(getActivity(), LoginActivity.class);
				startActivity(intent_top_bac);
				t2 = 0;
				intnumber();
				break;
			}
			Intent intent_rel_policy = new Intent();
			intent_rel_policy.setClass(getActivity(), PolicyActivity.class);
			startActivity(intent_rel_policy);
			t2 = 0;
			intnumber();
			break;
		case R.id.rel_party_manage:
			if (ticket.equals("")) {
				Intent intent_top_bac = new Intent();
				intent_top_bac.setClass(getActivity(), LoginActivity.class);
				startActivity(intent_top_bac);
				t3 = 0;
				intnumber();
				break;
			}
			Intent intent_rel_party_manage = new Intent();
			intent_rel_party_manage.setClass(getActivity(), PartyManageActivity.class);
			startActivity(intent_rel_party_manage);
			t3 = 0;
			intnumber();
			break;
		case R.id.rel_weblearn:
			t4 = 0;
			intnumber();
			if (ticket.equals("")) {
				Intent intent_top_bac = new Intent();
				intent_top_bac.setClass(getActivity(), LoginActivity.class);
				startActivity(intent_top_bac);
				break;
			}
			Intent intent_rel_weblearn = new Intent();
			intent_rel_weblearn.setClass(getActivity(), WebLearnActivity.class);
			startActivity(intent_rel_weblearn);
			break;
		case R.id.rel_clean:
			t5 = 0;
			intnumber();
			if (ticket.equals("")) {
				Intent intent_top_bac = new Intent();
				intent_top_bac.setClass(getActivity(), LoginActivity.class);
				startActivity(intent_top_bac);
				break;
			}
			Intent intent_rel_clean = new Intent();
			intent_rel_clean.setClass(getActivity(), CleanActivity.class);
			startActivity(intent_rel_clean);
			break;
		case R.id.rel_disciplinary:
			t6 = 0;
			intnumber();
			if (ticket.equals("")) {
				Intent intent_top_bac = new Intent();
				intent_top_bac.setClass(getActivity(), LoginActivity.class);
				startActivity(intent_top_bac);
				break;
			}
			Intent intent_rel_disciplinary = new Intent();
			intent_rel_disciplinary.setClass(getActivity(), DisciplinaryActivity.class);
			startActivity(intent_rel_disciplinary);
			break;
		case R.id.rel_member:
			t7 = 0;
			intnumber();
			if (ticket.equals("")) {
				Intent intent_top_bac = new Intent();
				intent_top_bac.setClass(getActivity(), LoginActivity.class);
				startActivity(intent_top_bac);
				break;
			}
			Intent intent_rel_member = new Intent();
			intent_rel_member.setClass(getActivity(), MemberActivity.class);
			startActivity(intent_rel_member);
			break;
		case R.id.rel_flag:
			t8 = 0;
			intnumber();
			if (ticket.equals("")) {
				Intent intent_top_bac = new Intent();
				intent_top_bac.setClass(getActivity(), LoginActivity.class);
				startActivity(intent_top_bac);
				break;
			}
			Intent intent_rel_flag = new Intent();
			intent_rel_flag.setClass(getActivity(), FlagActivity.class);
			startActivity(intent_rel_flag);
			break;
		case R.id.main_top_bac:
			if (ticket.equals("")) {
				Intent intent_top_bac = new Intent();
				intent_top_bac.setClass(getActivity(), LoginActivity.class);
				startActivity(intent_top_bac);
			}

			break;
		default:
			break;
		}
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

		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
		}

		@Override
		public void onPageScrollStateChanged(int arg0) {
		}
	}
}
