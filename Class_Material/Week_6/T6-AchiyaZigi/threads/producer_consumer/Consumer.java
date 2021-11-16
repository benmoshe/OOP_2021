package threads.producer_consumer;

public class Consumer implements Runnable {
    ControlRoom control;

    public Consumer(ControlRoom cr) {
        this.control = cr;
    }

    @Override
    public void run() {
        while(true){
            try {
                Thread.sleep((long)(Math.random() * 1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            };
            ControlRoom.Item item = this.control.get();
            // doing somthing with this item...
        }        
    }

}
