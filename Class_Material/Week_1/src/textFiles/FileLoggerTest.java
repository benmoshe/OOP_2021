package textFiles;
import random.MyRandom;
/**
 * This class represents a simple demo for using text files as logger.
 */
public class FileLoggerTest {
    public static void main(String[] a) {
        test1();

    }
    public static void test1() {
        MyRandom r = new MyRandom(123);
        FileLogger fl = new FileLogger("103.txt"); // date based (now) file name, should be Data/..
        System.out.println("***** class1.textFiles.FileLogger - Tester: *****");
        fl.log("this is a first line 123");
        fl.log("second line");
        for(int i=0;i<100;i++) {
            String a = ""+i+","+r.nextGaussian();
            fl.log(a);
        }
        fl.close();
    }
}
