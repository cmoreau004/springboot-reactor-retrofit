package com.cmoreau.springbootreactorretrofit.service;

import com.cmoreau.springbootreactorretrofit.model.GitHubUser;
import reactor.core.publisher.Mono;
import retrofit2.http.GET;
import retrofit2.http.Path;

import java.util.List;

public interface GitHubService {

    @GET("orgs/{org}/members")
    Mono<List<GitHubUser>> getOrganizationMembers(@Path("org") String org);

    @GET("users/{user}")
    Mono<GitHubUser> getUser(@Path("user") String user);


}
