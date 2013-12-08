package com.example.shoppinglist.adapters;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.shoppinglist.R;
import com.example.shoppinglist.sqlite.helper.DatabaseHelper;
import com.example.shoppinglist.sqlite.model.ProductModel;

public class AdapterProductList extends ArrayAdapter<ProductModel> {

	Context context;
	int layoutResourceId;
	List<ProductModel> data = null;
	DatabaseHelper db;
	
	public AdapterProductList(Context context, int layoutResourceId,
			List<ProductModel> data) {
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
			holder.name_product = (TextView) row.findViewById(R.id.name_product);
			holder.amount = (TextView) row.findViewById(R.id.amount);
			row.setTag(holder);
			
		} else {
			holder = (RowBeanHolder) row.getTag();
		}

		ProductModel object = data.get(position);
		holder.name_product.setText(db.getBarCode(object.getIDBarCode()).getName());
		holder.amount.setText(object.getAmount());
	
	
	return row;
	}
	
	
	
	static class RowBeanHolder {
		TextView name_product;
		TextView amount;
		}
}
 
