import java.time.LocalTime;

public record Login(String id, int terminal, LocalTime loginTime, LocalTime logoutTime) {
}
