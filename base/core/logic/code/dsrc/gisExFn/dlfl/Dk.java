package gisExFn.dlfl;
import java.util.ArrayList;
import java.util.List;

import com.butone.utils.StringUtils;

public class Dk {
		String dkID = StringUtils.getNewGuid32();
		String featureGUID;
		String countryBM;
		String countrySideBM;
		List<QuanShu> qsList = new ArrayList<QuanShu>();

		public QuanShu getQs(String qsdwdm, String qsdwmc, String qsxz) {
			QuanShu curBean = null;
			for (QuanShu bean : qsList) {
				if (bean.qsdwdm.equals(qsdwdm))
					curBean = bean;
			}
			if (curBean == null) {
				curBean = new QuanShu();
				curBean.qsdwdm = qsdwdm;
				curBean.qsdwmc = qsdwmc;
				curBean.qsxz = qsxz;
				this.qsList.add(curBean);
				System.out.println(curBean.qsID);
			}
			return curBean;
		}
	}