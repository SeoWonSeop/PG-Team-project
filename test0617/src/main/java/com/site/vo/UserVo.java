package com.site.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserVo {
	
	
	
	private String email;
	private int userNo;
	private String password;
	private String name;
	private String phoneNo;
	private String birth;
	private int adminCode;
	private String address1;
	private String address2;
	private String zipcode;

}
