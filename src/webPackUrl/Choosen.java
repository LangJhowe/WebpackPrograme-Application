package webPackUrl;

import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.CheckBox;

public class Choosen {
	private CheckBox checkBox= new CheckBox();
	public ObservableValue<CheckBox> getCheckBox() {
		return new ObservableValue<CheckBox>() {
			@Override
			public void addListener(ChangeListener<? super CheckBox> listener) {
				// TODO Auto-generated method stub
				System.out.println("addListener");
			}
			@Override
			public void removeListener(ChangeListener<? super CheckBox> listener) {
				// TODO Auto-generated method stub
				System.out.println("removeListener");
			}
			@Override
			public CheckBox getValue() {
				// TODO Auto-generated method stub
				System.out.println("getValue");
				return null;
			}
			@Override
			public void addListener(InvalidationListener listener) {
				// TODO Auto-generated method stub
				System.out.println("addListenerIn");
			}
			@Override
			public void removeListener(InvalidationListener listener) {
				// TODO Auto-generated method stub
				System.out.println("removeListenerIn");
			}
		};
	}
	public Boolean isChoosen() {
		return checkBox.isSelected();
	}
	@Override
	public String toString() {
		return "Choosen [checkBox=" + checkBox.isSelected() + "]";
	}
}
