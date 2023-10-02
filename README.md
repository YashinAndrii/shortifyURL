# Shortify URL

Shortify URL is a simple web application for shortening long URLs and creating shortened links.

## Description

This application provides the following features:

- Shortening long URLs, creating shortened links.
- Retrieving original URLs from shortened links.
- URL validation.
- Storing data in a MongoDB database.
- Integration with Redis for improved performance.

## Running the Project

You can run the project in Docker containers using Docker Compose. Make sure you have Docker and Docker Compose installed.

```bash
docker-compose up
```
## Usage

- To shorten a URL, go to [http://localhost:8080/shortify](http://localhost:8080/shortify) and enter the original URL.
- To retrieve the original URL, go to [http://localhost:8080/orig](http://localhost:8080/orig) and enter the shortened URL.
