//这里分开两个对象的原因是,菜单导航和功能是分开的,如果以后要在菜单导航上添加操作,描述MenuGroup属性即可
public class MenuGroup extends MenuCollection {
	public void addChildNode(MenuCollection child){
		getChildNodes().add(child);
	}
}
