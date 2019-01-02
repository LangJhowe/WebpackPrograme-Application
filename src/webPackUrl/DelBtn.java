package webPackUrl;

import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Button;

public class DelBtn {
	private Button delBtn= new Button("É¾³ý");
	public DelBtn(Button delBtn) {
		this.delBtn = delBtn;
	}
	public ObservableValue<Button> getDelBtn() {
		return new ObservableValue<Button>() {
			@Override
			public void addListener(ChangeListener<? super Button> listener) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void removeListener(ChangeListener<? super Button> listener) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public Button getValue() {
				// TODO Auto-generated method stub
				return null;
			}
			@Override
			public void addListener(InvalidationListener listener) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void removeListener(InvalidationListener listener) {
				// TODO Auto-generated method stub
				
			}
		};
	}
}
