
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class Orders implements Observable{
    
    private List<Observer> observers; // list of all orders 

    public Orders(List<Observer> observers) {
        this.observers = observers;
    }

    public Orders() {
        observers = new ArrayList<>();
    }
    
    
    

    @Override
    public void register(Observer obs) {
        if (obs == null) {
            throw new NullPointerException("Null Observer");
        }
            if (!observers.contains(obs)) {
                observers.add(obs);
            }
        
    }

    @Override
    public void unRegister(Observer obs) {
            observers.remove(obs);
       
    }
    
    @Override
    public void notifyObservers(Message m) {
        for(Observer o: observers) {
            o.update(m);
        }
    }
    
}
