import { Component, OnInit, InjectionToken } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ResourceService} from '../resource.service';
import { Resource } from '../resource';
import { ShiftListComponent } from '../shift-list/shift-list.component';
import { CrewListComponent } from '../crew-list/crew-list.component';
import { Globals } from '../globals';

@Component({
  selector: 'app-resource',
  templateUrl: './resource.component.html',
  styleUrls: ['./resource.component.css']
})
export class ResourceComponent implements OnInit {

  resource: Resource;
  LookupTable;

  ShiftListComponent = ShiftListComponent;
  CrewListComponent = CrewListComponent;

  constructor(private route: ActivatedRoute, private resourceService: ResourceService,
    private globals: Globals) { }

  ngOnInit() {
    console.log("Creating Resource");
	  let tag = this.route.snapshot.paramMap.get('tag');
    // using global object to pass values from container to lookup component, must be a better way
    this.globals.tag = tag;
    this.resource = new Resource(); // prevent 'type undefined' errors on page before object is loaded
    this.getResourceInfo(tag);
  }

  getResourceInfo(tag: string): void {
    this.resourceService.getSingleResource(tag)
      .subscribe(resource => {
        this.resource = resource;
      });
  }

  lookupShifts(): void {
    this.LookupTable = ShiftListComponent;
  }

  lookupCrews(): void {
    this.LookupTable = CrewListComponent;
  }
}
