import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        Library_Service libservice= new Library_Service();
        UserService userService = new UserService();

        // To load data of library books from file.
        libservice.loadBooksFromFile();
        // To load data of the users.
        
        userService.loadUsers();
        boolean loggedin = false;

        while(!loggedin)
        {
          System.out.println("Please select one from below options");
          System.out.println("1.Login \n"+"2.Register\n"+"3.Exit");
          System.out.println("Enter Your Choice !");
          int ch1 = sc.nextInt();

          switch(ch1)
          {
            case 1:
                loggedin = userService.loginUser();
                break;
            case 2:
                loggedin = userService.registerUser();
                break;
            case 3:
                
                System.out.println("Exit Successfull!.. ");
                System.exit(0);
                break;
            default :
                System.out.println("Invalid choice try again.. ");

                 
          }  
        }
        
        
        while(loggedin)
        {
           System.out.println("Welcome to Library Management System");
           System.out.println("1.Add Book\n"+
                   "2.Show All Books\n"+
                   "3.Show Available Books\n"+
                   "4.Borrow Book\n"+
                   "5.Return Book\n"+
                   "6.Remove Book\n"+ 
                   "7.Logout\n");
            System.out.println("Enter Your Choice !");
            int ch = sc.nextInt();
            switch(ch)
            {
                case 1:
                    libservice.addBook();
                break;
                case 2:
                    libservice.showAllBooks();
                break;
                case 3:
                    libservice.showAllAvailableBooks();
                break;
                case 4:
                    libservice.borrowBook();
                break;
                case 5:
                    libservice.returnBook();
                break;
                case 6:
                    libservice.removeBook();
                break;
                case 7:
                    loggedin = false;
                    System.out.println("Thankyou for using our application. Visit again!.. ");
                    System.out.println("Logged out successfully!");
                break;
                default :
                    System.out.println("Invalid choice try again.. ");

            }
        };
    }
}