package myproject.mymvc.service;

import myproject.mymvc.domain.Member;
import myproject.mymvc.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {
    // 서비스 로직에서 필요한게 뭐지? Repository와의 상호작용을 위해 불러오는 것이다.
    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) { // (변수가 속한)클래스명, 변수명
        this.memberRepository = memberRepository; //실질 변수 할당
    }
    // Repository에서 변수를 저장하고 가져오는걸 구현했다면, 이것을 응용해 서비스를 구현하는 것을 만듬
    // 변수 저장 등의 기능적인 측면은 완벽히 분리되었기 때문에 코드의 재사용성 및 유지보수가 가능하다.
    public Long join(Member member){ // member 객체 검증 > repository에 저장 > id 출력
        validateDuplicateMember(member); // 만약 여기서 통과하지 못하면 error발생, 통과 X
        memberRepository.save(member);
        return member.getId();
    }
    // 아이디 저장 기능을 구현하되, 중복 이름 방지를 구현하고 싶다.
    // 크게 두 가지를 통해 구현한다.
    // join이라는 회원 가입 로직인데, 여기서 중복 이름을 검사해 중복되면 throw가 던져짐.
    // 회원가입에 성공하면 id가 반환됨, id의 타입은 Long
    private void validateDuplicateMember(Member member) { // 이름 유효성 검증하는 코드
        //private으로 만든 이유: 해당 클래스 내에서만 쓰는 내수용이기 때문
        //void 이기 때문에 return이 없어도 된다.
        memberRepository.findByName(member.getName())
                .ifPresent(
                        m -> {
                            throw new IllegalStateException("이미 있는 이름입니다");
                        });//null이 아닐 때만 해당 람다식이 실행됨
    }
    // 전체 회원 조회 로직 findMembers()
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }
    // ID로 회원 찾기 로직
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
