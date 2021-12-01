package com.company;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {
    private static ArrayList<Chromosome> ChromosomeList = new ArrayList<>();
    private static ArrayList<Chromosome> ChromosomeList2 = new ArrayList<>();
    private static ArrayList<VW> VWList = new ArrayList<>();
    private static ArrayList<PercentageRangeClass> PercentageRangeList = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj liczbe chromosomów: ");
        int ChromosomesNumber = scanner.nextInt();
        /*
        System.out.println("Podaj wagę dla każdego bitu");
        for(int i = 0; i< 10; i++){
            int Weight = scanner.nextInt();
            WeightList.add(i, Weight);
            WeightListCalculated.add(i, 0);
        }
        System.out.println("Podaj wartość dla każdego bitu");
        for(int i = 0; i< 10; i++){
            int Value = scanner.nextInt();
            ValueList.add(i, Value);
            ValueListCalculated.add(i, 0);
        }*/

        System.out.println("Podaj Pk i Pm jako wartość od 0 do 1 w typie double: ");
        double Pk = scanner.nextDouble();
        double Pm = scanner.nextDouble();
        System.out.println("Podaj maksymalną wagę plecaka: ");
        int MaxWeight = scanner.nextInt();
        System.out.println("Podaj liczbę maksymalnych wystąpień wartości funkcji przystosowania");
        int NumOfMaxFitFunOccur = scanner.nextInt();
        int NumOfFitListIterations = 0;

        int NumOfIterations = 0;

        /*for(int i = 0; i< 10; i++){
            int Weight = scanner.nextInt();
            ChromosomeList.get(i).setWeight(Weight);
            ChromosomeList2.get(i).setWeight(Weight);
        }

        for(int i = 0; i< 10; i++){
            int Value = scanner.nextInt();
            ChromosomeList.get(i).setValue(Value);
            ChromosomeList2.get(i).setValue(Value);
        }
        */
        VWList.add(new VW(4,12));
        VWList.add(new VW(9,6));
        VWList.add(new VW(12,10));
        VWList.add(new VW(4,3));
        VWList.add(new VW(6,3));
        VWList.add(new VW(11,18));
        VWList.add(new VW(8,12));
        VWList.add(new VW(12,4));
        VWList.add(new VW(6,8));
        VWList.add(new VW(8,6));

        for ( int i=0; i<ChromosomesNumber; i++){
            int RandNumHelper = RandomNumberChromosome();
            ChromosomeList.add(new Chromosome(RandNumHelper));
            ChromosomeList2.add(new Chromosome(RandNumHelper));
            System.out.println(" | Początkowa pula chromosomów: " + ChromosomeList.get(i).getCh() + " | ");
        }

        for(int i = 0; i < ChromosomesNumber; i++) {
            StringBuilder BinarValueBuilder = new StringBuilder(Integer.toBinaryString(ChromosomeList.get(i).getCh()));
            while (BinarValueBuilder.length() < 10) {
                BinarValueBuilder.insert(0, "0");
            }
            String BinarValue = BinarValueBuilder.toString();
            ChromosomeList.get(i).setChromosomeBinar(BinarValue);
            System.out.println(ChromosomeList.get(i).getChromosomeBinar());
            ChromosomeList2.get(i).setChromosomeBinar(BinarValue);
        }

        int Final = 0;
        //for(;;) {
            double PercentageIterationValue = 0.00;
            double aValuePercent = 0;
            int ValueOfFitFun = 0;

            for (int j = 0; j < ChromosomesNumber; j++) {
                int WeightOfFitFun = 0;
                int CalculatedWeight = 0;
                for (int i = 0; i < 10; i++) {
                    char Checker = ChromosomeList.get(j).getChromosomeBinar().charAt(i);
                    CalculatedWeight = Integer.parseInt(String.valueOf(Checker)) *VWList.get(i).getWeight();
                    WeightOfFitFun += CalculatedWeight;
                }
                ChromosomeList.get(j).setWeightOfChromosome(WeightOfFitFun);
                /*
                for(int i=0; i<ChromosomesNumber;i++) {
                    while (MaxWeight > WeightListCalculated.get(i)) {
                        double x = RandomPercentageNumber();
                        int Lokus = RandomNumberLokusPm();
                        if(x<Pm){
                            String Beg = ChromosomeList.get(i).getChromosomeBinar().substring(0,Lokus-1);
                            String End = ChromosomeList.get(i).getChromosomeBinar().substring(Lokus);
                            String Mutate = ChromosomeList.get(i).getChromosomeBinar().substring(Lokus-1, Lokus);

                            if(Mutate.equals("0")){
                                String ChromosomeBinarFinal = Beg.concat("1"+End);
                                ChromosomeList.get(i).setChromosomeBinar(ChromosomeBinarFinal);
                            }
                            else if(Mutate.equals("1")){
                                String ChromosomeBinarFinal = Beg.concat("0"+End);
                                ChromosomeList.get(i).setChromosomeBinar(ChromosomeBinarFinal);
                            }
                        }
                    }
                }*/
                //ChromosomeList.get(j).setWeight(WeightOfFitFun);

            }
            for(int i=0;i<6;i++){
                System.out.println(ChromosomeList.get(i).getWeightOfChromosome());
            }
            /*
            if (ValueOfFitFun > Final) {
                Final = ValueOfFitFun;
                NumOfFitListIterations = 1;
            } else if (ValueOfFitFun == Final) {
                NumOfFitListIterations++;
            }
            if (NumOfFitListIterations == NumOfMaxFitFunOccur) {
                break;
            }
            */

        //}

    }

    public static int RandomNumberChromosome() {
        Random r = new Random();
        return r.nextInt((1023 - 1) + 1) + 1;
    }
    public static double RandomPercentageNumber() {
        return (Math.random() * (1));
    }
    public static int RandomNumberLokusPm() {
        Random r = new Random();
        return r.nextInt((10 - 1) + 1) + 1;
    }
    public static int RandomNumberLokusPk() {
        Random r = new Random();
        return r.nextInt((9 - 1) + 1) + 1;
    }
    public static int Random16() {
        Random r = new Random();
        return r.nextInt((6 - 1) + 1) + 1;
    }


    public static int CalcFun(int a, int b, int c, int d, int x){
        return (int) (a*(Math.pow(x, 3)) + b*(Math.pow(x, 2)) + c*x + d);
    }

    public static double PercentageValue(int a, int b){
        double c = a;
        double d = b;
        double x = c/ d;
        return x;
    }
}