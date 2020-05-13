var vmc = Vue.component('dashboard-row', {
    data: function () {
        return {};
    },
    props: ['resource','productionRun'],
    template: `<div class="panel panel-default">
	    <div class="panel-body">
            <div>
                <div style="width: 25%; float:left">
                    <div :class="resource.productionState == 0 ? 'resource-name stopped' : resource.productionState == 1 ? 'resource-name slow' : 'resource-name running'">
                        {{resource.name}}
                    </div>
                    <div class="label label-default">
                        {{resource.productionState == 0 ? '&nbsp;stopped' : resource.productionState == 1 ? '&nbsp;slow' : '&nbsp;running'}}
                    </div>
                    <h4>{{resource.currentShift}}&nbsp;&nbsp;&nbsp;{{resource.currentCrew}}</h4>
                </div>

                <div class="title" style="width: 25%; text-align:top; float:left">
				    <h3>{{resource.currentProduct}} ({{resource.currentProductionRun}})</h3>
					<h4>{{production-run.outCount}} units made</h4>
			    </div>
            </div>
	    </div>
    </div>`
  })
