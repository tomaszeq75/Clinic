import model.Doctor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class App {
    public static void main(String[] args) {

        ClinicDAO clinicDAO = new ClinicDAO();
        System.out.println(clinicDAO.getAllDoctors());

    }
}
