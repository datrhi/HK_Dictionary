package Dictionary;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Scanner;
import java.nio.file.Paths;
import java.io.IOException;
import java.io.PrintWriter;

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
    public void dictionaryLookup(Dictionary dictionary) {

        System.out.print(" Find word: ");
        Scanner sc = new Scanner(System.in);
        String findWord = sc.nextLine();
        //sc.close();

        if (dictionary.getDictionary().size() == 0) {
            System.out.println(" Dictionary is empty!");
            return;
        }
        for (int i = 0; i < dictionary.getDictionary().size(); i++) {
            if (dictionary.getDictionary().get(i).getWordTarget().equals(findWord)) {
                System.out.printf("%-12s%s\n","English  |","Vietnamese");
                System.out.printf("%-11s%s\n",findWord,dictionary.getDictionary().get(i).getWordExplain());
                return;
            }
        }
        System.out.println(" Not found!");
        return;
    }

    public void dictionaryRemove(Dictionary dictionary) {
        System.out.print(" Nhap tu muon xoa: ");
        Scanner sc = new Scanner(System.in);
        String removedWord = sc.nextLine();

        for(int i = 0; i < dictionary.getDictionary().size(); i++) {
            if(dictionary.getDictionary().get(i).getWordTarget().equals(removedWord)) {
                dictionary.getDictionary().remove(i);
            }
        }
        return;
    }


    public void dictionaryExportToFile(Dictionary dictionary) throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter printWriter = new PrintWriter("dictionaris(copy).txt", "UTF-8");
        for (int i = 0; i < dictionary.getDictionary().size(); i++) {
            printWriter.printf("%-11s%s\n",dictionary.getDictionary().get(i).getWordTarget(),
                    dictionary.getDictionary().get(i).getWordExplain());
        }
        printWriter.close();
    }
}
