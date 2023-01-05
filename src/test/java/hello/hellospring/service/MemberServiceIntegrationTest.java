package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest // 스프링 컨테니어와 Test 를 함께 실행한다. 보통 '통합 테스트' 라고 한다.
@Transactional // Test 시작전에 트랜잭션을 걸고 Test 끝났을 때 roll back 해준다. @Test annotation 붙은 메서드마다 적용
class MemberServiceIntegrationTest {
    @Autowired  MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
    //@Commit // @Transactional 상관없이 DB 데이터 Commit 한다.
    void 회원가입() {
        // given : 이러한 상황이 주어졌을 때
        Member member = new Member();
        member.setName("spring");

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
}