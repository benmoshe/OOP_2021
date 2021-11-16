package threads.moving_cars;

public class Main {
    public static final int screenSize = 60;
    static final String rightCarString = "[0=0>";
    static final String leftCarString = "(0^0]";

    public static void main(String args[]) {
        System.out.println("hello world");
        CarRunnable carToRight = new CarRunnable(2);
        CarRunnable carToLeft = new CarRunnable(1);
        Thread tLeft = new Thread(carToRight);
        Thread tRight = new Thread(carToLeft);
        tLeft.start();
        tRight.start();

        for (int i = 0; i < screenSize; i++) {

            int dist = screenSize - carToRight.getX() - carToLeft.getX();
            String distBetwin = " ".repeat(Math.abs(dist));

            if (dist >= 0) {
                String distRCar = " ".repeat(carToRight.getX());
                System.out.println(distRCar + rightCarString + distBetwin + leftCarString);

            }
            else {
                String distLCar = " ".repeat(screenSize - carToLeft.getX());
                System.out.println(distLCar + leftCarString + distBetwin + rightCarString);
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.print("\033[H\033[2J");
        }
        try {
            tLeft.join();
            tRight.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(carToRight.getX());
    }
}
