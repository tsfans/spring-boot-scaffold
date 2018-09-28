if [ "$RUNNING_ENV" = "production" ]
then
exec java -jar -Xmx1g -Xms256m target/spring-boot-scaffold-1.0.0.jar  --spring.profiles.active=production
elif [ "$RUNNING_ENV" = "staging" ]
then
exec java -jar -Xmx1g -Xms256m target/spring-boot-scaffold-1.0.0.jar  --spring.profiles.active=staging
else
exec java -jar target/spring-boot-scaffold-1.0.0.jar
fi
