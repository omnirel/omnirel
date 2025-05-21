import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        // 1. feladat

        Path inputPath = Paths.get("bejelentkezesek.txt");

        List<Login> logins = new ArrayList<>();

        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        try{
            List<String> line = Files.readAllLines(inputPath);

            for (String s : line){
                if (s.startsWith("id"))
                    continue;

                String[] parts = s.split(";");

                String id = parts[0];
                int terminal = Integer.parseInt(parts[1]);
                LocalTime loginTime = LocalTime.parse(parts[2], timeFormatter);
                LocalTime logoutTime = LocalTime.parse(parts[3], timeFormatter);

                logins.add(new Login(id, terminal, loginTime, logoutTime));
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        // 2. feladat

        HashSet<String> uniqueUsers = new HashSet<>();

        for (Login login : logins) {
            uniqueUsers.add(login.id());
        }

        System.out.printf("Összes bejelentkező: %s\n", uniqueUsers.size());

        // 3. feladat

        Login earliestLogin = logins.get(0);
        Login latestLogin = logins.get(0);

        for (Login login : logins) {
            if (login.loginTime().isBefore(earliestLogin.loginTime())){
                earliestLogin = login;
            }
            if (login.loginTime().isAfter(latestLogin.loginTime())){
                latestLogin = login;
            }
        }
        System.out.printf("A legkorábbi bejelentkező: %s\nA legkésőbbi bejelentkező: %s\n", earliestLogin.id(), latestLogin.id());

        // 4. feladat

        Login shortestLogin = logins.get(0);
        Duration minDuration = Duration.between(shortestLogin.loginTime(), shortestLogin.logoutTime());

        for (Login login : logins) {
            Duration currentDuration = Duration.between(login.loginTime(), login.logoutTime());
            if (currentDuration.compareTo(minDuration) < 0){
                minDuration = currentDuration;
                shortestLogin = login;
            }
        }

        System.out.printf("A legrövidebb egyszeri bejelentkezés: %s\n", shortestLogin);

        // 5. feladat

        Map<String, Duration> countSessionsDuration = new HashMap<>();

        for (Login login : logins) {
            String id = login.id();
            Duration duration = Duration.between(login.loginTime(), login.logoutTime());

            countSessionsDuration.put(id, countSessionsDuration.getOrDefault(id, Duration.ZERO).plus(duration));
        }

        String userWithMaxTotalSessionDuration = null;
        Duration minSessionDuration = Duration.ZERO;

        for (Map.Entry<String, Duration> entry : countSessionsDuration.entrySet()) {
            if (minSessionDuration.compareTo(entry.getValue()) < 0){
                minSessionDuration = entry.getValue();
                userWithMaxTotalSessionDuration = entry.getKey();
            }
        }

        System.out.printf("A legtöbb időt bejelentkezve összesen %s töltötte.\n", userWithMaxTotalSessionDuration);

        // 6. feladat

        Scanner sc = new Scanner(System.in);
        System.out.println("Időpont:");
        String inputTime = sc.nextLine();

        LocalTime inputTimeFormatted = LocalTime.parse(inputTime, timeFormatter);

        List<String> loggedUsers = new ArrayList<>();

        for (Login login : logins) {
            if (!inputTimeFormatted.isBefore(login.loginTime()) && !inputTimeFormatted.isAfter(login.logoutTime())){
                loggedUsers.add(login.id());
            }
        }
        System.out.printf("Bejelentkezettek: %s\n", loggedUsers);

        // 7. feladat

        List<String> loggedUsersInAfternoon = new ArrayList<>();

        for (Login login : logins) {
            if (!login.loginTime().isBefore(LocalTime.NOON)){
                loggedUsersInAfternoon.add(login.id() + " " + login.loginTime());
            }
        }

        try{
            Files.write(Paths.get("delutan.txt"), loggedUsersInAfternoon);
            System.out.println("Kész.");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Gebasz.");
        }

        // 8. feladat

        HashMap<String, Integer> countUsersLogins = new HashMap<>();

        for (Login login : logins) {
            String id = login.id();
            countUsersLogins.put(id, countUsersLogins.getOrDefault(id, 0) + 1);
        }

        List<String> countedUsersLogins = new ArrayList<>();

        for (Map.Entry<String, Integer> entry : countUsersLogins.entrySet()) {
            countedUsersLogins.add(entry.getKey() + " " + entry.getValue());
        }

        try{
            Files.write(Paths.get("logins.txt"), countedUsersLogins);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Gebasz.");
        }
    }
}
