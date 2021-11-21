import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class Test7 {
    //@NotNull
    static String str = "123";
    public static void main(String[] args) {

        reflection1();
        lambda1();
        lambda2();
    }

    public static void reflection1() {
        Class<Point2D> clp = Point2D.class;
        for(Method m: clp.getDeclaredMethods()) {
            System.out.println(m.getName());
            for(Parameter p: m.getParameters()) {
                System.out.println(" "+p.getName()+" "+p.getType());
            }
        }
    }
    public static void lambda1() {
            ArrayList<Integer> numbers = new ArrayList<Integer>();
            numbers.add(5);
            numbers.add(9);
            numbers.add(8);
            numbers.add(1);
            Consumer<Integer> method = (n) -> { System.out.println(n); };
            numbers.forEach( method );
    }
    public static void lambda2() {
        List<String> list = new ArrayList<String>();
        list.add("ankit");
        list.add("mayank");
        list.add("irfan");
        list.add("jai");

        list.forEach(
                (n) -> System.out.println(n)
        );
    }
}
