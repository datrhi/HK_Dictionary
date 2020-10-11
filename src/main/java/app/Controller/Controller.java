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

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

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
    private ToggleButton btn_fvr;

    @FXML
    private TextArea explain_content;

    @FXML
    private TextField input_wordEdit;

    @FXML
    private TextArea text_explain;

    @FXML
    private TextArea text_explainEdit;

    @FXML
    private TextField input_word;

    @FXML
    private ListView<String> list_fvr = new ListView<String>();

    @FXML
    private TabPane tab_pane;

    @FXML
    private ListView<String> list_history = new ListView<String>();

    private static DictionaryCommandline HKDIC = new DictionaryCommandline();


    @FXML
    void addListSearch(ActionEvent event) {

    }
    @FXML
    void AddNewWord(ActionEvent event) {

    }

    public void lookupSearch() {
        String target = input_word.getText();
        Word word = HKDIC.dictionaryLookup(target);
        if (HKDIC.history.contains(target)) HKDIC.history.remove(target);
        HKDIC.history.addFirst(target);
        explain_content.setText(word.toString());
        btn_fvr.setSelected(word.isFavor());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            HKDIC.insertFromFile();
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