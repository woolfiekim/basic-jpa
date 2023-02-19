package com.joohee;

import javax.persistence.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            // Team team = new Team();
            // team.setName("teamA");
            // em.persist(team);
            //
            // Member member = new Member();
            // member.setUsername("memberB");
            // member.setAge(10);
            // member.setTeam(team);
            // em.persist(member);

            // em.flush(); //DB에 insert 쿼리가 나간다.
            // em.clear();
            //
            // String query = "select m from Member m, Team t where m.username =  t.name";
            //
            // List<Member> result = em.createQuery(query, Member.class)
            //         .getResultList();
            //
            // System.out.println("result = " + result.size());

            // Member member1 = em.find(Member.class, 1L);
            //
            // em.clear();
            //
            // Member member2 = em.find(Member.class, 1L);
            //
            // System.out.println(member1 == member2);

            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            Member member = new Member();
            member.setUsername("member1");
            member.setTeam(team);
            em.persist(member);

            em.flush();
            em.clear();

            Member findMember = em.find(Member.class, member.getId());
            List<Member> members = findMember.getTeam().getMembers();



            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}