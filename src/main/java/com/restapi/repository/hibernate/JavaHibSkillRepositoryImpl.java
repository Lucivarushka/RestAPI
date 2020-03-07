package com.restapi.repository.hibernate;

import com.restapi.model.Skill;
import com.restapi.repository.SkillRepository;
import com.restapi.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class JavaHibSkillRepositoryImpl implements SkillRepository {
    private final HibernateUtil hibernateUtil = new HibernateUtil();

    @Override
    public void create(Skill skill) {
        hibernateUtil.openTransactionSession();
        Session session = hibernateUtil.getSession();
        session.save(skill);
        hibernateUtil.closeTransactionSession();
    }

    @Override
    public void update(Skill skill) {
        hibernateUtil.openTransactionSession();
        Session session = hibernateUtil.getSession();
        session.update(skill);
        hibernateUtil.closeTransactionSession();
    }

    @Override
    public void delete(Skill skill) {
        hibernateUtil.openTransactionSession();
        Session session = hibernateUtil.getSession();
        session.remove(skill);
        hibernateUtil.closeTransactionSession();
    }

    @Override
    public List<Skill> getAll() {
        hibernateUtil.openTransactionSession();
        String hql = "SELECT * FROM Skill";
        Session session = hibernateUtil.getSession();
        Query query = session.createNativeQuery(hql).addEntity(Skill.class);
        List<Skill> skills = query.list();
        hibernateUtil.closeTransactionSession();
        return skills;
    }

    @Override
    public Skill getId(Integer id) {
        hibernateUtil.openTransactionSession();
        String hql = "SELECT * FROM Skill WHERE id = :id";
        Session session = hibernateUtil.getSession();
        Query query = session.createNativeQuery(hql).addEntity(Skill.class);
        query.setParameter("id", id);
        Skill skill = (Skill) query.getSingleResult();
        hibernateUtil.closeTransactionSession();
        return skill;
    }
}
