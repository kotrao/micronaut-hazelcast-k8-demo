./gradlew clean build
./undeploy.sh
kubectl apply -f ./k8s/deployment-default.yml
