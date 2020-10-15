package app.Dictionary;

import com.sun.deploy.association.Action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.TreeSet;

public class DictionaryCommandline extends DictionaryManagement {

    /**
     *  In tat ca cac tu trong tu dien.
     */
    public static void showAllWords()
    {
        System.out.printf("No        | English             | Vietnamese\n\n");
        int index = 0;
        for (Word w : dictionary)
        {
            index++;
            System.out.printf("%-10d| %-20s| %s\n\n", index, w.getWordTarget(), w.getWordExplain());
        }
    }

    /**   Tim cac tu tuong tu.
     *
     * @param word từ tiếng anh
     * @return arraylist các từ tương tự
     */
    public ArrayList<String> dictionarySearcher(String word) {
        word = word.trim();
        Word w = new Word(word);
        TreeSet<Word> listWord = (TreeSet<Word>) dictionary.subSet(w,new Word(word+"z"));
        ArrayList<String> listSearch = new ArrayList<String>();
        if(!listWord.isEmpty()) {
            for (Word word1: listWord) {
                listSearch.add(word1.getWordTarget());
            }
        }
        return listSearch;
    }



    public static void instruction()
    {
        System.out.print("WELLCOME !!!\n");
        System.out.print("[0] Instruction\n");
        System.out.print("[1] Lookup\n[2] Search a word\n[3] Show All Words\n");
        System.out.print("[4] Add a word\n[5] Remove a word\n[6] Edit a word\n[7] Export to file\n[8] Exit\n");
    }

    public void dictionaryBasic() {
        insertFromCommandline();
        showAllWords();
    }

    public static void dictionaryAdvanced( ) throws IOException
    {
        instruction();
        DictionaryCommandline dc = new DictionaryCommandline();
        dc.insertFromFile2();
        int input;
        String w,w1,w2;
        Scanner sc = new Scanner(System.in);
        boolean exit = false;
        while (!exit)
        {
            input = sc.nextInt();
            sc.nextLine();
            switch (input)
            {
                case 0 :
                    instruction();
                    break;
                case 1 :
                    System.out.print("- Lookup -\n Write a word: ");
                    w = sc.nextLine();
                    if (!dc.dictionaryLookup(w).getWordExplain().equals("Not found"))
                        System.out.println(dc.dictionaryLookup(w).toString());
                    else System.out.println(w);
                    break;
                case 2 :
                    System.out.print("- Search -\n Write a word: ");
                    w = sc.nextLine();
                    ArrayList<String> arrayList = dc.dictionarySearcher(w);
                    for (String s : arrayList) System.out.println(s);
                    break;
                case 3 :
                    showAllWords();
                    break;
                case 4 :
                    System.out.print("- Add -\n Write a word in english: ");
                    w = sc.nextLine();
                    System.out.print(" Write the spelling of word: ");
                    w1 = sc.nextLine();
                    System.out.print(" Write the meaning of word: ");
                    w2 = sc.nextLine();
                    System.out.println(dc.insertWord(w, w1, w2));
                    break;
                case 5 :
                    System.out.print("- Remove -\nWrite a word: ");
                    w = sc.nextLine();
                    System.out.println(dc.dictionaryRemove(w));
                    break;
                case 6 :
                    System.out.print("- Edit -\n Write a word in english: ");
                    w = sc.nextLine();
                    System.out.print(" Write the spelling of word: ");
                    w1 = sc.nextLine();
                    System.out.print(" Write the meaning of word: ");
                    w2 = sc.nextLine();
                    System.out.println(dc.dictionaryEdit2(w,w2,w1));
                    break;
                case 7 :
                    dc.dictionaryExportToFile();
                    System.out.println("- Export -\n Export Successfully!");
                    break;
                case 8 :
                    exit = true;
                    break;
            }

        }
    }

    public static void main(String[] args) throws IOException {
        DictionaryCommandline dc = new DictionaryCommandline();
        dc.dictionaryAdvanced();
    }
}
