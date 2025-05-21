package org.example.naptar5_grafikus;

import java.time.LocalDate;
import java.time.LocalTime;

public record Naptar(String title, LocalDate date, LocalTime time, String description) {
}
