package com.doohong.board.service;

import com.doohong.board.domain.Member;
import com.doohong.board.domain.MemberAuthority;
import com.doohong.board.helper.CommonPageInfo;
import com.doohong.board.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;

@Service
@Transactional
public class MemberService implements UserDetailsService {
    @Autowired
    private MemberRepository repository;

    public List<Member> findAll() {
        return repository.findAll();
    }

    @Autowired
    private PasswordEncoder passwordEncoder;
    public Member save(Member member) {
        HashSet<MemberAuthority> a = new HashSet<>();
        a.add(MemberAuthority.USER);
        if(member.getUsername().equals("wnghd95@naver.com")){
            a.add(MemberAuthority.ADMIN);
        }
        member.setAuthorities(a);
        member.setPassword(passwordEncoder.encode(member.getPassword()));
        return repository.save(member);

    }

    public void createDummy(){
     for(int i=0; i<200; i++){
         Member member = new Member();
         member.setUsername("user"+i+"@naver.com");
         member.setPassword("1234");
         save(member);
     }
    }
    public CommonPageInfo<Member> findAll(Pageable pageable){
        Page<Member> all = repository.findAll(pageable);

        return new CommonPageInfo<Member>(all);


    }
    static class UserDetailImpl extends User{
        public UserDetailImpl(Member member){
            super(member.getUsername(),member.getPassword(), member.getAuthorities());
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByUsername(username)
        .map(UserDetailImpl::new)
        .orElseThrow(()-> new UsernameNotFoundException(username));

    }
}
