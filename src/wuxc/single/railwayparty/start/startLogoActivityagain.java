package wuxc.single.railwayparty.start;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;
import wuxc.single.railwayparty.MainActivity;
import wuxc.single.railwayparty.R;
import wuxc.single.railwayparty.internet.HttpGetData;

public class startLogoActivityagain extends Activity {
	private ImageView image_logo;
	private ImageView image_top;
	private LinearLayout lin_top;
	private int screenwidth = 0;
	private SharedPreferences PreGuidePage;// 用于确定是否是第一次登录

	private int GuidePage = 0;// 引导页显示标志字

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.wuxc_activity_startlogo);
		image_logo = (ImageView) findViewById(R.id.image_logo);
		image_top = (ImageView) findViewById(R.id.image_top);
		lin_top = (LinearLayout) findViewById(R.id.lin_top);
		screenwidth = getWindow().getWindowManager().getDefaultDisplay().getWidth();
		int width = (int) (screenwidth * 0.9);
		RelativeLayout.LayoutParams LayoutParams = (android.widget.RelativeLayout.LayoutParams) lin_top
				.getLayoutParams();
		LayoutParams.width = width;
		lin_top.setLayoutParams(LayoutParams);

		LinearLayout.LayoutParams LayoutParams1 = (android.widget.LinearLayout.LayoutParams) image_top
				.getLayoutParams();
		LayoutParams1.width = (int) (screenwidth * 0.7);
		LayoutParams1.height = (int) (width / 6.4);
		image_top.setLayoutParams(LayoutParams1);

		RelativeLayout.LayoutParams LayoutParams2 = (android.widget.RelativeLayout.LayoutParams) image_logo
				.getLayoutParams();
		LayoutParams2.height = (int) (screenwidth * 0.4);
		LayoutParams2.width = (int) (screenwidth * 0.4);
		image_logo.setLayoutParams(LayoutParams2);
		PreGuidePage = getSharedPreferences("GuidePage", Context.MODE_PRIVATE);
		GuidePage = PreGuidePage.getInt("GuidePage", 0);
		//
		if (GuidePage == 0) {
			Intent intent = new Intent();
			intent.setClass(this, guidePageActivity.class);
			startActivity(intent);
			finish();
		} else {
			Intent intent = new Intent();
			intent.setClass(this, startLogoActivity.class);
			startActivity(intent);
			finish();
		}

	}
}
