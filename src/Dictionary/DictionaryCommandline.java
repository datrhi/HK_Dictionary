/**
 *   Dictionary Version 2
 *   Nhap tu file & tìm tu
 *   Dang Trung Hieu & Ngo Minh Khanh
 */

package Dictionary;

import java.io.IOException;
import java.util.Scanner;

public class DictionaryCommandline {

    private Dictionary d = new Dictionary();

    public void insert_word() {
        DictionaryManagement dm = new DictionaryManagement();
        dm.insert_form_commandline(d);
    }

    public void insert_file() throws IOException {
        DictionaryManagement dm = new DictionaryManagement();
        dm.insert_from_file(d);
    }

    public void find_word() {
        DictionaryManagement dm = new DictionaryManagement();

        Word w = new Word();
        System.out.print(" Find word: ");
        Scanner sc = new Scanner(System.in);
        String find_word = sc.nextLine();
        w.set_word_target(find_word);

        String result = dm.dictionnary_lookup(d,w);
        if( (result.equals(" Dictionary is empty!") == false) && (result.equals(" Not found!") == false) ) {
            System.out.println(" English: " + w.get_word_target() + " - Vietnamese: " + result);
        }
        else {
            System.out.println(result);
        }

    }

    public void show_all_words() {

        System.out.println("-----------------------------------------\n");
        for(int i = 0; i < d.getDictionary().size(); i++) {
            System.out.println("No " + (i+1) + "      Enlish: " + d.getDictionary().get(i).get_word_target() + "     Vietnamese: " + d.getDictionary().get(i).get_word_explain() );
        }
    }

    public  void dictionary_basic() {
        DictionaryCommandline dc = new DictionaryCommandline();
        dc.insert_word();
        dc.show_all_words();
    }

    public void dictionary_advanced() throws IOException {
        DictionaryCommandline dc = new DictionaryCommandline();
        dc.insert_file();
        dc.show_all_words();
        dc.find_word();
    }

    public static void main(String[] args) throws IOException {
        DictionaryCommandline dcl = new DictionaryCommandline();
        dcl.dictionary_advanced();
    }
}
