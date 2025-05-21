import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // 1. feladat

        Path inputPath = Paths.get("vasarlasok.txt");

        List<Purchase> purchases = new ArrayList<>();

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

        try{
            List<String> line = Files.readAllLines(inputPath);

            for (String s : line){
                if (s.startsWith("datum"))
                    continue;

                String[] parts = s.split(";");

                LocalDate date = LocalDate.parse(parts[0], dateFormatter);
                LocalTime time = LocalTime.parse(parts[1], timeFormatter);
                int price = Integer.parseInt(parts[2]);
                String store = parts[3];

                purchases.add(new Purchase(date, time, price, store));
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        // 2. feladat

        Scanner sc = new Scanner(System.in);
        System.out.println("Kérlek írj be egy dátumot (yyyy.MM.dd):");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");

        boolean found = false;

        while (!found){
            try{
                String inputDate = sc.nextLine();
                LocalDate inputDateFormat = LocalDate.parse(inputDate, formatter);

                found = false;

                for (Purchase purchase : purchases){
                    if (purchase.date().equals(inputDateFormat)){
                        found = true;
                    }
                }

                if (found){
                    System.out.printf("Történt ezen a napon vásárlás: %s\n", inputDateFormat);
                }else{
                    System.out.printf("Nem történt ezen a napon vásárlás, adj meg újat!\n");
                }
            }catch (Exception e){
                System.out.println("Hibás dátumformátum, adj meg egy újat (yyyy.MM.dd):");
            }
        }

        // 3. feladat

        Purchase earliestPurchase = purchases.get(0);
        Purchase latestPurchase = purchases.get(0);

        for (Purchase purchase : purchases){
            if (purchase.date().isBefore(earliestPurchase.date()) || (purchase.date().equals(earliestPurchase.date()) && purchase.time().isBefore(earliestPurchase.time()))){
                earliestPurchase = purchase;
            }
            if (purchase.date().isAfter(latestPurchase.date()) || (purchase.date().equals(latestPurchase.date()) && purchase.time().isAfter(latestPurchase.time()))){
                latestPurchase = purchase;
            }
        }

        System.out.printf("A legkorábbi vásárlás: %s\nA legkésőbbi vásárlás: %s\n", earliestPurchase.date(), latestPurchase.date());

        // 4. feladat

        List<String> purchasesOnSaturday = new ArrayList<>();

        for (Purchase purchase : purchases){
            if (purchase.date().getDayOfWeek() == DayOfWeek.SATURDAY){
                purchasesOnSaturday.add(purchase.date().toString() + " " + purchase.time().toString() + " " + purchase.price() + " " + purchase.store());
            }
        }

        try{
            Files.write(Paths.get("szombatok.txt"), purchasesOnSaturday);
            System.out.println("Sikeres a fájlba való kiírás.");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Hiba történt a fájlba való kiírás során.");
        }

        // 5. feladat

        HashSet<LocalDate> uniqueDates = new HashSet<>();

        for (Purchase purchase : purchases){
            uniqueDates.add(purchase.date());
        }

        System.out.printf("Összesen %s napon történt vásárlás.\n", purchasesOnSaturday.size());

        // 6. feladat

        double sum = 0;

        for (Purchase purchase : purchases){
            sum += purchase.price();
        }

        double avg = sum / purchases.size();

        System.out.printf("Átlagosan %.0f forintot költöttünk egy vásárlás alkalmával.\n", avg);

        // 7. feladat

        HashSet<String> uniqueStores = new HashSet<>();
        for (Purchase purchase : purchases){
            uniqueStores.add(purchase.store());
        }
        System.out.printf("Összesen %s különböző áruházban vásároltunk.\n", uniqueStores.size());

    }
}
