package com.ttp.example.pycotestapp.fragment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.ClientProtocolException;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.ttp.example.pycotestapp.MainActivity;
import com.ttp.example.pycotestapp.R;
import com.ttp.example.pycotestapp.object.Entry;
import com.ttp.example.pycotestapp.object.GetNewsResponseObject;
import com.ttp.example.pycotestapp.services.GetNewsService;
import com.ttp.example.pycotestapp.utils.AppUtils;
import com.ttp.example.pycotestapp.utils.ImageLoader;
import com.ttp.example.pycotestapp.utils.StringUtils;

public class EntriesFragment extends Fragment {
	private ListView entriesListView;
	private EntryAdapter entryAdapter;
	private MainActivity mActivity;

	private GetNewsTask getNewsTask;

	private ImageLoader imageLoader;

	private List<Entry> entriesList = new ArrayList<Entry>();
	private Entry selectedEntry;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setRetainInstance(true);
		getNewsTask = new GetNewsTask();
		getNewsTask.execute();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.frag_entries, container, false);
		entriesListView = (ListView) view.findViewById(R.id.lv_entries);
		entryAdapter = new EntryAdapter(getActivity(), R.layout.entry_item,
				entriesList);
		entriesListView.setAdapter(entryAdapter);

		entriesListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				selectedEntry = entryAdapter.getItem(position);
				mActivity.showSelectedEntry(selectedEntry);
			}
		});
		imageLoader = ImageLoader.getInstance(mActivity);
		return view;
	}

	public Entry getSelectedEntry() {
		return selectedEntry;
	}

	public void clearSelectedEntry() {
		selectedEntry = null;
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);

		if (activity instanceof MainActivity) {
			mActivity = ((MainActivity) activity);
		}
	}

	@Override
	public void onDetach() {
		super.onDetach();

		mActivity = null;
	}

	private class GetNewsTask extends
			AsyncTask<Void, Void, GetNewsResponseObject> {
		@Override
		protected void onPreExecute() {
			super.onPreExecute();

			mActivity.showProgressDialog("Loading...");
		}

		@Override
		protected void onPostExecute(GetNewsResponseObject result) {
			super.onPostExecute(result);

			mActivity.hideProgressDialog();

			if (result != null) {
				entriesList = result.getResponseData().getFeed().getEntries();
				entryAdapter.setItems(entriesList);
				entryAdapter.notifyDataSetChanged();
			}
		}

		@Override
		protected GetNewsResponseObject doInBackground(Void... params) {
			GetNewsResponseObject response = null;
			try {
				GetNewsService service = new GetNewsService();
				response = service.getResponseObject();
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return response;
		}
	}

	private class EntryAdapter extends ArrayAdapter<Entry> {
		private List<Entry> items;

		public EntryAdapter(Context context, int resource, List<Entry> objects) {
			super(context, resource, objects);
			setItems(objects);
		}

		@Override
		public int getCount() {
			return items.size();
		}

		public void setItems(List<Entry> stores) {
			items = stores;
		}

		@Override
		public Entry getItem(int position) {
			return items.get(position);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			final Entry item = getItem(position);
			View view = convertView;
			ViewHolder holder;
			if (view != null) {
				holder = (ViewHolder) view.getTag();
			} else {
				holder = new ViewHolder();
				view = LayoutInflater.from(getActivity()).inflate(
						R.layout.entry_item, null);
				holder.thumbnail = (ImageView) view
						.findViewById(R.id.img_thumbnail);
				holder.title = (TextView) view.findViewById(R.id.txt_title);
				holder.contentSnippet = (TextView) view
						.findViewById(R.id.txt_content_snippet);
				view.setTag(holder);
			}

			String thumbnailUrl = AppUtils.getThumbnailImageUrl(item);
			if (StringUtils.isNotEmpty(thumbnailUrl)) {
				imageLoader.displayImage(thumbnailUrl, holder.thumbnail, 200,
						200);
			}
			holder.title.setText(item.getTitle());
			holder.contentSnippet.setText(item.getContentSnippet());

			return view;
		}

		@Override
		public boolean isEmpty() {
			return items == null || items.isEmpty();
		}

		class ViewHolder {
			ImageView thumbnail;
			TextView title;
			TextView contentSnippet;
		}
	}
}
