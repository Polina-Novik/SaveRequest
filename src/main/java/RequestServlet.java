import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/request")
public class RequestServlet extends HttpServlet {
    private static final String URL = "jdbc:mysql://localhost:3306/test_db";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name=req.getParameter("name");
        String number=req.getParameter("number");
        String message=req.getParameter("message");
        Map<String,String> errors=new HashMap<>();
        try{
            getNull(name);
        } catch (NullStringException e) {
            errors.put("name","enter your name");
        }
        try{
            Long.parseLong(number);
        } catch (Exception e){
            errors.put("number","number is not Long");
        }

        try{
            getNull(message);
        } catch (NullStringException e) {
            errors.put("message","enter your message");
        }
        if (errors.size()==0) {
            try {
                Class.forName(JDBC_DRIVER);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            Connection connection = null;
            try {
                connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                Statement statement = connection.createStatement();
            statement.execute("insert into request (name,number,message) values ('" + name + "'," + number+ ",'"+message+"'" + ")");
            req.getRequestDispatcher("success.jsp").forward(req,resp);}
            catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
            req.setAttribute("errors",errors);
            System.out.println(errors);
            req.getRequestDispatcher("safe-request.jsp").forward(req,resp);
        }

    }
    private static boolean getNull(String str) throws NullStringException {

        if (str.isEmpty()) throw new NullStringException();
        return true;
    }
}
