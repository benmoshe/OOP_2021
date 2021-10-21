package files;

import java.io.*;

public class file {

    public static void write2File(String name, String msg, boolean append){
        try {
            FileWriter fw = new FileWriter(name, append);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(msg);
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readFromFile(String name){
        try {
            FileReader fr = new FileReader(name);
            BufferedReader br = new BufferedReader(fr);
            System.out.println(br.readLine());
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        //write2File("./Class_Material/Week_2/T2-ItaiLash/src/files/file_example.txt","Hello World", false);
        readFromFile("./Class_Material/Week_2/T2-ItaiLash/src/files/file_example.txt");
    }

}
