package com.hoangha.demo.configuration;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;


@Configuration
public class AccountConfiguration {

	@Value("${elasticsearch.clustername}")
	String clusterName;
	
	@Value("${elasticsearch.host}")
	String eHost;
	
	@Value("${elasticsearch.port}")
	int ePort;
	
	@Bean
	public Client client() throws UnknownHostException {
		Settings esSettings = Settings.settingsBuilder()
                .put("cluster.name", clusterName)
                .build();
		return TransportClient.builder()
                .settings(esSettings)
                .build()
                .addTransportAddress(
				  new InetSocketTransportAddress(InetAddress.getByName(eHost), ePort));
	}
	
	@Bean
    public ElasticsearchOperations elasticsearchTemplate() throws Exception {
        return new ElasticsearchTemplate(client());
    }

}
