package com.example.shoppinglist.sqlite.model;

public class BarcodeModel {
	
	int id;
	String name;
	String barCode;
	
	public BarcodeModel(){
		
	}
	
	public BarcodeModel(String name, String barCode){
		this.name=name;
		this.barCode=barCode;
	}
	
	public BarcodeModel(int id, String name, String barCode){
		this.id = id;
		this.name=name;
		this.barCode=barCode;
	}
	
	//getters
	public int getId(){
		return this.id;
	}
	
	public String getName(){
		return this.name;
	}
	
	public String getBarCode(){
		return this.barCode;
	}
	
	//setters

	public void setId(int id){
		this.id = id;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public void setBarCode(String barCode){
		this.barCode = barCode;
	}
	
	@Override
	public String toString() {
		String nameANDBarCodeID = this.name + " " + this.barCode;
	    return nameANDBarCodeID;
	  }
}
