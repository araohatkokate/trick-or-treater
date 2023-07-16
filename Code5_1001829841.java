/*
 * Araohat Kokate 1001829841
 */
package javaapplication9;

import java.util.Scanner;
import java.util.Random;
import java.util.HashMap;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

public class Code5_1001829841
{

    public static void CreateCandyList(String filename, HashMap<String, Integer> CandyList) throws FileNotFoundException
    {
        File FH = new File(filename);
        Scanner infileread = null;
        String a = null;
        try
        {
            infileread = new Scanner(FH);
        }
        catch (Exception e)
        {
            System.out.println("Cannot open file..exiting");
            System.exit(0);
        }
        while (infileread.hasNextLine())
        {
            a = infileread.nextLine();
            String b[] = a.split("[|]");
            a = b[0];
            CandyList.put(a, Integer.parseInt(b[1]));
        }
        infileread.close();
    }

    public static String CreateHouses(String filename, ArrayList<House> Houses, HashMap<String, Integer> CandyList) throws FileNotFoundException
    {
        String HouseHeading = "          ";
        File FH = new File(filename);
        Scanner infileread = null;
        String a = " ";
        Random rn = new Random();
        try
        {
            infileread = new Scanner(FH);
        }
        catch (Exception e)
        {
            System.out.println("Cannot open file...exiting");
            System.exit(0);
        }
        while (infileread.hasNextLine())
        {
            a = infileread.nextLine();
            HouseHeading += a;
            for (int i = 0; i < 11 - a.length(); i++)
            {
                HouseHeading += " ";
            }
            if (rn.nextInt(2) == 0)
            {
                Houses.add(new CandyHouse(a, CandyList));
            }
            else
            {
                Houses.add(new ToothbrushHouse(a, CandyList));
            }
        }
        infileread.close();
        HouseHeading += "\n\n";
        return HouseHeading;
    }

    public static void CreateTOTs(String filename, ArrayList<TrickOrTreater> TOT, ArrayList<House> Houses) throws FileNotFoundException
    {
        File FH = new File(filename);
        Scanner infileread = null;
        String a = " ";
        try
        {
            infileread = new Scanner(FH);
        }
        catch (Exception e)
        {
            System.out.println("Cannot open file...exiting");
            System.exit(0);
        }
        while (infileread.hasNextLine())
        {
            a = infileread.nextLine();
            TOT.add(new TrickOrTreater(a, Houses));
        }
        infileread.close();
    }

    public static void main(String[] args) throws FileNotFoundException
    {
        Scanner in = new Scanner(System.in);
        if (args.length != 3)
        {
            System.out.println("Missing command line parameters");
            System.exit(0);
        }
        String file1 = args[0].substring(args[0].indexOf('=') + 1);
        String file2 = args[1].substring(args[1].indexOf('=') + 1);
        String file3 = args[2].substring(args[2].indexOf('=') + 1);
        HashMap<String, Integer> CandyList = new HashMap<>();
        ArrayList<House> Houses = new ArrayList<>();
        ArrayList<TrickOrTreater> TOT1 = new ArrayList<>();
        CreateCandyList(file1, CandyList);
        String HouseHeading = CreateHouses(file2, Houses, CandyList);
        CreateTOTs(file3, TOT1, Houses);
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (TrickOrTreater it : TOT1)
        {
            executorService.execute(it);
        }
        TextAreaFrame TAF = new TextAreaFrame();
        TAF.setVisible(true);
        while (TrickOrTreater.ImDone != TOT1.size())
        {
            String ScreenOutput = String.format("%s", HouseHeading);
            for (TrickOrTreater it : TOT1)
            {
                ScreenOutput += String.format("%s%s\n", it.getPath(), it.getName());
            }
            TAF.textArea.setText(ScreenOutput);
        }
        executorService.shutdown();
        String BucketContents = "Candy!!" + "\n\n";
        for (TrickOrTreater it : TOT1)
        {
            BucketContents += it.printBucket();
        }
        TAF.textArea.setText(BucketContents);
    }

}
