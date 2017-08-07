package wuxc.single.railwayparty;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import wuxc.single.railwayparty.internet.GetBitmapFromServer;
import wuxc.single.railwayparty.internet.HttpGetData;
import wuxc.single.railwayparty.internet.URLcontainer;
import wuxc.single.railwayparty.internet.UpLoadFile;
import wuxc.single.railwayparty.internet.getImageAbsolutePath;
import wuxc.single.railwayparty.internet.saveBitmap;
import wuxc.single.railwayparty.internet.savePNG;
import wuxc.single.railwayparty.layout.RoundImageView;
import wuxc.single.railwayparty.layout.dialogselecttwo;
import wuxc.single.railwayparty.layout.dialogtwo;
import wuxc.single.railwayparty.my.CreditsActivity;
import wuxc.single.railwayparty.my.CreditsRuleActivity;
import wuxc.single.railwayparty.my.EvaluationActivity;
import wuxc.single.railwayparty.my.MessageActivity;
import wuxc.single.railwayparty.my.MycheckActivity;
import wuxc.single.railwayparty.my.MycollectActivity;
import wuxc.single.railwayparty.my.MylearnActivity;
import wuxc.single.railwayparty.my.MypublishActivity;
import wuxc.single.railwayparty.my.MyvoteActivity;
import wuxc.single.railwayparty.my.SettingActivity;

public class MyFragment extends MainBaseFragment implements OnClickListener {
	private LinearLayout lin_top;
	private RoundImageView round_headimg;
	private int screenwidth = 0;
	private float scale = 0;
	private float scalepx = 0;
	private float dp = 0;
	private static final int UPLOAD_PICTURE = 1;
	private static final int GET_CUT_PICTURE = 2;
	private static final int GET_UPLOAD_RESULT = 3;
	private static final int PHOTO_REQUEST_TAKEPHOTO = 4;// 拍照
	private Bitmap mbitmap;
	private static String HeadimgAbsolutePath;
	private ImageView image_search;
	private RelativeLayout rel_message;
	private RelativeLayout rel_mylearn;
	private RelativeLayout rel_mycollect;
	private RelativeLayout rel_mypublish;
	private RelativeLayout rel_mycheck;
	private RelativeLayout rel_myvote;
	private RelativeLayout rel_credits;
	private RelativeLayout rel_evaluation;
	private LinearLayout lin_credits;
	private SharedPreferences PreUserInfo;// 存储个人信息
	private String LoginId;
	private String ticket = "";
	private String userPhoto;
	private TextView text_username;
	private TextView text_sign;
	private final static int GET_USER_HEAD_IMAGE = 6;
	private boolean UploadImage = false;
	private int credit = 0;
	private TextView text_credit;
	private int warning = 0;
	private Handler uiHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case GET_UPLOAD_RESULT:
				GetUpLoadResult(msg.obj);
				break;
			case GET_USER_HEAD_IMAGE:
				ShowHeadImage(msg.obj);
				break;
			case 9:
				Showcredit(msg.obj);
				break;
			default:
				break;
			}
		}
	};

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.wuxc_fragment_my, container, false);
		initview(view);
		initheight(view);
		PreUserInfo = getActivity().getSharedPreferences("UserInfo", Context.MODE_PRIVATE);
		ReadTicket();
		if (warning == 0) {
			showAlertDialog("", "");
		}
		GetHeadPic();
		getd();
		return view;
	}

	public void showAlertDialog(String versionNum, final String versionPath) {

		dialogtwo.Builder builder = new dialogtwo.Builder(getActivity());
		builder.setMessage("请修改个人资料及头像\n如已修改请忽略此消息");
		builder.setTitle("温馨提示");

		builder.setNegativeButton("知道了", new android.content.DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
				Editor edit = PreUserInfo.edit();
				edit.putInt("warning", 1);
				edit.commit();
			}
		});

		builder.create().show();

	}

	protected void Showcredit(Object obj) {
		// TODO Auto-generated method stub
		String datacre = null;

		try {
			JSONObject demoJson = new JSONObject(obj.toString());
			datacre = demoJson.getString("data");
			demoJson = new JSONObject(datacre);
			credit = demoJson.getInt("totalNum");
			Editor edit = PreUserInfo.edit();
			edit.putInt("credit", credit);
			edit.commit();
			text_credit.setText("" + credit);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	private void getd() {
		// TODO Auto-generated method stub

		final ArrayList ArrayValues = new ArrayList();

		ArrayValues.add(new BasicNameValuePair("ticket", "" + ticket));
		new Thread(new Runnable() { // 开启线程上传文件
			@Override
			public void run() {
				String LoginResultData = "";
				LoginResultData = HttpGetData.GetData("api/console/userScore/getListJsonStaticsDataRecord",
						ArrayValues);
				Message msg = new Message();
				msg.obj = LoginResultData;
				msg.what = 9;
				uiHandler.sendMessage(msg);
			}
		}).start();
	}

	protected void ShowHeadImage(Object obj) {
		// TODO Auto-generated method stub
		if (!(obj == null)) {
			try {
				Bitmap HeadImage = (Bitmap) obj;
				round_headimg.setImageBitmap(HeadImage);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}

	protected void GetUpLoadResult(Object obj) {
		// TODO Auto-generated method stub
		if (!(obj == null)) {
			String fileInfo = null;
			try {
				JSONObject demoJson = new JSONObject(obj.toString());
				fileInfo = demoJson.getString("fileInfo");
				if (!fileInfo.equals("") || !(fileInfo == null)) {
					GetImageNameFromResult(fileInfo);
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}

	private void GetImageNameFromResult(String fileInfo) {
		// TODO Auto-generated method stub
		String fileName = null;
		String filePath = null;
		try {
			JSONObject demoJson = new JSONObject(fileInfo);
			fileName = demoJson.getString("fileName");
			filePath = demoJson.getString("filePath");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
		}
		if (fileName.equals("image_headimg")) {
			Toast.makeText(getActivity(), "上传成功！", Toast.LENGTH_SHORT).show();
			WriteUsPhoto(filePath);
			UploadImage = true;
		} else {
			Toast.makeText(getActivity(), "上传失败！", Toast.LENGTH_SHORT).show();
		}
	}

	private void WriteUsPhoto(String filePath) {
		// TODO Auto-generated method stub
		Editor edit = PreUserInfo.edit();
		edit.putString("userPhoto", filePath);
		edit.commit();
	}

	private void GetHeadPic() {
		// TODO Auto-generated method stub
		new Thread(new Runnable() { // 开启线程上传文件
			@Override
			public void run() {
				Bitmap HeadImage = null;
				HeadImage = GetBitmapFromServer
						.getBitmapFromServer(URLcontainer.urlip + URLcontainer.GetFile + userPhoto);
				Message msg = new Message();
				msg.what = GET_USER_HEAD_IMAGE;
				msg.obj = HeadImage;
				uiHandler.sendMessage(msg);
			}
		}).start();
	}

	private void ReadTicket() {
		// TODO Auto-generated method stub
		warning = PreUserInfo.getInt("warning", 0);
		LoginId = PreUserInfo.getString("loginId", null);
		ticket = PreUserInfo.getString("ticket", "");
		userPhoto = PreUserInfo.getString("userPhoto", null);
		try {
			text_username.setText(PreUserInfo.getString("userName", "系统"));
			text_sign.setText(PreUserInfo.getString("sign", "忠诚于党，为民服务"));
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	private void initheight(View view) {
		// TODO Auto-generated method stub
		int height = (int) (screenwidth * 683 / 1125);

		LinearLayout.LayoutParams LayoutParams = (android.widget.LinearLayout.LayoutParams) lin_top.getLayoutParams();
		LayoutParams.height = height;
		lin_top.setLayoutParams(LayoutParams);

		LinearLayout.LayoutParams LayoutParams1 = (android.widget.LinearLayout.LayoutParams) round_headimg
				.getLayoutParams();
		LayoutParams1.height = (int) (height / 2.5);
		LayoutParams1.width = (int) (height / 2.5);
		round_headimg.setLayoutParams(LayoutParams1);
	}

	private void initview(View view) {
		// TODO Auto-generated method stub
		screenwidth = getActivity().getWindow().getWindowManager().getDefaultDisplay().getWidth();
		DisplayMetrics mMetrics = new DisplayMetrics();
		getActivity().getWindowManager().getDefaultDisplay().getMetrics(mMetrics);
		scale = getActivity().getResources().getDisplayMetrics().density;
		// Log.e("mMetrics", mMetrics.toString() + "scale=" + scale + "0.5f"
		// +
		// 0.5f);
		dp = screenwidth / scale + 0.5f;
		scalepx = screenwidth / dp;
		text_credit = (TextView) view.findViewById(R.id.text_credit);
		lin_top = (LinearLayout) view.findViewById(R.id.lin_top);
		round_headimg = (RoundImageView) view.findViewById(R.id.round_headimg);
		round_headimg.setOnClickListener(this);
		image_search = (ImageView) view.findViewById(R.id.image_search);
		image_search.setOnClickListener(this);
		rel_message = (RelativeLayout) view.findViewById(R.id.rel_message);
		rel_mylearn = (RelativeLayout) view.findViewById(R.id.rel_mylearn);
		rel_mycollect = (RelativeLayout) view.findViewById(R.id.rel_mycollect);
		rel_mypublish = (RelativeLayout) view.findViewById(R.id.rel_mypublish);
		rel_mycheck = (RelativeLayout) view.findViewById(R.id.rel_mycheck);
		rel_myvote = (RelativeLayout) view.findViewById(R.id.rel_myvote);
		rel_evaluation = (RelativeLayout) view.findViewById(R.id.rel_evaluation);
		rel_credits = (RelativeLayout) view.findViewById(R.id.rel_credits);
		text_username = (TextView) view.findViewById(R.id.text_username);

		text_sign = (TextView) view.findViewById(R.id.text_sign);

		lin_credits = (LinearLayout) view.findViewById(R.id.lin_credits);
		rel_message.setOnClickListener(this);
		lin_credits.setOnClickListener(this);
		rel_mylearn.setOnClickListener(this);
		rel_mycollect.setOnClickListener(this);
		rel_mypublish.setOnClickListener(this);
		rel_mycheck.setOnClickListener(this);
		rel_myvote.setOnClickListener(this);
		rel_evaluation.setOnClickListener(this);
		rel_credits.setOnClickListener(this);
	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();

		MainActivity.curFragmentTag = getString(R.string.str_my);

	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (data == null)
			return;
		Bundle bundle = data.getExtras();
		switch (requestCode) {
		case PHOTO_REQUEST_TAKEPHOTO:// 当选择拍照时调用
			if (!(data == null) && !(bundle == null)) {
				// Log.e("data", "" + data);

				// Log.e("PHOTO_REQUEST_TAKEPHOTO", "" +
				// PHOTO_REQUEST_TAKEPHOTO);
				final Bitmap photo = data.getParcelableExtra("data");
				File file = null;

				try {
					file = saveBitmap.saveMyBitmap("photo", photo);

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// Log.e("pic", "PHOTO_REQUEST_TAKEPHOTO");
				if (file != null) {
					// Log.e("pic", "PHOTO_REQUEST_TAKEPHOTO" +
					// Uri.fromFile(file));
					startPhotoZoom(Uri.fromFile(file));
					HeadimgAbsolutePath = getImageAbsolutePath.getPath(getActivity(), Uri.fromFile(file));

				} else {
					Toast.makeText(getActivity(), "手机无法存储", Toast.LENGTH_SHORT).show();
				}
			}
			// HeadimgAbsolutePath = MediaStore.EXTRA_OUTPUT;
			break;
		case UPLOAD_PICTURE:
			if (!(data == null)) {
				HeadimgAbsolutePath = "";
				startPhotoZoom(data.getData());
				// Log.e("pic", "PHOTO_REQUEST_TAKEPHOTO"+data.getData());
				HeadimgAbsolutePath = getImageAbsolutePath.getPath(getActivity(), data.getData());
			}

			break;

		case GET_CUT_PICTURE:
			if (data != null) {
				setPicToView(data);
			}
			break;

		default:
			break;

		}
		super.onActivityResult(requestCode, resultCode, data);
	}

	private void setPicToView(Intent picdata) {
		Bundle extras = picdata.getExtras();
		if (extras != null) {
			Bitmap photo = extras.getParcelable("data");
			mbitmap = photo;
			Drawable drawable = new BitmapDrawable(photo);
			round_headimg.setImageBitmap(photo);
			Log.e("HeadimgAbsolutePath", HeadimgAbsolutePath);
			final File file1 = savePNG.savePNG_After(photo, "wuxc", HeadimgAbsolutePath);
			File file = null;

			try {
				file = saveBitmap.saveMyBitmap("wuxc", photo);

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			new Thread(new Runnable() { // 开启线程上传文件
				@Override
				public void run() {
					String UpLoadResult = UpLoadFile.uploadHeadImage(file1,
							URLcontainer.urlip + "uploadUserPortrait/uploadSignle", LoginId, "" + ticket);
					Message msg = new Message();
					msg.what = GET_UPLOAD_RESULT;
					msg.obj = UpLoadResult;
					uiHandler.sendMessage(msg);
				}
			}).start();
		}
	}

	public void startPhotoZoom(Uri uri) {
		Intent intent = new Intent("com.android.camera.action.CROP");
		intent.setDataAndType(uri, "image/*");
		intent.putExtra("crop", "true");
		intent.putExtra("aspectX", 1);
		intent.putExtra("aspectY", 1);
		intent.putExtra("outputX", 150);
		intent.putExtra("outputY", 150);
		intent.putExtra("return-data", true);
		intent.putExtra("circleCrop", true);

		startActivityForResult(intent, GET_CUT_PICTURE);
	}

	private void selecttype() {

		dialogselecttwo.Builder builder = new dialogselecttwo.Builder(getActivity());
		builder.setMessage("免冠证件照");
		builder.setTitle("提示");
		builder.setPositiveButton("拍照", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
				Intent cameraintent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				// 指定调用相机拍照后照片的储存路径

				startActivityForResult(cameraintent, PHOTO_REQUEST_TAKEPHOTO);
			}

		});

		builder.setNegativeButton("相册", new android.content.DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
				Intent intent = new Intent(Intent.ACTION_PICK, null);
				intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
				startActivityForResult(intent, UPLOAD_PICTURE);
			}
		});

		builder.create().show();

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.round_headimg:
			selecttype();
			break;
		case R.id.image_search:
			Intent intent_search = new Intent();
			intent_search.setClass(getActivity(), SettingActivity.class);
			startActivity(intent_search);
			break;
		case R.id.lin_credits:
			if (true) {
				Intent intent_credits = new Intent();
				intent_credits.setClass(getActivity(), CreditsActivity.class);
				startActivity(intent_credits);
			}
			break;
		case R.id.rel_credits:
			if (true) {
				Intent intent_credits = new Intent();
				intent_credits.setClass(getActivity(), CreditsActivity.class);
				startActivity(intent_credits);
			}

			break;
		case R.id.rel_message:
			Intent intent_message = new Intent();
			intent_message.setClass(getActivity(), MessageActivity.class);
			startActivity(intent_message);
			break;

		case R.id.rel_mylearn:
			Intent intent_mylearn = new Intent();
			intent_mylearn.setClass(getActivity(), MylearnActivity.class);
			startActivity(intent_mylearn);
			break;
		case R.id.rel_mycollect:
			Intent intent_mycollect = new Intent();
			intent_mycollect.setClass(getActivity(), MycollectActivity.class);
			startActivity(intent_mycollect);
			break;
		case R.id.rel_mypublish:
			Intent intent_mypublish = new Intent();
			intent_mypublish.setClass(getActivity(), MypublishActivity.class);
			startActivity(intent_mypublish);
			break;
		case R.id.rel_mycheck:
			Intent intent_mycheck = new Intent();
			intent_mycheck.setClass(getActivity(), MycheckActivity.class);
			startActivity(intent_mycheck);
			break;
		case R.id.rel_myvote:
			Intent intent_vote = new Intent();
			intent_vote.setClass(getActivity(), MyvoteActivity.class);
			startActivity(intent_vote);
			break;
		case R.id.rel_evaluation:
			Intent intent_evaluation = new Intent();
			intent_evaluation.setClass(getActivity(), EvaluationActivity.class);
			startActivity(intent_evaluation);
			break;
		default:
			break;
		}
	}

}
