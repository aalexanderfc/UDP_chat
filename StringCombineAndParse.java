package UDP1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.DefaultListModel;

public class StringCombineAndParse {

    public static String combine_strings(List<String> stringList){
        String combinedString = String.join(";", stringList);
        return combinedString;
    } 


    public static List<String> convertToList(DefaultListModel<String> model) {
        List<String> stringList = new ArrayList<>();
        for (int i = 0; i < model.getSize(); i++) {
            stringList.add(model.getElementAt(i));
        }
        return stringList;
    }
    public static DefaultListModel<String> convertToDefaultListModel(List<String> stringList) {
        DefaultListModel<String> model = new DefaultListModel<>();
        for (String item : stringList) {
            model.addElement(item);
        }
        return model;    
    }
    public static void AddToListModel(DefaultListModel<String> model, String newString){
        // Check if the string is already in the model
        if (!model.contains(newString)) {
            model.addElement(newString);
        }
    }
    public static void RemoveFromListModel(DefaultListModel<String> model, String stringToRemove){
        // Check if the string is already in the model
        System.out.println("Remove from list" + combine_strings(convertToList(model)));
        System.out.println("what" + stringToRemove);
        if (model.contains(stringToRemove)) {
            System.out.println("model contains the " + stringToRemove + " proceed to remove. ");
            model.removeElement(stringToRemove);
            System.out.println("after removal" + combine_strings(convertToList(model)));
        }         
    }
    
}
