package webPackUrl;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.shape.LineTo;

public class WebPackUrl {
	private final SimpleStringProperty name;
	private final SimpleStringProperty programSite;
	private final SimpleStringProperty targetSite;
	private final SimpleObjectProperty<CheckBox> isChoosen;
	public WebPackUrl(String name, String programSite, String targetSite,
			CheckBox isChoosen) {
		this.name = new SimpleStringProperty(name);
		this.programSite = new SimpleStringProperty(programSite);
		this.targetSite = new SimpleStringProperty(targetSite);
		this.isChoosen = new SimpleObjectProperty(isChoosen);
//		this.operator = new SimpleObjectProperty(operator);
	}
	public void webpack() throws InterruptedException {
		String url = new File("WebpackData\\delAndWebpack.bat").getAbsolutePath();
		BufferedReader br = null;
		BufferedReader errbr = null;
		String sShell = "cmd /k start " + url + " " + targetSite.getValue() + " " + programSite.getValue();
		System.out.println(sShell);
		try {
			Process sp = Runtime.getRuntime().exec(sShell);
//			//取得命令结果的输出流
//			//输出流
//			InputStream fis = sp.getInputStream();
//			//错误流
//			InputStream ferrs = sp.getErrorStream();
//			br = new BufferedReader(new InputStreamReader(fis));//获取执行后出现的错误；getInputStream是获取执行后的结果
//			errbr = new BufferedReader(new InputStreamReader(ferrs));
//			String line = null;
//			String lineerr = null;
//			StringBuilder sb = new StringBuilder();
//			StringBuilder sberr = new StringBuilder();
//			while ((line = br.readLine()) != null) {
//				sb.append(line + "\n");
//				System.out.println(new String(sb.toString().getBytes("utf-8"),"iso-8859-1"));
//			}
////			while ((lineerr = errbr.readLine()) != null) {
////				sberr.append(line + "\n");
////				System.out.println(sberr);
////			}
//			int exitVal = sp.waitFor();
//			System.out.println("exitVal:" + exitVal);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(br!=null) {
				try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	public String getName() {
		return name.get();
	}
	public String getProgramSite() {
		return programSite.get();
	}
	public void setProgramSite(String programSite) {
		this.programSite.set(programSite);
	}
	public String getTargetSite() {
		return targetSite.get();
	}
	public void setTargetSite(String targetSite) {
		this.targetSite.set(targetSite);;
	}
	public CheckBox getIsChoosen() {
		return isChoosen.get();
	}
	public void setIsChoosen(CheckBox isChoosen) {
		this.isChoosen.set(isChoosen);
	}
	@Override
	public String toString() {
		return "{\"name\":\"" + name.getValue() + "\",\"programSite\":\""+ programSite.getValue() + "\",\"targetSite\":\"" + targetSite.getValue() + "\",\"isChoosen\":\"" + isChoosen.get().isSelected()
				+ "\"}";
	}
}
