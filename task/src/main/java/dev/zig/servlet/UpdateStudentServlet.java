package dev.zig.servlet;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import dev.zig.model.dto.request.UpdateGradeStudentByFioAndGroupRequest;
import dev.zig.service.StudentService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

import static dev.zig.util.UrlPath.UPDATE_GRADE_STUDENT_BY_FIO_AND_GROUP;

@WebServlet(name = "UpdateStudentServlet", urlPatterns = UPDATE_GRADE_STUDENT_BY_FIO_AND_GROUP)
public class UpdateStudentServlet extends HttpServlet {

    private final Gson gson;
    private final StudentService studentService;

    public UpdateStudentServlet(Gson gson, StudentService studentService) {
        this.gson = gson;
        this.studentService = studentService;
    }

    @Override
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws IOException {

        StringBuffer json = new StringBuffer();
        String line;

        try {
            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null)
                json.append(line);
        } catch (Exception e) {
            throw new IOException("Bad request");
        }

        try {
            UpdateGradeStudentByFioAndGroupRequest req = gson.fromJson(String.valueOf(json), UpdateGradeStudentByFioAndGroupRequest.class);
            System.out.println(String.format("request to %s: %S", UPDATE_GRADE_STUDENT_BY_FIO_AND_GROUP, req));
            studentService.updateGradeStudentByFioAndGroup(req.getFirstname(), req.getLastname(), req.getNumber(), req.getSubject(), req.getGrade());
            System.out.println("UPDATING - successful");
        } catch (JsonSyntaxException e) {
            throw new IOException("Error parsing JSON request string");
        }
    }
}
