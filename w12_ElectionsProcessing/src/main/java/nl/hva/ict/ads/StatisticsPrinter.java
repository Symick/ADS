package nl.hva.ict.ads;

import java.sql.SQLOutput;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class StatisticsPrinter {

    //TODO Implement all print methods conform specifications in exercise Elections Processing

    public void printTotalVotes ( CsvTable table ) {
        System.out.println("Total votes all counties of USA: " +
                table.stream().map(CandidateCountyVotes::new).mapToInt(CandidateCountyVotes::getTotalVotes).sum());
     }

    public void printVotesForWinners ( CsvTable table ) {
        System.out.println("Total votes for the winning candidates: " +
                table.stream().map(CandidateCountyVotes::new).filter(CandidateCountyVotes::isHasWon).mapToInt(CandidateCountyVotes::getTotalVotes).sum());
    }

    public void printVotesForLosers ( CsvTable table ) {
        System.out.println("Total votes for the losing candidates:" +
                table.stream().map(CandidateCountyVotes::new)
                        .filter(candidateCountyVotes -> !candidateCountyVotes.isHasWon())
                        .mapToInt(CandidateCountyVotes::getTotalVotes).sum());
    }

    public void printVotesPerParty(CsvTable table ) {
        System.out.println("Votes per party: " +
                table.stream().map(CandidateCountyVotes::new).collect(Collectors.groupingBy(
                        CandidateCountyVotes::getParty,
                        TreeMap::new,
                        Collectors.summingInt(CandidateCountyVotes::getTotalVotes)
                )));
    }

}
