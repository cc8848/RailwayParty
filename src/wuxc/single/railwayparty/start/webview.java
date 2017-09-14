package wuxc.single.railwayparty.start;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import wuxc.single.railwayparty.R;
import wuxc.single.railwayparty.internet.GetUnreadNumber;
import wuxc.single.railwayparty.internet.HttpGetData;

public class webview extends Activity implements OnClickListener {
	private WebView WebView;
	private String targeturl;

	private ImageView ImageBack;
	private TextView text_1;
	private TextView text_2;
	private TextView text_3;
	private TextView text_4;
	private TextView text_5;
	private TextView text_6;
	private TextView text_7;
	private LinearLayout lin_text;
	private String Name;
	private String Title;
	private String Time;
	// List<CommentModel> list = new ArrayList<CommentModel>();
	// private static CommentAdapter mAdapter;
	private int pageSize = 10;
	private int totalPage = 5;
	private int curPage = 1;
	private int screenwidth = 0;
	private float scale = 0;
	private float scalepx = 0;
	private float dp = 0;
	private String detail = "此次专项检查的范围是招用农民工较多的建筑、制造、采矿、餐饮和其他中小型劳动密集型企业以及个体经济组织。检查内容包括：非公企业与劳动者签订劳动合同情况；按照工资支付有关规定支付职工工资情况；遵守最低工资规定及依法支付加班工资情况；依法参加社会保险和缴纳社会保险费情况；遵守禁止使用童工规定以及女职工和未成年工特殊劳动保护规定情况；其他遵守劳动保障法律法规的情况。";
	private String Id = "";
	private String ticket = "";
	private String chn;
	private int recLen = 60;
	private String cover = "";
	Timer timer = new Timer();
	private boolean read = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.webview);
		WebView = (android.webkit.WebView) findViewById(R.id.webview);
		Intent intent = this.getIntent(); // 获取已有的intent对象
		Bundle bundle = intent.getExtras(); // 获取intent里面的bundle对象
		targeturl = bundle.getString("url");
		Name = bundle.getString("Name");
		Title = bundle.getString("Title");
		Time = bundle.getString("Time");
		Id = bundle.getString("Id");

		chn = bundle.getString("chn");

		try {
			detail = bundle.getString("detail");
			ticket = bundle.getString("ticket");
			cover = bundle.getString("cover");
			read = bundle.getBoolean("read");
		} catch (Exception e) {
			// TODO: handle exception
		}
		timer.schedule(task, 1000, 1000); // timeTask
		WebView.loadUrl(targeturl);
		ImageBack = (ImageView) findViewById(R.id.image_back);
		ImageBack.setOnClickListener(this);
		text_1 = (TextView) findViewById(R.id.text_1);
		text_2 = (TextView) findViewById(R.id.text_2);
		text_3 = (TextView) findViewById(R.id.text_3);
		text_4 = (TextView) findViewById(R.id.text_4);
		text_5 = (TextView) findViewById(R.id.text_5);
		text_6 = (TextView) findViewById(R.id.text_6);
		text_7 = (TextView) findViewById(R.id.text_7);
		text_1.setOnClickListener(this);
		text_2.setOnClickListener(this);
		text_3.setOnClickListener(this);
		text_4.setOnClickListener(this);
		text_5.setOnClickListener(this);
		text_6.setOnClickListener(this);
		text_7.setOnClickListener(this);
		lin_text = (LinearLayout) findViewById(R.id.lin_text);
		lin_text.setVisibility(View.GONE);
		if (!read) {
			record();
		}
		final ArrayList ArrayValues = new ArrayList();
		// ArrayValues.add(new BasicNameValuePair("ticket", ticket));
		// ArrayValues.add(new BasicNameValuePair("applyType", "" + 2));
		// ArrayValues.add(new BasicNameValuePair("helpSType", "" +
		// type));
		// ArrayValues.add(new BasicNameValuePair("modelSign",
		// "KNDY_APPLY"));
		// ArrayValues.add(new BasicNameValuePair("curPage", "" +
		// curPage));
		// ArrayValues.add(new BasicNameValuePair("pageSize", "" +
		// pageSize));
		// final ArrayList ArrayValues = new ArrayList();
		ArrayValues.add(new BasicNameValuePair("ticket", "" + ticket));
		// chn = GetChannelByKey.GetSign(PreALLChannel, "职工之家");
		ArrayValues.add(new BasicNameValuePair("chn", chn));

		ArrayValues.add(new BasicNameValuePair("datakey", "" + Id));

		new Thread(new Runnable() { // 开启线程上传文件
			@Override
			public void run() {
				String DueData = "";
				DueData = HttpGetData.GetData("api/cms/common/browserModelItem", ArrayValues);

			}
		}).start();
		// WebView.setWebChromeClient(new WebChromeClient() {
		// @Override
		// public void onShowCustomView(View view, CustomViewCallback callback)
		// {
		// super.onShowCustomView(view, callback);
		// }
		//
		// });
		// //
		// WebView.getSettings().setLayoutAlgorithm(LayoutAlgorithm.SINGLE_COLUMN);
		// // WebView.getSettings().setLoadWithOverviewMode(true);
		//
		// WebView.setWebViewClient(new WebViewClient() {
		// @Override
		// public void onPageFinished(WebView view, String url) {
		// super.onPageFinished(view, url);
		// }
		//
		// @Override
		// public void onPageStarted(WebView view, String url, Bitmap favicon) {
		// // TODO Auto-generated method stub
		// super.onPageStarted(view, url, favicon);
		// }
		//
		// public boolean shouldOverrideUrlLoading(WebView view, String url) {
		// // loadTime();
		// return true; // 表 示已经处理了这次URL的请求
		// }
		//
		// });
		WebSettings settings = WebView.getSettings();
		settings.setJavaScriptEnabled(true);
		settings.setUseWideViewPort(true);
		settings.setLoadWithOverviewMode(true);
		settings.setTextSize(WebSettings.TextSize.NORMAL);
		try {
			GetUnreadNumber.getunreadnumber(this);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private void record() {
		// TODO Auto-generated method stub
		final ArrayList ArrayValues = new ArrayList();

		ArrayValues.add(new BasicNameValuePair("ticket", "" + ticket));

		ArrayValues.add(new BasicNameValuePair("accessRecordDto.classify", chn));

		ArrayValues.add(new BasicNameValuePair("accessRecordDto.busKey", "" + Id));
		ArrayValues.add(new BasicNameValuePair("accessRecordDto.bigClassify", "channel"));
		ArrayValues.add(new BasicNameValuePair("accessRecordDto.title", "" + Title));

		new Thread(new Runnable() { // 开启线程上传文件
			@Override
			public void run() {
				String DueData = "";
				DueData = HttpGetData.GetData("api/pubshare/accessRecord/save", ArrayValues);

			}
		}).start();
	}

	TimerTask task = new TimerTask() {
		@Override
		public void run() {
			recLen++;
			Message message = new Message();
			message.what = 1;
			handler.sendMessage(message);
		}
	};
	final Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 1:

				if (recLen < 0) {
					timer.cancel();

				}
			}
		}
	};

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		timer.cancel();
		WebView.destroy();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if ((keyCode == KeyEvent.KEYCODE_BACK)) {
			if (true) {
				sendpost();
				finish();

				final ArrayList ArrayValues = new ArrayList();
				// ArrayValues.add(new BasicNameValuePair("ticket",
				// ticket));
				// ArrayValues.add(new BasicNameValuePair("applyType",
				// "" + 2));
				// ArrayValues.add(new BasicNameValuePair("helpSType",
				// "" + type));
				// ArrayValues.add(new BasicNameValuePair("modelSign",
				// "KNDY_APPLY"));
				// ArrayValues.add(new BasicNameValuePair("curPage", ""
				// + curPage));
				// ArrayValues.add(new BasicNameValuePair("mobile", "" +
				// text_phone.getText().toString()));
				// final ArrayList ArrayValues = new ArrayList();
				ArrayValues.add(new BasicNameValuePair("ticket", "" + ticket));
				// chn = GetChannelByKey.GetSign(PreALLChannel, "职工之家");
				// ArrayValues.add(new BasicNameValuePair("chn",
				// "dyq"));
				// chn = "dyq";
				ArrayValues.add(new BasicNameValuePair("learnRecordDto.title", Title));
				ArrayValues.add(new BasicNameValuePair("learnRecordDto.timeLength", "" + (recLen / 60)));
				ArrayValues.add(new BasicNameValuePair("learnRecordDto.cover", "" + cover));
				ArrayValues.add(new BasicNameValuePair("learnRecordDto.content", "" + detail));
				// ArrayValues.add(new BasicNameValuePair("classify", ""
				// +
				// classify));

				new Thread(new Runnable() { // 开启线程上传文件
					@Override
					public void run() {
						String DueData = "";
						DueData = HttpGetData.GetData("api/pb/learnRecord/save", ArrayValues);
						// Message msg = new Message();
						// msg.obj = DueData;
						// msg.what = 17;
						// uiHandler.sendMessage(msg);
					}
				}).start();

			}
			return false;
		} else {
			return super.onKeyDown(keyCode, event);
		}

	}

	private void sendpost() {
		if (recLen > 600) {
			// TODO Auto-generated method stub
			final ArrayList ArrayValues = new ArrayList();
			ArrayValues.add(new BasicNameValuePair("userScoreDto.inOut", "1"));
			ArrayValues.add(new BasicNameValuePair("userScoreDto.classify", "specialActivity"));
			ArrayValues.add(new BasicNameValuePair("userScoreDto.amount", "2"));
			ArrayValues.add(new BasicNameValuePair("userScoreDto.reason", "网上党校学习《" + Title + "》"));
			ArrayValues.add(new BasicNameValuePair("ticket", ticket));
			new Thread(new Runnable() { // 开启线程上传文件
				@Override
				public void run() {
					HttpGetData.GetData("api/console/userScore/save", ArrayValues);

				}
			}).start();
		}

	}

	@Override
	protected void onPause() {
		// WebView.reload();

		super.onPause();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		WebSettings settings = WebView.getSettings();
		switch (v.getId()) {
		case R.id.image_back:
			sendpost();
			finish();
			final ArrayList ArrayValues = new ArrayList();
			// ArrayValues.add(new BasicNameValuePair("ticket",
			// ticket));
			// ArrayValues.add(new BasicNameValuePair("applyType",
			// "" + 2));
			// ArrayValues.add(new BasicNameValuePair("helpSType",
			// "" + type));
			// ArrayValues.add(new BasicNameValuePair("modelSign",
			// "KNDY_APPLY"));
			// ArrayValues.add(new BasicNameValuePair("curPage", ""
			// + curPage));
			// ArrayValues.add(new BasicNameValuePair("mobile", "" +
			// text_phone.getText().toString()));
			// final ArrayList ArrayValues = new ArrayList();
			ArrayValues.add(new BasicNameValuePair("ticket", "" + ticket));
			// chn = GetChannelByKey.GetSign(PreALLChannel, "职工之家");
			// ArrayValues.add(new BasicNameValuePair("chn",
			// "dyq"));
			// chn = "dyq";
			ArrayValues.add(new BasicNameValuePair("learnRecordDto.title", Title));
			ArrayValues.add(new BasicNameValuePair("learnRecordDto.timeLength", "" + (recLen / 60)));
			ArrayValues.add(new BasicNameValuePair("learnRecordDto.cover", "" + cover));
			ArrayValues.add(new BasicNameValuePair("learnRecordDto.content", "" + detail));
			// ArrayValues.add(new BasicNameValuePair("classify", ""
			// +
			// classify));

			new Thread(new Runnable() { // 开启线程上传文件
				@Override
				public void run() {
					String DueData = "";
					DueData = HttpGetData.GetData("api/pb/learnRecord/save", ArrayValues);
					// Message msg = new Message();
					// msg.obj = DueData;
					// msg.what = 17;
					// uiHandler.sendMessage(msg);
				}
			}).start();
			break;
		case R.id.text_1:
			lin_text.setVisibility(View.GONE);

			settings.setTextSize(WebSettings.TextSize.SMALLEST);
			break;
		case R.id.text_2:
			lin_text.setVisibility(View.GONE);

			settings.setTextSize(WebSettings.TextSize.SMALLER);
			break;
		case R.id.text_3:
			lin_text.setVisibility(View.GONE);
			settings.setTextSize(WebSettings.TextSize.NORMAL);
			break;
		case R.id.text_4:
			lin_text.setVisibility(View.GONE);
			settings.setTextSize(WebSettings.TextSize.LARGER);
			break;
		case R.id.text_5:
			lin_text.setVisibility(View.GONE);
			settings.setTextSize(WebSettings.TextSize.LARGEST);
			break;
		case R.id.text_6:
			lin_text.setVisibility(View.GONE);
			break;
		case R.id.text_7:
			lin_text.setVisibility(View.VISIBLE);
			break;
		default:
			break;
		}
	}

}
