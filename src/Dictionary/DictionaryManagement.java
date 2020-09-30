package Dictionary;

import java.util.ArrayList;
//import java.util.List;
import java.util.Scanner;

public class DictionaryManagement {

    public void insert_form_commandline(Dictionary d) {

        Scanner sc = new Scanner(System.in);
        System.out.print(" So tu can nhap: ");
        int n = sc.nextInt();
        System.out.print("-------------------------------");
        sc.nextLine();

        for(int i = 0; i < n; i++) {

            Word w = new Word();

            System.out.print("\n Nhap tu moi " + (i+1) + ": ");
            String word_target = sc.nextLine();
            //sc.nextLine();
            w.set_word_target(word_target);

            System.out.print(" Nhap giai nghia " + (i+1) + ": ");
            String word_explain = sc.nextLine();
            //sc.nextLine();
            w.set_word_explain(word_explain);

            ArrayList<Word> newDic = d.getDictionary();
            newDic.add(w);
            d.setDictionary(newDic);
        }

    }
}
