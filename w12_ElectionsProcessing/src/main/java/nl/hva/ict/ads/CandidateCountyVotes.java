package nl.hva.ict.ads;

public class CandidateCountyVotes {
    private String state, county, candidate, party;
    private int totalVotes;
    private boolean hasWon;
    public CandidateCountyVotes(CsvRow r) {
        state = r.getField(0);
        county = r.getField(1);
        candidate = r.getField(2);
        party = r.getField(3);
        totalVotes = Integer.valueOf(r.getField(4));
        hasWon = Boolean.parseBoolean(r.getField(5));
    }

    public String getState() {
        return state;
    }

    public String getCounty() {
        return county;
    }

    public String getCandidate() {
        return candidate;
    }

    public String getParty() {
        return party;
    }

    public int  getTotalVotes() {
        return totalVotes;
    }

    public boolean isHasWon() {
        return hasWon;
    }

    @Override
    public String toString() {
        return "CandidateCountyVotes{" +
                "state='" + state + '\'' +
                ", county='" + county + '\'' +
                ", candidate='" + candidate + '\'' +
                ", party='" + party + '\'' +
                ", total_votes=" + totalVotes +
                ", won=" + hasWon +
                '}';
    }
}
