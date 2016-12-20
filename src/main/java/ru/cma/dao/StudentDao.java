package ru.cma.dao;

import org.apache.log4j.Logger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class StudentDao {
    private static final Logger log = Logger.getLogger(StudentDao.class);
    private Statement stm;

    public StudentDao(Statement stm) {
        this.stm = stm;
    }

    public void GetAllStudent() {
        String get = "SELECT * FROM school.students";
        try {
            ResultSet resultSet = stm.executeQuery(get);
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String middle_name = resultSet.getString(3);
                String last_name = resultSet.getString(4);
                String birthday = resultSet.getString(5);
                String group = resultSet.getString(6);
                System.out.printf("id: %d, name: %s, Middle name: %s, Last name %s, Birthday %s, Group %s %n", id, name,
                        middle_name, last_name, birthday, group);
            }
            log.info("Full list");
        } catch (SQLException e) {
            log.error("Some trouble while priting full database");
        }
    }

    public void deleteStudent(int id) {
        String delete = "DELETE FROM school.students WHERE student_id=" + id;
        try {
            stm.executeUpdate(delete);
            log.info("Row successfully deleted");
        } catch (SQLException e) {
            log.error("Some trouble while deleting row");
        }
    }

    public void addStudent(String firstName, String middleName, String lastName, String birthdate, String groupName) {
        String insert = "INSERT INTO school.students (first_name,middle_name,last_name,birthdate,group_name) VALUES ('" +
                firstName + "','" + middleName + "','" + lastName + "','" + birthdate + "','" + groupName + "')";

        try {
            stm.executeUpdate(insert);
            log.info("Row added");
        } catch (SQLException e) {
            log.error("Some trouble while adding row");
        }
    }
}
