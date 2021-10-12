## Run in regular mode
- cd ./test-regular
- ./run-port-8080.sh
- ./run-port-8081.sh

These commands will run two instances of the service on ports 8080 and 8081. Now run ./test.sh 

## Run in minikube

I am using skaffold to run it. So, please follow the stpes from the root of project:

- Make sure skaffold is installed (<a href="https://skaffold.dev/docs/install/">skaffold installation help</a>)
- Then run the command : skaffold dev
