package com.rakuishi.ok.api;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import com.rakuishi.ok.api.model.Feed;
import com.rakuishi.ok.api.model.Gist;
import com.rakuishi.ok.api.model.Repo;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.IOException;
import java.util.List;

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
    private Gson mGson;

    private OkAPIClient() {
        mOkHttpClient = new OkHttpClient();
        mSerializer = new Persister();
        mGson = new Gson();
    }

    public static OkAPIClient getInstance() {
        return OkAPIClient.instance;
    }

    public Observable<Response> getResponse(final Request request) {
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
        Request request = new Request.Builder()
                .url("http://rakuishi.com/index.xml")
                .get()
                .build();
        return getResponse(request)
                .map(convertXMLResponseToObject(mSerializer, Feed.class));
    }

    public Observable<List<Repo>> requestRepos() {
        Request request = new Request.Builder()
                .url("https://api.github.com/users/rakuishi/repos")
                .get()
                .build();
        return getResponse(request)
                .map(convertJSONResponseToObject(mGson, new TypeToken<List<Repo>>() {
                }));
    }

    public Observable<List<Gist>> requestGists() {
        Request request = new Request.Builder()
                .url("https://api.github.com/users/rakuishi/gists")
                .get()
                .build();
        return getResponse(request)
                .map(convertJSONResponseToObject(mGson, new TypeToken<List<Gist>>() {
                }));
    }

    private static <T> Func1<Response, T> convertXMLResponseToObject(final Serializer serializer, final Class<T> clazz) {
        return new Func1<Response, T>() {
            @Override
            public T call(Response response) {
                try {
                    return serializer.read(clazz, response.body().string());
                } catch (Exception e) {
                    throw new OnErrorFailedException(e);
                }
            }
        };
    }

    private static <T> Func1<Response, T> convertJSONResponseToObject(final Gson gson, final TypeToken<T> typeToken) {
        return new Func1<Response, T>() {
            @Override
            public T call(Response response) {
                try {
                    JsonElement element = gson.fromJson(response.body().string(), JsonElement.class);
                    return gson.fromJson(element, typeToken.getType());
                } catch (Exception e) {
                    throw new OnErrorFailedException(e);
                }
            }
        };
    }
}
