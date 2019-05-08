
package Simulation;

public class Singleton {

    private static Singleton SINGLE_INSTANCE = null;

    private Singleton() {}

    public static Singleton getInstance() {

        if (SINGLE_INSTANCE == null) {  

          synchronized(Singleton.class) {

          SINGLE_INSTANCE = new Singleton();

          }

        }

        return SINGLE_INSTANCE;

    }

}