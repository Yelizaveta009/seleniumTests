package com.telesens.classwork;

import com.google.gson.Gson;
import com.telesens.mobile.model.Subscriber;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Gsonreader {
        public static void main(String[] args) {
            try (BufferedReader reader = new BufferedReader(
                    new FileReader(new File("./subscribers.json")))) {
                StringBuilder json = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    json.append(line);
                }

                Gson gson = new Gson();
                Subscriber subscriber = gson.fromJson(json.toString(), Subscriber.class);
                System.out.println(subscriber);
            } catch (IOException exc) {
                exc.printStackTrace();
            }
        }

    }
