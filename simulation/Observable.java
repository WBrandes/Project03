package simulation;

interface Observable {
    
    public void notifyObservers(Message m);

    public void register(Observer obs);

    public void unRegister(Observer obs);
    
}
