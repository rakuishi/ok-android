package com.rakuishi.ok;

import android.util.Log;

import com.rakuishi.ok.api.OkAPIClient;
import com.rakuishi.ok.api.model.Feed;
import com.rakuishi.ok.api.model.Gist;
import com.rakuishi.ok.api.model.Repo;

import org.junit.Test;

import java.util.List;

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

    @Test
    public void testRequestRepos() throws Exception {
        OkAPIClient.getInstance().requestRepos()
                .subscribe(new Action1<List<Repo>>() {
                    @Override
                    public void call(List<Repo> repos) {
                        assertNotNull(repos);
                        Log.d(TAG, repos.toString());
                    }
                });
    }

    @Test
    public void testRequestGists() throws Exception {
        OkAPIClient.getInstance().requestGists()
                .subscribe(new Action1<List<Gist>>() {
                    @Override
                    public void call(List<Gist> gists) {
                        assertNotNull(gists);
                        Log.d(TAG, gists.toString());
                    }
                });
    }
}
