import model.Doctor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class App {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        Session session = factory.openSession();

        List<Doctor> resultList = session.createQuery("from Doctor").getResultList();

        System.out.println(resultList);

        session.close();
        factory.close();
    }
}
