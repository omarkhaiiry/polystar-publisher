package com.polystar.publisher.model.service;

import com.polystar.publisher.exception.CustomException;
import com.polystar.publisher.template.ClientTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@Service
public class PublisherService {
    private ClientTemplate clientTemplate;


    @Autowired
    public PublisherService(ClientTemplate clientTemplate) {
        this.clientTemplate = clientTemplate;

    }

    public Map<String, Integer> topRepeatedFiveWords(String filePath) throws CustomException {

        try (BufferedReader br = Files.newBufferedReader(Paths.get(filePath), StandardCharsets.ISO_8859_1)) {
            ExecutorService executor = Executors.newFixedThreadPool(5);
            Collection<Callable<Void>> tasks = new ArrayList<>();
            for (String line = null; (line = br.readLine()) != null; ) {
                String finalLine = line;
                Callable<Void> task = () -> {
                    clientTemplate.getTopWordInStringConcat(finalLine);
                    return null;
                };
                tasks.add(task);
            }
            executor.invokeAll(tasks);

        } catch (IOException | InterruptedException e) {
            throw new CustomException(e);
        }


        return clientTemplate.getCurrentTopRepeatedWordsConcat();
    }

    public String readFiles(String filePath) throws CustomException {

        try (BufferedReader br = Files.newBufferedReader(Paths.get(filePath), StandardCharsets.ISO_8859_1)) {
            ExecutorService executor = Executors.newFixedThreadPool(5);
            Collection<Callable<Void>> tasks = new ArrayList<>();
            for (String line = null; (line = br.readLine()) != null; ) {
                String finalLine = line;
                Callable<Void> task = () -> {
                    clientTemplate.getTopWordInStringConcat(finalLine);
                    return null;
                };
                tasks.add(task);
            }
            executor.invokeAll(tasks);

        } catch (IOException | InterruptedException e) {
            throw new CustomException(e);
        }

        return "Read Successfully";
    }


    public void getOneTimeTopWordInStringConcat(String filePath) throws CustomException {

        try (BufferedReader br = Files.newBufferedReader(Paths.get(filePath), StandardCharsets.ISO_8859_1)) {
            ExecutorService executor = Executors.newFixedThreadPool(5);
            Collection<Callable<Void>> tasks = new ArrayList<>();
            for (String line = null; (line = br.readLine()) != null; ) {
                String finalLine = line;
                Callable<Void> task = () -> {
                    clientTemplate.getOneTimeTopWordInStringConcat(finalLine);
                    return null;
                };
                tasks.add(task);
            }
            executor.invokeAll(tasks);

        } catch (IOException | InterruptedException e) {
            throw new CustomException(e);
        }
    }
    public void getOneTimeTopWordInStringConcat(List<String> filesPaths) throws CustomException {

        ExecutorService executor = Executors.newFixedThreadPool(filesPaths.size());
        Collection<Callable<Void>> tasks = new ArrayList<>();
        for (String filePath : filesPaths) {
            Callable<Void> task = () -> {
                getOneTimeTopWordInStringConcat(filePath);
                return null;
            };
            tasks.add(task);
        }
        try {
            executor.invokeAll(tasks);
        } catch (InterruptedException e) {
            throw new CustomException(e);
        }

    }
}
