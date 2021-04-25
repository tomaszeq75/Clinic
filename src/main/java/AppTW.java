import model.AppointmentDetails;
import model.ClientDTO;
import model.Doctor;
import model.DoctorDTO;

import java.util.List;

public class AppTW {
    public static void main(String[] args) {
        ClinicDAO clinicDAO = new ClinicDAO();

//        DoctorDTO doctorDTO = new DoctorDTO("Jan", "Kowalski", "okulista", "5");
//        clinicDAO.addDoctor(doctorDTO);
//
//        System.out.println(clinicDAO.getAllDoctors());
//
//        doctorDTO.setFirstName("Janusz");
//        clinicDAO.modifyDoctor(8, doctorDTO);
//        System.out.println(clinicDAO.getAllDoctors());
//
//        clinicDAO.removeDoctor(7);
//        clinicDAO.removeDoctor(8);

//        ClientDTO clientDTO = new ClientDTO();
//        clientDTO.setPesel("00000000001");
//        clientDTO.setFirstName("zzzz");
//        clientDTO.setLastName("ccccc");
//        clientDTO.setCity("łódź");
//        clientDTO.setStreet("Polna");
//        clientDTO.setStreetNumber("122/7");
//        clientDTO.setPhone("997");
//
//        clinicDAO.addClient(clientDTO);
//        clinicDAO.modifyClient(clientDTO);

//        List<AppointmentDetails> visitsByClientPesel = clinicDAO.getVisitsByClientPesel("00000000001");
//        System.out.println(visitsByClientPesel);

        List<AppointmentDetails> appByDoctorId = clinicDAO.getAppByDoctorId(1, false);
        appByDoctorId.forEach(System.out::println);
        appByDoctorId = clinicDAO.getAppByDoctorId(1, true);
        appByDoctorId.forEach(System.out::println);


    }
}
