package wuxc.single.railwayparty.branch;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ListActivity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import wuxc.single.railwayparty.R;
import wuxc.single.railwayparty.adapter.MemberAdapter;
import wuxc.single.railwayparty.internet.HttpGetData;
import wuxc.single.railwayparty.model.MemberModel;
import wuxc.single.railwayparty.model.PartyBranchDataListModel;
import wuxc.single.railwayparty.other.PDFOutlineElement;

public class PartyOrgActivity extends ListActivity implements OnClickListener, OnTouchListener, OnItemClickListener {
	private ListView ListData;
	List<MemberModel> list = new ArrayList<MemberModel>();
	List<PartyBranchDataListModel> listPartyBranchDataListModel = new ArrayList<PartyBranchDataListModel>();
	private static MemberAdapter mAdapter;
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
	private boolean[] read = { false, false, false, true, true, true, true, true, true, true, true, true, true, true,
			true, true, true, true, true, true };
	private String ticket="";
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
	private TextView text_1;
	private TextView text_2;
	private TextView text_3;
	private TextView text_4;
	private TextView text_5;
	private TextView text_6;
	private TextView text_7;
	private String fileClassify = "";
	private ArrayList<PDFOutlineElement> mPdfOutlinesCount = new ArrayList<PDFOutlineElement>();
	private ArrayList<PDFOutlineElement> mPdfOutlines = new ArrayList<PDFOutlineElement>();
	private TreeViewAdapter treeViewAdapter = null;
	public Handler uiHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case GET_DUE_DATA:
				GetDataDueData(msg.obj);
				break;
			default:
				break;
			}
		}
	};

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
				Toast.makeText(this, "服务器数据失败", Toast.LENGTH_SHORT).show();
			} else {
				Toast.makeText(this, "数据格式校验失败", Toast.LENGTH_SHORT).show();
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
				// / Toast.makeText(this, "无数据",
				// Toast.LENGTH_SHORT).show();

			} else {
				for (int i = 0; i < jArray.length(); i++) {
					json_data = jArray.getJSONObject(i);
					Log.e("json_data", "" + json_data);
					// JSONObject jsonObject = json_data.getJSONObject("data");
					PartyBranchDataListModel listinfo = new PartyBranchDataListModel();
					listinfo.setPartyName(json_data.getString("name"));
					listinfo.setPid(json_data.getString("pId"));
					listinfo.setId(json_data.getString("id"));
					listinfo.setHaschild(false);
					listinfo.setHasparent(false);
					listPartyBranchDataListModel.add(listinfo);

				}
			}

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int i = 0; i < listPartyBranchDataListModel.size(); i++) {
			PartyBranchDataListModel listinfo = listPartyBranchDataListModel.get(i);
			String id = listinfo.getId();
			for (int j = i; j < listPartyBranchDataListModel.size(); j++) {
				PartyBranchDataListModel listinfo1 = listPartyBranchDataListModel.get(j);
				String pid = listinfo1.getPid();
				if (id.equals(pid)) {
					listinfo.setHaschild(true);
					listinfo1.setHasparent(true);
				}
			}
		}
		// initialData();
		if (listPartyBranchDataListModel.size() > 0) {
			PartyBranchDataListModel listinfo = listPartyBranchDataListModel.get(0);

			PDFOutlineElement pdfOutlineElement = new PDFOutlineElement(listinfo.getId(), listinfo.getPartyName(),
					listinfo.isHasparent(), listinfo.isHaschild(), listinfo.getPid(), 0, false);

			mPdfOutlinesCount.add(pdfOutlineElement);
		}
		for (int i = 0; i < listPartyBranchDataListModel.size(); i++) {
			PartyBranchDataListModel listinfo = listPartyBranchDataListModel.get(i);
			Log.e("listinfo", listinfo.getstring());
			PDFOutlineElement pdfOutlineElement = new PDFOutlineElement(listinfo.getId(), listinfo.getPartyName(),
					listinfo.isHasparent(), listinfo.isHaschild(), listinfo.getPid(), 0, false);
			mPdfOutlines.add(pdfOutlineElement);
		}

		treeViewAdapter = new TreeViewAdapter(this, R.layout.outline, mPdfOutlinesCount);
		setListAdapter(treeViewAdapter);
		registerForContextMenu(getListView());
	}

	private class TreeViewAdapter extends ArrayAdapter {

		public TreeViewAdapter(Context context, int textViewResourceId, List objects) {
			super(context, textViewResourceId, objects);
			mInflater = LayoutInflater.from(context);
			mfilelist = objects;
			mIconCollapse = BitmapFactory.decodeResource(context.getResources(), R.drawable.add);
			mIconExpand = BitmapFactory.decodeResource(context.getResources(), R.drawable.jian);

		}

		private LayoutInflater mInflater;
		private List<PDFOutlineElement> mfilelist;
		private Bitmap mIconCollapse;
		private Bitmap mIconExpand;

		public int getCount() {
			return mfilelist.size();
		}

		public Object getItem(int position) {
			return position;
		}

		public long getItemId(int position) {
			return position;
		}

		public View getView(int position, View convertView, ViewGroup parent) {

			ViewHolder holder;
			/* if (convertView == null) { */
			convertView = mInflater.inflate(R.layout.outline, null);
			holder = new ViewHolder();
			holder.text = (TextView) convertView.findViewById(R.id.text);
			holder.icon = (ImageView) convertView.findViewById(R.id.icon);
			convertView.setTag(holder);
			/*
			 * } else { holder = (ViewHolder) convertView.getTag(); }
			 */

			int level = mfilelist.get(position).getLevel();
			holder.icon.setPadding(50 * (level + 1), holder.icon.getPaddingTop(), 0, holder.icon.getPaddingBottom());
			holder.text.setText(mfilelist.get(position).getOutlineTitle());
			if (mfilelist.get(position).isMhasChild() && (mfilelist.get(position).isExpanded() == false)) {
				holder.icon.setImageBitmap(mIconCollapse);
			} else if (mfilelist.get(position).isMhasChild() && (mfilelist.get(position).isExpanded() == true)) {
				holder.icon.setImageBitmap(mIconExpand);
			} else if (!mfilelist.get(position).isMhasChild()) {
				holder.icon.setImageBitmap(mIconCollapse);
				holder.icon.setVisibility(View.INVISIBLE);
			}
			return convertView;
		}

		class ViewHolder {
			TextView text;
			ImageView icon;

		}
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		if (!mPdfOutlinesCount.get(position).isMhasChild()) {
			Toast.makeText(this, mPdfOutlinesCount.get(position).getOutlineTitle(), 2000);
			/*
			 * int pageNumber; Intent i = getIntent(); PDFOutlineElement element
			 * = mPdfOutlinesCount .get(position); pageNumber =
			 * element.getOutlineElement().pageNumber; if (pageNumber <= 0) {
			 * String name = element.getOutlineElement().destName; pageNumber =
			 * idocviewer.getPageNumberForNameForOutline(name);
			 * element.getOutlineElement().pageNumber = pageNumber;
			 * element.getOutlineElement().destName = null; }
			 * i.putExtra("PageNumber", pageNumber); setResult(RESULT_OK, i);
			 * finish();
			 */

			return;
		}

		if (mPdfOutlinesCount.get(position).isExpanded()) {
			mPdfOutlinesCount.get(position).setExpanded(false);
			PDFOutlineElement pdfOutlineElement = mPdfOutlinesCount.get(position);
			ArrayList<PDFOutlineElement> temp = new ArrayList<PDFOutlineElement>();

			for (int i = position + 1; i < mPdfOutlinesCount.size(); i++) {
				if (pdfOutlineElement.getLevel() >= mPdfOutlinesCount.get(i).getLevel()) {
					break;
				}
				temp.add(mPdfOutlinesCount.get(i));
			}

			mPdfOutlinesCount.removeAll(temp);

			treeViewAdapter.notifyDataSetChanged();
			/*
			 * fileExploreAdapter = new TreeViewAdapter(this, R.layout.outline,
			 * mPdfOutlinesCount);
			 */

			// setListAdapter(fileExploreAdapter);

		} else {
			mPdfOutlinesCount.get(position).setExpanded(true);
			int level = mPdfOutlinesCount.get(position).getLevel();
			int nextLevel = level + 1;

			for (PDFOutlineElement pdfOutlineElement : mPdfOutlines) {
				int j = 1;
				if (pdfOutlineElement.getParent().equals(mPdfOutlinesCount.get(position).getId())) {
					pdfOutlineElement.setLevel(nextLevel);
					pdfOutlineElement.setExpanded(false);
					mPdfOutlinesCount.add(position + j, pdfOutlineElement);
					j++;
				}
			}
			treeViewAdapter.notifyDataSetChanged();
			/*
			 * fileExploreAdapter = new TreeViewAdapter(this, R.layout.outline,
			 * mPdfOutlinesCount);
			 */

			// setListAdapter(fileExploreAdapter);
		}
	}

	private String getdate(String string) {
		// TODO Auto-generated method stub
		String result = "07-28";
		try {
			String[] bStrings = string.split("-");
			result = bStrings[1] + "-" + bStrings[2];
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
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
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.wuxc_activity_branch_org);
		ImageView image_back = (ImageView) findViewById(R.id.image_back);
		image_back.setOnClickListener(this);
		initview();
		setonclicklistener();
		setheadtextview();
		// getdatalist(curPage);
		PreUserInfo = this.getSharedPreferences("UserInfo", Context.MODE_PRIVATE);
		ReadTicket();
		GetData();
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
		ArrayValues.add(new BasicNameValuePair("deptId", userPhoto));

		new Thread(new Runnable() { // 开启线程上传文件
			@Override
			public void run() {
				String DueData = "";
				DueData = HttpGetData.GetData("api/common/getAllOrg", ArrayValues);
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
		userPhoto = PreUserInfo.getString("deptId", "");
		LoginId = PreUserInfo.getString("userName", "");
	}

	private void setheadtextview() {
		headTextView = new TextView(getApplicationContext());
		headTextView.setGravity(Gravity.CENTER);
		headTextView.setMinHeight(100);
		headTextView.setText("正在刷新...");
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

			for (int i = 0; i < 0; i++) {

				MemberModel listinfo = new MemberModel();
				listinfo.setTime("2017-09-10");
				listinfo.setTitle("政工干部");
				listinfo.setName("yew");
				listinfo.setImageUrl("");
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
		mAdapter = new MemberAdapter(this, list, ListData);
		ListData.setAdapter(mAdapter);
	}

	private void setonclicklistener() {
		// TODO Auto-generated method stub
		ListData.setOnItemClickListener(this);
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		float tempY = event.getY();
		float tempyfoot = event.getY();
		firstItemIndex = ListData.getFirstVisiblePosition();
		lastItemIndex = ListData.getLastVisiblePosition();
		// Toast.makeText(getApplicationContext(), " lastItemIndex" +
		// lastItemIndex, Toast.LENGTH_SHORT).show();
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
		case MotionEvent.ACTION_MOVE:
			if (!isRecored && (firstItemIndex == 0)) {
				isRecored = true;
				startY = tempY;
			}
			int temp = 1;
			temp = (lastItemIndex) % pageSize;
			if (!isRecoredfoot && (temp == 0)) {
				isRecoredfoot = true;
				startYfoot = tempyfoot;
			}
			break;
		case MotionEvent.ACTION_UP:
		case MotionEvent.ACTION_CANCEL:
			isRecored = false;
			isRecoredfoot = false;
			break;

		default:
			break;
		}

		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			break;
		case MotionEvent.ACTION_UP:
		case MotionEvent.ACTION_CANCEL:
			ListData.setPadding(0, 0, 0, 0);
			if (tempY - startY < 400) {
				ListData.setPadding(0, -100, 0, 0);
			} else {
				curPage = 1;
				Toast.makeText(getApplicationContext(), "正在刷新", Toast.LENGTH_SHORT).show();
				getdatalist(pageSize);
			}
			int temp = 1;
			temp = (lastItemIndex) % pageSize;
			// temp = 0;
			if (temp == 0 && (startYfoot - tempyfoot > 400)) {
				curPage++;
				if (curPage > totalPage) {
					Toast.makeText(getApplicationContext(), " 没有更多了", Toast.LENGTH_SHORT).show();
					// // listinfoagain();
				} else {
					getdatalist(pageSize);
					Toast.makeText(getApplicationContext(), "正在加载下一页", Toast.LENGTH_SHORT).show();
				}

			} else {

			}
			break;
		case MotionEvent.ACTION_MOVE:
			if (isRecored && tempY > startY) {
				ListData.setPadding(0, (int) ((tempY - startY) / RATIO - 100), 0, 0);
			}
			if (isRecoredfoot && startYfoot > tempyfoot) {
				// footTextView.setVisibility(View.VISIBLE);
				ListData.setPadding(0, -100, 0, (int) ((startYfoot - tempyfoot) / RATIO));
			}
			break;

		default:
			break;
		}
		return false;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		// TODO Auto-generated method stub
		// recommendModel data = list.get(position - 1);
		// Intent intent = new Intent();
		// intent.setClass(getApplicationContext(),
		// SpecialDetailActivity.class);
		// Bundle bundle = new Bundle();
		// bundle.putString("Title", data.getTitle());
		// bundle.putString("detail", data.getDetail());
		// bundle.putString("Time", data.getTime());
		// bundle.putString("Name", "名字");
		// intent.putExtras(bundle);
		// startActivity(intent);
		Toast.makeText(getApplicationContext(), "点击第" + position + "条" + "item", Toast.LENGTH_SHORT).show();
	}

	private void initview() {
		// TODO Auto-generated method stub
		ListData = (ListView) findViewById(R.id.list_data);
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
