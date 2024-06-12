package com.multi.spring.sign.model.dto;

public class SignDTO {
	
	private int signid;
	private String name;
	private String birth;
	private String img;
	
	public int getSignid() {
		return signid;
	}
	public void setSignid(int signid) {
		this.signid = signid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	@Override
	public String toString() {
		return "SignDTO [signid=" + signid + ", name=" + name + ", birth=" + birth + ", img=" + img + "]";
	}
	
	
	
	

}
