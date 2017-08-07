package com.example.newsapp.photo.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import wuxc.single.railwayparty.R;

public class AddImageGridAdapter extends BaseAdapter {
	// 定义Context
	private Context context;
	// 图片地址
	private List<Bitmap> imageList = new ArrayList<Bitmap>();
	private int screenwidth;

	public AddImageGridAdapter(Context context) {
		this.context = context;
	}

	public AddImageGridAdapter(Context context, List<Bitmap> imageList, int screenwidth) {
		this.context = context;
		this.imageList = imageList;
		this.screenwidth = screenwidth;
	}

	// 获取图片的个数
	public int getCount() {
		return imageList.size();
	}

	// 获取图片在库中的位置
	public Object getItem(int position) {
		return position;
	}

	// 获取图片ID
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = LayoutInflater.from(context).inflate(R.layout.wuxc_image_add_grid_item, null);
		ImageView imageView = (ImageView) view.findViewById(R.id.img_view);
		imageView.setImageBitmap(imageList.get(position));
		RelativeLayout.LayoutParams LayoutParams = (android.widget.RelativeLayout.LayoutParams) imageView
				.getLayoutParams();
		LayoutParams.height = screenwidth / 4;
		LayoutParams.width = screenwidth / 4;
		imageView.setLayoutParams(LayoutParams);
		return view;
	}

	public List<Bitmap> getImageList() {
		return imageList;
	}

}
