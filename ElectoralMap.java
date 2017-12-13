import java.io.File;
import java.util.Scanner;
import java.lang.Exception;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
public class ElectoralMap
{
    int[] votes;
    double[][] coords;
    private class SubRegion
    {
        private String name;
        private int dVotes;
        private int rVotes;
        private int iVotes;
        private double[] xCoords;
        private double[] yCoords;
        private String color;
        
        private SubRegion(String userName, int[] userVotes, double[][] userCoords, String userColor)
        {
            name = userName;
            dVotes = userVotes[0];
            rVotes = userVotes[1];
            iVotes = userVotes[2];
            xCoords = userCoords[0];
            yCoords = userCoords[1];
            color = userColor;
        }
    }
    HashMap<String, ArrayList<String>> regions = new HashMap<>();
    
    public static void visualize(String region, int year) throws Exception
    {
        File f = new File("./input/"+region+year+".txt");

        double[] min = new double[2];
        double[] max = new double[2];
        int subCount = 0;

        StdDraw.enableDoubleBuffering();  

        Scanner inputObject = new Scanner(f);

        String tempMin = inputObject.nextLine();
        String tempMax = inputObject.nextLine();
        String[] minArr = tempMin.split("   ");
        String[] maxArr = tempMax.split("   ");
        for(int i = 0;i<min.length;i++){
            min[i] = Double.parseDouble(minArr[i]);
            max[i] = Double.parseDouble(maxArr[i]);
        }
        //0=long 1=lat
        StdDraw.setCanvasSize((int) (512*(max[0]-min[0])/(max[1]-min[1])), 512);

        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setXscale(min[0],max[0]);
        StdDraw.setYscale(min[1],max[1]);
        int subs = Integer.parseInt(inputObject.nextLine());        
        while(subCount < subs)
        {
            inputObject.nextLine();
            inputObject.nextLine();
            inputObject.nextLine();
            int subLength = Integer.parseInt(inputObject.nextLine());
            double[] xCoords = new double[subLength];
            double[] yCoords = new double[subLength];
            for(int i = 0; i < subLength; i++)
            {
                String[] data = inputObject.nextLine().split("   ");
                xCoords[i] = Double.parseDouble(data[0]);
                yCoords[i] = Double.parseDouble(data[1]);
            }
            subCount++;
        }
        StdDraw.show();
        inputObject.close();
        
        //reopen file
        //skip first line
        //parse thru CSV, assigning the votes to each different SubRegion class
        //use votes to calculate the color
        //draw a polygon with changed pencolor
    }
}