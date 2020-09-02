package mode;

import java.util.ArrayList;
import java.util.List;

public class TabFour extends menuItem {
	private List<DropDownBox> list = new ArrayList<DropDownBox>();
	public void addDropDownBox(DropDownBox d) 
	{
		list.add(d);
	}
	public DropDownBox getDropDownBoxByIndex(int index) 
	{
		return list.get(index);
	}
}
