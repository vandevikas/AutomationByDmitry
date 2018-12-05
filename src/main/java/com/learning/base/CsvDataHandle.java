package com.learning.base;

import com.opencsv.CSVReader;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.*;

public class CsvDataHandle {

    @DataProvider(name = "negativeLoginTestWithCSV")
    public static Iterator<Object[]> provideData(Method method) {
        List<Object[]> list = new ArrayList<>();

        String path = "src" + File.separator + "test" + File.separator
                + "java" + File.separator + "resources" + File.separator +
                "testData" + File.separator + method.getDeclaringClass().getSimpleName() +
                File.separator + method.getName() + ".csv";

        File file = new File(path);

        try {
            CSVReader reader = new CSVReader(new FileReader(file));
            String[] keys = reader.readNext();
            if (keys != null) {
                String[] dataParts;
                while ((dataParts = reader.readNext()) != null) {
                    Map<String, String> testData = new HashMap<>();
                    for (int i = 0; i < keys.length; i++) {
                        testData.put(keys[i], dataParts[i]);
                    }
                    list.add(new Object[]{testData});
                }
            }
            reader.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File" + path + "was not found.\n" + e.getStackTrace().toString());
        } catch (IOException e) {
            throw new RuntimeException("File" + path + "was not found.\n" + e.getStackTrace().toString());
        }
        return list.iterator();
    }
}