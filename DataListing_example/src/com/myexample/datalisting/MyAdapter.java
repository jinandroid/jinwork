package com.deftsoft.datalisting;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;

import com.deftsoft.database.Bean;

public class MyAdapter extends BaseAdapter
{
	Context mContext;
	ArrayList<Bean> mList;
	LayoutInflater inf;

	public MyAdapter(Context mContext, ArrayList<Bean> mList)
	{
		this.mList = mList;
		this.mContext = mContext;
		inf = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	public void notifyData(ArrayList<Bean> mList) {
		this.mList = mList;
		notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		return mList.size();
	}

	@Override
	public Object getItem(int position) {
		return mList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent)
	{
		ViewHolder holder = new ViewHolder();
		if (convertView == null) 
		 {
			convertView = inf.inflate(R.layout.row_item, null);
			
			holder.txtName = (TextView) convertView.findViewById(R.id.txt_name);
			holder.txtAge = (TextView) convertView.findViewById(R.id.txt_age);
			holder.chk = (CheckBox) convertView.findViewById(R.id.cb);

			convertView.setTag(holder);

		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		if (mList.get(position).getName() != null) 
		{
			holder.txtName.setText(mList.get(position).getName());
			holder.txtAge.setText(mList.get(position).getAge());
		}
	
		holder.chk.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,	boolean isChecked) 
			{
				if (isChecked) 
				{
					mList.get(position).setStatus("1");
				} 
				else 
				{
					mList.get(position).setStatus("0");
					((ShowData) mContext).uncheckSelecAll();
				}

				if (((ShowData) mContext).getSelectAllvalue()) 
				{
					((ShowData) mContext).checkSelecAll();
				}

				notifyDataSetChanged();
			}
		});

		
		if (mList.get(position).getStatus().equalsIgnoreCase("1"))
			holder.chk.setChecked(true);
		else
			holder.chk.setChecked(false);

		return convertView;
	}
	
}

	class ViewHolder 
	{
		TextView txtName, txtAge;
		CheckBox chk;
	}