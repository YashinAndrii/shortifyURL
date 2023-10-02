<!DOCTYPE html>
<html>
    <head>
        <title>URL Shortener</title>
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
        <form action="/orig" method="POST">
            <label for="shortURL">Short URL:</label>
            <input type="text" id="shortURL" name="shortURL" required>
            <button type="submit">Get original</button>
        </form>
        <p>Original URL: ${originalURL}</p>
        <a href="/shortify">Make shorter</a>
    </body>
</html>
