package triviagamee;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class QuestionReader {
    public static ArrayList<Question> readQuestionsFromFile(String filepath) {
        ArrayList<Question> questions = new ArrayList<>();
        try (Scanner scanner = new Scanner(Paths.get(filepath))) {
            while (scanner.hasNextLine()) {
                int counter = 1;
                String line = scanner.nextLine();
                String[] data = line.split(",");
                System.out.println(Arrays.toString(data));
                if (data.length == 6) {
                    String question = data[0];
                    String rightAnswer = data[1];
                    String choiceA = data[2];
                    String choiceB = data[3];
                    String choiceC = data[4];
                    String category = data[5];
                    Question newQuestion = new Question(counter, question, rightAnswer, choiceA, choiceB, choiceC,
                            category);
                    questions.add(newQuestion);
                    counter++;
                } else {
                    System.out.println("Invalid line in csv file");
                }
            }
            return questions;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return questions;
    }
}
