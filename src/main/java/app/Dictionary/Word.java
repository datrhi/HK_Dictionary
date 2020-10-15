package app.Dictionary;

public class Word implements Comparable<Word> {

    private String word_target;
    private String word_explain;
    private String word_spelling;
    private boolean isFavor;

    /**  Kiem tra co phai tu yeu thich khong
     *
     * @return True: tu yeu thich. False: ko phai tu yeu thich
     */
    public boolean isFavor() {
        return isFavor;
    }

    /**------------ Phương thức khởi tạo. ---------------*/
    Word() {
        word_target = "";
        word_explain = "";
    }

    public Word(String word_target, String word_explain) {
        this.word_target = word_target;
        this.word_explain = word_explain;
    }

    public Word(String word_target, String word_explain, String word_spelling) {
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
    //------------------ End. ---------------------------//



    /**----------------- Setter & Getter. ------------------*/
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

    public void setFavor(boolean favor) {
        isFavor = favor;
    }
    //------------ End. -----------------//



    /**   So sánh.
     *
     * @param word từ
     */
    @Override
    public int compareTo(Word word) {
        return this.getWordTarget().compareToIgnoreCase(word.getWordTarget());
    }

    /**
     *   thông tin 1 từ khi dc in ra.
     */
    public String toString() {
        return getWordTarget() + "    " + getWord_spelling()+"\n" + getWordExplain() + "\n";
    }


}
