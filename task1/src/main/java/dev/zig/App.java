package dev.zig;

import dev.zig.model.Students;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Path path = Path.of("task1","src", "main", "resources", "csv", "students.csv");

        try (Scanner scanner = new Scanner(System.in)) {
            var lines = Files.readAllLines(path);
            Students students = Students.from(lines);
            System.out.println("Поиск по фамилии: ");
            String findStudentByLastname = scanner.next();

            var averageGradeForUpperclassmans = students.getAverageGradeForUpperclassmans();
            var excellentStudentsOver14YO = students.getExcellentStudentsOver14YO();
            var studentsByLastname = students.getStudentsByLastname(findStudentByLastname);

            System.out.printf("Средний балл старшекласника: %s \n" +
                            "Всего %s отличника старше 14 лет: %s \n" +
                            "Найдено %s совпадений по фамили \"%s\": %s \n" +
                            "Некорректные данные для %s строк: %s \n",
                    averageGradeForUpperclassmans,
                    excellentStudentsOver14YO.size(),
                    excellentStudentsOver14YO,
                    studentsByLastname.size(),
                    findStudentByLastname,
                    studentsByLastname,
                    students.getInvalidLines().size(),
                    students.getInvalidLines());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }



    }
}


