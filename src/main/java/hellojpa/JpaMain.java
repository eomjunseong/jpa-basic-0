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
            Member member = new Member();
            member.setUsername("member1");
            member.setAge(10);
            em.persist(member);

            Member singleResult = em.createQuery(
                            "select m from Member m where m.username = :username", Member.class)
                    .setParameter("username", "member1")
                    .getSingleResult();
            System.out.println(member.getAge());


            tx.commit();//member는 준영속 상태가 되어 name이 바뀐 것이 적용되지 않는다.
        }catch(Exception error){
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();
    }
}