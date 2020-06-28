### Deployment

Configure AWS ECR CLI and authenticate according to AWS docs.
Then, run deploy.bat to build the application, package it in a container, and push to ECR.

### Database Connection

Create the following environment variables in order to connect to the database:
FM_DB - formatted as jdbc:mysql://hostname:portnumber
FM_DB_USERNAME
FM_DB_PASSWORD