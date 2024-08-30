import java.time.LocalDate;
import java.util.Scanner;
import java.util.List;

public class CarbonManage {
    private List<Carbon> cosList;
    Scanner scanner = new Scanner(System.in);
    public void readCarbon(){
        for(Carbon carbon : cosList){
            System.out.println("List : " + carbon.getQuantity() + carbon.getStart() + carbon.getEnd());
        }
    }

    public void Addconsumption (){
        System.out.println("Please enter the quantity used : ");
        double quantity = scanner.nextDouble();

        System.out.println("Please enter date: ");

        System.out.print("Start date here : ");
        String StartDate = scanner.nextLine();
        LocalDate startDate = LocalDate.parse(StartDate);

        System.out.print("End date here : ");
        String EndDate = scanner.nextLine();
        LocalDate endDate = LocalDate.parse(EndDate);

        Carbon carbon = new Carbon(quantity, startDate, endDate);
    }
}
