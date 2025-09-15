package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

public class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach  //@작업이 끝나고 시행할 코드
    public void afterEach(){
        repository.clearStore();    //repository 지워놔야 순서상관없이 테스트 가능
    }

    @Test
    public void save(){
        Member member = new Member();
        member.setName("String");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();
        Assertions.assertEquals(member,result); //같은거 잘 가져왔는지 확인
        assertThat(result).isEqualTo(member);
    }
    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("String1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("String2");
        repository.save(member2);

        Member result = repository.findByName("String1").get();

        assertThat(result).isEqualTo(member1);
    }
    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("String1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("String2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }
}
