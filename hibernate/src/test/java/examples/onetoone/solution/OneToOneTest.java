package examples.onetoone.solution;

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

	@Autowired
	private SessionFactory sessionFactory;

	@Test
	@SuppressWarnings("Duplicates")
	public void oneToOneLazyIssueResolved() {
		doInHibernate(() -> sessionFactory, session -> {
			Employee employee = new Employee();
			employee.setName("name");

			session.save(employee);

			EmployeeDetails details = new EmployeeDetails();
			details.setDetails("details");
			details.setEmployee(employee);

			session.save(details);
		});

		doInHibernate(() -> sessionFactory, session -> {
			Employee employee = session.get(Employee.class, 1L);

			EmployeeDetails details = session.get(EmployeeDetails.class, employee.getId());

			System.out.println(details);
			System.out.println(employee);
		});
	}
}
