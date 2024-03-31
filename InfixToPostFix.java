import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class InfixToPostFix {
    public static boolean isOpening(String op) {
        return List.of("(", "[", "{").contains(op);
    }

    public static boolean isClosing(String op) {
        return List.of(")", "]", "}").contains(op);
    }

    public static boolean isOperator(String operator) {
        return List.of("+", "-", "*", "/", "%", "^").contains(operator);
    }

    public static boolean hasLowerPrecedence(String op1, String op2) {
        return precedence(op1) <= precedence(op2);
    }

    public static int precedence(String operator) {
        return switch (operator) {
            case "+", "-" -> 1;
            case "/", "*" -> 2;
            case "%" -> 3;
            default -> 4;
        };
    }

    public static String infixToPostfix(String infix) {
        Stack<String> operators = new Stack<>();
        List<String> postfix = new ArrayList<>();
        for (String current : infix.split(" ")) {
            if (isOperator(current)) {
                while (!operators.empty()
                        && hasLowerPrecedence(current, operators.peek())) {
                    postfix.add(operators.pop());
                }
                operators.push(current);
            } else if (isOpening(current)) {
                operators.push(current);
            } else if (isClosing(current)) {
                while (!operators.empty() && !isOpening(operators.peek())) {
                    postfix.add(operators.pop());
                }
                operators.pop();
            } else {
                postfix.add(current);
            }
        }
        while (!operators.empty()) {
            postfix.add(operators.pop());
        }
        return String.join(" ", postfix);
    }

    public static void main(String[] args) {
        System.out.println(infixToPostfix("( 10 * 20 ) + 1000 * 25 / 30 * 100"));
    }
}