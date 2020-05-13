import { Component, OnInit, Input } from '@angular/core';
import { Resource } from '../resource';
import { ProductionRun } from '../production-run';
import { ProductionRunService } from '../production-run.service';

@Component({
  selector: 'app-dashboard-row',
  templateUrl: './dashboard-row.component.html',
  styleUrls: ['./dashboard-row.component.css']
})
export class DashboardRowComponent implements OnInit {

  productionRun: ProductionRun;

  @Input()
  resource: Resource;

  constructor(private productionRunService: ProductionRunService) { }

  ngOnInit() {
    this.productionRun = new ProductionRun();
  	this.productionRunService.getProductionRun(this.resource.tag)
      .subscribe(productionRun => this.productionRun = productionRun);
  }
}
