package fsexchange.ws.send.data.cooperation.permission;

import java.util.Date;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import fsexchange.ws.send.data.NodeKind;
import fsexchange.ws.send.data.XmlOut;

/**
 *  补交告知֪
 * @author Administrator
 *
 */
public class Complementnode implements XmlOut, NodeDate, NodeKind {
	private Date nodedate;
	private NotifyNode notifynode = new NotifyNode();
	private AcceptNode acceptnode;

	/**
	 * 补交告知环节
	 * @author Administrator
	 *
	 */
	public Date getDate() {
		return this.nodedate;
	};

	public void setDate(Date date) {
		this.nodedate = date;

	}

	public String getKind() {
		return "补交告知" + (acceptnode != null ? "(已结束)" : "");
	}

	public class NotifyNode implements XmlOut {
		private String nodeactor;
		private String complementdate;
		private String complementidea;
		private String complementlist;
		private String department;

		public Element getElement() {
			Document document = DocumentHelper.createDocument();
			Element root = document.addElement("notifynode");
			root.addElement("nodeactor").addCDATA(this.nodeactor);
			root.addElement("complementdate").addCDATA(this.complementdate);
			root.addElement("complementidea").addCDATA(this.complementidea);
			root.addElement("complementlist").addCDATA(this.complementlist);
			root.addElement("department").addCDATA(this.department);
			return root;
		}

		public String getNodeactor() {
			return nodeactor;
		}

		public void setNodeactor(String nodeactor) {
			this.nodeactor = nodeactor;
		}

		public String getComplementdate() {
			return complementdate;
		}

		public void setComplementdate(String complementdate) {
			this.complementdate = complementdate;
		}

		public String getComplementidea() {
			return complementidea;
		}

		public void setComplementidea(String complementidea) {
			this.complementidea = complementidea;
		}

		public String getComplementlist() {
			return complementlist;
		}

		public void setComplementlist(String complementlist) {
			this.complementlist = complementlist;
		}

		public String getDepartment() {
			return department;
		}

		public void setDepartment(String department) {
			this.department = department;
		}
	}

	/**
	 * 补交受理环节
	 * @author Administrator
	 *
	 */
	public class AcceptNode implements XmlOut {

		private String handler;
		private String handlerdate;
		private String handleraddr;
		private String handlerlist;
		private String department;

		public Element getElement() {
			Document document = DocumentHelper.createDocument();
			Element root = document.addElement("acceptnode");
			root.addElement("handler").addCDATA(this.handler);
			root.addElement("handlerdate").addCDATA(this.handlerdate);
			root.addElement("handleraddr").addCDATA(this.handleraddr);
			root.addElement("handlerlist").addCDATA(this.handlerlist);
			root.addElement("department").addCDATA(this.department);
			return root;
		}

		public String getHandler() {
			return handler;
		}

		public void setHandler(String handler) {
			this.handler = handler;
		}

		public String getHandlerdate() {
			return handlerdate;
		}

		public void setHandlerdate(String handlerdate) {
			this.handlerdate = handlerdate;
		}

		public String getHandleraddr() {
			return handleraddr;
		}

		public void setHandleraddr(String handleraddr) {
			this.handleraddr = handleraddr;
		}

		public String getHandlerlist() {
			return handlerlist;
		}

		public void setHandlerlist(String handlerlist) {
			this.handlerlist = handlerlist;
		}

		public String getDepartment() {
			return department;
		}

		public void setDepartment(String department) {
			this.department = department;
		}

	}

	public Element getElement() {
		Document document = DocumentHelper.createDocument();
		Element root = document.addElement("complementnode");
		root.add(notifynode.getElement());
		if (acceptnode != null) {
			root.add(acceptnode.getElement());
		}
		return root;
	}

	public NotifyNode getNotifynode() {
		return notifynode;
	}

	public void setNotifynode(NotifyNode notifynode) {
		this.notifynode = notifynode;
	}

	public AcceptNode getAcceptnode() {
		return acceptnode;
	}

	public void setAcceptnode(AcceptNode acceptnode) {
		this.acceptnode = acceptnode;
	}

}
