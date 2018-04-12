import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Iterator;

import org.apache.commons.beanutils.PropertyUtils;

import com.justep.system.data.Row;
import com.justep.system.data.Table;


public class DlSumFunc {
	 public static void sum(Table source,Table pewy,Table target) throws Exception {
		   DlAnalResult mResult = new DlAnalResult();
		   DlAnalResult jResult = new DlAnalResult();
		   DlAnalResult pResult = new DlAnalResult();
		   Iterator<Row> iter =  source.iterator();
		   while(iter.hasNext()){
			   Row  row = iter.next();
			   String pnode = row.getString("PARENTNODE");
			   String dlbm = row.getString("DLBM");
			   String qsxz = row.getString("QSXZ");
			   String dlbz = row.getString("DLBZ");
			   float mMj = row.getFloat("EDOUBLE1");
			   float jMj = row.getFloat("AREA");
			   if (pewy != null)
			   {
			   Iterator<Row> iterp =  pewy.iterator();
			   while(iterp.hasNext()){
				   Row  prow = iterp.next();
				   String nstr = prow.getString("NODE");
				   Integer oid =  prow.getInteger("OBJECTID");
				   if (pnode.equals(nstr) && (oid!=null)){
					   sumOneDl(pResult, dlbm, mMj, qsxz,dlbz);
				   }
			   }
			   }
			   sumOneDl(mResult, dlbm, mMj, qsxz,dlbz);
			   sumOneDl(jResult, dlbm, jMj, qsxz,dlbz);
			   
		   }
		   
		   appendRow(pResult,target,"批");
		   appendRow(mResult,target,"毛");
		   appendRow(jResult,target,"净");
		}

	private static  void appendRow(DlAnalResult bean,Table target,String kind) throws Exception{
		 Field[] fds = DlAnalResult.class.getDeclaredFields();
		 Row row = target.appendRow();
		 row.setString("KIND", kind);
		 for(int i=0;i<fds.length;i++){
			 String name = fds[i].getName();
			 Object value =  PropertyUtils.getProperty(bean, name);
			 if(! (value instanceof Float)){
				 continue;
			 }
			 String columnName = name.toUpperCase();
			 if (!target.getColumnNames().contains(columnName))
				 continue;
			 row.setValue(columnName, BigDecimal.valueOf(Double.parseDouble(String.valueOf(value))));
		 }
	}
	private static void sumOneDl(DlAnalResult result, String dlbm, float mj, String qsxz,String dlbz) {
		boolean bGy = qsxz.equals("10") || qsxz.equals("20");
		//带K地类
		if(dlbz!=null && dlbz.contains("K")) {
			result.setDkdl(result.getDkdl() + mj);
			if(bGy)
				result.setDkdlgy(result.getDkdlgy()+ mj);
			else
				result.setDkdljy(result.getDkdljy() + mj);
		}
		//耕地
		if("011|012|013".contains(dlbm)) {
			result.setGd(result.getGd() + mj);
			if(bGy)
				result.setGdgy(result.getGdgy()+ mj);
			else
				result.setGdjy(result.getGdjy() + mj);
		}
		//水田
		if("011".contains(dlbm)) {
			result.setSt(result.getSt() + mj);
			if(bGy)
				result.setStgy(result.getStgy()+ mj);
			else
				result.setStjy(result.getStjy() + mj);
		}
		//水浇地
		if("012".contains(dlbm)) {
			result.setSjd(result.getSjd() + mj);
			if(bGy)
				result.setSjdgy(result.getSjdgy()+ mj);
			else
				result.setSjdjy(result.getSjdjy() + mj);
		}
		//园地
		if("021|022|023".contains(dlbm)) {
			result.setYd(result.getYd() + mj);
			if(bGy)
				result.setYdgy(result.getYdgy()+ mj);
			else
				result.setYdjy(result.getYdjy() + mj);
		}
		//林地
		if("031|032|033".contains(dlbm)) {
			result.setLd(result.getLd() + mj);
			if(bGy)
				result.setLdgy(result.getLdgy()+ mj);
			else
				result.setLdjy(result.getLdjy() + mj);
		}
		//其它农用地
		if("041|042|104|122|123|117|114".contains(dlbm)) {
			result.setQtnyd(result.getQtnyd() + mj);
			if(bGy)
				result.setQtnydgy(result.getQtnydgy()+ mj);
			else
				result.setQtnydjy(result.getQtnydjy() + mj);
		}
		//农用地
		if("011|012|013|021|022|023|031|032|033|041|042|104|114|117|122|123".contains(dlbm)) {
			result.setNyd(result.getNyd() + mj);
			if(bGy)
				result.setNydgy(result.getNydgy()+ mj);
			else
				result.setNydjy(result.getNydjy() + mj);
		}
		
		//养殖水面
		if("114".contains(dlbm)) {
			result.setYzsm(result.getYzsm() + mj);
			if(bGy)
				result.setYzsmgy(result.getYzsmgy()+ mj);
			else
				result.setYzsmjy(result.getYzsmjy() + mj);
		}
		
		//建设用地
		if("051|052|053|054|061|062|063|071|072|081|082|083|084|085|086|087|088|091|092|093|094|095|101|102|103|105|106|107|113|118|121|201|202|203|204|205".contains(dlbm)) {
			result.setJsyd(result.getJsyd() + mj);
			if(bGy)
				result.setJsydgy(result.getJsydgy()+ mj);
			else
				result.setJsydjy(result.getJsydjy() + mj);
		}
		
		//未利用地
		if("111|112|115|116|119|043|124|125|126|127".contains(dlbm)) {
			result.setWlyd(result.getWlyd() + mj);
			if(bGy)
				result.setWlydgy(result.getWlydgy()+ mj);
			else
				result.setWlydjy(result.getWlydjy() + mj);
		}
		
		//总计
		result.setZmj(result.getZmj() + mj);
		if(bGy)
			result.setZmjgy(result.getZmjgy() + mj);
		else
			result.setZmjjy(result.getZmjjy()+mj);
	}
}
