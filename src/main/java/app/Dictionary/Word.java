package app.Dictionary;

public class Word implements Comparable<Word> {

    private String word_target;
    private String word_explain;
    private String word_spelling;

    private boolean isFavor;
    public boolean isFavor() {
        return isFavor;
    }
    //constructor
    Word() {
        word_target = "";
        word_explain = "";
    }

    public Word(String word_target, String word_explain) {
        this.word_target = word_target;
        this.word_explain = word_explain;
    }

    Word(String word_target, String word_explain, String word_spelling) {
        this.word_target = word_target;
        this.word_spelling = word_spelling;
        this.word_explain = word_explain;
    }

    Word(Word w) {
        this.word_target = w.word_target;
        this.word_explain = w.word_explain;
    }

    Word(String word_target) {
        this.word_target = word_target;
    }

    //getter & setter
    public void setWordTarget(String word_target) {
        this.word_target = word_target;
    }

    public void setWordExplain(String word_explain) {
        this.word_explain = word_explain;
    }

    public String getWord_spelling() {
        return word_spelling;
    }

    public void setWord_spelling(String word_spelling) {
        this.word_spelling = word_spelling;
    }

    public String getWordTarget() {
        return word_target;
    }

    public String getWordExplain() {
        return word_explain;
    }

    @Override
    public int compareTo(Word word) {
        return this.getWordTarget().compareToIgnoreCase(word.getWordTarget());
    }

    public String toString() {
        return getWordTarget() + "    " + getWord_spelling()+"\n" + getWordExplain() + "\n";
    }


}
