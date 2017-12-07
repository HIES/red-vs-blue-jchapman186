import java.io.File;
import java.util.Scanner;
import java.lang.Exception;
public class EmptyMap
{
    public EmptyMap()
    {
    }

    public void visualize(String region) throws Exception
    {

        File f = new File("./input/"+region+".txt");
        int pointCount = 0;
        int subCount = 0;

        StdDraw.enableDoubleBuffering();  

        Scanner inputObject = new Scanner(f);
        double[] min = new double[2];
        double[] max = new double[2];
        String tempMin = inputObject.nextLine();
        String tempMax = inputObject.nextLine();
        String[] minArr = tempMin.split("   ");
        String[] maxArr = tempMax.split("   ");
        for(int i = 0;i<min.length;i++){
            min[i] = Double.parseDouble(minArr[i]);
            max[i] = Double.parseDouble(maxArr[i]);
        }
        
        
        //0=long 1=lat
        System.out.println(max[1]);
        System.out.println(min[1]);
        System.out.println(max[0]);
        System.out.println(min[0]);
        System.out.println(max[1]-min[1]);
 
        //end testing
        StdDraw.setCanvasSize((int) (512*(max[1]-min[1])/(max[0]-min[0])) , 512);
        
        StdDraw.setPenRadius(0.05);
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setXscale(min[1],max[1]);
        StdDraw.setYscale(min[0],max[0]);

        int sub = Integer.parseInt(inputObject.nextLine());
        while(subCount < sub)
        {
            subCount++;
            inputObject.nextLine();
            inputObject.nextLine();
            inputObject.nextLine();
            int pointNo = Integer.parseInt(inputObject.nextLine());
            pointCount = 0;
            while(pointCount < pointNo)
            {
                pointCount++;
                String line = inputObject.nextLine();
                String[] data = line.split("   ");
                double x = Double.parseDouble(data[0]);
                double y = Double.parseDouble(data[1]);
                StdDraw.point(x,y);
            }
        }
        StdDraw.show();
    }
}

