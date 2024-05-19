package tr.com.nemesis.core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// ObjectHelper sınıfı, veritabanı işlemleri için yardımcı metodları içeren bir yardımcı sınıftır.
public class ObjectHelper {
	// Veritabanı bağlantısı için kullanıcı adı
	private String username = "root";
	// Veritabanı bağlantısı için şifre
	private String password = "123";
	// Veritabanı bağlantısı için URL
	private String url = "jdbc:mysql://localhost:3306/dbProject";
	// Kullanılacak JDBC sürücüsü sınıfının adı
	private static String driver = "com.mysql.cj.jdbc.Driver";
	
	// Statik blok, JDBC sürücüsünün yüklenmesini sağlar
	static {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	// getConnection() metodu, veritabanına bağlantı sağlar
	protected Connection getConnection() {
		Connection connection = null;
		
		try {
			// DriverManager sınıfını kullanarak veritabanına bağlantı oluşturulur
			connection = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return connection;
	}
}
