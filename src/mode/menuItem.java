package mode;

import java.util.ArrayList;
import java.util.List;

public class menuItem {
  private String  title ="";
  private String tips="";
  private List<DropDownBox> list = new ArrayList<DropDownBox>();
  public void addDropDownBox(DropDownBox d) 
  {
	  list.add(d);
  }

  
public String getTips() {
	return tips;
}
public void setTips(String tips) {
	this.tips = tips;
}
public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
}
public String getContent() {
	return content;
}
public void setContent(String content) {
	this.content = content;
}
private String content="";
}


