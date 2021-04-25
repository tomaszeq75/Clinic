import model.Doctor;

public class AppTW {
    public static void main(String[] args) {
        ClinicDAO clinicDAO = new ClinicDAO();

        Doctor doctor = new Doctor("Jan", "Kowalski", "okulista", "5");
        clinicDAO.addDoctor(doctor);
        System.out.println(clinicDAO.getAllDoctors());

        clinicDAO.removeDoctor(6);
        System.out.println(clinicDAO.getAllDoctors());



    }
}
