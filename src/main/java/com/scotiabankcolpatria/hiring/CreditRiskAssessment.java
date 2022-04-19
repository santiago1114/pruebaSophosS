package com.scotiabankcolpatria.hiring;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


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

    List<Integer> invalidIndexes = new ArrayList<>();
    List<Integer> peaksIdx = new ArrayList<>();
    List<Double> stds = new ArrayList<>();
    int maxIdx;
    boolean valid = false;
    int[] paymentDelaysAux = Arrays.copyOf(paymentDelays, paymentDelays.length);

    do {

      // get index of max value for auxiliary array
      maxIdx = maxIndex(paymentDelaysAux);

      // Conditional for the first index of the array
      if (maxIdx == 0){

        if (paymentDelays[maxIdx] > paymentDelays[maxIdx+1] &&
          paymentDelays[maxIdx] > paymentDelays[maxIdx+2]) {

          stds.add(standardDeviation(new int[]{paymentDelays[maxIdx+1],
            paymentDelays[maxIdx], paymentDelays[maxIdx+1]}));
          valid = true;
        }
        // Conditional for the last index of the array
      } else if (maxIdx == paymentDelays.length - 1) {

        if (paymentDelays[maxIdx] > paymentDelays[maxIdx-1] &&
          paymentDelays[maxIdx] > paymentDelays[maxIdx-2]) {

          stds.add(standardDeviation(new int[]{paymentDelays[maxIdx-1],
            paymentDelays[maxIdx], paymentDelays[maxIdx-2]}));
          valid = true;
        }

      } else {

        if (paymentDelays[maxIdx] > paymentDelays[maxIdx-1] &&
          paymentDelays[maxIdx] > paymentDelays[maxIdx+1]) {

          stds.add(standardDeviation(new int[]{paymentDelays[maxIdx-1],
            paymentDelays[maxIdx], paymentDelays[maxIdx+1]}));
          valid = true;
        }

      }

      if (valid) peaksIdx.add(maxIdx); // Add to peak array
      else invalidIndexes.add(maxIdx); // Add to invalid peak array
      valid = false;                   // Reset flag
      paymentDelaysAux[maxIdx] = 0;    // Discard value of auxiliary array

    } while ((invalidIndexes.size()+peaksIdx.size()) < paymentDelays.length - 1 );

    maxIdx = maxIndex(stds);           // Get maximum index std of valid peaks

    if (peaksIdx.size() == 0 ) return -1; // When no valid peaks is obtained
    else return peaksIdx.get(maxIdx);     // Return valid peak with max std
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

    for (int[] paymentDelay : paymentDelays) {
      for (int j = 0; j < PERIODS; j++) {
        if (paymentDelay[j] > 0) {
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

  /**
   * Returns mean value
   *
   * @param data 1D List<Double> array
   * @return mean of data
   */
  public static int maxIndex(List<Double> data) {
    int maxPeakIndex = 0;
    double maxPeakValue = 0;

    for (int i = 0; i < data.size(); i++) {
      if (data.get(i) > maxPeakValue) {
        maxPeakValue = data.get(i);
        maxPeakIndex = i;
      }
    }
    return maxPeakIndex;
  }

  /**
   * Gets index of maximum value from array
   * @param data int 1D array
   * @return     int index
   */
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
