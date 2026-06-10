package com.moneysaver.repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.moneysaver.model.Transaction;
import com.moneysaver.model.TransactionStore;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class TransactionRepository {
    private final String fileName;
    private final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .registerTypeAdapter(
                    java.time.LocalDate.class,
                    (com.google.gson.JsonSerializer<java.time.LocalDate>) (src, typeOfSrc, context) ->
                            new com.google.gson.JsonPrimitive(src.toString())
            )
            .registerTypeAdapter(
                    java.time.LocalDate.class,
                    (com.google.gson.JsonDeserializer<java.time.LocalDate>) (json, typeOfT, context) ->
                            java.time.LocalDate.parse(json.getAsString())
            )
            .create();

    public TransactionRepository(String fileName) {
        this.fileName = fileName;
    }

    public List<Transaction> loadTransactions() {
        try {
            File file = new File(fileName);

            // If file doesn't exist → create empty JSON file
            if (!file.exists()) {
                TransactionStore emptyStore = new TransactionStore();
                saveStore(emptyStore);
                return new ArrayList<>();
            }

            FileReader reader = new FileReader(file);

            TransactionStore store = gson.fromJson(reader, TransactionStore.class);

            reader.close();

            if (store == null || store.getTransactions() == null) {
                return new ArrayList<>();
            }

            return store.getTransactions();

        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public void saveTransactions(List<Transaction> transactions) {
        TransactionStore store = new TransactionStore(transactions);
        saveStore(store);
    }

    // Encapsulation (hidden helper method)
    private void saveStore(TransactionStore store) {
        try {
            FileWriter writer = new FileWriter(fileName);

            gson.toJson(store, writer);

            writer.flush();
            writer.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}