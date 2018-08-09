package com.cmoreau.springbootreactorretrofit.config;

import com.cmoreau.springbootreactorretrofit.service.GitHubService;
import com.jakewharton.retrofit2.adapter.reactor.ReactorCallAdapterFactory;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

@Configuration
@AllArgsConstructor
public class RetrofitConfig {

    private final GitHubProperties gitHubProperties;

    private Retrofit retrofit() {
        return new Retrofit.Builder()
                .baseUrl(gitHubProperties.getHost())
                .addCallAdapterFactory(ReactorCallAdapterFactory.create())
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
    }

    @Bean
    public GitHubService gitHubService(){
        return retrofit().create(GitHubService.class);
    }

}
