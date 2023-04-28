package myproject.mymvc.repository;

import myproject.mymvc.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

// 데이터 저장하는 역할
//MemberRepository의 구현체, MemoryMemberRepository 구현.
@Repository
public class MemoryMemberRepository implements MemberRepository{
    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    // 추상 메서드 오버라이드
    @Override
    public Member save(Member member) {
        member.setId(sequence++); // 1개씩 증가하며 할당
        // 동시에 id를 지정해줌 + id라는 key를 통해 Member 클래스의 member 객체
        store.put(member.getId(), member); // key, value 쌍으로 저장
        return member; //왜 member 리턴?
    }

    @Override
    public Optional<Member> findById(Long id) {
        // id를 통해 검색하되, null값이 반환되는 경우가 존재할 수 있음. 해당 요소를 고려할 것

        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) { // key: value 형태의 자료구조에서,
        // 이름을 통해 서치하는 것. 스트림으로 만든 뒤 순회하며 filter 메소드를 사용 후 반환
        System.out.println(store.values().stream()); // key는 Id, value는 member 객체임

        return store.values().stream() // key, value 값들을 stream으로 만듬
                .filter(member -> member.getName().equals(name))
                .findAny(); //아무거나 찾아서 반환 / 스트림 차료구조에 대해 더 이해해볼 것
    }

    @Override
    public List<Member> findAll() {

        return new ArrayList<>(store.values());
    }

    // 테스트 코드 작성을 위해 필요한 메서드 설계. 테스트 간 종속성 방지 위해 필요
    public void clearStore() { store.clear(); }
}
