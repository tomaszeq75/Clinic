import model.Doctor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;

public class ClinicDAO {

    private Session session;
    private SessionFactory factory;

    public List<Doctor> getAllDoctors() {
        List<Doctor> doctors = new ArrayList<>();
        initialize();
        doctors = session.createQuery("from Doctor").getResultList();
        close();
        return doctors;
    }

    public void initialize() {
        factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        session = factory.openSession();
    }

    public void close() {
        session.close();
        factory.close();
    }
}
