/**
 *   Dictionary Version 2
 *   Nhap tu file
 *   Dang Trung Hieu & Ngo Minh Khanh
 */

package Dictionary;

import java.io.IOException;

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

    public void show_all_words() {

        System.out.println("-----------------------------------------\n");
        for(int i = 0; i < d.getDictionary().size(); i++) {
            System.out.println("No " + (i+1) + "      Enlish: " + d.getDictionary().get(i).get_word_target() + "     Vietnamese: " + d.getDictionary().get(i).get_Word_explain() );
        }
    }

    public  void dictionary_basic() throws IOException {
        DictionaryCommandline dc = new DictionaryCommandline();
        dc.insert_file();
        dc.show_all_words();
    }

    public static void main(String[] args) throws IOException {
        DictionaryCommandline dcl = new DictionaryCommandline();
        dcl.dictionary_basic();
    }
}
