import java.util.Scanner;
import java.util.Stack;
import java.io.File;

public class CalculatorScanner {
    public Scanner scanner;

    public CalculatorScanner(String str) {
        scanner = new Scanner(str).useDelimiter("");
    }

    public CalculatorScanner(File file) throws Exception {
        try {
            scanner = new Scanner(file).useDelimiter("");
        } catch (Exception e) {
            throw new Exception("File does not exist");
        }
    }
    
    public boolean hasNextToken() {
        return scanner.hasNext();
    }

    public String readToken() throws Exception {
        // any digits, zero or one '.', followed by any digits
        String digits = "[0-9]*\\.?[0-9]*";
        String operators = "(\\+)?(\\-)?(\\*)?(\\^)?(\\()?(\\))?";
        StringBuilder sb = new StringBuilder();

        // if there isn't another character, throw exception
        if (!scanner.hasNext()) {
            throw new Exception("No more tokens");
        } else {
            // ignore any whitespace
            ignoreWhitespace();

            // if it's an operator, just return that operator else read number
            // or if it's neither of those things, throw an exception
            if (scanner.hasNext() && scanner.hasNext(operators)) {
                ignoreWhitespace();
                return scanner.next();
            } else if (scanner.hasNext(digits) && scanner.hasNext()) {
                while (scanner.hasNext() && scanner.hasNext(digits)) {
                    sb.append(scanner.next(digits));
                    ignoreWhitespace();
                }

                return sb.toString();
            } else {
                throw new Exception("Invalid token");
            }
        }
    }

    public void ignoreWhitespace() throws Exception {
        while (scanner.hasNext() && scanner.hasNext(" ")) {
            scanner.skip(" ");
        }
    }
}
