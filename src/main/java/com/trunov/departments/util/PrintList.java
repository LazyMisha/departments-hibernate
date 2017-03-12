package com.trunov.departments.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by misha on 12.03.17.
 */
public class PrintList {

    public static final List<String> COMMANDS = new ArrayList<>();

    static {
        COMMANDS.add(0, "1. type \"exit\" for quit from the application");
        COMMANDS.add(1, "2. type \"getAll -e employee_id\" to watch employee details");
        COMMANDS.add(2, "3. type \"getAll -d department_name\" to watch employees of this department");
        COMMANDS.add(3, "4. type \"rm -dv employee_id\" to remove developer");
        COMMANDS.add(4, "5. type \"rm -mn employee_id\" to remove manager");
        COMMANDS.add(5, "6. type \"rm -d department_name\" to remove department");
        COMMANDS.add(6, "7. type \"update -e employee_id -n employee_name -ln employee_lastname " +
                "-t type(m for manager or d for developer) -a age -dp department " +
                "-l language(developers only) -m methodology(manager only)\" to update employee information");
        COMMANDS.add(7, "8. type \"save -e -n employee_name -ln employee_lastname " +
                "-t type(m for manager or d for developer) -a age -dp department " +
                "-l language(developers only) -m methodology(managers only)\" to save new employee");
        COMMANDS.add(8, "9. type \"save -d department_name\" to save new department");
        COMMANDS.add(9, "10. type \"departments\" to return to the list of departments");
        COMMANDS.add(10, "11. type \"help\" for COMMANDS list");
        COMMANDS.add(11, "12. type \"all\" for view all employees");
        COMMANDS.add(12, "13. type \"search -e  -a age_to_search -d department\" for view all employees by age and department name");
        COMMANDS.add(13, "14. type \"top -d -t type_of_employee\" for view the department with the largest number of employees");
    }
}
