// File: module3.java
package mini_project;

import java.util.*;
import java.util.concurrent.*;

class Search<T> {
    public T find(List<T> list, T item) {
        for (T element : list) {
            if (element.equals(item)) return element;
        }
        return null;
    }
}

class RecommendationEngine implements Runnable {
    @Override
    public void run() {
        System.out.println("Generating product recommendations...");
        try { 
            Thread.sleep(2000); 
        } catch (InterruptedException e) { 
            System.out.println("Thread interrupted!"); 
        }
        System.out.println("Top Picks: Laptop, Smartphone, Headphones");
    }
}

public class module3 {
    public static void main(String[] args) {
        // Generic search
        List<String> products = Arrays.asList("Laptop", "Phone", "Tablet");
        Search<String> search = new Search<>();
        String result = search.find(products, "Phone");
        System.out.println("Search Result: " + result);

        // Multi-threading
        ExecutorService exec = Executors.newSingleThreadExecutor();
        exec.execute(new RecommendationEngine());
        exec.shutdown();
    }
}
