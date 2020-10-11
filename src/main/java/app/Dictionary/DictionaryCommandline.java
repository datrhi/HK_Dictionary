package app.Dictionary;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.TreeSet;

public class DictionaryCommandline extends DictionaryManagement {

    public static void showAllWords()
    {
        System.out.printf("No        | English             | Vietnamese\n\n");
        int index = 0;
        for (Word w : dictionary)
        {
            index++;
            System.out.printf("%-10d| %-20s| %s\n", index, w.getWordTarget(), w.getWordExplain());
        }
    }

    public ArrayList<String> dictionarySearcher(String word) {
        Word w = new Word(word);
        TreeSet<Word> listWord = (TreeSet<Word>) dictionary.subSet(w,new Word(word+"z"));
        ArrayList<String> listSearch = new ArrayList<String>();
        if(!listWord.isEmpty()) {
            for (Word word1: listWord) {
                listSearch.add(word1.getWordTarget());
            }
        }
        return listSearch;
    }

    public void dictionaryBasic() {
        insertFromCommandline();
        showAllWords();
    }

    public void dictionaryAdvanced() throws IOException {
        insertFromFile();
        showAllWords();
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhap tu muon tim:");
        String word = sc.nextLine();
        System.out.println(dictionaryLookup(word).toString());
    }

}
