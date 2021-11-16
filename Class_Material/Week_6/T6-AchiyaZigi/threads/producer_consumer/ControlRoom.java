package threads.producer_consumer;

import java.util.LinkedList;
import java.util.List;

public class ControlRoom {
    public class Item{
    }
    private List<Item> items;

    public ControlRoom(){
        this.items = new LinkedList<>();
    }
    public synchronized Item get(){
        while(items.isEmpty()){
            try {
                System.out.println("Not enough items. wating for producer");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // items isn't empty!
        System.out.println("consumed! left:"+(this.items.size()-1));
        notify();
        return this.items.remove(0);
    }

    public synchronized void put(Item item){
        while(this.items.size() >2){
                System.out.println("Too many items. wating for consumer");
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            this.items.add(item);
            System.out.println("produced! left:"+this.items.size());
            notify();
        
    }
}
