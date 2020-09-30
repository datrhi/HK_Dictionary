/**
 *   Dictionary Version 1
 *
 *   Dang Trung Hieu & Ngo Minh Khanh
 */

package Dictionary;

public class DictionaryCommandline {

    private Dictionary d = new Dictionary();

    public void insert_word() {
        DictionaryManagement dm = new DictionaryManagement();
        dm.insert_form_commandline(d);
    }

    public void show_all_words() {

        System.out.println("-----------------------------------------\n");
        for(int i = 0; i < d.getDictionary().size(); i++) {
            System.out.println("No " + (i+1) + "      Enlish: " + d.getDictionary().get(i).get_word_target() + "     Vietnamese: " + d.getDictionary().get(i).get_Word_explain() );
        }
    }

    public  void dictionary_basic() {
        DictionaryCommandline dc = new DictionaryCommandline();
        dc.insert_word();
        dc.show_all_words();
    }

    public static void main(String[] args){
        DictionaryCommandline dcl = new DictionaryCommandline();
        dcl.dictionary_basic();
    }
}
