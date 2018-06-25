package main;

public class Player {

    private Player() {}

    private static Player ourInstance = new Player();

    public static Player getInstance() {
        return ourInstance;
    }


    //Vomit is fast way to lose health and fatness. & toxic
    private int vomit;
    //Diarreah makes you fly upwards form mexican food ability NOT jump.
    private int shits;

    //Sick - this can be too much in general, or just too much in a given duration
    //correlates to vomiting
    private int sickness;

    //Armour - Gain from food loss from movement. Not lost from healthy food bcz thats not how life works
    //if too fat for too long random chance of heart attack
    //also may come with good abilities, stronger attacks
    //also may correlate to speed
    //fatness can be negatative for anerexia 0 is the goal
    private int fatness;
    //Health

    //makes you more impervious to damage the more you drink
    //after a certain point have a random chance of blacking out
    //the drunk scale will depend on fatness
    private int drunk;

    //this is just a skin, will make everything look crazy but not do anything else
    //every power up increases their potency
    private boolean high;





}
