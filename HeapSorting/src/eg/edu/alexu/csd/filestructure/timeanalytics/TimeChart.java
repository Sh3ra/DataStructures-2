package eg.edu.alexu.csd.filestructure.timeanalytics;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.ArrayList;

public class TimeChart extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        HBox root=new HBox();
        Scene scene=new Scene(root,1200,700);
        NumberAxis xAis=new NumberAxis();
        xAis.setLabel("Array Size(n)");
        NumberAxis yAxis=new NumberAxis();
        yAxis.setLabel("RunTime(ms)");
        yAxis.setLowerBound(0.0001);
        NumberAxis xAis2=new NumberAxis();
        xAis2.setLabel("Array Size(n)");
        NumberAxis yAxis2=new NumberAxis();
        yAxis2.setLabel("RunTime(ms)");
        yAxis2.setLowerBound(0.0001);
        NumberAxis xAis3=new NumberAxis();
        xAis3.setLabel("Array Size(n)");
        NumberAxis yAxis3=new NumberAxis();
        yAxis3.setLabel("RunTime(ms)");
        yAxis3.setLowerBound(0.0001);
        ScatterChart<Number,Number> lineChart1=new ScatterChart<>(xAis,yAxis);
        ScatterChart<Number,Number> lineChart2=new ScatterChart<Number, Number>(xAis2,yAxis2);
        ScatterChart<Number,Number> lineChart3=new ScatterChart<Number, Number>(xAis3,yAxis3);
        lineChart1.setTitle("Comparison between sorting algorithms");
        lineChart2.setTitle("Comparison between sorting algorithms");
        lineChart3.setTitle("Comparison between sorting algorithms");
        DataGeneration dataGeneration=new DataGeneration();
        DataGeneration.generateData();
        XYChart.Series<Number,Number> data1=new XYChart.Series<>();
        XYChart.Series<Number,Number> data2=new XYChart.Series<>();
        XYChart.Series<Number,Number> data3=new XYChart.Series<>();
        ArrayList<Integer> n2time=dataGeneration.getN2time();
        ArrayList<Integer> nlogn= DataGeneration.getNlongntime();
        ArrayList<Integer> heaptime=dataGeneration.getHeaptime();

        data1.setName("Slow Sort<Bubble Sort>");
        data2.setName("Fast Sort<Quick Sort>");
        data3.setName("Heap Sort");

        for(int i=0;i<n2time.size();i=i+1) {
            data1.getData().add(new XYChart.Data<Number, Number>(i, n2time.get(i)));
            data2.getData().add(new XYChart.Data<Number, Number>(i, nlogn.get(i)));
            data3.getData().add(new XYChart.Data<Number, Number>(i, heaptime.get(i)));
        }
        lineChart1.autosize();
        lineChart2.autosize();
        lineChart3.autosize();
        lineChart1.getData().add(data1);
        lineChart2.getData().add(data2);
        lineChart3.getData().add(data3);
        root.getChildren().addAll(lineChart1,lineChart2,lineChart3);

        primaryStage.setTitle("Sorting Algorithms Complexity");
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
