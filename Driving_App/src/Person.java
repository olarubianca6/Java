public class Person {
    private String name;
    private int age;
    private String destination;

    public Person(String name, int age, String destination) {
        this.name = name;
        this.age = age;
        this.destination = destination;
    }

    public String getName() {
        return name;
    }

    public int getAge(){
        return age;
    }

    public String getDestination(){
        return destination;
    }

    @Override
    public String toString(){
        return name + " (" + age + " years) heading to " + destination;
    }
}
