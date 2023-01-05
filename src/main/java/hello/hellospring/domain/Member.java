package hello.hellospring.domain;

import javax.persistence.*;

@Entity
public class Member {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) //@Id 기본키 지정, @GeneratedValue 기본키 설정,  GenerationType.IDENTITY -> DB 에서 기본키값 자동생성
    private Long id;
    
    //@Column(name="userName") // @Column(name="DB 컬럼명") -> DB 컬럼명과 매칭시켜줌
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
