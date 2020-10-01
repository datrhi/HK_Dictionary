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

        Word w = new Word();
        System.out.print(" Find word: ");
        Scanner sc = new Scanner(System.in);
        String findWord = sc.nextLine();
        w.setWordTarget(findWord);

        String result = dm.dictionaryLookup(dictionary,w);
        if( (!result.equals(" Dictionary is empty!")) && (!result.equals(" Not found!")) ) {
            System.out.println(" English: " + w.getWordTarget() + " - Vietnamese: " + result);
        }
        else {
            System.out.println(result);
        }

    }

    public void showAllWords() {

        System.out.println("-----------------------------------------\n");
        for(int i = 0; i < dictionary.getDictionary().size(); i++) {
            System.out.println("No " + (i+1) + "      Enlish: " + dictionary.getDictionary().get(i).getWordTarget() + "     Vietnamese: " + dictionary.getDictionary().get(i).getWordExplain() );
        }
    }

    public  void dictionaryBasic() {
        DictionaryCommandline dc = new DictionaryCommandline();
        dc.insertFromCommandline();
        dc.showAllWords();
    }

    public void dictionary_advanced() throws IOException {
        DictionaryCommandline dc = new DictionaryCommandline();
        dc.insertFromFile();
        dc.showAllWords();
        dc.findWord();
    }

    public static void main(String[] args) throws IOException {
        DictionaryCommandline dcl = new DictionaryCommandline();
        dcl.dictionary_advanced();
    }
}
