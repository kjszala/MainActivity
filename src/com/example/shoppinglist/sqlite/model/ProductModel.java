package com.example.shoppinglist.sqlite.model;

public class ProductModel {
	int id;
	int amount;
	int iDBarCode;
	int iDList;
	
	public ProductModel(){
		
	}
	
	public ProductModel(int amount){
		this.amount=amount;
	}
	
	public ProductModel(int id, int amount){
		this.id=id;
		this.amount=amount;
	}
	
	public ProductModel( int amount, int iDBarCode, int iDList){
		this.amount=amount;
		this.iDBarCode=iDBarCode;
		this.iDList=iDList;
	}
	
	public ProductModel(int id, int amount, int iDBarCode, int iDList){
		this.id = id;
		this.amount=amount;
		this.iDBarCode=iDBarCode;
		this.iDList=iDList;
	}
	
	//getters
	public int getId(){
		return this.id;
	}
	
	public int getAmount(){
		return this.amount;
	}
	
	public int getIDBarCode(){
		return this.iDBarCode;
	}
	
	public int getIDList(){
		return this.iDList;
	}
	
	//setters
	public void setId(int id){
		this.id=id;
	}
	
	public void setAmount(int amount){
		this.amount=amount;
	}
	
	public void setIDBarCode(int iDBarCode){
		this.iDBarCode=iDBarCode;
	}
	
	public void setIDList(int iDList){
		this.iDList=iDList;
	}

	
}
