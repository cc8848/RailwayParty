package wuxc.single.railwayparty.start;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import wuxc.single.railwayparty.R;
import wuxc.single.railwayparty.internet.HttpGetData;
import wuxc.single.railwayparty.internet.URLcontainer;
import wuxc.single.railwayparty.layout.RoundImageView;

public class SpecialDetailActivity2 extends Activity implements OnClickListener, OnItemClickListener {
	// private EditText EditAnswer;
	// private Button BtnAnswer;
	private ImageView ImageBack;
	private ImageView ImagePic;
	// private ScrollView ScrollLayout;
	private ListView ListData;
	// private TextView TextWarning;
	private TextView TextDetail;
	private TextView TextTime;
	private TextView TextName;
	private TextView TextTitle;

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
	private String ticket="";
	private String chn;
	private String userPhoto;
	private String LoginId;
	private WebView webView;
	private static final String GET_SUCCESS_RESULT = "success";
	private static final String GET_FAIL_RESULT = "fail";
	private static final int GET_DUE_DATA = 6;
	private TextView text_detail;
	private String content;
	private SharedPreferences PreUserInfo;// 存储个人信息
	private Handler uiHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 0:
				// GetData();
				// Toast.makeText(getApplicationContext(), "正在加载数据",
				// Toast.LENGTH_SHORT).show();
				show();
				break;
			case GET_DUE_DATA:
				GetDataDueData(msg.obj);
				break;
			default:
				break;

			}
		}

	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.special_detail_activity3);
		// Intent intent = this.getIntent(); // 获取已有的intent对象
		// Bundle bundle = intent.getExtras(); // 获取intent里面的bundle对象
		// Name = bundle.getString("Name");
		// Title = bundle.getString("Title");
		// Time = bundle.getString("Time");
		// Id = bundle.getString("Id");
		//
		// chn = bundle.getString("chn");
		// try {
		// detail = bundle.getString("detail");
		// ticket = bundle.getInt("ticket");
		// } catch (Exception e) {
		// // TODO: handle exception
		// }
		PreUserInfo = getSharedPreferences("UserInfo", Context.MODE_PRIVATE);
		ReadTicket();
		initview();
		setonclicklistener();
		setlistheight(0);
		settext();
		starttimedelay();
		TextName.setText("作者：" + "系统管理员");
		TextTime.setText("2017");
		String html = "<html>" + "<body>" + "<table>" + "<tr>" + "<td>成都天府</td>" + "</tr>" + "</table>" + "</body>"
				+ "</html>";
		text_detail.setText("摘要：" + detail);
		detail = "<p align=\"center\"><strong><span style=\"font-size: 16px\">党员组织关系转接的基础知识</span></strong></p><p><strong><span style=\"font-size: 16px\">1、什么是党员组织关系？</span></strong></p><p><span style=\"font-size: 16px\">　　党员组织关系是指党员对党的基层组织的隶属关系。党员组织关系的概念有广义和狭义之分，广义概念包括正式组织关系和临时组织关系；狭义概念特指正式组织关系。</span></p><p><strong><span style=\"font-size: 16px\">2、党员组织关系是如何确定的？</span></strong></p><p><span style=\"font-size: 16px\">&nbsp;&nbsp;&nbsp; 《党章》第八条规定，每个党员，不论职务高低，都必须编入党的一个支部、小组或其他特定组织，参加党的组织生活，接受党内外群众的监督。</span></p><p><span style=\"font-size: 16px\">申请入党的人一经被批准入党，接收其入党的党组织就把其编入党的一个基层组织，从此就确定了他的组织关系。党员的组织关系一经确定，党员就可以而且必须参加该组织的生活，并在其中积极工作。</span></p>"
				+ "<p><strong><span style=\"font-size: 16px\">3、为什么要转移党员组织关系？</span></strong></p>"
				+ "<p><span style=\"font-size: 16px\">&nbsp;&nbsp;&nbsp; 在现实生活中，党员的学习、工作单位或生活区域是经常发生变动的，如果不转移党员组织关系，就会形成党员脱离党组织的教育、管理、监督和服务的状况。只有通过转移党员组织关系，才能保证党员不论到哪里，党组织都会有效地把他们组织起来。</span></p>"
				+ "<p><strong><span style=\"font-size: 16px\">4、什么情况下需要转移党员组织关系？</span></strong></p>"
				+ "<p><span style=\"font-size: 16px\">&nbsp;&nbsp;&nbsp; 党员因<strong>升学、毕业、就业</strong>、退休、服役、退役、调动和外出学习、务工、经商以及其他原因，需要从一个单位或地区到另一个单位或地区时，党组织应当根据有关规定，相应地改变党员的组织隶属关系，将其组织关系从原来所在的基层党组织转移到所去单位或地区的基层党组织。</span></p>"
				+ "<p><strong><span style=\"font-size: 16px\">5、转移党员组织关系总的要求是什么？</span></strong></p>"
				+ "<p><span style=\"font-size: 16px\">&nbsp;&nbsp;&nbsp; <strong>凡党员所去单位已建立党组织的，</strong>应当将其党员组织关系转移到单位党组织；<strong>单位未建立党组织的，应当将其党员组织关系转移到单位所在地党组织或单位所属行业主管部门党组织，也可以转移到党员本人居住地党组织或县级以上政府人事（劳动）部门所属的人才（劳动）服务机构党组织</strong>。</span></p>"
				+ "<p><strong><span style=\"font-size: 16px\">6、&nbsp;高校毕业生党员如何转移党员组织关系</span></strong></p>"
				+ "<p align=\"left\"><span style=\"font-size: 16px\">　　根据中央组织部《关于进一步加强党员组织关系管理的意见》（中组发［２００４］１０号）规定，高校毕业生党员，已经落实工作单位的，应将党员组织关系及时转移到所去单位党组织；尚未落实工作单位的，可将党员关系转移到本人或父母居住地的街道、乡镇党组织，也可随同档案转移到县以上政府人事（劳动）部门所属的人才（劳动）服务机构党组织。</span></p>"
				+ "<p>&nbsp;</p>"
				+ "<p style=\"text-align: center\"><strong><span style=\"font-size: 16px\">党员组织关系转接的常见问题</span></strong></p>"
				+ "<p><strong><span style=\"font-size: 16px\">1、党员组织关系和人事关系必须一致吗？</span></strong></p>"
				+ "<p><span style=\"font-size: 16px\">&nbsp;&nbsp;&nbsp; 在通常情况下，党员组织关系与人事关系是一致的，如党政机关、军队、绝大部分国有企业事业单位等。还有一些短期内没有就业的毕业生和退役军人，党员组织关系暂时转到社区和人才市场党组织。随着改革开放地不断深入，劳动者由计划经济的单位人逐渐向市场经济的社会人转变，就业方式更加灵活，劳动关系和人事关系发生了分离。党员组织关系出现与劳动关系一致与人事关系分离或者与人事关系一致与劳动关系分离等现象，如非公企业、民办非企业和部分国有企事业单位等。</span></p>"
				+ "<p><strong><span style=\"font-size: 16px\">2、由谁办理转移党员组织关系手续？</span></strong></p>"
				+ "<p><span style=\"font-size: 16px\">&nbsp;&nbsp;&nbsp; 转移党员组织关系要由<strong>党员本人亲自或由党组织派人</strong>办理手续，不得私自委托他人特别是党外人员代办。</span></p>"
				+ "<p><span style=\"font-size: 16px\">&nbsp;&nbsp;&nbsp; </span><strong><span style=\"font-size: 16px\">注：如因特殊原因，确实需要委托他人（党外人员除外）办理，可带党员本人亲自手写签字的委托书前来办理。</span></strong></p>"
				+ "<p><strong><span style=\"font-size: 16px\">3、党员组织关系介绍信抬头填什么？（抬头）</span></strong></p>"
				+ "<p><span style=\"font-size: 16px\">&nbsp;&nbsp;&nbsp; 介绍信第二联抬头要按照转接党员正式组织关系权限，填写应转入或应接入的党组织全称或标准简称。设有组织部门的开往党委组织部（处），未设组织部门的开往党委不能开往党办。可以使用&times;&times;省委、&times;&times;市委、&times;&times;县委的简称，但自治区要使用&times;&times;自治区党委，市辖区要使用&times;&times;市&times;&times;区委的简称。</span></p>"
				+ "<p><span style=\"font-size: 16px\">介绍信第三联抬头应填写党员原所在党支部上一级基层党委全称或标准简称。</span></p>"
				+ "<p><span style=\"font-size: 16px\">4、党员组织关系介绍信中由何处去何处栏怎么填？（转往基层党组织名称）</span></p>"
				+ "<p><span style=\"font-size: 16px\">&nbsp;&nbsp;&nbsp; 由何处去何处，应填写由何单位或居住地去何单位或居住地。单位名称应填写全称或标准简称，居住地应由省（自治区、直辖市）、地（地级市、自治州）、县（市辖区、县级市、自治县）、乡镇（街道）填写到村（社区）。</span></p>"
				+ "<p><strong><span style=\"font-size: 16px\">5、党组织关系介绍信的有效期？</span></strong></p>"
				+ "<p><span style=\"font-size: 16px\">&nbsp;&nbsp;&nbsp; 有效期天数，市内通转一般市内六区7天，其他区县10天；全国通转一般30天，<strong>如遇学校放假等特殊情况最多不超过90天</strong>。落款日期应为开具介绍信或开具回执的当日，不得提前或延后填写。</span></p>"
				+ "<p><strong><span style=\"font-size: 16px\">6、转接党组织关系中对党员的要求是：</span></strong></p>"
				+ "<p><span style=\"font-size: 16px\">　　（１）因工作、学习、生活等原因离开原所在党组织，<strong>要及时转移党员组织关系<strong>（毕业离校前办理手续）</strong>，</strong>在<strong>规定时间内（有效期内）</strong>到所去地方或单位党组织报到。</span></p>"
				+ "<p><span style=\"font-size: 16px\">　　（２）短期外出或外出时间较长但无固定地点的，应当通过适当方式主动与原所在党组织保持联系，汇报外出期间的有关情况，按照规定交纳党费。</span></p>"
				+ "<p><span style=\"font-size: 16px\">　　（３）</span><strong><span style=\"font-size: 16px\">如果没有正当理由，连续６个月不参加党的组织生活，或不交纳党费，或不做党所分配的工作，就被认为是自行脱党。支部大会应当决定把这样的党员除名，并报上级党组织批准。</span></strong></p>"
				+ "<p><strong><span style=\"font-size: 16px\">7、党组织关系介绍信与党组织材料（入党志愿书等）是否在一起？ </span></strong></p>"
				+ "<p><strong><span style=\"font-size: 16px\">&nbsp;&nbsp;&nbsp; 不一定。</span></strong><span style=\"font-size: 16px\">应学校统一要求，党组织材料已整理统一归档至个人人事档案，跟随档案派遣至相应单位。党组织关系介绍信应前往工作（深造）单位或本人（父母）户籍所在基层党组织。</span></p><p><span style=\"font-size: 16px\">&nbsp;&nbsp;&nbsp;&nbsp; 例如：在北京就业，不解决户口，档案二分回省到户籍地人才中心，党组织材料也一并存在户籍地人才中心，而党组织关系转往工作单位或本人（父母）户籍所在基层党组织。</span></p>";
		// if (chn.equals("wsdx")) {
		webView = (android.webkit.WebView) findViewById(R.id.webview);
		// StringBuilder sb = new StringBuilder();
		// sb.append(detail);
		Log.e("here", "here");
		// webView.loadUrl("http://ww.baidu.com");
		webView.getSettings().setJavaScriptEnabled(true);
		webView.setWebChromeClient(new WebChromeClient());
		webView.getSettings().setLayoutAlgorithm(LayoutAlgorithm.SINGLE_COLUMN);

		webView.loadDataWithBaseURL(URLcontainer.urlip, detail, "text/html", "utf-8", null);
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
		// } else {
		// GetData();
		// }

		// detail=getNewContent(detail);
	}

	private void ReadTicket() {
		// TODO Auto-generated method stub
		ticket = PreUserInfo.getString("ticket", "");

	}

	private void GetData() {
		// TODO Auto-generated method stub

		// TODO Auto-generated method stub
		final ArrayList ArrayValues = new ArrayList();
		// ArrayValues.add(new BasicNameValuePair("ticket", ticket));
		// ArrayValues.add(new BasicNameValuePair("applyType", "" + 2));
		// ArrayValues.add(new BasicNameValuePair("helpSType", "" + type));
		// ArrayValues.add(new BasicNameValuePair("modelSign", "KNDY_APPLY"));
		// ArrayValues.add(new BasicNameValuePair("curPage", "" + curPage));
		// ArrayValues.add(new BasicNameValuePair("pageSize", "" + pageSize));
		// final ArrayList ArrayValues = new ArrayList();
		ArrayValues.add(new BasicNameValuePair("ticket", "" + ticket));
		// chn = GetChannelByKey.GetSign(PreALLChannel, "职工之家");
		ArrayValues.add(new BasicNameValuePair("chn", chn));
		ArrayValues.add(new BasicNameValuePair("datakey", "" + Id));

		new Thread(new Runnable() { // 开启线程上传文件
			@Override
			public void run() {
				String DueData = "";
				DueData = HttpGetData.GetData("api/cms/channel/channleContentData", ArrayValues);
				Message msg = new Message();
				msg.obj = DueData;
				msg.what = GET_DUE_DATA;
				uiHandler.sendMessage(msg);
			}
		}).start();

	}

	private void show() {
		// TODO Auto-generated method stub
		webView = (android.webkit.WebView) findViewById(R.id.webview);
		// StringBuilder sb = new StringBuilder();
		// sb.append(detail);
		Log.e("here", "here1");
		// webView.loadUrl("http://ww.baidu.com");
		webView.getSettings().setJavaScriptEnabled(true);
		webView.setWebChromeClient(new WebChromeClient());
		webView.getSettings().setLayoutAlgorithm(LayoutAlgorithm.SINGLE_COLUMN);

		webView.loadDataWithBaseURL(URLcontainer.urlip, content, "text/html", "utf-8", null);

	}

	private String getNewContent(String htmltext) {

		Document doc = Jsoup.parse(htmltext);
		Elements elements = doc.getElementsByTag("img");
		for (Element element : elements) {
			element.attr("width", "100%").attr("height", "auto");
		}

		Log.d("VACK", doc.toString());
		return doc.toString();
	}

	protected void GetDataDueData(Object obj) {

		// TODO Auto-generated method stub
		// String Type = null;
		String Data = null;
		// String pager = null;
		try {
			JSONObject demoJson = new JSONObject(obj.toString());
			String Type = demoJson.getString("type");

			Data = demoJson.getString("data");
			if (Type.equals(GET_SUCCESS_RESULT)) {

				GetDataList(Data, curPage);
			} else if (Type.equals(GET_FAIL_RESULT)) {
				Toast.makeText(getApplicationContext(), "服务器数据失败", Toast.LENGTH_SHORT).show();
			} else {
				Toast.makeText(getApplicationContext(), "数据格式校验失败", Toast.LENGTH_SHORT).show();
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	// private void GetDataList(String data, int arg) {
	//
	// if (arg == 1) {
	// list.clear();
	// }
	// JSONArray jArray = null;
	// try {
	// jArray = new JSONArray(data);
	// JSONObject json_data = null;
	// if (jArray.length() == 0) {
	// Toast.makeText(getApplicationContext(), "无数据",
	// Toast.LENGTH_SHORT).show();
	//
	// } else {
	// for (int i = 0; i < jArray.length(); i++) {
	// json_data = jArray.getJSONObject(i);
	// Log.e("json_data", "" + json_data);
	// JSONObject jsonObject = json_data.getJSONObject("data");
	// CommentModel listinfo = new CommentModel();
	//
	// listinfo.setTime(jsonObject.getString("createTime"));
	// listinfo.setComment(jsonObject.getString("content"));
	// listinfo.setRoundUrl(jsonObject.getString("userPhoto"));
	// listinfo.setName(jsonObject.getString("user_name"));
	// list.add(listinfo);
	//
	// }
	// }
	//
	// } catch (JSONException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	//
	// if (arg == 1) {
	// go();
	// } else {
	// mAdapter.notifyDataSetChanged();
	// }
	//
	// }

	// private void GetPager(String pager) {
	// // TODO Auto-generated method stub
	// try {
	// JSONObject demoJson = new JSONObject(pager);
	//
	// totalPage = demoJson.getInt("totalPage");
	//
	// } catch (JSONException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// } catch (Exception e) {
	// // TODO: handle exception
	// }
	// }
	//
	// private void GetData() {
	// // TODO Auto-generated method stub
	//
	// // TODO Auto-generated method stub
	// final ArrayList ArrayValues = new ArrayList();
	// ArrayValues.add(new BasicNameValuePair("ticket", ticket));
	// ArrayValues.add(new BasicNameValuePair("chn", chn));
	// ArrayValues.add(new BasicNameValuePair("datakey", Id));
	// ArrayValues.add(new BasicNameValuePair("curPage", "" + curPage));
	// ArrayValues.add(new BasicNameValuePair("pageSize", "" + pageSize));
	// new Thread(new Runnable() { // 开启线程上传文件
	// @Override
	// public void run() {
	// String DueData = "";
	// DueData = HttpGetData.GetData("api/cms/common/getChannelCommentData",
	// ArrayValues);
	// Message msg = new Message();
	// msg.obj = DueData;
	// msg.what = GET_DUE_DATA;
	// uiHandler.sendMessage(msg);
	// }
	// }).start();
	//
	// }

	private void GetDataList(String data, int curPage2) {
		// TODO Auto-generated method stub
		try {
			JSONObject demoJson = new JSONObject(data);

			content = demoJson.getString("content");

			new Thread(new Runnable() { // 开启线程上传文件
				@Override
				public void run() {
					content = getNewContent(content);
					Message msg = new Message();

					msg.what = 0;
					uiHandler.sendMessage(msg);
				}
			}).start();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private void starttimedelay() {
		// 原因：不延时的话list会滑到顶部
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {

			@Override
			public void run() {

				Message msg1 = new Message();
				msg1.what = 3;
				uiHandler.sendMessage(msg1);

			}

		}, 2000);
	}

	// private void getdatalist(int arg) {
	// if (arg == 1) {
	// list.clear();
	// }
	// // TODO Auto-generated method stub
	//
	// try {
	//
	// for (int i = 0; i < 10; i++) {
	//
	// CommentModel listinfo = new CommentModel();
	// listinfo.setTime("2016-12-14 20:00:00");
	// listinfo.setComment("这真是一篇好文章，学习了");
	// listinfo.setRoundUrl("");
	// listinfo.setName("刘志刚");
	//
	// list.add(listinfo);
	//
	// }
	// } catch (Exception e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	//
	// if (arg == 1) {
	// go();
	// } else {
	// mAdapter.notifyDataSetChanged();
	// }
	// setlistheight(list.size());
	// if (arg == totalPage) {
	// TextWarning.setText("没有更多了");
	// } else {
	// TextWarning.setText("点击加载更多");
	// }
	// }

	private void setlistheight(int size) {
		// TODO Auto-generated method stub
		screenwidth = getWindow().getWindowManager().getDefaultDisplay().getWidth();
		DisplayMetrics mMetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(mMetrics);
		scale = getResources().getDisplayMetrics().density;
		dp = screenwidth / scale + 0.5f;
		scalepx = screenwidth / dp;
		int height = (int) (size * 91 * scalepx);
		RelativeLayout.LayoutParams layoutParams1 = (android.widget.RelativeLayout.LayoutParams) ListData
				.getLayoutParams();
		layoutParams1.height = height;
		ListData.setLayoutParams(layoutParams1);
		height = (int) ((screenwidth - 20 * scalepx) / 2);
		layoutParams1 = (android.widget.RelativeLayout.LayoutParams) ImagePic.getLayoutParams();
		layoutParams1.height = height;
		ImagePic.setLayoutParams(layoutParams1);

	}

	// protected void go() {
	// mAdapter = new CommentAdapter(this, list, ListData);
	// ListData.setAdapter(mAdapter);
	// }

	private void settext() {
		// TODO Auto-generated method stub
		// TextWarning.setText("正在加载数据...");
		TextDetail.setText(detail);
		TextTime.setText(Time);
		TextName.setText(Name);
		TextTitle.setText(Title);
	}

	private void initview() {
		// TODO Auto-generated method stub
		// EditAnswer = (EditText) findViewById(R.id.edit_answer);
		// BtnAnswer = (Button) findViewById(R.id.btn_answer);
		ImageBack = (ImageView) findViewById(R.id.image_back);
		ImagePic = (ImageView) findViewById(R.id.image_pic);
		// ScrollLayout = (ScrollView) findViewById(R.id.scrolllayout);
		ListData = (ListView) findViewById(R.id.list_data);
		// TextWarning = (TextView) findViewById(R.id.text_warning);
		TextDetail = (TextView) findViewById(R.id.text_detail);
		TextTime = (TextView) findViewById(R.id.text_time);
		TextName = (TextView) findViewById(R.id.text_name);
		TextTitle = (TextView) findViewById(R.id.text_title);
		text_detail = (TextView) findViewById(R.id.text_detail);
	}

	private void setonclicklistener() {
		// TODO Auto-generated method stub
		// BtnAnswer.setOnClickListener(this);
		ImageBack.setOnClickListener(this);
		ListData.setOnItemClickListener(this);
		// TextWarning.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.image_back:
			finish();
			break;
		// case R.id.btn_answer:
		// break;
		// case R.id.text_warning:
		// curPage++;
		// if (!(curPage > totalPage)) {
		// getdatalist(curPage);
		// Toast.makeText(getApplicationContext(), "正在加载",
		// Toast.LENGTH_SHORT).show();
		// }
		//
		// break;
		default:
			break;
		}
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		// TODO Auto-generated method stub
		// CommentModel data = list.get(position);
		// Intent intent = new Intent();
		// intent.setClass(getApplicationContext(),
		// CommentDetailActivity.class);
		// Bundle bundle = new Bundle();
		// bundle.putString("Name", data.getName());
		// bundle.putString("Time", data.getTime());
		// bundle.putString("Comment", data.getComment());
		// intent.putExtras(bundle);
		// startActivity(intent);
	}

}
