package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository{

    /**
     * EntityManager 는 JPA 통합 매니저로 JPA 활용시 꼭 필요하다.
     * EntityManager 는 implementation 'org.springframework.boot:spring-boot-starter-data-jpa' gradle 빌드후 스프링 컨테이너에서 자동으로 bean 생성 해줌
     *
     */
    private final EntityManager em;

    @Autowired
    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member); // EntityManager persist() : 영속성 저장
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
        return result.stream().findAny();

    }

    @Override
    public List<Member> findAll() {
        /**
         *  select m from Member as m
         *  @Entity로 설정된 Member(별명 m) 에서 select 하고 그 결과를 Member 컬럼에 매칭해서 리턴한다. 
         *  DB 접속 -> sql -> dao 매칭 -> 리턴
          */
        return em.createQuery("select m from Member m", Member.class).getResultList();
    }
}
