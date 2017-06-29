package wuxc.single.railwayparty.branch;

import java.util.ArrayList;
import java.util.Calendar;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.view.Window;
import wuxc.single.railwayparty.R;
import wuxc.single.railwayparty.layout.PartViewforage;

public class Statisticsforage extends Activity implements OnClickListener {

	private double[] data = new double[] { 600, 400, 500, 200, 100, 800 };
	private PartViewforage histogramView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.wuxc_activity_statistics_for_age);
		ImageView image_back = (ImageView) findViewById(R.id.image_back);
		image_back.setOnClickListener(this);
		histogramView = (PartViewforage) this.findViewById(R.id.histogram);

		histogramView.setProgress(data);
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
