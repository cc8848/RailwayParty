package wuxc.single.railwayparty.my;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import wuxc.single.railwayparty.R;

public class phoneActivity extends Activity implements OnClickListener, OnTouchListener {
	private EditText editphone;
	private TextView textChar;
	private TextView textSave;
	private static final int MAX_COUNT = 11;
	private ImageView imageBack;
	private SharedPreferences PreUserInfo;// 存储个人信息
	private String phone = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.wuxc_activity_phone);
		// edit_motto
		// text_save
		// text_char
		// image_back
		editphone = (EditText) findViewById(R.id.edit_motto);
		editphone.addTextChangedListener(mTextWatcher);
		editphone.setSelection(editphone.length()); // 将光标移动最后一个字符后面
		textChar = (TextView) findViewById(R.id.text_char);
		textSave = (TextView) findViewById(R.id.text_save);
		imageBack = (ImageView) findViewById(R.id.image_back);
		textSave.setOnClickListener(this);
		textSave.setOnTouchListener(this);
		imageBack.setOnClickListener(this);
		PreUserInfo = getSharedPreferences("UserInfo", Context.MODE_PRIVATE);

		readinfo();
	}

	private TextWatcher mTextWatcher = new TextWatcher() {

		private int editStart;

		private int editEnd;

		public void afterTextChanged(Editable s) {
			editStart = editphone.getSelectionStart();
			editEnd = editphone.getSelectionEnd();

			// 先去掉监听器，否则会出现栈溢出
			editphone.removeTextChangedListener(mTextWatcher);

			// 注意这里只能每次都对整个EditText的内容求长度，不能对删除的单个字符求长度
			// 因为是中英文混合，单个字符而言，calculateLength函数都会返回1
			while (calculateLength(s.toString()) > MAX_COUNT) { // 当输入字符个数超过限制的大小时，进行截断操作
				s.delete(editStart - 1, editEnd);
				editStart--;
				editEnd--;
			}
			editphone.setText(s);
			editphone.setSelection(editStart);

			// 恢复监听器
			editphone.addTextChangedListener(mTextWatcher);

			setLeftCount();
		}

		public void beforeTextChanged(CharSequence s, int start, int count, int after) {

		}

		public void onTextChanged(CharSequence s, int start, int before, int count) {

		}

	};

	/**
	 * 计算分享内容的字数，一个汉字=两个英文字母，一个中文标点=两个英文标点 注意：该函数的不适用于对单个字符进行计算，因为单个字符四舍五入后都是1
	 * 
	 * @param c
	 * @return
	 */
	private long calculateLength(CharSequence c) {
		double len = 0;
		for (int i = 0; i < c.length(); i++) {
			int tmp = (int) c.charAt(i);
			if (tmp > 0 && tmp < 127) {
				len += 1;
			} else {
				len++;
			}
		}
		return Math.round(len);
	}

	/**
	 * 刷新剩余输入字数,最大值新浪微博是140个字，人人网是200个字
	 */
	private void setLeftCount() {
		textChar.setText(String.valueOf((MAX_COUNT - getInputCount())));
	}

	/**
	 * 获取用户输入的分享内容字数
	 * 
	 * @return
	 */
	private long getInputCount() {
		return calculateLength(editphone.getText().toString());
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.text_save:
			int action = event.getAction();
			if (action == MotionEvent.ACTION_DOWN) {
				textSave.setTextColor(Color.parseColor("#FF7F24"));
			} else if (action == MotionEvent.ACTION_UP) {
				textSave.setTextColor(Color.parseColor("#ffffff"));
			} else if (action == MotionEvent.ACTION_CANCEL) {
				textSave.setTextColor(Color.parseColor("#ffffff"));
			}
			break;

		default:
			break;
		}
		return false;

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.image_back:
			finish();
			break;
		case R.id.text_save:
			phone = editphone.getText().toString();
			if (phone.equals("")) {
				Toast.makeText(getApplicationContext(), "请设置签名", Toast.LENGTH_SHORT).show();

			} else {
				writephone();
				Intent intent = new Intent();
				setResult(0, intent);
				finish();
			}
			break;

		default:
			break;
		}
	}

	private void readinfo() {
		// TODO Auto-generated method stub
		phone = PreUserInfo.getString("PhoneNumber", "");
		editphone.setText(phone);
	}

	private void writephone() {
		// TODO Auto-generated method stub
		Editor edit = PreUserInfo.edit();
		edit.putString("PhoneNumber", phone);
		edit.commit();
	}

}
