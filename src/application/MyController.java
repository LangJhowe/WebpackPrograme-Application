package application;

import java.awt.Desktop.Action;
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
import java.util.Stack;

import javax.print.attribute.standard.RequestingUserName;

import com.fasterxml.jackson.databind.ObjectMapper;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.LineTo;
import javafx.stage.DirectoryChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import webPackUrl.Choosen;
import webPackUrl.WebPackUrl;

public class MyController implements Initializable {
	@FXML AnchorPane layout;
	@FXML Button programBtn;
	@FXML Button targetBtn;
	@FXML
	private Button addBtn;
	@FXML
	private Button delBtn;
	@FXML
	private Button saveBtn;
	@FXML
	private Button webPackBtn;

	@FXML TextField name;
	@FXML
	private TextField programSite;
	@FXML
	private TextField targetSite;
	@FXML
	private TableView<WebPackUrl> tableView;

	@FXML
	private TableColumn nameCol;
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
		tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);// 占满
		try {

			showList(loadData());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ObservableList<WebPackUrl> loadData() throws FileNotFoundException, IOException {
		File file = new File("WebpackData/data.txt");
		File dir = new File("WebpackData");
		ObservableList<WebPackUrl> data = FXCollections.observableArrayList();
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
					String[] dataString = bufferLine.substring(1, len - 1).split("\\}\\,\\{");
					for (String o : dataString) {
//						System.out.println(o);
						String[] itemArr = o.split(",");
						String name = "";
						String programSite = "";
						String trgetSite = "";
						Boolean isChoosen = false;
						for (String i : itemArr) {
							int indexComma = i.indexOf(":");
							int slen = i.length();
							String keyStr = i.substring(1, indexComma - 1);
							if (keyStr.equals("name")) {
								name = i.substring(indexComma + 2, slen - 1);
							} else if(i.substring(1, indexComma - 1).equals("programSite")) {
								programSite = i.substring(indexComma + 2, slen - 1);
							} else if (i.substring(1, indexComma - 1).equals("targetSite")) {
								trgetSite = i.substring(indexComma + 2, slen - 1);
							} else if (i.substring(1, indexComma - 1).equals("isChoosen")) {
								isChoosen = Boolean.parseBoolean(i.substring(indexComma + 2, slen - 2));
							}
						}
						CheckBox cBox = new CheckBox();
						cBox.setSelected(isChoosen);
						data.add(new WebPackUrl(name, programSite, trgetSite, cBox));
					}
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

	public void showList(ObservableList<WebPackUrl> data) {
		nameCol.setMinWidth(100);
		nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
		programSiteCol.setMinWidth(100);
		programSiteCol.setCellValueFactory(new PropertyValueFactory<>("programSite"));
		targetSiteCol.setMinWidth(100);
		targetSiteCol.setCellValueFactory(new PropertyValueFactory<>("targetSite"));
		isChoosenCol.setMinWidth(50);
		isChoosenCol.setCellValueFactory(new PropertyValueFactory<>("isChoosen"));
		isChoosenCol.setCellFactory(new Callback<TableColumn<WebPackUrl, String>, TableCell<WebPackUrl, CheckBox>>() {

			@Override
			public TableCell<WebPackUrl, CheckBox> call(TableColumn<WebPackUrl, String> param) {
				// TODO Auto-generated method stub
//				param.get
//				System.out.println("xx" + param);
				return new TableCell<WebPackUrl, CheckBox>() {
					protected void updateItem(CheckBox cb, boolean arg1) {
						super.updateItem(cb, arg1);

						if (arg1) {
							setText(null);
							setGraphic(null);
						} else {
//							cb.selectedProperty().addListener((ObservableValue<? extends Boolean> ov,Boolean old_val,Boolean new_val) -> {
//								cb.setSelected(new_val);
//							});
							StackPane sPane = new StackPane();
							sPane.getChildren().add(cb);
							sPane.setAlignment(Pos.CENTER);
							setGraphic(sPane);
						}
					}
				};
			}
		});
//		isChoosenCol.setCellFactory((col) -> {
//			TableCell<WebPackUrl, String> cell = new TableCell<WebPackUrl, String>() {
//				@Override
//				public void updateItem(String item, boolean empty) {
//					super.updateItem(item, empty);
//					this.setText(null);
//					this.setGraphic(null);
//					System.out.println(item);
//					if (!empty) {
//						StackPane sPane = new StackPane();
//						CheckBox cBox = new CheckBox();
//						cBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
//							@Override
//							public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue,
//									Boolean newValue) {
//								System.out.println("gou");
//								
//							}
//						});
//						sPane.getChildren().add(cBox);
//						sPane.setAlignment(Pos.CENTER);
//						this.setGraphic(sPane);
//					}
//				}
//
//			};
//			return cell;
//		});
		operatorCol.setMinWidth(40);
		operatorCol.setCellFactory((col) -> {
			TableCell<WebPackUrl, String> cell = new TableCell<WebPackUrl, String>() {
				@Override
				public void updateItem(String item, boolean empty) {
					super.updateItem(item, empty);
					this.setText(null);
					this.setGraphic(null);

					if (!empty) {
						Button delBtn = new Button("删除");
						StackPane sPane = new StackPane();
						sPane.getChildren().add(delBtn);
						sPane.setAlignment(Pos.CENTER);
						this.setGraphic(sPane);
						delBtn.setOnMouseClicked((me) -> {
							ObservableList<WebPackUrl> data = tableView.getItems();
							WebPackUrl clickedWebpackUrl = this.getTableView().getItems().get(this.getIndex());
							data.remove(this.getIndex());
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
		ObservableList<WebPackUrl> data = tableView.getItems();
		if (programSite.getText().equals("") | targetSite.getText().equals("")) {
			System.out.println("out");
			return;
		}
		data.add(new WebPackUrl(name.getText(), programSite.getText(), targetSite.getText(), new CheckBox()));
		saveData();

	}

	// 打包选中的数据并保存此时的数据
	public void webpackSelectedProgram() {
		ObservableList<WebPackUrl> data = tableView.getItems();
		List<WebPackUrl> selected = new ArrayList<WebPackUrl>();
		for(WebPackUrl o: data) {
			if(o.getIsChoosen().isSelected()) {
				o.webpack();
			}
		}
	}

	public List<WebPackUrl> getSelectedList() {
		ObservableList<WebPackUrl> data = tableView.getItems();
		List<WebPackUrl> selected = new ArrayList<WebPackUrl>();
		System.out.println("---------------------");
		for (WebPackUrl o : data) {
			System.out.println(o.toString());
			if (o.getIsChoosen().isSelected()) {
				selected.add(o);

			}
		}
		System.out.println("---------------------");
		return selected;
	}

	public String aryToString(List data) {
		if(data.size() == 0) return "";
		StringBuilder sb = new StringBuilder();
		ObservableList<WebPackUrl> data1 = tableView.getItems();
		for (WebPackUrl o : data1) {
			sb.append(o.toString()).append(",");
		}
		sb.deleteCharAt(sb.length() - 1);
		return sb.toString();
	}

	// 保存按钮
	public void saveData() {
		ObservableList<WebPackUrl> data = tableView.getItems();
		FileWriter writeFile = null;
		try {
			writeFile = new FileWriter("WebpackData/data.txt");
			String aryStr = aryToString(data);
			BufferedWriter bWriter = new BufferedWriter(writeFile);
			System.out.println(aryStr);
			bWriter.write(aryStr);
			bWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//选择项目文件位置
	public void chooseProgramSite() {
		programSite.setText(chooseSite("选择项目所在文件夹").toString());
	}
	//选择目标文件位置
	public void chooseTargetSite() {
		targetSite.setText(chooseSite("选择目标生成文件夹").toString());
	}
	public File chooseSite(String title) {
		//在controller获取scene
		Stage stage = (Stage) layout.getScene().getWindow();
		DirectoryChooser dChooser = new DirectoryChooser();
		dChooser.setTitle(title);
		return dChooser.showDialog(stage);
	}
}