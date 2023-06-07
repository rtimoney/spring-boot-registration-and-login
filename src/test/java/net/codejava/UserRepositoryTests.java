package net.codejava;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryTests { 

	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	private UserRepository repo;
	

	@Test
	public void testCreateUser(){
		User user = new User();
		user.setEmail("testemail2@gmail.com");
		user.setPassword("password2023");
		user.setFirstName("Testjohn");
		user.setLastName("Testsmith");
		user.setAddress1("line 1");
		user.setTitle("mr");
		user.setPostcode("MCR500");
		
		User savedUser = repo.save(user);
		
		User existUser = entityManager.find(User.class, savedUser.getId());
		
		assertThat(user.getEmail()).isEqualTo(existUser.getEmail());

	}
	
	@Test
	public void testFindByEmail() {
		String email = "testemail@gmail.com";
		User user = repo.findByEmail(email);
		
		assertThat(user.getEmail()).isEqualTo(email);
	}
}
