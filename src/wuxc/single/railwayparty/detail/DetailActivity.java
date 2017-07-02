package wuxc.single.railwayparty.detail;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import wuxc.single.railwayparty.R;

public class DetailActivity extends Activity implements OnClickListener {
	private int screenwidth = 0;
	private int height = 1;
	private int width = 1;
	private ImageView image_content;
	private int source;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.wuxc_activity_detail);
		Intent intent = this.getIntent(); // 获取已有的intent对象
		Bundle bundle = intent.getExtras(); // 获取intent里面的bundle对象
		height = bundle.getInt("height");
		width = bundle.getInt("width");
		source = bundle.getInt("source");
		ImageView image_back = (ImageView) findViewById(R.id.image_back);
		image_back.setOnClickListener(this);
		image_content = (ImageView) findViewById(R.id.image_content);
		screenwidth = getWindow().getWindowManager().getDefaultDisplay().getWidth();
		LinearLayout.LayoutParams LayoutParams = (android.widget.LinearLayout.LayoutParams) image_content
				.getLayoutParams();
		LayoutParams.height = screenwidth * height / width;
		image_content.setLayoutParams(LayoutParams);
		try {
			image_content.setImageResource(source);
		} catch (Exception e) {
			// TODO: handle exception
		}
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
