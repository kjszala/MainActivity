package com.example.shoppinglist;

import java.util.List;

import com.example.shoppinglist.adapters.AdapterProductList;
import com.example.shoppinglist.sqlite.helper.DatabaseHelper;
import com.example.shoppinglist.sqlite.model.ListModel;
import com.example.shoppinglist.sqlite.model.ProductModel;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.ListActivity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.support.v4.app.NavUtils;
import android.content.Intent;
import android.os.Build;

@SuppressLint("NewApi")
public class CreateNewListDataBaseActivity extends ListActivity {
	DatabaseHelper db;
	String name = null;
	EditText mEdit;
	ListModel newList;
	List<ProductModel> products;
	private ListView listView1;
	long listID;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			String value = extras.getString("LIST_ID");
			listID = Long.valueOf(value);
		}
		
		setContentView(R.layout.activity_create_new_list_data_base);
		db = new DatabaseHelper(this);
		mEdit = (EditText) findViewById(R.id.listNameField);
		newList = db.getList(listID);
		mEdit.setText(newList.getName());
		
		products = db.getAllProduct(listID);		
		AdapterProductList adapter = new AdapterProductList(this,
                R.layout.product_list_item, products);
		adapter.notifyDataSetChanged();
        listView1 = getListView();
        registerForContextMenu(listView1);
        listView1.setAdapter(adapter);
        listView1.invalidateViews();
	}
	
    /**
     * Creates context menu on point list view
     */
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo){
            super.onCreateContextMenu(menu, v, menuInfo);
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.product_context_menu, menu);
    }
    
    
    /**
     * Sterring in context menu
     */
    @Override
    public boolean onContextItemSelected(MenuItem item){ 		
    	AdapterContextMenuInfo  adapterList = (AdapterContextMenuInfo)item.getMenuInfo();     
    	switch(item.getItemId()) {
    	case R.id.delete_item_context:
    		long helpID = Long.valueOf(String.valueOf(adapterList.id));
    		db.deleteProduct(helpID);
    		listView1.invalidateViews();
    		
           break;
            
    	
    	
    	case R.id.eddit_item_context:
    	    Intent intent = new Intent(this, AddNewProduct.class);
    		intent.putExtra("LIST_ID", listID);
    	    startActivity(intent);
    		break;
    		
    	}
		return false;
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
				newList = db.getList(listID);
				newList.setName(name);
				db.updateList(newList);		
				NavUtils.navigateUpFromSameTask(this);
		
		}
		else{
			Toast toast = Toast.makeText(getApplicationContext(),
					"Pleas put name for list", Toast.LENGTH_SHORT);
			toast.show();
		}
	}
	
	public void openNewPageToScan(View view){
		newList = db.getList(listID);
		newList.setName(name);
		db.updateList(newList);
		//Log.d("id listy", String.valueOf(db.getList(listID).getId()));
		//Log.d("id listy232", db.getList(listID).getName());
		Intent intent = new Intent(this, AddNewProduct.class);
		intent.putExtra("LIST_ID", String.valueOf(listID));
		startActivity(intent);
	}
	

}
