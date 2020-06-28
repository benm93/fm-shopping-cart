cmd \C gradlew.bat bootJar
docker build -t fm-container .
docker tag fm-container:latest 517318246503.dkr.ecr.us-east-1.amazonaws.com/fm-container:latest
docker push 517318246503.dkr.ecr.us-east-1.amazonaws.com/fm-container:latest