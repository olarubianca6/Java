import java.time.LocalDate;
import java.time.LocalTime;

public class Main {
    public static void main(String[] args) {
        Trip trip = new Trip("Paris", "June 10 - June 20");

        Statue eiffelTower = new Statue("Eiffel Tower", "iconic landmark");
        eiffelTower.setOpeningHours(LocalDate.of(2024, 06, 20), LocalTime.of(9,30), LocalTime.of(23, 30));

        Church notreDame = new Church("Notre Dame Cathedral", "famous gothic cathedral");
        notreDame.setOpeningHours(LocalDate.of(2024, 06, 20), LocalTime.of(8,0), LocalTime.of(18, 30));
        notreDame.getOpeningHours(LocalDate.of(2024, 06, 20));

        Concert theWeeknd = new Concert("The Weeknd", "pop artist");
        theWeeknd.setEntryFee(100.0);
        theWeeknd.setOpeningHours(LocalDate.of(2024, 06, 20), LocalTime.of(20,0), null);

        trip.addAttraction(eiffelTower);
        trip.addAttraction(notreDame);
        trip.addAttraction(theWeeknd);

        System.out.println(trip);
        trip.printAttractions(LocalDate.of(2024,06,20));
        trip.getFreeAttractions(LocalDate.of(2024,06,20));

        TravelPlan travelPlan = new TravelPlan();

        travelPlan.addAttraction(LocalDate.of(2024,06,20), eiffelTower);
        travelPlan.addAttraction(LocalDate.of(2024,06,20), notreDame);
        travelPlan.addAttraction(LocalDate.of(2024,06,20), theWeeknd);

        System.out.println("\n");
        travelPlan.printPlan();
    }
}




