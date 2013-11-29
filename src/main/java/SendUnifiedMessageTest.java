import org.jboss.aerogear.unifiedpush.JavaSender;
import org.jboss.aerogear.unifiedpush.SenderClient;
import org.jboss.aerogear.unifiedpush.message.MessageResponseCallback;
import org.jboss.aerogear.unifiedpush.message.UnifiedMessage;
import org.junit.Test;

public class SendUnifiedMessageTest {

	@Test
	public void sendUnifiedMessageTest() {
		JavaSender sender = new SenderClient("http://aerogear-mtaylor.rhcloud.com");       // [1]

		UnifiedMessage unifiedMessage = new UnifiedMessage.Builder()                 // [2]
		                .pushApplicationId("9d646a12-e601-4452-9e05-efb0fccdfd08")
		                .masterSecret("ed75f17e-cf3c-4c9b-a503-865d91d60d40")
		                .alert("Hello from Java Sender API!")
		                .sound("default")
		                .badge("1")
		                .timeToLive(3600)  // [3]
		                .attribute("some_key", "with_value")  // [4]
		                .build();

		sender.send(unifiedMessage, new MessageResponseCallback() {      // [5]

		            public void onComplete(int statusCode) {
		            	if(statusCode != 200)
		            	{
		            		throw new RuntimeException("Request failed. HTTP Status code:" + statusCode);
		            	}
		             
		            }

		            public void onError(Throwable throwable) {
		            	throw new RuntimeException(throwable);
		            }
		          });
	}
}
