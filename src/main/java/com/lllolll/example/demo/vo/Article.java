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
	int hitCount;
	
	private String extra__writerName;
	private boolean extra__actorCanDelete;
	private boolean extra__actorCanModify;
	
	public String getRegDateForPrint() {
		return regDate.substring(2, 16);
	}
	public String getUpdateDateForPrint() {
		return updateDate.substring(2, 16);
	}
}
