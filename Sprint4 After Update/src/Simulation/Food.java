
package Simulation;


public class Food
{
    private String name;

    Food(String name)
    {
        this.name = name;
    }

    Food()
    {
        this("");
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    @Override
    public String toString()
    {
        return name;
    }
}