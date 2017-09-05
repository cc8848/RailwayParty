package wuxc.single.railwayparty.other;

import java.util.ArrayList;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.Notification.Action;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.Toast;
import cn.jpush.android.api.JPushInterface;
import android.view.Window;
import wuxc.single.railwayparty.R;
import wuxc.single.railwayparty.internet.HttpGetData;
import wuxc.single.railwayparty.internet.URLcontainer;
import wuxc.single.railwayparty.main.TipsDetailActivity;
import wuxc.single.railwayparty.start.ImagePPT;
import wuxc.single.railwayparty.start.MessageDetailActivity;
import wuxc.single.railwayparty.start.SpecialDetailActivity;
import wuxc.single.railwayparty.start.artDetail;
import wuxc.single.railwayparty.start.webview;
import wuxc.single.railwayparty.start.wsdxActivity;

public class PushLoadActivity extends Activity implements OnClickListener {
	private LinearLayout lin_all;
	private String chn = "";
	private String id = "";
	private String ticket = "";
	private SharedPreferences PreUserInfo;// 存储个人信息
	private static final String GET_SUCCESS_RESULT = "success";
	private static final String GET_FAIL_RESULT = "fail";
	private static final int GET_DUE_DATA = 6;
	private SharedPreferences ItemNumber;
	private String type = "";
	private String messageid = "";
	private Handler uiHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {

			case GET_DUE_DATA:
				GetDataDueData(msg.obj);
				break;
			case 12:
				getmessagedetail(msg.obj);
				break;
			default:
				break;

			}
		}

	};

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

				GetDataList(Data, 1);
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

	protected void getmessagedetail(Object obj) {

		// TODO Auto-generated method stub
		// String Type = null;
		String Data = null;
		// String pager = null;
		try {
			JSONObject demoJson = new JSONObject(obj.toString());
			String Type = demoJson.getString("type");

			Data = demoJson.getString("data");
			if (Type.equals(GET_SUCCESS_RESULT)) {
				demoJson = new JSONObject(Data);
				String id = demoJson.getString("keyid");
				String content = demoJson.getString("content");
				String time = demoJson.getString("sendDate");
				String title = demoJson.getString("title");
				Intent intent = new Intent();
				intent.setClass(getApplicationContext(), MessageDetailActivity.class);
				Bundle bundle = new Bundle();
				bundle.putString("Title", title);
				bundle.putString("Time", time);
				bundle.putString("detail", content);
				bundle.putString("chn", "wsdx");
				bundle.putString("Id", id);
				intent.putExtras(bundle);
				startActivity(intent);
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

	private void GetDataList(String data, int curPage2) {

		// TODO Auto-generated method stub

		try {
			JSONObject json_data = new JSONObject(data);
			if (chn.equals("wsdx")) {
				String title = json_data.getString("title");
				String time = json_data.getString("releaseDate");
				String Id = json_data.getString("keyid");
				int fileClassify = json_data.getInt("fileClassify");
				String cover = json_data.getString("sacleImage");
				String otherLink = json_data.getString("otherLinks");
				String content = json_data.getString("content");
				boolean cont = true;
				if (!(json_data.getString("otherLinks").equals("") || json_data.getString("otherLinks") == null
						|| json_data.getString("otherLinks").equals("null"))) {
					// listinfo.setContent(json_data.getString("source"));
					cont = false;
				} else {
					cont = true;
				}
				if (cont) {
					if (fileClassify == 1) {
						Intent intent = new Intent();
						intent.setClass(getApplicationContext(), ImagePPT.class);
						Bundle bundle = new Bundle();
						bundle.putString("Title", title);
						bundle.putString("Time", time);
						bundle.putString("detail", content);
						bundle.putString("chn", chn);
						bundle.putString("Id", Id);
						bundle.putString("cover", cover);
						bundle.putString("ticket", ticket);
						bundle.putBoolean("read", true);
						intent.putExtras(bundle);
						startActivity(intent);

					} else {
						Intent intent = new Intent();
						intent.setClass(getApplicationContext(), wsdxActivity.class);
						Bundle bundle = new Bundle();
						bundle.putString("Title", title);
						bundle.putString("Time", time);
						bundle.putString("detail", content);
						bundle.putString("chn", chn);
						bundle.putString("Id", Id);
						bundle.putString("cover", cover);
						bundle.putString("ticket", ticket);
						bundle.putBoolean("read", true);
						intent.putExtras(bundle);
						startActivity(intent);

					}

				} else {

					Intent intent = new Intent();
					intent.setClass(getApplicationContext(), webview.class);
					Bundle bundle = new Bundle();
					bundle.putString("url", otherLink);
					bundle.putString("Title", title);
					bundle.putString("Time", time);
					bundle.putString("detail", content);
					bundle.putString("chn", chn);
					bundle.putString("Id", Id);
					bundle.putString("cover", cover);
					bundle.putString("ticket", ticket);
					bundle.putBoolean("read", true);
					intent.putExtras(bundle);
					startActivity(intent);

				}
			} else if (chn.equals("dyq")) {
				String title = json_data.getString("title");
				String time = json_data.getString("createtime");
				String Id = json_data.getString("keyid");
				String content = json_data.getString("content");
				JSONArray jArray1 = null;
				int number = 0;
				String[] photo = { "", "", "", "", "", "", "", "", "", "" };
				String imagelist = json_data.getString("imageList");
				try {

					jArray1 = new JSONArray(json_data.getString("imageList"));
					JSONObject json_data1 = null;
					for (int j = 0; j < jArray1.length(); j++) {
						json_data1 = jArray1.getJSONObject(j);
						photo[j] = json_data1.getString("filePath");
						Log.e("photo", photo[j]);

						number++;
					}
				} catch (Exception e) {
					// TODO: handle exception
				}

				Intent intent = new Intent();
				intent.setClass(getApplicationContext(), TipsDetailActivity.class);
				Bundle bundle = new Bundle();
				bundle.putString("Title", title);
				bundle.putString("Time", time);
				bundle.putString("detail", content);
				bundle.putString("chn", "chn");
				bundle.putString("Id", Id);
				bundle.putInt("number", number);
				bundle.putStringArray("photo", photo);

				bundle.putString("modelSign", "dyq");
				bundle.putString("imagelist", imagelist);
				bundle.putString("url", "api/cms/common/getChannelCommentData");
				bundle.putString("curl", "api/cms/common/saveChannelComment2");
				intent.putExtras(bundle);
				startActivity(intent);
			} else if (chn.equals("dyyj")) {
				String title = json_data.getString("title");
				String time = json_data.getString("createtime");
				String Id = json_data.getString("keyid");
				String content = json_data.getString("content");
				JSONArray jArray1 = null;
				int number = 0;
				String[] photo = { "", "", "", "", "", "", "", "", "", "" };
				String imagelist = json_data.getString("imageList");
				try {

					jArray1 = new JSONArray(json_data.getString("imageList"));
					JSONObject json_data1 = null;
					for (int j = 0; j < jArray1.length(); j++) {
						json_data1 = jArray1.getJSONObject(j);
						photo[j] = json_data1.getString("filePath");
						Log.e("photo", photo[j]);

						number++;
					}
				} catch (Exception e) {
					// TODO: handle exception
				}

				Intent intent = new Intent();
				intent.setClass(getApplicationContext(), TipsDetailActivity.class);
				Bundle bundle = new Bundle();
				bundle.putString("Title", title);
				bundle.putString("Time", time);
				bundle.putString("detail", content);
				bundle.putString("chn", "chn");
				bundle.putString("Id", Id);
				bundle.putInt("number", number);
				bundle.putStringArray("photo", photo);

				bundle.putString("modelSign", "dyyj");
				bundle.putString("imagelist", imagelist);
				bundle.putString("url", "api/cms/common/getChannelCommentData");
				bundle.putString("curl", "api/cms/common/saveChannelComment2");
				intent.putExtras(bundle);
				startActivity(intent);
			} else if (chn.equals("xxxd")) {
				String title = json_data.getString("title");
				String time = json_data.getString("createtime");
				String Id = json_data.getString("keyid");
				String content = json_data.getString("content");
				JSONArray jArray1 = null;
				int number = 0;
				String[] photo = { "", "", "", "", "", "", "", "", "", "" };
				String imagelist = json_data.getString("imageList");
				try {

					jArray1 = new JSONArray(json_data.getString("imageList"));
					JSONObject json_data1 = null;
					for (int j = 0; j < jArray1.length(); j++) {
						json_data1 = jArray1.getJSONObject(j);
						photo[j] = json_data1.getString("filePath");
						Log.e("photo", photo[j]);

						number++;
					}
				} catch (Exception e) {
					// TODO: handle exception
				}

				Intent intent = new Intent();
				intent.setClass(getApplicationContext(), TipsDetailActivity.class);
				Bundle bundle = new Bundle();
				bundle.putString("Title", title);
				bundle.putString("Time", time);
				bundle.putString("detail", content);
				bundle.putString("chn", "modelSign");
				bundle.putString("Id", Id);
				bundle.putInt("number", number);
				bundle.putStringArray("photo", photo);

				bundle.putString("modelSign", "xinde");
				bundle.putString("imagelist", imagelist);
				bundle.putString("url", "api/cms/common/getModelCommentData");
				bundle.putString("curl", "api/cms/common/saveModelComment2");
				intent.putExtras(bundle);
				startActivity(intent);
			} else if (chn.equals("qwx")) {
				String title = json_data.getString("title");
				String time = json_data.getString("releaseDate");
				String Id = json_data.getString("keyid");

				String content = json_data.getString("content");
				Intent intent = new Intent();
				intent.setClass(getApplicationContext(), artDetail.class);
				Bundle bundle = new Bundle();
				bundle.putString("Title", title);
				bundle.putString("Time", time);
				bundle.putString("detail", content);
				bundle.putString("chn", chn);
				bundle.putString("Id", Id);
				bundle.putBoolean("read", false);
				intent.putExtras(bundle);
				startActivity(intent);
			} else {
				String title = json_data.getString("title");
				String time = json_data.getString("releaseDate");
				String Id = json_data.getString("keyid");

				String content = json_data.getString("content");
				Intent intent = new Intent();
				intent.setClass(getApplicationContext(), SpecialDetailActivity.class);
				Bundle bundle = new Bundle();
				bundle.putString("Title", title);
				bundle.putString("Time", time);
				bundle.putString("detail", content);
				bundle.putString("chn", chn);
				bundle.putString("Id", Id);
				bundle.putBoolean("read", false);
				intent.putExtras(bundle);
				startActivity(intent);
			}
			finish();
			saveRead(chn);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			finish();
			Toast.makeText(getApplicationContext(), "推送数据解析错误", Toast.LENGTH_SHORT).show();

		}

	}

	private void saveRead(String channel) {
		// TODO Auto-generated method stub
		if (channel.equals("jzxx")) {
			SharedPreferences PreForJZXX;
			PreForJZXX = getSharedPreferences("JZXX", Context.MODE_PRIVATE);
			Editor edit = PreForJZXX.edit();
			edit.putBoolean(id, true);
			edit.commit();
			Editor edit1 = ItemNumber.edit();
			edit1.putInt("JZXXread", (PreForJZXX.getAll().size() - 1));
			edit1.commit();
		} else if (channel.equals("zdwj")) {
			SharedPreferences PreForZDWJ;
			PreForZDWJ = getSharedPreferences("ZDWJ", Context.MODE_PRIVATE);
			Editor edit = PreForZDWJ.edit();
			edit.putBoolean(id, true);
			edit.commit();
			Editor edit1 = ItemNumber.edit();
			edit1.putInt("ZDWJread", (PreForZDWJ.getAll().size() - 1));
			edit1.commit();
		} else if (channel.equals("djyw")) {
			SharedPreferences PreForDJYW;
			PreForDJYW = getSharedPreferences("DJYW", Context.MODE_PRIVATE);
			Editor edit = PreForDJYW.edit();
			edit.putBoolean(id, true);
			edit.commit();
			Editor edit1 = ItemNumber.edit();
			edit1.putInt("DJYWread", (PreForDJYW.getAll().size() - 1));
			edit1.commit();
		} else if (channel.equals("tzggd")) {
			SharedPreferences PreForTZGG;
			PreForTZGG = getSharedPreferences("TZGG", Context.MODE_PRIVATE);
			Editor edit = PreForTZGG.edit();
			edit.putBoolean(id, true);
			edit.commit();
			Editor edit1 = ItemNumber.edit();
			edit1.putInt("TZGGread", (PreForTZGG.getAll().size() - 1));
			edit1.commit();
		} else if (channel.equals("szrd")) {
			SharedPreferences PreForSZRD;
			PreForSZRD = getSharedPreferences("SZRD", Context.MODE_PRIVATE);
			Editor edit = PreForSZRD.edit();
			edit.putBoolean(id, true);
			edit.commit();
			Editor edit1 = ItemNumber.edit();
			edit1.putInt("SZRDread", (PreForSZRD.getAll().size() - 1));
			edit1.commit();
		} else if (channel.equals("dyfc")) {
			SharedPreferences PreForDYFC;
			PreForDYFC = getSharedPreferences("DYFC", Context.MODE_PRIVATE);
			Editor edit = PreForDYFC.edit();
			edit.putBoolean(id, true);
			edit.commit();
			Editor edit1 = ItemNumber.edit();
			edit1.putInt("DYFCread", (PreForDYFC.getAll().size() - 1));
			edit1.commit();
		} else if (channel.equals("wsdx")) {
			SharedPreferences PreForWSDX;
			PreForWSDX = getSharedPreferences("WSDX", Context.MODE_PRIVATE);
			Editor edit = PreForWSDX.edit();
			edit.putBoolean(id, true);
			edit.commit();
			Editor edit1 = ItemNumber.edit();
			edit1.putInt("WSDXread", (PreForWSDX.getAll().size() - 1));
			edit1.commit();
		} else if (channel.equals("dqlh")) {
			SharedPreferences PreForDQLH;
			PreForDQLH = getSharedPreferences("DQLH", Context.MODE_PRIVATE);
			Editor edit = PreForDQLH.edit();
			edit.putBoolean(id, true);
			edit.commit();
			Editor edit1 = ItemNumber.edit();
			edit1.putInt("DQLHread", (PreForDQLH.getAll().size() - 1));
			edit1.commit();
		} else if (channel.equals("dsyyj")) {
			SharedPreferences PreForDSYYJ;
			PreForDSYYJ = getSharedPreferences("DSYYJ", Context.MODE_PRIVATE);
			Editor edit = PreForDSYYJ.edit();
			edit.putBoolean(id, true);
			edit.commit();
			Editor edit1 = ItemNumber.edit();
			edit1.putInt("DSYYJread", (PreForDSYYJ.getAll().size() - 1));
			edit1.commit();
		} else if (channel.equals("lzsx")) {
			SharedPreferences PreForLZSX;
			PreForLZSX = getSharedPreferences("LZSX", Context.MODE_PRIVATE);
			Editor edit = PreForLZSX.edit();
			edit.putBoolean(id, true);
			edit.commit();
			Editor edit1 = ItemNumber.edit();
			edit1.putInt("LZSXread", (PreForLZSX.getAll().size() - 1));
			edit1.commit();
		} else if (channel.equals("yasjlb")) {
			SharedPreferences PreForYASJLB;
			PreForYASJLB = getSharedPreferences("YASJLB", Context.MODE_PRIVATE);
			Editor edit = PreForYASJLB.edit();
			edit.putBoolean(id, true);
			edit.commit();
			Editor edit1 = ItemNumber.edit();
			edit1.putInt("YASJLBread", (PreForYASJLB.getAll().size() - 1));
			edit1.commit();
		} else if (channel.equals("qlkm")) {
			SharedPreferences PreForQLKM;
			PreForQLKM = getSharedPreferences("QLKM", Context.MODE_PRIVATE);
			Editor edit = PreForQLKM.edit();
			edit.putBoolean(id, true);
			edit.commit();
			Editor edit1 = ItemNumber.edit();
			edit1.putInt("QLKMread", (PreForQLKM.getAll().size() - 1));
			edit1.commit();
		} else if (channel.equals("xxjl")) {
			SharedPreferences PreForXXJL;
			PreForXXJL = getSharedPreferences("XXJL", Context.MODE_PRIVATE);
			Editor edit = PreForXXJL.edit();
			edit.putBoolean(id, true);
			edit.commit();
			Editor edit1 = ItemNumber.edit();
			edit1.putInt("XXJLread", (PreForXXJL.getAll().size() - 1));
			edit1.commit();
		} else if (channel.equals("qtxs")) {
			SharedPreferences PreForQTXS;
			PreForQTXS = getSharedPreferences("QTXS", Context.MODE_PRIVATE);
			Editor edit = PreForQTXS.edit();
			edit.putBoolean(id, true);
			edit.commit();
			Editor edit1 = ItemNumber.edit();
			edit1.putInt("QTXSread", (PreForQTXS.getAll().size() - 1));
			edit1.commit();
		} else if (channel.equals("qwx")) {
			SharedPreferences PreForQWX;
			PreForQWX = getSharedPreferences("QWX", Context.MODE_PRIVATE);
			Editor edit = PreForQWX.edit();
			edit.putBoolean(id, true);
			edit.commit();
			Editor edit1 = ItemNumber.edit();
			edit1.putInt("QWXread", (PreForQWX.getAll().size() - 1));
			edit1.commit();
		} else if (channel.equals("qnxf")) {
			SharedPreferences PreForQNXF;
			PreForQNXF = getSharedPreferences("QNXF", Context.MODE_PRIVATE);
			Editor edit = PreForQNXF.edit();
			edit.putBoolean(id, true);
			edit.commit();
			Editor edit1 = ItemNumber.edit();
			edit1.putInt("QNXFread", (PreForQNXF.getAll().size() - 1));
			edit1.commit();
		} else if (channel.equals("xxtb")) {
			SharedPreferences PreForXXTB;
			PreForXXTB = getSharedPreferences("XXTB", Context.MODE_PRIVATE);
			Editor edit = PreForXXTB.edit();
			edit.putBoolean(id, true);
			edit.commit();
			Editor edit1 = ItemNumber.edit();
			edit1.putInt("XXTBread", (PreForXXTB.getAll().size() - 1));
			edit1.commit();
		} else if (channel.equals("djfg")) {
			SharedPreferences PreForDJFG;
			PreForDJFG = getSharedPreferences("DJFG", Context.MODE_PRIVATE);
			Editor edit = PreForDJFG.edit();
			edit.putBoolean(id, true);
			edit.commit();
			Editor edit1 = ItemNumber.edit();
			edit1.putInt("DJFGread", (PreForDJFG.getAll().size() - 1));
			edit1.commit();
		} else if (channel.equals("jszn")) {
			SharedPreferences PreForJSZN;
			PreForJSZN = getSharedPreferences("JSZN", Context.MODE_PRIVATE);
			Editor edit = PreForJSZN.edit();
			edit.putBoolean(id, true);
			edit.commit();
			Editor edit1 = ItemNumber.edit();
			edit1.putInt("JSZNread", (PreForJSZN.getAll().size() - 1));
			edit1.commit();
		} else if (channel.equals("whzn")) {
			SharedPreferences PreForWHZN;
			PreForWHZN = getSharedPreferences("WHZN", Context.MODE_PRIVATE);
			Editor edit = PreForWHZN.edit();
			edit.putBoolean(id, true);
			edit.commit();
			Editor edit1 = ItemNumber.edit();
			edit1.putInt("WHZNread", (PreForWHZN.getAll().size() - 1));
			edit1.commit();
		} else if (channel.equals("cyzn")) {
			SharedPreferences PreForCYZN;
			PreForCYZN = getSharedPreferences("CYZN", Context.MODE_PRIVATE);
			Editor edit = PreForCYZN.edit();
			edit.putBoolean(id, true);
			edit.commit();
			Editor edit1 = ItemNumber.edit();
			edit1.putInt("CYZNread", (PreForCYZN.getAll().size() - 1));
			edit1.commit();
		} else if (channel.equals("jyzn")) {
			SharedPreferences PreForJYZN;
			PreForJYZN = getSharedPreferences("JYZN", Context.MODE_PRIVATE);
			Editor edit = PreForJYZN.edit();
			edit.putBoolean(id, true);
			edit.commit();
			Editor edit1 = ItemNumber.edit();
			edit1.putInt("JYZNread", (PreForJYZN.getAll().size() - 1));
			edit1.commit();
		} else if (channel.equals("djkh")) {
			SharedPreferences PreForDJKH;
			PreForDJKH = getSharedPreferences("DJKH", Context.MODE_PRIVATE);
			Editor edit = PreForDJKH.edit();
			edit.putBoolean(id, true);
			edit.commit();
			Editor edit1 = ItemNumber.edit();
			edit1.putInt("DJKHread", (PreForDJKH.getAll().size() - 1));
			edit1.commit();
		} else if (channel.equals("dwgk")) {
			SharedPreferences PreForDWGK;
			PreForDWGK = getSharedPreferences("DWGK", Context.MODE_PRIVATE);
			Editor edit = PreForDWGK.edit();
			edit.putBoolean(id, true);
			edit.commit();
			Editor edit1 = ItemNumber.edit();
			edit1.putInt("DWGKread", (PreForDWGK.getAll().size() - 1));
			edit1.commit();
		} else if (channel.equals("dnjc")) {
			SharedPreferences PreForDNJC;
			PreForDNJC = getSharedPreferences("DNJC", Context.MODE_PRIVATE);
			Editor edit = PreForDNJC.edit();
			edit.putBoolean(id, true);
			edit.commit();
			Editor edit1 = ItemNumber.edit();
			edit1.putInt("DNJCread", (PreForDNJC.getAll().size() - 1));
			edit1.commit();
		} else if (channel.equals("lxyzxx")) {
			SharedPreferences PreForLXYZXX;
			PreForLXYZXX = getSharedPreferences("LXYZXX", Context.MODE_PRIVATE);
			Editor edit = PreForLXYZXX.edit();
			edit.putBoolean(id, true);
			edit.commit();
			Editor edit1 = ItemNumber.edit();
			edit1.putInt("LXYZXXread", (PreForLXYZXX.getAll().size() - 1));
			edit1.commit();
		}

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.wuxc_activity_push);
		lin_all = (LinearLayout) findViewById(R.id.lin_all);
		lin_all.setOnClickListener(this);
		PreUserInfo = getSharedPreferences("UserInfo", Context.MODE_PRIVATE);
		ItemNumber = getSharedPreferences("ItemNumber", Context.MODE_PRIVATE);
		ReadTicket();
		Intent intent = getIntent();
		if (null != intent) {
			Bundle bundle = getIntent().getExtras();
			String extra = bundle.getString(JPushInterface.EXTRA_EXTRA);
			try {
				JSONObject jsonObject = new JSONObject(extra);
				chn = jsonObject.getString("channelSign");
				id = jsonObject.getString("keyid");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				chn = "";
				id = "";
			}
			try {
				JSONObject jsonObject = new JSONObject(extra);
				type = jsonObject.getString("type");
				messageid = jsonObject.getString("keyid");
			} catch (Exception e) {
				// TODO: handle exception
				type = "";
				messageid = "";
			}

		}
		if (ticket.equals("") || ticket == null) {
			Toast.makeText(getApplicationContext(), "你尚未登录", Toast.LENGTH_SHORT).show();
			finish();
		} else if (chn.equals("") || id.equals("")) {
			if (type.equals("message")) {
				GetMessage();
			} else {
				Toast.makeText(getApplicationContext(), "推送参数错误", Toast.LENGTH_SHORT).show();
				finish();
			}

		} else {
			GetData();
		}
	}

	private void GetMessage() {

		final ArrayList ArrayValues = new ArrayList();

		ArrayValues.add(new BasicNameValuePair("ticket", "" + ticket));
		ArrayValues.add(new BasicNameValuePair("datakey", "" + messageid));

		new Thread(new Runnable() { // 开启线程上传文件
			@Override
			public void run() {
				String DueData = "";
				DueData = HttpGetData.GetData("api/console/systemMessage/getJsonData", ArrayValues);
				Message msg = new Message();
				msg.obj = DueData;
				msg.what = 12;
				uiHandler.sendMessage(msg);
			}
		}).start();

	}

	private void GetData() {

		final ArrayList ArrayValues = new ArrayList();

		ArrayValues.add(new BasicNameValuePair("ticket", "" + ticket));
		ArrayValues.add(new BasicNameValuePair("chn", chn));
		ArrayValues.add(new BasicNameValuePair("datakey", "" + id));

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

	private void ReadTicket() {
		// TODO Auto-generated method stub
		ticket = PreUserInfo.getString("ticket", "");

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.lin_all:
			finish();
			break;

		default:
			break;
		}
	}
}
