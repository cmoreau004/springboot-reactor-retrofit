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
public class GitHubOrganization {

    Long id;

    String login;

    String name;

    @JsonProperty("html_url")
    String url;

    @JsonProperty("public_repos")
    Integer reposNumber;

    @JsonProperty("created_at")
    String creationDate;

}
