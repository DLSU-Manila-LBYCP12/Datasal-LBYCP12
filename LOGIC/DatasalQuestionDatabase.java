
package LOGIC;
import java.util.*;
import java.io.*;

public class DatasalQuestionDatabase {
    private LinkedList<String> questions;
    private LinkedList<String> choices;
    private LinkedList<String> answers;
    
    public DatasalQuestionDatabase() {
        questions = new LinkedList();
        choices = new LinkedList();
        answers = new LinkedList();
        
        try {
            FileReader fQuestions = new FileReader("datasal-questions.txt");
            FileReader fAnswers = new FileReader("datasal-answers.txt");
            FileReader fChoices = new FileReader("datasal-choices.txt");
            
            BufferedReader bQuestions = new BufferedReader(fQuestions);
            BufferedReader bAnswers = new BufferedReader(fAnswers);
            BufferedReader bChoices = new BufferedReader(fChoices);
            
            try {
                String line = bQuestions.readLine();
                while(line!=null) {
                    questions.add(line);
                    line = bQuestions.readLine();
                }
                
                line = bChoices.readLine();
                while(line!=null) {
                    choices.add(line);
                    line = bChoices.readLine();
                }
                
                line = bAnswers.readLine();
                while(line!=null) {
                    answers.add(line);
                    line = bAnswers.readLine();
                }
            }
            catch(IOException e) {
                System.out.println("Input Output Exception");
            }
        }
        catch(FileNotFoundException e) {
            System.out.println("File not found Exception");
        }
    }
    
    public DatasalQuestion getQuestionData(int qNumber) {
        return new DatasalQuestion(questions.get(qNumber-1), answers.get(qNumber-1), choices.get(qNumber-1));
    }
    
    public int getNumQuestions() {
        return questions.size();
    }
}
