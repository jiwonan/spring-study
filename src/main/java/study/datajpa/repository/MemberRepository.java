package study.datajpa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import study.datajpa.dto.MemberDto;
import study.datajpa.entity.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    List<Member> findByUsernameAndAgeGreaterThan(String username, int age);

    List<Member> findTop3HelloBy();

    // named query가 없는 경우 기존 방식으로 쿼리 생성.
    List<Member> findByUsername(@Param("username") String username);

    // 이름이 없는 네임드 쿼리. 실행 시 오류 캐치.
    @Query("select m from Member m where m.username = :username and m.age = :age")
    List<Member> findUser(@Param("username") String username, @Param("age") int age);

    @Query("select m.username from Member m")
    List<String> findUsernameList();

//    @Query("select new study.datajpa.dto.MemberDto(m.id, m.username, t.team) from Member m join m.team t")
//    List<MemberDto> findMemberDto();

    @Query("select m from Member m where m.username in :names")
    List<Member> findByNames(@Param("names") List<String> names);

    List<Member> findListByUsername(String username);
    Member findMemberByUsername(String username);
    Optional<Member> findOptionalByUsername(String username);

    // List나 Slice도 사용할 수 있음.
    // count 쿼리를 분리하면 성능이 좋아짐.
    @Query(value = "select m from Member left join m.team t",
           countQuery = "select count (m.username) from Member m")
    Page<Member> findByAge(int age, Pageable pageable);
}
