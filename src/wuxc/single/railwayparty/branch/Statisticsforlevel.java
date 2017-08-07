package wuxc.single.railwayparty.branch;

import java.util.ArrayList;
import java.util.Calendar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.Toast;
import android.view.Window;
import wuxc.single.railwayparty.R;
import wuxc.single.railwayparty.layout.PartViewforage;
import wuxc.single.railwayparty.layout.PartViewforlevel;

public class Statisticsforlevel extends Activity implements OnClickListener {

	private int[] data = new int[] { 0, 0, 0, 0, 0, 0, 0, 400, 500, 200, 100, 800, 600, 400, 500, 200, 100, 800, 600,
			400, 500, 200, 100, 800 };
	private PartViewforlevel histogramView;
	public static int total = 0;
	private String[] ySteps = new String[] { "", "", "", "", "" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.wuxc_activity_statistics_for_level);
		ImageView image_back = (ImageView) findViewById(R.id.image_back);
		image_back.setOnClickListener(this);
		String Data = null;
		// String pager = null;
		try {
			Intent intent = this.getIntent(); // 获取已有的intent对象
			Bundle bundle = intent.getExtras(); // 获取intent里面的bundle对象
			String data1 = bundle.getString("data");
			JSONObject demoJson = new JSONObject(data1);
			String Type = demoJson.getString("type");
			total = 0;
			Data = demoJson.getString("datas");
			if (Type.equals("success")) {
				JSONArray jArray = null;

				jArray = new JSONArray(Data);
				JSONObject json_data = null;
				if (jArray.length() == 0) {
					Toast.makeText(getApplicationContext(), "无数据", Toast.LENGTH_SHORT).show();
					finish();
				} else {
					int temp = 0;
					if (jArray.length() < 6) {
						temp = jArray.length();
					} else {
						temp = 6;
					}
					for (int i = 0; i < temp; i++) {
						json_data = jArray.getJSONObject(i);

						if (json_data.getString("title").equals("初中")) {
							data[0] = json_data.getInt("num");
						} else if (json_data.getString("title").equals("高中")) {
							data[1] = json_data.getInt("num");
						} else if (json_data.getString("title").equals("中专")) {
							data[2] = json_data.getInt("num");
						} else if (json_data.getString("title").equals("大专")) {
							data[3] = json_data.getInt("num");
						} else if (json_data.getString("title").equals("本科")) {
							data[4] = json_data.getInt("num");
						} else {
							data[5] = json_data.getInt("num");
						}

					}
					for (int i = 0; i < temp; i++) {
						if (data[i] > total) {
							total = data[i];
						}
					}
					total = total + 40;
					ySteps[0] = "" + total / 40 * 40;
					ySteps[1] = "" + total / 40 * 30;
					ySteps[2] = "" + total / 40 * 20;
					ySteps[3] = "" + total / 40 * 10;
					ySteps[4] = "" + total / 40 * 0;

					histogramView = (PartViewforlevel) this.findViewById(R.id.histogram);

					histogramView.setProgress(data, ySteps);
				}

			} else {
				Toast.makeText(getApplicationContext(), "数据错误", Toast.LENGTH_SHORT).show();
			 
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.image_back:
			finish();
			break;

		default:
			break;
		}
	}

}
