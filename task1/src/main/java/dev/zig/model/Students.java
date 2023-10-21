package dev.zig.model;

import dev.zig.utils.StudentUtils;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

@Getter
@Setter
//Так как не было жестоко запрета на использования стандартных коллекций решил не изобретать велосипед.
//Обоснование с точки зрения О-нотации: использовал везде ArrayList так как
// у меня нет операций на ставку в середину, а лишь в конец. Константное время выполнения с точки зрения о-нотации
public class Students {

    private List<Student> students = new ArrayList<>();
    private Map<Integer, List<Student>> sortedStudentsByGroup;
    private Map<Integer, List<Student>> sortedStudentsByAge;
    private Map<String, List<Student>> sortedStudentsByLastname;

    private List<String> invalidLines = new ArrayList<>();

    public static Students from(List<String> csvLines) {
        Students newObj = new Students();

        for (int i = 1; i < csvLines.size(); i++) {
            try {
                newObj.getStudents().add(Student.from(csvLines.get(i)));
            } catch (Exception e) {
                newObj.getInvalidLines().add(csvLines.get(i));
            }
        }

        newObj.setSortedStudentsByGroup();
        newObj.setSortedStudentsByAge();
        newObj.setSortedStudentsByLastname();
        return newObj;
    }


    public List<Student> getExcellentStudentsOver14YO() {
        var sortedByAverageGrade = StudentUtils.sortStudentsByAverageGrade(students);
        return sortedByAverageGrade.get(5).stream().filter(student -> student.getAge() >= 14).collect(toList());
    }


    public Double getAverageGradeForUpperclassmans() {

        double sum = students.parallelStream()
                .filter(Student::getIsUpperclassman)
                .mapToDouble(student -> student.getPerformance().getAverageGrade())
                .sum();

        long count = students.parallelStream()
                .filter(Student::getIsUpperclassman)
                .count();

        return count > 0 ? sum / count : 0.0;
    }


    public List<Student> getStudentsByLastname(String lastname) {
        return students.stream().filter(student -> student.getLastname().equals(lastname)).collect(toList());
    }


    private void setSortedStudentsByGroup() {
        this.sortedStudentsByGroup = (Map<Integer, List<Student>>) StudentUtils.sortStudentsBy(students, "group");
    }


    private void setSortedStudentsByAge() {
        this.sortedStudentsByAge = (Map<Integer, List<Student>>) StudentUtils.sortStudentsBy(students, "age");
    }


    private void setSortedStudentsByLastname() {
        this.sortedStudentsByLastname = (Map<String, List<Student>>) StudentUtils.sortStudentsBy(students, "lastname");
    }


}


