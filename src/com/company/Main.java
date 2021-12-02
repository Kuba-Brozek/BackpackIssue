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
        System.out.println("Podaj Wagę i Wartość dla " + i + " bitu: ");
            int Weight = scanner.nextInt();
            int Value = scanner.nextInt();
            VWList.add(new VW(Weight, Value));
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
        System.out.println(" | Początkowa pula chromosomów | ");
        for ( int i=0; i<ChromosomesNumber; i++){
            int RandNumHelper = RandomNumberChromosome();
            ChromosomeList.add(new Chromosome(RandNumHelper, 0.0));
            ChromosomeList2.add(new Chromosome(RandNumHelper, 0.0));
            PercentageRangeList.add(new PercentageRangeClass(0.0,0.0));
            System.out.print(" |  " + ChromosomeList.get(i).getCh() + " | ");
        }

        for(int i = 0; i < ChromosomesNumber; i++) {
            StringBuilder BinarValueBuilder = new StringBuilder(Integer.toBinaryString(ChromosomeList.get(i).getCh()));
            while (BinarValueBuilder.length() < 10) {
                BinarValueBuilder.insert(0, "0");
            }
            String BinarValue = BinarValueBuilder.toString();
            ChromosomeList.get(i).setChromosomeBinar(BinarValue);

            ChromosomeList2.get(i).setChromosomeBinar(BinarValue);
        }

        int Final = 0;
        int FinalValue = 0;
        for(;;) {
            int ValueOfFitFunForAllChromosomes = 0;
            double PercentageIterationValue = 0.00;
            double aValuePercent = 0;
            int ValueOfFitFun = 0;

            for (int j = 0; j < ChromosomesNumber; j++) {
                //that loop calculates Weight of each chromosome based on users keyboard input, checks if weight is bigger than admissible, and if so it throws one of bits from 1 to 0 untill
                // it finds first weight which is below limit
                int WeightOfFitFun = 0;
                int CalculatedWeight = 0;
                for (int i = 0; i < 10; i++) {
                    char Checker = ChromosomeList.get(j).getChromosomeBinar().charAt(i);
                    CalculatedWeight = Integer.parseInt(String.valueOf(Checker)) * VWList.get(i).getWeight();
                    WeightOfFitFun += CalculatedWeight;
                }
                ChromosomeList.get(j).setWeightOfChromosome(WeightOfFitFun);
                if (MaxWeight < ChromosomeList.get(j).getWeightOfChromosome()){
                    for (;;) {
                        int Bit = RandomNumberLokusPm();
                        String Beg = ChromosomeList.get(j).getChromosomeBinar().substring(0, Bit - 1);
                        String End = ChromosomeList.get(j).getChromosomeBinar().substring(Bit);
                        String Mutate = String.valueOf(ChromosomeList.get(j).getChromosomeBinar().charAt(Bit - 1));
                        if (Mutate.equals("1")) {
                            String ChromosomeBinarFinal = Beg + "0" + End;
                            ChromosomeList.get(j).setChromosomeBinar(ChromosomeBinarFinal);
                            ChromosomeList2.get(j).setChromosomeBinar(ChromosomeBinarFinal);
                            int decimal = Integer.parseInt(ChromosomeList.get(j).getChromosomeBinar(),2);
                            ChromosomeList.get(j).setCh(decimal);
                            ChromosomeList2.get(j).setCh(decimal);
                            ChromosomeList.get(j).setWeightOfChromosome(ChromosomeList.get(j).getWeightOfChromosome() - VWList.get(Bit - 1).getWeight());
                            ChromosomeList2.get(j).setWeightOfChromosome(ChromosomeList.get(j).getWeightOfChromosome() - VWList.get(Bit - 1).getWeight());
                        }
                        if (MaxWeight > ChromosomeList.get(j).getWeightOfChromosome()) {
                            break;
                        }
                    }
                }
            }

            for(int j = 0; j < ChromosomesNumber; j++){
                int ValueofFitFun = 0;
                int CalculatedValue = 0;
                for (int i = 0; i < 10; i++) {
                    char Checker = ChromosomeList.get(j).getChromosomeBinar().charAt(i);
                    CalculatedValue = Integer.parseInt(String.valueOf(Checker)) * VWList.get(i).getValue();
                    ValueofFitFun += CalculatedValue;
                }
                ChromosomeList.get(j).setValueOfChromosome(ValueofFitFun);
                ValueOfFitFunForAllChromosomes += ChromosomeList.get(j).getValueOfChromosome();

            }
            if (ValueOfFitFunForAllChromosomes > Final) {
                Final = ValueOfFitFunForAllChromosomes;
                NumOfFitListIterations = 1;
            } else if(ValueOfFitFunForAllChromosomes == Final){
                NumOfFitListIterations++;
            }
            if (NumOfFitListIterations == NumOfMaxFitFunOccur) {
                break;
            }


            for(int i = 0; i<ChromosomesNumber; i++){
                ChromosomeList.get(i).setPercent(PercentageValue(ChromosomeList.get(i).getValueOfChromosome(),ValueOfFitFunForAllChromosomes));
            }
            for(int i = 0; i< ChromosomesNumber; i++){
                aValuePercent = PercentageIterationValue;
                PercentageIterationValue += ChromosomeList.get(i).getPercent();
                PercentageRangeList.set(i, new PercentageRangeClass(aValuePercent, PercentageIterationValue));
            }
            for(int i=0; i< ChromosomesNumber; i++){
                double x = RandomPercentageNumber();
                for(int j=0; j< ChromosomesNumber; j++){
                    if( x > PercentageRangeList.get(j).a && x < PercentageRangeList.get(j).b){
                        ChromosomeList.get(i).setCh(ChromosomeList2.get(j).getCh());
                    }
                }
            }
            for(int i = 0; i < ChromosomesNumber; i++) {
                ChromosomeList2.get(i).setCh(ChromosomeList.get(i).getCh());
                StringBuilder BinarValueBuilder = new StringBuilder(Integer.toBinaryString(ChromosomeList.get(i).getCh()));
                while (BinarValueBuilder.length() < 10) {
                    BinarValueBuilder.insert(0, "0");
                }
                String BinarValue = BinarValueBuilder.toString();
                ChromosomeList.get(i).setChromosomeBinar(BinarValue);
                ChromosomeList2.get(i).setChromosomeBinar(BinarValue);
            }

            int PkhelperNumber = 0;
            for(int i = 0; i < ChromosomesNumber/2; i++) {
                double x = RandomPercentageNumber();
                int random16 = Random16();
                int random162 = Random16();
                PkhelperNumber += 2;
                int Lokus = RandomNumberLokusPk();

                if(x< Pk){
                    String BegOneOf2 = ChromosomeList2.get(random16-1).getChromosomeBinar().substring(0, Lokus);
                    String BegTwoOf2 = ChromosomeList2.get(random162-1).getChromosomeBinar().substring(0, Lokus);
                    String EndOneOf2 = ChromosomeList2.get(random16-1).getChromosomeBinar().substring(Lokus, ChromosomeList.get(i).getChromosomeBinar().length());
                    String EndTwoOf2 = ChromosomeList2.get(random162-1).getChromosomeBinar().substring(Lokus, ChromosomeList.get(i).getChromosomeBinar().length());
                    String One = BegOneOf2+EndTwoOf2;
                    String Two = BegTwoOf2+EndOneOf2;
                    ChromosomeList.get(PkhelperNumber-2).setChromosomeBinar(One);
                    ChromosomeList.get(PkhelperNumber-1).setChromosomeBinar(Two);
                }
            }

            for(int i = 0; i < ChromosomesNumber; i++) {
                double x = RandomPercentageNumber();
                int Lokus = RandomNumberLokusPm();
                if (x < Pm) {
                    String Beg = ChromosomeList.get(i).getChromosomeBinar().substring(0, Lokus-1);
                    String End = ChromosomeList.get(i).getChromosomeBinar().substring(Lokus);
                    String Mutate = ChromosomeList.get(i).getChromosomeBinar().substring(Lokus-1, Lokus);
                    if(Mutate.equals("0")){
                        String ChromosomeBinarFinal = Beg+"1"+End;
                        ChromosomeList.get(i).setChromosomeBinar(ChromosomeBinarFinal);
                    }
                    else if(Mutate.equals("1")){
                        String ChromosomeBinarFinal = Beg+"1"+End;
                        ChromosomeList.get(i).setChromosomeBinar(ChromosomeBinarFinal);
                    }
                }
            }

            for(int i = 0; i< ChromosomesNumber; i++){
                int decimal = Integer.parseInt(ChromosomeList.get(i).getChromosomeBinar(), 2);
                ChromosomeList.get(i).setCh(decimal);
                ChromosomeList2.get(i).setCh(decimal);
            }
            NumOfIterations++;
        }
        System.out.println("\n | Liczba iteracji: " + NumOfIterations+ " | ");
        //System.out.println("Największy fenotyp: "+FinalValue);
        System.out.println(" | Największa funkcja przystosowania: "+ Final/6 + " | ");
        for(int i = 0; i< ChromosomesNumber; i++){
            int j = i+1;
            System.out.print(" | CH"+j+ " = " + ChromosomeList.get(i).getChromosomeBinar()+ " | ");
            System.out.print(" | Fenotyp: " + ChromosomeList.get(i).getCh()+ " | ");
            System.out.print(" | Wartość plecaka: " + ChromosomeList.get(i).getValueOfChromosome()+ " | ");
            System.out.println(" | Waga plecaka: " + ChromosomeList.get(i).getWeightOfChromosome()+ " | ");
        }
    }

    public static int RandomNumberChromosome() {
        Random r = new Random();
        return r.nextInt((1023 - 1) + 1);
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

    public static double PercentageValue(int a, int b){
        double c = a;
        double d = b;
        double x = c/ d;
        return x;
    }
}