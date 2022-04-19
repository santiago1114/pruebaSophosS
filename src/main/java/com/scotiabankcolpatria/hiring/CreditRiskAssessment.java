package com.scotiabankcolpatria.hiring;

import java.util.Arrays;


/**
 * CreditRiskAssessment, a class with methods for calculate statistical values from payment
 * delays of a client
 */
public class CreditRiskAssessment {


  /**
   * calculate the standardDeviation from 1D int array
   *
   * @param paymentDelays an 1D int array with delays of payment for each period
   * @return standard deviation of payment delays
   */
  public double standardDeviation(int[] paymentDelays) {

    double std = 0;
    double m = mean(paymentDelays);

    for (int paymentDelay : paymentDelays) {
      std += Math.pow(paymentDelay - m, 2);
    }

    return Math.sqrt(std / paymentDelays.length);
  }

  /**
   * Calculate maximum value of payment delay
   *
   * @param paymentDelays an 1D int array with delays of payment for each period
   * @return int index of maximum value of payment delays
   */
  public int paymentDelayMaxPeakIndex(int[] paymentDelays) {

    int maxPeakIndex = -1, maxPeakValue = 0, maxPeakIndexAux = 0;
    int[] auxArray;
    int maxLocalIndex;

    for (int i = 1; i < paymentDelays.length - 1; i++) {
      auxArray = Arrays.copyOfRange(paymentDelays, i - 1, i + 2);
      maxLocalIndex = maxIndex(auxArray);
      if (auxArray[maxLocalIndex]>maxPeakValue) {
        maxPeakIndex = i + maxLocalIndex - 1;
        maxPeakValue = auxArray[maxLocalIndex];
      }

      boolean valid = true;
      for (int j = 0; j < auxArray.length; j++) {
        if (j != maxLocalIndex) {
          if (auxArray[maxLocalIndex] <= auxArray[j]) {
            valid = false;
            break;
          }
        }
      }
      if (valid && auxArray[maxLocalIndex] > maxPeakValue){

        maxPeakIndex = i + maxLocalIndex - 1;
        if (maxPeakIndexAux==0) maxPeakIndexAux = maxPeakIndex;
        maxPeakValue = paymentDelays[maxPeakIndex];
      }

    }

    return maxPeakIndex;
  }

  /**
   * Calculate late payment probability by period based on 2D array with payment delays
   *
   * @param paymentDelays an 2D int array with delays of payment for each period
   * @return an 1D double array with late payment probability
   */
  public double[] latePaymentProbabilityByPeriod(int[][] paymentDelays) {

    final int PRODUCTS = paymentDelays.length;
    final int PERIODS = paymentDelays[0].length;
    final double PROBABILITY = 1.0 / PRODUCTS;
    double[] arrayProbabilityByPeriod = new double[PERIODS];

    for (int i = 0; i < PRODUCTS; i++) {
      for (int j = 0; j < PERIODS; j++) {
        if (paymentDelays[i][j] > 0) {
          arrayProbabilityByPeriod[j] += PROBABILITY;
        }
      }
    }

    return arrayProbabilityByPeriod;
  }

  /**
   * Returns mean value
   *
   * @param data 1D int array
   * @return mean of data
   */
  public static double mean(int[] data) {
    double sum = 0;
    for (int i : data) {
      sum += i;
    }
    return sum / data.length;
  }

  public static int maxIndex(int[] data) {
    int maxPeakIndex = 0, maxPeakValue = 0;

    for (int i = 0; i < data.length; i++) {
      if (data[i] > maxPeakValue) {
        maxPeakValue = data[i];
        maxPeakIndex = i;
      }
    }
    return maxPeakIndex;
  }

}
