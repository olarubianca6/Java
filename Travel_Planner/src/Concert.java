import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

public class Concert extends Attraction implements Visitable, Payable {
    private LocalDate date;
    private TimeInterval<LocalTime> openingTime;
    private TimeInterval<LocalTime> closingTime;
    private double entryFee;

    private Map<LocalDate, TimeInterval<LocalTime>> timetable = new HashMap<>();

    public Concert(String name, String description) {
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
    public void setEntryFee(double fee) {
        this.entryFee = fee;
    }

    @Override
    public double getEntryFee(){
        return entryFee;
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
