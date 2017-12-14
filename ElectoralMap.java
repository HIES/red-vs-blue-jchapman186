import java.io.File;
import java.util.Scanner;
import java.lang.Exception;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.awt.Color;

public class ElectoralMap {
    public static void main(String[] args) throws Exception {
        visualize("NV", 2012);
        System.out.println("Donezo!");
    }

    static class SubRegion {
        private String name;
        private int[] votes = new int[3]; //0 = rep, 1 = dem, 2 = other
        private double[] x;
        private double[] y;
        private Color color;

        private SubRegion(String userName, double[] userX, double[] userY) {
            name = userName;
            x = userX;
            y = userY;
        }
        private void addVotes(int[] dataVotes)
        {
            votes[0] = dataVotes[0];
            votes[1] = dataVotes[1];
            votes[2] = dataVotes[2];

            if(votes[0] > votes[2] && votes[1] > votes[2])
            {
             if(votes[0] > votes[1])
             {
                 color = Color.RED;
             }
             else
             {
                 color = Color.BLUE;
             }
            }
        }
    }

    public static void visualize(String region, int year) throws Exception {
        File f = new File("./input/" + region + ".txt");

        HashMap<String, ArrayList<SubRegion>> regions = new HashMap<>();

        double[] min = new double[2];
        double[] max = new double[2];

        StdDraw.enableDoubleBuffering();

        Scanner inputObject = new Scanner(f);

        String tempMin = inputObject.nextLine();
        String tempMax = inputObject.nextLine();
        String[] minArr = tempMin.split("   ");
        String[] maxArr = tempMax.split("   ");
        for (int i = 0; i < min.length; i++) {
            min[i] = Double.parseDouble(minArr[i]);
            max[i] = Double.parseDouble(maxArr[i]);
        }
        //0=long 1=lat
        StdDraw.setCanvasSize((int) (512 * (max[0] - min[0]) / (max[1] - min[1])), 512);

        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setXscale(min[0], max[0]);
        StdDraw.setYscale(min[1], max[1]);
        int subNumber = Integer.parseInt(inputObject.nextLine());
        for (int s = 0; s < subNumber; s++) {

            inputObject.nextLine();
            String subName = inputObject.nextLine();
            inputObject.nextLine();
            int subLength = Integer.parseInt(inputObject.nextLine());
            double[] xCoords = new double[subLength];
            double[] yCoords = new double[subLength];

            for (int i = 0; i < subLength; i++) {
                String[] data = inputObject.nextLine().split("   ");
                xCoords[i] = Double.parseDouble(data[0]);
                yCoords[i] = Double.parseDouble(data[1]);
            }
            SubRegion newSub = new SubRegion(subName, xCoords, yCoords);
            if (regions.containsKey(subName)) {
                regions.get(subName).add(newSub);
            }
            else {
                ArrayList<SubRegion> subList = new ArrayList<>();
                subList.add(newSub);
                regions.put(subName, subList);
            }
        }


        inputObject.close();

        File newF = new File("./input/" + region + year + ".txt");
        inputObject = new Scanner(newF);
        inputObject.nextLine();

        while (inputObject.hasNextLine()) {
            String[] line = inputObject.nextLine().split(",");
            String regName = line[0];
            int[] vote = new int[3];
            vote[0] = Integer.parseInt(line[1]);
            vote[1] = Integer.parseInt(line[2]);
            vote[2] = Integer.parseInt(line[3]);
            regions.get(regName).get(0).addVotes(vote);
            for(int i = 0; i < regions.get(regName).size(); i++)
            {
                StdDraw.setPenColor(regions.get(regName).get(0).color);
                StdDraw.filledPolygon(regions.get(regName).get(0).x, regions.get(regName).get(0).y);
            }
        }
        StdDraw.show();
    }
}