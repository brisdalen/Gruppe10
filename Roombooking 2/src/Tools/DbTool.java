package Tools;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbTool {
    Connection connection;
    Statement statement;

    public Connection dbLogIn(PrintWriter out) {
        try {
            Context context = new InitialContext();
            DataSource dataSource = (DataSource) context.lookup("java:comp/env/jdbc/localhost/Roombooking");
            connection = dataSource.getConnection();

            return connection;
        } catch (NamingException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void printResults(PrintWriter out) throws SQLException {
        out.print("yo");
        String strSelect = "Select * from User";
        statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(strSelect);
        out.print("Your results are:" + "\n");
        while (resultSet.next()) {
            out.print(resultSet.getString("User_firstName"));

        }
        out.print("query complete");
    }
}
