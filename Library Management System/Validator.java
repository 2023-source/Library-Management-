import java.util.Scanner;
import java.util.regex.Pattern;
import java.time.Year;
import java.util.List;

public class Validator
{
    public static final String RED="\u001B[31m";
    public static final String RESET="\u001B[0m";
    private static Pattern ID_PATTERN = Pattern.compile("^\\d{1,4}$");
    private static Pattern AUTHORTITLE_PATTERN=Pattern.compile("^[a-zA-Z ]+$");
    private static Pattern PublishYear_Pattern=Pattern.compile("^\\d{4}$");
    Scanner sc = new Scanner(System.in);

    public String isUniqueBookId(List<Book> books) {
        
        String bookId;
        
        while(true)
        {
            System.out.println("Enter Book Id: ");
            bookId = sc.nextLine();
            boolean flag=true;
            
            if(flag == true)
            {
               for (Book book : books) 
                {
                  if (book.getId().equals(bookId)) 
                  {
                   System.out.println(RED+"SORRY ! BOOK WITH SAME ID ALREADY EXIST "+ RESET);
                   flag = false;
                  }
                }
            }

            if (flag == true && ID_PATTERN.matcher(bookId).matches())
            {
                break;
            }
            else if(flag == true && !ID_PATTERN.matcher(bookId).matches())
            {
                System.out.println(RED+"SORRY ! PLEASE ENTER VALID 4 DIGIT BOOK ID "+RESET);
            }

            System.out.println(flag);

        }

        return bookId;
    }

    public String validateId()
    {
        String bookId;
        while(true)
        {
            System.out.println("Enter Book Id: ");
            bookId = sc.nextLine();

            if(!ID_PATTERN.matcher(bookId).matches())
            {
               System.out.println(RED+"SORRY ! PLEASE ENTER VALID 4 DIGIT BOOK ID "+RESET);
            }
            else
            {
                break;
            }
        }
        return bookId;
    }

    public String validatebookAuthorTitle(String input)
    {
        String result;
        while(true)
        {
            if(input == "Title")
            { System.out.println("Enter Book Title that you want to purchase: ");}
            else
            {System.out.println("Enter Book Author that you want to purchase: ");}

            result = sc.nextLine();

            if(!AUTHORTITLE_PATTERN.matcher(result).matches())
            {
                System.out.println(RED+"Please Enter Valid "+input+RESET);
            }
            else
            {
                break;
            }
        }
        return result;
    }

    public String validatebookPublishYear()
    {
        String year;
        int currentYear = Year.now().getValue();
        while(true){
            System.out.println("Enter Publish Year of Book that you want:  ");
            year=sc.nextLine();
            if(!PublishYear_Pattern.matcher(year).matches()){
                System.out.println(RED+"Enter valid Publish Year"+RESET);
            }
            else if(Integer.parseInt(year) > currentYear)
            {
                System.out.println(RED+"Publish Year cannot be greater than the current year "+currentYear+RESET);
            }
            else{
                break;
            }
        }
        return year;
    }
}