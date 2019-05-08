package simulation;

import java.util.ArrayList;
import java.util.List;


public class Trucks implements Observable{
    
    private List<Observer> observers; // list of all trucks 

    public Trucks(List<Observer> observers) {
        this.observers = observers;
    }

    public Trucks() {
        observers = new ArrayList<>();
    }
    
    
    

    @Override
    public void register(Observer obs) {
        if (obs == null) {
            throw new NullPointerException("Null simulation.Observer");
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
