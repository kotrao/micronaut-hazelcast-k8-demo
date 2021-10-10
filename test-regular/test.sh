echo "Setting Values through instance 1"
curl  "localhost:8080/put?key=key1&value=hazelcast"
curl  "localhost:8080/put?key=key2&value=expeed"

echo "\n\nRetrieving values from instance 1"
curl "localhost:8080/get?key=key1"
curl "localhost:8080/get?key=key2"

echo "\n\nRetrieving values from instance 2"
curl "localhost:8081/get?key=key1"
curl "localhost:8081/get?key=key2"
