package com.web.gid;

public class DataGrid {
	
	private Integer index;
	
	private String name;	
	
	private String value;
	
	public DataGrid(Integer index, String name, String value) {
		this.index = index;
		this.name = name;
		this.value = value;
	}


	public DataGrid() {
	}

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
		

}
