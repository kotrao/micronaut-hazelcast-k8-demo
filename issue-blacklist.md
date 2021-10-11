I am trying to test Micronaut cache with embedded hazelcast in kubernetes. Hazelcast seems to be accepting only one of the pods into cluster. 
It is blacklisting all other pods. Any idea what I am doing wrong? 

I am seeing the following message:

```
[cache-service] Oct 11, 2021 3:28:18 PM com.hazelcast.internal.cluster.impl.DiscoveryJoiner
[cache-service] INFO: [172.17.0.7]:5701 [dev] [4.1.3] [172.17.0.5]:5701 is added to the blacklist.
[cache-service] Oct 11, 2021 3:28:18 PM com.hazelcast.internal.cluster.impl.DiscoveryJoiner
[cache-service] INFO: [172.17.0.7]:5701 [dev] [4.1.3] [172.17.0.6]:5701 is added to the blacklist.
```
Following are the configuration details:

Micronaut Version: 2.5.9

## Hazelcast configuration
```java
@Factory
public class HazelcastConfiguration {

    @Bean
    @Requires(env="localk8")
    public Config hazelcastConfigLocalK8() {
        System.out.println("****hazelcastConfig: localk8");

        Config configuration = new Config();
        JoinConfig joinConfig = configuration.getNetworkConfig().getJoin();
        joinConfig.getTcpIpConfig().setEnabled(false);
        joinConfig.getMulticastConfig().setEnabled(false);
        joinConfig.getKubernetesConfig().setEnabled(true)
                .setProperty("namespace", "default")
                .setProperty("service-name", "hazelcast-service");

        return configuration;
    }
}
```

## Kubernetes deployment file
```yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: cache-service
  labels:
    app: cache-service
  namespace: default
spec:
  replicas: 3
  selector:
    matchLabels:
      app: cache-service
  template:
    metadata:
      labels:
        app: cache-service
    spec:
      serviceAccountName: default
      containers:
        - name: cache-service
          image: micronaut-hazelcast-k8-demo:latest
          ports:
            - name: http
              containerPort: 8080
            - name: multicast
              containerPort: 5701
          env:
            - name: MICRONAUT_ENVIRONMENTS
              value: localk8
---
apiVersion: v1
kind: Service
metadata:
  name: cache-service
  namespace: default
  labels:
    app: cache-service
spec:
  ports:
    - port: 8080
      protocol: TCP
  selector:
    app: cache-service
---
apiVersion: v1
kind: Service
metadata:
  name: hazelcast-service
  namespace: default
spec:
  selector:
    app: cache-service
  ports:
    - name: hazelcast
      port: 5701
      protocol: TCP
```
## gradle dependencies being used
```
dependencies {
    implementation("io.micronaut:micronaut-http-client")
    implementation("io.micronaut:micronaut-runtime")
    implementation("io.micronaut.kubernetes:micronaut-kubernetes-discovery-client")
    implementation("io.micronaut.cache:micronaut-cache-hazelcast")
    implementation("javax.annotation:javax.annotation-api")
    runtimeOnly("ch.qos.logback:logback-classic")
    implementation("io.micronaut:micronaut-validation")
    runtimeOnly group: 'com.hazelcast', name: 'hazelcast-kubernetes', version: '2.2.3'

}
```