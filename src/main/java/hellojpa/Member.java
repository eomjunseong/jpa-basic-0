package hellojpa;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Member {

    @Id @GeneratedValue
    private Long id;
    private String username;
    private int age;


//    @OneToOne
//    @JoinColumn(name="loker_id")
//    private Locker locker;

    @ManyToOne(fetch=FetchType.LAZY)
//    @ManyToOne
    @JoinColumn(name="TEAM_ID")
    private Team team;

//    @Enumerated(EnumType.STRING)
//    private MemberType type;
//
//
//    public void changeTeam(Team team){
//        this.team = team;
//        team.getMembers().add(this);
//    }


}
