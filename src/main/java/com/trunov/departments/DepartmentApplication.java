package com.trunov.departments;

import com.trunov.departments.util.HibernateUtil;
import com.trunov.departments.util.PrintList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by misha on 12.03.17.
 */
public class DepartmentApplication {

    private String str;
    private String arr[];
    private ArrayList<String> list = new ArrayList<>();

    public void run(){
        HibernateUtil.getSessionFactory();

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
                arr = str.split(" ");
                for (String anArr : arr) {
                    list.add(anArr);
                }
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

                        // getAll COMMANDS
                    case "getAll":

                        // save COMMANDS
                    case "save":

                        // remove COMMANDS
                    case "rm":

                        // update COMMANDS
                    case "update":

                        // all command
                    case "all":

                        // search command
                    case "search":

                        // top command
                    case "top":

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
