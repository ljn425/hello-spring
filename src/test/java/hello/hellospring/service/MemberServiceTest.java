package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {
    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach // 테스트 시작전 호출되는 메서드
    public void beforeEach() {  //DI 개념 Dependency Injection , 외부에서 객체 주입
        //System.out.println("beforeEach() 호출 ");
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach // 클래스 내 메서드 실행 끝날 때마다 호출되는 메서드
    public void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void 회원가입() {
        // given : 이러한 상황이 주어졌을 때
        Member member = new Member();
        member.setName("MinSu");

        // when : 이러한 조건에서
        Long saveId = memberService.join(member);

        // then : 이러한 결과가 나와야 한다
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외() {
        // given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        // when
        memberService.join(member1);
        
        //assertThrows(발생해야하는예외, 실행로직) : 원하는 예외를 발생시키는 테스트를 해야하는 경우 쓰이는 메서드, try catch 문 대신 쓰이는 메서드
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        // then
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

        /* try {
            memberService.join(member2);
            fail();
        } catch(IllegalStateException e) {
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다123.");
        }*/


    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}