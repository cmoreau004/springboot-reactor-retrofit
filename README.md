# springboot-reactor-retrofit

## Description

Example of Spring Boot application which consumes GitHub API using Reactor and Retrofit2.

## Resources
- Retrofit2 reactor adapter : https://github.com/JakeWharton/retrofit2-reactor-adapter

For testing : 
- Wiremock
- Datas from reactor organization and its two first members

##Running the app
Run the application on port 8080 : 
```
git clone https://github.com/cmoreau004/springboot-reactor-retrofit.git
cd springboot-reactor-retrofit
gradle bootRun
```
You can use curl to make requests or use swagger : http://localhost:8080/swagger-ui.html
