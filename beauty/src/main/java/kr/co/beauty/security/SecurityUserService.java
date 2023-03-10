package kr.co.beauty.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import kr.co.beauty.entity.MemberEntity;
import kr.co.beauty.repository.MemberRepo;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SecurityUserService implements UserDetailsService{
	
	@Autowired
	private MemberRepo repo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		log.info("here1 : " + username);
		
		//해당하는 사용자를 찾아보고 없으면
		MemberEntity member = repo.findById(username).get();
		
		log.info("here2 : " + member);
		
		if(member == null) {
			//시큐리티에 예외 발생시키기
			throw new UsernameNotFoundException(username);
		}
		//세션에 저장될 사용자 프로필 생성 user -> id, pw, role 밖에 저장 불가능
		//따라서 커스텀 유저디테일이 필요함 -> MyUserDetails 사용
		UserDetails userDts = MyUserDetails.builder()
											.member(member)
											.build();
		return userDts;
	}
}
