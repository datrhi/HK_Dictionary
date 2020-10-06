package Dictionary;

import java.io.IOException;
import java.util.Scanner;

public class DictionaryCommandline {

    private Dictionary dictionary = new Dictionary();

    public void dictionarySearcher() {
        Dictionary newDic = new Dictionary();
        System.out.print(" Nhap cac chu cai tu can tim: ");
        Scanner sc = new Scanner(System.in);
        String word = sc.nextLine();
        int index = dictionary.binarySearch(0,dictionary.getDictionary().size()-1,word,dictionary);

        if(index < 0) {
            System.out.println(" Not found!");
        }
        if(index >= 0) {
            newDic.getDictionary().add(dictionary.getDictionary().get(index));

            int left = index - 1;
            int right = index + 1;

            while(left >= 0) {
                Word leftWord = dictionary.getDictionary().get(left);
                if (leftWord.getWordTarget().startsWith(word)) {
                    newDic.getDictionary().add(leftWord);
                    left--;
                }
                else
                    break;
            }

            while(right <= dictionary.getDictionary().size()-1) {
                Word rightWord = dictionary.getDictionary().get(right);
                if (rightWord.getWordTarget().startsWith(word)) {
                    newDic.getDictionary().add(rightWord);
                    right++;
                }
                else
                    break;
            }
        }
        newDic.showAllWords(newDic);
    }

    public void dictionaryBasic() {
        dictionary.insertFromCommandline(dictionary);
        dictionary.showAllWords(dictionary);
    }

    public void dictionaryAdvanced() throws IOException {
        dictionary.insertFromFile(dictionary);
        dictionary.showAllWords(dictionary);
        dictionary.dictionaryLookup(dictionary);
        dictionary.dictionaryRemove(dictionary);
        dictionary.showAllWords(dictionary);
        dictionary.dictionaryExportToFile(dictionary);
    }

    public static void main(String[] args) throws IOException {
        DictionaryCommandline dcl = new DictionaryCommandline();
        dcl.dictionaryAdvanced();
        dcl.dictionarySearcher();
    }
}
