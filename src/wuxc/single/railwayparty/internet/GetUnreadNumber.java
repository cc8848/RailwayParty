package wuxc.single.railwayparty.internet;

import java.util.ArrayList;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Handler;
import android.os.Message;

public class GetUnreadNumber {
	private SharedPreferences PreForZDWJ;
	private static SharedPreferences ItemNumber;
	private static SharedPreferences PreUserInfo;// 存储个人信息
	private static String ticket = "";
	private static Handler uiHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {

			case 12:
				GetDataDetailFromVersion(msg.obj);
				break;
			default:
				break;
			}
		}
	};

	protected static void GetDataDetailFromVersion(Object obj) {
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

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public static void getunreadnumber(Activity activity) {
		PreUserInfo = activity.getSharedPreferences("UserInfo", Context.MODE_PRIVATE);
		ItemNumber = activity.getSharedPreferences("ItemNumber", Context.MODE_PRIVATE);
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
				msg.what = 12;
				uiHandler.sendMessage(msg);
			}
		}).start();
	}

}
