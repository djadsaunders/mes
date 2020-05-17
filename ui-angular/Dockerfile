FROM ubuntu
RUN apt-get update
RUN apt-get install -y webfs
RUN mkdir -p /var/www/{html}

# Copy mime.types file for webfs
WORKDIR /etc
COPY mime.types .

# Copy Angular app
WORKDIR /var/www/html
COPY dist/ui-angular/ .

# Copy entrypoint script
COPY docker-entrypoint.sh /usr/local/bin

ENTRYPOINT ["/usr/local/bin/docker-entrypoint.sh"]