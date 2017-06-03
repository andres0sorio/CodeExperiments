package fichedbsvc;

import java.sql.SQLException;
import java.util.UUID;

import org.junit.Test;
import org.sql2o.Sql2o;
import org.sql2o.converters.UUIDConverter;
import org.sql2o.quirks.PostgresQuirks;

public class ConnectionTests {

	@Test
	public void connectSql2o() {

		String address = new String("jdbc:postgresql://localhost:5432/fichedb");
		String dbUsername = new String("aosorio");
		String dbPassword = new String("sparkforthewin");

		Sql2o sql2o = new Sql2o(address, dbUsername, dbPassword, new PostgresQuirks() {
			{
				// make sure we use default UUID converter.
				converters.put(UUID.class, new UUIDConverter());
			}
		});
		
		try {
			sql2o.getDataSource().getConnection();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}

	}

}
