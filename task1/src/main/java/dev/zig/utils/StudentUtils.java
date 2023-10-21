package dev.zig.utils;

import dev.zig.model.Student;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@UtilityClass
public class StudentUtils {


    public static Map<Integer, List<Student>> sortStudentsByAverageGrade(List<Student> students) {
        Map<Integer, List<Student>> result = new HashMap<>();
        for (Student student : students) {
            result.compute(student.getPerformance().getAverageGrade().intValue(), (k, v) -> sort(student, v));
        }
        return result;
    }

    public static Map<?, List<Student>> sortStudentsBy(List<Student> students, String sortType) {

        Map<String, List<Student>> stringKeyResult = new HashMap<>();
        Map<Integer, List<Student>> intKeyResult = new HashMap<>();

        switch (sortType) {
            case "age":
                for (Student student : students) {
                    intKeyResult.compute(student.getAge(), (k, v) -> sort(student, v));
                }
                return intKeyResult;
            case "group":
                for (Student student : students) {
                    stringKeyResult.compute(student.getGroup(), (k, v) -> sort(student, v));
                }
                return stringKeyResult;
            case "lastname":
                for (Student student : students) {
                    stringKeyResult.compute(String.valueOf(student.getLastname().charAt(0)), (k, v) -> sort(student, v));
                }
                return stringKeyResult;
            default:
                throw new RuntimeException(String.format("Неизвестный формат сортировки: %s", sortType));
        }

    }

    private static List<Student> sort(Student student, List<Student> list) {
        if (list == null) {
            var objs = new ArrayList<Student>();
            objs.add(student);
            return objs;
        } else {
            list.add(student);
            return list;
        }
    }

}
