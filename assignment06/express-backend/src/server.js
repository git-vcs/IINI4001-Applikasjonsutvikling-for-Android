let express = require("express");
let bodyParser = require("body-parser");
let app = express();
app.use(bodyParser.json()); // for å tolke JSON

//debug-metode
app.get("/api/test", (req, res) => {
    console.log("Fikk request fra klient");
    console.log(req.body);
    res.sendStatus(200);
});

var server=app.listen(8080);