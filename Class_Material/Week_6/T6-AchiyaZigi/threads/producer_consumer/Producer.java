package threads.producer_consumer;

public class Producer implements Runnable {
    ControlRoom control;

    public Producer(ControlRoom cr) {
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
            this.control.put(control.new Item());
        }        
    }

}
