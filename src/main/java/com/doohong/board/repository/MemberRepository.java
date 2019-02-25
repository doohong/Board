package com.doohong.board.repository;

import com.doohong.board.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findByUsername(String username);
}
