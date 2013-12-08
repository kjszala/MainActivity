package com.example.shoppinglist;

import com.example.shoppinglist.sqlite.helper.DatabaseHelper;
import com.example.shoppinglist.sqlite.model.ListModel;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.SyncStateContract.Helpers;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void changeBrowseBarCodeDataBase(View view) {
		Intent intent = new Intent(this, BrowseBarCodeDataBaseActivity.class);
		startActivity(intent);
	}

	public void changeBrowseExistingListDataBase(View view) {
		Intent intent = new Intent(this,
				BrowseExistingListDataBaseActivity.class);
		startActivity(intent);
	}

	public void changeCreateNewListDataBase(View view) {
		DatabaseHelper db = new DatabaseHelper(this);
		ListModel newList = new ListModel();
		long listID = db.createList(newList);
		
		Intent intent = new Intent(this, CreateNewListDataBaseActivity.class);
		intent.putExtra("LIST_ID", listID);
		startActivity(intent);
	}

}
