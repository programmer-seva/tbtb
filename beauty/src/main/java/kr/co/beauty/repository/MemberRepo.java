package kr.co.beauty.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.co.beauty.entity.MemberEntity;

public interface MemberRepo extends JpaRepository<MemberEntity, String> {
	
	public int countByUid(String uid);
}
