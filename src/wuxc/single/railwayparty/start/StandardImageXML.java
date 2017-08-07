package wuxc.single.railwayparty.start;

import com.polites.android.GestureImageView;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Window;
import wuxc.single.railwayparty.R;
import wuxc.single.railwayparty.internet.GetBitmapFromServer;
import wuxc.single.railwayparty.internet.URLcontainer;

public class StandardImageXML extends Activity {
	private int inturl;
	private String url = "";
	private String userPhoto;
	private Bitmap HeadImage;
	private GestureImageView image;
	private final static int GET_USER_HEAD_IMAGE = 6;
	private Handler uiHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {

			case GET_USER_HEAD_IMAGE:
				ShowHeadImage(msg.obj);
				break;

			default:
				break;
			}
		}
	};

	protected void ShowHeadImage(Object obj) {
		// TODO Auto-generated method stub
		if (!(obj == null)) {
			try {
				HeadImage = (Bitmap) obj;
				image.setImageBitmap(HeadImage);
				// if (HeadImage != null && !HeadImage.isRecycled()) {
				// Log.e("HeadImage", "recycle");
				// HeadImage.recycle();
				// System.gc();
				// }
			} catch (Exception e) {
				// TODO: handle exception

			} catch (OutOfMemoryError e) {
				// TODO: handle exception
			}
		}
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
		image = (GestureImageView) findViewById(R.id.image);
		if (url.equals("")) {
			image.setImageResource(inturl);
		} else {
			userPhoto = URLcontainer.urlip + "upload" + url;
			GetHeadPic();
		}
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

	private void GetHeadPic() {
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
				uiHandler.sendMessage(msg);
			}
		}).start();
	}
}