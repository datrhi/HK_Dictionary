package app.Controller;

import app.Dictionary.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import sun.awt.HKSCS;
//import android.widget.ArrayAdapter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.List;

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
    private Button btn_saveRemove;

    @FXML
    private ToggleButton btn_fvr;

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


    /**---------------------- Lookup. -------------------------*/
    @FXML
    public void lookupSearch() {
        String target = input_word.getText().trim();
        Word word = HKDIC.dictionaryLookup(target);
        explain_content.setText(word.toString());
        if (HKDIC.history.contains(target)) HKDIC.history.remove(target);
        HKDIC.history.addFirst(target);
        btn_fvr.setSelected(word.isFavor());
    }

    @FXML
    void addListSearch() {
        String target = input_word.getText().trim();
        if(!target.isEmpty()) {
            list_search.getItems().setAll(HKDIC.dictionarySearcher(target));
        }
        else{
            list_search.getItems().clear();
            explain_content.clear();
        }
    }

    @FXML
    void selectFromListSearch(MouseEvent event) {
        String target = list_search.getSelectionModel().getSelectedItem();
        if(target != null) {
            input_word.setText(target);
            addListSearch();
            Word word = HKDIC.dictionaryLookup(target);
            explain_content.setText(word.toString());
            if (HKDIC.history.contains(target)) HKDIC.history.remove(target);
            HKDIC.history.addFirst(target);
        }
    }
    //-End.-//


    /**---------------------- Edit. -------------------------*/
    @FXML
    void addListEdit() {
        String target = input_wordEdit.getText().trim();
        if(!target.isEmpty()) {
            Word word = HKDIC.dictionaryLookup(target);
            text_spellingEdit.setText(word.getWord_spelling());
            text_explainEdit.setText(word.getWordExplain());
            list_searchEdit.getItems().setAll(HKDIC.dictionarySearcher(target));
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
            Word word = HKDIC.dictionaryLookup(target);
            input_wordEdit.setText(target);
            addListEdit();
            text_explainEdit.setText(word.getWordExplain());
            text_spellingEdit.setText(word.getWord_spelling());
        }
    }

    @FXML
    void EditWord() {
        String word_target = list_searchEdit.getSelectionModel().getSelectedItem().trim();
        String word_explain = text_explainEdit.getText().trim();
        String word_spelling = text_spellingEdit.getText().trim();
        label_sttEdit.setText(HKDIC.dictionaryEdit2(word_target,word_explain,word_spelling));
    }
    //-End.-//


    /**---------------------- Adđ. -------------------------*/
    @FXML
    void AddNewWord() {
        String word_target = input_wordAdd.getText().trim();
        String word_explain = text_explain.getText().trim();
        String word_spelling = text_spelling.getText().trim();
        label_stt.setText(HKDIC.insertWord(word_target,word_spelling,word_explain));
        if(word_target.isEmpty()){
            text_spelling.clear();
            text_explain.clear();
        }
    }
    //-End.-//

    /**---------------------- Remove. -------------------------*/
    @FXML
    void AddListRemove() {
        String target = input_wordRemove.getText().trim();
        if(!target.isEmpty()) {
            list_searchRemove.getItems().setAll(HKDIC.dictionarySearcher(target));
        }
        else{
            list_searchRemove.getItems().clear();
        }
    }

    @FXML
    void selectFromListRemove() {
        String target = list_searchRemove.getSelectionModel().getSelectedItem().trim();
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
    }
    //-End.//

    /**---------------------- Favorite List. -------------------------*/
    @FXML
    void addFavorite() {
        list_fvr.getItems().clear();
        for (String w : HKDIC.favor) {
            list_fvr.getItems().add(w);
        }
    }

    @FXML
    void SelectFromListFavorite() {
        String target = list_fvr.getSelectionModel().getSelectedItem().trim();
        if(target != null) {
            Word word = HKDIC.dictionaryLookup(target);
            text_explainFvr.setText(word.toString());
        }
    }
    /*
    @FXML
    void RemoveWordFavorite() {
        String newWord = list_fvr.getSelectionModel().getSelectedItem().trim();
        Word t
     }
     */
    //-End.//


    /**---------------------- Historic List. -------------------------*/
    @FXML
    void addHistory() {
        list_history.getItems().clear();
        for (String w : HKDIC.history) {
            list_history.getItems().add(w);
        }
    }

    @FXML
    void SelectFromListHistory() {
        String target = list_history.getSelectionModel().getSelectedItem().trim();
        if(target != null) {
            Word word = HKDIC.dictionaryLookup(target);
            text_explainHistory.setText(word.toString());
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            HKDIC.insertFromFile2();
        } catch (IOException e) {
            e.printStackTrace();
        }

        btn_fvr.selectedProperty().addListener(((observable, oldValue, newValue) -> {
            String target = input_word.getText();
            Word w = HKDIC.dictionaryLookup(target);
            w.setFavor(newValue);
            if (newValue) {
                if (!HKDIC.favor.contains(target)) HKDIC.favor.addFirst(target);

            } else {
                HKDIC.favor.remove(target);
            }
        }));
        input_word.setOnKeyPressed(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.ENTER) {
                    lookupSearch();
                }
            }
        });
    }

    //    input_wordEdit.setOnKeyPressed(new EventHandler<KeyEvent>() {
//            @Override
//            public void handle(KeyEvent event) {
//                if (event.getCode() == KeyCode.ENTER) {
//                    String word_target = input_wordEdit.getText();
//                    if(HKDIC.dictionaryLookup(word_target).getWordExplain().equals("Not found!")) {
//                        text_explainEdit.setText("This word is not already existed, remember to add it before editing");
//                    } else {
//                        text_explainEdit.setText(HKDIC.dictionaryLookup(word_target).getWordExplain());
//                    }
//                }
//            }
//        });



        //    @FXML
//    void checkStatus() {
//        String newWord = input_wordAdd.getText();
//        newWord.trim();
//        if (newWord.equals("")) {
//            label_stt.setText("");
//            return;
//        }
//        if (HKDIC.dictionaryLookup(newWord).getWordExplain().equals("Not found!")) {
//            label_stt.setText("Can be added");
//        } else {
//            label_stt.setText("This word is already existed!");
//        }
//    }

}