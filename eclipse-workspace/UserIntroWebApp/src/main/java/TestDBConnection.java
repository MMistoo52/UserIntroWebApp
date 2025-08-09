import java.sql.Connection;

public class TestDBConnection {
    public static void main(String[] args) throws Exception {
        try (Connection conn = DBUtil.getConnection()) {
            System.out.println("✅ Connected!");
        }
    }
}
