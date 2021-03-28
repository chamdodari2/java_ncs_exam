package java_ncs_exam.dao.dbconn;

import java.sql.Connection;

import org.junit.Assert;
import org.junit.Test;

public class JdbcConnTest {

	@Test
	public void testGetConnection() {
		System.out.printf("%s()%n","testGetConnection");
		Connection con = JdbcConn.getConnection();
		System.out.println("con >" + con);
		Assert.assertNotNull(con);   ///여기 주의!
	}

}
