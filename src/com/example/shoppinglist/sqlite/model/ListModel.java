package com.example.shoppinglist.sqlite.model;

public class ListModel {
	long id;
	String name;
	
	public ListModel(){
		
	}
	
	public ListModel(String name){
		this.name=name;
	}
	
	public ListModel(long id, String name){
		this.id = id;
		this.name = name;
	}
	
	//getters
	
	public long getId(){
		return this.id;
	}
	
	public String getName(){
		return this.name;
	}
	
	//setters

	public void setId(long id){
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
