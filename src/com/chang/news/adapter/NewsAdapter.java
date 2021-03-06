package com.chang.news.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageLoader.ImageListener;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;
import com.chang.news.bean.NewsBean;
import com.chang.news.util.BitmapCache;
import com.chang.news.R;

public class NewsAdapter extends BaseAdapter
{
	private List<NewsBean> mList;
	private LayoutInflater mInflater;

	public static String[] URLS;

	ListView listView;
	
	RequestQueue mQueue;
	ImageLoader imageLoader;
	ImageListener listener;

	public NewsAdapter(Context context, List<NewsBean> data, ListView listView)
	{
		mList = data;
		this.listView = listView;
		mInflater = LayoutInflater.from(context);
		mQueue = Volley.newRequestQueue(context);
		imageLoader = new ImageLoader(mQueue, new BitmapCache());
	}

	@Override
	public int getCount()
	{
		return mList.size();
	}

	@Override
	public Object getItem(int position)
	{
		return mList.get(position);
	}

	@Override
	public long getItemId(int position)
	{
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		ViewHolder viewHolder;
		NewsBean newsBeam = mList.get(position);
		if(convertView == null)
		{
			viewHolder = new ViewHolder();
			convertView = mInflater.inflate(R.layout.item_layout, null);
			viewHolder.icon = (NetworkImageView) convertView.findViewById(R.id.id_icon);
			viewHolder.title = (TextView) convertView.findViewById(R.id.id_title);
			viewHolder.content = (TextView) convertView.findViewById(R.id.id_content);
			viewHolder.time = (TextView) convertView.findViewById(R.id.id_time);
			convertView.setTag(viewHolder);
			
		}
		else
		{
			viewHolder = (ViewHolder) convertView.getTag();
		}
		
		String url = newsBeam.newsIconUrl;
		if ("".equals(url) || url==null) {
			viewHolder.icon.setVisibility(View.GONE);
		} else {
			viewHolder.icon.setVisibility(View.VISIBLE);
			((NetworkImageView) viewHolder.icon).setImageUrl(url,imageLoader); 
		}
		
		viewHolder.title.setText(newsBeam.newsTitle);
		viewHolder.content.setText(newsBeam.newsContent);
		viewHolder.time.setText(newsBeam.newsTime);
		return convertView;
	}

	class ViewHolder
	{
		public TextView title;
		public TextView content;
		public TextView time;
		public ImageView icon;
	}

}
