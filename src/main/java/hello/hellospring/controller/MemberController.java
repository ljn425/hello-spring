package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MemberController {
    private final MemberService memberService;

    @Autowired  // @Autowired 스프링 컨테이너에서 MemberService  bean 을 찾아 memberService 변수에 대입한다.
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}
