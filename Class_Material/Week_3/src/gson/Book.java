package gson;

public class Book {

    private String title;
    private String isbn;
    private long year;
    private String[] authors;

    public Book() {
    }

    public Book(String title, String isbn, long year, String[] authors) {
        this.title = title;
        this.isbn = isbn;
        this.year = year;
        this.authors = authors;
    }
    public String toString() {
        String ans = ""+this.getClass().getSimpleName(), au="";
        for (String a:this.authors) {
            au +=a+",";

        }

        ans += " | Title: "+this.title+",  ISBN: "+this.isbn+",  Authors: "+au+",  Year: "+this.year;
        return ans;
    }

    // getters and setters, equals(), toString() .... (omitted for brevity)
}