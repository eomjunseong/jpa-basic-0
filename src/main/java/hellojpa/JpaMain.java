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

            tx.commit();//member는 준영속 상태가 되어 name이 바뀐 것이 적용되지 않는다.
        }catch(Exception error){
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();
    }
    /**
     * @OneTOMany
     * @JoinColumn 은 반대편에 PK ->
     *
     * @ManyToOne
     * @JoinColumn 얘가 PK
     *
     *  LAZY X :
     *  em.find(member) memeber team 한번에 조회
     *  em.createQuery(select member) --> member만 뽑고 team 만 또 뽑고 여러번 나감
     *
     *  LAZY O :
     *  em.find(member) member 만 조회
     *  em.createQuery(select member) --> member만 뽑음, 따로 또 호출하면 그제서야 TEAM select
     *  한방 쿼리 필요시 : batch_size , fetch join, (?)
     */
}