import fsexchange.ws.send.DefaultBusinessCollectTask;
import fsexchange.ws.test.TestBusinessCollectTask;

public class FsExchange {
	//
	private static DefaultBusinessCollectTask ythEx = new DefaultBusinessCollectTask();

	public static void executeYTHExchange(String bizRecId) {
		ythEx.run(bizRecId); 
	}

	/**
	 * 模拟交换过程不发送数据，不上传附件
	 * 
	 * @param bizRecId
	 */
	public static void testYTHExhcange(String bizRecId) {
		new TestBusinessCollectTask().run();  
	}
}