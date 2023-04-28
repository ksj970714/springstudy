package myproject.mymvc.controller;

import myproject.mymvc.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService){
        this.memberService = memberService; // singleton 패턴에 맞게 컨테이너에 있는 요소와 연결지어줌
                // 그게 Autowired임
    }
}
