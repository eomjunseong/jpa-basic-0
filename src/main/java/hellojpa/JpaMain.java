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
            Locker locker = new Locker();
            em.persist(locker);

            Member member = new Member();
            member.setUsername("asdasd");
            member.setLocker(locker);
            em.persist(member);

            em.flush();
            em.clear();

            Locker locker1 = em.find(Locker.class, locker.getId());
            System.out.println(locker1.getMember().getUsername());


            tx.commit();//member는 준영속 상태가 되어 name이 바뀐 것이 적용되지 않는다.
        }catch(Exception error){
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();
    }
}