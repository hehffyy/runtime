package fsexchange.ws.send;

import com.justep.model.Config;
import com.justep.model.ModelUtils;
import com.justep.system.data.Row;
import com.justep.system.data.Table;

public class DefaultBusinessCollectTask extends AbstractBusinessCollectTask {

	public DefaultBusinessCollectTask() {
		Config config = (Config) ModelUtils.getModelObjectByFullName("/fsExchange/config/exchangeService", Config.TYPE);
		this.setUserService(new UserServiceImpl(config.getItem("userService").getValue("wsUrl"), config.getItem("userService").getValue("intferfaceName")));
		this.setBusinessCollectService(new BusinessCollectServiceImpl(config.getItem("postDataService").getValue("wsUrl"), config.getItem("postDataService").getValue("intferfaceName")));

	}

	@Override
	protected Row getApplyRec(String bizRecId) {
		// TODO 自动生成的方法存根
		return super.getApplyRec(bizRecId);
	}

	@Override
	protected Table queryYewuShenPiTable(String sphj, String bizRecId) {
		// TODO 自动生成的方法存根
		return super.queryYewuShenPiTable(sphj, bizRecId);
	}

	@Override
	protected Row getShouLiRec(String bizRecId) {
		// TODO 自动生成的方法存根
		return super.getShouLiRec(bizRecId);
	}

	@Override
	protected boolean isSuspendExchange(String process) {
		// TODO 自动生成的方法存根
		return super.isSuspendExchange(process);
	}

	@Override
	protected boolean isSwitchShenPiSX(String process, String spsxdxbh) {
		// TODO 自动生成的方法存根
		return super.isSwitchShenPiSX(process, spsxdxbh);
	}

}
