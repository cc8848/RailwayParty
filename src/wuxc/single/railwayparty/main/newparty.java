package wuxc.single.railwayparty.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import wuxc.single.railwayparty.R;

public class newparty extends Fragment {
	private View view;// 缓存Fragment view
	private WebView webview;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		if (null != view) {
			ViewGroup parent = (ViewGroup) view.getParent();
			if (null != parent) {
				parent.removeView(view);
			}
		} else {
			view = inflater.inflate(R.layout.newparty, container, false);
			initview(view);
			webview.loadUrl("http://www.12371.cn/special/xg19thjs/");
			WebSettings settings = webview.getSettings();
			settings.setJavaScriptEnabled(true);
			settings.setUseWideViewPort(true);
			settings.setLoadWithOverviewMode(true);
			settings.setTextSize(WebSettings.TextSize.NORMAL);
			webview.setWebViewClient(new WebViewClient() {
				@Override
				public boolean shouldOverrideUrlLoading(WebView view, String url) {

					webview.loadUrl(url); // 在当前的webview中跳转到新的url

					return true;
				}
			});

		}

		return view;
	}

	private void initview(View view2) {
		// TODO Auto-generated method stub
		webview = (WebView) view.findViewById(R.id.webview);
	}

}
