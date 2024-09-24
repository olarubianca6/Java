import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CarpoolingApp {

    private List<Person> persons;
    private static List<Driver> drivers;
    private static List<Passenger> passengers;

    public CarpoolingApp(int numPersons) {
        this.persons = createRandomPersons(numPersons);
        this.drivers = persons.stream()
                .filter(p -> p instanceof Driver)
                .map(p -> (Driver) p)
                .collect(Collectors.toList());
        this.passengers = persons.stream()
                .filter(p -> p instanceof Passenger)
                .map(p -> (Passenger) p)
                .collect(Collectors.toList());
    }

    public static void main() {

        CarpoolingApp app = new CarpoolingApp(10);


        LinkedList<Driver> sortedDrivers = drivers.stream()
                .sorted(Comparator.comparingInt(Driver::getAge))
                .collect(Collectors.toCollection(LinkedList::new));

        TreeSet<Passenger> sortedPassengers = passengers.stream()
                .collect(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(Passenger::getName))));

        System.out.println("Sorted drivers by age:");
        sortedDrivers.forEach(System.out::println);

        System.out.println("Sorted passengers by name:");
        sortedPassengers.forEach(System.out::println);

        System.out.println("Driver routes (All destinations):");
        List<String> driverRoutes = app.getRoutes();
        driverRoutes.forEach(System.out::println);

        System.out.println("\nDestination map:");
        Map<String, List<Person>> destinationMap = app.getDestinatioMap();
        destinationMap.forEach((destination, people) -> {
            System.out.println("Destination: " + destination);
            people.forEach(System.out::println);
        });

        System.out.println("\n Matching drivers and passengers:");
        app.matchPeople();
    }

    public static List<Person> createRandomPersons(int count) {
        String[] names = {"Alice", "Bob", "Charlie", "David", "Eve", "Frank", "Grace", "Heidi", "Ivan", "Judy"};
        String[] destinations = {"school", "office", "gym", "mall", "park", "airport"};
        Random random = new Random();

        return Stream.generate(() -> {
            String name = names[random.nextInt(names.length)];
            int age = random.nextInt(60) + 18;
            String destination = destinations[random.nextInt(destinations.length)];
        if (random.nextBoolean()) {
            List<String> route = new ArrayList<>();
            int routeLength = random.nextInt(3) + 1;
            for ( int j = 0; j < routeLength; j++) {
                route.add(destination);
            }
            return new Driver(name, age, destination, route);
        } else {
            return new Passenger(name, age, destination);
        }
        }).limit(count).toList();
    }

    public List<String> getRoutes() {
        return drivers.stream()
                .flatMap(driver -> driver.getRoute().stream())
                .distinct()
                .toList();
    }

    public Map<String, List<Person>> getDestinatioMap() {
        return persons.stream()
                .collect(Collectors.groupingBy(Person::getDestination));
    }

    public void matchPeople() {
        List<Passenger> unmatchedPassengers = new ArrayList<>(passengers);

        for (Driver driver : drivers) {
            String driverDestination = driver.getDestination();
            Optional<Passenger> matchedPassenger = unmatchedPassengers.stream()
                    .filter(p -> p.getDestination().equals(driverDestination))
                    .findFirst();

            if (matchedPassenger.isPresent()) {
                Passenger p = matchedPassenger.get();
                System.out.println("Driver " + driver.getName() + " matches with passenger "+ p.getName()
                        + " (Destination: " + driverDestination + ")");
                unmatchedPassengers.remove(p);

            }
        }

        if (!unmatchedPassengers.isEmpty()) {
            System.out.println("Unmatched Passengers: ");
            unmatchedPassengers.forEach(p -> System.out.println(p.getName() + " - " + p.getDestination()));
        }
    }
}
