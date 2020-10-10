package Dictionary;

import java.util.ArrayList;

public class Dictionary extends DictionaryManagement {

    private ArrayList<Word> dictionary = new ArrayList<Word>();

    /*
    Dictionary() {
        super(dictionary);
    }
    */

    public void setDictionary(ArrayList<Word> dictionary) {
        this.dictionary = dictionary;
    }

    public ArrayList<Word> getDictionary() {
        return dictionary;
    }

    // public void add

}
