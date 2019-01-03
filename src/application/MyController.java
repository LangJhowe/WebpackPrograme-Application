package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import com.fasterxml.jackson.databind.ObjectMapper;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.shape.LineTo;
import webPackUrl.Choosen;
import webPackUrl.WebPackUrl;

public class MyController implements Initializable {

	@FXML
	private Button addBtn;
	@FXML
	private Button delBtn;
	@FXML
	private Button webPackBtn;

	@FXML
	private TextField programSite;
	@FXML
	private TextField targetSite;
	@FXML
	private TableView tableView;

	@FXML
	private TableColumn programSiteCol;
	@FXML
	private TableColumn targetSiteCol;
	@FXML
	private TableColumn isChoosenCol;
	@FXML
	private TableColumn operatorCol;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		// TODO (don't really need to do anything here).
		try {
			loadData();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		showList();
	}

	public ObservableList<WebPackUrl> loadData() throws FileNotFoundException, IOException {
		File file = new File("WebpackData/data.txt");
		File dir = new File("WebpackData");
		ObservableList<WebPackUrl> data = FXCollections.observableArrayList();
		ObjectMapper mapper = new ObjectMapper();
		WebPackUrl weboackurl = new WebPackUrl("ceshi", "ceshi", new CheckBox());
		String ceshi = mapper.writeValueAsString(weboackurl);
		System.out.println(mapper);
		if (!dir.exists()) {
			dir.mkdir();
			dir.createNewFile();
		}
		if (file.exists()) {
			String pathname = "WebpackData/data.txt";
			try (FileReader reader = new FileReader(pathname); BufferedReader br = new BufferedReader(reader)) {
				String line;
				while ((line = br.readLine()) != null) {
					// 一次读入一行数据
					System.out.println(line);
					StringBuffer bufferLine = new StringBuffer(line);
					int len = bufferLine.length();
					StringBuffer newBufferLine = new StringBuffer();
					String[] dataString = bufferLine.substring(2,len-2).split("\\}\\,\\{");
					for(String o:dataString) {
						System.out.println(o);
						String[] itemArr = o.split(",");
						String programSite = "";
						String trgetSite = "";
						Boolean isChoosen = false;

						for(String i:itemArr) {
							int indexComma = i.indexOf(":");
//							System.out.println(i.substring(0,indexComma)+"1");
//							System.out.println("\"programSite\"1");
//							System.out.println("\"programSite\""=="\"programSite\"");
							if(i.substring(0,indexComma)=="\"programSite\"") {
								programSite = i.substring(indexComma+1);
//								System.out.println(i.substring(0,indexComma));
							} else if(i.substring(0,indexComma)=="\"targeSite\"") {
								trgetSite = i.substring(indexComma+1);
							} else if(i.substring(0,indexComma)=="\"isChoosen\""){
								isChoosen = Boolean.getBoolean(i.substring(indexComma+1));
							}
						}
						data.add(new WebPackUrl(programSite, trgetSite, new CheckBox()));
					}
//					for(WebPackUrl j:data) {
//						System.out.println(j.getProgramSite());
//					}
//					System.out.println(data);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			try {
				file.createNewFile();

			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		return data;
	}

	public void showList() {
		ObservableList<WebPackUrl> data = FXCollections.observableArrayList(
				new WebPackUrl("aaa", "aaa", new CheckBox()), new WebPackUrl("bbb", "bbb", new CheckBox()),
				new WebPackUrl("ccc", "ccc", new CheckBox()), new WebPackUrl("ddd", "ddd", new CheckBox()));
		programSiteCol.setMinWidth(100);
		programSiteCol.setCellValueFactory(new PropertyValueFactory<>("programSite"));
		targetSiteCol.setMinWidth(100);
		targetSiteCol.setCellValueFactory(new PropertyValueFactory<>("targetSite"));
		isChoosenCol.setMinWidth(100);
		isChoosenCol.setCellValueFactory(new PropertyValueFactory<>("isChoosen"));
		operatorCol.setMinWidth(100);
		operatorCol.setCellFactory((col) -> {
			TableCell<WebPackUrl, String> cell = new TableCell<WebPackUrl, String>() {
				@Override
				public void updateItem(String item, boolean empty) {
					super.updateItem(item, empty);
					this.setText(null);
					this.setGraphic(null);

					if (!empty) {
						Button delBtn = new Button("删除");
						this.setGraphic(delBtn);
						delBtn.setOnMouseClicked((me) -> {
							WebPackUrl clickedWebpackUrl = this.getTableView().getItems().get(this.getIndex());
							System.out.println(clickedWebpackUrl.getIsChoosen().isSelected());
						});
					}
				}

			};
			return cell;
		});
		tableView.setItems(data);
	}

	public void addProgram() throws IOException, IOException {
		try {
			ObservableList<WebPackUrl> data = tableView.getItems();
//			   System.out.println(programSite.getText() == null);
//			   System.out.println(programSite.getText() == "");
//			   System.out.println(programSite.getText().equals(""));
			if (programSite.getText().equals("") | targetSite.getText().equals("")) {
				System.out.println("out");
				return;
			}
//				File writeName = new File("WebpackData/data.txt");
//				try (FileWriter writer = new FileWriter(writeName);
//						BufferedWriter out = new BufferedWriter(writer);){
//					out.write("我会写文件啦");
//				} catch (Exception e) {
//					// TODO: handle exception
//				}
			data.add(new WebPackUrl(programSite.getText(), targetSite.getText(), new CheckBox()));
			FileWriter writeFile = new FileWriter("WebpackData/data.txt");
			String aryStr = aryToString(data);
			BufferedWriter bWriter = new BufferedWriter(writeFile);
			System.out.println(aryStr);
			bWriter.write(aryStr);
			bWriter.close();
			// System.out.println(data);
		} finally {

		}

	}

	public void webpackSelectedProgram() {
		List<WebPackUrl> selected = new ArrayList<WebPackUrl>();
		System.out.println(getSelectedList());
	}

	public List<WebPackUrl> getSelectedList() {
		ObservableList<WebPackUrl> data = tableView.getItems();
		List<WebPackUrl> selected = new ArrayList<WebPackUrl>();
		for (WebPackUrl o : data) {
			if (o.getIsChoosen().isSelected()) {
				selected.add(o);
			}
		}
		return selected;
	}

	public String aryToString(List data) {
		StringBuilder sb = new StringBuilder();
		ObservableList<WebPackUrl> data1 = tableView.getItems();
		for (WebPackUrl o : data1) {
			sb.append("{" + "programSite:" + o.getProgramSite() + "," + "targetSite:" + o.getTargetSite() + ","
					+ "isChoosen:" + o.getIsChoosen().isSelected() + "}").append(",");
		}
		sb.deleteCharAt(sb.length() - 1);
		return sb.toString();
	}
}