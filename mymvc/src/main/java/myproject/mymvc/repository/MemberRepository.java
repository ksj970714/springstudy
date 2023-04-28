package myproject.mymvc.repository;


import myproject.mymvc.domain.Member;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

// 아이디로 찾기, name으로 찾기, 전부 다 찾기 구현
public interface MemberRepository  {

    Member save(Member member); // 추상 클래스으기 때문에 뭐해야한다만 보여주면 됨

    Optional<Member> findById(Long Id); //id 매개변수로 받아, member 객체를 반환
    // 만약 없다면 null 대 Optional.empty() 를 반환하게 됨

    Optional<Member> findByName(String Name);
    List<Member> findAll();
}
