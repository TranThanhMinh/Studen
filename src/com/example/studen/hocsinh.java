package com.example.studen;

public class hocsinh {

	private String stt;
	private String name;
	private String diem;


	public String getName(){
		return name;
	}
	public void setName(String name){
		this.name = name;
	}

	public String getStt() {
		return stt;
	}

	public void setStt(String stt) {
		this.stt = stt;
	}

	public String getDiem() {
		return diem;
	}

	public void setDiem(String diem) {
		this.diem = diem;
	}
	public String toString() {
		// TODO Auto-generated method stub
		return this.stt +" - " +this.name +" - "+this.diem  ;
	}

}
