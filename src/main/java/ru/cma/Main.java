package ru.cma;

import org.apache.log4j.Logger;
import ru.cma.connection.JDBCConnection;
import ru.cma.dao.StudentDao;

import java.util.Scanner;

public class Main {
    private static final Logger log = Logger.getLogger(Main.class);

    public static void main(String[] args) {
        mainMenu();
    }

    private static void mainMenu() {
        System.out.println();
        System.out.println("=============Menu===========");
        System.out.println("Enter 1 to see full database");
        System.out.println("Enter 2 to add student");
        System.out.println("Enter 3 to delete student");
        System.out.println("Enter 4 to exit");
        choice();
    }

    private static void choice() {
        Scanner scanner = new Scanner(System.in);
        StudentDao studentDao = new StudentDao(new JDBCConnection().getStatement());
        try {
            int choice = Integer.parseInt(scanner.next());
            switch (choice) {
                case 1:
                    studentDao.GetAllStudent();
                    mainMenu();
                    break;
                case 2:
                    System.out.println("Input first name");
                    String firstName = scanner.next();
                    System.out.println("Input middle name");
                    String middleName = scanner.next();
                    System.out.println("Input last name");
                    String lastName = scanner.next();
                    System.out.println("Input birthday");
                    String birthday = scanner.next();
                    System.out.println("Input group");
                    String group = scanner.next();
                    studentDao.addStudent(firstName, middleName, lastName, birthday, group);
                    mainMenu();
                    break;
                case 3:
                    System.out.println("Input student id to delete student");
                    try {
                        studentDao.deleteStudent(scanner.nextInt());
                    } catch (Exception e) {
                        log.info("Input correct id");
                    }
                    mainMenu();
                    break;
                case 4:
                    scanner.close();
                    System.exit(0);
                default:
                    throw new Exception("Incorrect choice");

            }
        } catch (Exception e) {
            log.info("Error. Input number from 1-4");
            mainMenu();
        }
    }
}