package com.example.shoppinglist;

import java.util.List;


import com.example.shoppinglist.adapters.AdapterListsList;
import com.example.shoppinglist.sqlite.helper.DatabaseHelper;
import com.example.shoppinglist.sqlite.model.ListModel;

import android.os.Bundle;
import android.app.ListActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.support.v4.app.NavUtils;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.widget.TextView;

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
        Log.d("Reading: ", "Reading all contacts111111111.."); 
        listView2 = getListView();
        listView2.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            	Log.d("czy wogole wchodzi do niego", "ciekawe");
                
                // selected item 
                int listID = Integer.valueOf(((TextView) view).getText().toString());
                 Log.d("weszlo cos",((TextView) view).getText().toString());
                // Launching new Activity on selecting single List Item
                Intent i = new Intent(getApplicationContext(), EditExistingListDataBase.class);
                // sending data to new activity
                i.putExtra("listID", listID);
                startActivity(i);       
            }

  		
          });
        listView2.setAdapter(adapter);
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
