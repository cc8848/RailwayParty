package wuxc.single.railwayparty.my;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import wuxc.single.railwayparty.R;

import wuxc.single.railwayparty.adapter.TipsDetailAdapter;
import wuxc.single.railwayparty.adapter.TipsDetailAdapter.Callback;
import wuxc.single.railwayparty.internet.HttpGetData;
import wuxc.single.railwayparty.internet.URLcontainer;
import wuxc.single.railwayparty.layout.dialogtwo;
import wuxc.single.railwayparty.model.BuildModel;
import wuxc.single.railwayparty.model.TipsDetailModel;
import wuxc.single.railwayparty.start.SpecialDetailActivity;
import wuxc.single.railwayparty.start.StandardImageXML;
import wuxc.single.railwayparty.start.imageshow;

public class MyPublishDetailActivity extends Activity
		implements Callback, OnItemClickListener, OnClickListener, OnTouchListener {
	private RelativeLayout rel_comment;
	private EditText edit_comment;
	private Button btn_comment;
	private ListView list_data;
	private TextView text_time;
	private TextView text_title;
	private ImageView image_back;
	private ImageView image_zan;
	private ImageView image_delete;
	private String Title;
	private String Time;
	private String Id;
	private int number = 0;
	private String detail;
	private String[] photo = { "", "", "", "", "", "", "", "", "", "" };
	private ListView ListData;
	List<TipsDetailModel> list = new ArrayList<TipsDetailModel>();
	private static TipsDetailAdapter mAdapter;
	private String ticket = "";
	private SharedPreferences PreUserInfo;// 存储个人信息
	private String url;
	private String curl;
	private String modelSign;
	private String chn;
	private static final String GET_SUCCESS_RESULT = "success";
	private static final String GET_FAIL_RESULT = "fail";
	private static final int GET_DUE_DATA = 6;
	private boolean zan = false;
	private String imagelist = "";
	private int sigl = 0;
	public Handler uiHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case GET_DUE_DATA:
				GetDataDueData(msg.obj);
				// Log.e("1111", "11111");
				break;
			case 9:
				GetDataDueDatac(msg.obj);
				// Log.e("1111", "11111");
				break;
			case 23:
				delete(msg.obj);
				// Log.e("1111", "11111");
				break;
			default:
				break;
			}
		}
	};

	protected void delete(Object obj) {

		String Type = null;

		try {
			JSONObject demoJson = new JSONObject(obj.toString());
			Type = demoJson.getString("type");

			if (Type.equals(GET_SUCCESS_RESULT)) {
				// GetPager(Data);
				Toast.makeText(getApplicationContext(), "删除成功", Toast.LENGTH_SHORT).show();
				finish();
			} else if (Type.equals(GET_FAIL_RESULT)) {
				Toast.makeText(getApplicationContext(), "删除失败", Toast.LENGTH_SHORT).show();
			} else {
				Toast.makeText(getApplicationContext(), "删除失败", Toast.LENGTH_SHORT).show();
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	protected void GetDataDueData(Object obj) {
		Log.e("1111", "22222");
		// TODO Auto-generated method stub
		String Type = null;
		String Data = null;
		String pager = null;
		try {
			JSONObject demoJson = new JSONObject(obj.toString());
			Type = demoJson.getString("type");
			// pager = demoJson.getString("pager");
			Data = demoJson.getString("datas");
			if (Type.equals(GET_SUCCESS_RESULT)) {
				// GetPager(Data);
				GetDataList(Data, 1);
			} else if (Type.equals(GET_FAIL_RESULT)) {
				Toast.makeText(getApplicationContext(), "无数据", Toast.LENGTH_SHORT).show();
			} else {
				Toast.makeText(getApplicationContext(), "无数据", Toast.LENGTH_SHORT).show();
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	protected void GetDataDueDatac(Object obj) {
		Log.e("1111", "22222");
		// TODO Auto-generated method stub
		String Type = null;

		try {
			JSONObject demoJson = new JSONObject(obj.toString());
			Type = demoJson.getString("type");
			// pager = demoJson.getString("pager");

			if (Type.equals(GET_SUCCESS_RESULT)) {
				// GetPager(Data);
				Toast.makeText(getApplicationContext(), "评论成功", Toast.LENGTH_SHORT).show();
				GetData();
				InputMethodManager inputManager =

						(InputMethodManager) edit_comment.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);

				inputManager.hideSoftInputFromWindow(edit_comment.getWindowToken(), 0);
				edit_comment.setText("");
			} else if (Type.equals(GET_FAIL_RESULT)) {
				Toast.makeText(getApplicationContext(), "评论失败", Toast.LENGTH_SHORT).show();
			} else {
				Toast.makeText(getApplicationContext(), "评论失败", Toast.LENGTH_SHORT).show();
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private void GetDataList(String data, int arg) {
		Log.e("1111", "33333");
		if (arg == 1) {
			Log.e("1111", "3344");
			list.clear();
			if (true) {
				TipsDetailModel listinfo = new TipsDetailModel();
				listinfo.setNickName("");
				listinfo.setContent("");
				listinfo.setImageHeadimg("");
				listinfo.setTime("");
				listinfo.setDetail(detail);
				listinfo.setImage("");
				listinfo.setType(0);
				listinfo.setNumber(0);
				list.add(listinfo);
			}
			for (int i = 0; i < number; i++) {
				if (true) {

					TipsDetailModel listinfo = new TipsDetailModel();
					listinfo.setNickName("");
					listinfo.setContent("");
					listinfo.setImageHeadimg("");
					listinfo.setTime("");
					listinfo.setDetail("");
					Log.e("photo", photo[i]);
					listinfo.setImage(photo[i]);
					listinfo.setType(1);
					listinfo.setNumber(i);
					list.add(listinfo);
					Editor edit = PreUserInfo.edit();
					edit.putString("image" + i, photo[i]);
					edit.commit();
				}
			}
		}
		JSONArray jArray = null;
		try {
			jArray = new JSONArray(data);
			JSONObject json_data = null;
			if (jArray.length() == 0) {
				// / Toast.makeText(getActivity(), "无数据",
				// Toast.LENGTH_SHORT).show();

			} else {
				for (int i = 0; i < jArray.length(); i++) {
					json_data = jArray.getJSONObject(i);
					Log.e("json_data", "" + json_data);
					// JSONObject jsonObject = json_data.getJSONObject("data");
					TipsDetailModel listinfo = new TipsDetailModel();
					listinfo.setNickName(json_data.getString("user_name"));
					listinfo.setContent(json_data.getString("content"));
					listinfo.setImageHeadimg(json_data.getString("userPhoto"));
					listinfo.setTime(json_data.getString("createTime"));
					listinfo.setDetail("");
					listinfo.setImage("");
					listinfo.setType(2);

					list.add(listinfo);

				}
			}

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Log.e("1111", "" + list.size());
		if (arg == 1) {
			Log.e("1111", "44444");

			go();

		} else {
			mAdapter.notifyDataSetChanged();
		}

	}

	protected void go() {
		if (sigl == 0) {
			mAdapter = new TipsDetailAdapter(this, list, ListData, this);

			ListData.setAdapter(mAdapter);
		} else {
			mAdapter.notifyDataSetChanged();
		}

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.wuxc_activity_mypublish_detail);
		rel_comment = (RelativeLayout) findViewById(R.id.rel_comment);
		edit_comment = (EditText) findViewById(R.id.edit_comment);
		btn_comment = (Button) findViewById(R.id.btn_comment);
		ListData = (ListView) findViewById(R.id.list_data);
		text_time = (TextView) findViewById(R.id.text_time);
		text_title = (TextView) findViewById(R.id.text_title);
		image_back = (ImageView) findViewById(R.id.image_back);
		image_zan = (ImageView) findViewById(R.id.image_zan);
		image_delete = (ImageView) findViewById(R.id.image_delete);
		btn_comment.setOnClickListener(this);
		ListData.setOnItemClickListener(this);
		image_back.setOnClickListener(this);
		image_zan.setOnClickListener(this);
		image_delete.setOnClickListener(this);
		Intent intent = this.getIntent(); // 获取已有的intent对象
		Bundle bundle = intent.getExtras(); // 获取intent里面的bundle对象

		Title = bundle.getString("Title");
		Time = bundle.getString("Time");
		Id = bundle.getString("Id");
		number = bundle.getInt("number");
		detail = bundle.getString("detail");
		photo = bundle.getStringArray("photo");
		url = bundle.getString("url");
		curl = bundle.getString("curl");
		modelSign = bundle.getString("modelSign");
		chn = bundle.getString("chn");
		imagelist = bundle.getString("imagelist");
		text_title.setText(Title);
		text_time.setText(Time);
		if (true) {
			JSONArray jArray1 = null;
			number = 0;
			try {

				jArray1 = new JSONArray(imagelist);
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
		}
		PreUserInfo = getSharedPreferences("UserInfo", Context.MODE_PRIVATE);
		ReadTicket();

		GetData();

		final ArrayList ArrayValues = new ArrayList();
		// ArrayValues.add(new BasicNameValuePair("ticket", ticket));
		// ArrayValues.add(new BasicNameValuePair("applyType", "" + 2));
		// ArrayValues.add(new BasicNameValuePair("helpSType", "" +
		// type));
		// ArrayValues.add(new BasicNameValuePair("modelSign",
		// "KNDY_APPLY"));
		// ArrayValues.add(new BasicNameValuePair("curPage", "" +
		// curPage));
		// ArrayValues.add(new BasicNameValuePair("pageSize", "" +
		// pageSize));
		// final ArrayList ArrayValues = new ArrayList();
		ArrayValues.add(new BasicNameValuePair("ticket", "" + ticket));
		// chn = GetChannelByKey.GetSign(PreALLChannel, "职工之家");
		ArrayValues.add(new BasicNameValuePair(chn, modelSign));

		ArrayValues.add(new BasicNameValuePair("datakey", "" + Id));

		new Thread(new Runnable() { // 开启线程上传文件
			@Override
			public void run() {
				String DueData = "";
				DueData = HttpGetData.GetData("api/cms/common/browserModelItem", ArrayValues);

			}
		}).start();

	}

	private void ReadTicket() {
		// TODO Auto-generated method stub
		ticket = PreUserInfo.getString("ticket", "");
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
		ArrayValues.add(new BasicNameValuePair(chn, modelSign));

		ArrayValues.add(new BasicNameValuePair("datakey", "" + Id));

		new Thread(new Runnable() { // 开启线程上传文件
			@Override
			public void run() {
				String DueData = "";
				DueData = HttpGetData.GetData(url, ArrayValues);
				Message msg = new Message();
				msg.obj = DueData;
				msg.what = GET_DUE_DATA;
				uiHandler.sendMessage(msg);
			}
		}).start();

	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		return false;
	}

	public void showAlertDialog() {

		dialogtwo.Builder builder = new dialogtwo.Builder(this);
		builder.setMessage("是否确定删除本条动态");
		builder.setTitle("温馨提示");
		builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
				final ArrayList ArrayValues = new ArrayList();
				ArrayValues.add(new BasicNameValuePair("ticket", "" + ticket));
				ArrayValues.add(new BasicNameValuePair("chn", "dyq"));
				ArrayValues.add(new BasicNameValuePair("datakey", "" + Id));

				new Thread(new Runnable() { // 开启线程上传文件
					@Override
					public void run() {
						String DueData = "";
						DueData = HttpGetData.GetData("api/pb/tiezi/delete", ArrayValues);
						Message msg = new Message();
						msg.obj = DueData;
						msg.what = 23;
						uiHandler.sendMessage(msg);
					}
				}).start();
			}
		});

		builder.setNegativeButton("取消", new android.content.DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});

		builder.create().show();

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.image_back:
			finish();
			break;
		case R.id.image_delete:
			showAlertDialog();
			break;
		case R.id.image_zan:
			if (zan) {
				Toast.makeText(getApplicationContext(), "重复点赞", Toast.LENGTH_SHORT).show();
			} else {
				Toast.makeText(getApplicationContext(), "点赞成功", Toast.LENGTH_SHORT).show();
				zan = true;
				image_zan.setImageResource(R.drawable.zan_yes);
				final ArrayList ArrayValues = new ArrayList();
				// ArrayValues.add(new BasicNameValuePair("ticket", ticket));
				// ArrayValues.add(new BasicNameValuePair("applyType", "" + 2));
				// ArrayValues.add(new BasicNameValuePair("helpSType", "" +
				// type));
				// ArrayValues.add(new BasicNameValuePair("modelSign",
				// "KNDY_APPLY"));
				// ArrayValues.add(new BasicNameValuePair("curPage", "" +
				// curPage));
				// ArrayValues.add(new BasicNameValuePair("pageSize", "" +
				// pageSize));
				// final ArrayList ArrayValues = new ArrayList();
				ArrayValues.add(new BasicNameValuePair("ticket", "" + ticket));
				// chn = GetChannelByKey.GetSign(PreALLChannel, "职工之家");
				ArrayValues.add(new BasicNameValuePair(chn, modelSign));

				ArrayValues.add(new BasicNameValuePair("datakey", "" + Id));

				new Thread(new Runnable() { // 开启线程上传文件
					@Override
					public void run() {
						String DueData = "";
						DueData = HttpGetData.GetData("api/cms/common/toUp", ArrayValues);

					}
				}).start();
			}
			break;
		case R.id.btn_comment:
			String temp = edit_comment.getText().toString();
			sigl = 9;
			if (temp.equals("") || temp == null) {

				Toast.makeText(getApplicationContext(), "请输入评论内容", Toast.LENGTH_SHORT).show();

			} else {
				if (true) {
					final ArrayList ArrayValues = new ArrayList();
					// ArrayValues.add(new BasicNameValuePair("ticket",
					// ticket));
					// ArrayValues.add(new BasicNameValuePair("applyType", "" +
					// 2));
					// ArrayValues.add(new BasicNameValuePair("helpSType", "" +
					// type));
					// ArrayValues.add(new BasicNameValuePair("modelSign",
					// "KNDY_APPLY"));
					// ArrayValues.add(new BasicNameValuePair("curPage", "" +
					// curPage));
					// ArrayValues.add(new BasicNameValuePair("pageSize", "" +
					// pageSize));
					// final ArrayList ArrayValues = new ArrayList();
					ArrayValues.add(new BasicNameValuePair("ticket", "" + ticket));
					// chn = GetChannelByKey.GetSign(PreALLChannel, "职工之家");
					ArrayValues.add(new BasicNameValuePair(chn, modelSign));

					ArrayValues.add(new BasicNameValuePair("datakey", "" + Id));

					new Thread(new Runnable() { // 开启线程上传文件
						@Override
						public void run() {
							String DueData = "";
							DueData = HttpGetData.GetData("api/cms/common/toDown", ArrayValues);

						}
					}).start();
				}
				if (true) {
					final ArrayList ArrayValues = new ArrayList();
					// ArrayValues.add(new BasicNameValuePair("ticket",
					// ticket));
					// ArrayValues.add(new BasicNameValuePair("applyType", "" +
					// 2));
					// ArrayValues.add(new BasicNameValuePair("helpSType", "" +
					// type));
					// ArrayValues.add(new BasicNameValuePair("modelSign",
					// "KNDY_APPLY"));
					// ArrayValues.add(new BasicNameValuePair("curPage", "" +
					// curPage));
					// ArrayValues.add(new BasicNameValuePair("pageSize", "" +
					// pageSize));
					// final ArrayList ArrayValues = new ArrayList();
					ArrayValues.add(new BasicNameValuePair("ticket", "" + ticket));
					// chn = GetChannelByKey.GetSign(PreALLChannel, "职工之家");
					ArrayValues.add(new BasicNameValuePair(chn, modelSign));

					ArrayValues.add(new BasicNameValuePair("datakey", "" + Id));
					ArrayValues.add(new BasicNameValuePair("content", "" + temp));

					new Thread(new Runnable() { // 开启线程上传文件
						@Override
						public void run() {
							String DueData = "";
							DueData = HttpGetData.GetData(curl, ArrayValues);
							Message msg = new Message();
							msg.obj = DueData;
							msg.what = 9;
							uiHandler.sendMessage(msg);
						}
					}).start();
				}

			}
			break;
		default:
			break;
		}
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void click(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.lin_all:
			TipsDetailModel data = list.get((Integer) v.getTag());
			if (data.getType() == 1) {
				// Intent intent = new Intent();
				// intent.setClass(getApplicationContext(),
				// SpecialDetailActivity.class);
				// // Bundle bundle = new Bundle();
				// // bundle.putString("Title", data.getTitle());
				// // bundle.putString("Time", data.getTime());
				// // bundle.putString("detail", data.getContent());
				// // bundle.putString("chn", chn);
				// // bundle.putString("Id", data.getId());
				// // intent.putExtras(bundle);
				// startActivity(intent);
				//
				// Toast.makeText(getApplicationContext(), "删除第" + (Integer)
				// v.getTag() + "条", Toast.LENGTH_SHORT).show();
				// Intent intent = new Intent();
				// intent.setClass(getApplicationContext(),
				// StandardImageXML.class);
				// Bundle bundle = new Bundle();
				// bundle.putString("url", data.getImage());
				// bundle.putInt("inturl", R.drawable.logo);
				//
				// intent.putExtras(bundle);
				// startActivity(intent);
				Intent intent = new Intent();
				Bundle bundle = new Bundle();
				bundle.putInt("number", number);
				bundle.putInt("page", data.getNumber());
				intent.putExtras(bundle);
				intent.setClass(getApplicationContext(), imageshow.class);
				startActivity(intent);
			}
			break;
		default:
			break;
		}
	}
}
