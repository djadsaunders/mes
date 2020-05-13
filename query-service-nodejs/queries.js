var fs = require('fs');

fs.readFile(__dirname + "/queries/all_resources.sql", "utf-8", function(err, data) {
  if (err) throw(err);
  module.exports.ALL_RESOURCES = data;
});

fs.readFile(__dirname + "/queries/single_resource.sql", "utf-8", function(err, data) {
  if (err) throw(err);
  module.exports.SINGLE_RESOURCE = data;
});

fs.readFile(__dirname + "/queries/production_run_info.sql", "utf-8", function(err, data) {
  if (err) throw(err);
  module.exports.PRODUCTION_RUN_INFO = data;
});