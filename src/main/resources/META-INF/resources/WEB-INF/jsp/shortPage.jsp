<!DOCTYPE html>
<html>
    <head>
        <title>URL Shortify</title>
        <style>
            body {
                text-align: center;
                font-size: 20px;
            }
            form {
                display: inline-block;
            }
            p {
                margin-top: 10px;
            }
        </style>
    </head>
    <body>
        <h1>URL Shortify</h1>
        <form action="/shortify" method="POST">
            <label for="originalURL">Original URL:</label>
            <input type="text" id="originalURL" name="originalURL" required>
            <button type="submit">Shorten</button>
        </form>
        <p>Shortened URL: ${shortenedURL}</p>
        <a href="/orig">Get original</a>
    </body>
</html>
