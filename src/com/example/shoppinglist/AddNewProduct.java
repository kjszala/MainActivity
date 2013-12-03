package com.example.shoppinglist;

import java.util.List;

import com.example.shoppinglist.adapters.AdapterBarCodeToAddProduct;
import com.example.shoppinglist.sqlite.helper.DatabaseHelper;
import com.example.shoppinglist.sqlite.model.BarcodeModel;
import com.example.shoppinglist.sqlite.model.ProductModel;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import android.os.Bundle;
import android.app.ListActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.support.v4.app.NavUtils;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;

public class AddNewProduct extends ListActivity {
	DatabaseHelper db;
	private ListView listView1;
	List<BarcodeModel> barCodeDataForList;
	
	String scanContent = null;
	int amount = 0;
	EditText mEdit;
	ProductModel product;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_new_product);
		// Show the Up button in the action bar.
		setupActionBar();
		product = new ProductModel();		
		db = new DatabaseHelper(this);
		barCodeDataForList = db.getAllBarCode();
		AdapterBarCodeToAddProduct adapter = new AdapterBarCodeToAddProduct(this,
                R.layout.bra_code_to_add_product, barCodeDataForList);
        listView1 = getListView();
        listView1.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                 
                // selected item 
                int barCodeID = Integer.valueOf(((TextView) view).getText().toString());
                product.setIDBarCode(barCodeID);               
            }

  		
          });
        listView1.setAdapter(adapter);
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
		getMenuInflater().inflate(R.menu.add_new_product, menu);
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
					product.setIDBarCode(bd.getId());
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
		
		public void addNewProductAfterScan(View view){
			IntentIntegrator scanIntegrator = new IntentIntegrator(this);
			scanIntegrator.initiateScan();
		
		}		
			
		public void addNewProduct(View view){
			amount = Integer.valueOf(mEdit.getText().toString());
			if (amount !=0 && product.getIDBarCode()<=0) {
					product.setAmount(amount);
					db.addProductWithBarCode(product, -1);				
					NavUtils.navigateUpFromSameTask(this);
			
			}
			else if (amount ==0){
				Toast toast = Toast.makeText(getApplicationContext(),
						"Pleas put amount for product", Toast.LENGTH_SHORT);
				toast.show();
			}
			
			else if (product.getIDBarCode()<=0){
				Toast toast = Toast.makeText(getApplicationContext(),
						"Pleas put amount for product", Toast.LENGTH_SHORT);
				toast.show();
			}
			
		}

}
