import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DBUtil {

    public static Connection getConnection() throws Exception {
        // DEBUG: where is the classloader looking?
        System.out.println("DEBUG URL  = " + DBUtil.class.getClassLoader().getResource("dbconfig.properties"));
        System.out.println("DEBUG ROOT = " + DBUtil.class.getClassLoader().getResource(""));

        Properties props = new Properties();
        InputStream in = DBUtil.class.getClassLoader().getResourceAsStream("dbconfig.properties");
        if (in == null) {
            throw new Exception("dbconfig.properties not found on classpath");
        }
        props.load(in);

        String url  = props.getProperty("db.url");       // jdbc:mysql://localhost:3306/userintrodb
        String user = props.getProperty("db.username");   // root
        String pass = props.getProperty("db.password");   // your pw

        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(url, user, pass);
    }
}
