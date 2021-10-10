kubectl config use-context minikube
kubectl delete deployment cache-service -n default
kubectl delete service cache-service  -n default
kubectl delete service hazelcast-service -n default