package nl.hva.ict.ads;

public class  ElectionsProcessing {
    static final String FILENAME = "/president_county_candidate.csv";

    public static void main (String[] args) {

        StatisticsPrinter statPrinter = new StatisticsPrinter();

        statPrinter.printTotalVotes( new CsvTable(FILENAME) );

        statPrinter.printVotesForWinners(new CsvTable(FILENAME));

        statPrinter.printVotesForLosers( new CsvTable(FILENAME) );

        statPrinter.printVotesPerParty( new CsvTable(FILENAME) );
    }
}
