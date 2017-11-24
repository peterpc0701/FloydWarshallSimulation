/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package floyd.warshall;

import com.jfoenix.controls.JFXButton;
import java.awt.Color;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author void
 */
public class TableViewerController implements Initializable {
    
    public static int steps;
    private static int ctr2 = -1;
    private static int ctr;
    public static double[][][] edges;
    private Pane[][] tempPane;
    private Label[][] label;
    
    @FXML
    private Label pathLabel;
    
    @FXML
    private AnchorPane tablePane;
    
    @FXML
    private JFXButton nextButton;

    @FXML
    private JFXButton previousButton;

    @FXML
    private GridPane sequenceGrid;

    @FXML
    private GridPane pathGrid;

    @FXML
    void nextButtonAction(ActionEvent event) {
        if (ctr < steps) {
            
            ctr++;
            ctr2++;

            for (int i = 0; i < steps; i++) {
                for (int j = 0; j < steps; j++) {
                    pathGrid.getChildren().removeAll(label[i][j]);
                    pathGrid.getChildren().removeAll(tempPane[i][j]);
                }
            }

            pathLabel.setText("Step: " + ctr);
            pathGrid.setAlignment(Pos.CENTER);

            for (int i = 0; i < steps; i++) {
                for (int j = 0; j < steps; j++) {
                    label[i][j] = new Label();
                    tempPane[i][j] = new Pane();

                    String x = Double.toString(edges[ctr][i][j]);

                    label[i][j].setAlignment(Pos.CENTER);

                    if (x.equals("Infinity")) {
                        x = "∞";
                    }

                    label[i][j].setText(x);

                    if (i == ctr2 || j == ctr2) {
                        tempPane[i][j].setStyle("-fx-background-color: #009968; -fx-border-color: #000000;");
                        label[i][j].setTextFill(Paint.valueOf("white"));
                    } else {
                        if(edges[ctr][i][j] != edges[ctr - 1][i][j])
                            label[i][j].setTextFill(Paint.valueOf("red"));
                        
                        tempPane[i][j].setStyle("-fx-border-color: #000000;");
                        
                    }
                    pathGrid.add(tempPane[i][j], j, i);

                    GridPane.setHalignment(label[i][j], HPos.CENTER);
                    pathGrid.add(label[i][j], j, i);

                }
            }

        }
    }


    @FXML
    void previousButtonAction(ActionEvent event) throws IOException {
        if(ctr ==  0){
            AnchorPane temp = FXMLLoader.load(getClass().getResource("VertexAndEdge.fxml"));
            tablePane.getChildren().setAll(temp);
        }
        else{
            System.out.println(ctr +" "+ ctr2);
            ctr2--;
            ctr--;
            
            for (int i = 0; i < steps; i++) {
                for (int j = 0; j < steps; j++) {
                    pathGrid.getChildren().removeAll(label[i][j]);
                    pathGrid.getChildren().removeAll(tempPane[i][j]);
                }
            }
            
            if(ctr == 0)
                pathLabel.setText("Initial Path");
            else
                pathLabel.setText("Step: " + ctr);
            
            pathGrid.setAlignment(Pos.CENTER);

            for (int i = 0; i < steps; i++) {
                for (int j = 0; j < steps; j++) {
                    label[i][j] = new Label();
                    tempPane[i][j] = new Pane();

                    String x = Double.toString(edges[ctr][i][j]);

                    label[i][j].setAlignment(Pos.CENTER);

                    if (x.equals("Infinity")) {
                        x = "∞";
                    }

                    label[i][j].setText(x);

                    if (ctr2 >= 0 && (i == ctr2 || j == ctr2)) {
                        tempPane[i][j].setStyle("-fx-background-color: #009968; -fx-border-color: #000000;");
                        label[i][j].setTextFill(Paint.valueOf("white"));
                    } else {
                        if(ctr >= 1 && (edges[ctr][i][j] != edges[ctr - 1][i][j]))
                            label[i][j].setTextFill(Paint.valueOf("red"));
                        
                        tempPane[i][j].setStyle("-fx-border-color: #000000;");
                    }
                    pathGrid.add(tempPane[i][j], j, i);

                    GridPane.setHalignment(label[i][j], HPos.CENTER);
                    pathGrid.add(label[i][j], j, i);

                }
            }
            
            
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        ctr = -1; 
        
        tempPane = new Pane[steps][steps];
        label = new Label[steps][steps];
        
        pathLabel.setText("Initial Path");
        pathGrid.setAlignment(Pos.CENTER);
        
        for (int i = 0; i < steps; i++) {
            for (int j = 0; j < steps; j++) {
                label[i][j] = new Label();
                tempPane[i][j] = new Pane();
                
                String x = Double.toString(edges[0][i][j]);
                
                
                label[i][j].setAlignment(Pos.CENTER);
                
                if(x.equals("Infinity"))
                    x = "∞";
                
                label[i][j].setText(x);

               
                tempPane[i][j].setStyle("-fx-border-color: #000000;");
                
                pathGrid.add(tempPane[i][j], j, i);

                GridPane.setHalignment(label[i][j], HPos.CENTER);
                pathGrid.add(label[i][j], j, i);
                
            }
        }
        ctr++;   
    }    
    
}