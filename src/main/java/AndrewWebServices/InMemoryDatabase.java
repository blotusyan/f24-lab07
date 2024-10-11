package AndrewWebServices;

import java.util.HashMap;
import java.util.Map;

public class InMemoryDatabase extends Database {
    private Map<String, Integer> userData;

    public InMemoryDatabase() {
        userData = new HashMap<>();
        // Initialize with some test data
        userData.put("Scotty", 17214);
        userData.put("Alice", 12345);
        // Add more test users as needed
    }

    @Override
    public int getPassword(String accountName) {
        Integer password = userData.get(accountName);
        if (password != null) {
            return password;
        } else {
            // Simulate behavior when the account doesn't exist
            return -1; // Or throw an exception if appropriate
        }
    }
}
