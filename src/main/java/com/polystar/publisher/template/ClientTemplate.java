package com.polystar.publisher.template;


import com.polystar.publisher.props.Props;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedHashMap;


public class ClientTemplate {

    private Props props;
    RestTemplate restTemplate;

    public ClientTemplate(Props props , RestTemplate restTemplate) {
        this.props = props;
        this.restTemplate = restTemplate;
    }

    public LinkedHashMap<String ,Integer> getTopWordInStringConcat(String line) {
        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity<String> httpEntity = new HttpEntity<>(line,httpHeaders);
        return restTemplate.postForEntity(props.getAnalyticsServerUrl()+props.getTopWordsPath(),httpEntity, LinkedHashMap.class).getBody();

    }
    public LinkedHashMap<String ,Integer> getOneTimeTopWordInStringConcat(String line) {
        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity<String> httpEntity = new HttpEntity<>(line,httpHeaders);
        return restTemplate.postForEntity(props.getAnalyticsServerUrl()+props.getTopOnetimeRepeatedPath(),httpEntity, LinkedHashMap.class).getBody();

    }

    public LinkedHashMap<String ,Integer> getCurrentTopRepeatedWordsConcat() {

        return restTemplate.getForEntity(props.getAnalyticsServerUrl()+props.getTopRepeatedPath(), LinkedHashMap.class).getBody();

    }
}
