package com.example.shoppinglist.sqlite.model;

public class ProductModel {
	long id;
	int amount;
	long iDBarCode;
	long iDList;
	
	public ProductModel(){
		
	}
	
	public ProductModel(int amount){
		this.amount=amount;
	}
	
	public ProductModel(long id, int amount){
		this.id=id;
		this.amount=amount;
	}
	
	public ProductModel( int amount, long iDBarCode, long iDList){
		this.amount=amount;
		this.iDBarCode=iDBarCode;
		this.iDList=iDList;
	}
	
	public ProductModel(long id, int amount, long iDBarCode, long iDList){
		this.id = id;
		this.amount=amount;
		this.iDBarCode=iDBarCode;
		this.iDList=iDList;
	}
	
	//getters
	public long getId(){
		return this.id;
	}
	
	public int getAmount(){
		return this.amount;
	}
	
	public long getIDBarCode(){
		return this.iDBarCode;
	}
	
	public long getIDList(){
		return this.iDList;
	}
	
	//setters
	public void setId(long id){
		this.id=id;
	}
	
	public void setAmount(int amount){
		this.amount=amount;
	}
	
	public void setIDBarCode(long iDBarCode){
		this.iDBarCode=iDBarCode;
	}
	
	public void setIDList(long iDList){
		this.iDList=iDList;
	}

	
}
