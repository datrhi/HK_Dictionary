package Dictionary;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.nio.file.Paths;
import java.io.IOException;
import java.io.PrintWriter;

public class DictionaryManagement {


    //protected Dictionary dictionary = new Dictionary();

    /*
    public DictionaryManagement(){
        this.dictionary = dictionary;
    }

    /*
    DictionaryManagement(Dictionary dictionary) {
        this.dictionary = dictionary;
    }
    */
    public void showAllWords(Dictionary dictionary) {
        System.out.printf("%-5s%-12s%s\n","No","English  |","Vietnamese");
        for(int i = 0; i < dictionary.getDictionary().size(); i++) {
            System.out.printf("%-5d%-11s%s\n",(i+1),dictionary.getDictionary().get(i).getWordTarget(),
                    dictionary.getDictionary().get(i).getWordExplain());
        }
    }

    public void insertFromCommandline(Dictionary dictionary) {

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

            ArrayList<Word> newDic = dictionary.getDictionary();
            newDic.add(w);
            Collections.sort(newDic);  // soft
            dictionary.setDictionary(newDic);

        }
    }

    public void insertFromFile(Dictionary dictionary) throws IOException {

        Scanner read_file = new Scanner(Paths.get("C:\\Users\\Admin\\Documents\\uetdic\\dictionaries.txt"), "UTF-8");
        while (read_file.hasNextLine()) {
            Word w = new Word();

            String word_target = read_file.next();
            w.setWordTarget(word_target);

            String word_explain = read_file.nextLine();
            w.setWordExplain(word_explain);

            ArrayList<Word> newDic = dictionary.getDictionary();
            newDic.add(w);
            Collections.sort(newDic); // sort
            dictionary.setDictionary(newDic);
        }
        read_file.close();

    }


    public int binaryLookup(int start, int end, String word, Dictionary dictionary) {
        if(end < start) {
            return -1;
        }
        int mid = start + (end - start) / 2;
        int compare = word.compareToIgnoreCase(dictionary.getDictionary().get(mid).getWordTarget());
        if(compare == 0) {
            return mid;
        }
        if(compare < 0) {
            return binaryLookup(start,mid-1,word,dictionary);
        }
        return binaryLookup(mid+1,end,word,dictionary);
    }

    public void dictionaryLookup(Dictionary dictionary) {

        System.out.print(" Find word: ");
        Scanner sc = new Scanner(System.in);
        String word = sc.nextLine();
        //sc.close();


        if (dictionary.getDictionary().size() == 0) {
            System.out.println(" Dictionary is empty!");
            return;
        }
        int index = binaryLookup(0,dictionary.getDictionary().size()-1,word,dictionary);
        if( index >= 0) {
            System.out.printf("%-12s%s\n","English  |","Vietnamese");
            System.out.printf("%-11s%s\n",word,dictionary.getDictionary().get(index).getWordExplain());
            return;
        }
        System.out.println(" Not found!");
        return;
    }

    public void dictionaryRemove(Dictionary dictionary) {
        System.out.print(" Nhap tu muon xoa: ");
        Scanner sc = new Scanner(System.in);
        String word = sc.nextLine();

        int index = binaryLookup(0,dictionary.getDictionary().size()-1,word,dictionary);
        if( index >= 0) {
            dictionary.getDictionary().remove(index);
            return;
        }
        System.out.println(" Not found!");
        return;
    }


    public void dictionaryExportToFile(Dictionary dictionary) throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter printWriter = new PrintWriter("dictionaris(copy).txt", "UTF-8");
        for (int i = 0; i < dictionary.getDictionary().size(); i++) {
            printWriter.printf("%-11s%s\n",dictionary.getDictionary().get(i).getWordTarget(),
                    dictionary.getDictionary().get(i).getWordExplain());
        }
        printWriter.close();
    }


    public int binarySearch(int start, int end, String word,Dictionary dictionary) {

        if(start > end) {
            return -1;
        }
        int mid = start + (end - start) / 2;
        String midWord = dictionary.getDictionary().get(mid).getWordTarget();
        if (midWord.startsWith(word)) {
            return mid;
        }
        int compare = word.compareToIgnoreCase(midWord);
        if (compare < 0) return binarySearch(start, mid - 1, word,dictionary);
        return binarySearch(mid + 1, end, word,dictionary);
    }


}
