package com.restapi.repository.hibernate;

import com.restapi.model.Developer;
import com.restapi.model.Skill;
import com.restapi.repository.DeveloperRepository;
import com.restapi.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class JavaHibDeveloperRepositoryImpl implements DeveloperRepository {
    private final HibernateUtil hibernateUtil = new HibernateUtil();

    @Override
    public void create(Developer developer) {
        hibernateUtil.openTransactionSession();
        Session session = hibernateUtil.getSession();
        session.save(developer);
        hibernateUtil.closeTransactionSession();
    }

    @Override
    public void update(Developer developer) {
        hibernateUtil.openTransactionSession();
        Session session = hibernateUtil.getSession();
        session.update(developer);
        hibernateUtil.closeTransactionSession();
    }

    @Override
    public void delete(Developer developer) {
        hibernateUtil.openTransactionSession();
        Session session = hibernateUtil.getSession();
        session.remove(developer);
        hibernateUtil.closeTransactionSession();
    }

    @Override
    public List<Developer> getAll() {
        hibernateUtil.openTransactionSession();
        String hql = "SELECT * FROM Developer";
        Session session = hibernateUtil.getSession();
        Query query = session.createNativeQuery(hql).addEntity(Developer.class);
        List<Developer> developers = query.list();
        hibernateUtil.closeTransactionSession();
        return developers;
    }

    @Override
    public Developer getId(Integer id) {
        hibernateUtil.openTransactionSession();
        String hql = "SELECT * FROM Developer WHERE id = :id";
        Session session = hibernateUtil.getSession();
        Query query = session.createNativeQuery(hql).addEntity(Developer.class);
        query.setParameter("id", id);
        Developer developer = (Developer) query.getSingleResult();
        hibernateUtil.closeTransactionSession();
        return developer;
    }

    public Set<Skill> getSkillByDev(Integer id) {
        hibernateUtil.openTransactionSession();
        String sql = "SELECT* FROM SKILL WHERE developer_fk = :id";
        Session session = hibernateUtil.getSession();
        Query query = session.createNativeQuery(sql).addEntity(Skill.class);
        query.setParameter("id", id);
        List<Skill> resultFromDB = query.list();
        Set<Skill> skills = new HashSet<>();
        skills.addAll(resultFromDB);
        hibernateUtil.closeTransactionSession();
        return skills;
    }
}
