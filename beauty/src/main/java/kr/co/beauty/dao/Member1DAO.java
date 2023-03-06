/* 
 * 김동근
 * Member1VO 
 */
package kr.co.beauty.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import kr.co.beauty.vo.Member1VO;

@Mapper
@Repository
public interface Member1DAO {
	public Member1VO selectMember(@Param("email") String email);
}
