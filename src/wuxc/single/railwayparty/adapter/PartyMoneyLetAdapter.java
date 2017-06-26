package wuxc.single.railwayparty.adapter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import wuxc.single.railwayparty.R;
import wuxc.single.railwayparty.cache.PartyMoneyLetCache;
import wuxc.single.railwayparty.internet.ImageLoader;
import wuxc.single.railwayparty.model.PartyMoneyLetModel;;

public class PartyMoneyLetAdapter extends ArrayAdapter<PartyMoneyLetModel> {
	private ListView listView;
	private ImageLoader ImageLoader;
	private String imageurl = "";
	private int screenwidth = 0;
	private Activity thisactivity;

	public PartyMoneyLetAdapter(Activity activity, List<PartyMoneyLetModel> imageAndTexts, ListView listView) {
		super(activity, 0, imageAndTexts);
		this.listView = listView;
		this.thisactivity = activity;
		ImageLoader = new ImageLoader();

	}

	public View getView(int position, View convertView, ViewGroup parent) {
		Activity activity = (Activity) getContext();

		// Inflate the views from XML
		View rowView = convertView;
		PartyMoneyLetCache viewCache;
		if (rowView == null) {
			LayoutInflater inflater = activity.getLayoutInflater();

			rowView = inflater.inflate(R.layout.wuxc_item_vote_party_money_let, null);

			viewCache = new PartyMoneyLetCache(rowView);
			rowView.setTag(viewCache);
		} else {
			viewCache = (PartyMoneyLetCache) rowView.getTag();
		}
		PartyMoneyLetModel imageAndText = getItem(position);

		// Load the image and set it on the ImageView

		TextView TextTitle = viewCache.getTextTitle();
		TextTitle.setText("" + imageAndText.getTitle());

		return rowView;
	}

	public Bitmap getBitmapByPath(String fileName) {
		// String myJpgPath =
		// Environment.getExternalStorageDirectory()+"pepper/" + fileName;
		BitmapFactory.Options options = new BitmapFactory.Options();
		// options.inSampleSize = 12;
		Bitmap bm = BitmapFactory.decodeFile(fileName, options);
		return bm;
	}

	private String getBitName(String imageUrl) {
		// TODO Auto-generated method stub
		String[] temp = imageUrl.split("");
		String result = "";
		for (int i = 0; i < temp.length; i++) {
			if (temp[i].equals("/") || temp[i].equals(".")) {
				temp[i] = "";
			}
			result = result + temp[i];
		}
		return result;
	}

	public void saveMyBitmap(String bitName, Bitmap mBitmap) throws IOException {
		String path = Environment.getExternalStorageDirectory() + "/chat/";
		String myJpgPath = Environment.getExternalStorageDirectory() + "/chat/" + bitName + ".png";
		File tmp = new File(path);
		if (!tmp.exists()) {
			tmp.mkdir();
		}
		File f = new File(myJpgPath);
		f.createNewFile();
		FileOutputStream fOut = null;
		try {
			fOut = new FileOutputStream(f);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		mBitmap.compress(Bitmap.CompressFormat.PNG, 100, fOut);
		try {
			fOut.flush();
			fOut.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Bitmap cutBmp(Bitmap bmp) {
		Bitmap result;
		int w = bmp.getWidth();// 输入长方形宽
		int h = bmp.getHeight();// 输入长方形高
		int nw;// 输出正方形宽
		result = Bitmap.createBitmap(bmp, 15 * w / 100, 15 * h / 100, 7 * w / 10, 7 * h / 10);
		// }
		return result;
	}
}
