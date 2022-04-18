package com.scotiabankcolpatria.hiring;

public class CreditRiskAssessment {

    public double standardDeviation(int[] paymentDelays) {
        
        double std = 0;
        double mean = this.mean(paymentDelays);

        for(int paymentDelay: paymentDelays) {
          std += Math.pow(paymentDelay-mean,2);  
        } 

        return Math.sqrt(std/paymentDelays.length);
    }

    public int paymentDelayMaxPeakIndex(int[] paymentDelays) {
        //TODO implement.
        return -1;
    }

    public double[] latePaymentProbabilityByPeriod(int[][] paymentDelays) {
        //TODO implement.
        return null;
    }


    public static double mean(int[] data){

        for(int i: paymentDelays) {
            sum += i;
        }
        return sum/data.length;
    }

}
