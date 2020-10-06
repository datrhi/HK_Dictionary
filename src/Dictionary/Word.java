package Dictionary;

public class Word implements Comparable<Word>{

    private String word_target;
    private String word_explain;

    Word() {
        word_target = "";
        word_explain = "";
    }

    Word (String word_target, String word_explain) {
        this.word_target = word_target;
        this.word_explain = word_explain;
    }

    Word (Word w) {
        this.word_target = w.word_target;
        this.word_explain = w.word_explain;
    }

    public void setWordTarget (String word_target) {
        this.word_target = word_target;
    }

    public void setWordExplain (String word_explain) {
        this.word_explain = word_explain;
    }

    public String getWordTarget() {
        return word_target;
    }

    public String getWordExplain() {
        return word_explain;
    }

    /*
    @Override
    public int compareTo(Word word) {
        // sort student's name by ASC
        return this.getWordTarget().compareTo(word.getWordTarget());
    }

     */

    @Override
    public int compareTo(Word word) {
        return this.getWordTarget().compareToIgnoreCase(word.getWordTarget());
    }
}
