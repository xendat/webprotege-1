FROM openjdk:11-jre-slim

# Install Tomcat and other dependencies
RUN apt-get update && \
    apt-get install -y wget unzip && \
    wget https://archive.apache.org/dist/tomcat/tomcat-8/v8.5.57/bin/apache-tomcat-8.5.57.tar.gz && \
    tar -xzf apache-tomcat-8.5.57.tar.gz && \
    mv apache-tomcat-8.5.57 /usr/local/tomcat && \
    rm apache-tomcat-8.5.57.tar.gz && \
    rm -rf /usr/local/tomcat/webapps/* && \
    mkdir -p /usr/local/tomcat/webapps/ROOT && \
    mkdir -p /etc/webprotege && \
    mkdir -p /data/webprotege && \
    chmod 777 /data/webprotege

# Create WebProtege configuration
RUN echo "webprotege.mongodb.host=mongodb" > /etc/webprotege/webprotege.properties && \
    echo "webprotege.mongodb.port=27017" >> /etc/webprotege/webprotege.properties && \
    echo "webprotege.application.version=4.0.2" >> /etc/webprotege/webprotege.properties && \
    echo "webprotege.data.directory=/data/webprotege" >> /etc/webprotege/webprotege.properties && \
    echo "webprotege.mongodb.connection.timeout.ms=30000" >> /etc/webprotege/webprotege.properties

WORKDIR /usr/local/tomcat/webapps/ROOT

# Download and extract WebProtege
COPY webprotege-server/target/webprotege-4.0.2.war webprotege.war
RUN unzip webprotege.war && \
    rm webprotege.war && \
    apt-get remove -y wget unzip && \
    rm -rf /var/lib/apt/lists/*

# Copy our fixed files
COPY custom-classes/edu/stanford/bmir/protege/web/server/change/ /usr/local/tomcat/webapps/ROOT/WEB-INF/classes/edu/stanford/bmir/protege/web/server/change/

EXPOSE 8080

CMD ["/usr/local/tomcat/bin/catalina.sh", "run"]
