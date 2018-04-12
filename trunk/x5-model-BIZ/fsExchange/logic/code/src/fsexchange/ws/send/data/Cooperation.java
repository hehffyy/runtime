package fsexchange.ws.send.data;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import fsexchange.ws.send.data.cooperation.Agency;
import fsexchange.ws.send.data.cooperation.Application;
import fsexchange.ws.send.data.cooperation.Parallel;
import fsexchange.ws.send.data.cooperation.Permission;

public class Cooperation implements XmlOut {
	private Application application = new Application();
	private Agency agency = new Agency();
	private Parallel parallel;
	private Permission permission = new Permission();

	public Element getElement() {
		Document document = DocumentHelper.createDocument();
		Element root = document.addElement("cooperation");
		root.add(application.getElement());
		if (this.agency != null) {
			root.add(agency.getElement());
		}
		if (this.parallel != null) {
			root.add(parallel.getElement());
		}
		root.addElement("permissions").add(permission.getElement());
		return root;
	}

	public Application getApplication() {
		return application;
	}

	public void setApplication(Application application) {
		this.application = application;
	}

	public Agency getAgency() {
		return agency;
	}

	public void setAgency(Agency agency) {
		this.agency = agency;
	}

	public Parallel getParallel() {
		return parallel;
	}

	public void setParallel(Parallel parallel) {
		this.parallel = parallel;
	}

	public Permission getPermission() {
		return permission;
	}

	public void setPermission(Permission permission) {
		this.permission = permission;
	}

}
