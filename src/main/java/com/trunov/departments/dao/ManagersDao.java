package com.trunov.departments.dao;

import com.trunov.departments.entity.Manager;
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
public class ManagersDao {

    SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    Session session = sessionFactory.openSession();
    Criteria criteria = session.createCriteria(Manager.class);
    DepartmentsDao departmentsDao = new DepartmentsDao();

    public Manager getById(long id) {
        Object manager = null;
        try {
            session.beginTransaction();
            manager = criteria.add(Restrictions.eq("id", id)).uniqueResult();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            System.out.println(e.getMessage());
        }
        return (Manager) manager;
    }

    public List<Manager> getAll() {
        List<Manager> managers = new ArrayList<>();
        try {
            session.beginTransaction();
            managers = session.createCriteria(Manager.class).list();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            System.out.println(e.getMessage());
        }
        return managers;
    }

    public List<Manager> getAllByDepartmentName(String departmentName) {
        List<Manager> managers = new ArrayList<>();
        try {
            session.beginTransaction();
            managers = criteria.add(Restrictions.eq("department.id", departmentsDao.getIdByName(departmentName))).list();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            System.out.println(e.getMessage());
        }
        return managers;
    }

    public void save(Manager manager) {
        try {
            session.beginTransaction();
            session.save(manager);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            System.out.println(e.getMessage());
        }
    }

    public void removeById(Manager manager) {
        try {
            session.beginTransaction();
            session.load(manager, manager.getId());
            session.delete(manager);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            System.out.println(e.getMessage());
        }
    }

    public void updateById(Manager manager) {
        try {
            session.beginTransaction();
            session.update(manager);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            System.out.println(e.getMessage());
        }
    }

    public List<Manager> search(int managerAge, long departmentId) {
        List<Manager> managers = null;
        try{
            session.beginTransaction();
            criteria.add(Restrictions.eq("age", managerAge)).uniqueResult();
            managers = criteria.add(Restrictions.eq("department.id", departmentId)).list();
            session.getTransaction().commit();
        }catch (Exception e) {
            session.getTransaction().rollback();
            System.out.println(e.getMessage());
        }

        return managers;
    }

    public List count(){
        String HQL_QUERY_COUNT = "select dep.name from Department dep join dep.managers man group by man.id";
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