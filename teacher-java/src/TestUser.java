import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.database.Users;

class TestUser {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void testInsert() {
		Users users = new Users("username", "userPassword", "1997-10-09", "0", "0", "116@qq.com", "110", "123", "....");
		users.insert();
	}

}
