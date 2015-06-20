package com.ttp.example.pycotestapp.fragment;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.ttp.example.pycotestapp.R;
import com.ttp.example.pycotestapp.object.Entry;

public class DetailFragment extends Fragment {
	private WebView webView;
	private Entry entry;
	private ProgressBar loadingIndicator;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.frag_detail, container, false);
		webView = (WebView) view.findViewById(R.id.webview);
		loadingIndicator = (ProgressBar) view
				.findViewById(R.id.loading_indicator);

		webView.setWebViewClient(new WebViewClient() {

			@Override
			public void onPageFinished(WebView view, String url) {
				super.onPageFinished(view, url);
				loadingIndicator.setVisibility(View.GONE);
			}

			@Override
			public void onPageStarted(WebView view, String url, Bitmap favicon) {
				super.onPageStarted(view, url, favicon);
				loadingIndicator.setVisibility(View.VISIBLE);
			}
		});

		if (entry != null) {
			webView.loadUrl(entry.getLink());
		}
		return view;
	}

	public void setContent(Entry e) {
		entry = e;

		if (webView != null) {
			webView.loadUrl(entry.getLink());
		}
	}
}
