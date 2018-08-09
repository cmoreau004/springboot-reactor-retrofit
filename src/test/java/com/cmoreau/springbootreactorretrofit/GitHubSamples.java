package com.cmoreau.springbootreactorretrofit;

import com.cmoreau.springbootreactorretrofit.model.Stats;
import reactor.core.publisher.Flux;

public class GitHubSamples {

    public static String ORGANIZATION_NAME = "reactor";
    public static Integer TOTAL_FOLLOWERS_NB = 359;
    public static Integer TOTAL_REPOS_NB = 168;
    public static String USER_LOGIN1 = "ifesdjeen";
    public static String USER_NAME1 = "Alex Petrov";
    public static String USER_LOGIN2 = "markpollack";
    public static String USER_NAME2 = "Mark Pollack";

    static String getMembersStats(){
        Stats stats = new Stats<>(TOTAL_FOLLOWERS_NB, TOTAL_REPOS_NB);
        return stats.toString();
    }

    static String getMembers(){
        Flux<String> flux = Flux.just(USER_LOGIN1 + "(" + USER_NAME1+")", USER_LOGIN2 + "(" + USER_NAME2+")");
        return flux.toString();
    }
}
