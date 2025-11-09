// File: module2.java
package mini_project;

import java.io.*;

class InvalidRatingException extends Exception {
    public InvalidRatingException(String msg) { super(msg); }
}

class Review {
    private String text;
    private int rating;
    public Review(String text, int rating) throws InvalidRatingException {
        if (rating < 1 || rating > 5) throw new InvalidRatingException("Rating must be 1-5");
        this.text = text; this.rating = rating;
    }
    public void saveReview() throws IOException {
        try (FileWriter fw = new FileWriter("reviews.txt", true)) {
            fw.write(rating + "★ - " + text + "\n");
        }
    }
}

public class module2 {
    public static void main(String[] args) {
        try {
            Review r1 = new Review("Excellent product!", 5);
            r1.saveReview();

            Review r2 = new Review("Not worth the price", 2);
            r2.saveReview();

            System.out.println("Reviews saved successfully!");
        } catch (InvalidRatingException | IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
