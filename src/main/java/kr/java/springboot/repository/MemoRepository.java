package kr.java.springboot.repository;

import kr.java.springboot.entity.Memo;
import org.springframework.data.jpa.repository.JpaRepository;

// @Repository 생략가능 -> JpaRepository 얘가 대신 스캔해줌
// JpaRepository<Entity타입, ID(PK)타입>
public interface MemoRepository extends JpaRepository<Memo, Long> {
}
