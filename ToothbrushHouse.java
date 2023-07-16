/*
 * Araohat Kokate 1001829841
 */
package javaapplication9;

import java.util.HashMap;

public class ToothbrushHouse extends House
{

    public ToothbrushHouse(String HouseName, HashMap<String, Integer> candylist)
    {
        super(HouseName, candylist);
    }

    @Override
    public synchronized String ringDoorbell(TrickOrTreater TOT)
    {
        TOT.addToPath("-");
        try
        {
            Thread.sleep(3000);
        }
        catch (InterruptedException e)
        {
            Thread.currentThread().interrupt();
        }
        return "Toothbrush";
    }
}