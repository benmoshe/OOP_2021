package singelton;

public class Student {
    private Logger logger = Logger.getInstance();
    private String name;

    public Student(String name) {
        this.name = name;
        this.logger.log(this.name, "A student instaciated!");
    }

    public void graduate() {
        this.logger.log(this.name, "A student graduated!");
    }
}
