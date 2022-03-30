package hello.login.domain.member;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.*;

@Slf4j
@Repository
public class MemberRepository {
    private static Map<Long, Member> store = new HashMap<>();//static 사용
    private static long sequence = 0L;

    public Member save(Member member) {
        member.setId(++sequence);
        log.info("save: member={}", member);
        store.put(member.getId(), member);
        return member;
    }

    public Member findById(Long id){
        return store.get(id);
    }


    //Optional 빈 깡통 상자 . 자바 8에서 제공 .
    public Optional<Member> findByLoginId(String loginId){
//        List<Member> all = findAll();
//        for (Member m : all){
//            if(m.getLoginId().equals(loginId)){
//                return Optional.of(m);
//            }
//        }
//        return Optional.empty();
        //람다
        //리스트를 stream으로 바꿔
        //filter에서 만족하는애만 다음으로
        //멤버의 로그인 아이디와 loginId가 같으면 걔만 다음 단계로
        //findFirst 먼저 나온애들 먼저 반환
        //위에 코드를 밑에 식으로 바로 바꿨으.
        return findAll().stream()
                .filter(m->m.getLoginId().equals(loginId))
                .findFirst();
    }


    public List<Member> findAll(){
         return new ArrayList<>(store.values());//리스트로 변환
    }

    public void clearStore(){
        store.clear();
    }


}
