import java.io.File;
import java.util.Scanner;
import java.lang.Exception;
public class EmptyMap
{
    public EmptyMap()
    {
    }

    public static void visualize(String region) throws Exception
    {
        File f = new File("./input/"+region+".txt");

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
        System.out.println("Long: " + min[0] + " " + max[0]);
        System.out.println("Lat: " + min[1] + " " + max[1]);
        System.out.println("Scale: " + (int) (512*(max[0]-min[0])/(max[1]-min[1])));
        StdDraw.setCanvasSize((int) (512*(max[0]-min[0])/(max[1]-min[1])), 512);

        //StdDraw.setPenRadius(0.05);
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setXscale(min[0],max[0]);
        StdDraw.setYscale(min[1],max[1]);
        int subs = Integer.parseInt(inputObject.nextLine());
        System.out.println("#subs: " + subs);
        while(subCount < subs)
        {
            System.out.println("blank:" + inputObject.nextLine());
            System.out.println(inputObject.nextLine());
            System.out.println(inputObject.nextLine());
            int subLength = Integer.parseInt(inputObject.nextLine());
            System.out.println("#points: " + subLength);
            double[] xCoords = new double[subLength];
            double[] yCoords = new double[subLength];
            for(int i = 0; i < subLength; i++)
            {
                String[] data = inputObject.nextLine().split("   ");
                xCoords[i] = Double.parseDouble(data[0]);
                yCoords[i] = Double.parseDouble(data[1]);
            }
            subCount++;
            System.out.println(subCount);
            StdDraw.polygon(xCoords, yCoords);
        }
        StdDraw.show();
        inputObject.close();
    }
}