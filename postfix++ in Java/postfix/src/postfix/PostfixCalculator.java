//package postfix;
//
//import java.util.Stack;
//import java.util.HashMap;
//import java.util.Scanner;
//
//public class PostfixCalculator {
//    private Stack<Double> stack;
//    private HashMap<Character, Double> variables;
//
//    public PostfixCalculator() {
//        stack = new Stack<>();
//        variables = new HashMap<>();
//    }
//
//    // Function to evaluate a postfix expression
//    public double evaluate(String postfixExpr) throws Exception {
//        Scanner scanner = new Scanner(postfixExpr);
//        while (scanner.hasNext()) {
//            String token = scanner.next();
//            if (isOperator(token.charAt(0))) { // Check if token is an operator
//                if (stack.size() < 2) {
//                    throw new RuntimeException("Invalid postfix expression");
//                }
//                // Pop operands from stack
//                double operand2 = stack.pop();
//                double operand1 = stack.pop();
//                // Apply operator and push result back to stack
//                double result = applyOperator(token.charAt(0), operand1, operand2);
//                stack.push(result);
//            } else if (Character.isLetter(token.charAt(0))) { // Check if token is a variable (single uppercase letter)
//                if (token.length() != 1 || token.charAt(0) < 'A' || token.charAt(0) > 'Z') {
//                    throw new RuntimeException("Invalid variable name");
//                }
//                if (!variables.containsKey(token.charAt(0))) {
//                    throw new RuntimeException("Undefined variable");
//                }
//                // Push variable value onto stack
//                double operand = variables.get(token.charAt(0));
//                stack.push(operand);
//            } else if (token.equals("=")) { // Check if token is assignment operator
//                if (stack.size() < 2) {
//                    throw new RuntimeException("Invalid postfix expression");
//                }
//                // Pop value and variable name from stack
//                double value = stack.pop();
//                char variable = scanner.next().charAt(0); // Read the variable character
//                if (variable < 'A' || variable > 'Z') {
//                    throw new RuntimeException("Invalid variable name");
//                }
//                // Assign variable with value
//                setVariableValue(variable, value);
//            } else { // Token is a numeric operand
//                stack.push(Double.parseDouble(token)); // Push operand onto stack
//            }
//        }
//        scanner.close();
//
//        if (stack.size() != 1) { // Stack should contain exactly one value at the end
//            throw new RuntimeException("Invalid postfix expression");
//        }
//
//        return stack.pop(); // Return the final result
//    }
//
//    // Function to apply arithmetic operations
//    private double applyOperator(char op, double operand1, double operand2) {
//        switch (op) {
//            case '+':
//                return operand1 + operand2;
//            case '-':
//                return operand1 - operand2;
//            case '*':
//                return operand1 * operand2;
//            case '/':
//                return operand1 / operand2;
//            case '^':
//                return Math.pow(operand1, operand2);
//            default:
//                throw new RuntimeException("Invalid operator");
//        }
//    }
//
//    // Function to check if a character is an operator
//    private boolean isOperator(char c) {
//        return c == '+' || c == '-' || c == '*' || c == '/' || c == '^';
//    }
//
//    // Function to set variable value
//    private void setVariableValue(char variable, double value) {
//        variables.put(variable, value);
//    }
//
//    public static void main(String[] args) {
//        PostfixCalculator calculator = new PostfixCalculator();
//        Scanner scanner = new Scanner(System.in);
//
//        System.out.print("Enter a postfix expression: ");
//        String postfixExpression = scanner.nextLine();
//
//        try {
//            double result = calculator.evaluate(postfixExpression);
//            System.out.println("Result: " + result);
//        } catch (Exception e) {
//            System.err.println("Error: " + e.getMessage());
//        }
//
//        scanner.close();
//    }
//}

package postfix;

import java.util.Stack;
import java.util.HashMap;
import java.util.Scanner;

public class PostfixCalculator {
    private Stack<Double> stack;             // Stack to hold operands during evaluation
    private HashMap<Character, Double> variables; // HashMap to store variable values

    // Constructor initializes stack and variables HashMap
    public PostfixCalculator() {
        stack = new Stack<>();
        variables = new HashMap<>();
    }

    // Function to evaluate a postfix expression
    public double evaluate(String postfixExpr) throws Exception {
        Scanner scanner = new Scanner(postfixExpr); // Scanner to tokenize the postfix expression
        while (scanner.hasNext()) { // Loop through each token in the postfix expression
            String token = scanner.next(); // Get the next token
            if (isOperator(token.charAt(0))) { // Check if token is an operator
                if (stack.size() < 2) { // Ensure there are enough operands on the stack
                    throw new RuntimeException("Invalid postfix expression"); // Throw exception if invalid
                }
                double operand2 = stack.pop(); // Pop the top operand from stack
                double operand1 = stack.pop(); // Pop the second top operand from stack
                double result = applyOperator(token.charAt(0), operand1, operand2); // Apply operator
                stack.push(result); // Push result back onto stack
            } else if (Character.isLetter(token.charAt(0))) { // Check if token is a variable (single uppercase letter)
                if (token.length() != 1 || token.charAt(0) < 'A' || token.charAt(0) > 'Z') {
                    throw new RuntimeException("Invalid variable name"); // Throw exception for invalid variable name
                }
                if (!variables.containsKey(token.charAt(0))) {
                    throw new RuntimeException("Undefined variable"); // Throw exception if variable is undefined
                }
                double operand = variables.get(token.charAt(0)); // Get variable value
                stack.push(operand); // Push variable value onto stack
            } else if (token.equals("=")) { // Check if token is assignment operator
                if (stack.size() < 2) {
                    throw new RuntimeException("Invalid postfix expression");
                }
                double value = stack.pop(); // Pop value from stack
                char variable = scanner.next().charAt(0); // Read variable name
                if (variable < 'A' || variable > 'Z') {
                    throw new RuntimeException("Invalid variable name");
                }
                setVariableValue(variable, value); // Set variable value in HashMap
            } else { // Token is a numeric operand
                stack.push(Double.parseDouble(token)); // Push numeric operand onto stack
            }
        }
        scanner.close(); // Close scanner

        if (stack.size() != 1) { // Stack should contain exactly one value at the end
            throw new RuntimeException("Invalid postfix expression"); // Throw exception if invalid expression
        }

        return stack.pop(); // Return the final result from stack
    }

    // Function to apply arithmetic operations based on operator
    private double applyOperator(char op, double operand1, double operand2) {
        switch (op) {
            case '+':
                return operand1 + operand2;
            case '-':
                return operand1 - operand2;
            case '*':
                return operand1 * operand2;
            case '/':
                return operand1 / operand2;
            case '^':
                return Math.pow(operand1, operand2);
            default:
                throw new RuntimeException("Invalid operator"); // Throw exception for invalid operator
        }
    }

    // Function to check if a character is an operator
    private boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '^'; // Return true if character is an operator
    }

    // Function to set variable value in HashMap
    private void setVariableValue(char variable, double value) {
        variables.put(variable, value); // Store variable value in HashMap
    }

    // Main method for testing the postfix calculator
    public static void main(String[] args) {
        PostfixCalculator calculator = new PostfixCalculator(); // Create new instance of PostfixCalculator
        Scanner scanner = new Scanner(System.in); // Create Scanner object for user input

        System.out.print("Enter a postfix expression: "); // Prompt user for postfix expression
        String postfixExpression = scanner.nextLine(); // Read user input

        try {
            double result = calculator.evaluate(postfixExpression); // Evaluate postfix expression
            System.out.println("Result: " + result); // Print the result
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage()); // Print error message if exception occurs
        }

        scanner.close(); // Close scanner
    }
}


