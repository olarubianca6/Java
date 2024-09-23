import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class TravelPlan {
    private Map<LocalDate, List<Attraction>> plan = new HashMap<>();

    public void addAttraction(LocalDate date, Attraction attraction){
        plan.computeIfAbsent(date, k -> new ArrayList<>()).add(attraction);
    }

    public void printPlan(){
        for(Map.Entry<LocalDate, List<Attraction>> entry : plan.entrySet()) {
            LocalDate date = entry.getKey();
            List<Attraction> attractions = entry.getValue();

            System.out.println("Date: " + date);
            for (Attraction attraction : attractions) {
                System.out.println("  - " + attraction.getName());

                if (attraction instanceof Visitable) {
                    TimeInterval<LocalTime> openingHours = ((Visitable) attraction).getOpeningHours(date);
                    if (openingHours != null) {
                        System.out.println("    Opening hours: " + openingHours);
                    } else {
                        System.out.println("    No visiting hours available for this date.");
                    }
                }
            }
        }
    }
}
