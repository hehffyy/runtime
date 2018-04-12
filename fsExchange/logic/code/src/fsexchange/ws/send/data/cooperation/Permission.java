package fsexchange.ws.send.data.cooperation;

import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import fsexchange.ws.send.data.XmlOut;
import fsexchange.ws.send.data.cooperation.permission.Certificate;
import fsexchange.ws.send.data.cooperation.permission.Chargeinfo;

/**
 * 审批数据
 * @author Administrator
 *
 */
public class Permission implements XmlOut {
	private String id;
	private String name;
	private String handler;
	private String bsid;
	private String bsnum;
	private String xmmc;
	private String regionarea;
	private String cid;
	private String cbsnum;
	private String department;
	private String flgdgxd;
	private String fsywgxd;

	private String sljtdd;
	private String tjfs;
	private String isfinish;
	private String dealresults;
	private String hzbh;
	private String stagebh;
	private String status;

	private List<XmlOut> materials = new ArrayList<XmlOut>();//审批材料节点
	private List<XmlOut> nodes = new ArrayList<XmlOut>();//审批环节审批办理节点
	private Certificate certificate;//审批办结产生共享资源的信息节点
	private Chargeinfo chargeinfo;//收费信息节点
	private List<XmlOut> dispatches;//调度节点

	public Element getElement() {
		Document document = DocumentHelper.createDocument();
		Element root = document.addElement("permission");
		root.addElement("id").addCDATA(this.id);
		root.addElement("name").addCDATA(this.name);
		root.addElement("handler").addCDATA(this.handler);
		root.addElement("bsid").addCDATA(this.bsid);
		root.addElement("bsnum").addCDATA(this.bsnum);
		root.addElement("xmmc").addCDATA(this.xmmc);
		root.addElement("regionarea").addCDATA(this.regionarea);
		root.addElement("cid").addCDATA(this.cid);
		root.addElement("cbsnum").addCDATA(this.cbsnum);
		root.addElement("department").addCDATA(this.department);
		root.addElement("flgdgxd").addCDATA(this.flgdgxd);
		root.addElement("fsywgxd").addCDATA(this.fsywgxd);
		root.addElement("sljtdd").addCDATA(this.sljtdd);
		root.addElement("tjfs").addCDATA(this.tjfs);
		root.addElement("isfinish").addCDATA(this.isfinish);
		root.addElement("dealresults").addCDATA(this.dealresults);
		root.addElement("hzbh").addCDATA(this.hzbh);
		root.addElement("stagebh").addCDATA(this.stagebh);
		root.addElement("status").addCDATA(this.status);

		Element materials = root.addElement("materials");
		for (XmlOut m : this.materials) {
			materials.add(m.getElement());
		}

		Element nodes = root.addElement("nodes");
		for (XmlOut n : this.nodes) {
			nodes.add(n.getElement());
		}
		if (this.chargeinfo != null) {
			root.add(this.chargeinfo.getElement());
		}
		if (this.certificate != null) {
			root.add(this.certificate.getElement());
		}
		if (this.dispatches != null && this.dispatches.size() > 0) {
			root.addElement("dispatches");
			Element dispNode = root.addElement("dispatches");
			for (XmlOut d : this.dispatches) {
				dispNode.add(d.getElement());
			}
			dispNode.addElement("status");
		}

		return root;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHandler() {
		return handler;
	}

	public void setHandler(String handler) {
		this.handler = handler;
	}

	public String getBsid() {
		return bsid;
	}

	public void setBsid(String bsid) {
		this.bsid = bsid;
	}

	public String getBsnum() {
		return bsnum;
	}

	public void setBsnum(String bsnum) {
		this.bsnum = bsnum;
	}

	public String getXmmc() {
		return xmmc;
	}

	public void setXmmc(String xmmc) {
		this.xmmc = xmmc;
	}

	public String getRegionarea() {
		return regionarea;
	}

	public void setRegionarea(String regionarea) {
		this.regionarea = regionarea;
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getCbsnum() {
		return cbsnum;
	}

	public void setCbsnum(String cbsnum) {
		this.cbsnum = cbsnum;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getFlgdgxd() {
		return flgdgxd;
	}

	public void setFlgdgxd(String flgdgxd) {
		this.flgdgxd = flgdgxd;
	}

	public String getFsywgxd() {
		return fsywgxd;
	}

	public void setFsywgxd(String fsywgxd) {
		this.fsywgxd = fsywgxd;
	}

	public String getSljtdd() {
		return sljtdd;
	}

	public void setSljtdd(String sljtdd) {
		this.sljtdd = sljtdd;
	}

	public String getTjfs() {
		return tjfs;
	}

	public void setTjfs(String tjfs) {
		this.tjfs = tjfs;
	}

	public String getIsfinish() {
		return isfinish;
	}

	public void setIsfinish(String isfinish) {
		this.isfinish = isfinish;
	}

	public String getDealresults() {
		return dealresults;
	}

	public void setDealresults(String dealresults) {
		this.dealresults = dealresults;
	}

	public String getHzbh() {
		return hzbh;
	}

	public void setHzbh(String hzbh) {
		this.hzbh = hzbh;
	}

	public String getStagebh() {
		return stagebh;
	}

	public void setStagebh(String stagebh) {
		this.stagebh = stagebh;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<XmlOut> getMaterials() {
		return materials;
	}

	public void setMaterials(List<XmlOut> materials) {
		this.materials = materials;
	}

	public List<XmlOut> getNodes() {
		return nodes;
	}

	public void setNodes(List<XmlOut> nodes) {
		this.nodes = nodes;
	}

	public Certificate getCertificate() {
		return certificate;
	}

	public void setCertificate(Certificate certificate) {
		this.certificate = certificate;
	}

	public Chargeinfo getChargeinfo() {
		return chargeinfo;
	}

	public void setChargeinfo(Chargeinfo chargeinfo) {
		this.chargeinfo = chargeinfo;
	}

	public List<XmlOut> getDispatches() {
		return dispatches;
	}

	public void setDispatches(List<XmlOut> dispatches) {
		this.dispatches = dispatches;
	}

}
