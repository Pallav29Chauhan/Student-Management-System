package src.sms.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

public class EnvLoader {

    private static final Map<String, String> envMap = new HashMap<>();

    // Static block runs once when the class is loaded
    static {
        try (BufferedReader br = new BufferedReader(new FileReader(".env"))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                // Skip empty lines or comments
                if (line.isEmpty() || line.startsWith("#"))
                    continue;

                // Split on first '='
                String[] parts = line.split("=", 2);
                if (parts.length == 2) {
                    envMap.put(parts[0].trim(), parts[1].trim());
                }
            }
        } catch (Exception e) {
            System.out.println(" Failed to load .env file. Make sure it exists in project root.");
        }
    }

    // Get environment variable
    public static String get(String key) {
        return envMap.get(key);
    }
}
