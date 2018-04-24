import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/*
 * Main class handles most of the UI elements and also aggregates driver
 */




public class Main extends Application implements EventHandler<ActionEvent> {

    Driver driver = new Driver();
    //Creates buttons
    Button button1 = new Button();
    Button button2 = new Button();
    Button button3 = new Button();
    Button button4 = new Button("Get Cost On");
    Button button5 = new Button("Get Cost US");
    Button button6 = new Button("Get Building");
    //creates laebels
    Label Samsung =  new Label("The total number of Samsung devices sold is: ");
    Label item_47 = new Label("Total number of time item 47");
    Label Cat_id_7 = new Label("The Total cost for category 7: ");
    Label Cost_in_Ontario =  new Label("This is the Cost in Ontario:");
    Label CostinUS = new Label("This is the Cost in United States: ");
    Label Building_id = new Label("Building_Id: "+" has the most purchase cost");

    public static void main(String[] args) {

        launch(args); // tells javafx to use the the start method

    }


    public void start(Stage primaryStage) throws Exception {
        GridPane layout = new GridPane();
        
       
        int indent = 1; // sets lebel/button indent

        driver.getData();
        driver.getData2();
        
        
        //the following places labels and buttons on the screen 
        button1.setText("Get Samsung");
        button1.setOnAction(this);


        button2.setText("Get item 47");
        button2.setOnAction(this);


        button3.setText("Get Cost: ");
        button3.setOnAction(this);


        button4.setOnAction(this);


        button5.setOnAction(this);


        button6.setOnAction(this);


        layout.setPadding(new Insets(400,0,0,250));
        
        layout.add(Samsung,0,0);
        layout.add(button1,12, 0);
       
        layout.add(item_47,0,1);
        layout.add(button2,12,1);
        
        
        layout.add(Cat_id_7,0, 2*indent, 1, 1);
        layout.add(button3,12, 2*indent, 1, 1);
        
        
        layout.add(Cost_in_Ontario,0, 3*indent, 1, 1);
        layout.add(button4,12, 3*indent, 1, 1);
        
        
        layout.add(CostinUS,0, 4*indent, 1, 1);
        layout.add(button5,12, 4*indent, 1, 1);
        
        
        layout.add(Building_id,0, 5*indent, 1, 1);
        layout.add(button6,12, 5*indent, 1, 1);
        
        // sets scene
        Scene scene = new Scene(layout);
        scene.getStylesheets().add((Main.class.getResource("styles.css").toExternalForm()));
        primaryStage.setScene(scene);
        primaryStage.show();





    }
// the handle method methods tells how each button should handle specified events
    public void handle(ActionEvent event) {

        if(event.getSource()==button1){


            String num = Integer.toString(driver.getSamsung());
           Samsung.setText("The total number of Samsung devices sold is: "+num); ;

        }
        if(event.getSource()==button2){


            String num = Integer.toString(driver.getitem47());
            item_47.setText("The total number of items 47 sold is: "+num); ;

        }
        if(event.getSource()==button3){


            String num = Double.toString( driver.get_cost_item7());
            Cat_id_7.setText("The total purchase amount of Category 47 sold is: $"+num+" "); ;

        }
        if(event.getSource()==button4){



            String num = Double.toString(driver.Ontario_cost());
            Cost_in_Ontario.setText("The total made in Ontario is: $"+num); ;

        }
        if(event.getSource()==button5){


            String num = Double.toString(driver.Us_cost());
            CostinUS.setText("The total made in the US is: $"+num); ;

        }
        if(event.getSource()==button6){


            String num = Double.toString(driver.most_item());
            Building_id.setText("The building with the most purchase cost: "+num); ;

        }

    }
}
