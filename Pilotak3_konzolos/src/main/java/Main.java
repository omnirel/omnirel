import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        // 1. feladat

        Path inputPath = Paths.get("pilotak.csv");

        List<Pilota> pilotakList = new ArrayList<>();

        DateTimeFormatter dateformat = DateTimeFormatter.ofPattern("yyyy.MM.dd");

        try{
            List<String> line = Files.readAllLines(inputPath, StandardCharsets.ISO_8859_1);

            for (String s : line) {
                if (s.startsWith("név"))
                    continue;

                String[] split = s.split(";");

                String name = split[0].trim();
                LocalDate birthDate = LocalDate.parse(split[1].trim(), dateformat);
                String nationality = split[2].trim();

                pilotakList.add(new Pilota(name, birthDate, nationality));
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        // 2. feladat

        Pilota youngestPilot = pilotakList.get(0);
        Pilota oldestPilot = pilotakList.get(0);

        for (Pilota p : pilotakList) {
            if (p.birthDate().isAfter(youngestPilot.birthDate()) || p.birthDate().equals(youngestPilot.birthDate())) {
                youngestPilot = p;
            }
            if (p.birthDate().isBefore(oldestPilot.birthDate()) || p.birthDate().equals(oldestPilot.birthDate())) {
                oldestPilot = p;
            }
        }

        System.out.printf("A legfiatalabb pilóta: %s \nA legidősebb pilóta: %s\n", youngestPilot.name(), oldestPilot.name());

        // 3. feladat

        System.out.printf("Legfiatalabb ezen a napon született: %s \nLegidősebb ezen a napon született: %s\n ", youngestPilot.birthDate().getDayOfWeek(), oldestPilot.birthDate().getDayOfWeek());

        // 4. feladat

        Scanner sc = new Scanner(System.in);
        System.out.println("Kérlek, írj be egy dátumot (yyyy-MM-dd):");
        String inputDate = sc.nextLine();

        LocalDate inputDateFormatted = LocalDate.parse(inputDate, dateformat);
        boolean havePilot = false;

        for (Pilota p : pilotakList) {
            if (p.birthDate().equals(inputDateFormatted)) {
                havePilot = true;
            }
        }

        if (havePilot) {
            System.out.println("Van olyan pilóta, aki ezen a napon született.");
        }else{
            System.out.println("Nincs olyan pilóta, aki ezen a napon született.");
        }

        // 5. feladat

        Pilota nearestBornPilot = pilotakList.get(0);
        long minDuration = Math.abs(ChronoUnit.DAYS.between(nearestBornPilot.birthDate(), inputDateFormatted));

        for (Pilota p : pilotakList) {
            long currentDuration = Math.abs(ChronoUnit.DAYS.between(p.birthDate(), inputDateFormatted));
            if (currentDuration < minDuration) {
                minDuration = currentDuration;
                nearestBornPilot = p;
            }
        }
        System.out.printf("A legközelebbi születésű ehhez a naphoz: %s\n", nearestBornPilot.name());

        // 6. feladat

        Map<String, Integer> countNationality = new HashMap<>();

        for (Pilota p : pilotakList) {
            String nationality = p.nationality();

            countNationality.put(nationality, countNationality.getOrDefault(nationality, 0) + 1);
        }

        String maxTotalNationality = null;
        int maxCount = Integer.MIN_VALUE;

        for (Map.Entry<String, Integer> entry : countNationality.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                maxTotalNationality = entry.getKey();
            }
        }
        System.out.printf("A legtöbb nemzetiség: %s - %d\n", maxTotalNationality, maxCount);




    }
}
