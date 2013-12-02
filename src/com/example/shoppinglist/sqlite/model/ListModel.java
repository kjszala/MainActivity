package com.example.shoppinglist.sqlite.model;

public class ListModel {
	int id;
	String name;
	
	public ListModel(){
		
	}
	
	public ListModel(String name){
		this.name=name;
	}
	
	public ListModel(int id, String name){
		this.id = id;
		this.name = name;
	}
	
	//getters
	
	public int getId(){
		return this.id;
	}
	
	public String getName(){
		return this.name;
	}
	
	//setters

	public void setId(int id){
		this.id=id;
	}
	
	public void setName(String name){
		this.name=name;
	}
	
	@Override
	public String toString() {
		String nameList = this.name;
	    return nameList;
	  }
	
}
