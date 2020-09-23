let express = require("express");
let bodyParser = require("body-parser");
let app = express();
app.use(bodyParser.json()); // for å tolke JSON

//todo lag en metode for xml hvis json ikke fungere i javater
app.post("/api/test", (req, res) => {
    console.log("Fikk request fra klient");
    console.log(req.body);
    res.json({sum:req.body.tall1+req.body.tall2}).status(200);
    res.status(200);
});


app.post("/api/sum", (req, res) => {
    console.log("Fikk request fra klient");
    console.log(req.body);
    res.json({sum:req.body.tall1+req.body.tall2}).status(200);
    res.status(200);
});



var server=app.listen(8080);