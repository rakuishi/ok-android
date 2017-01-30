package com.rakuishi.ok.api;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import com.rakuishi.ok.model.Feed;
import com.rakuishi.ok.model.Gist;
import com.rakuishi.ok.model.Repo;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.functions.Function;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@Singleton
public class OkAPIClient {

    private OkHttpClient httpClient;
    private Gson gson;
    private Persister persister;

    @Inject
    public OkAPIClient(OkHttpClient httpClient, Gson gson, Persister persister) {
        this.httpClient = httpClient;
        this.gson = gson;
        this.persister = persister;
    }

    public Observable<Response> getResponse(String url) {
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();

        return Observable.create(e -> {
            try {
                Response response = this.httpClient.newCall(request).execute();
                e.onNext(response);
                e.onComplete();
            } catch (IOException exception) {
                e.onError(exception);
            }
        });
    }

    public Observable<Feed> requestFeed() {
        return getResponse("https://rakuishi.com/index.xml")
                .map(mapXML2Object(this.persister, Feed.class));
    }

    public Observable<List<Repo>> requestRepos() {
        return getResponse("https://api.github.com/users/rakuishi/repos")
                .map(mapJson2Object(this.gson, new TypeToken<List<Repo>>() {
                }));
    }

    public Observable<List<Gist>> requestGists() {
        return getResponse("https://api.github.com/users/rakuishi/gists")
                .map(mapJson2Object(this.gson, new TypeToken<List<Gist>>() {
                }));
    }

    private static <T> Function<Response, T> mapXML2Object(final Serializer serializer,
                                                           final Class<T> clazz) {
        return response -> serializer.read(clazz, response.body().string());
    }

    private static <T> Function<Response, T> mapJson2Object(final Gson gson,
                                                            final TypeToken<T> typeToken) {
        return response -> {
            JsonElement element = gson.fromJson(response.body().string(), JsonElement.class);
            return gson.fromJson(element, typeToken.getType());
        };
    }
}
