package com.rakuishi.ok;

import com.rakuishi.ok.api.OkAPIClient;
import com.rakuishi.ok.api.model.Feed;

import org.junit.Test;
import static org.junit.Assert.*;

import rx.functions.Action1;

/**
 * Created by rakuishi on 15/05/02.
 */
public class OkAPIClientTest {

    public static final String TAG = OkAPIClientTest.class.getSimpleName();

    @Test
    public void testRequestFeed() throws Exception {

        OkAPIClient.getInstance().requestFeed()
                .subscribe(new Action1<Feed>() {
                    @Override
                    public void call(Feed feed) {
                        assertNotNull(feed);
                        assertEquals(feed.getTitle(), "rakuishi.com");
                        assertEquals(feed.getLink(), "http://rakuishi.com/");
                    }
                });
    }
}
