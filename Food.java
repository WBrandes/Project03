/*
 * The Food class handles the functionality of food in this simulation.
 * It has one attribute and one method: a name and a method to get that name.
 */





import java.util.Random;

public class Food
{
    String name;

    Food()
    {
        name = "";
    }

    Food(String typeOfFood)
    {
        name = typeOfFood + ((new Random()).nextInt(3) + 1);
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