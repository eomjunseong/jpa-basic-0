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
            for (int i = 0; i < 100; i++) {
                Member member = new Member();
                member.setUsername("member"+i);
                member.setAge(i);
                em.persist(member);
            }


            List<Member> result = em.createQuery("select m from Member m order by m.age desc", Member.class)
                    .setFirstResult(3)//id 기준 100~1 중에 100,99,98, 재끼고 97 부터 10개 
                    .setMaxResults(10)
                    .getResultList();
            System.out.println(result.size());
            for (Member member1 : result) {
                System.out.println(member1);

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