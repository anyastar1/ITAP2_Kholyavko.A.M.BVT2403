package com.example;

public class ArrayAverage1 {
    public static void main(String[] args) {
        Object[] arr = {1, 2, 3, 4, "a", 5}; 
        int sum = 0;
        int count = 0;
    
        try {
            for (int i = 0; i < arr.length; i++) {
                sum += Integer.parseInt(arr[i].toString());
                count++;
            }
            System.out.println("Среднее: " + (double) sum / count);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Ошибка: выход за границы массива");
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: элемент массива не является числом");
        }
    }
 }
 