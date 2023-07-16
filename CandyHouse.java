/*
 * Araohat Kokate 1001829841
 */
package javaapplication9;

import java.util.Random;
import java.util.HashMap;

public class CandyHouse extends House
{

    CandyHouse(String HouseName, HashMap<String, Integer> candylist)
    {
        super(HouseName, candylist);
    }

    @Override
    public synchronized String ringDoorbell(TrickOrTreater TOT)
    {
        String Candy = null;
        TOT.addToPath("+");
        try
        {
            Thread.sleep(3000);
        }
        catch (InterruptedException e)
        {
            Thread.currentThread().interrupt();
        }
        Random rn = new Random();
        int a = rn.nextInt(CandyList.size() - 1) + 1;
        for (HashMap.Entry mapElement : CandyList.entrySet())
        {
            if ((int) mapElement.getValue() == a)
            {
                Candy = (String) mapElement.getKey();
            }
        }
        return Candy;
    }
}