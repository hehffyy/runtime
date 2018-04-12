import com.justep.system.context.*;

public class ReceiveProcess {

//	public static String currentPersonID = ContextHelper.getPerson().getID();
	
	/**
	 * GetRecTitle
	 */
	public static void receiveProcessBeforeGetRecTitle() {
		
		ContextHelper.getActionContext().setParameter("personId", ContextHelper.getPerson().getID());
	}

	/**
	 * GetRecInfo
	 */
	public static void receiveProcessBeforeGetRecInfo() {
	
		ContextHelper.getActionContext().setParameter("personId", ContextHelper.getPerson().getID());
	}
}