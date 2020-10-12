package app.Dictionary;

//import java.io.FileNotFoundException;
//import java.io.UnsupportedEncodingException;
//import java.io.IOException;
//import java.io.PrintWriter;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.*;
import java.nio.file.Paths;
import java.io.*;
import java.util.TreeSet;


public class DictionaryManagement extends Dictionary {


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

//        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File("dictionaries.txt");
        Scanner read_file = new Scanner(file);
        //   Scanner read_file = new Scanner(Paths.get("D:\\BTL_OOP\\uetdic\\src\\main\\resources\\dictionaries.txt"), "UTF-8");
        while (read_file.hasNextLine()) {
            String[] word = read_file.nextLine().split("\t");
            Word w = new Word(word[0], word[1].trim());
            dictionary.add(w);
        }
        read_file.close();

    }


    public Word dictionaryLookup(String word) {
        Word w = new Word(word);
        TreeSet<Word> listWord = (TreeSet<Word>) dictionary.subSet(w, new Word(word + "z"));
        Iterator<Word> iterator = listWord.iterator();
        if (iterator.hasNext()) {
            Word s = iterator.next();
            if (s.getWordTarget().equals(word)) return s;
        }
        return new Word(word, "Not found!");
    }

    public String dictionaryRemove(String word) {
        word.trim();
        word.replaceAll("/t", "");
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

    /**
     * insert file test
     */
    public void insertFromFile2() throws IOException {
        try {
            String file = new String(Files.readAllBytes(Paths.get("AnhViet.txt")), StandardCharsets.UTF_8);
            String[] word = file.split("@");
            for (String w : word) {
                String list[] = w.split("\r?\n", 2);
                if (list.length > 1) {
                    String word_explain = new String();
                    String word_target = new String();
                    String word_spelling = new String();
                    if (list[0].contains("/")) {
                        word_target = list[0].substring(0, list[0].indexOf("/"));
                        word_spelling = list[0].substring(list[0].indexOf("/"), list[0].length());
                    } else {
                        word_target = list[0];
                        word_spelling = "";
                    }
                    word_explain = list[1];
                    dictionary.add(new Word(word_target,word_explain,word_spelling));
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }


}
