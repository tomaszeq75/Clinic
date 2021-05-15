import model.AppointmentDetails;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class VisitFactory {

    private ClinicDAO clinicDAO;
    private List<AppointmentDetails> appointments;
    private final Scanner scanner;

    private int doctorId;
    private LocalDate startDay, endDay;
    private LocalTime startTime, endTime;

    public VisitFactory(ClinicDAO clinicDAO) {
        this.clinicDAO = clinicDAO;
        scanner = new Scanner(System.in);
    }

    public void addAppointments() {

        int period = 30;
        LocalDate currentDay = startDay;
        LocalTime currentTime = startTime;
        while (currentDay.isBefore(endDay.plusDays(1))) {
            while (currentTime.isBefore(endTime)) {
                System.out.println(currentDay + " " + currentTime);
                currentTime = currentTime.plusMinutes(30);
            }
            currentDay = currentDay.plusDays(1);
            currentTime = startTime;
        }
    }

    private boolean checkIfVisitTermExist(LocalDateTime dateTime) {

        if (appointments.stream().anyMatch(a -> a.getDateTime().equals(dateTime))) {
            return true;
        } else {
            return false;
        }
    }

    private void getAppointments(int doctorId) {
        appointments = clinicDAO.getAppByDoctorId(doctorId, false);
    }

    public void menu() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        String choice = "";
        do {
            showMenu();
            choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    setAppointmentsDetailsToAdd();
                    addAppointments();
                    break;
                case "2":
                    setAppointmentsDetailsToRemove();
            }

        } while (!"x".equalsIgnoreCase(choice));


    }

    private void setAppointmentsDetailsToRemove() {
        System.out.println("Podaj ID lekarza");
        doctorId = getDoctorId();
        System.out.println("Podaj pierwszy dzień w formacie 'yyyy-mm-dd'");
        startDay = getDate();
        System.out.println("Podaj ostatni dzień w formacie 'yyyy-mm-dd'");
        endDay = getDate();
    }
    private void setAppointmentsDetailsToAdd() {


        System.out.println("Podaj ID lekarza");
        doctorId = getDoctorId();
        System.out.println("Podaj pierwszy dzień w formacie 'yyyy-mm-dd'");
        startDay = getDate();
        System.out.println("Podaj ostatni dzień w formacie 'yyyy-mm-dd'");
        endDay = getDate();
        System.out.println("Podaj godzinę rozpoczęcia pracy w formacie 'hh:mm'");
        startTime = getTime();
        System.out.println("Podaj godzinę zakończenia pracy w formacie 'hh:mm'");
        endTime = getTime();
    }

    private int getDoctorId() {
        int id = Integer.parseInt(scanner.nextLine());
        return id;
    }

    private LocalDate getDate() {
        LocalDate date = null;
        do {
            try {
                date = LocalDate.parse(scanner.nextLine());
            } catch (DateTimeParseException e) {
                System.out.println("Błedny format daty, musi być podana w formacie 'yyyy-mm-dd'");
            }
        } while (date == null);
        return date;
    }

    private LocalTime getTime() {
        LocalTime time = null;
        do {
            try {
                time = LocalTime.parse(scanner.nextLine());
            } catch (DateTimeParseException e) {
                System.out.println("Błedny format godziny, musi być podana w formacie 'hh:mm'");
            }
        } while (time == null);
        return time;
    }

    private void showMenu() {
        System.out.println("1. Dodaj harmonogram wizyt lekarza");
        System.out.println("2. Usuń harmonogram wizyt lekarza");
        System.out.println("x. Wyjście");
    }

}
