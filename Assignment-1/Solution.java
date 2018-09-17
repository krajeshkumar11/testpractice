import java.util.Scanner;


class Question {
    public String question;
    public String[] options;
    public String op1;
    public String op2;
    public String op3;
    public String op4;
    public String correctAns;
    public String maxMarks;
    public String negativeMarks;
    public Question() {

    }
    public Question(String question, String op1, String op2, String op3, String op4, String correctAns, String maxMarks, String negativeMarks) {
    	options = new String[4];
        this.question = question;
        this.options[0] = op1;
        this.options[1] = op2;
        this.options[2] = op3;
        this.options[3] = op4;
        this.correctAns = correctAns;
        this.maxMarks = maxMarks;
        this.negativeMarks = negativeMarks;
    }
}

class Quiz {
    public Question[] questions;
    public int questionscount;
    public int score;
    public String[] answers;
    public int answerscount;
    public Quiz() {
        questions = new Question[10];
        answers = new String[10];
        questionscount = 0;
        score = 0;
    }

    public int getQuesstionsCount() {
    	return questionscount;
    }

    public boolean add(Question addquestion){
        if (Integer.parseInt(addquestion.correctAns) <= 4){
            questions[questionscount++] = addquestion;
            return true;
        }
        return false;
    }

    public void checkAnswer(int index, String answer){
        boolean flag = false;
        // System.out.println(questions[index].question);
        if (questions[index].options[Integer.parseInt(questions[index].correctAns) - 1].equals(answer)) {
            // System.out.println("Correct Answer! - Marks Awarded: " + Integer.parseInt(questions[index].maxMarks));
            score += Integer.parseInt(questions[index].maxMarks);
        } else {
            // System.out.println(" Correct Answer! - Marks Awarded: " + Integer.parseInt(questions[position].negativeMarks));
            score += Integer.parseInt(questions[index].negativeMarks);
        }
        // System.out.println(score);
        answers[answerscount++] = answer;
    }

    public void printQuestions() {
    	int count = 1;
        for (int i = 0; i < questionscount; i++) {
            System.out.println(questions[i].question + "(" + (count++) + ")");
            System.out.println(questions[i].options[0] + "	" + questions[i].options[1] + "	" + questions[i].options[2] + "	" + questions[i].options[3]);
            System.out.println();
            if (count == 5) {
            	count = 1;
            }
        }
    }

    public int totalscore() {
    	for (int i = 0; i < questionscount; i++) {
    		System.out.println(questions[i].question);
    		// System.out.println("RAJESH: " + answers[i] + " " + questions[i].options[Integer.parseInt(questions[i].correctAns) - 1]);
    		if (questions[i].options[Integer.parseInt(questions[i].correctAns) - 1].equals(answers[i])) {
	            System.out.println(" Correct Answer! - Marks Awarded: " + Integer.parseInt(questions[i].maxMarks));
	            // score += Integer.parseInt(questions[i].maxMarks);
	        } else {
	            System.out.println(" Wrong Answer! - Penalty: " + Integer.parseInt(questions[i].negativeMarks));
	            // score += Integer.parseInt(questions[position].negativeMarks);
	        }
    	}
        return score;
    }
}
/**
 * Solution class for code-eval.
 */
public class Solution {
     /**
     * Constructs the object.
     */
    private Solution() {
        // leave this blank
    }
    /**
     * main function to execute test cases.
     *
     * @param      args  The arguments
     */
    public static void main(final String[] args) {
         // instantiate this Quiz
        Quiz q = new Quiz();
         // code to read the test cases input file
        Scanner s = new Scanner(System.in);
        // check if there is one more line to process
        while (s.hasNext()) {
            // read the line
            String line = s.nextLine();
             // split the line using space
            String[] tokens = line.split(" ");
              // based on the list operation invoke the corresponding method
            switch (tokens[0]) {
                case "LOAD_QUESTIONS":
                System.out.println("|----------------|");
                System.out.println("| Load Questions |");
                System.out.println("|----------------|");
                loadQuestions(s, q, Integer.parseInt(tokens[1]));
                break;
                case "START_QUIZ":
                System.out.println("|------------|");
                System.out.println("| Start Quiz |");
                System.out.println("|------------|");
                startQuiz(s, q, Integer.parseInt(tokens[1]));
                break;
                case "SCORE_REPORT":
                System.out.println("|--------------|");
                System.out.println("| Score Report |");
                System.out.println("|--------------|");
                displayScore(q);
                // System.out.println("HELLO");
                break;
                default:
                break;
            }
        }
    }
    /**
     * Loads questions.
     *
     * @param      s              The scanner object for user input
     * @param      quiz           The quiz object
     * @param      questionCount  The question count
     */
    public static void loadQuestions(final Scanner s, final Quiz quiz, final int questionCount) {
        // write your code here to read the questions from the console
        // tokenize the question line and create the question object
        // add the question objects to the quiz class
        // questions = new Quiz[questionCount];
        if (questionCount > 0){
        	int i = 0;
        	boolean flag = false;
            boolean flag1 = false;
            boolean flag2 = false;
	        while(i < questionCount){
	            String line = s.nextLine();
	            String[] tokens = line.split(":");
	            String[] optionstokens = tokens[1].split(",");
	            if(tokens.length < 5){
	            	flag = true;
	            	break;
	            }
                if(Integer.parseInt(tokens[2]) > optionstokens.length){
                    flag2 = true;
                    break;
                }
	            Question newQuiz = new Question(tokens[0], optionstokens[0], optionstokens[1], optionstokens[2], optionstokens[3], tokens[2], tokens[3], tokens[4]);
                if (!quiz.add(newQuiz)) {
                    flag1 = true;
                    System.out.println("Error! Correct answer choice number is out of range for " + newQuiz.question);
                    break;
                }
                i++;
            }
            if (flag) {
                System.out.println("Error! Malformed question");
            } else if(flag2){
                System.out.println("trick question  does not have enough answer choices");
            } else if(!flag1){
	        	System.out.println(questionCount + " are added to the quiz");
	        }
        } else {
        	System.out.println("Quiz does not have questions");
        }
    }

    /**
     * Starts a quiz.
     *
     * @param      s            The scanner object for user input
     * @param      quiz         The quiz object
     * @param      answerCount  The answer count
     */
    public static void startQuiz(final Scanner s, final Quiz quiz, final int answerCount) {
        // write your code here to display the quiz questions
        // read the user responses from the console
        // store the user respones in the quiz object
    	if(quiz.getQuesstionsCount() > 0){
    		quiz.printQuestions();
	        int i = 0;
	        while(i < answerCount){
	            String line = s.nextLine();
	            String[] tokens = line.split(" ");
	            quiz.checkAnswer(i, line);
	            i++;
	        }
    	}
    }

    /**
     * Displays the score report
     *
     * @param      quiz     The quiz object
     */
    public static void displayScore(final Quiz quiz) {
        // write your code here to display the score report
        if(quiz.getQuesstionsCount() > 0){
        	System.out.println("Total Score: " + quiz.totalscore());
    	}
    }
}
