package SebestaScanner;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
public class SebestaScanner {
	
	static int charClass;
	static String lexeme ="";
	static char nextChar;
	static int token;
	static int nextToken;
	static int character;
	static int lexlen ;
	static FileReader inputStream = null;
	static final int LETTER = 0;
	static final int DIGIT = 1;
	static final int UNKNOWN = 99;
	static final int EOF = -1;
	static final int INT_LIT = 10;
	static final int IDENT = 11;
	static final int ASSIGN_OP = 20;
	static final int ADD_OP = 21;
	static final int SUB_OP = 22;
	static final int MULT_OP = 23;
	static final int DIV_OP = 24;
	static final int LEFT_PAREN = 25;
	static final int RIGHT_PAREN = 26;
	
	
	public static void main(String[] arg) throws IOException
	{
		try
		{

			inputStream = new FileReader("front.in");
            
			getChar();
			do
			{
				lex();
			} while (nextToken!=EOF);
			 
		}
		catch(FileNotFoundException e)
		{
			
			System.out.println("File not found");
			System.out.println("Directory: "+ System.getProperty("user.dir"));
		}
		finally
		{
			if (inputStream != null)
			{
				try
				{
					inputStream.close();
				}
				catch(IOException e)
				{
					System.out.println("File close error.");
				}
			}
		}
		
	}
	
	
	public static void addChar()
	{
		lexeme += nextChar;
	}	
	
	
	public static void getChar() throws IOException
	{
		nextChar = (char) inputStream.read();
		if (Character.isSpaceChar(nextChar))
		{
			getChar();
		}
		if(nextChar != EOF)
		{
			if (Character.isLetter(nextChar))
			{
				charClass = LETTER;
			}
			else if (Character.isDigit(nextChar))
			{
				charClass = DIGIT;
			}
			else
			{
				charClass = UNKNOWN;
			}
		}
	}  
	
	
	public static int lookup(char ch)
	{
		switch(ch)
		{
			case '(':
				addChar();
				nextToken=LEFT_PAREN;
				break;
			
			
			case ')':
				addChar();
				nextToken=RIGHT_PAREN;
				break;
			
			case '+':
				addChar();
				nextToken=ADD_OP;
				break;
	
			case '-':
				addChar();
				nextToken=SUB_OP;
				break;
	
			case '*':
				addChar();
				nextToken=MULT_OP;
				break;
	
			case '/':
				addChar();
				nextToken=DIV_OP;
				break;
	
			default:
				addChar();
				nextToken=EOF;
				lexeme = "EOF";
				break;
		}
		return nextToken;

	}
	
	
	public static int lex() throws IOException
	{
		lexeme = "";
		switch(charClass)
		{
			case LETTER:
				addChar();
				getChar();
			
				while (charClass == LETTER || charClass == DIGIT )
				{
					addChar();
					getChar();
				}
				nextToken = IDENT;
				break;
			case DIGIT:
				addChar();
				getChar();
			
				while (charClass == DIGIT )
				{
					addChar();
		 			getChar();
				}
				nextToken = INT_LIT;
				break;
			case UNKNOWN:
				lookup(nextChar);
				getChar();
				break;
			case EOF:
				nextToken = EOF;
				lexeme = "EOF";
				break;
		}
		
		System.out.print("Next Token is : " + nextToken + " , Next lexeme is: " + lexeme+ "\n");
		return nextToken;
	}
	
}
