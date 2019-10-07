package com.telesens.classwork;

import com.google.gson.Gson;
import com.telesens.mobile.model.Subscriber;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class Gsonwriter {
    public static void main(String[] args) {
        List<Subscriber> subscribers = new ArrayList<>(  );

        Gson gson = new Gson();
        String json = gson.toJson(subscribers);

        System.out.println( subscribers );

        try (Writer writer = new FileWriter(
                new File("./subscribers.json"))) {
            writer.write(json);
        } catch (IOException exc) {
            exc.printStackTrace();
        }

    }
}


