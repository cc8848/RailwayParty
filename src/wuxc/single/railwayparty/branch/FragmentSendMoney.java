package wuxc.single.railwayparty.branch;

import java.util.ArrayList;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import wuxc.single.railwayparty.R;
import wuxc.single.railwayparty.internet.GetBitmapFromServer;
import wuxc.single.railwayparty.internet.HttpGetData;
import wuxc.single.railwayparty.internet.URLcontainer;
import wuxc.single.railwayparty.layout.RoundImageView;
import wuxc.single.railwayparty.layout.dialogselecttwo;
import wuxc.single.railwayparty.layout.dialogtwo;

public class FragmentSendMoney extends Fragment implements OnClickListener {
	private View view;// 缓存Fragment view
	private TextView TextYear;
	private TextView TextTotalPay;
	private ImageView ImageLeft;
	private ImageView ImageRight;

	private Button BtnPay;
	private String idnumber;
	private String name;

	private RelativeLayout RelativeResult;
	private LinearLayout LinResult;
	private LinearLayout LinButtonResult;
	private LinearLayout Lin1;
	private LinearLayout Lin2;
	private LinearLayout Lin3;
	private LinearLayout LinNumber1;
	private LinearLayout LinNumber2;
	private LinearLayout LinNumber3;
	private LinearLayout LinNumber4;
	private LinearLayout LinNumber5;
	private LinearLayout LinNumber6;
	private LinearLayout LinNumber7;
	private LinearLayout LinNumber8;
	private LinearLayout LinNumber9;
	private LinearLayout LinNumber10;
	private LinearLayout LinNumber11;
	private LinearLayout LinNumber12;
	private ImageView ImageNumber1;
	private ImageView ImageNumber2;
	private ImageView ImageNumber3;
	private ImageView ImageNumber4;
	private ImageView ImageNumber5;
	private ImageView ImageNumber6;
	private ImageView ImageNumber7;
	private ImageView ImageNumber8;
	private ImageView ImageNumber9;
	private ImageView ImageNumber10;
	private ImageView ImageNumber11;
	private ImageView ImageNumber12;
	private ImageView ImageDot1;
	private ImageView ImageDot2;
	private ImageView ImageDot3;
	private ImageView ImageDot4;
	private ImageView ImageDot5;
	private ImageView ImageDot6;
	private ImageView ImageDot7;
	private ImageView ImageDot8;
	private ImageView ImageDot9;
	private ImageView ImageDot10;
	private ImageView ImageDot11;
	private ImageView ImageDot12;
	private boolean GetMonth = true;
	private int[] DotResourceId = { R.drawable.dot_ok, R.drawable.dot_grey };
	private int[][] NumberResourceId = {
			{ R.drawable.h1, R.drawable.h2, R.drawable.h3, R.drawable.h4, R.drawable.h5, R.drawable.h6, R.drawable.h7,
					R.drawable.h8, R.drawable.h9, R.drawable.h10, R.drawable.h11, R.drawable.h12 },
			{ R.drawable.ns1, R.drawable.ns2, R.drawable.ns3, R.drawable.ns4, R.drawable.ns5, R.drawable.ns6,
					R.drawable.ns7, R.drawable.ns8, R.drawable.ns9, R.drawable.ns10, R.drawable.ns11,
					R.drawable.ns12 }, };
	// 0--已交
	// 1--未交

	private int[] status = { 1, 0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0 };
	// 0--未选
	// 1--已选
	private int[] condition = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };

	private double[] money = { 14, 56, 32, 23, 21, 53, 123.7, 344.7, 3454.6, 56.9, 786.9, 976.5 };
	private double totalpay = 0.0;
	private int screenwidth = 0;
	private float scale = 0;
	private float scalepx = 0;
	private float dp = 0;
	private int inityear = 2017;
	private String monthstring = "";
	private static final String[] STR_MONTH = { "-01", "-02", "-03", "-04", "-05", "-06", "-07", "-08", "-09", "-10",
			"-11", "-12", };

	// 0-无这一年的数据
	// 1-有这一年的数据

	private String ticket = "";
	private final static int GET_USER_HEAD_IMAGE = 6;
	private SharedPreferences PreUserInfo;// 存储个人信息
	private static final int GET_MONTH_RESULT_DATA = 1;
	private static final String GET_SUCCESS_RESULT = "success";
	private String Months = null;
	private double salary = 0;
	private String rechargeresult = "";
	private boolean canwrite = true;
	private String orderId = "";
	private static final int GET_DUE_DATA = 9;
	private String Month = "";
	private final static int GO_CHANGE_HEADIMG = 8;

	private static final String GET_FAIL_RESULT = "fail";
	private TextView text_last_month;
	private TextView text_last_time;
	private TextView text_name;
	private Button btn_ok;
	private double pMonthFaredouble;

	private RoundImageView round_headimg;
	private String userPhoto;
	protected void ShowHeadImage(Object obj) {
		// TODO Auto-generated method stub
		if (!(obj == null)) {
			try {
				Bitmap HeadImage = (Bitmap) obj;
				round_headimg.setImageBitmap(HeadImage);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}

	public Handler uiHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case GET_USER_HEAD_IMAGE:
				ShowHeadImage(msg.obj);
				break;
			case GET_MONTH_RESULT_DATA:
				GetDataDetailFromMonth(msg.obj);
				break;
			case GET_DUE_DATA:
				GetDataDueData(msg.obj);
				break;
			default:
				break;
			}
		}
	};

	protected void GetDataDueData(Object obj) {

		// TODO Auto-generated method stub
		String Type = null;
		String Data = "";
		try {
			JSONObject demoJson = new JSONObject(obj.toString());
			Type = demoJson.getString("type");

			if (Type.equals(GET_SUCCESS_RESULT)) {
				orderId = "";
				orderId = demoJson.getString("orderId");
				totalpay = demoJson.getDouble("payMoney");
				if (!orderId.equals("")) {
					rechargeresult = "success";
				} else {
					rechargeresult = "";
				}
				// showalert();
			} else if (Type.equals(GET_FAIL_RESULT)) {
				Toast.makeText(getActivity(), "服务器数据失败", Toast.LENGTH_SHORT).show();
			} else {
				Toast.makeText(getActivity(), "数据格式校验失败", Toast.LENGTH_SHORT).show();
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	protected void GetDataDetailFromMonth(Object obj) {

		// TODO Auto-generated method stub
		String Type = null;
		String Data = null;

		try {
			JSONObject demoJson = new JSONObject(obj.toString());
			Type = demoJson.getString("type");
			Data = demoJson.getString("data");
			if (Type.equals(GET_SUCCESS_RESULT)) {
				GetDetailData(Data);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private void GetDetailData(String data) {
		// TODO Auto-generated method stub

		String months = null;
		try {
			JSONObject demoJson = new JSONObject(data);
			name = demoJson.getString("name");
			months = demoJson.getString("months");
			// salary = demoJson.getDouble("salary");
			// for (int i = 0; i < 12; i++) {
			// money[i] = salary;
			// }
			Months = months;

			GetMonth(months);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private void GetMonth(String months) {
		// TODO Auto-generated method stub
		for (int i = 0; i < 12; i++) {
			status[i] = 0;
		}
		JSONArray jArray;
		try {
			jArray = new JSONArray(months);

			for (int i = 0; i < jArray.length(); i++) {

				String month = jArray.getString(i);
				for (int j = 0; j < 12; j++) {
					if (month.equals(inityear + STR_MONTH[j])) {
						status[j] = 1;
					}
				}
			}
			ShowResult();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
			view = inflater.inflate(R.layout.wuxc_fragment_send_money, container, false);
			initview(view);
			setonclicklistener();
			setlayoutheight();
			setinitview();
			PreUserInfo = getActivity().getSharedPreferences("UserInfo", Context.MODE_PRIVATE);
			ReadTicket();
			GetHeadPic();
		}

		return view;
	}
	private void GetHeadPic() {
		// TODO Auto-generated method stub
		new Thread(new Runnable() { // 开启线程上传文件
			@Override
			public void run() {
				Bitmap HeadImage = null;
				HeadImage = GetBitmapFromServer
						.getBitmapFromServer(URLcontainer.urlip + URLcontainer.GetFile + userPhoto);
				Message msg = new Message();
				msg.what = GET_USER_HEAD_IMAGE;
				msg.obj = HeadImage;
				uiHandler.sendMessage(msg);
			}
		}).start();
	}
	private void ReadTicket() {
		// TODO Auto-generated method stub
		userPhoto = PreUserInfo.getString("userPhoto", null);
		ticket = PreUserInfo.getString("ticket", "");
		idnumber = PreUserInfo.getString("icardNo", "");
		text_name = (TextView) view.findViewById(R.id.text_name);
		text_name.setText(PreUserInfo.getString("userName", ""));
		text_last_month.setText("截止日期：" + PreUserInfo.getString("pFareEndMonth", ""));
		text_last_time.setText("每月党费：" + PreUserInfo.getString("pMonthFare", ""));
		try {
			pMonthFaredouble = Double.parseDouble(PreUserInfo.getString("pMonthFare", ""));
			for (int i = 0; i < money.length; i++) {
				money[i] = pMonthFaredouble;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		// LoginId = PreUserInfo.getString("userName", "");

	}

	private void setinitview() {
		// TODO Auto-generated method stub
		RelativeResult.setVisibility(View.INVISIBLE);
		LinResult.setVisibility(View.INVISIBLE);
		LinButtonResult.setVisibility(View.INVISIBLE);
	}

	private void setlayoutheight() {
		// TODO Auto-generated method stub
		screenwidth = getActivity().getWindow().getWindowManager().getDefaultDisplay().getWidth();
		DisplayMetrics mMetrics = new DisplayMetrics();
		getActivity().getWindowManager().getDefaultDisplay().getMetrics(mMetrics);
		scale = getResources().getDisplayMetrics().density;
		dp = screenwidth / scale + 0.5f;
		scalepx = screenwidth / dp;
		int height = (int) ((screenwidth - 67 * scalepx) / 4);
		LinearLayout.LayoutParams layoutParams1 = (android.widget.LinearLayout.LayoutParams) Lin1.getLayoutParams();
		layoutParams1.height = height;
		Lin1.setLayoutParams(layoutParams1);
		layoutParams1 = (android.widget.LinearLayout.LayoutParams) Lin2.getLayoutParams();
		layoutParams1.height = height;
		Lin2.setLayoutParams(layoutParams1);
		layoutParams1 = (android.widget.LinearLayout.LayoutParams) Lin3.getLayoutParams();
		layoutParams1.height = height;
		Lin3.setLayoutParams(layoutParams1);
	}

	private void setonclicklistener() {
		// TODO Auto-generated method stub

		TextYear.setOnClickListener(this);
		TextTotalPay.setOnClickListener(this);
		ImageLeft.setOnClickListener(this);
		ImageRight.setOnClickListener(this);

		BtnPay.setOnClickListener(this);

		RelativeResult.setOnClickListener(this);
		LinResult.setOnClickListener(this);
		LinButtonResult.setOnClickListener(this);
		Lin1.setOnClickListener(this);
		Lin2.setOnClickListener(this);
		Lin3.setOnClickListener(this);
		LinNumber1.setOnClickListener(this);
		LinNumber2.setOnClickListener(this);
		LinNumber3.setOnClickListener(this);
		LinNumber4.setOnClickListener(this);
		LinNumber5.setOnClickListener(this);
		LinNumber6.setOnClickListener(this);
		LinNumber7.setOnClickListener(this);
		LinNumber8.setOnClickListener(this);
		LinNumber9.setOnClickListener(this);
		LinNumber10.setOnClickListener(this);
		LinNumber11.setOnClickListener(this);
		LinNumber12.setOnClickListener(this);
		btn_ok.setOnClickListener(this);
	}

	private void showmonth(int i) {
		// TODO Auto-generated method stub

		if (i == 11) {
			if (condition[i] == 1) {
				for (int j = 0; j < 12; j++) {
					condition[j] = 0;
				}
			} else {
				for (int j = 0; j < 12; j++) {
					condition[j] = 1;
				}
			}
		} else {
			if (condition[i] == 1 && condition[i + 1] == 1) {
				for (int j = 0; j < i + 1; j++) {
					condition[j] = 1;
				}
				for (int j = i + 1; j < 12; j++) {
					condition[j] = 0;
				}
			} else if (condition[i] == 1 && condition[i + 1] == 0) {
				for (int j = 0; j < 12; j++) {
					condition[j] = 0;
				}
			} else if (condition[i] == 0 && condition[i + 1] == 0) {
				for (int j = 0; j < i + 1; j++) {
					condition[j] = 1;
				}
				for (int j = i + 1; j < 12; j++) {
					condition[j] = 0;
				}
			} else {
				for (int j = 0; j < i + 1; j++) {
					condition[j] = 1;
				}
				for (int j = i + 1; j < 12; j++) {
					condition[j] = 0;
				}
			}
		}
		if (status[0] != 0) {
			if (condition[0] == 1) {
				ImageNumber1.setImageResource(R.drawable.rs1);
			} else {
				ImageNumber1.setImageResource(R.drawable.ns1);
			}
		}
		if (status[1] != 0) {
			if (condition[1] == 1) {
				ImageNumber2.setImageResource(R.drawable.rs2);
			} else {
				ImageNumber2.setImageResource(R.drawable.ns2);
			}
		}
		if (status[2] != 0) {
			if (condition[2] == 1) {
				ImageNumber3.setImageResource(R.drawable.rs3);
			} else {
				ImageNumber3.setImageResource(R.drawable.ns3);
			}
		}
		if (status[3] != 0) {
			if (condition[3] == 1) {
				ImageNumber4.setImageResource(R.drawable.rs4);
			} else {
				ImageNumber4.setImageResource(R.drawable.ns4);
			}
		}
		if (status[4] != 0) {
			if (condition[4] == 1) {
				ImageNumber5.setImageResource(R.drawable.rs5);
			} else {
				ImageNumber5.setImageResource(R.drawable.ns5);
			}
		}
		if (status[5] != 0) {
			if (condition[5] == 1) {
				ImageNumber6.setImageResource(R.drawable.rs6);
			} else {
				ImageNumber6.setImageResource(R.drawable.ns6);
			}
		}
		if (status[6] != 0) {
			if (condition[6] == 1) {
				ImageNumber7.setImageResource(R.drawable.rs7);
			} else {
				ImageNumber7.setImageResource(R.drawable.ns7);
			}
		}
		if (status[7] != 0) {
			if (condition[7] == 1) {
				ImageNumber8.setImageResource(R.drawable.rs8);
			} else {
				ImageNumber8.setImageResource(R.drawable.ns8);
			}
		}
		if (status[8] != 0) {
			if (condition[8] == 1) {
				ImageNumber9.setImageResource(R.drawable.rs9);
			} else {
				ImageNumber9.setImageResource(R.drawable.ns9);
			}
		}
		if (status[9] != 0) {
			if (condition[9] == 1) {
				ImageNumber10.setImageResource(R.drawable.rs10);
			} else {
				ImageNumber10.setImageResource(R.drawable.ns10);
			}
		}
		if (status[10] != 0) {
			if (condition[10] == 1) {
				ImageNumber11.setImageResource(R.drawable.rs11);
			} else {
				ImageNumber11.setImageResource(R.drawable.ns11);
			}
		}
		if (status[11] != 0) {
			if (condition[11] == 1) {
				ImageNumber12.setImageResource(R.drawable.rs12);
			} else {
				ImageNumber12.setImageResource(R.drawable.ns12);
			}
		}
		ShowPayMoney();
	}

	private void initview(View view) {
		// TODO Auto-generated method stub
		round_headimg = (RoundImageView) view.findViewById(R.id.image_headimg);

		btn_ok = (Button) view.findViewById(R.id.btn_ok);
		TextYear = (TextView) view.findViewById(R.id.text_year);
		TextTotalPay = (TextView) view.findViewById(R.id.text_total_pay);
		ImageLeft = (ImageView) view.findViewById(R.id.image_left);
		ImageRight = (ImageView) view.findViewById(R.id.image_right);
		text_last_month = (TextView) view.findViewById(R.id.text_last_month);
		text_last_time = (TextView) view.findViewById(R.id.text_last_time);
		BtnPay = (Button) view.findViewById(R.id.btn_pay);
		RelativeResult = (RelativeLayout) view.findViewById(R.id.relative_result);
		LinResult = (LinearLayout) view.findViewById(R.id.lin_result);
		LinButtonResult = (LinearLayout) view.findViewById(R.id.lin_button_result);
		Lin1 = (LinearLayout) view.findViewById(R.id.lin_1);
		Lin2 = (LinearLayout) view.findViewById(R.id.lin_2);
		Lin3 = (LinearLayout) view.findViewById(R.id.lin_3);
		LinNumber1 = (LinearLayout) view.findViewById(R.id.lin_number_1);
		LinNumber2 = (LinearLayout) view.findViewById(R.id.lin_number_2);
		LinNumber3 = (LinearLayout) view.findViewById(R.id.lin_number_3);
		LinNumber4 = (LinearLayout) view.findViewById(R.id.lin_number_4);
		LinNumber5 = (LinearLayout) view.findViewById(R.id.lin_number_5);
		LinNumber6 = (LinearLayout) view.findViewById(R.id.lin_number_6);
		LinNumber7 = (LinearLayout) view.findViewById(R.id.lin_number_7);
		LinNumber8 = (LinearLayout) view.findViewById(R.id.lin_number_8);
		LinNumber9 = (LinearLayout) view.findViewById(R.id.lin_number_9);
		LinNumber10 = (LinearLayout) view.findViewById(R.id.lin_number_10);
		LinNumber11 = (LinearLayout) view.findViewById(R.id.lin_number_11);
		LinNumber12 = (LinearLayout) view.findViewById(R.id.lin_number_12);
		ImageNumber1 = (ImageView) view.findViewById(R.id.image_number_1);
		ImageNumber2 = (ImageView) view.findViewById(R.id.image_number_2);
		ImageNumber3 = (ImageView) view.findViewById(R.id.image_number_3);
		ImageNumber4 = (ImageView) view.findViewById(R.id.image_number_4);
		ImageNumber5 = (ImageView) view.findViewById(R.id.image_number_5);
		ImageNumber6 = (ImageView) view.findViewById(R.id.image_number_6);
		ImageNumber7 = (ImageView) view.findViewById(R.id.image_number_7);
		ImageNumber8 = (ImageView) view.findViewById(R.id.image_number_8);
		ImageNumber9 = (ImageView) view.findViewById(R.id.image_number_9);
		ImageNumber10 = (ImageView) view.findViewById(R.id.image_number_10);
		ImageNumber11 = (ImageView) view.findViewById(R.id.image_number_11);
		ImageNumber12 = (ImageView) view.findViewById(R.id.image_number_12);
		ImageDot1 = (ImageView) view.findViewById(R.id.image_dot_1);
		ImageDot2 = (ImageView) view.findViewById(R.id.image_dot_2);
		ImageDot3 = (ImageView) view.findViewById(R.id.image_dot_3);
		ImageDot4 = (ImageView) view.findViewById(R.id.image_dot_4);
		ImageDot5 = (ImageView) view.findViewById(R.id.image_dot_5);
		ImageDot6 = (ImageView) view.findViewById(R.id.image_dot_6);
		ImageDot7 = (ImageView) view.findViewById(R.id.image_dot_7);
		ImageDot8 = (ImageView) view.findViewById(R.id.image_dot_8);
		ImageDot9 = (ImageView) view.findViewById(R.id.image_dot_9);
		ImageDot10 = (ImageView) view.findViewById(R.id.image_dot_10);
		ImageDot11 = (ImageView) view.findViewById(R.id.image_dot_11);
		ImageDot12 = (ImageView) view.findViewById(R.id.image_dot_12);

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.lin_number_1:
			if (status[0] == 0) {
				Toast.makeText(getActivity(), "1月党费已交", Toast.LENGTH_SHORT).show();
			} else {
				showmonth(0);
			}
			break;
		case R.id.lin_number_2:
			if (status[1] == 0) {
				Toast.makeText(getActivity(), "2月党费已交", Toast.LENGTH_SHORT).show();
			} else {
				showmonth(1);
			}
			break;
		case R.id.lin_number_3:
			if (status[2] == 0) {
				Toast.makeText(getActivity(), "3月党费已交", Toast.LENGTH_SHORT).show();
			} else {
				showmonth(2);
			}
			break;
		case R.id.lin_number_4:
			if (status[3] == 0) {
				Toast.makeText(getActivity(), "4月党费已交", Toast.LENGTH_SHORT).show();
			} else {
				showmonth(3);
			}
			break;
		case R.id.lin_number_5:
			if (status[4] == 0) {
				Toast.makeText(getActivity(), "5月党费已交", Toast.LENGTH_SHORT).show();
			} else {
				showmonth(4);
			}
			break;
		case R.id.lin_number_6:
			if (status[5] == 0) {
				Toast.makeText(getActivity(), "6月党费已交", Toast.LENGTH_SHORT).show();
			} else {
				showmonth(5);
			}
			break;
		case R.id.lin_number_7:
			if (status[6] == 0) {
				Toast.makeText(getActivity(), "7月党费已交", Toast.LENGTH_SHORT).show();
			} else {
				showmonth(6);
			}
			break;
		case R.id.lin_number_8:
			if (status[7] == 0) {
				Toast.makeText(getActivity(), "8月党费已交", Toast.LENGTH_SHORT).show();
			} else {
				showmonth(7);
			}
			break;
		case R.id.lin_number_9:
			if (status[8] == 0) {
				Toast.makeText(getActivity(), "9月党费已交", Toast.LENGTH_SHORT).show();
			} else {
				showmonth(8);
			}
			break;
		case R.id.lin_number_10:
			if (status[9] == 0) {
				Toast.makeText(getActivity(), "10月党费已交", Toast.LENGTH_SHORT).show();
			} else {
				showmonth(9);
			}
			break;
		case R.id.lin_number_11:
			if (status[10] == 0) {
				Toast.makeText(getActivity(), "11月党费已交", Toast.LENGTH_SHORT).show();
			} else {
				showmonth(10);
			}
			break;
		case R.id.lin_number_12:
			if (status[11] == 0) {
				Toast.makeText(getActivity(), "12月党费已交", Toast.LENGTH_SHORT).show();
			} else {
				showmonth(11);
			}
			break;

		case R.id.image_left:
			inityear = inityear - 1;
			GetMonth(Months);
			break;
		case R.id.image_right:
			inityear = inityear + 1;
			GetMonth(Months);
			break;
		case R.id.text_year:

			break;
		case R.id.btn_pay:
			if (monthstring.equals("")) {
				Toast.makeText(getActivity(), "您未选择要支付的月份", Toast.LENGTH_SHORT).show();

			} else if (canwrite) {
				selecttype();
			} else {
				Toast.makeText(getActivity(), "请勿重复提交", Toast.LENGTH_SHORT).show();

			}

			break;
		case R.id.btn_ok:

			ShowResult();

			GetMonthData(idnumber);
			break;
		default:
			break;
		}
	}

	private void GetMonthData(String idnumber) {
		// TODO Auto-generated method stub
		final ArrayList ArrayValues = new ArrayList();
		ArrayValues.add(new BasicNameValuePair("idCardNo", idnumber));
		ArrayValues.add(new BasicNameValuePair("ticket", "" + ticket));
		new Thread(new Runnable() { // 开启线程上传文件
			@Override
			public void run() {
				String LoginResultData = "";
				LoginResultData = HttpGetData.GetData("api/pb/common/queryFare", ArrayValues);
				Message msg = new Message();
				msg.obj = LoginResultData;
				msg.what = GET_MONTH_RESULT_DATA;
				uiHandler.sendMessage(msg);
			}
		}).start();
	}

	private void selecttype() {

		dialogselecttwo.Builder builder = new dialogselecttwo.Builder(getActivity());
		builder.setMessage("请选择缴费方式");
		builder.setTitle("提示");
		builder.setPositiveButton("线上缴费", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
				showAlertDialog();
			}

		});

		builder.setNegativeButton("线下缴费", new android.content.DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
				showAlert();
			}
		});

		builder.create().show();

	}

	public void showAlertDialog() {

		dialogtwo.Builder builder = new dialogtwo.Builder(getActivity());
		builder.setMessage("您将支付" + totalpay + "元\n用于缴纳" + inityear + "年" + monthstring + "月份党费");
		builder.setTitle("缴费确认");
		builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();

			}
		});

		builder.setNegativeButton("取消", new android.content.DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});

		builder.create().show();

	}

	public void showAlert() {

		dialogtwo.Builder builder = new dialogtwo.Builder(getActivity());
		builder.setMessage("请您尽快进行线下缴费");
		builder.setTitle("温馨提示");
		builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();

			}
		});

		builder.setNegativeButton("取消", new android.content.DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});

		builder.create().show();

	}

	// private void GetMonth(String months) {
	// // TODO Auto-generated method stub
	// for (int i = 0; i < 12; i++) {
	// status[i] = 0;
	// }
	// // JSONArray jArray;
	// // try {
	// // jArray = new JSONArray(months);
	// //
	// // for (int i = 0; i < jArray.length(); i++) {
	// //
	// // String month = jArray.getString(i);
	// // for (int j = 0; j < 12; j++) {
	// // if (month.equals(inityear + STR_MONTH[j])) {
	// // status[j] = 1;
	// // }
	// // }
	// // }
	// // ShowResult();
	// // } catch (JSONException e) {
	// // // TODO Auto-generated catch block
	// // e.printStackTrace();
	// // }
	// TextYear.setText("" + inityear);
	// setImage();
	// }

	private void ShowResult() {
		// TODO Auto-generated method stub
		RelativeResult.setVisibility(View.VISIBLE);
		LinResult.setVisibility(View.VISIBLE);
		LinButtonResult.setVisibility(View.VISIBLE);
		btn_ok.setVisibility(View.GONE);

		text_last_time.setVisibility(View.GONE);
		text_last_month.setVisibility(View.GONE);
		TextYear.setText("" + inityear);
		setImage();
	}

	public void setImage() {
		// TODO Auto-generated method stub
		ImageView[] ImageNumber = { ImageNumber1, ImageNumber2, ImageNumber3, ImageNumber4, ImageNumber5, ImageNumber6,
				ImageNumber7, ImageNumber8, ImageNumber9, ImageNumber10, ImageNumber11, ImageNumber12 };
		ImageView[] ImageDot = { ImageDot1, ImageDot2, ImageDot3, ImageDot4, ImageDot5, ImageDot6, ImageDot7, ImageDot8,
				ImageDot9, ImageDot10, ImageDot11, ImageDot12 };
		for (int i = 0; i < 12; i++) {
			ImageNumber[i].setImageResource(NumberResourceId[status[i]][i]);
			ImageDot[i].setImageResource(DotResourceId[status[i]]);
		}

	}

	private void ShowPayMoney() {
		// TODO Auto-generated method stub
		double result = 0;
		monthstring = "";
		Month = "";
		for (int i = 0; i < 12; i++) {
			if (condition[i] == 1) {
				result = result + money[i];
				monthstring = monthstring + (i + 1) + "、";
				Month = Month + (inityear + STR_MONTH[i]) + ",";
			}

		}
		if (!monthstring.equals("")) {
			String b[] = monthstring.split("");
			monthstring = "";
			for (int i = 0; i < b.length - 1; i++) {
				monthstring = monthstring + b[i];
			}
			String c[] = Month.split("");
			Month = "";
			for (int i = 0; i < c.length - 1; i++) {
				Month = Month + c[i];
			}
		}

		totalpay = result;
		TextTotalPay.setText("共计" + result + "元");
	}
}
