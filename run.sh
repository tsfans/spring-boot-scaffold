if [ "$RUNNING_ENV" = "production" ]
then
exec java -Xmx1g -Xms256m target/1.0.0.jar  --spring.profiles.active=production
elif [ "$RUNNING_ENV" = "staging" ]
then
exec java -Xmx1g -Xms256m target/1.0.0.jar  --spring.profiles.active=staging
else
exec java -jar target/1.0.0.jar
fi
