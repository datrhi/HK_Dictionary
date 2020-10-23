package app.Dictionary;

//import java.io.FileNotFoundException;
//import java.io.UnsupportedEncodingException;
//import java.io.IOException;
//import java.io.PrintWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.TreeSet;


public class DictionaryManagement extends Dictionary {


    /**   Thêm từ vào từ điển hiện tại.
     *
     * @param word_target: từ tiếng anh
     * @param word_spelling: cách phát âm
     * @param word_explain: nghĩa tiếng việt
     * @return status kiểu string
     */
    public String insertWord(String word_target,String word_spelling,String word_explain) {
        if (word_target.equals("")) return "Type word target!";
        if (dictionary.contains(new Word(word_target))) return "Can not be added";
        if (word_spelling.equals("")) return "Type word spelling with this form : /word_spelling/";
        if (word_explain.equals("")) return "Type word explain";
        Word w = new Word(word_target,word_explain,word_spelling);
        dictionary.add(w);
        editedWord.addFirst(w);
        if (removedWord.contains(w)) removedWord.remove(w);
        return "OK";
    }


    /**
     *  Them tư bang cach su dung commandline.
     */
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
            w.setWordTarget(word_target.trim());

            System.out.print(" Nhap giai nghia " + (i + 1) + ": ");
            String word_explain = sc.nextLine();
            w.setWordExplain(word_explain.trim());

            dictionary.add(w);

        }
    }


    public void insertFromFile1() throws IOException {
        File file = new File("dictionaries.txt");
        Scanner read_file = new Scanner(file);
        while (read_file.hasNextLine()) {
            String[] word = read_file.nextLine().split("\t");
            Word w = new Word(word[0].trim(), word[1].trim());
            dictionary.add(w);
        }
        read_file.close();
    }


    /**    Tìm kiếm chính xác từ muốn kiếm
     *
     * @param word là tu tiếng anh muốn tìm kiểu String
     * @return từ muốn tìm kiểu Word
     */
    public static Word dictionaryLookup(String word) {
        word = word.trim();
        Word w = new Word(word);
        TreeSet<Word> listWord = (TreeSet<Word>) dictionary.subSet(w, new Word(word + "z"));
        Iterator<Word> iterator = listWord.iterator();
        if (iterator.hasNext()) {
            Word s = iterator.next();
            if (s.getWordTarget().equals(word)) return s;
        }
        return new Word(word, "Not found!");
    }


    /**    Tìm kiếm chính xác từ muốn kiếm băng tìm kiếm nhị phân
     *
     * @param word là tu tiếng anh muốn tìm kiểu String
     * @return từ muốn tìm kiểu Word
     */
    public Word binaryLookup(int start, int end, String word) {

        word = word.trim();
        ArrayList<Word> WordArray = new ArrayList<> (dictionary);
        if(end < start) {
            return new Word(word,"Not found!");
        }
        int mid = start + (end - start) / 2;
        int compare = word.compareToIgnoreCase(WordArray.get(mid).getWordTarget());
        if(compare == 0) {
            return WordArray.get(mid);
        }
        if(compare < 0) {
            return binaryLookup(start,mid-1,word);
        }
        return binaryLookup(mid+1,end,word);
    }


    /**  Xóa 1 Word khỏi từ điển hiện tại
     *
     * @param word từ tiếng anh muốn xóa kiểu String
     * @return status kiểu String
     */
    public String dictionaryRemove(String word) {
        Word w = new Word(word);
        if (word.equals("")) return "Type a word";
        if (!dictionary.contains(w)) return "Not found!";
        dictionary.remove(w);
        removedWord.add(w.getWordTarget());
        if (history.contains(w.getWordTarget())) history.remove(w.getWordTarget());
        if (favor.contains(w.getWordTarget())) favor.remove(w.getWordTarget());
        if (editedWord.contains(w))editedWord.remove(word);
        return "Done!";
    }


    /**   Xuất ra file.
     *
     * @throws IOException
     */
    public void dictionaryExportToFile() throws IOException {
        FileWriter fw = new FileWriter("data\\dictionary.txt");
        for (Word w : dictionary) fw.write(w.toString());
        fw.close();
    }

    public void dictionaryExportToFile2() throws IOException {
        FileWriter fw = new FileWriter("data\\history.txt");
        for(String w : history ) fw.write(w+"\n");
        fw.close();
        fw = new FileWriter("data\\favor.txt");
        for(String w : favor ) fw.write(w+"\n");
        fw.close();
        fw = new FileWriter("data\\editedWord.txt");
        for(Word w : editedWord ) fw.write("@" +w.getWordTarget()+" "+w.getWord_spelling()+"\n"+w.getWordExplain()+"\n");
        fw.close();
        fw = new FileWriter("data\\removedWord.txt");
        for(String w : removedWord ) fw.write(w+"\n");
        fw.close();
    }


    /**   Insert từ điển từ file.
     *
     * @throws IOException
     */
    public static void insertFromFile2() throws IOException {
        try {
            String file = new String(Files.readAllBytes(Paths.get("data\\AnhViet.txt")), StandardCharsets.UTF_8);
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
                    dictionary.add(new Word(word_target.trim(),word_explain.trim(),word_spelling.trim()));
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void insertFromFile3() throws IOException
    {
        try {
            String file = new String(Files.readAllBytes(Paths.get("data\\editedWord.txt")), StandardCharsets.UTF_8);
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
                    editedWord.add(new Word(word_target.trim(),word_explain.trim(),word_spelling.trim()));
                    dictionary.add(new Word(word_target.trim(),word_explain.trim(),word_spelling.trim()));
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        Scanner sc = null;
        insertFromFile2();
        File file = new File("data\\favor.txt");
        sc = new Scanner(file);
        while (sc.hasNextLine()){
            String target = sc.nextLine();
            if(target.equals("")) break;
            favor.add(target);
            Word word = dictionaryLookup(target);
            word.setFavor(true);
            dictionary.remove(word);
            dictionary.add(word);
        }
        file = new File("data\\history.txt");
        sc = new Scanner(file);
        while (sc.hasNextLine()){
            String target = sc.nextLine();
            if(target.equals("")) break;
            history.add(target);
        }
        file = new File("data\\removedWord.txt");
        sc = new Scanner(file);
        while (sc.hasNextLine()){
            String target = sc.nextLine();
            if(target.equals("")) break;
            dictionary.remove(new Word(target));
        }
    }


    /**   Edit một Word trong từ điển.
     *
     * @param word_target tư tiếng anh
     * @param word_explain nghia tieng viet
     * @param word_spelling cacn danh van
     */
    public void dictionaryEdit(String word_target, String word_explain, String word_spelling) {
        word_target = word_target.trim();
        word_explain = word_explain.trim();
        Word word = new Word(word_target, word_explain);
        TreeSet<Word> listWord = (TreeSet<Word>) dictionary.subSet(word, new Word(word + "z"));
        Iterator<Word> iterator = listWord.iterator();
        if (iterator.hasNext()) {
            Word s = iterator.next();
            if (s.getWordTarget().equals(word_target)) {
                s.setWordExplain(word_explain);
                s.setWord_spelling(word_spelling);
            }
        }
    }

    public String dictionaryEdit(String word_target, String word_explain) {
        word_target = word_target.trim();
        word_explain = word_explain.trim();
        Word word = new Word(word_target, word_explain);
        TreeSet<Word> listWord = (TreeSet<Word>) dictionary.subSet(word, new Word(word + "z"));
        Iterator<Word> iterator = listWord.iterator();
        if (iterator.hasNext()) {
            Word s = iterator.next();
            if (s.getWordTarget().equals(word_target)) {
                s.setWordExplain(word_explain);
            }
        }
        return word.toString();
    }


    /**   Edit một Word trong tu dien.
     *
     * @param word_target tu tieng anh
     * @param word_explain nghia tieng viet
     * @param word_spelling cach danh van
     * @return status
     */
    public String dictionaryEdit2(String word_target, String word_explain,String word_spelling) {
        if (word_target.equals("")) return "Type word target!";
        if (!dictionary.contains(new Word(word_target))) return "Please add before edit it";
        if (word_spelling.equals("") || (!word_spelling.startsWith("/")||!word_spelling.endsWith("/"))) return "Form spelling is /word_spelling/";
        if (word_explain.equals("")) return "Type word explain";
        Word w = dictionaryLookup(word_target);
        w.setWordExplain(word_explain);
        w.setWord_spelling(word_spelling);
        editedWord.addFirst(w);
        if (removedWord.contains(w)) removedWord.remove(w);
        return "OK";
    }


}
