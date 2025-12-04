package kr.java.springboot.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data // 복잡한 연관관계 시 에러의 주범 (toString 때문에)
@Table(name = "memo")
public class Memo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 알아서 1씩 증가하는 PK
    // (IDENTITY -> MySQL, MariaDB에서 주로 사용)
    private Long id;
    private String content;
}
