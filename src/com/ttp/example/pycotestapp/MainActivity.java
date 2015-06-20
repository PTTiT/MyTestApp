package com.ttp.example.pycotestapp;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.View;

import com.ttp.example.pycotestapp.fragment.DetailFragment;
import com.ttp.example.pycotestapp.fragment.EntriesFragment;
import com.ttp.example.pycotestapp.object.Entry;

public class MainActivity extends FragmentActivity {
	private ProgressDialog mProgress;
	private EntriesFragment entriesFragment;
	private DetailFragment detailFragment;
	private View entriesContainer, detailContainer;
	private boolean isTablet = false;
	private boolean isDetailShowed = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		isTablet = getResources().getBoolean(R.bool.is_tablet);
		setContentView(R.layout.activity_main);

		FragmentManager fm = getSupportFragmentManager();
		entriesFragment = (EntriesFragment) fm
				.findFragmentById(R.id.entries_container);
		detailFragment = (DetailFragment) fm
				.findFragmentById(R.id.detail_container);

		entriesContainer = findViewById(R.id.entries_container);
		detailContainer = findViewById(R.id.detail_container);

		if (!isTablet) {
			detailContainer.setVisibility(View.GONE);
		}
		if (entriesFragment.getSelectedEntry() != null) {
			showSelectedEntry(entriesFragment.getSelectedEntry());
		}
	}

	public void showSelectedEntry(Entry selectedEntry) {
		if (!isTablet) {
			entriesContainer.setVisibility(View.GONE);
			detailContainer.setVisibility(View.VISIBLE);
			isDetailShowed = true;
		}
		detailFragment.setContent(selectedEntry);
	}

	@Override
	public void onBackPressed() {
		if (!isTablet && isDetailShowed) {
			entriesContainer.setVisibility(View.VISIBLE);
			detailContainer.setVisibility(View.GONE);
			entriesFragment.clearSelectedEntry();
			isDetailShowed = false;
		} else {
			super.onBackPressed();
		}
	}

	public void showProgressDialog(String message) {
		hideProgressDialog();
		mProgress = ProgressDialog.show(this, "", message);
	}

	public void hideProgressDialog() {
		if (mProgress != null && mProgress.isShowing()) {
			mProgress.dismiss();
		}
	}
}
