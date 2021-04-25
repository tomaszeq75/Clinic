import model.Client;

public class AppP {
    public static void main(String[] args) {

        Client client = new Client();
        System.out.println(client.getPesel());
        ClinicDAO clinicDAO = new ClinicDAO();
        clinicDAO.getVisitsByClientPesel("2222222222");
    }
}
