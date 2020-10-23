package app.Controller;

import app.Dictionary.DictionaryCommandline;
import app.Dictionary.Word;
import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ResourceBundle;

//import android.widget.ArrayAdapter;

public class Controller implements Initializable {

    @FXML
    private ListView<String> list_search = new ListView<String>();

    @FXML
    private TextField input_wordAdd;

    @FXML
    private Button btn_saveAdd;

    @FXML
    private Button btn_saveEdit;

    @FXML
    private Button btn_speak;

    @FXML
    private Button btn_saveRemove;

    @FXML
    private ToggleButton btn_fvr;

    @FXML
    private ToggleButton btn_API;

    @FXML
    private TextArea explain_content;

    @FXML
    private TextField input_wordEdit;

    @FXML
    private TextField input_word;

    @FXML
    private TextArea text_explain;

    @FXML
    private TextArea text_explainEdit;

    @FXML
    private TextArea text_explainFvr;

    @FXML
    private TextArea text_explainHistory;

    @FXML
    private TextField input_wordRemove;

    @FXML
    private ListView<String> list_fvr = new ListView<String>();

    @FXML
    private TabPane tab_pane;

    @FXML
    private ListView<String> list_history = new ListView<String>();

    private static DictionaryCommandline HKDIC = new DictionaryCommandline();

    //private

    @FXML
    private ListView<String> list_searchEdit;

    @FXML
    private ListView<String> list_searchRemove;

    @FXML
    private Label label_stt;

    @FXML
    private TextField text_spelling;

    @FXML
    private TextField text_spellingEdit;

    @FXML
    private Label label_sttEdit;

    @FXML
    private Label label_sttRemove;

    VoiceManager vm;
    Voice voice;


    /**---------------------- Lookup. -------------------------*/
    @FXML
    public void lookupSearch() throws IOException {
        String target = input_word.getText().trim();
        if (btn_API.isSelected()) {
            String s = translate("en", "vi", target);
            if(s.equals(target))
                explain_content.setText("Not found!");
            else
                explain_content.setText(s);
            Word word = new Word(target);
            if (HKDIC.history.contains(target)) HKDIC.history.remove(target);
            HKDIC.history.addFirst(target);
            btn_fvr.setSelected(word.isFavor());
        }
        else {
            //Word word = HKDIC.dictionaryLookup(target);
            Word word = HKDIC.binaryLookup(0, HKDIC.dictionary.size(), target);
            if(word.getWordExplain().equals("Not found!"))
                explain_content.setText(word.getWordExplain());
            else
                explain_content.setText(word.toString());
            if (HKDIC.history.contains(target)) HKDIC.history.remove(target);
            HKDIC.history.addFirst(target);
            btn_fvr.setSelected(word.isFavor());
        }

    }

    @FXML
    void addListSearch() {
        String target = input_word.getText().trim();
        if(!target.isEmpty()) {
            //list_search.getItems().setAll(HKDIC.dictionarySearcher(target));
            list_search.getItems().setAll(HKDIC.binarySearcher(0, HKDIC.dictionary.size(), target));
        }
        else{
            list_search.getItems().clear();
            explain_content.clear();
        }
    }

    @FXML
    void selectFromListSearch(MouseEvent event) throws IOException {
        String target = list_search.getSelectionModel().getSelectedItem();
        if(target != null) {
            input_word.setText(target);
            if(btn_API.isSelected()) {
                String s = translate("en", "vi", target);
                explain_content.setText(s);
                Word word = new Word(target);
                if (HKDIC.history.contains(target)) HKDIC.history.remove(target);
                HKDIC.history.addFirst(target);
                btn_fvr.setSelected(word.isFavor());
            }
            else {
                //Word word = HKDIC.dictionaryLookup(target);
                Word word = HKDIC.binaryLookup(0, HKDIC.dictionary.size(), target);
                if(word.getWordExplain().equals("Not found!"))
                    explain_content.setText(word.getWordExplain());
                else
                    explain_content.setText(word.toString());
                if (HKDIC.history.contains(target)) HKDIC.history.remove(target);
                HKDIC.history.addFirst(target);
                btn_fvr.setSelected(word.isFavor());
            }
        }
    }
    //------------------------ End. ----------------------------//


    /**---------------------- Edit. -------------------------*/
    @FXML
    void addListEdit() {
        String target = input_wordEdit.getText().trim();
        if(!target.isEmpty()) {
            //Word word = HKDIC.dictionaryLookup(target);
            Word word = HKDIC.binaryLookup(0, HKDIC.dictionary.size(), target);
            text_spellingEdit.setText(word.getWord_spelling());
            text_explainEdit.setText(word.getWordExplain());
            //list_searchEdit.getItems().setAll(HKDIC.dictionarySearcher(target));
            list_searchEdit.getItems().setAll(HKDIC.binarySearcher(0, HKDIC.dictionary.size(), target));
        }
        else{
            list_searchEdit.getItems().clear();
            text_explainEdit.clear();
            text_spellingEdit.clear();
        }
    }

    @FXML
    void selectFromListEdit() {
        String target = list_searchEdit.getSelectionModel().getSelectedItem();
        if(target != null) {
            //Word word = HKDIC.dictionaryLookup(target);
            Word word = HKDIC.binaryLookup(0, HKDIC.dictionary.size(), target);
            input_wordEdit.setText(target);
            addListEdit();
            text_explainEdit.setText(word.getWordExplain());
            text_spellingEdit.setText(word.getWord_spelling());
        }
    }

    @FXML
    void EditWord() {
        String word_target = input_wordEdit.getText().trim();
        String word_explain = text_explainEdit.getText().trim();
        String word_spelling = text_spellingEdit.getText().trim();
        label_sttEdit.setText(HKDIC.dictionaryEdit2(word_target,word_explain,word_spelling));
        input_wordEdit.clear();
        text_spellingEdit.clear();
        text_explainEdit.clear();
        list_searchEdit.getItems().clear();
    }
    //------------------------ End. ----------------------------//


    /**---------------------- Add. -------------------------*/
    @FXML
    void AddNewWord() {
        String word_target = input_wordAdd.getText().trim();
        String word_explain = text_explain.getText().trim();
        String word_spelling = text_spelling.getText().trim();
        label_stt.setText(HKDIC.insertWord(word_target,word_spelling,word_explain));
        input_wordAdd.clear();
        text_spelling.clear();
        text_explain.clear();
    }
    //------------------------ End. ----------------------------//

    /**---------------------- Remove. -------------------------*/
    @FXML
    void AddListRemove() {
        String target = input_wordRemove.getText().trim();
        if(!target.isEmpty()) {
            //list_searchRemove.getItems().setAll(HKDIC.dictionarySearcher(target));
            list_searchRemove.getItems().setAll(HKDIC.binarySearcher(0, HKDIC.dictionary.size(), target));
        }
        else{
            list_searchRemove.getItems().clear();
        }
    }

    @FXML
    void selectFromListRemove() {
        String target = list_searchRemove.getSelectionModel().getSelectedItem();
        if(target != null) {
            input_wordRemove.setText(target);
            AddListRemove();
        }
    }

    @FXML
    void RemoveWord() {
        String target = input_wordRemove.getText().trim();
        label_sttRemove.setText(HKDIC.dictionaryRemove(target));
        list_fvr.getItems().remove(target);
        input_wordRemove.clear();
        list_searchRemove.getItems().clear();
    }
    //------------------------ End. ----------------------------//


    /**---------------------- Favorite List. -------------------------*/
    @FXML
    void addFavorite() {
        list_fvr.getItems().clear();
        list_fvr.getItems().setAll(HKDIC.favor);
    }

    @FXML
    void SelectFromListFavorite() throws IOException {
        String target = list_fvr.getSelectionModel().getSelectedItem();
        if(target != null) {
            //Word word = HKDIC.dictionaryLookup(target);
            Word word = HKDIC.binaryLookup(0, HKDIC.dictionary.size(), target);
            if(!word.getWordExplain().equals("Not found!"))
                text_explainFvr.setText(word.toString());
            else {
                String s = translate("en", "vi", target);
                text_explainFvr.setText(s);
            }
        }
    }
    //------------------------ End. ----------------------------//


    /**------------------------ Speak ------------------------*/
    @FXML
    private void speakBtn() {
        voice.speak(input_word.getText());
        //System.out.print(input_word.getText());
    }
    //------------------------ End. ----------------------------//


    /**---------------------- Historic List. -------------------------*/
    @FXML
    void addHistory() {
        list_history.getItems().clear();
        /*
        for (String w : HKDIC.history) {
            list_history.getItems().add(w);
        }*/
        list_history.getItems().setAll(HKDIC.history);
    }

    @FXML
    void SelectFromListHistory() throws IOException {
        String target = list_history.getSelectionModel().getSelectedItem();
        if(target != null) {
            //Word word = HKDIC.dictionaryLookup(target);
            Word word = HKDIC.binaryLookup(0, HKDIC.dictionary.size(), target);
            if(!word.getWordExplain().equals("Not found!"))
                text_explainHistory.setText(word.toString());
            else {
                String s = translate("en", "vi", target);
                text_explainHistory.setText(s);
            }
        }
    }
    //------------------------ End. ----------------------------//


    /**------------------------ Translate  ------------------------*/

    public String translate(String langFrom, String langTo, String text) throws IOException {
        // INSERT YOU URL HERE
        String urlStr = "https://script.google.com/macros/s/AKfycbw2qKkvobro8WLNZUKi2kGwGwEO4W8cBavcKqcuCIGhGBBtVts/exec" +
                "?q=" + URLEncoder.encode(text, "UTF-8")
                + "&target=" + langTo +
                "&source=" + langFrom;
        URL url = new URL(urlStr);
        StringBuilder response = new StringBuilder();
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return response.toString();
    }
    //------------------------ End. ----------------------------//


    public static void  saveData(){
        try {
            HKDIC.dictionaryExportToFile2();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        String voiceName = "kevin16";
        vm = VoiceManager.getInstance();
        voice = vm.getVoice("kevin16");
        if (voice == null) {
            System.err.println("Cannot find a voice named " + voiceName + ".  Please specify a different voice.");
        } else {
            voice.allocate();
        }

        try {
            HKDIC.insertFromFile3();
        } catch (IOException e) {
            e.printStackTrace();
        }

        btn_fvr.selectedProperty().addListener(((observable, oldValue, newValue) -> {
            String target = input_word.getText();
            //Word w = HKDIC.dictionaryLookup(target);
            Word word = HKDIC.binaryLookup(0, HKDIC.dictionary.size(), target);
            word.setFavor(newValue);
            if (newValue) {
                if (!HKDIC.favor.contains(target)) HKDIC.favor.addFirst(target);

            } else {
                HKDIC.favor.remove(target);
            }
            //btn_fvr.setSelected(false);
        }));

        input_word.setOnKeyPressed(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.ENTER) {
                    try {
                        lookupSearch();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}