import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

public class Statue extends Attraction implements Visitable {
    private LocalDate date;
    private TimeInterval<LocalTime> openingTime;
    private TimeInterval<LocalTime> closingTime;

    private Map<LocalDate, TimeInterval<LocalTime>> timetable = new HashMap<>();

    public Statue(String name, String description) {
        super(name, description);
    }

    @Override
    public void setOpeningHours(LocalDate date, LocalTime openingTime, LocalTime closingTime) {
        timetable.put(date, new TimeInterval<>(openingTime, closingTime));
    }

    @Override
    public TimeInterval<LocalTime> getOpeningHours(LocalDate date) {
        return timetable.get(date);
    }

    @Override
    public String toString(LocalDate date) {
        TimeInterval<LocalTime> openingHours = getOpeningHours(date);
        if (openingHours != null) {
            return super.toString() + " (Open on " + date + " from " + openingHours.getStart() + " to " + openingHours.getEnd() + ")";
        } else {
            return super.toString() + " (Opening hours not available for " + date + ")";
        }
    }
}
