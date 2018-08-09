package com.cmoreau.springbootreactorretrofit.controller;

import com.cmoreau.springbootreactorretrofit.service.GitHubOrganizationService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.TEXT_EVENT_STREAM_VALUE;

@RestController
@RequestMapping("organizations")
@AllArgsConstructor
public class GitHubOrganizationController {

    private final GitHubOrganizationService gitHubOrganizationService;

    @GetMapping("/{organization}/members-stats")
    @ApiOperation(
            value = "Get the total number of followers an repositories for all members of an organization",
            produces = TEXT_EVENT_STREAM_VALUE
    )
    public Mono<String> getMembersStats(@PathVariable("organization") String organization){
        return gitHubOrganizationService.getMembersStats(organization);
    }

    @GetMapping("/{organization}/members")
    @ApiOperation(
            value = "Get login and name for all members of an organization",
            produces = TEXT_EVENT_STREAM_VALUE
    )
    public Flux<String> getMembers(@PathVariable("organization") String organization){
        return gitHubOrganizationService.getMembers(organization);
    }


}
