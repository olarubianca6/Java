public class TimeInterval<T> {
    private T start;
    private T end;

    public TimeInterval(T start, T end){
        this.start = start;
        this.end = end;
    }

    public T getStart(){
        return start;
    }

    public T getEnd(){
        return end;
    }

    @Override
    public String toString() {
        return start + " - " + end;
    }
}
