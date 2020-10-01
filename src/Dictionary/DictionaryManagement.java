package Dictionary;

import java.util.ArrayList;
import java.util.Scanner;
import java.nio.file.Paths;
import java.io.IOException;

public class DictionaryManagement {

    public void insertFromCommandline(Dictionary dictionary) {

        Scanner sc = new Scanner(System.in);
        System.out.print(" So tu can nhap: ");
        int n = sc.nextInt();
        System.out.print("-------------------------------");
        sc.nextLine();

        for (int i = 0; i < n; i++) {

            Word w = new Word();

            System.out.print("\n Nhap tu moi " + (i + 1) + ": ");
            String word_target = sc.nextLine();
            //sc.nextLine();
            w.setWordTarget(word_target);

            System.out.print(" Nhap giai nghia " + (i + 1) + ": ");
            String word_explain = sc.nextLine();
            //sc.nextLine();
            w.setWordExplain(word_explain);

            ArrayList<Word> newDic = dictionary.getDictionary();
            newDic.add(w);
            dictionary.setDictionary(newDic);
        }
    }

    public void insertFromFile(Dictionary dictionary) throws IOException {

        Scanner read_file = new Scanner(Paths.get("C:\\Users\\Admin\\Documents\\uetdic\\dictionaries.txt"), "UTF-8");
        while (read_file.hasNextLine()) {
            Word w = new Word();

            String word_target = read_file.next();
            w.setWordTarget(word_target);

            String word_explain = read_file.nextLine();
            w.setWordExplain(word_explain);

            ArrayList<Word> newDic = dictionary.getDictionary();
            newDic.add(w);
            dictionary.setDictionary(newDic);
        }
        read_file.close();
    }

    /**
     *
     */
    public Word dictionaryLookup(Dictionary dictionary) {
        Word word = new Word();
        System.out.print(" Find word: ");
        Scanner sc = new Scanner(System.in);
        String findWord = sc.nextLine();
        word.setWordTarget(findWord);

        if (dictionary.getDictionary().size() == 0) {
            word.setWordExplain(" Dictionary is empty!");
            return word;
        }
        for (int i = 0; i < dictionary.getDictionary().size(); i++) {
            if (dictionary.getDictionary().get(i).getWordTarget().equals(word.getWordTarget())) {
                word.setWordExplain( dictionary.getDictionary().get(i).getWordExplain() );
                return word;
            }
        }
        word.setWordExplain(" Not found!");
        return word;
    }

}
