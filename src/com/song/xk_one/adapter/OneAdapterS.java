package com.song.xk_one.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.song.xk_one.R;
import com.song.xk_one.bean.TweetBean;
import com.song.xk_one.utils.VolleyUtils;

public class OneAdapterS extends MyBaseAdapter<TweetBean> {

	public OneAdapterS(final Context mContext, final ArrayList<TweetBean> arrayList, int item_layout_id) {
		super(mContext, arrayList, item_layout_id);
		this.imybaseAdapter = new IMyBaseAdapterCallBack() {

			@Override
			public com.song.xk_one.adapter.MyBaseAdapter.IViewHolder initItemView(View mView) {
				// TODO findviewbyId
				MyViewHolder myViewHolder = new MyViewHolder();
				myViewHolder.tv = (TextView) mView.findViewById(R.id.one_item_titleTvId);
				myViewHolder.iv = (NetworkImageView) mView.findViewById(R.id.one_item_ivId);
				return myViewHolder;
			}

			@Override
			public void handleItemView(com.song.xk_one.adapter.MyBaseAdapter.IViewHolder iviewHolder, int position) {
				// TODO UI修改
				TweetBean tweetBean = arrayList.get(position);
				MyViewHolder vHolder = (MyViewHolder) iviewHolder;
				vHolder.tv.setText(tweetBean.body);
				VolleyUtils.getInstance(mContext).setNetworkImage(tweetBean.portrait, vHolder.iv);
			}

			class MyViewHolder implements IViewHolder {
				public TextView tv;
				public NetworkImageView iv;
			}

		};
	}

}
