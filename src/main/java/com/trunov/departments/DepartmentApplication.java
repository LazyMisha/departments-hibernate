package com.trunov.departments;

import com.trunov.departments.dao.DepartmentsDao;
import com.trunov.departments.dao.DevelopersDao;
import com.trunov.departments.dao.ManagersDao;
import com.trunov.departments.entity.Department;
import com.trunov.departments.entity.Developer;
import com.trunov.departments.entity.Manager;
import com.trunov.departments.util.HibernateUtil;
import com.trunov.departments.util.PrintList;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by misha on 12.03.17.
 */
class DepartmentApplication {

    //private static final Logger log = Logger.getLogger(DepartmentApplication.class);

    private String str;
    //todo: List<String> list = new ArrayList<>();
    private ArrayList<String> list = new ArrayList<>();

    void run(){
        System.out.println("please type help for view list of all COMMANDS!");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while (true){
            try {
                str = reader.readLine();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
            if (str.equals("exit")) {
                HibernateUtil.shutdown();
                break;
            } else {
                String[] arr = str.split(" ");
                Collections.addAll(list, arr);
                switch (list.get(0)) {
                        // list of COMMANDS
                    case "help":
                        list.clear();
                        for (String command : PrintList.COMMANDS) {
                            System.out.println(command);
                        }
                        break;
                        // list of departments
                    case "departments":
                        DepartmentsDao departmentsDao = new DepartmentsDao();
                        for (Department department : departmentsDao.getAll()){
                            System.out.println(department.toString());
                        }
                        list.clear();
                        break;
                        // getAll COMMANDS
                    case "getAll":
                        if (list.size() != 3) {
                            System.out.println("not correct command, please type help for vew all COMMANDS!");
                            System.out.println("type \"getAll -e employee_id\" to watch employee details");
                            System.out.println("type \"getAll -d department_name\" to watch employees of this department");
                            list.clear();
                            break;
                        }else if (list.contains("-e")){
                            DevelopersDao developersDao = new DevelopersDao();
                            ManagersDao managersDao = new ManagersDao();
                            System.out.println("Developer with id " + list.get(2));
                            Developer dev = developersDao.getById(Integer.parseInt(list.get(2)));
                            System.out.println(dev.toString());

                            System.out.println("Manager with id " + list.get(2));
                            Manager man = managersDao.getById(Integer.parseInt(list.get(2)));
                            System.out.println(man.toString());
                            list.clear();
                            break;
                        }else if (list.contains("-d")){
                            DevelopersDao developersDao = new DevelopersDao();
                            ManagersDao managersDao = new ManagersDao();
                            System.out.println("All developers from department " + list.get(2));
                            for (Developer developer : developersDao.getAllByDepartmentByName(list.get(2))) {
                                System.out.println(developer.toString());
                            }
                            System.out.println("All managers from department " + list.get(2));
                            for (Manager manager : managersDao.getAllByDepartmentName(list.get(2))) {
                                System.out.println(manager.toString());
                            }
                            list.clear();
                            break;
                        }
                        // save COMMANDS
                    case "save":
                        if (list.contains("-d")) {
                            Department department = new Department(list.get(2));
                            DepartmentsDao departmentsDao1 = new DepartmentsDao();
                            departmentsDao1.save(department);
                            list.clear();
                            break;
                        }else if (list.size() != 14) {
                            System.out.println("not correct command, please type help for vew all COMMANDS!");
                            System.out.println("type \"save -e -n employee_name -ln last_name -t type(m for manager or d for developer) " +
                                    "-a age -dp department_id -l language(developers only) -m methodology(managers only)\" to save new employee");
                            list.clear();
                            break;
                        }else if (list.contains("-e")) {
                            //save developer
                            if (list.contains("d")) {
                                if (list.contains("-m")) {
                                    System.out.println("The developer does not have methodology field");
                                    list.clear();
                                    break;
                                }else {
                                    DevelopersDao developersDao = new DevelopersDao();
                                    Developer developer = new Developer(list.get(3), list.get(5),
                                            Integer.parseInt(list.get(9)),
                                            list.get(7), list.get(13), new Department(Long.parseLong(list.get(11))));
                                    developersDao.save(developer);
                                    list.clear();
                                    break;
                                }
                            }// save manager
                            else if (list.contains("m")) {
                                if (list.contains("-l")) {
                                    System.out.println("The manager does not have a language field");
                                    list.clear();
                                    break;
                                } else {
                                    ManagersDao managersDao = new ManagersDao();
                                    Manager manager = new Manager(list.get(3), list.get(5),
                                            Integer.parseInt(list.get(9)),
                                            list.get(7), list.get(13), new Department(Long.parseLong(list.get(11))));
                                    managersDao.save(manager);
                                    list.clear();
                                    break;
                                }
                            }
                        }
                        // remove COMMANDS
                    case "rm":
                        if (list.size() != 3) {
                            System.out.println("this command is not correct, please type help for view all COMMANDS!");
                            System.out.println("type \"rm -mn employee_id\" to remove manager");
                            System.out.println("type \"rm -dv employee_id\" to remove developer");
                            System.out.println("type \"rm -d department_id\" to remove department");
                            list.clear();
                            break;
                            // remove department
                        }else if (list.contains("-d")) {
                            Department department = new Department(Long.parseLong(list.get(2)));
                            DepartmentsDao departmentsDao1 = new DepartmentsDao();
                            departmentsDao1.removeById(department);
                            list.clear();
                            break;
                            // remove developer
                        } else if (list.contains("-dv")) {
                            Developer developer = new Developer(Long.parseLong(list.get(2)));
                            DevelopersDao developersDao = new DevelopersDao();
                            developersDao.removeById(developer);
                            list.clear();
                            break;
                            // remove manager
                        } else if (list.contains("-mn")) {
                            Manager manager = new Manager(Long.parseLong(list.get(2)));
                            ManagersDao managersDao = new ManagersDao();
                            managersDao.removeById(manager);
                            list.clear();
                            break;
                        }
                        // update COMMANDS
                    case "update":
                        if (list.size() != 15) {
                            System.out.println("this command is not correct, please type help for view all COMMANDS!");
                            System.out.println("type \"update -e employee_id -n employee_name -ln employee_lastname " +
                                    "-t type(m for manager or d for developer) -a age -dp department " +
                                    "-l language(developers only) -m methodology(manager only)\" to update employee information");
                            list.clear();
                            break;
                        }// update developer
                        else if (list.contains("d")) {
                            if (list.contains("-m")) {
                                System.out.println("The developer does not have methodology field");
                                list.clear();
                                break;
                            } else {
                                Developer developer = new Developer();
                                DevelopersDao developersDao = new DevelopersDao();
                                developer.setId(Integer.parseInt(list.get(2)));
                                developer.setName(list.get(4));
                                developer.setLastName(list.get(6));
                                developer.setType(list.get(8));
                                developer.setAge(Integer.parseInt(list.get(10)));
                                developer.setLanguage(list.get(14));
                                developer.setDepartment(new Department(Long.parseLong(list.get(12))));
                                developersDao.updateById(developer);
                                list.clear();
                                break;
                            }
                        }// update manager
                        else if (list.contains("m")) {
                            if (list.contains("-l")) {
                                System.out.println("The manager does not have a language field");
                                list.clear();
                                break;
                            } else {
                                Manager manager = new Manager();
                                ManagersDao managersDao = new ManagersDao();
                                manager.setId(Integer.parseInt(list.get(2)));
                                manager.setName(list.get(4));
                                manager.setLastName(list.get(6));
                                manager.setType(list.get(8));
                                manager.setAge(Integer.parseInt(list.get(10)));
                                manager.setMethodology(list.get(14));
                                manager.setDepartment(new Department(Long.parseLong(list.get(12))));
                                managersDao.updateById(manager);
                                list.clear();
                                break;
                            }
                        }
                        // all command
                    case "all":
                        if (list.size() != 1) {
                            System.out.println("this command is not correct, please type help for view all COMMANDS!");
                            System.out.println("type \"all\" for view all employees");
                            list.clear();
                            break;
                        } else {
                            DevelopersDao developersDao = new DevelopersDao();
                            ManagersDao managersDao = new ManagersDao();
                            for (Developer developer: developersDao.getAll()) {
                                System.out.println("Department: " + developer.getDepartment().getName() + "\n" +
                                        "Name: " + developer.getName() + "\n" +
                                        "Type: " + developer.getType() + "\n" +
                                        "Age: " + developer.getAge() + "\n");
                            }
                            for(Manager manager : managersDao.getAll()){
                                System.out.println("Department: " + manager.getDepartment().getName() + "\n" +
                                        "Name: " + manager.getName() + "\n" +
                                        "Type: " + manager.getType() + "\n" +
                                        "Age: " + manager.getAge() + "\n");
                            }
                            list.clear();
                            break;
                        }
                        // search command
                    case "search":
                        if (list.size() != 6) {
                            System.out.println("this command is not correct, please type help for view all COMMANDS!");
                            System.out.println("type \"search -e  -a age_to_search -d department_id\" for view all employees by age and department name");
                            list.clear();
                            break;
                        } else {
                            DevelopersDao developersDao = new DevelopersDao();
                            ManagersDao managersDao = new ManagersDao();

                            System.out.println("Developers with age: " + list.get(3)+ ", and department_id: " + list.get(5) + ":");
                            for(Developer developer : developersDao.search(Integer.parseInt(list.get(3)), Long.parseLong(list.get(5)))) {
                                System.out.println(developer.toString() + "\n");
                            }

                            System.out.println("Managers with age: " + list.get(3)+ ", and department_id: " + list.get(5) + ":");
                            for(Manager manager : managersDao.search(Integer.parseInt(list.get(3)), Long.parseLong(list.get(5)))) {
                                System.out.println(manager.toString() + "\n");
                            }
                            list.clear();
                            break;
                        }
                        // top command
                    case "top":
                        if (list.size() != 4) {
                            System.out.println("this command is not correct, please type help for view all COMMANDS!");
                            System.out.println("type \"top -d -t type_of_employee\" for view the department with the largest number of employees");
                            list.clear();
                            break;
                        }// top department of managers
                        else if (list.contains("d")) {
                            DevelopersDao developersDao = new DevelopersDao();
                            List list = developersDao.count();
                            System.out.println("max count of developers in the Department: " + list.get(0));
                            break;
                        }// top department of developers
                        else if (list.contains("m")) {
                            ManagersDao managersDao = new ManagersDao();
                            List list = managersDao.count();
                            System.out.println("max count of managers in the Department: " + list.get(0));
                            list.clear();
                            break;
                        }
                        // mistakes
                    default:
                        list.clear();
                        System.out.println("this command is not correct, please type help for view all COMMANDS!");
                        break;
                }
            }
        }
    }
}