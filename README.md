1. Start application on port 8080


2. Configure Citrus http client with destination ip (not localhost or 127.0.0.1)
@Bean
    public HttpClient httpClient() {
        return CitrusEndpoints
                .http()
                .client()
                .requestUrl("http://192.168.1.146:8080")
                .build();
    }

3.1.1. Start pynt with proxy mode pynt listen --captured-domains "*" --insecure

3.1.2. Run test with VM options

3.2. Run pynt command --cmd "mvn -Dhttp.proxyHost=127.0.0.1 -Dhttp.proxyPort=6666 verify"



