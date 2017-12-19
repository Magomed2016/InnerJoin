import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        if(args.length==5) {
            String string;
            ArrayList<String> ArrayListTableA = new ArrayList<>();
            ArrayList<String> ArrayListTableB = new ArrayList<>();
            LinkedList<String> LinkedListTableA = new LinkedList<>();
            LinkedList<String> LinkedListTableB = new LinkedList<>();
            HashMap<Integer, String> hashMapTableA = new HashMap<>();
            HashMap<Integer, String> hashMapTableB = new HashMap<>();

            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(args[0]))) {
                int i = 0;
                while ((string = bufferedReader.readLine()) != null) {

                    if (string.equals(""))
                        continue;
                    ArrayListTableA.add(string);
                    LinkedListTableA.add(string);
                    hashMapTableA.put(i, string);
                    i++;


                }
            } catch (FileNotFoundException e) {
                System.err.println("Файл не найден");
                e.printStackTrace();
            } catch (IOException e) {
                System.err.println("Не возможно установить соединение");
                e.printStackTrace();
            }

            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(args[1]))) {
                int i = 0;
                while ((string = bufferedReader.readLine()) != null) {

                    if (string.equals(""))
                        continue;
                    ArrayListTableB.add(string);
                    LinkedListTableB.add(string);
                    hashMapTableB.put(i, string);
                    i++;

                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            //System.out.println(hashMapTableA.entrySet().toString());
            //System.out.println(hashMapTableB.entrySet().toString());


            write(innerJoinArrayList(ArrayListTableA, ArrayListTableB), args[2]);
            write(innerJoinLinkedList(LinkedListTableA, LinkedListTableB), args[3]);
            write(innerJoinHashMap(hashMapTableA, hashMapTableB), args[4]);
        } else try {
            throw new Exception("неверное количество входных данных");
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public static void write( String string, String url){
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(url))) {
            bufferedWriter.write(string);
        } catch (IOException e) {
            System.err.println("не удаётся установить соединенеие");
            e.printStackTrace();
        }
    }


    public static String innerJoinArrayList(ArrayList<String> tableA, ArrayList<String> tableB){

        long time = System.currentTimeMillis();

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("ID A.VALUE B.VALUE"+"\r\n");

        for (String s : tableA) {
            String[] string = s.split(" ");
            for (String s1 : tableB) {
                String[] string1 = s1.split(" ");
                if(string[0].trim().equals(string1[0].trim()))
                    stringBuilder.append(string[0]+"  "+string[1]+"       "+string1[1]+"\r\n");
                    //System.out.println(string[0]+" "+string[1]+" "+string1[1]);
            }
        }
        System.out.println(System.currentTimeMillis() - time);
        return String.valueOf(stringBuilder);

    }

    public static String innerJoinLinkedList(LinkedList<String> tableA, LinkedList<String> tableB){

        long time = System.currentTimeMillis();
        Collections.sort(tableA);
        Collections.sort(tableB);
        StringBuilder stringBuilder = new StringBuilder();

        if(tableA.getLast().trim().compareTo(tableB.getLast().trim())==1)
        {
            stringBuilder.append("ID A.VALUE B.VALUE"+"\r\n");
            for (String s : tableB) {
                String[] string = s.split(" ");
                for (String s1 : tableA) {
                    if(s1.compareTo(s)==1)
                        break;
                    String[] string1 = s1.split(" ");
                    if(string[0].trim().equals(string1[0].trim()))
                        stringBuilder.append(string[0]+"  "+string[1]+"       "+string1[1]+"\r\n");

                }
            }
            System.out.println(System.currentTimeMillis()- time);
        } else {
            stringBuilder.append("ID B.VALUE A.VALUE");
            for (String s : tableA) {
                String[] string = s.split(" ");
                for (String s1 : tableB) {
                    if(s1.compareTo(s)==1)
                        break;
                    String[] string1 = s1.split(" ");
                    if (string[0].trim().equals(string1[0].trim()))
                        stringBuilder.append(string[0] + "  " + string[1] + "       " + string1[1] + "\r\n");
                    else break;

                }
            }
            System.out.println(System.currentTimeMillis()- time);
        }
        return  String.valueOf(stringBuilder);
    }

    public static String innerJoinHashMap(HashMap<Integer,String> tableA, HashMap<Integer,String> tableB){

        long time = System.currentTimeMillis();

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("ID A.VALUE B.VALUE"+"\r\n");

        for (Integer integer : tableA.keySet()) {
            for (Integer integer1 : tableB.keySet()) {
                if((tableA.get(integer).split(" "))[0].trim().equals((tableB.get(integer1).split(" "))[0].trim()))
                    stringBuilder.append((tableA.get(integer).split(" "))[0].trim()+"  "+(tableA.get(integer).split(" "))[1].trim()+"       "+(tableB.get(integer1).split(" "))[1].trim()+"\r\n");
                    //System.out.println((tableA.get(integer).split(" "))[0].trim()+" "+(tableA.get(integer).split(" "))[1].trim()+" "+(tableB.get(integer1).split(" "))[1].trim());
            }
        }
        System.out.println(System.currentTimeMillis() - time);
        return String.valueOf(stringBuilder);
    }

}
