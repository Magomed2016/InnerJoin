import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        String string;
        ArrayList<String> ArrayListTableA = new ArrayList<>();
        ArrayList<String> ArrayListTableB = new ArrayList<>();
        LinkedList<String> LinkedListTableA = new LinkedList<>();
        LinkedList<String> LinkedListTableB = new LinkedList<>();
        HashMap<Integer,String> hashMapTableA = new HashMap<>();
        HashMap<Integer,String> hashMapTableB = new HashMap<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(args[0]))) {
            int i = 0;
            while((string=bufferedReader.readLine())!=null) {


                ArrayListTableA.add(string);
                LinkedListTableA.add(string);
                hashMapTableA.put(i,string);
                i++;


            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

       try(BufferedReader bufferedReader = new BufferedReader(new FileReader(args[1]))){
           int i = 0;
           while((string=bufferedReader.readLine())!=null) {


               ArrayListTableB.add(string);
               LinkedListTableB.add(string);
               hashMapTableB.put(i,string);
               i++;

           }
       } catch (FileNotFoundException e) {
           e.printStackTrace();
       } catch (IOException e) {
           e.printStackTrace();
       }

        System.out.println(hashMapTableA.entrySet().toString());
        System.out.println(hashMapTableB.entrySet().toString());

        innerJoinArrayList(ArrayListTableA,ArrayListTableB);
        innerJoinLinkedList(LinkedListTableA,LinkedListTableB);
        innerJoinHashMap(hashMapTableA,hashMapTableB);



    }
    public static void innerJoinArrayList(ArrayList<String> tableA, ArrayList<String> tableB){

        for (String s : tableA) {
            String[] string = s.split(" ");
            for (String s1 : tableB) {
                String[] string1 = s1.split(" ");
                if(string[0].trim().equals(string1[0].trim()))
                    System.out.println(string[0]+" "+string[1]+" "+string1[1]);
            }
        }
        System.out.println("==================================");

    }

    public static void innerJoinLinkedList(LinkedList<String> tableA, LinkedList<String> tableB){

        for (String s : tableA) {
            String[] string = s.split(" ");
            for (String s1 : tableB) {
                String[] string1 = s1.split(" ");
                if(string[0].trim().equals(string1[0].trim()))
                    System.out.println(string[0]+" "+string[1]+" "+string1[1]);
            }
        }
        System.out.println("==================================");
    }

    public static void innerJoinHashMap(HashMap<Integer,String> tableA, HashMap<Integer,String> tableB){

        for (Integer integer : tableA.keySet()) {
            for (Integer integer1 : tableB.keySet()) {
                if((tableA.get(integer).split(" "))[0].trim().equals((tableB.get(integer1).split(" "))[0].trim()))
                    System.out.println((tableA.get(integer).split(" "))[0].trim()+" "+(tableA.get(integer).split(" "))[1].trim()+" "+(tableB.get(integer1).split(" "))[1].trim());
            }
        }
    }

}
