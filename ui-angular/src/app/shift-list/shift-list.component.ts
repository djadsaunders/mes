import { Component, OnInit } from '@angular/core';
import { ShiftService } from '../shift.service';
import { Shift } from '../shift';
import { Globals } from '../globals';
import { Router } from '@angular/router';

@Component({
  selector: 'app-shift-list',
  templateUrl: '../shift-list/shift-list.component.html',
  styleUrls: ['./shift-list.component.css']
})
export class ShiftListComponent implements OnInit {
  shifts: Shift[];

  constructor(private service: ShiftService, private globals: Globals, private router: Router) {}

  ngOnInit() {
  	this.service.getShifts()
      .subscribe(shifts => this.shifts = shifts);
  }

  public shiftSelected(shift: Shift) {
    let obs = this.service.changeShift(this.globals.tag, shift);
    obs.subscribe(() => {
      this.router.navigate(['dashboard']);
    });
  }
}
