package com.example.shoppinglist;

import java.util.List;


import com.example.shoppinglist.adapters.AdapterListsList;
import com.example.shoppinglist.sqlite.helper.DatabaseHelper;
import com.example.shoppinglist.sqlite.model.ListModel;
import com.example.shoppinglist.sqlite.model.ProductModel;

import android.os.Bundle;
import android.app.ListActivity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.ListView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.support.v4.app.NavUtils;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;

public class BrowseExistingListDataBaseActivity extends ListActivity {
	DatabaseHelper db;
	private ListView listView2;
	List<ListModel> listDataForList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_browse_existing_list_data_base);
		// Show the Up button in the action bar.
		setupActionBar();
		db = new DatabaseHelper(this); 
		listDataForList = db.getAllList();
		AdapterListsList adapter = new AdapterListsList(this,
                R.layout.lists_list_item, listDataForList);
		// Reading all contacts
        listView2 = getListView();
        registerForContextMenu(listView2);
        listView2.setAdapter(adapter);
	}
	
    /**
     * Creates context menu on point list view
     */
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo){
            super.onCreateContextMenu(menu, v, menuInfo);
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.list_context_menu, menu);
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
    		List<ProductModel> productToDelete = db.getAllProduct(helpID);
    		if(productToDelete.size() > 0 ){
    		for(ProductModel productInList : productToDelete){
    		db.deleteProduct(productInList.getId());
    		}
    		}
    		db.deleteList(db.getList(helpID));
    		listView2.invalidateViews();
    		
           break;
            
    	
    	
    	case R.id.eddit_item_context:
    		long helpIDEdit = Long.valueOf(String.valueOf(adapterList.id));
    	    Intent intent = new Intent(this, CreateNewListDataBaseActivity.class);
    		intent.putExtra("LIST_ID", String.valueOf(helpIDEdit));
    	    startActivity(intent);
    		break;
    		
    	}
		return false;
    }
    

	/**
	 * Set up the {@link android.app.ActionBar}, if the API is available.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.browse_existing_list_data_base, menu);
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
	
	
}
