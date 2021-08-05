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
		
		// ������Ʈ 
		
		//1. series �迭 ����
		XYChart.Series series = new XYChart.Series(); 
		series.setName("�Ǹŷ�");
	
		// 2. �迭�� ������[����Ʈ] �ֱ� 
		ObservableList �Ǹŷ�����Ʈ = FXCollections.observableArrayList();
		
		for( int i = 0 ; i<=10000 ; i+=10 ) {
			�Ǹŷ�����Ʈ.add( new XYChart.Data( "�Ǹŷ�" , i ) );
		}
		
		series.setData(�Ǹŷ�����Ʈ);
		
		// 3. �ش� �迭�� ������Ʈ�� �߰� 
		barchart.getData().add(series);
		
		XYChart.Series today = new XYChart.Series();
		XYChart.Series yesterday = new XYChart.Series();
		XYChart.Series nextday = new XYChart.Series();
		
		Calendar calendar = new GregorianCalendar();
		
			SimpleDateFormat format = new SimpleDateFormat("yy-MM-dd");
			String todaydate = format.format( calendar.getTime() );	
			
			calendar.add( calendar.DATE, -1); // �ڷ� 1�� �̵�
			String yesterdaydate = format.format(  calendar.getTime() ) ;
			
			calendar.add( calendar.DATE, +2); // ������ 2�� �̵� 
			String nextdaydate = format.format(  calendar.getTime() ) ;
			
		today.setName(todaydate);
		yesterday.setName(yesterdaydate);
		nextday.setName(nextdaydate);
			
		ObservableList �Ǹŷ�����Ʈ2 = FXCollections.observableArrayList();
		�Ǹŷ�����Ʈ2.add( new XYChart.Data(todaydate , 5000) );
		
		ObservableList �Ǹŷ�����Ʈ3 = FXCollections.observableArrayList();
		�Ǹŷ�����Ʈ3.add( new XYChart.Data(yesterdaydate , 4000) );
		
		ObservableList �Ǹŷ�����Ʈ4 = FXCollections.observableArrayList();
		�Ǹŷ�����Ʈ4.add( new XYChart.Data(nextdaydate , 8000) );
		
		today.setData(�Ǹŷ�����Ʈ2);
		yesterday.setData(�Ǹŷ�����Ʈ3);
		nextday.setData(�Ǹŷ�����Ʈ4);
		
		barchart.getData().add(yesterday);
		barchart.getData().add(today);
		barchart.getData().add(nextday);
		
		
		
		
		
		
		
	}
	
	

}
