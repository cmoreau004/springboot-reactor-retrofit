package com.cmoreau.springbootreactorretrofit.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
public class GitHubUser {

    Long id;

    String name;

    String login;

    @JsonProperty("html_url")
    String url;

    @JsonProperty("public_repos")
    Integer reposNumber;

    @JsonProperty("followers")
    Integer followersNb;

    String bio;

}
