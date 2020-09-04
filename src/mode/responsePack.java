package mode;

import java.util.List;

public class responsePack {
private int version = 0;
private List<menuItem> list = null;
public int getVersion() {
	return version;
}
public void setVersion(int version) {
	this.version = version;
}
public List<menuItem> getList() {
	return list;
}
public void setList(List<menuItem> list) {
	this.list = list;
}
}
