package org.hojin.domain;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

public class UserVO {
	@NotBlank(message="이메일형식이 아닙니다")
	private String uid;
	
	@Size(min=4, message="패스워드는 4자 이상이어야 합니다")
	private String upw;
	
	@Size(min = 3, message = "이름은 3자 이상이어야 합니다")
	private String uname;
	
	private int upoint;
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getUpw() {
		return upw;
	}
	public void setUpw(String upw) {
		this.upw = upw;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public int getUpoint() {
		return upoint;
	}
	public void setUpoint(int upoint) {
		this.upoint = upoint;
	}
	@Override
	public String toString() {
		return uid + " : " + uname;
	}
	
	
}
