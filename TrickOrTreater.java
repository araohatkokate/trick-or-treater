/*
 * Araohat Kokate 1001829841
 */
package javaapplication9;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class TrickOrTreater implements Runnable
{

    public static int ImDone;
    private String name;
    private String path = ".";
    private ArrayList<House> ListOfHouses = new ArrayList<>();
    private HashMap<String, Integer> Bucket = new HashMap<>();

    TrickOrTreater(String name, ArrayList<House> ListOfHouses)
    {
        this.name = name;
        this.ListOfHouses = ListOfHouses;
    }

    public String getName()
    {
        return name;
    }

    public String getPath()
    {
        return path;
    }

    public void addToPath(String s)
    {
        path += s;
    }

    private void Walk(int speed)
    {
        for (int i = 0; i < 10; i++)
        {
            path = path + ".";

            try
            {
                Thread.sleep(speed);
            }
            catch (InterruptedException e)
            {
                Thread.currentThread().interrupt();
            }

        }
    }

    public String printBucket()
    {
        String Candy, BucketContents;
        int CandyCount = 0;
        BucketContents = String.format("%-10s - ", name);
        for (HashMap.Entry mapElement : Bucket.entrySet())
        {
            Candy = (String) mapElement.getKey();
            CandyCount = (int) mapElement.getValue();
            BucketContents += String.format("%15s %d ", Candy, CandyCount);
        }
        BucketContents += "\n";
        return BucketContents;
    }

    @Override
    public void run()
    {
        Random rn = new Random();
        int speed = 0, count = 0;
        String Candy;
        for (House it : ListOfHouses)
        {
            speed = rn.nextInt(1501) + 1;
            Walk(speed);
            Candy = it.ringDoorbell(this);
            if (Bucket.containsKey(Candy))
            {
                count = Bucket.get(Candy);
                Bucket.put(Candy, ++count);
            }
            else
            {
                Bucket.put(Candy, 1);
            }
        }

        synchronized (this)
        {
            ImDone++;
        }
    }
}