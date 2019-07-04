import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class LinearRegression {
    private static String source = "/Users/giri/Documents/LearningStuff/MachineLearning/accidents.txt";
    public static void main(String[] args) throws Exception{
        try {
            Scanner sc = new Scanner(System.in);
            FileReader file = new FileReader(source);
            Scanner in = new Scanner(file);
            List<Double> xAxisList = new ArrayList<>();
            List<Double> yAxisList = new ArrayList<>();
            in.nextLine();
            while (in.hasNext()) {
                xAxisList.add(Double.parseDouble(in.next()));
                in.next();
                yAxisList.add(Double.parseDouble(in.next()));
                in.next();
                in.next();
            }
            LinearModelCreator linearModel = new LinearModelCreator();
            linearModel.createModel(xAxisList, yAxisList);
            Thread.sleep(1000);
            linearModel.createLinearRegression2(xAxisList,yAxisList);
            System.out.println("------------------------------------------->");
            System.out.println("Enter the year to predict the value ");
            int value;
            value = sc.nextInt();
            System.out.println("Predicted value is Model1-> "+ linearModel.predictModel1((double)value));
            Thread.sleep(1000);
            System.out.println("Predicted value is Model2->"+linearModel.predictModel2((double)value));
        }
        catch(Exception e){
            System.out.println("Enter the right choise");
        }
    }
}
