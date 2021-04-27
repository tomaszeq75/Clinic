import model.*;
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

    private void getDoctorFromDTO(Doctor doctor, DoctorDTO doctorDTO) {
        doctor.setFirstName(doctorDTO.getFirstName());
        doctor.setLastName(doctorDTO.getLastName());
        doctor.setSpecializations(doctorDTO.getSpecializations());
        doctor.setRoom(doctorDTO.getRoom());
    }

    private void getClientFromDTO(Client client, ClientDTO clientDTO) {
        client.setPesel(clientDTO.getPesel());
        client.setFirstName(clientDTO.getFirstName());
        client.setLastName(clientDTO.getLastName());
        client.setCity(clientDTO.getCity());
        client.setStreet(clientDTO.getStreet());
        client.setStreetNumber(clientDTO.getStreetNumber());
        client.setPhone(clientDTO.getPhone());
    }

    public boolean addDoctor(DoctorDTO doctorDTO) {
        Doctor doctor = new Doctor();
        getDoctorFromDTO(doctor, doctorDTO);
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

    public void removeDoctor(int id) {
        Doctor doctor = new Doctor();
        doctor.setId(id);

        initialize();
        Transaction transaction = session.beginTransaction();
        session.remove(doctor);
        transaction.commit();
        close();
    }

    public boolean modifyDoctor(int id, DoctorDTO doctorDTO) {
        boolean result = true;
        initialize();
        Transaction transaction = session.beginTransaction();
        Doctor doctor = session.find(Doctor.class, id);
        if (doctor != null) {
            getDoctorFromDTO(doctor, doctorDTO);
            doctor.setId(id);
            session.update(doctor);
            transaction.commit();
            System.out.println("jest doktor");
        } else {
            result = false;
            System.out.println("nie ma doktora");
            transaction.rollback();
        }
        close();
        return true;
    }

    public List<Doctor> getAllDoctors() {
        List<Doctor> doctors = new ArrayList<>();
        initialize();
        doctors = session.createQuery("from Doctor").getResultList();
        close();
        return doctors;
    }

    public boolean addClient(ClientDTO clientDTO) {
        boolean result = true;
        Client client = new Client();
        getClientFromDTO(client, clientDTO);
        initialize();
        Transaction transaction = session.beginTransaction();
        if (session.find(Client.class, client.getPesel()) == null) {
            session.persist(client);
            transaction.commit();
            System.out.println("klient dodany");
        } else {
            transaction.rollback();
            result = false;
            System.out.println("taki klient już istnieje");
        }
        close();
        return result;
    }

    public boolean modifyClient(ClientDTO clientDTO) {
        boolean result = true;
        initialize();
        Transaction transaction = session.beginTransaction();
        Client client = session.find(Client.class, clientDTO.getPesel());
        if (client != null) {
            getClientFromDTO(client, clientDTO);
            session.update(client);
            transaction.commit();
            System.out.println("jest taki klient");
        } else {
            System.out.println("nie takiego klienta");
            transaction.rollback();
            result = false;
        }
        close();
        return result;
    }

    public List<Appointment> getAppsByDoctor(Doctor doctor) {
        List<Appointment> appointments = new ArrayList<>();
        return appointments;
    }

    public boolean setAppById(int drId, String pesel) {
        return true;
    }

    public List<AppointmentDetails> getAppByDoctorId(int drId, boolean freeOnly) {
        List<Appointment> appointments = new ArrayList<>();
        List<AppointmentDetails> appointmentDetails = new ArrayList<>();
        String queryString = "select a from Appointment a where a.doctor.id = :drId";
        if (freeOnly) {
            queryString += " and a.client.pesel is null";
        }
        initialize();
        Query query = session.createQuery(queryString);
        query.setParameter("drId", drId);
        appointments = query.getResultList();
        for (Appointment a : appointments) {
            appointmentDetails.add(getAppointmentDetails(a));
        }
        close();
        return appointmentDetails;
    }

    public List<AppointmentDetails> getVisitsByClientPesel(String pesel) {
        List<Appointment> appointments = new ArrayList<>();
        List<AppointmentDetails> appDetailsList = new ArrayList<>();
        String queryString = "select a from Appointment a where a.client.pesel = :pesel";
        initialize();

        Query query = session.createQuery(queryString);
        query.setParameter("pesel", pesel);
        appointments = query.getResultList();
        for (Appointment a : appointments) {
            appDetailsList.add(getAppointmentDetails(a));
        }
        close();
        return appDetailsList;
    }

    public boolean cancelVisit(String pesel, int visitNumber) {
        boolean result = true;
        return result;
    }

    private AppointmentDetails getAppointmentDetails(Appointment appointment) {
        AppointmentDetails appDetail = new AppointmentDetails();
        appDetail.setId(appointment.getId());
        if (appointment.getClient() != null) {
            appDetail.setPesel(appointment.getClient().getPesel());
            appDetail.setFirstName(appointment.getClient().getFirstName());
            appDetail.setFirstName(appointment.getClient().getLastName());
        }
        appDetail.setDrLastName(appointment.getDoctor().getLastName());
        appDetail.setRoom(appointment.getDoctor().getRoom());
        appDetail.setDateTime(appointment.getDateTime());
        return appDetail;
    }

    public boolean setAppointment(String pesel, int drId, int appId) {
        initialize();
        Appointment appointment = session.find(Appointment.class, appId);
        Doctor doctor = session.find(Doctor.class, drId);
        Client client = session.find(Client.class, pesel);
        if (appointment != null && doctor != null && client != null
                && appointment.getClient() == null && appointment.getDoctor() == doctor) {
            Transaction transaction = session.beginTransaction();
            appointment.setDoctor(doctor);
            appointment.setClient(client);
            transaction.commit();
            close();
            return true;
        }

        if (doctor == null) System.out.println("nie ma lekarza o id = " + drId);
        if (client == null) System.out.println("nie ma pacjenta o nr pesel = " + pesel);
        if (appointment == null) {
            System.out.println("nie ma terminu o id = " + appId);
        } else if (appointment.getClient() != null) {
            System.out.println("termin o id = " + appId + " jest już zajęty");
        } else if (appointment.getDoctor() != doctor) {
            System.out.println("termin o id = " + appId + " należy do doktora o id = " + appointment.getDoctor().getId());
        }

        close();
        return false;
    }

    public Client getClient(String pesel) {
        initialize();
        Client client = session.find(Client.class,pesel);

        close();
        if (client == null) {
            System.out.println("Nie ma takiego klienta");
        }
        return client;
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
