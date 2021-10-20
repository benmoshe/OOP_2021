package files;

import java.io.*;

public class file {

    public static void write2File(String path, String msg){
            try {
                FileWriter fw = new FileWriter(path);
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write(msg);
                bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    public static void readFromFile(String path){
        try {
            FileReader fr = new FileReader(path);
            BufferedReader br = new BufferedReader(fr);
            System.out.println(br.readLine());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        write2File("./Class_Material/Week_2/T2-ItaiLash/src/files/test.txt", "hello world");
        readFromFile("./Class_Material/Week_2/T2-ItaiLash/src/files/test.txt");
    }

}
