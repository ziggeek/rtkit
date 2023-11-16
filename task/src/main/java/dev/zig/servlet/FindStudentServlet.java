package dev.zig.servlet;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import dev.zig.model.dto.request.AverageGradeByGroupSearchRequest;
import dev.zig.service.StudentService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import static dev.zig.util.UrlPath.FIND_AVERAGE_GRADE_STUDENTS_BY_GROUP;

@WebServlet(name = "UpdateStudentServlet", urlPatterns = FIND_AVERAGE_GRADE_STUDENTS_BY_GROUP)
public class FindStudentServlet extends HttpServlet {

    private final Gson gson = new Gson();
    private final StudentService studentService = new StudentService();

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
            AverageGradeByGroupSearchRequest req = gson.fromJson(String.valueOf(json), AverageGradeByGroupSearchRequest.class);
            System.out.println(String.format("request to %s: %S", FIND_AVERAGE_GRADE_STUDENTS_BY_GROUP, req));
            var respJson = studentService.findAverageGradeStudentsByGroup(req.getNumber());
            System.out.println("FINDING - successful");

            String resp = this.gson.toJson(respJson);

            PrintWriter out = response.getWriter();
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            out.print(resp);
            out.flush();

        } catch (JsonSyntaxException e) {
            throw new IOException("Error parsing JSON request string");
        }
    }
}
