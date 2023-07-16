/*
 * Araohat Kokate 1001829841
 */
package javaapplication9;

import java.util.HashMap;

public abstract class House
{

    private String houseName;
    HashMap<String, Integer> CandyList = new HashMap<>();

    House(String houseName, HashMap<String, Integer> CandyList)
    {
        this.houseName = houseName;
        this.CandyList = CandyList;
    }

    public abstract String ringDoorbell(TrickOrTreater TOT);
}
