package com.usermanagementapp.domain;

import lombok.Data;

@Data
public class UnlockAccount {
	private String email;
	private String tempPazzword;
	private String pazzword;
	private String confirmPazzword;

}
