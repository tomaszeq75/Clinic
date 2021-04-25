import model.Appointment;
import model.Client;
import model.Doctor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class ClinicDAO {

    private Session session;
    private SessionFactory factory;


    public boolean addDoctor(Doctor doctor) {
        List<Doctor> doctors = getAllDoctors();
        if (doctors.contains(doctor)) {
            System.out.println("Taki lekarz już istnieje");
            return false;
        }
        initialize();
        Transaction transaction = session.beginTransaction();
        session.persist(doctor);
        transaction.commit();
        close();
        return true;
    }

    public boolean removeDoctor(Doctor doctor) {
        return true;
    }

    public boolean modifyDoctor(Doctor doctor) {
        return true;
    }

    public List<Doctor> getAllDoctors() {
        List<Doctor> doctors = new ArrayList<>();
        initialize();
        doctors = session.createQuery("from Doctor").getResultList();
        close();
        return doctors;
    }

    public boolean addClient(Client client) {
        return true;
    }

    public void modifyClient(Client client) {

    }

    public List<Appointment> getAppsByDoctor(Doctor doctor) {
        List<Appointment> appointments = new ArrayList<>();
        return appointments;
    }

    public boolean setAppById(int drId, String pesel) {
        return true;
    }

    public void getVisitsByClientPesel(String pesel) {
        List<Appointment> appointments = new ArrayList<>();
        String query = "select a from Appointment a where a.client.pesel = :pesel";
        initialize();

        Query query1 = session.createQuery(query);
        query1.setParameter("pesel", pesel);
        appointments = query1.getResultList();
        System.out.println(appointments);

        close();
        System.out.println(appointments);
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
