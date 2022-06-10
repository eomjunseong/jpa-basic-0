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
            Team team1 = new Team();
            team1.setName("팀A");
            Team team2 = new Team();
            team2.setName("팀B");
            em.persist(team1);
            em.persist(team2);

            Member member1 = new Member();
            member1.setUsername("member1");
            member1.setTeam(team1);
            member1.setAge(10);
            Member member2 = new Member();
            member2.setUsername("member2");
            member2.setTeam(team1);
            member2.setAge(10);
            Member member3 = new Member();
            member3.setUsername("member3");
            member3.setTeam(team2);
            member3.setAge(10);


            em.persist(member1);
            em.persist(member2);
            em.persist(member3);

            em.flush();
            em.clear();

//            String query="select m from Member m join fetch m.team";
            String query="select distinct t from Team t join fetch t.members";
            List<Team> resultList = em.createQuery(query, Team.class).getResultList();
            for (Team s : resultList) {
                System.out.println("s = " + s.getName() + " | "+s.getMembers().size());
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