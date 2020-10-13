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
    private TextField input_wordRemove;

    @FXML
    private TextField spellingAdd;

    @FXML
    private TextField spellingEdit;

    @FXML TextArea explain_fvr;

    @FXML
    private ListView<String> list_fvr = new ListView<String>();

    @FXML
    private TabPane tab_pane;

    @FXML
    private ListView<String> list_history = new ListView<String>();

    private static DictionaryCommandline HKDIC = new DictionaryCommandline();


    @FXML
    public void lookupSearch() {
        String target = input_word.getText().trim();
        Word word = HKDIC.dictionaryLookup(target);
        explain_content.setText(word.toString());
        if(target.isEmpty()) explain_content.clear();
    }

    @FXML
    void addListSearch() {
        String target = input_word.getText().trim();
        if(!target.isEmpty()) {
            list_search.getItems().setAll(HKDIC.dictionarySearcher(target));
        }
        else{
            list_search.getItems().clear();
        }
    }

    @FXML
    void selectFromListSearch() {
        String target = list_search.getSelectionModel().getSelectedItem().trim();
        if(!target.isEmpty()) {
            Word word = HKDIC.dictionaryLookup(target);
            input_word.setText(target);
            addListSearch();
            explain_content.setText(word.toString());
        }
    }

    @FXML
    void AddNewWord() {
        String word_target = input_wordAdd.getText().trim();
        String word_explain = text_explain.getText().trim();
        String word_spelling = spellingAdd.getText().trim();
        Word word = new Word(word_target, word_explain, word_spelling);
        HKDIC.insertWord(word);
    }

    @FXML
    void ShowTextEdit() {
        String word_target = input_wordEdit.getText().trim();
        Word word = HKDIC.dictionaryLookup(word_target);
        text_explainEdit.setText(word.getWordExplain());
        spellingEdit.setText(word.getWord_spelling());
        if(word_target.isEmpty()) {
            text_explainEdit.clear();
            spellingEdit.clear();
        }

    }

    @FXML
    void EditWord() {
        String word_target = input_wordEdit.getText().trim();
        String word_explain = text_explainEdit.getText().trim();
        String word_spelling = spellingEdit.getText().trim();
        HKDIC.dictionaryEdit(word_target, word_explain, word_spelling);
    }

    @FXML
    void RemoveWord() {
        String w = input_wordRemove.getText().trim();
        HKDIC.dictionaryRemove(w);
    }

    @FXML
    void AddFavorite() {
        String target = input_word.getText().trim();
        if (!target.isEmpty()) {
            list_fvr.getItems().add(target);
        }
    }

    @FXML
    void SelectFromFavorite() {
        String newWord = list_fvr.getSelectionModel().getSelectedItem().trim();
        if(!newWord.isEmpty()) {
            Word word = HKDIC.dictionaryLookup(newWord);
            explain_fvr.setText(word.toString());
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            HKDIC.insertFromFile2();
        } catch (IOException e) {
            e.printStackTrace();
        }

        input_word.setOnKeyPressed(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.ENTER) {
                    lookupSearch();
                }
            }
        });

    }


}