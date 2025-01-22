package welcome;

import jakarta.persistence.*;

public class Welcome {

    public static void main(String[] args) {

        // 엔티티 매니저 팩토리는 DB마다 하나만 생성한 뒤 애플리케이션 전체에서 공유함
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("welcome");

        // 엔티티 매니저는 클라이언트 요청마다 생성하고 스레드끼리 공유하지 않음
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        // JPA의 모든 데이터 변경은 트랜잭션 안에서 실행되어야 함
        tx.begin();

        try {
            Member memberA = new Member(1L, "memberA");
            Member memberB = new Member(2L, "memberB");
            Member memberC = new Member(3L, "memberC");

            // 회원 등록
            em.persist(memberA);
            em.persist(memberB);
            em.persist(memberC);

            // 회원 조회 및 수정
            Member foundMember = em.find(Member.class, 3L);
            foundMember.setName("memberD");

            // 회원 삭제
            em.remove(memberB);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }

}