package threads.basic;

import java.util.concurrent.atomic.AtomicInteger;

public class IncSmart implements IncInterface{
    public AtomicInteger x;
    public IncSmart(){
        this.x = new AtomicInteger(0);
    }
    public void inc(){
        this.x.incrementAndGet();
    }
}
