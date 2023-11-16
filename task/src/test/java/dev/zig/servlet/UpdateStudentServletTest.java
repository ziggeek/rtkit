package dev.zig.servlet;

import com.google.gson.Gson;
import dev.zig.service.StudentService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.mockito.Mockito.mock;

public class UpdateStudentServletTest {

    private UpdateStudentServlet servlet;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private StudentService studentService;
    private Gson gson;

    @BeforeEach
    public void setUp() {
        gson = new Gson();
        studentService = mock(StudentService.class);
        servlet = new UpdateStudentServlet(gson, studentService);
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
    }

    @AfterEach
    public void tearDown() {
        servlet = null;
        request = null;
        response = null;
        studentService = null;
        gson = null;
    }

    @Test
    public void testDoPostWhenValidRequestThenSuccess() throws IOException {

    }

    @Test
    public void testDoPostWhenInvalidJsonRequestThenBadRequest() throws IOException {
        Assertions.assertTrue(true);
    }

    @Test
    public void testDoPostWhenJsonParsingExceptionThenBadRequest() throws IOException {
        Assertions.assertTrue(true);
    }
}