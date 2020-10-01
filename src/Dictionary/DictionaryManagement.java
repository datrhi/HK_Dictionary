package Dictionary;

import java.util.ArrayList;
import java.util.Scanner;
import java.nio.file.Paths;
import java.io.IOException;

public class DictionaryManagement {

    public void insert_form_commandline(Dictionary d) {

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
            w.set_word_target(word_target);

            System.out.print(" Nhap giai nghia " + (i + 1) + ": ");
            String word_explain = sc.nextLine();
            //sc.nextLine();
            w.set_word_explain(word_explain);

            ArrayList<Word> newDic = d.getDictionary();
            newDic.add(w);
            d.setDictionary(newDic);
        }
    }

    public void insert_from_file(Dictionary d) throws IOException {

        Scanner read_file = new Scanner(Paths.get("C:\\Users\\Admin\\Documents\\uetdic\\dictionaries.txt"), "UTF-8");

        while (read_file.hasNextLine()) {
            Word w = new Word();

            String word_target = read_file.next();
            w.set_word_target(word_target);

            String word_explain = read_file.nextLine();
            w.set_word_explain(word_explain);

            ArrayList<Word> newDic = d.getDictionary();
            newDic.add(w);
            d.setDictionary(newDic);
        }
        read_file.close();
    }

    /**
     *
     */
    public String dictionnary_lookup(Dictionary d, Word word) {
        //int s;

        if (d.getDictionary().size() == 0) {
            return " Dictionary is empty!";
        }
        for (int i = 0; i < d.getDictionary().size(); i++) {
            if (d.getDictionary().get(i).get_word_target().equals(word.get_word_target()) == true) {
                word.set_word_explain( d.getDictionary().get(i).get_word_explain() );
                return d.getDictionary().get(i).get_word_explain();
            }
        }
        return " Not found!";
    }

}
