package com.cmoreau.springbootreactorretrofit.service;

import com.cmoreau.springbootreactorretrofit.model.GitHubUser;
import com.cmoreau.springbootreactorretrofit.model.Stats;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GitHubOrganizationService {

    private final GitHubService gitHubService;

    public Mono<String> getMembersStats(String org) {
        Mono<List<GitHubUser>> organizationMembers = gitHubService.getOrganizationMembers(org);
        return organizationMembers.flatMapMany(Flux::fromIterable)
                .flatMap(member -> gitHubService.getUser(member.getLogin()))
                .map(user -> new Stats<>(user.getFollowersNb(), user.getReposNumber()))
                .reduce(new Stats<>(0, 0), (x, y) -> new Stats<>(x.getFollowers() + y.getFollowers(), x.getRepositories() + y.getRepositories()))
                .map(Stats::toString);
    }

    public Flux<String> getMembers(String org) {
        Mono<List<GitHubUser>> organizationMembers = gitHubService.getOrganizationMembers(org);
        Flux<GitHubUser> users = organizationMembers.flatMapMany(Flux::fromIterable)
                .flatMap(member -> gitHubService.getUser(member.getLogin()));
        return users.map(user -> user.getLogin() + "(" + user.getName() + ")");
    }
}
