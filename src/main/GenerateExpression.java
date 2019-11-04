package main;

import java.util.Scanner;

/**
 * This call generates and runs the expressions as they come in
 */
class GenerateExpression {
	private Stack<String> myStack;
    private boolean expressionGenerated = false;
    private boolean shouldCont = true;

    /**
     * Loads in the provided input and determines how to run the program
     * 1: ops.txt
     * 2: scanner input
     * @param data
     */
	GenerateExpression(InOut data){
        myStack = new Stack<>();
        String ops = data.getOps();
        String[] splitOps = ops.split("\\s+");

	    Scanner s = new Scanner(System.in);
	    System.out.println("Options{\n\t[1]: How do want to run the program? from a file \"ops.txt\"\n\t[2]: Using the scanner\n}");
	    System.out.print("Please enter a integer: ");
	    int options = s.nextInt();

        if(options == 1) {
            //Input file was selected so just run all of the operations
            processExpressionStack(splitOps);
        }else{
            System.out.println("Now Enter Your Expression");
            while (shouldCont == true){
                //Enter another expression prompt
                System.out.print("> ");
                String expression = s.next();
                processExpressionStack(new String[]{expression});
            }
        }

		if(!expressionGenerated)
			errno.pError(0);
	}

    /**
     * UNIT test which allows input to be inserted inline or from a input file
     *
     * @param operations A string buffer that is the operation is going to be inserted or excuted in order;
     */
	private void processExpressionStack(String[] operations) {
        label:
        for (String operation : operations) {
            if (!operation.isEmpty()) {

                switch (operation) {
                    case "!":
                        shouldCont = false;
                        break label;
                    case "?":
                        expressionGenerated = true;
                        //Print the URLS corresponding to the expression at the top of the query stack
                        if (myStack.size() != 0) {
                            String peeked = myStack.peek();
                            WMUsearchEngine.runExpression(peeked);
                        }
                        break;
                    case "&&":
                    case "||":
                        //pop the two previous values in the stack and evaluate the expression
                        StringBuilder builder = new StringBuilder();
                        if (myStack.size() < 2)
                            errno.pError(2);

                        String str2 = myStack.pop();
                        String str1 = myStack.pop();
                        builder.append(str1);
                        builder.append(" ");
                        builder.append(str2);
                        builder.append(" ");
                        builder.append(operation);
                        myStack.push(builder.toString());
                        break;
                    default:
                        myStack.push(operation);
                        break;
                }
            }
        }
	}
}
