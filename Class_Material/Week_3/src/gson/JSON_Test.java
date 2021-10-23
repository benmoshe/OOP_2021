package gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JSON_Test {
    public static void main(String[] a) {
        // test1();
        //test2();
        test3();
        test4();
    }
    // example from: https://attacomsian.com/blog/gson-read-write-json
    public static void test4() {
        try {
            // create Gson instance
            Gson gson = new Gson();

            // create a reader
            Reader reader = Files.newBufferedReader(Paths.get("book.json"));

            // convert JSON string to Book object
            Book book = gson.fromJson(reader, Book.class);

            // print book
            System.out.println("The JSON (using GSON lib): "+book);

            // close reader
            reader.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    // example from: https://attacomsian.com/blog/gson-read-write-json
    public static void test3() {
        try {
            // create book object
            Book book = new Book("Thinking in Java", "978-0131872486", 1998,
                    new String[]{"Bruce Eckel"});

            // convert book object to JSON
      //      Gson gson = new Gson().toJson(book);
            String json = new Gson().toJson(book);

            // print JSON string
            System.out.println(json);
            Gson gson = new Gson();
            Writer writer = Files.newBufferedWriter(Paths.get("book.json"));

            // convert book object to JSON file
            gson.toJson(book, writer);

            // close writer
            writer.close();


        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
    public static void test2() {
        VersionedClass versionedObject = new VersionedClass();
        Gson gson = new GsonBuilder().setVersion(1.0).create();
        String jsonOutput = gson.toJson(versionedObject);
        System.out.println(jsonOutput);
        System.out.println();

        gson = new Gson();
        jsonOutput = gson.toJson(versionedObject);
        System.out.println(jsonOutput);

    }

    public static void test1() {

        System.out.println("test 123");

        Gson gson = new Gson();
        gson.toJson(1);            // ==> 1
        gson.toJson("abcd");       // ==> "abcd"
        gson.toJson(new Long(10)); // ==> 10
        int[] values = { 1 };
        gson.toJson(values);       // ==> [1]

// Deserialization
        int one = gson.fromJson("1", int.class);
        one = gson.fromJson("1", Integer.class);
        Long ll = gson.fromJson("1", Long.class);
        Boolean ff = gson.fromJson("false", Boolean.class);
        String str = gson.fromJson("\"abc\"", String.class);
        String[] anotherStr = gson.fromJson("[\"abc\"]", String[].class);
      //  String jsonOutput = gson.toJson(gson);
        System.out.println("GSON: "+gson);
    }
}
