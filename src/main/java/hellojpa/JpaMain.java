package hellojpa;

import javax.persistence.*;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf =  Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try{
            Team team = new Team();
            team.setName("teamA");
            em.persist(team);

            Member member = new Member();
            member.setUsername("member1");
            member.setAge(10);
            member.setTeam(team);
            member.setType(MemberType.ADMIN);
            em.persist(member);
/* case
            String query =
                    "select " +
                            "case when m.age<=10 then '학생요금' "+
                            "     when m.age>=60 then '성인요금' " +
                            " else '일반요금' end " +
                            "from Member m";
            List<String> result = em.createQuery(query, String.class).getResultList();
            for (String s : result) {
                System.out.println("s = " + s);
            }*/
//coalesce
            String query = "select coalesce(m.username,'이름없음') from Member m ";
            List<String> result = em.createQuery(query, String.class).getResultList();
            for (String s : result) {
                System.out.println("s = " + s);
            }

            tx.commit();//member는 준영속 상태가 되어 name이 바뀐 것이 적용되지 않는다.
        }catch(Exception error){
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();
    }
}