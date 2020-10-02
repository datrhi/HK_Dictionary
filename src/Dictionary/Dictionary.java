package Dictionary;

import java.util.ArrayList;
import java.util.List;

public class Dictionary {

    private ArrayList<Word> Dictionary = new ArrayList<Word>();

    public void setDictionary(ArrayList<Word> dictionary) {
        Dictionary = dictionary;
    }

    public ArrayList<Word> getDictionary() {
        return Dictionary;
    }

}
