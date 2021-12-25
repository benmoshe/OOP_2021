package singelton;

public class Main {

    public static void rememberMe(String name) {
        Logger logger = Logger.getInstance();
        logger.log(name, "asked to be remembered", "(from function: rememberMe)");
    }

    public static void main(String[] args) {
        Logger logger = Logger.getInstance();
        logger.log("Achiya", "main started", "(from function: main)");

        Student s1 = new Student("Adi Cohen");
        rememberMe("Achiya zigler");
        Student s2 = new Student("Amir Hazan");
        rememberMe("John Doe");
        s1.graduate();
        logger.printLogs("Adi Cohen");
    }
}
