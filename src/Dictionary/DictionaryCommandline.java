package Dictionary;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

public class DictionaryCommandline {

    private final Dictionary dictionary = new Dictionary();

    public void insertFromCommandline() {
        DictionaryManagement dm = new DictionaryManagement();
        dm.insertFromCommandline(dictionary);
    }

    public void insertFromFile() throws IOException {
        DictionaryManagement dm = new DictionaryManagement();
        dm.insertFromFile(dictionary);
    }

    public void findWord() {
        DictionaryManagement dm = new DictionaryManagement();
        dm.dictionaryLookup(dictionary);
    }

    public void dictionaryRemove() {
        DictionaryManagement dm = new DictionaryManagement();
        dm.dictionaryRemove(dictionary);
    }

    public void dictionaryExportToFile() throws FileNotFoundException, UnsupportedEncodingException {
        DictionaryManagement dm = new DictionaryManagement();
        dm.dictionaryExportToFile(dictionary);
    }

    public void dictionarySearcher() {

    }

    public void showAllWords() {
        System.out.printf("%-5s%-12s%s\n","No","English  |","Vietnamese");
        for(int i = 0; i < dictionary.getDictionary().size(); i++) {
            System.out.printf("%-5d%-11s%s\n",(i+1),dictionary.getDictionary().get(i).getWordTarget(),
                    dictionary.getDictionary().get(i).getWordExplain());
        }
    }

    public void dictionaryBasic() {
        DictionaryCommandline dc = new DictionaryCommandline();
        dc.insertFromCommandline();
        dc.showAllWords();
    }

    public void dictionaryAdvanced() throws IOException {
        DictionaryCommandline dc = new DictionaryCommandline();
        dc.insertFromFile();
        dc.showAllWords();
        dc.findWord();
        dc.dictionaryRemove();
        dc.showAllWords();
        dc.dictionaryExportToFile();
    }

    public static void main(String[] args) throws IOException {
        DictionaryCommandline dcl = new DictionaryCommandline();
        dcl.dictionaryAdvanced();
    }
}
