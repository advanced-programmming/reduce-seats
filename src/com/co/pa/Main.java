package com.co.pa;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main {

    public static void main(String[] args) {
	    int[] P1 = {1, 4, 1}; // 2
        //int[] P2 = {4, 4, 2, 4}; // 3
        //int[] P3 = {2, 3, 4, 2}; // 2

        int[] S1 = {1, 5, 1};
        //int[] S2 = {5, 5, 2, 5};
        //int[] S3 = {2, 5, 7, 2};

        System.out.printf(solution(P1, S1) + "");
    }


    public static int solution(int[] P, int[] S){
        List<Car> carList = new ArrayList<>(P.length);
        int totalP = 0;

        for(int i = 0; i < P.length; i++) {
            carList.add(new Car(S[i], P[i]));
            totalP += P[i];
        }
        Collections.sort(carList, Comparator.comparingInt(Car::getAvailable).reversed());

        int assignedP = 0;
        for(int i = 0; i < carList.size(); i++){
            Car current = carList.get(i);
            assignedP += current.getP();

            int index = (i + 1);
            while(!carList.get(i).isFull() && assignedP != totalP){
                Car carIndex = carList.get(index);
                int totalPersonCarIndex = carIndex.getP();

               if(totalPersonCarIndex <= current.getAvailable()) {
                   current.setP(current.getP() + totalPersonCarIndex);
                   carIndex.setP(0);
                   assignedP += totalPersonCarIndex;
               } else {
                   int totalPersonToMoveCar = current.getAvailable();
                   current.setP(current.getP() + totalPersonToMoveCar);
                   carIndex.setP(carIndex.getP() - totalPersonToMoveCar);
                   assignedP += totalPersonToMoveCar;
               }

               index++;
            }


        }

        return (int) carList.stream().filter(x -> x.getP() > 0).count();
    }
}


class Car {
    private int s;
    private int p;
    private boolean isFull;
    private int available;

    public Car(int s, int p){
        this.s = s;
        this.p = p;
        this.isFull = s == p;
        this.available = this.isFull ? 0 : (s - p);
    }

    public int getS() {
        return s;
    }

    public int getP() {
        return p;
    }

    public boolean isFull() {
        return isFull;
    }

    public int getAvailable() {
        return available;
    }

    public void setS(int s) {
        this.s = s;
    }

    public void setP(int p) {
        this.p = p;
        this.isFull = s == p;
        this.available = this.isFull ? 0 : (s - p);
    }

    public void setFull(boolean full) {
        isFull = full;
    }

    public void setAvailable(int available) {
        this.available = available;
    }
}
