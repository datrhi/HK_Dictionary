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
    void addListSearch() {
        String target = input_word.getText();
        if(!target.isEmpty()) {
            list_search.getItems().setAll(HKDIC.dictionarySearcher(target));
        }
        else{
            list_search.getItems().clear();
        }
    }

    @FXML
    void selectFromListSearch(MouseEvent event) {
        String list_search_word = list_search.getSelectionModel().getSelectedItem();
        if(!list_search_word.isEmpty()) {
            Word word = HKDIC.dictionaryLookup(list_search_word);
            explain_content.setText(word.toString());
        }
    }

    @FXML
    void AddNewWord(ActionEvent event) {

    }

    public void lookupSearch() {
        String target = input_word.getText();
        Word word = HKDIC.dictionaryLookup(target);
        explain_content.setText(word.toString());
        if(target.isEmpty()) explain_content.clear();
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