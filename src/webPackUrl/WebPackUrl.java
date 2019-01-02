package webPackUrl;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;

public class WebPackUrl {
	private final SimpleStringProperty programSite;
	private final SimpleStringProperty targetSite;
	private final SimpleObjectProperty<CheckBox> isChoosen;
//	private final SimpleObjectProperty<Button> operator;
	public WebPackUrl(String programSite, String targetSite,
			CheckBox isChoosen) {
		this.programSite = new SimpleStringProperty(programSite);
		this.targetSite = new SimpleStringProperty(targetSite);
		this.isChoosen = new SimpleObjectProperty(isChoosen);
//		this.operator = new SimpleObjectProperty(operator);
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
		return "WebPackUrl [programSite=" + programSite + ", targetSite=" + targetSite + ", isChoosen=" + isChoosen.getValue()
				+ "]";
	}
//	public void setOperator(Button operator) {
//		this.operator.set(operator);
//	}
//	public SimpleObjectProperty<Button> getOperator() {
//		return operator;
//	}
//	
}
