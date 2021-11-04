package ExerciseC;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
   @FXML
   private RadioButton radbtn_1, radbtn_2, radbtn_3;
   @FXML
   private Pane pane;
   @FXML
   private ComboBox<String> comboBox;
   @FXML
   private Rectangle rectangle;
   @FXML
   private ToggleGroup StrokeWidth;
   @FXML
   private TextField width,height;
   @FXML
   private CheckBox stroke;

   private Alert.AlertType type = Alert.AlertType.INFORMATION;
   private Alert alertwindow = new Alert(type,"");
   private int StrokeWidthTemp = 0;

   @Override
   public void initialize(URL url, ResourceBundle resourceBundle) {
      StrokeWidth.selectToggle(radbtn_1);
      comboBox.getItems().addAll("RED","GREEN","BLUE","BLACK");
      pane.setStyle(" -fx-background-color: white; -fx-border-color: white; -fx-border-insets: 2; -fx-border-width: 2;");
   }
   public void changeColor(ActionEvent e){
      String choice = comboBox.getValue();
      switch (choice){
         case "BLUE" -> rectangle.setFill(Color.BLUE);
         case "BLACK" -> rectangle.setFill(Color.BLACK);
         case "GREEN" -> rectangle.setFill(Color.GREEN);
         case "RED" -> rectangle.setFill(Color.RED);
      }
   }
   public void changeDim(ActionEvent e){
      double hei = pane.getHeight()-10;
      double wid = pane.getWidth()-10;
      if(hei>=Double.parseDouble(height.getText())&&
              wid>=Double.parseDouble(width.getText())) {
         rectangle.setHeight(Double.parseDouble(height.getText()));
         rectangle.setWidth(Double.parseDouble(width.getText()));
         rectangle.setLayoutX((pane.getWidth() - rectangle.getWidth()) / 2);
         rectangle.setLayoutY((pane.getHeight() - rectangle.getHeight()) / 2);
      }
      else{
         alertwindow.getDialogPane().setContentText("Do not draw outside of height(y):"+hei+" and width(x):"+wid);
         alertwindow.getDialogPane().setHeaderText("Error! Can't draw out of bounds.");
         alertwindow.setTitle("input error");
         alertwindow.showAndWait();
      }
   }
   public void addStroke(ActionEvent e){
      if(stroke.isSelected()){
         getStrokeWidth();
         rectangle.setStrokeWidth(StrokeWidthTemp);
      }
      else {
         rectangle.setStrokeWidth(0);
      }
   }
   public void getStrokeWidth(){
      if(radbtn_1.isSelected()){
         StrokeWidthTemp=1;
      }
      else if(radbtn_2.isSelected()){
         StrokeWidthTemp=2;
      }
      else if(radbtn_3.isSelected())
         StrokeWidthTemp=3;
   }
   public void mouse(MouseEvent e){
      double hei = pane.getHeight()-10;
      double wid = pane.getWidth()-10;
      if(e.isPrimaryButtonDown()&& rectangle.getWidth()+5<wid) {
         rectangle.setWidth(rectangle.getWidth() + 5);
      }
      else if(e.isSecondaryButtonDown()&& rectangle.getHeight()+5<hei) {
         rectangle.setHeight(rectangle.getHeight() + 5);
      }
      rectangle.setLayoutX((pane.getWidth()- rectangle.getWidth())/2);
      rectangle.setLayoutY((pane.getHeight()- rectangle.getHeight())/2);
   }
}
