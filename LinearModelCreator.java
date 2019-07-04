

import java.util.ArrayList;
import java.util.List;

public class LinearModelCreator {
     private static double Slope1 = 0.00;
     private static double Intercept1 = 0.00;
     private static double Slope2 =0.00;
     private static double Intercept2 =0.00;

    public void createModel(List<Double> xAxis, List<Double> yAxis){
        if(!xAxis.isEmpty() || !yAxis.isEmpty()){
            createLinearRegression1(xAxis,yAxis);
        }

    }
    public void createLinearRegression1(List<Double> xAxis, List<Double> yAxis){
        double m,b;
        int n = xAxis.size();
        double xAvg = (xAxis.stream().reduce(0.00,(total,e)->total=total+e)) / xAxis.size();
        double yAvg = (yAxis.stream().reduce(0.00,(total,e)->total=total+e)) / yAxis.size();
        double xyBarSum = 0;
        for(int i=0;i<xAxis.size();i++){
            xyBarSum = xyBarSum + ((xAxis.get(i)-xAvg) * (yAxis.get(i)-yAvg));
        }
        double xBarSquare = xAxis.stream().reduce(0.00,(total,e)->total = total+((e-xAvg)*(e-xAvg)));
        double yBarSquare = yAxis.stream().reduce(0.00,(total,e)->total = total+((e-yAvg)*(e-yAvg)));
        double passionCoEfficient = xyBarSum/Math.sqrt((xBarSquare * yBarSquare));
        double individualities = ((Math.sqrt(yBarSquare)/n)/(Math.sqrt(xBarSquare)/n));
        m = passionCoEfficient*(individualities);
        b = yAvg - m*(xAvg);
        Slope1 = m;
        Intercept1 = b;
        System.out.println("Y = "+Slope1+"* X + "+ b);
        System.out.println("Error is :"+ErrorCalculation(xAxis,yAvg,yBarSquare,1));
    }

    public double predictModel1( double value){
        double y = Slope1*(value)+Intercept1;
        return y;
    }

    public void createLinearRegression2(List<Double> xAxis, List<Double> yAxis){
        double xBar = (xAxis.stream().reduce(0.00,(total,e)->total=(total+e))) / xAxis.size();
        double yBar = (yAxis.stream().reduce(0.00,(total,e)->total=(total+e))) / yAxis.size();
        double xBarSquare = xAxis.stream().reduce(0.00,(total,e)->total = total+((e-xBar)*(e-xBar)));
        double yBarSquare = yAxis.stream().reduce(0.00,(total,e)->total=total+((e-yBar)*(e-yBar)));
        double sum =0;
        for(int i=0;i<xAxis.size();i++){
            sum = sum+((xAxis.get(i)-xBar)*(yAxis.get(i)-yBar));
        }
        Slope2 = sum/xBarSquare;
        Intercept2 = yBar - Slope2*xBar;
        System.out.println("Y = "+Slope2+"* X + "+ Intercept1);
        System.out.println("Error is :"+ErrorCalculation(xAxis,yBar,yBarSquare,2));

    }
    public double predictModel2(double value){
        return Slope2*(value)+Intercept2;
    }
    public double  ErrorCalculation(List<Double> xAxis, double yBar,double yBarSum,int choise){
        List<Double> yPredicted = new ArrayList<>();
        if(choise == 1){
            for(Double x : xAxis){
                double predicted = (Slope1 * x) + Intercept1;
                yPredicted.add(predicted);
            }
            double yPBar = yPredicted.stream().reduce(0.00,(total,e)->total=total+(e-yBar)*(e-yBar));
            double error = yPBar/yBarSum;
            return error;

        }
        else{
            for(Double x : xAxis) {
                double predicted = (Slope2 * x) + Intercept2;
                yPredicted.add(predicted);
            }

            double yPBar = yPredicted.stream().reduce(0.00,(total,e)->total=total+(e-yBar)*(e-yBar));
            double error = yPBar/yBarSum;
            return error;
        }

    }

}
