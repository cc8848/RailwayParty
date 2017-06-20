package wuxc.single.railwayparty;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.view.ViewGroup;

public class MainFragment extends MainBaseFragment implements OnClickListener {
	private int screenwidth = 0;
	private ImageView ImageGobg;
	private RelativeLayout rel_main;
	private RelativeLayout main_top_bac;
	private RelativeLayout rel_1;
	private RelativeLayout rel_2;
	private RelativeLayout rel_3;
	private RelativeLayout rel_4;
	private LinearLayout lin_four;
	private ImageView headimg;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.wuxc_fragment_main, container, false);
		screenwidth = getActivity().getWindow().getWindowManager().getDefaultDisplay().getWidth();
		initview(view);
		initheight(view);
		return view;
	}

	private void initview(View view) {
		// TODO Auto-generated method stub
		ImageGobg = (ImageView) view.findViewById(R.id.image_go_zb);
		rel_main = (RelativeLayout) view.findViewById(R.id.rel_main);
		main_top_bac = (RelativeLayout) view.findViewById(R.id.main_top_bac);
		rel_1 = (RelativeLayout) view.findViewById(R.id.rel_1);
		rel_2 = (RelativeLayout) view.findViewById(R.id.rel_2);
		rel_3 = (RelativeLayout) view.findViewById(R.id.rel_3);
		rel_4 = (RelativeLayout) view.findViewById(R.id.rel_4);
		lin_four = (LinearLayout) view.findViewById(R.id.lin_four);
		headimg = (ImageView) view.findViewById(R.id.headimg);
	}

	private void initheight(View view) {
		// TODO Auto-generated method stub
		int height = (int) (screenwidth / 3.3);
		RelativeLayout.LayoutParams LayoutParams = (android.widget.RelativeLayout.LayoutParams) ImageGobg
				.getLayoutParams();
		LayoutParams.height = height;
		ImageGobg.setLayoutParams(LayoutParams);

		RelativeLayout.LayoutParams LayoutParams1 = (android.widget.RelativeLayout.LayoutParams) rel_main
				.getLayoutParams();
		LayoutParams1.bottomMargin = height;
		rel_main.setLayoutParams(LayoutParams1);
		height = (int) (screenwidth / 2.5);
		RelativeLayout.LayoutParams LayoutParams2 = (android.widget.RelativeLayout.LayoutParams) main_top_bac
				.getLayoutParams();
		LayoutParams2.height = height;
		main_top_bac.setLayoutParams(LayoutParams2);
		RelativeLayout.LayoutParams LayoutParams9 = (android.widget.RelativeLayout.LayoutParams) headimg
				.getLayoutParams();
		LayoutParams9.height = (int) (height / 2);
		LayoutParams9.width = (int) (height / 2);
		LayoutParams9.topMargin = height / 5;
		headimg.setLayoutParams(LayoutParams9);

		height = (int) (screenwidth / 2.1);
		RelativeLayout.LayoutParams LayoutParams3 = (android.widget.RelativeLayout.LayoutParams) lin_four
				.getLayoutParams();
		LayoutParams3.height = height;
		lin_four.setLayoutParams(LayoutParams3);

		height = (int) (screenwidth / 2.1 / 2) - 8;
		LinearLayout.LayoutParams LayoutParams4 = (android.widget.LinearLayout.LayoutParams) rel_1.getLayoutParams();
		LayoutParams4.height = height;
		LayoutParams4.width = height;
		rel_1.setLayoutParams(LayoutParams4);

		LinearLayout.LayoutParams LayoutParams5 = (android.widget.LinearLayout.LayoutParams) rel_2.getLayoutParams();
		LayoutParams5.height = height;
		LayoutParams5.width = height;
		rel_2.setLayoutParams(LayoutParams5);

		LinearLayout.LayoutParams LayoutParams6 = (android.widget.LinearLayout.LayoutParams) rel_3.getLayoutParams();
		LayoutParams6.height = height;
		LayoutParams6.width = height;
		rel_3.setLayoutParams(LayoutParams6);

		LinearLayout.LayoutParams LayoutParams7 = (android.widget.LinearLayout.LayoutParams) rel_4.getLayoutParams();
		LayoutParams7.height = height;
		LayoutParams7.width = height;
		rel_4.setLayoutParams(LayoutParams7);
	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();

		MainActivity.curFragmentTag = getString(R.string.str_main);

	}

	@Override
	public void onClick(View v) {
	}

}
