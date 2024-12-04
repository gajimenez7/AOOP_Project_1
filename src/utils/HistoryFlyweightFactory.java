package src.utils;

import java.util.HashMap;
import java.util.Map;

public class HistoryFlyweightFactory {
    private static final Map<String, String> transactionTypes = new HashMap<>();

    static {
        transactionTypes.put("deposit", "Deposit Transaction");
        transactionTypes.put("withdraw", "Withdraw Transaction");
        transactionTypes.put("transfer", "Transfer Transaction");
        transactionTypes.put("payment", "Payment Transaction");
        transactionTypes.put("invalid", "Invalid Transaction");
    }

    public static String getTransactionType(String key) {
        return transactionTypes.getOrDefault(key, "Unknown Transaction");
    }
}
