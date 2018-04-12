package gisExFn.dlfl;

import java.util.ArrayList;
import java.util.List;

import com.butone.utils.StringUtils;



public class QuanShu {
	String qsID=StringUtils.getNewGuid32();
	String qsdwmc;
	String qsdwdm;
	String qsxz;
	List<DlDetail> dlList = new ArrayList<DlDetail>();

	public DlDetail addDl(String dlbm, String dlmc, Double area) {
		DlDetail curBean = null;
		for (DlDetail bean : dlList) {
			if (bean.dlbm.equals(dlbm))
				curBean = bean;
		}
		if (curBean == null) {
			curBean = new DlDetail();
			curBean.dlbm = dlbm;
			curBean.dlmc = dlmc;
			curBean.area = area;
			this.dlList.add(curBean);
		} else {
			curBean.area = curBean.area + area;
		}
		return curBean;
	}
}
