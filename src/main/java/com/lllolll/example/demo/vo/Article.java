package com.lllolll.example.demo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Article{
	int id;
	private String regDate;
	private String updateDate;
	int memberId;
	String title;
	String body;
}
