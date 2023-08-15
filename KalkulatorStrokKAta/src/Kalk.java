import java.util.Scanner;

class KalkulatorStrok {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите выражение: ");
        String input = scanner.nextLine();
        scanner.close();

        try {
            String result = evaluateExpression(input);
            if (result.length() > 40) {
                result = result.substring(0, 40) + "...";
            }
            System.out.println(result);
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    private static String evaluateExpression(String input) {
        String[] parts = input.split(" ");

        if (parts.length != 3) {
            throw new IllegalArgumentException("Недопустимый формат выражения");
        }

        String operand1 = parts[0];
        String operator = parts[1];
        String operand2 = parts[2];

        if (!isValidOperand(operand1) || !isValidOperand(operand2)) {
            throw new IllegalArgumentException("Недопустимые переменные");
        }

        if (!isValidOperator(operator)) {
            throw new IllegalArgumentException("Недопустимая операция");
        }

        if (operator.equals("+")) {
            return "\"" + operand1.substring(1, operand1.length() - 1) + operand2.substring(1, operand2.length() - 1) + "\"";
        } else if (operator.equals("-")) {
            return "\"" + operand1.substring(1, operand1.length() - 1).replace(operand2.substring(1, operand2.length() - 1), "") + "\"";
        } else if (operator.equals("*")) {
            int multiplier = Integer.parseInt(operand2);
            StringBuilder result = new StringBuilder();
            for (int i = 0; i < multiplier; i++) {
                result.append(operand1.substring(1, operand1.length() - 1));
            }
            return "\"" + result.toString() + "\"";
        } else if (operator.equals("/")) {
            int divisor = Integer.parseInt(operand2);
            int newLength = operand1.substring(1, operand1.length() - 1).length() / divisor;
            return "\"" + operand1.substring(1, operand1.length() - 1).substring(0, newLength) + "\"";
        } else {
            throw new IllegalArgumentException("Недопустимая операция");
        }
    }

    private static boolean isValidOperand(String operand) {
        return (operand.matches("\"[^\"]{1,10}\"") || operand.matches("[1-9]|10"));
    }

    private static boolean isValidOperator(String operator) {
        return (operator.equals("+") || operator.equals("-") || operator.equals("*") || operator.equals("/"));
    }
}