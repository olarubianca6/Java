import java.util.List;

public class Driver extends Person{
    private List<String> route;

    public Driver(String name, int age, String destination, List<String> route) {
        super(name, age, destination);
        this.route = route;
    }

    public List<String> getRoute(){
        return route;
    }

    @Override
    public String toString() {
        return "Driver -> " + super.toString() + ", Route: " + route;
    }
}
