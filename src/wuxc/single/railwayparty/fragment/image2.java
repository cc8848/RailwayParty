package wuxc.single.railwayparty.fragment;

import com.polites.android.GestureImageView;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
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

public class image2 extends Fragment implements OnClickListener {
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
				userPhoto = PreUserInfo.getString("image" +  (imageshow.bigpage*10+1), "");
				GetHeadPic();
			} catch (Exception e) {
				// TODO: handle exception
			}

		}
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
