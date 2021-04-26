import model.Client;
import model.ClientDTO;

public class AppP {
    public static void main(String[] args) {

        Client client = new Client();
//        System.out.println(client.getPesel());
        ClinicDAO clinicDAO = new ClinicDAO();
//        clinicDAO.getVisitsByClientPesel("2222222222");
        ClientDTO clientDTO = new ClientDTO();
//        System.out.println(clientDTO.getPesel());
//        System.out.println(clinicDAO.getClient("1111111111"));

    }
}
