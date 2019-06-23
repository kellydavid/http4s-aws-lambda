# http4s-aws-lambda
Running http4s in aws lambda

This project is intented to act as a demonstration of using the [scala-server-lambda](https://github.com/howardjohn/scala-server-lambda)
library with http4s which provides an entrypoint for the AWS lambda java8 runtime.

To run this in AWS lambda: 
1. Create a jar using by running `sbt assembly`.
2. Create an aws lambda via the console and upload the jar found here: `resource-hello/target/scala-2.12/resource-hello-assembly-0.0.1-SNAPSHOT.jar`
3. Set the handler entrypoint to `io.kellydavid.http4sawslambda.HelloLambda::handle`
4. Run a test using the AWS Api Gateway proxy blueprint. Set the `path` property to `/hello/world`. Set the 
`httpMethod` property to `GET`.
