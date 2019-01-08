package webPackUrl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;

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
	public void webpack() {
		String obatFile = getClass().getResource("/") + "resource";
		BufferedReader br = null;
		int xieG = obatFile.indexOf("/");
		String batFile = obatFile.substring(xieG+1).replace("/", "\\");
//		System.out.println(batFile.replace("/", "\\"));
		String fShell = "cd " + batFile;
		String sShell = "start " + batFile + "\\delAndWebpack.bat " +programSite.getValue() + " " + targetSite.getValue();
//		String shell = "ping www.baidu.com";
		System.out.println(sShell);
		try {
			Process p = Runtime.getRuntime().exec(fShell);
			Process sp = Runtime.getRuntime().exec(sShell);
			br = new BufferedReader(new InputStreamReader(p.getInputStream()));//获取执行后出现的错误；getInputStream是获取执行后的结果
			String line = null;
			StringBuilder sb = new StringBuilder();
			while ((line = br.readLine()) != null) {
				sb.append(line + "\n");
				System.out.println(sb);
			}
			System.out.println(sb);//打印执行后的结果
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
