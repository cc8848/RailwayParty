package wuxc.single.railwayparty.branch;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.R.transition;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.inputmethod.InputMethodManager;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import wuxc.single.railwayparty.BranchFragment;
import wuxc.single.railwayparty.R;
import wuxc.single.railwayparty.adapter.TalkAdapter;
import wuxc.single.railwayparty.internet.HttpGetData;
import wuxc.single.railwayparty.internet.URLcontainer;
import wuxc.single.railwayparty.internet.UpLoadFile;
import wuxc.single.railwayparty.model.TalkModel;
import wuxc.single.railwayparty.model.TalkModel;
import wuxc.single.railwayparty.start.SpecialDetailActivity;
import wuxc.single.railwayparty.start.webview;

public class FragmentPartygrouptalk extends Fragment implements OnTouchListener, OnClickListener, OnItemClickListener {
	private ListView ListData;
	List<TalkModel> list = new ArrayList<TalkModel>();
	List<TalkModel> listshow = new ArrayList<TalkModel>();
	private static TalkAdapter mAdapter;
	private int firstItemIndex = 0;
	private int lastItemIndex = 0;
	private float startY = 0;
	private float startYfoot = 0;
	private boolean isRecored;
	private boolean isRecoredfoot;
	private int pageSize = 10;
	private int totalPage = 10;
	private int curPage = 1;
	private final static int RATIO = 2;
	private TextView headTextView = null;
	private View view;// 缓存Fragment view
	// private boolean[] type = { true, true, false, false, false, false, false,
	// false, false, false, false, false };
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
	private EditText edit_comment;
	private Button btn_comment;
	private ImageView image;
	private String fileInfo = "";
	public Handler uiHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case GET_DUE_DATA:
				GetDataDueData(msg.obj);
				break;
			case 9:
				curPage = 1;
				GetData();
				break;
			case 1:
				GetDataAttachment(msg.obj);

				break;
			default:
				break;
			}
		}
	};

	protected void GetDataAttachment(Object obj) {

		// TODO Auto-generated method stub
		String state = null;

		try {
			JSONObject demoJson = new JSONObject(obj.toString());
			state = demoJson.getString("state");
			fileInfo = demoJson.getString("fileInfo");
			if (state.equals("1")) {

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
				ArrayValues.add(new BasicNameValuePair("chatInfoDto.par_keyid", BranchFragment.id));
				Log.e("par_keyid", BranchFragment.id);
				JSONObject jsonObject = new JSONObject();
				try {
					jsonObject.put("msgType", "image");
					jsonObject.put("data", fileInfo);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				ArrayValues.add(new BasicNameValuePair("chatInfoDto.content", "" + jsonObject));

				new Thread(new Runnable() { // 开启线程上传文件
					@Override
					public void run() {
						String DueData = "";
						DueData = HttpGetData.GetData("api/pb/chatInfo/save", ArrayValues);
						Message msg = new Message();
						msg.obj = DueData;
						msg.what = 9;
						uiHandler.sendMessage(msg);
					}
				}).start();

			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			// Toast.makeText(getActivity(), "", 0).show();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	protected void GetDataDueData(Object obj) {

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
				GetPager(Data);
				GetDataList(Data, curPage);
			} else if (Type.equals(GET_FAIL_RESULT)) {
				// Toast.makeText(getActivity(), "服务器数据失败",
				// Toast.LENGTH_SHORT).show();
			} else {
				// Toast.makeText(getActivity(), "数据格式校验失败",
				// Toast.LENGTH_SHORT).show();
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private void GetDataList(String data, int arg) {
		;
		if (arg == 1) {
			list.clear();
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
					json_data = json_data.getJSONObject("data");
					TalkModel listinfo = new TalkModel();
					// try {
					// listinfo.setBi(json_data.getInt("iszengread"));
					// } catch (Exception e) {
					// // TODO: handle exception
					// listinfo.setBi(0);
					// }
					// listinfo.setTime(json_data.getString("releaseDate"));
					// listinfo.setTitle(json_data.getString("title"));
					// listinfo.setId(json_data.getString("keyid"));
					// //
					// listinfo.setBackGround(json_data.getString("sacleImage"));
					// listinfo.setContent(json_data.getString("summary"));
					// listinfo.setSummary(json_data.getString("summary"));
					// listinfo.setCont(true);
					// listinfo.setGuanzhu("231");
					// listinfo.setZan("453");
					// // listinfo.setImageurl(headimg[i]);
					// listinfo.setHeadimgUrl(json_data.getString("userPhoto"));
					// listinfo.setRead(true);
					listinfo.setName(json_data.getString("user_name"));
					if (json_data.getString("senderId").equals(ticket)) {
						listinfo.setMy(true);
					} else {
						listinfo.setMy(false);
					}
					listinfo.setImage("");
					listinfo.setImageUrl(json_data.getString("userPhoto"));
					json_data = new JSONObject(json_data.getString("content"));
					// json_data =
					// json_data.getJSONObject(json_data.getString("content"));
					listinfo.setDetail(json_data.getString("data"));
					try {
						json_data = new JSONObject(json_data.getString("data"));
						// json_data =
						// json_data.getJSONObject(json_data.getString("content"));
						listinfo.setImage(json_data.getString("filePath"));
						listinfo.setPic(true);
					} catch (Exception e) {
						// TODO: handle exception
					}

					list.add(listinfo);
					// try {
					// listinfo.setLink(json_data.getString("otherLinks"));
					// if (json_data.getString("summary").equals("") ||
					// json_data.getString("summary") == null
					// || json_data.getString("summary").equals("null")) {
					// listinfo.setContent(json_data.getString("source"));
					// listinfo.setCont(false);
					// }
					//
					// } catch (Exception e) {
					// // TODO: handle exception
					// }

				}
			}

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int i = 0; i < list.size(); i++) {
			TalkModel talkModel = list.get(list.size() - 1 - i);
			listshow.add(talkModel);
		}
		if (arg == 1) {
			go();
		} else {
			mAdapter.notifyDataSetChanged();
		}

	}

	private void GetPager(String pager) {
		// TODO Auto-generated method stub
		try {
			JSONObject demoJson = new JSONObject(pager);

			totalPage = demoJson.getInt("totalPage");

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
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
		if (null != view) {
			ViewGroup parent = (ViewGroup) view.getParent();
			if (null != parent) {
				parent.removeView(view);
			}
		} else {
			view = inflater.inflate(R.layout.wuxc_a_only_list3, container, false);
			initview(view);
			setonclicklistener();
			setheadtextview();
			PreUserInfo = getActivity().getSharedPreferences("UserInfo", Context.MODE_PRIVATE);
			ReadTicket();
			GetData();
			// getdatalist(curPage);
		}

		return view;

	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		// TODO Auto-generated method stub
		// TalkModel data = list.get(position - 1);
		// if (true) {
		// Intent intent = new Intent();
		// intent.setClass(getActivity(), SpecialDetailActivity.class);
		// Bundle bundle = new Bundle();
		// bundle.putString("Title", data.getTitle());
		// bundle.putString("Time", data.getTime());
		// bundle.putString("detail", data.getContent());
		// bundle.putString("chn", chn);
		// bundle.putString("Id", data.getId());
		// intent.putExtras(bundle);
		// startActivity(intent);
		// } else {
		// Intent intent = new Intent();
		// intent.setClass(getActivity(), webview.class);
		// Bundle bundle = new Bundle();
		// bundle.putString("url", data.getLink());
		// // // bundle.putString("Time", "2016-11-23");
		// // // bundle.putString("Name", "小李");
		// // // bundle.putString("PageTitle", "收藏详情");
		// // // bundle.putString("Detail",
		// // //
		// //
		// "中国共产主义青年团，简称共青团，原名中国社会主义青年团，是中国共产党领导的一个由信仰共产主义的中国青年组成的群众性组织。共青团中央委员会受中共中央委员会领导，共青团的地方各级组织受同级党的委员会领导，同时受共青团上级组织领导。1922年5月，团的第一次代表大会在广州举行，正式成立中国社会主义青年团，1925年1月26日改称中国共产主义青年团。1959年5月4日共青团中央颁布共青团团徽。");
		// intent.putExtras(bundle);
		// startActivity(intent);
		// }
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
		ArrayValues.add(new BasicNameValuePair("par_keyid", BranchFragment.id));
		Log.e("par_keyid", BranchFragment.id);
		ArrayValues.add(new BasicNameValuePair("curPage", "" + curPage));
		ArrayValues.add(new BasicNameValuePair("pageSize", "" + pageSize));

		new Thread(new Runnable() { // 开启线程上传文件
			@Override
			public void run() {
				String DueData = "";
				DueData = HttpGetData.GetData("api/pb/chatInfo/getListJsonData", ArrayValues);
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

	private void initview(View view2) {
		// TODO Auto-generated method stub
		ListData = (ListView) view.findViewById(R.id.list_data);
		edit_comment = (EditText) view.findViewById(R.id.edit_comment);
		btn_comment = (Button) view.findViewById(R.id.btn_comment);
		btn_comment.setOnClickListener(this);
		image = (ImageView) view.findViewById(R.id.image);
		image.setOnClickListener(this);
	}

	private void setonclicklistener() {
		// TODO Auto-generated method stub
		ListData.setOnItemClickListener(this);
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// // TODO Auto-generated method stub
		// float tempY = event.getY();
		// float tempyfoot = event.getY();
		// firstItemIndex = ListData.getFirstVisiblePosition();
		// lastItemIndex = ListData.getLastVisiblePosition();
		// // Toast.makeText(getActivity(), " lastItemIndex" +
		// // lastItemIndex, Toast.LENGTH_SHORT).show();
		// switch (event.getAction()) {
		// case MotionEvent.ACTION_DOWN:
		// case MotionEvent.ACTION_MOVE:
		// if (!isRecored && (firstItemIndex == 0)) {
		// isRecored = true;
		// startY = tempY;
		// }
		// int temp = 1;
		// temp = (lastItemIndex) % pageSize;
		// if (!isRecoredfoot && (temp == 0)) {
		// isRecoredfoot = true;
		// startYfoot = tempyfoot;
		// }
		// break;
		// case MotionEvent.ACTION_UP:
		// case MotionEvent.ACTION_CANCEL:
		// isRecored = false;
		// isRecoredfoot = false;
		// break;
		//
		// default:
		// break;
		// }
		//
		// switch (event.getAction()) {
		// case MotionEvent.ACTION_DOWN:
		// break;
		// case MotionEvent.ACTION_UP:
		// case MotionEvent.ACTION_CANCEL:
		// // ListData.setPadding(0, 0, 0, 0);
		// if (tempY - startY < 400) {
		// // ListData.setPadding(0, -100, 0, 0);
		// } else {
		// curPage++;
		// if (curPage > totalPage) {
		// // Toast.makeText(getActivity(), " 没有更多了",
		// // Toast.LENGTH_SHORT).show();
		// // // listinfoagain();
		// } else {
		// // Toast.makeText(getActivity(), "正在刷新下一页",
		// // Toast.LENGTH_SHORT).show();
		// // GetData();
		// }
		//
		// }
		// int temp = 1;
		// temp = (lastItemIndex) % pageSize;
		// // temp = 0;
		// if (temp == 0 && (startYfoot - tempyfoot > 400)) {
		// curPage = 1;
		// if (curPage > totalPage) {
		// // Toast.makeText(getActivity(), " 没有更多了",
		// // Toast.LENGTH_SHORT).show();
		// // // listinfoagain();
		// } else {
		// // GetData();
		// // Toast.makeText(getActivity(), "正在刷新",
		// // Toast.LENGTH_SHORT).show();
		// }
		//
		// } else {
		//
		// }
		// break;
		// case MotionEvent.ACTION_MOVE:
		// if (isRecored && tempY > startY) {
		// ListData.setPadding(0, (int) ((tempY - startY) / RATIO - 100), 0, 0);
		// }
		// if (isRecoredfoot && startYfoot > tempyfoot) {
		// // footTextView.setVisibility(View.VISIBLE);
		// ListData.setPadding(0, -100, 0, (int) ((startYfoot - tempyfoot) /
		// RATIO));
		// }
		// break;
		//
		// default:
		// break;
		// }
		return false;
	}

	// @Override
	// public void onItemClick(AdapterView<?> parent, View view, int position,
	// long id) {
	// // TODO Auto-generated method stub
	// // recommendModel data = list.get(position - 1);
	// // Intent intent = new Intent();
	// // intent.setClass(getActivity(), SpecialDetailActivity.class);
	// // Bundle bundle = new Bundle();
	// // bundle.putString("Title", data.getTitle());
	// // bundle.putString("detail", data.getDetail());
	// // bundle.putString("Time", data.getTime());
	// // bundle.putString("Name", "名字");
	// // intent.putExtras(bundle);
	// // startActivity(intent);
	// Toast.makeText(getActivity(), "点击第" + position + "条" + "item",
	// Toast.LENGTH_SHORT).show();
	// }

	private void setheadtextview() {
		headTextView = new TextView(getActivity());
		headTextView.setGravity(Gravity.CENTER);
		headTextView.setMinHeight(100);
		headTextView.setText("");
		headTextView.setTypeface(Typeface.DEFAULT_BOLD);
		headTextView.setTextSize(15);
		headTextView.invalidate();
		ListData.addHeaderView(headTextView, null, false);
		ListData.setPadding(0, -100, 0, 0);
		ListData.setOnTouchListener(this);
	}

	private void getdatalist(int arg) {
		if (arg == 1) {
			list.clear();
		}
		// TODO Auto-generated method stub

		try {

			for (int i = 0; i < 10; i++) {

				TalkModel listinfo = new TalkModel();
				listinfo.setName("张超");
				listinfo.setDetail("这是一条消息");
				listinfo.setImageUrl("");
				// listinfo.setMy(type[i]);
				list.add(listinfo);

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (arg == 1) {
			go();
		} else {
			mAdapter.notifyDataSetChanged();
		}

	}

	protected void go() {
		ListData.setPadding(0, -100, 0, 0);
		mAdapter = new TalkAdapter(getActivity(), listshow, ListData);
		ListData.setAdapter(mAdapter);
		ListData.setTranscriptMode(ListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);

		ListData.setSelection(mAdapter.getCount() - 1);
		InputMethodManager inputManager =

				(InputMethodManager) edit_comment.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);

		inputManager.hideSoftInputFromWindow(edit_comment.getWindowToken(), 0);
		edit_comment.setText("");
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

	}

	@Override
	public void onStart() {
		super.onStart();
	}

	@Override
	public void onResume() {
		super.onResume();
	}

	@Override
	public void onPause() {
		super.onPause();
	}

	@Override
	public void onStop() {
		super.onStop();
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
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
		case R.id.btn_comment:
			if (edit_comment.getText().toString().equals("") || edit_comment.getText().toString() == null) {

			} else {
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
				ArrayValues.add(new BasicNameValuePair("chatInfoDto.par_keyid", BranchFragment.id));
				Log.e("par_keyid", BranchFragment.id);
				JSONObject jsonObject = new JSONObject();
				try {
					jsonObject.put("msgType", "text");
					jsonObject.put("data", edit_comment.getText().toString());
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				ArrayValues.add(new BasicNameValuePair("chatInfoDto.content", "" + jsonObject));

				new Thread(new Runnable() { // 开启线程上传文件
					@Override
					public void run() {
						String DueData = "";
						DueData = HttpGetData.GetData("api/pb/chatInfo/save", ArrayValues);
						Message msg = new Message();
						msg.obj = DueData;
						msg.what = 9;
						uiHandler.sendMessage(msg);
					}
				}).start();
			}

			break;
		case R.id.image:
			Intent intent = null;
			// if (Build.VERSION.SDK_INT < 19) {
			intent = new Intent(Intent.ACTION_GET_CONTENT);
			intent.setType("image/*");
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

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (data == null)
			return;
		Bundle bundle = data.getExtras();
		switch (requestCode) {

		case 0:
			// 发送选择的文件
			if (data != null) {
				Uri uri = data.getData();
				if (uri != null) {
					final File file = GetFile(uri);
					// text_load.setVisibility(View.VISIBLE);
					if (!(file == null)) {
						new Thread(new Runnable() { // 开启线程上传文件
							@Override
							public void run() {
								String UpLoadResult = UpLoadFile.uploadFileatt(file,
										URLcontainer.urlip + "console/pb/chatgroupfileUpload/uploadMultiple",
										BranchFragment.id, "" + ticket);
								Message msg = new Message();
								msg.what = 1;
								msg.obj = UpLoadResult;
								uiHandler.sendMessage(msg);
							}
						}).start();
					}

				}
			}

			break;
		default:
			break;
		}
	}

	/**
	 * 获取文件
	 * 
	 * @param uri
	 */
	private File GetFile(Uri uri) {
		String filePath = null;
		if ("content".equalsIgnoreCase(uri.getScheme())) {
			String[] projection = { "_data" };
			Cursor cursor = null;

			try {
				cursor = getActivity().getContentResolver().query(uri, projection, null, null, null);
				int column_index = cursor.getColumnIndexOrThrow("_data");
				if (cursor.moveToFirst()) {
					filePath = cursor.getString(column_index);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if ("file".equalsIgnoreCase(uri.getScheme())) {
			filePath = uri.getPath();
		}
		File file = new File(filePath);
		if (file == null || !file.exists()) {

			Toast.makeText(getActivity(), "文件不存在", 0).show();
			return file;
		}
		if (file.length() > 20 * 1024 * 1024) {

			Toast.makeText(getActivity(), "文件不能大于20M", 0).show();
			return null;
		}
		return file;
	}
}
