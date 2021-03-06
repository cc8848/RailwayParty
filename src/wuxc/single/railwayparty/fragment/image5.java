package wuxc.single.railwayparty.fragment;

import com.polites.android.GestureImageView;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import wuxc.single.railwayparty.R;
import wuxc.single.railwayparty.internet.GetBitmapFromServer;
import wuxc.single.railwayparty.internet.URLcontainer;
import wuxc.single.railwayparty.start.imageshow;

public class image5 extends Fragment implements OnClickListener {
	private GestureImageView image;
	private SharedPreferences PreUserInfo;// 存储个人信息
	private final static int GET_USER_HEAD_IMAGE = 6;
	private boolean UploadImage = false;
	private int credit = 0;
	private TextView text_credit;
	private int warning = 0;
	private String userPhoto = "";
	private View view = null;
	private Bitmap HeadImage;
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
	public void onAttach(Activity activity) {
		super.onAttach(activity);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.image, container, false);
		image = (GestureImageView) view.findViewById(R.id.image);

		return view;
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
				}	Message msg = new Message();
				msg.what = GET_USER_HEAD_IMAGE;
				msg.obj = HeadImage;
				uiHandler.sendMessage(msg);
			}
		}).start();
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

	}

	@Override
	public void onStart() {
		super.onStart();
		Log.e("HeadImage", "onPause");
	}

	@Override
	public void onResume() {
		super.onResume();
		if (view != null) {
			try {
				PreUserInfo = getActivity().getSharedPreferences("UserInfo", Context.MODE_PRIVATE);
				userPhoto = PreUserInfo.getString("image" +  (imageshow.bigpage*10+4), "");
				if (userPhoto.equals("")) {
					image.setImageResource(R.drawable.logo);
				} else {
					if (userPhoto.indexOf("http") < 0 && userPhoto.indexOf("upload")< 0) {

//					} else {
						userPhoto = "/upload" + userPhoto;
					}
					String Photo = URLcontainer.urlipno + userPhoto;
					String userName = getBitName(Photo);
					String temppath = Environment.getExternalStorageDirectory() + "/chat/" + userName + ".png";
					Bitmap bm1 = null;
					bm1 = getBitmapByPath(temppath);
					if (bm1 == null) {
						Log.e("加载图片", "加载图片" + userName);
						GetHeadPic();
					} else {
						Log.e("引用图片", "引用图片" + userName);
						image.setImageBitmap(bm1);
					}
				}
			} catch (Exception e) {
				// TODO: handle exception
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
	public void onPause() {
		super.onPause();
		Log.e("HeadImage", "onPause");
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

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		Log.e("HeadImage", "onDestroyView");
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}

	@Override
	public void onDetach() {
		super.onDetach();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {

		default:
			break;
		}
	}
}
