package com.doohong.board.service;

import com.doohong.board.domain.Member;
import com.doohong.board.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MemberService {
    @Autowired
    private MemberRepository repository;

    public List<Member> findAll() {
        return repository.findAll();
    }

    public Member save(Member member) {
        return repository.save(member);
    }
}
