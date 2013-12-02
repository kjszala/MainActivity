package com.example.shoppinglist.adapters;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.shoppinglist.R;
import com.example.shoppinglist.sqlite.helper.DatabaseHelper;
import com.example.shoppinglist.sqlite.model.ListModel;

public class AdapterListsList extends ArrayAdapter<ListModel> {

	Context context;
	int layoutResourceId;
	List<ListModel> data = null;
	DatabaseHelper db;
	
	public AdapterListsList(Context context, int layoutResourceId,
			List<ListModel> data) {
		super(context, layoutResourceId, data);
		this.layoutResourceId = layoutResourceId;
		this.context = context;
		this.data = data;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		RowBeanHolder holder = null;
		
		
		if (row == null) {
			LayoutInflater inflater = ((Activity) context).getLayoutInflater();
			row = inflater.inflate(layoutResourceId, parent, false);

			holder = new RowBeanHolder();
			holder.name_list = (TextView) row.findViewById(R.id.name_list);
			holder.btnDelete = (Button) row.findViewById(R.id.buttonDelete);
			holder.btnDelete.setTag(position);
			row.setTag(holder);
			
		} else {
			holder = (RowBeanHolder) row.getTag();
		}

		ListModel object = data.get(position);
		holder.name_list.setText(object.getName());

	
	holder.btnDelete.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			//DOPISAC!!!!
		}
	}); 
	return row;
	}
	
	
	
	static class RowBeanHolder {
		Button btnDelete;
		TextView name_list;
	}
}

