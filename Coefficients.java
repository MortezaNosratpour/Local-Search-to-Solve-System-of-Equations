public class Coefficients {
    double[] coefficient;
    double resultOfEquation;
    public Coefficients(String coefficient){
        String[] splittedCoefficient = coefficient.split(",");
        int numOfCoefficient = splittedCoefficient.length;
        this.coefficient = new double[numOfCoefficient-1];
        for (int counter = 0; counter < numOfCoefficient-1; counter++){
            this.coefficient[counter] = Double.valueOf(splittedCoefficient[counter]);
          //  System.out.print(this.coefficient[counter] );
        }
        this.resultOfEquation = Double.valueOf(splittedCoefficient[numOfCoefficient-1]);
        //System.out.println(this.resultOfEquation);
    }
}
