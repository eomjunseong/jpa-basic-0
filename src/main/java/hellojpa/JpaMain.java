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

//            String query ="select m.username,'HELLO', TRUE,m.type from Member m where m.type= hellojpa.MemberType.ADMIN";
            String query ="select m.username,'HELLO', TRUE,m.type from Member m where m.type= :type";
            List<Object[]> list = em.createQuery(query)//            String query ="select m.username,'HELLO', TRUE from Member m ";
                    .setParameter("type",MemberType.ADMIN)
                    .getResultList();
            for (Object[] objects : list) {
                System.out.println(objects[0]);
                System.out.println(objects[1]);
                System.out.println(objects[2]);
                System.out.println(objects[3]);
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