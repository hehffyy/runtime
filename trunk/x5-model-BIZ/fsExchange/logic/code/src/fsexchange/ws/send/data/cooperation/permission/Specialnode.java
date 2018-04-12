package fsexchange.ws.send.data.cooperation.permission;

import java.util.Date;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import fsexchange.ws.send.data.NodeKind;
import fsexchange.ws.send.data.XmlOut;

public class Specialnode implements XmlOut, NodeDate, NodeKind {
	private Date nodedate;
	private ApplyNode applynode = new ApplyNode();
	private HandleNode handlenode;

	public Date getDate() {
		return this.nodedate;
	}

	public void setDate(Date date) {
		this.nodedate = date;

	}

	public String getKind() {
		return "特别程序" + (handlenode != null ? "(已结束)" : "");
	}

	public Element getElement() {
		Document document = DocumentHelper.createDocument();
		Element root = document.addElement("specialnode");
		root.add(applynode.getElement());
		if (handlenode != null) {
			root.add(handlenode.getElement());
		}
		return root;
	}

	public ApplyNode getApplynode() {
		return applynode;
	}

	public void setApplynode(ApplyNode applynode) {
		this.applynode = applynode;
	}

	public HandleNode getHandlenode() {
		return handlenode;
	}

	public void setHandlenode(HandleNode handlenode) {
		this.handlenode = handlenode;
	}

	public class ApplyNode implements XmlOut {
		private String specialtype;
		private String specialname;
		private String specialstartdate;
		private String specialuser;
		private String specialusertel;
		private String specialuserphone;
		private String specialidea;
		private String specialcontent;
		private String speciallimit;
		private String specialunit;
		private String department;

		public Element getElement() {
			Document document = DocumentHelper.createDocument();
			Element root = document.addElement("applynode");
			root.addElement("specialtype").addCDATA(this.specialtype);
			root.addElement("specialname").addCDATA(this.specialname);
			root.addElement("specialstartdate").addCDATA(this.specialstartdate);
			root.addElement("specialuser").addCDATA(this.specialuser);
			root.addElement("specialusertel").addCDATA(this.specialusertel);
			root.addElement("specialuserphone").addCDATA(this.specialuserphone);
			root.addElement("specialidea").addCDATA(this.specialidea);
			root.addElement("specialcontent").addCDATA(this.specialcontent);
			root.addElement("speciallimit").addCDATA(this.speciallimit);
			root.addElement("specialunit").addCDATA(this.specialunit);
			root.addElement("department").addCDATA(this.department);
			return root;
		}

		public String getSpecialtype() {
			return specialtype;
		}

		public void setSpecialtype(String specialtype) {
			this.specialtype = specialtype;
		}

		public String getSpecialname() {
			return specialname;
		}

		public void setSpecialname(String specialname) {
			this.specialname = specialname;
		}

		public String getSpecialstartdate() {
			return specialstartdate;
		}

		public void setSpecialstartdate(String specialstartdate) {
			this.specialstartdate = specialstartdate;
		}

		public String getSpecialuser() {
			return specialuser;
		}

		public void setSpecialuser(String specialuser) {
			this.specialuser = specialuser;
		}

		public String getSpecialusertel() {
			return specialusertel;
		}

		public void setSpecialusertel(String specialusertel) {
			this.specialusertel = specialusertel;
		}

		public String getSpecialuserphone() {
			return specialuserphone;
		}

		public void setSpecialuserphone(String specialuserphone) {
			this.specialuserphone = specialuserphone;
		}

		public String getSpecialidea() {
			return specialidea;
		}

		public void setSpecialidea(String specialidea) {
			this.specialidea = specialidea;
		}

		public String getSpecialcontent() {
			return specialcontent;
		}

		public void setSpecialcontent(String specialcontent) {
			this.specialcontent = specialcontent;
		}

		public String getSpeciallimit() {
			return speciallimit;
		}

		public void setSpeciallimit(String speciallimit) {
			this.speciallimit = speciallimit;
		}

		public String getSpecialunit() {
			return specialunit;
		}

		public void setSpecialunit(String specialunit) {
			this.specialunit = specialunit;
		}

		public String getDepartment() {
			return department;
		}

		public void setDepartment(String department) {
			this.department = department;
		}
	}

	public class HandleNode implements XmlOut {
		private String specialresult;
		private String specialresultdate;
		private String specialenddate;
		private String specialpay;
		private String department;

		public Element getElement() {
			Document document = DocumentHelper.createDocument();
			Element root = document.addElement("handlenode");
			root.addElement("specialresult").addCDATA(this.specialresult);
			root.addElement("specialresultdate").addCDATA(this.specialresultdate);
			root.addElement("specialenddate").addCDATA(this.specialenddate);
			root.addElement("specialpay").addCDATA(this.specialpay);
			root.addElement("department").addCDATA(this.department);
			return root;
		}

		public String getSpecialresult() {
			return specialresult;
		}

		public void setSpecialresult(String specialresult) {
			this.specialresult = specialresult;
		}

		public String getSpecialresultdate() {
			return specialresultdate;
		}

		public void setSpecialresultdate(String specialresultdate) {
			this.specialresultdate = specialresultdate;
		}

		public String getSpecialenddate() {
			return specialenddate;
		}

		public void setSpecialenddate(String specialenddate) {
			this.specialenddate = specialenddate;
		}

		public String getSpecialpay() {
			return specialpay;
		}

		public void setSpecialpay(String specialpay) {
			this.specialpay = specialpay;
		}

		public String getDepartment() {
			return department;
		}

		public void setDepartment(String department) {
			this.department = department;
		}
	}
}
