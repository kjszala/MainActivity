package com.example.shoppinglist;

import java.util.List;

import com.example.shoppinglist.adapters.AdapterBarcodeList;
import com.example.shoppinglist.sqlite.helper.DatabaseHelper;
import com.example.shoppinglist.sqlite.model.BarcodeModel;
import com.example.shoppinglist.sqlite.model.ProductModel;
import com.example.shoppinglist.R;

import android.os.Bundle;
import android.app.ListActivity;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ListView;
import android.support.v4.app.NavUtils;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;

public class BrowseBarCodeDataBaseActivity extends ListActivity {
		DatabaseHelper db;
		private ListView listView1;
		List<BarcodeModel> barCodeDataForList;
    
	@Override
	protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_browse_bar_code_data_base);
		

		
		db = new DatabaseHelper(this);

		
		barCodeDataForList = db.getAllBarCode();
		// Reading all contacts
		AdapterBarcodeList adapter = new AdapterBarcodeList(this,
                R.layout.bar_code_list_item, barCodeDataForList);
        listView1 = getListView();
        registerForContextMenu(listView1);
        listView1.setAdapter(adapter);

		
	    
	}
	
    /**
     * Creates context menu on point list view
     */
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo){
            super.onCreateContextMenu(menu, v, menuInfo);
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.barcode_context_menu, menu);
    }
    
    
    /**
     * Sterring in context menu
     */
    @Override
    public boolean onContextItemSelected(MenuItem item){ 		
    	AdapterContextMenuInfo  adapterBarCode = (AdapterContextMenuInfo)item.getMenuInfo();     
    	switch(item.getItemId()) {
    	case R.id.delete_item_context:
    		long helpID = Long.valueOf(String.valueOf(adapterBarCode.id));
    		List<ProductModel> productToDelete = db.getAllProductWithThisBarCode(helpID);
    		if(productToDelete.size() > 0 ){
    		for(ProductModel productInList : productToDelete){
    		db.deleteProduct(productInList.getId());
    		}
    		}
    		db.deleteBarCode(db.getBarCode(helpID));
    		listView1.invalidateViews();
    		
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
		getMenuInflater().inflate(R.menu.browse_bar_code_data_base, menu);
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
    		//db.closeDB();
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void changeAddNewBarCode(View view) {
	    Intent intent = new Intent(this, AddNewBarCode.class);
	    startActivity(intent);
	}
	
	@Override
	  protected void onPause() {
	    db.closeDB();
	    super.onPause();
	  }

	
	

}
