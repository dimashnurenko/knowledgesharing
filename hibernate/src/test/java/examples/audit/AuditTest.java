package examples.audit;

import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hibernate.testing.transaction.TransactionUtil.doInHibernate;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AuditTest {

	private static SessionFactory sessionFactory;

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		AuditTest.sessionFactory = sessionFactory;
	}

	private void setUp() {
		doInHibernate(() -> sessionFactory, session -> {
			Message message = new Message();
			message.setContent("content");
			session.save(message);
		});
	}

	@Test
	public void messageShouldBeStored() {
		setUp();

		doInHibernate(() -> sessionFactory, session -> {
			Message message = session.get(Message.class, 1L);

			assertThat(message.getCreatedDate(), is(notNullValue()));
			assertThat(message.getCreatedBy(), is(notNullValue()));
		});
	}

	@Test
	public void messageShouldBeUpdated() {
		setUp();

		doInHibernate(() -> sessionFactory, session -> {
			SecurityContext.token = "token2";
			Message message = session.get(Message.class, 1L);

			message.setContent("content3");
		});

		doInHibernate(() -> sessionFactory, session -> {
			Message message = session.get(Message.class, 1L);

			assertThat(message.getCreatedDate(), is(notNullValue()));
			assertThat(message.getCreatedBy(), is(notNullValue()));
			assertThat(message.getUpdatedDate(), is(notNullValue()));
			assertThat(message.getUpdatedBy(), is(equalTo(2L)));
		});
	}
}
