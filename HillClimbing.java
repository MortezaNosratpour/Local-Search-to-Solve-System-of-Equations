import java.io.File;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class HillClimbing {
    public static void main(String[] args) {
       ArrayList<Coefficients> coefficient = new ArrayList<>();
        System.out.println("Please enter the file name: ");
        Scanner scanner = new Scanner(System.in);
        String fileName = scanner.nextLine();
        fileLoader(fileName, coefficient);
        System.out.println("Please enter the range's first number :");
        double rangeFirstNumber = scanner.nextDouble();
        System.out.println("Please enter the range's last number :");
        double rangeLastNumber = scanner.nextDouble();
        System.out.println("Please enter number of step :");
        double step = scanner.nextDouble();
        int numOfXi = coefficient.get(0).coefficient.length;
        int numOfEquation = coefficient.size();
        int time = 100;
        double[][] answer = new double[time][numOfXi+1];
       LocalTime startTime =java.time.LocalTime.now();
        Climbing(numOfXi, rangeFirstNumber, rangeLastNumber, step, coefficient);
        for (int counter =0; counter < time; counter++){
             answer[counter] = Climbing(numOfXi, rangeFirstNumber, rangeLastNumber, step, coefficient);
        }

        double min = Double.MAX_VALUE;
        int index  = 0;
        for (int counter = 0; counter < time; counter++){
            if (min > answer[counter][numOfXi]){
                index = counter;
                min = answer[counter][numOfXi];
            }
        }
        LocalTime endTime = java.time.LocalTime.now();
        int diff = endTime.getNano() - startTime.getNano();
        System.out.println(index);
        System.out.println(java.time.LocalTime.now());
        System.out.println("the best one is this :");
        for (int counter = 0; counter < numOfXi; counter++){
            System.out.print(answer[index][counter] + ", ");
        }
        System.out.println("average tolorance is : " + answer[index][numOfXi]/numOfXi);
//        System.out.println("others are : ");
//        for (int i = 0; i < time; i++){
//            for (int j = 0; j < numOfXi; j++){
//                System.out.print(answer[i][j] + ", ");
//            }
//            System.out.println("average tolorance is : " + answer[i][numOfXi]/numOfXi );
//        }
        System.out.println("algorithm run in " + diff + " nanoSecond");
    }

    private static void fileLoader(String fileName, ArrayList<Coefficients> coefficient){
        File file = new File(fileName);
        try {
            Scanner fileScanner = new Scanner(file);
            System.out.println(fileName + " found successfully!");
            while (fileScanner.hasNextLine()){
                String string = fileScanner.nextLine();
                coefficient.add(new Coefficients(string));
            }
        }
        catch (Exception e){
            //System.out.println("There is a problem with finding " + fileName);
        }
    }


    private static double randomGenerator(double a, double b){
        double uniformRandom = Math.random();
        double randomNumber = (a + (b-a)*uniformRandom) ;
        return randomNumber;
    }


    private static double calculateTolorance(double[] newXi, ArrayList<Coefficients> coefficients, double sumOfMainResult){
        double sumofResults = 0, tolorance = 0;
        for (int i = 0 ; i < coefficients.size(); i++){
            double result = 0;
            for (int j = 0 ; j < newXi.length - 1 ; j++){
               // System.out.println("coefficient : "+ coefficients.get(i).coefficient[j] + " xi"  +newXi[j]);
                result += coefficients.get(i).coefficient[j] * newXi[j];
            }
            sumofResults += Math.pow((result - coefficients.get(i).resultOfEquation), 2);
        }
        return sumofResults;
    }


    private static  double[] Climbing(int numOfXi, double firstNumOfRang, double lastNumOfRang, double step, ArrayList<Coefficients> coefficients){
        double sumOfMainEquationsResult = sumOfEquationResult(coefficients);
       double[] Xi  = new double[numOfXi + 1];
        //System.out.println("the random first state : ");
        for (int counter = 0; counter < numOfXi; counter++){
            Xi[counter] = randomGenerator(firstNumOfRang/2, lastNumOfRang/2);
            //System.out.print(Xi[counter] + ", ");
        }

        boolean flag = true;
        double[] plateu = new double[numOfXi + 1];
        boolean isInPlateu= false;
        boolean shouldBack = false;
        double[] back = new double[numOfXi + 1];
        int inPlateu = 0;
        int loopcounter = 0;
        while (flag){
            double currentStateErr = calculateTolorance(Xi, coefficients, sumOfMainEquationsResult);
            double[] nextNeighbor = makeNewNeighbor(numOfXi, Xi, currentStateErr, coefficients, step, sumOfMainEquationsResult);
            if (nextNeighbor == null){
                shouldBack = true;
                flag = false;
                for (int counter = 0; counter < numOfXi + 1; counter++){
                   back[counter] = Xi[counter];
                }
            }
            else if (nextNeighbor.length == 1 && nextNeighbor[0] == 0){
                for (int counter = 0; counter < numOfXi + 1; counter++){
                    plateu[counter] = Xi[counter];
                }
                inPlateu++;
                if (inPlateu == 1000){
                    flag = false;
                    isInPlateu = true;
                }
                if (inPlateu % 50 == 0){
                    step = step * Math.pow(1.2, 15);
                }

            }
            else{
                Xi = nextNeighbor;

                if (loopcounter % 10 == 0){
                    step = step/1.2;
                }
                loopcounter++;
            }
        }
        if (isInPlateu){
            int loopCounter = 0;
            double[] maxOfPlateu = new double[numOfXi + 1];
            for (int counter = 0; counter < numOfXi; counter++){
                maxOfPlateu[counter] = Xi[counter];
            }
            while (loopCounter != 100){
                step = step * 1.2;
                loopCounter++;
                double currentStateErr = calculateTolorance(maxOfPlateu, coefficients, sumOfMainEquationsResult);
                double[] nextNeighbor = makeNewNeighbor(numOfXi, maxOfPlateu, currentStateErr, coefficients, step, sumOfMainEquationsResult);
                if (nextNeighbor == null){
                    if (plateu[numOfXi] > Xi[numOfXi]){
                        return maxOfPlateu;
                    }
                    else{
                        return maxOfPlateu;
                    }
                }
                if (nextNeighbor.length == 1 && nextNeighbor[0] == 0){
                    continue;
                }
                else{
                    maxOfPlateu = nextNeighbor;
                }
            }
        }
        if (shouldBack){
            double maxEInBack = back[numOfXi];
            step = (-1*step)/10;
            boolean backFlag = true;
            while (backFlag){
                double currentStateErr = calculateTolorance(Xi, coefficients, sumOfMainEquationsResult);
                double[] nextNeighbor = makeNewNeighbor(numOfXi, Xi, currentStateErr, coefficients, step, sumOfMainEquationsResult);
                if (nextNeighbor == null){
                    backFlag = false;
                    return Xi;
                }
                else if (nextNeighbor.length == 1 && nextNeighbor[0] == 0){
                    backFlag = false;
                    return Xi;
                }
                else{
                    Xi = nextNeighbor;
                }
            }
        }
        return Xi;
    }
    private static double[] makeNewNeighbor(int numOfXi, double[] currentState, double currentStateE, ArrayList<Coefficients>coefficients, double step, double sumOfResults){
        double[][] neighbors = new double[2*numOfXi][numOfXi+1];
        double[] tempCurrentState = new double[numOfXi + 1];
        for (int counter = 0; counter < numOfXi + 1; counter++){
            tempCurrentState[counter] = currentState[counter];
        }
        for (int counter = 0; counter < numOfXi; counter++){
            tempCurrentState[counter] += step;
            for (int counter2 = 0; counter2 < numOfXi; counter2++){
                neighbors[counter][counter2] = tempCurrentState[counter2];
            }
            neighbors[counter][numOfXi] = calculateTolorance(neighbors[counter], coefficients, sumOfResults);
            tempCurrentState[counter] -= step;
        }
        for (int counter = numOfXi; counter < 2*numOfXi; counter++){
            tempCurrentState[counter - numOfXi] -= step;
            for (int counter2 = 0; counter2 < numOfXi; counter2++){
                neighbors[counter][counter2 ] = tempCurrentState[counter2];
            }
            neighbors[counter][numOfXi] = calculateTolorance(neighbors[counter], coefficients, sumOfResults);
            tempCurrentState[counter - numOfXi] += step;
        }
        double minE = Double.MAX_VALUE;
        int index = 0;
        for (int counter = 0; counter < 2*numOfXi; counter++){
            if (neighbors[counter][numOfXi] < minE){
                index = counter;
                minE = neighbors[counter][numOfXi];
            }
        }
        if (minE > currentStateE){
            return null;
        }
        else if (minE == currentStateE){
            double[]x = {0};
            return x;
        }
        else
            return neighbors[index];


    }

    private static double sumOfEquationResult(ArrayList<Coefficients> coefficients){
        int sumOfEquationsResult = 0;
        for (int counter = 0; counter < coefficients.size(); counter++){
            sumOfEquationsResult += coefficients.get(counter).resultOfEquation;
        }
        return sumOfEquationsResult;
    }
}
