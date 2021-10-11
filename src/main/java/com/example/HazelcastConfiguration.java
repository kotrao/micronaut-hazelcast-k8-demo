package com.example;

import com.hazelcast.config.Config;
import com.hazelcast.config.JoinConfig;
import io.micronaut.context.annotation.Bean;
import io.micronaut.context.annotation.Factory;
import io.micronaut.context.annotation.Requires;

@Factory
public class HazelcastConfiguration {

    @Requires(env="local")
    public Config hazelcastConfig() {

        System.out.println("****hazelcastConfig: local");
        Config configuration = new Config()
                .setClusterName("micronaut-cluster");
        JoinConfig joinConfig = configuration.getNetworkConfig().getJoin();
        joinConfig.getMulticastConfig().setEnabled(false);
        joinConfig.getTcpIpConfig().setEnabled(true).addMember("localhost");
        return configuration;
    }
    @Bean
    @Requires(env="localk8")
    public Config hazelcastConfigLocalK8() {
        System.out.println("****hazelcastConfig: localk8");

        Config configuration = new Config();
//                .setClusterName("apptor-hazelnet-cluster");

        JoinConfig joinConfig = configuration.getNetworkConfig().getJoin();
        joinConfig.getTcpIpConfig().setEnabled(false);
        joinConfig.getMulticastConfig().setEnabled(false);
        joinConfig.getKubernetesConfig().setEnabled(true)
                .setProperty("namespace", "default")
                .setProperty("service-name", "hazelcast-service");
                //.setProperty("resolve-not-ready-addresses", "true");

        return configuration;
    }
}
