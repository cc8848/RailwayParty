package wuxc.single.railwayparty.main;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.UUID;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.newsapp.photo.adapter.AddImageGridAdapter;
import com.example.newsapp.photo.controller.SelectPicPopupWindow;
import com.example.newsapp.photo.photo.Item;
import com.example.newsapp.photo.photo.PhotoAlbumActivity;
import com.example.newsapp.photo.photoviewer.photoviewerinterface.ViewPagerActivity;
import com.example.newsapp.photo.photoviewer.photoviewerinterface.ViewPagerDeleteActivity;
import com.example.newsapp.photo.util.PictureManageUtil;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.provider.MediaStore.Images.Thumbnails;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import wuxc.single.railwayparty.R;
import wuxc.single.railwayparty.internet.HttpGetData;
import wuxc.single.railwayparty.internet.URLcontainer;
import wuxc.single.railwayparty.internet.UpLoadFile;

public class PublishTipsDYQActivity extends FragmentActivity implements OnClickListener {
	private EditText edit_name;
	private EditText edit_content;
	private Button btn_ok;
	private String ticket = "";
	private String chn;
	private String userPhoto;
	private String LoginId;
	private SharedPreferences PreUserInfo;// 存储个人信息
	private SharedPreferences PreALLChannel;// 存储所用频道信息
	private static final String GET_SUCCESS_RESULT = "success";
	private static final String GET_FAIL_RESULT = "fail";
	private static final int GET_DUE_DATA = 6;
	private TextView TextArticle;
	private TextView TextVideo;
	private int type = 2;
	private LinearLayout lin_select;
	private LinearLayout lin_center;
	private TextView text_one;
	private TextView text_two;
	private TextView text_three;
	private TextView text_label;
	private int classify = 0;
	private TextView text_load;
	private String attachment_ext;
	private String attachment_scalePath;
	private String attachment_classify;
	private String attachment_fileName;
	private String attachment_par_keyid;
	private String attachment_size;
	private String attachment_filePath;
	private String attachment_pathType;
	private String attachment_key;
	public Handler uiHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case GET_DUE_DATA:
				GetDataDueData(msg.obj);
				break;
			case 1:
				GetDataAttachment(msg.obj);
				break;
			default:
				break;
			}
		}
	};
	/* 用来标识请求照相功能的activity */
	private final int CAMERA_WITH_DATA = 3023;
	/* 用来标识请求gallery的activity */
	private final int PHOTO_PICKED_WITH_DATA = 3021;
	// GridView预览删除页面过来
	private final int PIC_VIEW_CODE = 2016;
	/* 拍照的照片存储位置 */
	private final File PHOTO_DIR = new File(
			Environment.getExternalStorageDirectory() + "/Android/data/com.photo.choosephotos");
	private File mCurrentPhotoFile;// 照相机拍照得到的图片
	// 用来显示预览图
	private ArrayList<Bitmap> microBmList = new ArrayList<Bitmap>();
	// 所选图的信息(主要是路径)
	private ArrayList<Item> photoList = new ArrayList<Item>();
	private AddImageGridAdapter imgAdapter;
	private Bitmap addNewPic;
	private GridView gridView;// 显示所有上传图片
	private SelectPicPopupWindow menuWindow;
	private int pic_number = 0;
	private int screenwidth = 0;
	private JSONArray kArray = new JSONArray();

	private boolean write = false;
	private int load = 0;

	protected void GetDataAttachment(Object obj) {

		// TODO Auto-generated method stub
		String state = null;
		String fileInfo = null;
		try {
			JSONObject demoJson = new JSONObject(obj.toString());
			state = demoJson.getString("state");
			fileInfo = demoJson.getString("fileInfo");
			if (state.equals("1")) {
				// Toast.makeText(getApplicationContext(), "文件上传成功", 0).show();

				GetDetailDataAttachment(fileInfo);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			// Toast.makeText(getApplicationContext(), "", 0).show();
		} catch (Exception e) {
			// TODO: handle exception
		}
		load++;
		if (load == photoList.size()) {
			Log.e("kArray", kArray.toString());
			GetData();
		}
	}

	private void GetDetailDataAttachment(String fileInfo) {
		// TODO Auto-generated method stub
		try {
			JSONObject demoJson = new JSONObject(fileInfo);

			attachment_ext = demoJson.getString("ext");
			attachment_classify = demoJson.getString("classify");
			attachment_fileName = demoJson.getString("fileName");
			attachment_filePath = demoJson.getString("filePath");
			attachment_key = demoJson.getString("key");
			attachment_par_keyid = demoJson.getString("par_keyid");
			attachment_pathType = demoJson.getString("pathType");
			attachment_scalePath = demoJson.getString("scalePath");
			attachment_size = demoJson.getString("size");
			attachment_classify = "imageList";
			try {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("operateFlag", "1");
				jsonObject.put("ext", attachment_ext);
				jsonObject.put("scalePath", attachment_scalePath);
				jsonObject.put("classify", attachment_classify);
				jsonObject.put("fileName", attachment_fileName);
				jsonObject.put("par_keyid", attachment_par_keyid);
				jsonObject.put("size", attachment_size);
				jsonObject.put("filePath", attachment_filePath);
				jsonObject.put("pathType", attachment_key);
				jsonObject.put("key", attachment_key);
				kArray.put(jsonObject);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	@SuppressLint("NewApi")
	protected void GetDataDueData(Object obj) {
		text_load.setVisibility(View.GONE);
		for (int i = 0; i < kArray.length(); i++) {
			kArray.remove(i);
			i--;
		}

		String Type = null;
		String Data = null;
		String pager = null;
		try {
			JSONObject demoJson = new JSONObject(obj.toString());
			Type = demoJson.getString("type");
			// pager = demoJson.getString("pager");
			// Data = demoJson.getString("datas");
			if (Type.equals(GET_SUCCESS_RESULT)) {
				Toast.makeText(getApplicationContext(), "发表成功", Toast.LENGTH_SHORT).show();
				finish();
				text_load.setVisibility(View.GONE);
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

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.wuxc_activity_publishtips3);
		ImageView image_back = (ImageView) findViewById(R.id.image_back);
		image_back.setOnClickListener(this);
		edit_name = (EditText) findViewById(R.id.edit_name);
		edit_content = (EditText) findViewById(R.id.edit_content);
		btn_ok = (Button) findViewById(R.id.btn_ok);
		btn_ok.setOnClickListener(this);
		PreUserInfo = getSharedPreferences("UserInfo", Context.MODE_PRIVATE);
		ReadTicket();
		TextView text_upload = (TextView) findViewById(R.id.text_upload);
		text_upload.setOnClickListener(this);
		text_load = (TextView) findViewById(R.id.text_load);
		lin_select = (LinearLayout) findViewById(R.id.lin_select);
		lin_center = (LinearLayout) findViewById(R.id.lin_center);
		text_one = (TextView) findViewById(R.id.text_one);
		text_two = (TextView) findViewById(R.id.text_two);
		text_three = (TextView) findViewById(R.id.text_three);
		text_label = (TextView) findViewById(R.id.text_label);
		lin_select.setOnClickListener(this);
		lin_center.setOnClickListener(this);
		text_one.setOnClickListener(this);
		text_two.setOnClickListener(this);
		text_label.setOnClickListener(this);
		text_load.setOnClickListener(this);
		text_three.setOnClickListener(this);
		text_load.setOnClickListener(this);
		text_load.setVisibility(View.GONE);
		screenwidth = getWindow().getWindowManager().getDefaultDisplay().getWidth();

		if (!(PHOTO_DIR.exists() && PHOTO_DIR.isDirectory())) {
			PHOTO_DIR.mkdirs();
		}
		// 添加图片
		gridView = (GridView) findViewById(R.id.allPic);
		// 加号图片
		addNewPic = BitmapFactory.decodeResource(this.getResources(), R.drawable.add_new_pic);
		// addNewPic = PictureManageUtil.resizeBitmap(addNewPic, 180, 180);
		pic_number = microBmList.size();
		Log.e("pic_number", "pic_number" + pic_number);
		if (pic_number < 9) {
			microBmList.add(addNewPic);
		}

		imgAdapter = new AddImageGridAdapter(this, microBmList, screenwidth);
		gridView.setAdapter(imgAdapter);
		// 事件监听，点击GridView里的图片时，在ImageView里显示出来
		gridView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
				if (position == (photoList.size())) {
					menuWindow = new SelectPicPopupWindow(PublishTipsDYQActivity.this, itemsOnClick);
					menuWindow.showAtLocation(PublishTipsDYQActivity.this.findViewById(R.id.uploadPictureLayout),
							Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0); // 设置layout在PopupWindow中显示的位置
				} else {
					Intent intent = new Intent(PublishTipsDYQActivity.this, ViewPagerDeleteActivity.class);
					intent.putParcelableArrayListExtra("files", photoList);
					intent.putExtra(ViewPagerActivity.CURRENT_INDEX, position);
					startActivityForResult(intent, PIC_VIEW_CODE);
				}
			}
		});
		// GetData();
	}

	public String getTimeByCalendar() {
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);// 获取年份
		int month = cal.get(Calendar.MONTH);// 获取月份
		int day = cal.get(Calendar.DAY_OF_MONTH);// 获取日
		// int hour=cal.get(Calendar.HOUR);//小时
		// int minute=cal.get(Calendar.MINUTE);//分
		// int second=cal.get(Calendar.SECOND);//秒
		// int WeekOfYear = cal.get(Calendar.DAY_OF_WEEK);//一周的第几天
		// System.out.println("现在的时间是：公元"+year+"年"+month+"月"+day+"日
		// "+hour+"时"+minute+"分"+second+"秒 星期"+WeekOfYear);
		String Mon = "";
		String Day = "";
		month++;
		if (month < 10) {
			Mon = "0" + month;
		}
		if (day < 10) {
			Day = "0" + day;
		}
		Log.e("getTimeByCalendar", year + "-" + Mon + "-" + Day);
		return year + "-" + Mon + "-" + Day;
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
		ArrayValues.add(new BasicNameValuePair("article.classify", "" + classify));
		ArrayValues.add(new BasicNameValuePair("article.releaseDate", "" + getTimeByCalendar()));
		ArrayValues.add(new BasicNameValuePair("chn", "dyq"));
		ArrayValues.add(new BasicNameValuePair("par_keyid", "886571396132638720"));
		ArrayValues.add(new BasicNameValuePair("article.title", "" + edit_name.getText().toString()));
		ArrayValues.add(new BasicNameValuePair("article.hstate", "3"));
		ArrayValues.add(new BasicNameValuePair("article.content", "" + edit_content.getText().toString()));
		for (int i = 0; i < kArray.length(); i++) {
			try {
				JSONObject jsonObject = kArray.getJSONObject(i);
				// jsonObject.put("operateFlag", "1");
				// jsonObject.put("ext", attachment_ext);
				// jsonObject.put("scalePath", attachment_scalePath);
				// jsonObject.put("classify", attachment_classify);
				// jsonObject.put("fileName", attachment_fileName);
				// jsonObject.put("par_keyid", attachment_par_keyid);
				// jsonObject.put("size", attachment_size);
				// jsonObject.put("filePath", attachment_filePath);
				// jsonObject.put("pathType", attachment_key);
				// jsonObject.put("key", attachment_key);
				ArrayValues.add(new BasicNameValuePair("attacement.operateFlag", jsonObject.getString("operateFlag")));
				ArrayValues.add(new BasicNameValuePair("attacement.ext", jsonObject.getString("ext")));
				ArrayValues.add(new BasicNameValuePair("attacement.scalePath", jsonObject.getString("scalePath")));
				ArrayValues.add(new BasicNameValuePair("attacement.classify", jsonObject.getString("classify")));
				ArrayValues.add(new BasicNameValuePair("attacement.fileName", jsonObject.getString("fileName")));
				ArrayValues.add(new BasicNameValuePair("attacement.par_keyid", jsonObject.getString("par_keyid")));
				ArrayValues.add(new BasicNameValuePair("attacement.size", jsonObject.getString("size")));
				ArrayValues.add(new BasicNameValuePair("attacement.filePath", jsonObject.getString("filePath")));
				ArrayValues.add(new BasicNameValuePair("attacement.pathType", jsonObject.getString("pathType")));
				ArrayValues.add(new BasicNameValuePair("attacement.key", jsonObject.getString("key")));

			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		new Thread(new Runnable() { // 开启线程上传文件
			@Override
			public void run() {
				String DueData = "";
				DueData = HttpGetData.GetData("api/pb/tiezi/saveData", ArrayValues);
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
		userPhoto = PreUserInfo.getString("userPhoto", "");
		LoginId = PreUserInfo.getString("userName", "");
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.image_back:
			finish();
			break;
		case R.id.lin_select:
			lin_select.setVisibility(View.GONE);
			break;
		case R.id.lin_center:
			lin_select.setVisibility(View.VISIBLE);
			break;
		case R.id.text_one:
			lin_select.setVisibility(View.GONE);
			classify = 1;
			text_label.setText("党内活动");
			break;
		case R.id.text_two:
			lin_select.setVisibility(View.GONE);
			classify = 2;
			text_label.setText("党员教育");
			break;
		case R.id.text_three:
			lin_select.setVisibility(View.GONE);
			classify = 3;
			text_label.setText("党员生活");
			break;
		case R.id.text_label:
			lin_select.setVisibility(View.VISIBLE);
			break;
		case R.id.btn_ok:
			text_load.setVisibility(View.VISIBLE);
			load = 0;
			for (int i = 0; i < photoList.size(); i++) {
				text_load.setText("正在上传");
				Log.e("photoList", photoList.get(i).getPhotoPath());
				final File file = GetFile(photoList.get(i).getPhotoPath());
				text_load.setVisibility(View.VISIBLE);
				if (!(file == null)) {
					new Thread(new Runnable() { // 开启线程上传文件
						@Override
						public void run() {
							String UpLoadResult = UpLoadFile.uploadFileatt(file,
									URLcontainer.urlip + "console/form/formfileUpload/uploadSignle", "tiezi",
									"" + ticket);
							Message msg = new Message();
							msg.what = 1;
							msg.obj = UpLoadResult;
							uiHandler.sendMessage(msg);
						}
					}).start();
				}
			}
			// GetFile( photoList.get(0).getPhotoPath());

			break;
		case R.id.text_upload:
			Intent intent = null;
			// if (Build.VERSION.SDK_INT < 19) {
			intent = new Intent(Intent.ACTION_GET_CONTENT);
			intent.setType("file/*");
			intent.addCategory(Intent.CATEGORY_OPENABLE);
			// } else {
			// intent = new Intent(Intent.ACTION_PICK,
			// android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
			// }
			startActivityForResult(intent, 0);
			break;
		default:
			break;
		}
	}

	// @Override
	// public void onActivityResult(int requestCode, int resultCode, Intent
	// data) {
	// // TODO Auto-generated method stub
	// super.onActivityResult(requestCode, resultCode, data);
	// if (data == null)
	// return;
	// Bundle bundle = data.getExtras();
	// switch (requestCode) {
	//
	// case 0:
	// // 发送选择的文件
	// if (data != null) {
	// Uri uri = data.getData();
	// if (uri != null) {
	// // Toast.makeText(getApplicationContext(), "正在上传",
	// // 0).show();
	//
	// final File file = GetFile(uri);
	// text_load.setVisibility(View.VISIBLE);
	// if (!(file == null)) {
	// new Thread(new Runnable() { // 开启线程上传文件
	// @Override
	// public void run() {
	// String UpLoadResult = UpLoadFile.uploadFileatt(file,
	// URLcontainer.urlip + "console/form/formfileUpload/uploadSignle", "xinde",
	// "" + ticket);
	// Message msg = new Message();
	// msg.what = 1;
	// msg.obj = UpLoadResult;
	// uiHandler.sendMessage(msg);
	// }
	// }).start();
	// }
	// }
	// }
	//
	// break;
	// default:
	// break;
	// }
	// }

	/**
	 * 获取文件
	 * 
	 * @param uri
	 */
	private File GetFile(String filePath) {
		// String filePath = null;
		// if ("content".equalsIgnoreCase(uri.getScheme())) {
		// String[] projection = { "_data" };
		// Cursor cursor = null;
		//
		// try {
		// cursor = getContentResolver().query(uri, projection, null, null,
		// null);
		// int column_index = cursor.getColumnIndexOrThrow("_data");
		// if (cursor.moveToFirst()) {
		// filePath = cursor.getString(column_index);
		// }
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		// } else if ("file".equalsIgnoreCase(uri.getScheme())) {
		// filePath = uri.getPath();
		// }
		File file = new File(filePath);
		if (file == null || !file.exists()) {

			Toast.makeText(getApplicationContext(), "文件不存在", 0).show();
			return file;
		}
		if (file.length() > 20 * 1024 * 1024) {

			Toast.makeText(getApplicationContext(), "文件不能大于20M", 0).show();
			return null;
		}
		return file;
	}

	// 为弹出窗口实现监听类
	private View.OnClickListener itemsOnClick = new View.OnClickListener() {
		public void onClick(View v) {
			menuWindow.dismiss();
			switch (v.getId()) {
			case R.id.btn_take_photo: {
				String status = Environment.getExternalStorageState();
				if (status.equals(Environment.MEDIA_MOUNTED)) {
					// 判断是否有SD卡
					doTakePhoto();// 用户点击了从照相机获取
				} else {
					Toast.makeText(PublishTipsDYQActivity.this, "没有SD卡", Toast.LENGTH_LONG).show();
				}
				break;
			}
			case R.id.btn_pick_photo: {
				// 打开选择图片界面
				doPickPhotoFromGallery();
				break;
			}
			default:
				break;
			}
		}
	};

	/**
	 * 拍照获取图片
	 * 
	 */
	protected void doTakePhoto() {
		try {
			// 创建照片的存储目录
			mCurrentPhotoFile = new File(PHOTO_DIR, getPhotoFileName());// 给新照的照片文件命名
			final Intent intent = getTakePickIntent(mCurrentPhotoFile);
			// Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
			startActivityForResult(intent, CAMERA_WITH_DATA);
		} catch (ActivityNotFoundException e) {
			Toast.makeText(this, "找不到相机", Toast.LENGTH_LONG).show();
		}
	}

	public String getPhotoFileName() {
		UUID uuid = UUID.randomUUID();
		return uuid + ".jpg";
	}

	public static Intent getTakePickIntent(File f) {
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE, null);
		intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
		return intent;
	}

	// 请求Gallery程序
	protected void doPickPhotoFromGallery() {
		try {
			final ProgressDialog dialog;
			dialog = new ProgressDialog(this);
			dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER); // 设置为圆形
			dialog.setMessage("数据加载中...");
			dialog.setIndeterminate(false);//
			// dialog.setCancelable(true); //按回退键取消
			dialog.show();
			Window window = dialog.getWindow();
			View view = window.getDecorView();
			// Tools.setViewFontSize(view,21);
			new Handler().postDelayed(new Runnable() {
				public void run() {
					// 初始化提示框
					dialog.dismiss();
				}

			}, 1000);
			// final Intent intent = new
			// Intent(PublishTipsActivity.this,GetAllImgFolderActivity.class);
			final Intent intent = new Intent(PublishTipsDYQActivity.this, PhotoAlbumActivity.class);
			Bundle bundle = new Bundle();

			bundle.putInt("pic_number", pic_number);
			intent.putExtras(bundle);
			startActivityForResult(intent, PHOTO_PICKED_WITH_DATA);
		} catch (ActivityNotFoundException e) {
			Toast.makeText(this, "图库中找不到照片", Toast.LENGTH_LONG).show();
		}
	}

	Handler handler = new Handler() {

		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 1:
				imgAdapter.notifyDataSetChanged();
				break;
			default:
				break;
			}
		};
	};

	/**
	 * 处理其他页面返回数据
	 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode != RESULT_OK)
			return;
		switch (requestCode) {
		case PHOTO_PICKED_WITH_DATA: {
			// 调用Gallery返回的
			ArrayList<Item> tempFiles = new ArrayList<Item>();
			if (data == null)
				return;
			tempFiles = data.getParcelableArrayListExtra("fileNames");
			Log.e("test", "被选中的照片" + tempFiles.toString());

			if (tempFiles == null) {
				return;
			}
			microBmList.remove(addNewPic);
			Bitmap bitmap;
			for (int i = 0; i < tempFiles.size(); i++) {
				bitmap = MediaStore.Images.Thumbnails.getThumbnail(this.getContentResolver(),
						tempFiles.get(i).getPhotoID(), Thumbnails.MINI_KIND, null);
				int rotate = PictureManageUtil.getCameraPhotoOrientation(tempFiles.get(i).getPhotoPath());
				bitmap = PictureManageUtil.rotateBitmap(bitmap, rotate);
				microBmList.add(bitmap);
				photoList.add(tempFiles.get(i));
			}

			pic_number = microBmList.size();
			Log.e("pic_number", "pic_number" + pic_number);
			if (pic_number < 9) {
				microBmList.add(addNewPic);
			}
			imgAdapter.notifyDataSetChanged();
			break;
		}
		case CAMERA_WITH_DATA: {
			Long delayMillis = 0L;
			if (mCurrentPhotoFile == null) {
				delayMillis = 500L;
			}
			handler.postDelayed(new Runnable() {
				@Override
				public void run() {
					// 照相机程序返回的,再次调用图片剪辑程序去修剪图片
					// 去掉GridView里的加号
					microBmList.remove(addNewPic);
					Item item = new Item();
					item.setPhotoPath(mCurrentPhotoFile.getAbsolutePath());
					photoList.add(item);
					// 根据路径，得到一个压缩过的Bitmap（宽高较大的变成500，按比例压缩）
					Bitmap bitmap = PictureManageUtil.getCompressBm(mCurrentPhotoFile.getAbsolutePath());
					// 获取旋转参数
					int rotate = PictureManageUtil.getCameraPhotoOrientation(mCurrentPhotoFile.getAbsolutePath());
					// 把压缩的图片进行旋转
					bitmap = PictureManageUtil.rotateBitmap(bitmap, rotate);
					microBmList.add(bitmap);
					pic_number = microBmList.size();
					Log.e("pic_number", "pic_number" + pic_number);
					if (pic_number < 9) {
						microBmList.add(addNewPic);
					}
					Message msg = handler.obtainMessage(1);
					msg.sendToTarget();
				}
			}, delayMillis);
			break;
		}
		case PIC_VIEW_CODE: {
			ArrayList<Integer> deleteIndex = data.getIntegerArrayListExtra("deleteIndexs");
			if (deleteIndex.size() > 0) {
				for (int i = deleteIndex.size() - 1; i >= 0; i--) {
					microBmList.remove((int) deleteIndex.get(i));
					photoList.remove((int) deleteIndex.get(i));
				}
			}
			pic_number = microBmList.size();
			Log.e("pic_number", "pic_number" + pic_number);
			if (pic_number < 9 && photoList.size() >= microBmList.size()) {
				microBmList.add(addNewPic);
			}
			imgAdapter.notifyDataSetChanged();
			break;
		}
		}
	}
}
