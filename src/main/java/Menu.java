import model.*;

import java.util.List;
import java.util.Scanner;

public class Menu {
    public static void main(String[] args) {


        ClinicDAO clinicDAO = new ClinicDAO();


        Scanner keyboard = new Scanner(System.in);
        String user ="";

        while (!user.equals("exit")) {
            System.out.println("MENU:");
            System.out.print("1. Dodaj klienta |");
            System.out.print("2. Dodaj wizytę |");
            System.out.print("3. Edytuj klienta |");
            System.out.print("4. Edytuj wizytę |");
            System.out.print("5. Pokaż wizyty klienta |");
            System.out.print("6. Dodaj lekarza |");
            System.out.print("7. Usuń lekarza |");
            System.out.print("8. Edytuj lekarza |");
            System.out.print("9. Pokaż wszystkich lekarzy\n");
            System.out.print("10. Pokaż wizyty lekarza |");
            System.out.println("exit. Wyjście z programu.");
            user = keyboard.nextLine();
            if (user.equals("1")) {
                System.out.println("Podaj pesel nowego klienta");
                String pesel = keyboard.nextLine();
                System.out.println("Podaj Imię nowego klienta");
                String firstName = keyboard.nextLine();
                System.out.println("Podaj nazwisko nowego klienta");
                String lastName = keyboard.nextLine();
                System.out.println("Podaj miasto");
                String city = keyboard.nextLine();
                System.out.println("Podaj ulicę");
                String street = keyboard.nextLine();
                System.out.println("Podaj numer ulicy");
                String streetNumber = keyboard.nextLine();
                System.out.println("Podaj numer telefonu");
                String phone = keyboard.nextLine();
                ClientDTO client = new ClientDTO(pesel,firstName,lastName,city,street,
                        streetNumber,phone);
                clinicDAO.addClient(client);
            } else if (user.equals("2")) {
                System.out.println("Podaj termin wizyty (format daty)");
                String visit = keyboard.nextLine();
//                while (visit == true) {
//                    System.out.println("Termin jest zajęty, podaj inny");
//                    visit = keyboard.nextLine();
//                }
                System.out.println("Wybrany termin to " + visit);
                System.out.println("Podaj pesel pacjenta, ktorego przypisać do wizyty");
                String pesel = keyboard.nextLine();
                //createVisit
            } else if (user.equals("3")) {
                System.out.println("Podaj pesel klienta do edycji");
                String pesel = keyboard.nextLine();
                ClientDTO client = new ClientDTO();
                client.setPesel(pesel);
                clinicDAO.modifyClient(client);
                // todo uzupełnić resztę danych
//                client.getPesel();
//                client.
            } else if (user.equals("4")) {
                System.out.println("Podaj wizytę którą chcesz zmodyfikować");
                String visit = keyboard.nextLine();
                //modifyVisit
            } else if (user.equals("5")) {
                System.out.println("Podaj pesel");
                String pesel = keyboard.nextLine();
                List<AppointmentDetails> visitsByClientPesel = clinicDAO.getVisitsByClientPesel(pesel);
                System.out.println(visitsByClientPesel);
            } else if (user.equals("6")) {
                System.out.println("Podaj imię lekarza");
                String firstName = keyboard.nextLine();
                System.out.println("Podaj Nazwisko lekarza");
                String lastName = keyboard.nextLine();
                System.out.println("Podaj specjlalizację");
                String spec = keyboard.nextLine();
                System.out.println("Podaj numer pokoju");
                String room = keyboard.nextLine();
                DoctorDTO doctor = new DoctorDTO(firstName, lastName, spec, room);
                clinicDAO.addDoctor(doctor);
            } else if (user.equals("7")) {
                System.out.println("Podaj Id lekarza");
                int doctorId = keyboard.nextInt();
                clinicDAO.removeDoctor(doctorId);
            } else if (user.equals("8")) {
                System.out.println("Podaj Id lekarza");
                int doctorId = keyboard.nextInt();
                DoctorDTO doctorDTO = new DoctorDTO();
                clinicDAO.modifyDoctor(doctorId,doctorDTO);
            } else if (user.equals("9")) {
                System.out.println(clinicDAO.getAllDoctors());
            } else if (user.equals("10")) {
                System.out.println("Podaj lekarza");
                String doctor = keyboard.nextLine();
                Doctor doctor1 = new Doctor();
                clinicDAO.getAppsByDoctor(doctor1);
            }
        }
    }
}
