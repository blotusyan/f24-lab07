package AndrewWebServices;

public class InMemoryRecSys extends RecSys {
    @Override
    public String getRecommendation(String accountName) {
        // Return a predefined movie recommendation
        if ("Scotty".equals(accountName)) {
            return "Animal House";
        } else if ("Alice".equals(accountName)) {
            return "The Matrix";
        } else {
            return "Unknown Movie";
        }
    }
}
