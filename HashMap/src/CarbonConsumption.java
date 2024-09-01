import java.time.LocalDate;

public class CarbonConsumption {
    private static int idCounter = 1;
    private int id;
    //private int userId;
    private double quantity;
    private LocalDate start;
    private LocalDate end;

    public CarbonConsumption(double quantity, LocalDate start, LocalDate end) {
        this.id = idCounter++;
        this.quantity = quantity;
        this.start = start;
        this.end = end;
    }

    public CarbonConsumption() {
    }

    public int getId() {
        return id;
    }

    public double getQuantity() {
        return quantity;
    }


    public LocalDate getStart() {
        return start;
    }


    public LocalDate getEnd() {
        return end;
    }

}
