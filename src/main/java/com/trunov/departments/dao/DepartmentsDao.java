package com.trunov.departments.dao;

import com.trunov.departments.entity.Department;
import com.trunov.departments.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by misha on 12.03.17.
 */
public class DepartmentsDao {

    private Session session = HibernateUtil.getSessionFactory().openSession();

    public List<Department> getAll(){
        List<Department> departments = new ArrayList<>();
        try {
            session.beginTransaction();
            departments = session.createCriteria(Department.class).list();
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
            department = (Department) session.createCriteria(Department.class)
                    .add(Restrictions.eq("name", name))
                    .uniqueResult();
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

    public void remove(Department department) {
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