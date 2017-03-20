package com.trunov.departments.dao;

import com.trunov.departments.entity.Developer;
import com.trunov.departments.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by misha on 12.03.17.
 */
public class DevelopersDao {

    //todo: make these private
    SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    Session session = sessionFactory.openSession();
    Criteria criteria = session.createCriteria(Developer.class);
    DepartmentsDao departmentsDao = new DepartmentsDao();

    public Developer getById(long id){
        Object developer = null;
        try {
            session.beginTransaction();
            developer = criteria.add(Restrictions.eq("id", id)).uniqueResult();
            session.getTransaction().commit();
        }catch (Exception e){
            session.getTransaction().rollback();
            System.out.println(e.getMessage());
        }
        return (Developer) developer;
    }

    public List<Developer> getAll(){
        List<Developer> developers = new ArrayList<>();
        try{
            session.beginTransaction();
            developers = session.createCriteria(Developer.class).list();
            session.getTransaction().commit();
        }catch (Exception e){
        session.getTransaction().rollback();
        System.out.println(e.getMessage());
    }
        return developers;
    }

    public List<Developer> getAllByDepartmentByName(String departmentName){
        List<Developer> developers = new ArrayList<>();
        try{
            session.beginTransaction();
            developers = criteria.add(Restrictions.eq("department.id", departmentsDao.getIdByName(departmentName))).list();
            session.getTransaction().commit();
        }catch (Exception e){
            session.getTransaction().rollback();
            System.out.println(e.getMessage());
        }
        return developers;
    }

    public void save(Developer developer){
        try {
            session.beginTransaction();
            session.save(developer);
            session.getTransaction().commit();
        }catch (Exception e){
            session.getTransaction().rollback();
            System.out.println(e.getMessage());
        }
    }

    public void removeById(Developer developer){
        try {
            session.beginTransaction();
            session.load(developer, developer.getId());
            session.delete(developer);
            session.getTransaction().commit();
        }catch (Exception e){
            session.getTransaction().rollback();
            System.out.println(e.getMessage());
        }
    }

    public void updateById(Developer developer){
        try{
            session.beginTransaction();
            session.update(developer);
            session.getTransaction().commit();
        }catch (Exception e){
            session.getTransaction().rollback();
            System.out.println(e.getMessage());
        }
    }

    public List<Developer> search(int developerAge , long departmentId){
        List<Developer> developers = null;
        try {
            session.beginTransaction();
            criteria.add(Restrictions.eq("age", developerAge)).list();
            developers = criteria.add(Restrictions.eq("department.id", departmentId)).list();
            session.getTransaction().commit();
        }catch (Exception e) {
            session.getTransaction().rollback();
            System.out.println(e.getMessage());
        }
        return developers;
    }

    public List count(){
        String HQL_QUERY_COUNT = "select dep.name from Department dep join dep.developers dev group by dev.id";
        List list = null;
        try{
            session.beginTransaction();
            Query query = session.createQuery(HQL_QUERY_COUNT);
            list = query.list();
            session.getTransaction().commit();
        }catch (Exception e) {
            session.getTransaction().rollback();
            System.out.println(e.getMessage());
        }
        return list;
    }
}
