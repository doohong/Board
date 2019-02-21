package com.doohong.board.controller;

import com.doohong.board.domain.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/member")
public class MemberController {

    List<Member> memberList = new ArrayList<>();
    //member
    @GetMapping
    public String index(Model model){

        model.addAttribute("list",memberList);
        return "member";


    }

    @PostMapping
    public String create(Member member, Model model){
        log.info("member {} ",member);
        memberList.add(member);
        return "redirect:/member";
    }
}
