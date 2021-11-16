package threads.producer_consumer;

public class Main {
    public static void main(String[] args) {
        ControlRoom control = new ControlRoom();
        Producer producer = new Producer(control);
        Consumer consumer = new Consumer(control);
        Thread tProd = new Thread(producer);
        Thread tCons = new Thread(consumer);

        tProd.setName("producer");
        tCons.setName("consumer");

        tProd.start();
        tCons.start();
    }
    
}
