
package LOGIC;

/**
 * 
 */
public class DatasalQuestion {
    private String question;
    private String answer;
    private String[] choices;
    
    public DatasalQuestion(String newQuestion, String newAnswer, String choiceString) {
        question = newQuestion;
        answer = newAnswer;
        choices = choiceString.split(",");
    }
    
    public String getQuestion() {
        return question;
    }
    
    public String getAnswer() {
        return answer;
    }
    
    public String[] getChoices() {
        return choices;
    }
}
