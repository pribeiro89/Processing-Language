import edu.princeton.cs.algs4.*;
import processing.core.PApplet;

public class HanoiSketch extends PApplet{
	private static int n = 4;									
	private static int passos = (int) (Math.pow(2, n) - 1);		
	private static int control = 0;
	
	private static final int WIDTH = 800;   // min width of window
	private static final int HEIGHT = 480;  // max width of window
	
	private static final double HERTZ = 0.5;       // frequency
	private static final double INITIALDELAY = 1;  // initial delay, in seconds
	
	private double startTime;     	// time when the animation started
	private int steps;            	// number of steps in the animation
	
	private int Peg1X = (WIDTH/2) - 200;	// posicao x da primeira haste. as 3 estao separadas por 200 px
	private Hanoi h;
	ArrayBag<int[]> movimentos = new ArrayBag<int[]>();
	static ArrayBag<Stack<Integer>> result = new ArrayBag<Stack<Integer>>();

  // The colors should be in a separate class.
	final int aliceblue = color(240, 248, 255);
	final int antiquewhite = color(250, 235, 215);
	final int aqua = color(0, 255, 255);
	final int aquamarine = color(127, 255, 212);
	final int azure = color(240, 255, 255);
	final int beige = color(245, 245, 220);
	final int bisque = color(255, 228, 196);
	final int black = color(0, 0, 0);
	final int blanchedalmond = color(255, 235, 205);
	final int blue = color(0, 0, 255);
	final int blueviolet = color(138, 43, 226);
	final int brown = color(165, 42, 42);
	final int burlywood = color(222, 184, 135);
	final int cadetblue = color(95, 158, 160);
	final int chartreuse = color(127, 255, 0);
	final int chocolate = color(210, 105, 30);
	final int coral = color(255, 127, 80);
	final int cornflowerblue = color(100, 149, 237);
	final int cornsilk = color(255, 248, 220);
	final int crimson = color(220, 20, 60);
	final int cyan = color(0, 255, 255);
	final int darkblue = color(0, 0, 139);
	final int darkcyan = color(0, 139, 139);
	final int darkgoldenrod = color(184, 134, 11);
	final int darkgray = color(169, 169, 169);
	final int darkgreen = color(0, 100, 0);
	final int darkkhaki = color(189, 183, 107);
	final int darkmagenta = color(139, 0, 139);
	final int darkolivegreen = color(85, 107, 47);
	final int darkorange = color(255, 140, 0);
	final int darkorchid = color(153, 50, 204);
	final int darkred = color(139, 0, 0);
	final int darksalmon = color(233, 150, 122);
	final int darkseagreen = color(143, 188, 143);
	final int darkslateblue = color(72, 61, 139);
	final int darkslategray = color(47, 79, 79);
	final int darkturquoise = color(0, 206, 209);
	final int darkviolet = color(148, 0, 211);
	final int deeppink = color(255, 20, 147);
	final int deepskyblue = color(0, 191, 255);
	final int dimgray = color(105, 105, 105);
	final int dodgerblue = color(30, 144, 255);
	final int firebrick = color(178, 34, 34);
	final int floralwhite = color(255, 250, 240);
	final int forestgreen = color(34, 139, 34);
	final int fuchsia = color(255, 0, 255);
	final int gainsboro = color(220, 220, 220);
	final int ghostwhite = color(248, 248, 255);
	final int gold = color(255, 215, 0);
	final int goldenrod = color(218, 165, 32);
	final int gray = color(128, 128, 128);
	final int green = color(0, 128, 0);
	final int greenyellow = color(173, 255, 47);
	final int honeydew = color(240, 255, 240);
	final int hotpink = color(255, 105, 180);
	final int indianred = color(205, 92, 92);
	final int indigo = color(75, 0, 130);
	final int ivory = color(255, 255, 240);
	final int khaki = color(240, 230, 140);
	final int lavender = color(230, 230, 250);
	final int lavenderblush = color(255, 240, 245);
	final int lawngreen = color(124, 252, 0);
	final int lemonchiffon = color(255, 250, 205);
	final int lightblue = color(173, 216, 230);
	final int lightcoral = color(240, 128, 128);
	final int lightcyan = color(224, 255, 255);
	final int lightgoldenrodyellow = color(250, 250, 210);
	final int lightgray = color(211, 211, 211);
	final int lightgreen = color(144, 238, 144);
	final int lightpink = color(255, 182, 193);
	final int lightsalmon = color(255, 160, 122);
	final int lightseagreen = color(32, 178, 170);
	final int lightskyblue = color(135, 206, 250);
	final int lightslategray = color(119, 136, 153);
	final int lightsteelblue = color(176, 196, 222);
	final int lightyellow = color(255, 255, 224);
	final int lime = color(0, 255, 0);
	final int limegreen = color(50, 205, 50);
	final int linen = color(250, 240, 230);
	final int magenta = color(255, 0, 255);
	final int maroon = color(128, 0, 0);
	final int mediumaquamarine = color(102, 205, 170);
	final int mediumblue = color(0, 0, 205);
	final int mediumorchid = color(186, 85, 211);
	final int mediumpurple = color(147, 112, 219);
	final int mediumseagreen = color(60, 179, 113);
	final int mediumslateblue = color(123, 104, 238);
	final int mediumspringgreen = color(0, 250, 154);
	final int mediumturquoise = color(72, 209, 204);
	final int mediumvioletred = color(199, 21, 133);
	final int midnightblue = color(25, 25, 112);
	final int mintcream = color(245, 255, 250);
	final int mistyrose = color(255, 228, 225);
	final int moccasin = color(255, 228, 181);
	final int navajowhite = color(255, 222, 173);
	final int navy = color(0, 0, 128);
	final int oldlace = color(253, 245, 230);
	final int olive = color(128, 128, 0);
	final int olivedrab = color(107, 142, 35);
	final int orange = color(255, 165, 0);
	final int orangered = color(255, 69, 0);
	final int orchid = color(218, 112, 214);
	final int palegoldenrod = color(238, 232, 170);
	final int palegreen = color(152, 251, 152);
	final int paleturquoise = color(175, 238, 238);
	final int palevioletred = color(219, 112, 147);
	final int papayawhip = color(255, 239, 213);
	final int peachpuff = color(255, 218, 185);
	final int peru = color(205, 133, 63);
	final int pink = color(255, 192, 203);
	final int plum = color(221, 160, 221);
	final int powderblue = color(176, 224, 230);
	final int purple = color(128, 0, 128);
	final int red = color(255, 0, 0);
	final int rosybrown = color(188, 143, 143);
	final int royalblue = color(65, 105, 225);
	final int saddlebrown = color(139, 69, 19);
	final int salmon = color(250, 128, 114);
	final int sandybrown = color(244, 164, 96);
	final int seagreen = color(46, 139, 87);
	final int seashell = color(255, 245, 238);
	final int sienna = color(160, 82, 45);
	final int silver = color(192, 192, 192);
	final int skyblue = color(135, 206, 235);
	final int slateblue = color(106, 90, 205);
	final int slategray = color(112, 128, 144);
	final int snow = color(255, 250, 250);
	final int springgreen = color(0, 255, 127);
	final int steelblue = color(70, 130, 180);
	final int tan = color(210, 180, 140);
	final int teal = color(0, 128, 128);
	final int thistle = color(216, 191, 216);
	final int tomato = color(255, 99, 71);
	final int turquoise = color(64, 224, 208);
	final int violet = color(238, 130, 238);
	final int wheat = color(245, 222, 179);
	final int white = color(255, 255, 255);
	final int whitesmoke = color(245, 245, 245);
	final int yellow = color(255, 255, 0);
	final int yellowgreen = color(154, 205, 50);
	
	final int [] HTMLColors = {
	   aqua, black, blue, fuchsia, gray, 
	   green, lime, maroon, navy, olive,
	   orange, purple, red, silver, teal,
	   white, yellow};

	final int [] allColors = { aliceblue, antiquewhite, aqua, aquamarine,
	      azure, beige, bisque, black, blanchedalmond, blue, blueviolet, brown,
	      burlywood, cadetblue, chartreuse, chocolate, coral, cornflowerblue,
	      cornsilk, crimson, cyan, darkblue, darkcyan, darkgoldenrod, darkgray,
	      darkgreen, darkkhaki, darkmagenta, darkolivegreen, darkorange,
	      darkorchid, darkred, darksalmon, darkseagreen, darkslateblue,
	      darkslategray, darkturquoise, darkviolet, deeppink, deepskyblue, dimgray,
	      dodgerblue, firebrick, floralwhite, forestgreen, fuchsia, gainsboro,
	      ghostwhite, gold, goldenrod, gray, green, greenyellow, honeydew, hotpink,
	      indianred, indigo, ivory, khaki, lavender, lavenderblush, lawngreen,
	      lemonchiffon, lightblue, lightcoral, lightcyan, lightgoldenrodyellow,
	      lightgray, lightgreen, lightpink, lightsalmon, lightseagreen,
	      lightskyblue, lightslategray, lightsteelblue, lightyellow, lime,
	      limegreen, linen, magenta, maroon, mediumaquamarine, mediumblue,
	      mediumorchid, mediumpurple, mediumseagreen, mediumslateblue,
	      mediumspringgreen, mediumturquoise, mediumvioletred, midnightblue,
	      mintcream, mistyrose, moccasin, navajowhite, navy, oldlace, olive,
	      olivedrab, orange, orangered, orchid, palegoldenrod, palegreen,
	      paleturquoise, palevioletred, papayawhip, peachpuff, peru, pink, plum,
	      powderblue, purple, red, rosybrown, royalblue, saddlebrown, salmon,
	      sandybrown, seagreen, seashell, sienna, silver, skyblue, slateblue,
	      slategray, snow, springgreen, steelblue, tan, teal, thistle, tomato,
	      turquoise, violet, wheat, white, whitesmoke, yellow, yellowgreen };
	
	public void settings() {
		size(WIDTH, HEIGHT);
	}
	  
	public void setup() {
		h = new Hanoi(n);
		movimentos = h.solution();
		startTime = millis() + INITIALDELAY * 1000;
	    steps = 0;
	}
	  
	public void draw() {
	    background(200);
	    update();
	    display();
	}
	
	private void update() {
		double seconds = (millis() - startTime) / 1000.0;
	      int timeUnits = (int)(seconds * HERTZ);
	      if (timeUnits > steps) {
	        steps++;
	        if (control < passos) {
	          result = h.play(control);
	          control++;
	        }
	        else
	          result = h.play(passos);
	      }
	 }
	
	private void display() {
		rectMode(CENTER);
		noStroke();
	
	    poles();
	    
	    ellipseMode(CENTER);
	    fill(yellowgreen);
	    ellipse(Peg1X, height/2, 50, 50);
	    ellipse(Peg1X + 200, height/2, 50, 50);
	    ellipse(Peg1X + 400 , height/2, 50, 50);
	  }
	
	private void poles() {
		int position = Peg1X;
		for (Stack<Integer> stack : result){
			drawStack(position, stack);
			position+=200;
		}
	}
	
	private void drawStack(int position, Stack<Integer> stack) {
		Stack<Integer> temp = new Stack<Integer>();
		for (Integer value : stack)
			temp.push(value);
		for (Integer disk : temp) {
			fill (HTMLColors[disk]);
			ellipse(position, height/2, (100+(disk*35)), (100+(disk*35)));
		}
	}
	
	private static void getArgs(String[] args) {
	    if (args.length >= 1)
	      n = Integer.parseInt(args[0]);
	}
	
	public static void main(String[] args) {
		getArgs(args);
	    PApplet.main(new String[] { HanoiSketch2.class.getName() });
	}
}

/*--------------------------------------------------------------------------------*/
/* 
 * Same implementation as can be found in the Hanoi.java file in the Java-Programming -> Uni-Exercices folder 
 * Duplication was necessary because of Mooshak submission used to evalute the program.
 */

class Hanoi {
	static ArrayBag<Stack<Integer>> myBag = new ArrayBag<Stack<Integer>>(3);
	private static int n;
	private static ArrayBag<int[]> sol = new ArrayBag<int[]>();
	
	public Hanoi (int n) {
		this.n = n;
		sol = solution();
	}
		
	private static Bag<int[]> solve(int n, int x, int y, int z){
			Bag<int[]> result = new Bag<>();
			solveNow(n, x, y, z, result);
			return result;
	}
	
	private static void solveNow(int n, int x, int y, int z, Bag<int[]> r){
		if (n > 0) {
			solveNow(n-1, x, z, y, r);
			r.add(new int[]{x, y});
			solveNow(n-1, z, y, x, r);
		}
	}
	
	public ArrayBag<int[]> solution(){
		ArrayBag<int[]> res = new ArrayBag<int[]>();
		Bag<int[]> r = solve(n, 0, 1, 2);
		for(int[] a : r){
			res.add(a);
		}
		return res;
	}
	
	public ArrayBag<Stack<Integer>> play(int m){
		ArrayBag<Stack<Integer>> aBag = new ArrayBag<Stack<Integer>>();
		for (int i = 0; i < 3; i++)
			aBag.add(new Stack<Integer>());
		
		for (int i = n; i > 0; i--)
            aBag.get(0).push(i);

		for (int j = 0; j < m; j++){
			int de = sol.get(j)[0];
			int para = sol.get(j)[1];
			aBag.get(para).push(aBag.get(de).pop());
		}
		
		for (Stack<Integer> s : aBag){
			String space = "";
			if (!s.isEmpty()) {
				for (Integer i : s){
					StdOut.print(space + i);
					space = " ";
				}
			}
			else
				StdOut.print("*");
			StdOut.print("\n");
		}
		return aBag;	
	}
	
	public static void testHanoi(){
		int n = StdIn.readInt();
		Hanoi h = new Hanoi(n);
		int steps = (int) (Math.pow(2, n) - 1);

		while (!StdIn.isEmpty()){
			int m = StdIn.readInt();
			if (m > steps)
				h.play(steps);
			else if (m <= steps)
				h.play(m);
		}
	}

	public static void main(String[] args) {
		testHanoi();
	}
}
