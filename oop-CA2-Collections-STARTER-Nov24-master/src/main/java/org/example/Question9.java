import java.util.Scanner;
import java.util.Stack;

/**
 * Name:Brandyvie Mbuyi Mayombo
 * Class Group: sd2B
 */
public class Question9 {

    /*
     * Reads in an equation from the user and evaluates it.
     */
    public static void main(String[] args) {
        String equation;
        Scanner in = new Scanner(System.in);
        System.out.println("Please enter an equation:");
        equation = in.nextLine().trim();

        try {
            double result = evaluateExpression(equation);
            System.out.println("Result: " + result);
        } catch (Exception e) {
            System.out.println("Invalid equation: " + e.getMessage());
        }
    }


    public static double evaluateExpression(String expression) {
        Stack<Double> values = new Stack<>();
        Stack<Character> operators = new Stack<>();

        for (int i = 0; i < expression.length(); i++) {
            char ch = expression.charAt(i);


            if (ch == ' ') continue;


            if (Character.isDigit(ch)) {
                StringBuilder number = new StringBuilder();
                while (i < expression.length() && (Character.isDigit(expression.charAt(i)) || expression.charAt(i) == '.')) {
                    number.append(expression.charAt(i++));
                }
                i--;
                values.push(Double.parseDouble(number.toString()));
            }

            else if (ch == '(') {
                operators.push(ch);
            }

            else if (ch == ')') {
                while (!operators.isEmpty() && operators.peek() != '(') {
                    values.push(applyOperation(operators.pop(), values.pop(), values.pop()));
                }
                if (!operators.isEmpty() && operators.peek() == '(') {
                    operators.pop();
                }
            }

            else if (isOperator(ch)) {

                while (!operators.isEmpty() && precedence(operators.peek()) >= precedence(ch)) {
                    values.push(applyOperation(operators.pop(), values.pop(), values.pop()));
                }
                operators.push(ch);
            }
        }


        while (!operators.isEmpty()) {
            values.push(applyOperation(operators.pop(), values.pop(), values.pop()));
        }


        return values.pop();
    }

    
    private static double applyOperation(char operator, double b, double a) {
        switch (operator) {
            case '+': return a + b;
            case '-': return a - b;
            case '*': return a * b;
            case '/':
                if (b == 0) throw new ArithmeticException("Division by zero");
                return a / b;
            default: throw new IllegalArgumentException("Unknown operator: " + operator);
        }
    }

    
    private static boolean isOperator(char ch) {
        return ch == '+' || ch == '-' || ch == '*' || ch == '/';
    }

    
    private static int precedence(char operator) {
        if (operator == '+' || operator == '-') return 1;
        if (operator == '*' || operator == '/') return 2;
        return 0;
    }
}


