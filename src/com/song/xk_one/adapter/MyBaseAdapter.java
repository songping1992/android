package com.song.xk_one.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * adaper的父类
 */
public class MyBaseAdapter<T> extends BaseAdapter {

	private ArrayList<T> mArrayList;
	@SuppressWarnings("unused")
	private Context mContext;
	public LayoutInflater layoutInflater;
	protected IMyBaseAdapterCallBack imybaseAdapter;
	private int layout_id;

	public MyBaseAdapter(Context mContext, ArrayList<T> arrayList,
			int item_layout_id) {
		this.mArrayList = arrayList;
		this.mContext = mContext;
		this.layoutInflater = LayoutInflater.from(mContext);
		this.layout_id = item_layout_id;
	}

	@Override
	public int getCount() {
		return mArrayList.size();
	}

	@Override
	public Object getItem(int position) {
		return mArrayList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		IViewHolder iviewHolder = null;
		if (convertView == null) {
			convertView = layoutInflater.inflate(layout_id, null);
			iviewHolder = imybaseAdapter.initItemView(convertView);
			convertView.setTag(iviewHolder);
		} else {
			iviewHolder = (IViewHolder) convertView.getTag();
		}

		imybaseAdapter.handleItemView(iviewHolder, position);

		return convertView;
	}

	public interface IMyBaseAdapterCallBack {
		public IViewHolder initItemView(View mView);

		public void handleItemView(IViewHolder iviewHolder, int position);
	}

	public interface IViewHolder {

	}

}
