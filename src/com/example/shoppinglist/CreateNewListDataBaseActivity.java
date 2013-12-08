package com.example.shoppinglist;

import java.util.List;

import com.example.shoppinglist.adapters.AdapterProductList;
import com.example.shoppinglist.sqlite.helper.DatabaseHelper;
import com.example.shoppinglist.sqlite.model.ListModel;
import com.example.shoppinglist.sqlite.model.ProductModel;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.ListActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import android.support.v4.app.NavUtils;
import android.content.Intent;
import android.os.Build;

@SuppressLint("NewApi")
public class CreateNewListDataBaseActivity extends ListActivity {
	DatabaseHelper db;
	String name = null;
	EditText mEdit;
	ListModel newList;
	List<ProductModel> lpm;
	private ListView listView1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
//tu dopisac i dorobic!! koniecznie		    String value = extras.getString("new_variable_name");
		}
		setContentView(R.layout.activity_create_new_list_data_base);
		db = new DatabaseHelper(this);
		lpm = null;
		mEdit = (EditText) findViewById(R.id.listNameField);
		Log.d("nazwa listy", newList.getName());
		mEdit.setText(newList.getName());
		List<ProductModel> products = db.getAllProduct(-1);
		AdapterProductList adapter = new AdapterProductList(this,
                R.layout.product_list_item, products);
        listView1 = getListView();
        listView1.setAdapter(adapter);
	}

	/**
	 * Set up the {@link android.app.ActionBar}, if the API is available.
	 */
	@SuppressLint("NewApi")
	private void setupActionBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.create_new_list_data_base, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void saveNewList(View view) {
		name = mEdit.getText().toString();
		if (name != null) {
				newList.setName(name);
				long listID = db.createList(newList);
				List<ProductModel> products = db.getAllProduct(-1);
				
				for (ProductModel pdb : products) {
					if (pdb.getIDList() == -1) {
						pdb.setIDList(Integer.valueOf(String.valueOf(listID)));
						db.updateProduct(pdb);					}
				}
				
				NavUtils.navigateUpFromSameTask(this);
		
		}
		else{
			Toast toast = Toast.makeText(getApplicationContext(),
					"Pleas put name for list", Toast.LENGTH_SHORT);
			toast.show();
		}
	}
	
	public void openNewPageToScan(View view){
		Intent intent = new Intent(this, AddNewProduct.class);
		startActivity(intent);	
	}
	

}
