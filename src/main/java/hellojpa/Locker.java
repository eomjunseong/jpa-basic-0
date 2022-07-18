package hellojpa;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
@Data
public class Locker {
    @Id @GeneratedValue
    private Long id;

    private String lockerName;

    @OneToOne(mappedBy = "locker")
    private Member member;
}
