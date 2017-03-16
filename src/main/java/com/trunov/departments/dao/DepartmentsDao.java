package com.trunov.departments.dao;

import com.trunov.departments.entity.Department;
import com.trunov.departments.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by misha on 12.03.17.
 */
public class DepartmentsDao {

    SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    Session session = sessionFactory.openSession();
    Criteria criteria = session.createCriteria(Department.class);

    public List<Department> getAll(){
        List<Department> departments = new ArrayList<>();
        try {
            session.beginTransaction();
            departments = criteria.list();
            session.getTransaction().commit();
        }catch (Exception e) {
            session.getTransaction().rollback();
            System.out.println(e.getMessage());
        }
        return departments;
    }

    public long getIdByName(String name){
        Department department = null;
        try{
            session.beginTransaction();
            criteria.add(Restrictions.eq("name", name));
            department = (Department) criteria.uniqueResult();
            session.getTransaction().commit();
        }catch (Exception e){
            session.getTransaction().rollback();
            System.out.println(e.getMessage());
        }
        return department.getId();
    }

    public void save(Department department){
        try {
            session.beginTransaction();
            session.save(department);
            session.getTransaction().commit();
        }catch (Exception e){
            session.getTransaction().rollback();
            System.out.println(e.getMessage());
        }
    }

    public void removeById(Department department) {
        try {
            session.beginTransaction();
            session.load(department, department.getId());
            session.delete(department);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            System.out.println(e.getMessage());
        }
    }
}