var vm = new Vue({
    el: '#app',
    data: {
      lastRefreshedTime: null,
      resources: [],
      productionRunInfo: []
    },
    created: function() {
        this.lastRefreshedTime = moment(new Date(), 'YYYY-MM-DD').format('DD/MM/YYYY HH:mm:ss');
        this.getAllResources();
    },
    methods: {
        getAllResources: function() {
            $.get("http://localhost:8081/query/allResources", function(resp) {
            vm.resources = resp;
            resp.forEach(resource => {
              $.get("http://localhost:8081/query/productionRun?tag=" + resource.tag, function(resp) {
                vm.productionRunInfo.push(resp);
              });
            })
          });
        }
    }
  });