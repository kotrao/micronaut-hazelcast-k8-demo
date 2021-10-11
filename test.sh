curl  -H "Connection: close" "localhost:8080/put?key=key1&value=hazelcast"
curl  -H "Connection: close" "localhost:8080/put?key=key2&value=expeed"
curl -H "Connection: close" "localhost:8080/get?key=key1"
curl -H "Connection: close" "localhost:8080/get?key=key2"
