package fsexchange.ws.test;

import fsexchange.ws.send.AbstractBusinessCollectTask;

public class TestBusinessCollectTask extends AbstractBusinessCollectTask {

	public TestBusinessCollectTask() {
		this.setUserService(new UserServiceTest());
		this.setBusinessCollectService(new BusinessCollectServiceTest());
	}

	@Override
	protected boolean isEnableFtp() {
		return false;
	}

}
