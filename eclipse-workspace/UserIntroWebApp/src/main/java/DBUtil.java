import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DBUtil {
    public static Connection getConnection() throws Exception {
        // Load dbconfig.properties from classpath (WEB-INF/classes or src/main/resources)
        Properties props = new Properties();
        InputStream in = DBUtil.class.getClassLoader().getResourceAsStream("dbconfig.properties");
        if (in == null) {
            throw new Exception("dbconfig.properties not found on classpath");
        }
        props.load(in);

        String url  = props.getProperty("db.url");
        String user = props.getProperty("db.username");
        String pass = props.getProperty("db.password");

        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(url, user, pass);
    }
}
