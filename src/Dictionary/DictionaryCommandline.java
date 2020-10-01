package Dictionary;

import java.io.IOException;
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
        Word result = dm.dictionaryLookup(dictionary);
        if( (!result.getWordExplain().equals(" Dictionary is empty!")) && (!result.getWordExplain().equals(" Not found!")) ) {
            showWord(result);
        }
        else {
            System.out.println(result.getWordExplain());
        }

    }
    public void showWord(Word word) {
        System.out.println(" English: " + word.getWordTarget() + " - Vietnamese: " + word.getWordExplain() );
    }

    public void showAllWords() {

        System.out.println("-----------------------------------------\n");
        for(int i = 0; i < dictionary.getDictionary().size(); i++) {
            System.out.print("No " + (i+1) );
            showWord(dictionary.getDictionary().get(i)) ;
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
    }

    public static void main(String[] args) throws IOException {
        DictionaryCommandline dcl = new DictionaryCommandline();
        dcl.dictionaryAdvanced();
    }
}
