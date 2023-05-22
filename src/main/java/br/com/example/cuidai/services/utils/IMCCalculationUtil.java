package br.com.example.cuidai.services.utils;

public class IMCCalculationUtil {
    public static Double calculate(Double weight, Double height){
        return weight / Math.pow(height, 2);
    }
}
