package app.Dictionary;

//import java.io.FileNotFoundException;
//import java.io.UnsupportedEncodingException;
//import java.io.IOException;
//import java.io.PrintWriter;
import java.util.*;
import java.nio.file.Paths;
import java.io.*;
import java.util.TreeSet;


public class DictionaryManagement extends Dictionary{


    public void insertFromCommandline() {

        Scanner sc = new Scanner(System.in);
        System.out.print(" So tu can nhap: ");
        int n = sc.nextInt();
        System.out.print("-------------------------------");
        sc.nextLine();

        for (int i = 0; i < n; i++) {

            Word w = new Word();

            System.out.print("\n Nhap tu moi " + (i + 1) + ": ");
            String word_target = sc.nextLine();
            w.setWordTarget(word_target);

            System.out.print(" Nhap giai nghia " + (i + 1) + ": ");
            String word_explain = sc.nextLine();
            w.setWordExplain(word_explain);

            dictionary.add(w);

        }
    }

    public void insertFromFile() throws IOException {

        Scanner read_file = new Scanner(Paths.get("C:\\Users\\TNC\\Desktop\\uetdic\\dictionaries.txt"), "UTF-8");
        while (read_file.hasNextLine()) {
            Word w = new Word();

            String word_target = read_file.next();
            w.setWordTarget(word_target);

            String word_explain = read_file.nextLine();
            w.setWordExplain(word_explain);

            dictionary.add(w);
        }
        read_file.close();

    }

    public Word dictionaryLookup(String word) {
        Word w = new Word(word);
        TreeSet<Word> listWord = (TreeSet<Word>) dictionary.subSet(w,new Word(w+"z"));
        Iterator<Word> iterator =listWord.iterator();
        if(iterator.hasNext()) {
            Word s = iterator.next();
            if(s.getWordTarget().equals(word)) return s;
        }
        return new Word(w);
    }

    public String dictionaryRemove(String word) {
        word.trim();
        word.replaceAll("/t","");
        Word w = new Word(word);
        if (word.equals("")) return "Type a word";
        if (!dictionary.contains(w)) return "Not found!";
        dictionary.remove(w);
        removedWord.add(w.getWordTarget());
        if (history.contains(w.getWordTarget())) history.remove(w.getWordTarget());
        if (favor.contains(w.getWordTarget())) favor.remove(w.getWordTarget());
        return "Done!";
    }


    public void dictionaryExportToFile() throws IOException {
        FileWriter fw = new FileWriter("dictionary.txt");
        for (Word w : dictionary) fw.write(w.toString());
        fw.close();
    }




}
