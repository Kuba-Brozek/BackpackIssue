package com.company;

public class Chromosome {

    int Ch = 0;
    String ChromosomeBinar = null;
    double Percent = 0.0;
    int WeightOfChromosome = 0;
    int ValueOfChromosome =0;


    public Chromosome (int Ch, double Percent){
        this.Ch = Ch;
        this.Percent = Percent;
    }


    public Chromosome(int ch, String chromosomeBinar, double percent, int weightOfChromosome, int valueOfChromosome) {
        Ch = ch;
        ChromosomeBinar = chromosomeBinar;
        Percent = percent;
        WeightOfChromosome = weightOfChromosome;
        ValueOfChromosome = valueOfChromosome;
    }

    public int getCh() {
        return Ch;
    }

    public void setCh(int ch) {
        Ch = ch;
    }

    public String getChromosomeBinar() {
        return ChromosomeBinar;
    }

    public void setChromosomeBinar(String chromosomeBinar) {
        ChromosomeBinar = chromosomeBinar;
    }

    public double getPercent() {
        return Percent;
    }

    public void setPercent(double percent) {
        Percent = percent;
    }

    public int getWeightOfChromosome() {
        return WeightOfChromosome;
    }

    public void setWeightOfChromosome(int weightOfChromosome) {
        WeightOfChromosome = weightOfChromosome;
    }

    public int getValueOfChromosome() {
        return ValueOfChromosome;
    }

    public void setValueOfChromosome(int valueOfChromosome) {
        ValueOfChromosome = valueOfChromosome;
    }
}
