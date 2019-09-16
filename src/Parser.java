import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.regex.*;
import javax.swing.JFileChooser;

/**
 * The parser and interpreter. The top level parse function, a main method for
 * testing, and several utility methods are provided. You need to implement
 * parseProgram and all the rest of the parser.
 */
public class Parser {

	/**
	 * Top level parse method, called by the World
	 */
	static RobotProgramNode parseFile(File code) {
		Scanner scan = null;
		try {
			scan = new Scanner(code);

			// the only time tokens can be next to each other is
			// when one of them is one of (){},;
			scan.useDelimiter("\\s+|(?=[{}(),;])|(?<=[{}(),;])");

			RobotProgramNode n = parseProgram(scan); // You need to implement this!!!

			scan.close();
			return n;
		} catch (FileNotFoundException e) {
			System.out.println("Robot program source file not found");
		} catch (ParserFailureException e) {
			System.out.println("Parser error:");
			System.out.println(e.getMessage());
			scan.close();
		}
		return null;
	}

	/** For testing the parser without requiring the world */

	public static void main(String[] args) {
		if (args.length > 0) {
			for (String arg : args) {
				File f = new File(arg);
				if (f.exists()) {
					System.out.println("Parsing '" + f + "'");
					RobotProgramNode prog = parseFile(f);
					System.out.println("Parsing completed ");
					if (prog != null) {
						System.out.println("================\nProgram:");
						System.out.println(prog);
					}
					System.out.println("=================");
				} else {
					System.out.println("Can't find file '" + f + "'");
				}
			}
		} else {
			while (true) {
				JFileChooser chooser = new JFileChooser(".");// System.getProperty("user.dir"));
				int res = chooser.showOpenDialog(null);
				if (res != JFileChooser.APPROVE_OPTION) {
					break;
				}
				RobotProgramNode prog = parseFile(chooser.getSelectedFile());
				System.out.println("Parsing completed");
				if (prog != null) {
					System.out.println("Program: \n" + prog);
				}
				System.out.println("=================");
			}
		}
		System.out.println("Done");
	}

	// Useful Patterns

	static Pattern NUMPAT = Pattern.compile("-?\\d+"); // ("-?(0|[1-9][0-9]*)");
	static Pattern OPENPAREN = Pattern.compile("\\(");
	static Pattern CLOSEPAREN = Pattern.compile("\\)");
	static Pattern OPENBRACE = Pattern.compile("\\{");
	static Pattern CLOSEBRACE = Pattern.compile("\\}");
	static Pattern SENSORS = Pattern.compile("fuelLeft|oppLR|barrelLR|oppFB|numBarrels|barrelFB|wallDist");

	/**
	 * PROG ::= STMT+
	 */
	static RobotProgramNode parseProgram(Scanner s) {
		// THE PARSER GOES HERE
		if(!s.hasNext()){
			fail("No files to parse", s);
		}
		ProgNode node = new ProgNode();
		while(s.hasNext()){
			node.addNode(parseStatement(s));
		}
		return node;

	}

	//Parsing through the statement non terminal
	private static RobotProgramNode parseStatement(Scanner s ){
		ArrayList<RobotProgramNode> children = new ArrayList<RobotProgramNode>();
		//if(!s.hasNext("move")) { fail("no 'move'", s); }
		//children.add(new STMTNode(s.next()));
		//if(!s.hasNext("turnL")) {   }

		if(s.hasNext("move") ){
			STMTNode node = new STMTNode(parseACT(s));
			if(s.hasNext(";")) {
				s.next();
				return node;
			}
			fail("';' not found. ", s);
		}
		if(s.hasNext("turnL") ){
			STMTNode node = new STMTNode(parseACT(s));
			if(s.hasNext(";")) {
				s.next();
				return node;
			}
			fail("';' not found. ", s);
		}
		if(s.hasNext("turnR") ){
			STMTNode node = new STMTNode(parseACT(s));
			if(s.hasNext(";")) {
				s.next();
				return node;
			}
			fail("';' not found. ", s);
		}
		if(s.hasNext("wait") ){
			STMTNode node = new STMTNode(parseACT(s));
			if(s.hasNext(";")) {
				s.next();
				return node;
			}
			fail("';' not found. ", s);
		}
		if(s.hasNext("takeFuel") ){
			STMTNode node = new STMTNode(parseACT(s));
			if(s.hasNext(";")) {
				s.next();
				return node;
			}
			fail("';' not found. ", s);
		}
		if(s.hasNext("turnAround") ){
			STMTNode node = new STMTNode(parseACT(s));
			if(s.hasNext(";")) {
				s.next();
				return node;
			}
			fail("';' not found. ", s);
		}
		if(s.hasNext("shieldOn") ){
			STMTNode node = new STMTNode(parseACT(s));
			if(s.hasNext(";")) {
				s.next();
				return node;
			}
			fail("';' not found. ", s);
		}
		if(s.hasNext("shieldOff") ){
			STMTNode node = new STMTNode(parseACT(s));
			if(s.hasNext(";")) {
				s.next();
				return node;
			}
			fail("';' not found. ", s);
		}

		if(s.hasNext ("loop")){
			STMTNode node = new STMTNode(parseLOOP(s));
			s.next();
			return parseLOOP(s);
		}

		if(s.hasNext("if")){
			STMTNode node = new STMTNode(parseIf(s));
			return node;
		}

		if(s.hasNext("while")){
			STMTNode node = new STMTNode(parseWhile(s));
			return node;
		}
//		if(s.hasNext("-?[0-9]+")){
//			STMTNode node = new STMTNode(parseNum(s));
//			return node;
//		}



		fail("Invalid STMT", s);
		return null;
	}

	private static RobotProgramNode parseACT(Scanner s){

		//scans through the text file and checks if the scanner has passed through a specific action
		//if it has then return them as actionNodes.
		if(s.hasNext("move")){
			s.next();
			if(s.hasNext(OPENPAREN)){
				s.next();
				RobotProgramNode move = new MoveNode(parseExpr(s));
				if(s.hasNext(CLOSEPAREN)){
					s.next();
					return move;
				}
			}
			return new MoveNode();
		}
		if(s.hasNext("turnR")){
			s.next();
			return new TurnRNode();
		}
		if(s.hasNext("turnL")){
			s.next();
			return new TurnLNode();
		}
		if(s.hasNext("turnAround")){
			s.next();
			return new TurnAroundNode();
		}
		if(s.hasNext("takeFuel")){
			s.next();
			return new TakeFuelNode();
		}
		if(s.hasNext("wait")){
			s.next(); //goes to the next string
			if(s.hasNext(OPENPAREN)){ //checks if the next string is a open bracket
				s.next(); //goes to the next string which is the expression
				RobotProgramNode node = new WaitNode(parseExpr(s));
				if(s.hasNext(CLOSEPAREN)){
					s.next();
					return node;
				}
				else{
					fail("')' not found after the expression terminal", s);
				}
			}fail("'(' not found before the expression terminal", s);
			return new WaitNode();
		}
		if(s.hasNext("shieldOn")){
			s.next();
			return new ShieldOnNode();
		}
		if(s.hasNext("shieldOff")){
			s.next();
			return new ShieldOffNode();
		}
		fail("Error", s);
		return null;
	}

	private static RobotProgramNode parseLOOP(Scanner s){
		//scans through the text file and checks if the scanner has passed through a specific action
		//if it has then it returns a loopNode.
		if(s.hasNext("loop")){
			s.next();
			return new LoopNode(parseBlock(s));
		}
		fail("loop not found", s);
		return null;
	}

	private static RobotProgramNode parseBlock(Scanner s){
		if(!s.hasNext(OPENBRACE)){ fail("Open brace not found", s); }
		else { s.next(); }//passing through the open brace
		BlockNode node = new BlockNode();
		while(!s.hasNext(CLOSEBRACE)){ node.addNode(parseStatement(s)); }
		if(!s.hasNext(CLOSEBRACE)){ fail("Close brace doesn't exist",s); }
		s.next();
		return node;
	}

	//Stage 1
	private static RobotProgramNode parseIf(Scanner s){
		if(s.hasNext("if")){
			s.next(); //scanner scans past if
			if(s.hasNext(OPENPAREN)){
				s.next();
				RobotProgramNode ifNode = new IfNode(parseCond(s)); //if node and passes this to the cond node
				if(s.hasNext(CLOSEPAREN)){
					s.next();
					IfNode castingIfNode = (IfNode) ifNode; //cast ifNode to an IfNode object, casting is turning an object of one specific type and turning it into another object type.
					castingIfNode.block((BlockNode)parseBlock(s)); //get the casted if node and parse the block outside the statement
					while(s.hasNext("elif")){ //while it is if add the condition into the if list so it can be displayed
						castingIfNode.addElseBlock((IfNode)parseIf(s)); //recursively going down and considering the elseif case also
					}
					if(s.hasNext("else")){
						s.next();
						castingIfNode.block((BlockNode)parseBlock(s));
					}
					return ifNode;
				}
				fail("')' not found after conditional non terminal", s);
			}
			fail("'(' not found before conditional non terminal", s);
		}

		fail("'if' statement doesn't exist", s);
		return null;
	}

	private static RobotProgramNode parseWhile(Scanner s){
		if(s.hasNext("while")){
			s.next();
			if(s.hasNext(OPENPAREN)) {
				s.next();
				RobotProgramNode whileNode = new WhileNode(parseCond(s)); //create a new whileNode and parse through cond
				if (s.hasNext(CLOSEPAREN)) {
					s.next();
					WhileNode castingWhileNode = (WhileNode) whileNode; //cast whileNode to a WhileNode object, casting is turning an object of one specific type and turning it into another object type.
					castingWhileNode.block((BlockNode) parseBlock(s));
					return whileNode;
				} else {
					fail("')' not found after conditional non terminal ", s);
				}
			} else {
				fail("'(' not found before conditional non terminal", s);
				}
				fail("'while' doesn't exist", s);
			}
			return null;
	}

	private static ConditionalNode parseCond(Scanner s){
		//relationship nodes gt lt eq

		if(s.hasNext("lt")){
			s.next();

			if(s.hasNext(OPENPAREN)){
				s.next();
				//NumNode expr1 = parseNum(s);
				//SensorNode sen = parseSen(s);
				ExpressionNode expr1 = parseExpr(s); //sensorNode

				if(s.hasNext(",")){
					s.next();
					//NumNode num = parseNum(s);
					//NumNode expr2 = parseNum(s);
					ExpressionNode expr2 = parseExpr(s); //numNode
					if(s.hasNext(CLOSEPAREN)){
						s.next();
						ConditionalNode node = new LessThanNode(expr1,expr2);
						return node;
					}  else {fail("')' not found ",s );}
				} else {fail("',' not found", s);}
			} else {
				fail("'(' not found", s);
			}
		}

		if(s.hasNext("eq")){
			s.next();
			if(s.hasNext(OPENPAREN)){
				s.next();
				//NumNode expr1 = parseNum(s);
				ExpressionNode expr1 = parseExpr(s);
				if(s.hasNext(",")){
					s.next();
					//NumNode expr2 = parseNum(s);
					ExpressionNode expr2 = parseExpr(s);
					if(s.hasNext(CLOSEPAREN)){
						s.next();
						ConditionalNode node = new EqualToNode(expr1,expr2);
						return node;
					}  else {fail("')' not found ",s );}
				} else {fail("',' not found", s);}
			} else {fail("'(' not found", s);}
		}

		if(s.hasNext("gt")){
			s.next();
			if(s.hasNext(OPENPAREN)){
				s.next();
				//NumNode expr1 = parseNum(s);
				ExpressionNode expr1 = parseExpr(s);
				if(s.hasNext(",")){
					s.next();
					//NumNode expr2 = parseNum(s);
					ExpressionNode expr2 = parseExpr(s);
					if(s.hasNext(CLOSEPAREN)){
						s.next();
						ConditionalNode node = new GreaterThanNode(expr1,expr2);
						return node;
					}  else {fail("')' not found ",s );}
				} else {fail("',' not found", s);}
			} else {fail("'(' not found", s);}
		}

		if(s.hasNext("not")){
			s.next();
			if(s.hasNext(OPENPAREN)){
				s.next();
				ConditionalNode node1 = new CondNode(parseCond(s));
				if(s.hasNext(CLOSEPAREN)){
					s.next();
					return new NotNode(node1);
				}else {fail("')' doesn't exist after the condition",s);}
			} else{fail("'(' doesn't exist before the condition",s);}
		}

		if(s.hasNext("or")){
			s.next();
			if(s.hasNext(OPENPAREN)){
				s.next();
				ConditionalNode node1 = new CondNode(parseCond(s));
				if(s.hasNext(",")){
					s.next();
					ConditionalNode node2 = new CondNode(parseCond(s));
					if(s.hasNext(CLOSEPAREN)){
						s.next();
						return new OrNode(node1,node2);
					}else {fail("')' doesn't exist after the condition",s);}
				}else {fail("',' doesn't exist in between both the conditions",s);}
			} else{fail("'(' doesn't exist before conditions",s);}
		}

		if(s.hasNext("and")){
			s.next();
			if(s.hasNext(OPENPAREN)){
				s.next();
				ConditionalNode node1 = new CondNode(parseCond(s));
				if(s.hasNext(",")){
					s.next();
					ConditionalNode node2 = new CondNode(parseCond(s));
					if(s.hasNext(CLOSEPAREN)){
						s.next();
						return new AndNode(node1,node2);
					}else {fail("')' doesn't exist after the condition",s);}
				}else {fail("',' doesn't exist in between both the conditions",s);}
			} else{fail("'(' doesn't exist before conditions",s);}
		}


		//general worse case
		fail("Invalid cond",s);
		return null;
	}

	private static SensorNode parseSen(Scanner s){
		if(s.hasNext("fuelLeft")){
			s.next();
			SensorNode node = new FuelLeftNode();
			return node;
		}

		if(s.hasNext("oppLR")){
			s.next();
			SensorNode node = new OppLRNode();
			return node;
		}

		if(s.hasNext("oppFB")){
			s.next();
			SensorNode node = new OppFBNode();
			return node;
		}

		if(s.hasNext("numBarrels")){
			s.next();
			SensorNode node = new NumBarrelsNode();
			return node;

		}

		if(s.hasNext("barrelLR")){
			s.next();
			if(s.hasNext(OPENPAREN)){
				s.next();
				ExpressionNode expr = parseExpr(s);
				if(s.hasNext(CLOSEPAREN)) {
					s.next();
					return new BarrelLRNode(expr);
				}
				fail("llr fail", s);
			}
			SensorNode node = new BarrelLRNode();
			return node;
		}

		if(s.hasNext("barrelFB")){
			s.next();
			if(s.hasNext(OPENPAREN)){
				s.next();
				ExpressionNode expr = parseExpr(s);
				if(s.hasNext(CLOSEPAREN)) {
					s.next();
					return new BarrelLRNode(expr);
				}
				fail("FB fail", s);
			}
			SensorNode node = new BarrelFBNode();
			return node;
		}

		if(s.hasNext("wallDist")){
			s.next();
			SensorNode node = new WallDistNode();
			return node;
		}
		fail("Invalid sensor terminal", s);

		return null;
	}

	private static NumNode parseNum(Scanner s){
		if(s.hasNext("-?[0-9]+")){
			return new NumNode(s.nextInt());
		}
		else {
			fail("Number argument error", s);
		}
		return null; //so it compiles
	}

	//Expression
	private static ExpressionNode parseExpr(Scanner s){
		if(s.hasNext("-?[0-9]+")){
			return new NumNode(s.nextInt());
		}
		if(isSensor(s)){
			return parseSen(s);
		}
		if(s.hasNext("add") || s.hasNext("sub") || s.hasNext("mul") || s.hasNext("div"))
			return parseOP(s);

		if(s.hasNext("-?[1-9][0-9]*|0")){
			return new NumNode(s.nextInt());
		}

		fail("Invalid expr", s);
		return null;
	}

	//Parse Operation
	private static ExpressionNode parseOP(Scanner s){

		//parsing Add
		if(s.hasNext("add")){
			s.next();
			if(s.hasNext(OPENPAREN)) { //scan through the open bracket
				s.next();
				ExpressionNode expr1 = parseExpr(s); //the first expression in the OP grammar
				if (s.hasNext(",")) {
					s.next(); //scans through the comma and the scanner is on the second expression
					ExpressionNode expr2 = parseExpr(s); //parse the second expression through
					if (s.hasNext(CLOSEPAREN)) {
						s.next();
						return new AddNode(expr1, expr2);
					}
					else{ fail("')' not found after the expression", s);}
				}
				else{ fail("',' not found", s); }
			}
			else{fail("'(' not found before the expression", s);}
		}

		//parsing sub
		if(s.hasNext("sub")){
			s.next();
			if(s.hasNext(OPENPAREN)) { //scan through the open bracket
				s.next();
				ExpressionNode expr1 = parseExpr(s); //the first expression in the OP grammar
				if (s.hasNext(",")) {
					s.next(); //scans through the comma and the scanner is on the second expression
					ExpressionNode expr2 = parseExpr(s); //parse the second expression through
					if (s.hasNext(CLOSEPAREN)) {
						s.next();
						return new SubNode(expr1, expr2);
					}
					else{ fail("')' not found after the expression", s);}
				}
				else{ fail("',' not found", s); }
			}
			else{fail("'(' not found before the expression", s);}
		}

		//parsing div
		if(s.hasNext("div")){
			s.next();
			if(s.hasNext(OPENPAREN)) { //scan through the open bracket
				s.next();
				ExpressionNode expr1 = parseExpr(s); //the first expression in the OP grammar
				if (s.hasNext(",")) {
					s.next(); //scans through the comma and the scanner is on the second expression
					ExpressionNode expr2 = parseExpr(s); //parse the second expression through
					if (s.hasNext(CLOSEPAREN)) {
						s.next();
						return new DivNode(expr1, expr2);
					}
					else{ fail("')' not found after the expression", s);}
				}
				else{ fail("',' not found", s); }
			}
			else{fail("'(' not found before the expression", s);}
		}

		//parsing Mul
		if(s.hasNext("mul")){
			s.next();
			if(s.hasNext(OPENPAREN)) { //scan through the open bracket
				s.next();
				ExpressionNode expr1 = parseExpr(s); //the first expression in the OP grammar
				if (s.hasNext(",")) {
					s.next(); //scans through the comma and the scanner is on the second expression
					ExpressionNode expr2 = parseExpr(s); //parse the second expression through
					if (s.hasNext(CLOSEPAREN)) {
						s.next();
						return new MulNode(expr1, expr2);
					}
					else{ fail("')' not found after the expression", s);}
				}
				else{ fail("',' not found", s); }
			}
			else{fail("'(' not found before the expression", s);}
		}

		return null;
	}

	static boolean isSensor(Scanner s) {
		if (s.hasNext("fuelLeft") || s.hasNext("oppLR") || s.hasNext("oppFB") || s.hasNext("numBarrels") || s.hasNext("barrelLR") || s.hasNext("barrelFB") || s.hasNext("wallDist")){
			return true;
		}
		return false;
	}


	// utility methods for the parser

	/**
	 * Report a failure in the parser.
	 */
	static void fail(String message, Scanner s) {
		String msg = message + "\n   @ ...";
		for (int i = 0; i < 5 && s.hasNext(); i++) {
			msg += " " + s.next();
		}
		throw new ParserFailureException(msg + "...");
	}

	/**
	 * Requires that the next token matches a pattern if it matches, it consumes
	 * and returns the token, if not, it throws an exception with an error
	 * message
	 */
	static String require(String p, String message, Scanner s) {
		if (s.hasNext(p)) {
			return s.next();
		}
		fail(message, s);
		return null;
	}

	static String require(Pattern p, String message, Scanner s) {
		if (s.hasNext(p)) {
			return s.next();
		}
		fail(message, s);
		return null;
	}

	/**
	 * Requires that the next token matches a pattern (which should only match a
	 * number) if it matches, it consumes and returns the token as an integer if
	 * not, it throws an exception with an error message
	 */
	static int requireInt(String p, String message, Scanner s) {
		if (s.hasNext(p) && s.hasNextInt()) {
			return s.nextInt();
		}
		fail(message, s);
		return -1;
	}

	static int requireInt(Pattern p, String message, Scanner s) {
		if (s.hasNext(p) && s.hasNextInt()) {
			return s.nextInt();
		}
		fail(message, s);
		return -1;
	}

	/**
	 * Checks whether the next token in the scanner matches the specified
	 * pattern, if so, consumes the token and return true. Otherwise returns
	 * false without consuming anything.
	 */
	static boolean checkFor(String p, Scanner s) {
		if (s.hasNext(p)) {
			s.next();
			return true;
		} else {
			return false;
		}
	}

	static boolean checkFor(Pattern p, Scanner s) {
		if (s.hasNext(p)) {
			s.next();
			return true;
		} else {
			return false;
		}
	}

}

// You could add the node classes here, as long as they are not declared public (or private)
