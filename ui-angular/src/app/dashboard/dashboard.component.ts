import { Component, OnInit } from '@angular/core';
import { ResourceService } from '../resource.service';
import { Resource } from '../resource';
import { interval } from 'rxjs';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  resources: Resource[];

  lastRefreshedTime: Date;

  // Not implemented yet
  refreshOn: boolean = true;

  constructor(private resourceService: ResourceService) { 
  }

  ngOnInit() {
    this.resources = [];
    this.getResources();
    //interval(5000).subscribe(() => this.getResources());
  }

  getResources(): void {
  	this.resourceService.getResources()
      .subscribe(resources => this.resources = resources);
    this.lastRefreshedTime = new Date();
  }

  // Not implemented yet
  toggleRefresh(): void {
    if (this.refreshOn) {
      this.refreshOn = false;
    }
    else {
      this.refreshOn = true;
    }
  }
}
