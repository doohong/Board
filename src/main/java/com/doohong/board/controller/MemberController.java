package com.doohong.board.controller;

import com.doohong.board.domain.Member;
import com.doohong.board.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/member")
public class MemberController {

    List<Member> memberList = new ArrayList<>();

    @Autowired
    MemberService memberService;
    //member
    @GetMapping
    public String index(Pageable pageable, Model model){
        log.info("page:{}",pageable);
        model.addAttribute("pageinfo",memberService.findAll(pageable));
        model.addAttribute("member",new Member());
        return "member";


    }

    @PostMapping
    public String create(@Valid Member member, BindingResult bindingResult, Model model){
        log.info("member {} ",member);
        if(bindingResult.hasErrors()){
            model.addAttribute("member",member);
            return "member";
        }
        memberService.save(member);
        return "redirect:/member";

    }

    @GetMapping("/dummy")
    @ResponseBody
    public String dummy(){
        memberService.createDummy();
        return "ok";
    }
}
