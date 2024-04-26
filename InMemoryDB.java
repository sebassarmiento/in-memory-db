import java.util.HashMap;
import java.util.Map;

public class InMemoryDB {
    private Map<String, Integer> mainData = new HashMap<>();
    private Map<String, Integer> transactionData;
    private boolean inTransaction = false;

    public Integer get(String key) {
        if (inTransaction && transactionData.containsKey(key)) {
            return transactionData.get(key);
        }
        return mainData.getOrDefault(key, null);
    }

    public void put(String key, int val) throws Exception {
        if (!inTransaction) {
            throw new Exception("No transaction in progress. Please start a transaction before making changes.");
        }
        transactionData.put(key, val);
    }

    public void begin_transaction() throws Exception {
        if (inTransaction) {
            throw new Exception("Transaction already in progress. Commit or rollback the current transaction before starting a new one.");
        }
        inTransaction = true;
        transactionData = new HashMap<>(mainData); // Create a copy of the main data for transactional manipulation
    }

    public void commit() throws Exception {
        if (!inTransaction) {
            throw new Exception("No transaction in progress to commit.");
        }
        mainData = new HashMap<>(transactionData); // Commit changes by making the transaction data the main data
        transactionData = null;
        inTransaction = false;
    }

    public void rollback() throws Exception {
        if (!inTransaction) {
            throw new Exception("No transaction in progress to rollback.");
        }
        transactionData = null;
        inTransaction = false;
    }

    public static void main(String[] args) {
        InMemoryDB db = new InMemoryDB();

        try {
            System.out.println(db.get("A")); // null

            // This will throw an exception since no transaction is in progress
            db.put("A", 5);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            db.begin_transaction();
            db.put("A", 5); // set A to 5
            System.out.println(db.get("A")); // null because A is not committed

            db.put("A", 6); // update A to 6 within the transaction
            db.commit(); // commit the transaction
            System.out.println(db.get("A")); // should return 6

            db.commit(); // throws an error
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            db.rollback(); // throws an error
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println(db.get("B")); // null

        try {
            db.begin_transaction();
            db.put("B", 10);
            db.rollback(); // Rollback the transaction
            System.out.println(db.get("B")); // null because changes were rolled back
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
