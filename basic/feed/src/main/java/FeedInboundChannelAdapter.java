import com.rometools.rome.feed.synd.SyndEntry;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.integration.endpoint.SourcePollingChannelAdapter;
import org.springframework.messaging.Message;
import org.springframework.messaging.PollableChannel;
import java.net.URL;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;

/**
 * @author Ryan Rahardjo on 3/18/2019
 */
public class FeedInboundChannelAdapter {
    public static void main(String[] args) {
        ConfigurableApplicationContext ac = new ClassPathXmlApplicationContext("FeedInboundChannelAdapterSample-context.xml");
        PollableChannel feedChannel = ac.getBean("feedChannel", PollableChannel.class);
        for (int i = 0; i < 10000; i++) {
            Message<SyndEntry> message = (Message<SyndEntry>) feedChannel.receive(2000);
            if (message != null){
                SyndEntry entry = message.getPayload();
                System.out.println(i);
                System.out.println(entry);
            }
        }

//        Read all feed by url
//        try {
//            String url = "http://rss.detik.com/index.php/detikcom";
//
//            try (XmlReader reader = new XmlReader(new URL(url))) {
//                SyndFeed feed = new SyndFeedInput().build(reader);
//                System.out.println(feed.getTitle());
//                System.out.println("***********************************");
//                for (SyndEntry entry : feed.getEntries()) {
//                    System.out.println(entry);
//                    System.out.println("***********************************");
//                }
//                System.out.println("Done");
//            }
//        }  catch (Exception e) {
//            e.printStackTrace();
//        }
    }
}
