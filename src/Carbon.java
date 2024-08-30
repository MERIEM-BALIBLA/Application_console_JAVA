import java.time.LocalDate;

public class Carbon {
    private static int idCounter = 1;
    private int id;
    //private int userId;
    private double quantity;
    private LocalDate start;
    private LocalDate end;

    public Carbon(double quantity, LocalDate start, LocalDate end) {
        this.id = idCounter++;
        this.quantity = quantity;
        this.start = start;
        this.end = end;
    }

    public Carbon() {
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
