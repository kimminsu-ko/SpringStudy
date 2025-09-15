package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository ;

    @BeforeEach //동작전에 시행할 코드
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();        //진짜 저장소에서 그대로 가져오기위함
        memberService = new MemberService(memberRepository);
    }

    @AfterEach  //@작업이 끝나고 시행할 코드
    public void afterEach(){
        memberRepository.clearStore();  //repository 지워놔야 순서상관없이 테스트 가능
    }

    @Test
    void 회원가입() {
        //given
        Member member = new Member();
        member.setName("spring");

        //when
        Long saveId = memberService.join(member);

        //then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외(){
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));//오른쪽하면 왼쪽이 터져야함 터져야 성공

        assertThat(e.getMessage()).isEqualTo("이미 존재 하는 회원입니다.");
//        try{
//            memberService.join(member2);
//            fail("what?");
//        } catch (IllegalStateException e){
//            assertThat(e.getMessage()).isEqualTo("이미 존재 하는 회원입니다.");
//        }

        //then
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}