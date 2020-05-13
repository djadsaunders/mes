import { Component, OnInit} from '@angular/core';
import { CrewService } from '../crew.service';
import { Crew } from '../crew';
import { Globals } from '../globals';
import { Router } from '@angular/router';

@Component({
  selector: 'app-crew-list',
  templateUrl: './crew-list.component.html',
  styleUrls: ['./crew-list.component.css'],
})
export class CrewListComponent implements OnInit {

  crews: Crew[];

  constructor(private service: CrewService, private globals: Globals, private router: Router) {}

  ngOnInit() {
  	this.service.getCrews()
      .subscribe(crews => this.crews = crews);
  }

  crewSelected(crew: Crew) {
    let obs = this.service.changeCrew(this.globals.tag, crew);
    obs.subscribe(() => {
      this.router.navigate(['dashboard']);
    });
  }
}
