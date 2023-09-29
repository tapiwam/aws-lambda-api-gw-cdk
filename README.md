# aws-lambda-api-gw-cdk

Test app deploying a small java application to AWS Lambda and API Gateway

Follow this tutorial: https://medium.com/aws-tip/deploy-a-java-lambda-function-and-api-gateway-with-aws-cdk-6f22db5731a9

## Setup

- AWS CLI - `brew install awscli`
- AWS SAM CLI - `brew install aws-sam-cli`
- AWS CDK - `npm install -g cdk`

```bash
mvn archetype:generate \
-DgroupId=com.tmaruni \
-DartifactId=bmi-calculator \
-DarchetypeArtifactId=maven-archetype-quickstart \
-DinteractiveMode=false
```
