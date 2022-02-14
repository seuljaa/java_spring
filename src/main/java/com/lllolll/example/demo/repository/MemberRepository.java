package com.lllolll.example.demo.repository;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.lllolll.example.demo.vo.Member;

@Mapper
public interface MemberRepository {
	@Insert("""
			INSERT INTO `member`
			SET regDate = NOW(),
			updateDate = NOW(),
			loginId = #{loginId},
			loginPw = #{loginPw},
			`name` = #{name},
			`nickname` = #{nickname},
			cellphonNo = #{cellphonNo},
			email = #{email};
									""")
	void join(@Param("loginId") String loginId, @Param("loginPw") String loginPw, @Param("name") String name,
			@Param("nickname") String nickname, @Param("cellphonNo") String cellphonNo, @Param("email") String email);
	
	@Select("SELECT COUNT(*) FROM `member`")
	int getLastId();
	
	@Select("SELECT * FROM `member` AS M WHERE M.id = #{id}")
	Member getMemberById(@Param("id") int id);
}
