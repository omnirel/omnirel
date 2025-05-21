import java.time.LocalDate;
import java.time.LocalTime;

public record Purchase(LocalDate date, LocalTime time, int price, String store) {
}
