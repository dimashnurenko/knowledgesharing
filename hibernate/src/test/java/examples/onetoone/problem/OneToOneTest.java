package examples.onetoone.problem;

import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hibernate.testing.transaction.TransactionUtil.doInHibernate;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OneToOneTest {
	private static SessionFactory sessionFactory;

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		OneToOneTest.sessionFactory = sessionFactory;
	}

	/**
	 * For every managed entity, the Persistence Context requires both the entity type and the identifier,
	 * so the child identifier must be known when loading the parent entity.
	 */
	@SuppressWarnings("Duplicates")
	@Test
	public void oneToOneLazyIssue() {
		doInHibernate(() -> sessionFactory, session -> {
			User user = new User();
			session.save(user);

			Address address = new Address();
			address.setZip("zip");
			address.setUser(user);
			session.save(address);

			user.setAddress(address);
		});

		doInHibernate(() -> sessionFactory, session -> {
//			User user = session.get(User.class, 1L);

			Address address = session.get(Address.class, 1L);

//			System.out.println(user);
			System.out.println(address);
		});
	}
}
