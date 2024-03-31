import java.util.*;

public class InfixPostfix {

    public static boolean isOpening(String operator) {
        return List.of("(", "[", "{").contains(operator);
    }

    public static boolean isClosing(String operator) {
        return List.of(")", "]", "}").contains(operator);
    }

    public static boolean isOperator(String operator) {
        return List.of("+", "-", "*", "/", "%", "^").contains(operator);
    }

    public static int kedudukan(String operator) {
        return switch (operator) {
            case "+", "-" -> 1;
            case "/", "*" -> 2;
            case "%" -> 3;
            default -> 4;
        };
    }

    public static boolean hasKedudukanRendah(String op1, String op2) {
        return kedudukan(op1) <= kedudukan(op2);
    }

    public static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    public static int hasilPostFix(String postfix) {
        Stack<Integer> stack = new Stack<>();
        for (String component : postfix.split(" ")) {
            if (isNumeric(component)) {
                stack.push(Integer.parseInt(component));
            } else {
                int nbr1 = stack.pop();
                int nbr2 = stack.pop();
                switch (component) {
                    case "+" -> stack.push(nbr1 + nbr2);
                    case "-" -> stack.push(nbr1 - nbr2);
                    case "/" -> stack.push(nbr1 / nbr2);
                    case "*" -> stack.push(nbr1 * nbr2);
                    case "%" -> stack.push(nbr1 % nbr2);
                    // case "^" -> stack.push(nbr1 ^ nbr2);
                }
            }
        }
        return stack.pop();
    }

    public static String infixKePostfix(String infix) {
        Stack<String> operators = new Stack<>();
        List<String> postfix = new ArrayList<>();
        for (String current : infix.split(" ")) {
            if (isOperator(current)) {
                while (!operators.empty() && hasKedudukanRendah(current, operators.peek())
                        && !isOpening(operators.peek())) {
                    postfix.add(operators.pop());
                }
                operators.push(current);
            } else if (isOpening(current)) {
                while (!operators.isEmpty() && !isClosing(operators.peek())) {
                    postfix.add(operators.pop());
                }

            } else if (isClosing(current)) {
                while (!operators.isEmpty() && !isOpening(operators.peek())) {
                    postfix.add(operators.pop());
                }
            } else {
                postfix.add(current);
            }
        }
        while (!operators.isEmpty()) {
            postfix.add(operators.pop());
        }

        return String.join(" ", postfix);
    }

    public static void main(String[] args) {
        System.out.println(infixKePostfix("( 10 * 20 ) + 1000 * 25 / 30 * 100"));
        System.out.println(hasilPostFix("10 20 * 1000 25 * 30 / 100 * +"));
    }
}
