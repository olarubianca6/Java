import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

interface Visitable {

    void setOpeningHours(LocalDate date, LocalTime openingtime, LocalTime closingTime);
    TimeInterval<LocalTime> getOpeningHours(LocalDate date);
}

interface Payable {
    void setEntryFee(double fee);
    double getEntryFee();
}

abstract public class Attraction implements Comparable<Attraction> {
    protected String name;
    protected String description;

    public Attraction(String name, String description) {
        this.name = name;
        this.description = description;
    }

    @Override
    public int compareTo(Attraction other) {
        return this.name.compareTo(other.name);
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return name + ": " + description;
    }

    public abstract String toString(LocalDate date);
}

class Trip {
    private String city;
    private String period;
    private List<Attraction> attractions;

    public Trip(String city, String period) {
        this.city = city;
        this.period = period;
        this.attractions = new ArrayList<>();
    }

    public void addAttraction(Attraction attraction){
        attractions.add(attraction);
    }

    public void printAttractions(LocalDate date){
        attractions.sort(null);
        for (Attraction attraction : attractions) {
            System.out.println(attraction.toString(date));
        }
    }
public void getFreeAttractions(LocalDate date) {
        List<Visitable> free = new ArrayList<>();
        for (Attraction attraction : attractions) {
            if (attraction instanceof Visitable && !(attraction instanceof Payable)) {
                Visitable visitable = (Visitable) attraction;

                TimeInterval<LocalTime> openingHours = visitable.getOpeningHours(date);
                if (openingHours != null) {
                    free.add(visitable);
                }
            }
        }

        free.sort(Comparator.comparing(v -> v.getOpeningHours(date).getStart()));

        System.out.println("Free attractions sorted by opening hour:");
        for (Visitable visitable : free) {
            Attraction attraction = (Attraction) visitable;
            TimeInterval<LocalTime> openingTime = visitable.getOpeningHours(date);
            System.out.println(attraction.getName() + " (Open at: " + openingTime.getStart() + ")");
        }
    }

    @Override
    public String toString() {
        return "Trip to " + city + " during " + period;
    }

}
