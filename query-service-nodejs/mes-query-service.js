var express = require('express');
var app = express();
var mysql = require('mysql');
var queries = require('./queries.js');
var cors = require('cors')

app.use(cors());

// Connect to DB
var dbConnection = mysql.createConnection({
  host: "172.21.0.2",
  user: "root",
  password: "adm1n",
  database: "mes"
});

dbConnection.connect(function(err) {
  if (err) throw err;
  console.log("Connected to database!");
});

// Start server
var server = app.listen(9080, function () {
   var host = server.address().address;
   var port = server.address().port;
   console.log("App listening at http://%s:%s", host, port)
})

// Get a single resource
app.get('/query/singleResource', function (req, res) {
  dbConnection.query(queries.SINGLE_RESOURCE, [req.query.tag], function (err, result, fields) {
    if (err) throw err;
    res.send(result[0]);
  });
});

// Get all resources
app.get('/query/allResources', function (req, res) {
  dbConnection.query(queries.ALL_RESOURCES, function (err, result, fields) {
        if (err) throw err;
        res.send(result);
      });
});

// Get a production run
app.get('/query/productionRun', function (req, res) {
  dbConnection.query(queries.PRODUCTION_RUN_INFO, [req.query.tag], function (err, result, fields) {
    if (err) throw err;
    res.send(result[0]);
  });
});
