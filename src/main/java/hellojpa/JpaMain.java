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

            //1. Query 타입으로 조회
            //2. Object[] 타입으로 조회
            //3. NEW 명령어로 조회 , 패키지명 써줘야함 

            List<MemberDTO> resultList = em.createQuery(
                            "select new hellojpa.MemberDTO(m.username, m.age) from Member m ", MemberDTO.class)
                    .getResultList();
            MemberDTO memberDTO = resultList.get(0);
            System.out.println(memberDTO.getAge());
            System.out.println(memberDTO.getUsername());


            tx.commit();//member는 준영속 상태가 되어 name이 바뀐 것이 적용되지 않는다.
        }catch(Exception error){
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();
    }
}