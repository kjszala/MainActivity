package com.example.shoppinglist;

import java.util.List;

import com.example.shoppinglist.sqlite.helper.DatabaseHelper;
import com.example.shoppinglist.sqlite.model.BarcodeModel;
import com.example.shoppinglist.sqlite.model.ListModel;
import com.example.shoppinglist.sqlite.model.ProductModel;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.support.v4.app.NavUtils;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;

public class CreateNewListDataBaseActivity extends Activity {
	DatabaseHelper db;
	String name = null;
	EditText mEdit;
	ListModel newList;
	String scanContent = null;
	List<ProductModel> lpm;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_new_list_data_base);
		db = new DatabaseHelper(this);
		newList = new ListModel();
		lpm = null;
		mEdit = (EditText) findViewById(R.id.listNameField);
		mEdit.setText(null);
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
		if (name != null && name != "New list name") {
				newList.setName(name);
				db.addList(newList);					
				NavUtils.navigateUpFromSameTask(this);
		
		}
		else{
			Toast toast = Toast.makeText(getApplicationContext(),
					"Pleas put name for list", Toast.LENGTH_SHORT);
			toast.show();
		}
	}
	
	public void addNewProductAfterScan(View view){
		IntentIntegrator scanIntegrator = new IntentIntegrator(this);
		scanIntegrator.initiateScan();
	
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
		IntentResult scanningResult = IntentIntegrator.parseActivityResult(
				requestCode, resultCode, intent);
		boolean existBarCode = false;
		if (scanningResult != null) {
			scanContent = scanningResult.getContents();
			List<BarcodeModel> barCodeDB = db.getAllBarCode();
			for (BarcodeModel bd : barCodeDB) {
				if (bd.getBarCode() == scanContent) {
					existBarCode = true;
					
					lpm.add(new ProductModel(0,bd.getId(),-1));
					break;
				}
			}
		}

		else if(!existBarCode) {
			Toast toast = Toast.makeText(getApplicationContext(),
					"Bar code isn't in database!", Toast.LENGTH_SHORT);
			toast.show();
		}
		
		else {
			Toast toast = Toast.makeText(getApplicationContext(),
					"No scan data receive!", Toast.LENGTH_SHORT);
			toast.show();
		}
	}
	
	
	public void addNewProductFromList(View view){
		
	}

}
