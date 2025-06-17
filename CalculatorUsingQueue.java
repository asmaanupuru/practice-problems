import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class CalculatorUsingQueue {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Queue<String> queue = new LinkedList<>();

        System.out.println("Enter a simple expression with spaces (e.g. 3 + 5 * 2):");
        String input = scanner.nextLine();

        // Split the input by spaces and add each token to the queue
        String[] tokens = input.split(" ");
        for (String token : tokens) {
            queue.add(token);
        }

        try {
            double result = calculate(queue);
            System.out.println("Result: " + result);
        } catch (Exception e) {
            System.out.println("Invalid expression.");
        }

        scanner.close();
    }

    // This method calculates the result using the queue
    private static double calculate(Queue<String> queue) {
        // Poll the first number
        double result = Double.parseDouble(queue.poll());

        // Process the rest of the queue (operator followed by number)
        while (!queue.isEmpty()) {
            String operator = queue.poll();
            double nextNumber = Double.parseDouble(queue.poll());

            switch (operator) {
                case "+":
                    result += nextNumber;
                    break;
                case "-":
                    result -= nextNumber;
                    break;
                case "*":
                    result *= nextNumber;
                    break;
                case "/":
                    if (nextNumber == 0) {
                        throw new ArithmeticException("Division by zero");
                    }
                    result /= nextNumber;
                    break;
                default:
                    throw new IllegalArgumentException("Unknown operator: " + operator);
            }
        }

        return result;
    }
}
