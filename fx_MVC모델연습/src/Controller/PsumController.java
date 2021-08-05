package Controller;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;

public class PsumController implements Initializable {
	
	
    @FXML
    private BarChart barchart;

    @FXML
    private PieChart piechart;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		// 막대차트 
		
		//1. series 계열 생성
		XYChart.Series series = new XYChart.Series(); 
		series.setName("판매량");
	
		// 2. 계열에 데이터[리스트] 넣기 
		ObservableList 판매량리스트 = FXCollections.observableArrayList();
		
		for( int i = 0 ; i<=10000 ; i+=10 ) {
			판매량리스트.add( new XYChart.Data( "판매량" , i ) );
		}
		
		series.setData(판매량리스트);
		
		// 3. 해당 계열을 막대차트에 추가 
		barchart.getData().add(series);
		
		XYChart.Series today = new XYChart.Series();
		XYChart.Series yesterday = new XYChart.Series();
		XYChart.Series nextday = new XYChart.Series();
		
		Calendar calendar = new GregorianCalendar();
		
			SimpleDateFormat format = new SimpleDateFormat("yy-MM-dd");
			String todaydate = format.format( calendar.getTime() );	
			
			calendar.add( calendar.DATE, -1); // 뒤로 1일 이동
			String yesterdaydate = format.format(  calendar.getTime() ) ;
			
			calendar.add( calendar.DATE, +2); // 앞으로 2일 이동 
			String nextdaydate = format.format(  calendar.getTime() ) ;
			
		today.setName(todaydate);
		yesterday.setName(yesterdaydate);
		nextday.setName(nextdaydate);
			
		ObservableList 판매량리스트2 = FXCollections.observableArrayList();
		판매량리스트2.add( new XYChart.Data(todaydate , 5000) );
		
		ObservableList 판매량리스트3 = FXCollections.observableArrayList();
		판매량리스트3.add( new XYChart.Data(yesterdaydate , 4000) );
		
		ObservableList 판매량리스트4 = FXCollections.observableArrayList();
		판매량리스트4.add( new XYChart.Data(nextdaydate , 8000) );
		
		today.setData(판매량리스트2);
		yesterday.setData(판매량리스트3);
		nextday.setData(판매량리스트4);
		
		barchart.getData().add(yesterday);
		barchart.getData().add(today);
		barchart.getData().add(nextday);
		
		
		
		
		
		
		
	}
	
	

}
