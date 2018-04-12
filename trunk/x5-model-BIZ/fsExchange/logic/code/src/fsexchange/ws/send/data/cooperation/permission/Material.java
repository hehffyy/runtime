package fsexchange.ws.send.data.cooperation.permission;

import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import fsexchange.ws.send.data.XmlOut;

public class Material implements XmlOut {
	private String id;//材料实例ID-1（申报时产生的材料编号）
	private String mlid;//材料ID-1（运行管理平台配置材料时产生）
	private String pid;//父材料ID（运行管理平台配置时产生）
	private String mlname;//材料名称
	private String mtype;//材料类型（电子化要求：在线填报(1)，系统共享(2)，电子化上传(3)，证照(4), 窗口递交(5),速递服务(6)）
	private String selecttype;//已选择的电子化要求（1:在线填报;2:系统共享3:电子化上传;4:证照验证;5:窗口递交;6:速递服务）
	private String fid;//上传文件返回ID（电子化上传产生的文件ID或在线填报产生的FORMDATA的ID）
	private String fpath;//FTP文件存储路径（补充）
	private String fname;//上传材料名称
	private String status = "提交";//材料状态(0未提交1提交2补交)
	private String orinum;//原件份数
	private String copynum;//复印件份数
	private String isneed;//是否必要(0非必要1必要)
	private String baseinfo = "0";//是否是基本信息填报表单(0:非表单;1:个人;2:企业;3:业务)
	private String formver;//表单版本号
	private String adjustment;//材料状态为补交时，填写补交意见（补充）
	private List<XmlOut> datainfos;//审批申报表单，当baseinfo节点不为0时，dataxml节点必填
	private List<XmlOut> subMaterials;

	public Element getElement() {
		Document document = DocumentHelper.createDocument();
		Element root = document.addElement("material");
		root.addElement("id").addCDATA(this.id);
		root.addElement("mlid").addCDATA(this.mlid);
		root.addElement("pid").addCDATA(this.pid);
		root.addElement("mlname").addCDATA(this.mlname);
		root.addElement("mtype").addCDATA(this.mtype);
		root.addElement("selecttype").addCDATA(this.selecttype);
		root.addElement("fid").addCDATA(this.fid);
		root.addElement("fpath").addCDATA(this.fpath);
		root.addElement("fname").addCDATA(this.fname);
		root.addElement("status").addCDATA(this.status);
		root.addElement("orinum").addCDATA(this.orinum);
		root.addElement("copynum").addCDATA(this.copynum);
		root.addElement("isneed").addCDATA(this.isneed);
		root.addElement("baseinfo").addCDATA(this.baseinfo);
		root.addElement("formver").addCDATA(this.formver);
		root.addElement("adjustment").addCDATA(this.adjustment);
		if (!("0".equals(this.baseinfo))) {
			this.datainfos = new ArrayList<XmlOut>();
		}
		if (this.datainfos != null && this.datainfos.size() > 0) {
			Element datainfoNode = root.addElement("dataxml").addElement("datainfo");
			for (XmlOut o : this.datainfos) {
				datainfoNode.add(o.getElement());
			}
		}
		if (this.subMaterials != null && this.subMaterials.size() > 0) {
			Element materialNode = root.addElement("material");
			for (XmlOut o : this.subMaterials) {
				materialNode.add(o.getElement());
			}
		}

		return root;
	}

	public List<XmlOut> getSubMaterials() {
		return subMaterials;
	}

	public void setSubMaterials(List<XmlOut> subMaterials) {
		this.subMaterials = subMaterials;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMlid() {
		return mlid;
	}

	public void setMlid(String mlid) {
		this.mlid = mlid;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getMlname() {
		return mlname;
	}

	public void setMlname(String mlname) {
		this.mlname = mlname;
	}

	public String getMtype() {
		return mtype;
	}

	public void setMtype(String mtype) {
		this.mtype = mtype;
	}

	public String getSelecttype() {
		return selecttype;
	}

	public void setSelecttype(String selecttype) {
		this.selecttype = selecttype;
	}

	public String getFid() {
		return fid;
	}

	public void setFid(String fid) {
		this.fid = fid;
	}

	public String getFpath() {
		return fpath;
	}

	public void setFpath(String fpath) {
		this.fpath = fpath;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getOrinum() {
		return orinum;
	}

	public void setOrinum(String orinum) {
		this.orinum = orinum;
	}

	public String getCopynum() {
		return copynum;
	}

	public void setCopynum(String copynum) {
		this.copynum = copynum;
	}

	public String getIsneed() {
		return isneed;
	}

	public void setIsneed(String isneed) {
		this.isneed = isneed;
	}

	public String getBaseinfo() {
		return baseinfo;
	}

	public void setBaseinfo(String baseinfo) {
		this.baseinfo = baseinfo;
	}

	public String getFormver() {
		return formver;
	}

	public void setFormver(String formver) {
		this.formver = formver;
	}

	public String getAdjustment() {
		return adjustment;
	}

	public void setAdjustment(String adjustment) {
		this.adjustment = adjustment;
	}

	public List<XmlOut> getDatainfos() {
		return datainfos;
	}

	public void setDatainfos(List<XmlOut> datainfos) {
		this.datainfos = datainfos;
	}
}
