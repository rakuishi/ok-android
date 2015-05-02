package com.rakuishi.ok.api;

import com.rakuishi.ok.api.model.Feed;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.IOException;

import rx.Observable;
import rx.Subscriber;
import rx.exceptions.OnErrorFailedException;
import rx.functions.Func1;

/**
 * Created by rakuishi on 15/05/02.
 */
public class OkAPIClient {

    public static final String TAG = OkAPIClient.class.getSimpleName();

    private static final OkAPIClient instance = new OkAPIClient();
    private OkHttpClient mOkHttpClient;
    private Serializer mSerializer;

    private OkAPIClient() {
        mOkHttpClient = new OkHttpClient();
        mSerializer = new Persister();
    }

    public static OkAPIClient getInstance() {
        return OkAPIClient.instance;
    }

    public Observable<Response> request(final Request request) {
        return Observable.create(new Observable.OnSubscribe<Response>() {
            @Override
            public void call(Subscriber<? super Response> subscriber) {
                try {
                    Response response = mOkHttpClient.newCall(request).execute();
                    subscriber.onNext(response);
                    subscriber.onCompleted();
                } catch (IOException e) {
                    subscriber.onError(e);
                }
            }
        });
    }

    public Observable<Feed> requestFeed() {
        Request req = new Request.Builder()
                .url("http://rakuishi.com/index.xml")
                .get()
                .build();
        return request(req)
                .map(new MapResponseToFeed(mSerializer));
    }

    private static class MapResponseToFeed implements Func1<Response, Feed> {

        private Serializer mSerializer;

        public MapResponseToFeed(Serializer serializer) {
            mSerializer = serializer;
        }

        @Override
        public Feed call(Response response) {
            try {
                return mSerializer.read(Feed.class, response.body().string());
            } catch (Exception e) {
                throw new OnErrorFailedException(e);
            }
        }
    }
}
