import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Iterator;

public class Library_Service
{
    // Used to give color for message display..
    public static final String RED="\u001B[31m";
    public static final String RESET="\u001B[0m";
    public static final String BLUE="\u001B[34m";
    public static final String GREEN="\u001B[32m";
    public static final String CYAN="\u001B[36m";
    private static final String FILE_NAME = "books.txt";

    Scanner sc = new Scanner(System.in);
    Validator validator = new Validator();
    List<Book> books = new ArrayList<>();

    public void loadBooksFromFile() 
    {

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) 
        {
            String line;
            while ((line = br.readLine()) != null) {
                String[] bookData = line.split(",");
                Book book = new Book(bookData[0], bookData[1], bookData[2], bookData[3], bookData[4]);
                books.add(book);
            }
        } catch (IOException e) 
        {
            System.out.println(RED + "Error reading from file: " + e.getMessage() + RESET);
        }
    }

    public void saveBooksToFile() 
    {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) 
        {
            for (Book book : books) {
                bw.write(book.getId() + "," + book.getTitle() + "," + book.getAuthor() + "," + book.getPublishYear() + "," + book.getStatus());
                bw.newLine();
            }
        } catch (IOException e) 
        {
        System.out.println(RED + "Error writing to file: " + e.getMessage() + RESET);
        }
    }

    // List hold the elements of type Book 
    public void addBook()
    {
      String bookId = validator.isUniqueBookId(books);
      String bookTitle = validator.validatebookAuthorTitle("Title");
      String bookAuthor = validator.validatebookAuthorTitle("Author");
      String bookPublishYear = validator.validatebookPublishYear();
      Book book = new Book(bookId,bookTitle,bookAuthor,bookPublishYear,"Available");
      books.add(book);
      System.out.println(GREEN+"Book Added Successfully !!!"+RESET);
      saveBooksToFile();
    }

    public void showAllBooks() 
    {
     boolean flag=false;
     System.out.println("\n----------------------------------------------------------------------------------------------");
     System.out.format(CYAN+"%s%15s%15s%15s%15s","ID","TITLE","AUTHOR","PUBLISH YEAR","STATUS"+RESET);
     System.out.println("\n----------------------------------------------------------------------------------------------");

        for (Book book:books){
            System.out.format("%s%15s%15s%15s%15s",book.getId(),book.getTitle(),book.getAuthor(),book.getPublishYear(),book.getStatus());
            System.out.println();
            flag=true;
          }
        System.out.println("\n----------------------------------------------------------------------------------------------");
       if(!flag)
           System.out.println(RED+"There are no Books in Library"+RESET);
    }

    public void showAllAvailableBooks(){
        boolean flag=false;
        System.out.println("\n----------------------------------------------------------------------------------------------");
        System.out.format(CYAN+"%s%15s%15s%15s%15s","ID","TITLE","AUTHOR","PUBLISH YEAR","STATUS"+RESET);
        System.out.println("\n----------------------------------------------------------------------------------------------");

        if(books.size()>0){
            for (Book book:books){
                if(book.getStatus().equals("Available")){
                    System.out.format("%s%15s%15s%15s%15s",book.getId(),book.getTitle(),book.getAuthor(),book.getPublishYear(),book.getStatus());
                    System.out.println();
                    flag=true;
                }
            }
        }
        else{
            System.out.println(RED+"No Books Available in the library"+RESET);
        }
        System.out.println("\n----------------------------------------------------------------------------------------------");
        if(!flag)
            System.out.println(RED+"There are no books with status Available"+RESET);

    }
    public void borrowBook(){
       String bookId= validator.validateId();
       boolean flag=false;
       for(Book book:books){
           if(book.getId().equals(bookId) && book.getStatus().equals("Available")){
               flag=true;
               System.out.println(GREEN+"Book Borrowed Successfully !!!"+RESET);
               book.setStatus("Not Available");
               System.out.println("Borrowed Book Details: "+book);
               saveBooksToFile();
               break;
           }
          }
       if(!flag)
           System.out.println(RED+"Either book is not available to borrow or book is not in the library"+RESET);


    }
    public void returnBook(){
        boolean flag=false;
        String bookId= validator.validateId();
        for(Book book:books){
            if(book.getId().equals(bookId) && book.getStatus().equals("Not Available")){
                flag=true;
                System.out.println(GREEN+"Book Returned Successfully !!!"+RESET);
                book.setStatus("Available");
                System.out.println("Returned Book Details: "+book);
                saveBooksToFile();
                break;
            }

        }
        if(!flag)
            System.out.println(RED+"Either this book is not available or not present in the library."+RESET);

    }

    public void removeBook()
    {
      String bookId = validator.validateId();
      boolean flag=false;
      Iterator<Book> iterator = books.iterator();
        while (iterator.hasNext()) 
        {
            Book book = iterator.next();
            if (book.getId().equals(bookId) && book.getStatus().equals("Available")) {
                flag = true;
                iterator.remove();
                System.out.println(GREEN + "Book Removed Successfully !!!" + RESET);
                System.out.println("Removed Book Details: " + book);
                saveBooksToFile();
                break;
            }
        }
        if(!flag)
        {
            System.out.println(RED+"We can not remove this book"+RESET);
        }
      

    }
    


} 