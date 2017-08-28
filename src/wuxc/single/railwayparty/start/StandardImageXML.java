package wuxc.single.railwayparty.start;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import org.json.JSONArray;
import org.json.JSONObject;

import com.polites.android.GestureImageView;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import wuxc.single.railwayparty.R;
import wuxc.single.railwayparty.internet.GetBitmapFromServer;
import wuxc.single.railwayparty.internet.URLcontainer;
import wuxc.single.railwayparty.model.imagePPTModel;

public class StandardImageXML extends Activity implements OnClickListener {
	private int inturl;
	private String url = "";
	private String userPhoto;
	private Bitmap HeadImage;
	private GestureImageView image;
	private final static int GET_USER_HEAD_IMAGE = 6;
	private String userName = "";
	private String[] path = new String[200];
	private int totalnumber = 0;
	private int number = 999;
	private ImageView image_last;
	private ImageView image_next;
	private TextView text_number;
	private int pic_index = 999;
	private int time_position = 1;
	private Handler uiHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {

			case GET_USER_HEAD_IMAGE:
				ShowHeadImage(msg.obj, msg.arg1);
				break;

			default:
				break;
			}
		}
	};

	protected void ShowHeadImage(Object obj, int index) {
		// TODO Auto-generated method stub
		if (index != pic_index) {

		} else if (!(obj == null)) {
			try {
				HeadImage = (Bitmap) obj;
				saveMyBitmap(userName, HeadImage);
				image.setImageBitmap(HeadImage);

			} catch (Exception e) {
				// TODO: handle exception

			} catch (OutOfMemoryError e) {
				// TODO: handle exception
			}
		}
	}

	public void saveMyBitmap(String bitName, Bitmap mBitmap) throws IOException {

	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.image);
		Intent intent = this.getIntent(); // 获取已有的intent对象
		Bundle bundle = intent.getExtras(); // 获取intent里面的bundle对象
		url = bundle.getString("url");
		inturl = bundle.getInt("inturl");
		try {
			number = bundle.getInt("number");
			String patharray = bundle.getString("path");
			JSONArray jArray = null;

			jArray = new JSONArray(patharray);
			totalnumber = 0;
			for (int i = 0; i < jArray.length(); i++) {
				totalnumber++;
				try {
					JSONObject demoJson1 = jArray.getJSONObject(i);
					String temp = demoJson1.getString("path");
					path[i] = temp;
				} catch (Exception e) {
					// TODO: handle exception

				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			number = 999;
		}
		image_last = (ImageView) findViewById(R.id.image_last);
		image_next = (ImageView) findViewById(R.id.image_next);
		text_number = (TextView) findViewById(R.id.text_number);
		if (number == 999) {
			image_last.setVisibility(View.GONE);
			image_next.setVisibility(View.GONE);
			text_number.setVisibility(View.GONE);
		} else {
			image_last.setVisibility(View.VISIBLE);
			image_next.setVisibility(View.VISIBLE);
			text_number.setVisibility(View.VISIBLE);
		}
		image_last.setOnClickListener(this);
		image_next.setOnClickListener(this);
		text_number.setText(number + "/" + totalnumber);
		image = (GestureImageView) findViewById(R.id.image);
		ShowPic(999);

	}

	private void ShowPic(int index) {
		// TODO Auto-generated method stub
		image.setImageResource(R.drawable.logo);
		if (url.equals("")) {
			// image.setImageResource(R.drawable.logo);
		} else {
			userPhoto = URLcontainer.urlip + "upload" + url;
			userName = getBitName(userPhoto);
			String temppath = Environment.getExternalStorageDirectory() + "/chat/" + userName + ".png";
			Bitmap bm1 = null;
			bm1 = getBitmapByPath(temppath);
			if (bm1 == null) {
				Log.e("加载图片", "加载图片" + userName);
				GetHeadPic(index);
			} else {
				Log.e("引用图片", "引用图片" + userName);
				image.setImageBitmap(bm1);
			}
		}
	}

	public Bitmap getBitmapByPath(String fileName) {
		// String myJpgPath =
		// Environment.getExternalStorageDirectory()+"pepper/" + fileName;
		BitmapFactory.Options options = new BitmapFactory.Options();
		// options.inSampleSize = 12;
		Bitmap bm = BitmapFactory.decodeFile(fileName, options);
		return bm;
	}

	private String getBitName(String imageUrl) {
		// TODO Auto-generated method stub
		String[] temp = imageUrl.split("");
		String result = "";
		for (int i = 0; i < temp.length; i++) {
			if (temp[i].equals("/") || temp[i].equals(".")) {
				temp[i] = "";
			}
			result = result + temp[i];
		}
		return result + "600";
	}

	@Override
	public void onStop() {
		super.onStop();
		if (HeadImage != null && !HeadImage.isRecycled()) {
			Log.e("HeadImage", "recycle");
			HeadImage.recycle();
			System.gc();
		}
	}

	private void GetHeadPic(final int index) {
		// TODO Auto-generated method stub
		new Thread(new Runnable() { // 开启线程上传文件
			@Override
			public void run() {
				Bitmap HeadImage = null;
				if (userPhoto.indexOf("http") >= 0) {
					HeadImage = GetBitmapFromServer.getBitmapFromServer(userPhoto);
				} else {
					HeadImage = GetBitmapFromServer.getBitmapFromServer(URLcontainer.urlipno + userPhoto);
				}
				Message msg = new Message();
				msg.what = GET_USER_HEAD_IMAGE;
				msg.obj = HeadImage;
				msg.arg1 = index;

				uiHandler.sendMessage(msg);
			}
		}).start();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.image_last:
			if (time_position != 1) {
				Toast.makeText(getApplicationContext(), "您点击过快了", Toast.LENGTH_SHORT).show();

			} else if (number != 1) {
				time_position = 0;
				starttimedelay();
				number--;
				url = path[number - 1];
				text_number.setText(number + "/" + totalnumber);
				int temp = (int) (Math.random() * (1000));
				pic_index = temp;
				ShowPic(temp);
			} else {
				Toast.makeText(getApplicationContext(), "已经是第一张了", Toast.LENGTH_SHORT).show();
			}

			break;
		case R.id.image_next:
			if (time_position != 1) {
				Toast.makeText(getApplicationContext(), "您点击过快了", Toast.LENGTH_SHORT).show();

			} else if (number != totalnumber) {
				time_position = 0;
				starttimedelay();
				number++;
				url = path[number - 1];
				text_number.setText(number + "/" + totalnumber);
				int temp = (int) (Math.random() * (1000));
				pic_index = temp;
				ShowPic(temp);
			} else {
				Toast.makeText(getApplicationContext(), "已经是最后一张了", Toast.LENGTH_SHORT).show();
			}

			break;
		default:
			break;
		}

	}

	private void starttimedelay() {
		// 原因：不延时的话list会滑到顶部
		final Timer timer = new Timer();
		timer.schedule(new TimerTask() {

			@Override
			public void run() {

				try {
					time_position = 1;
					timer.cancel();
				} catch (Exception e) {
					// TODO: handle exceptioni

				}

			}

		}, 150);
	}
}