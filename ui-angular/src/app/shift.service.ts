import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../environments/environment';
import { Shift } from './shift';
import { LookupService } from './lookup-service';
import { Observable } from 'rxjs';
import { catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class ShiftService extends LookupService {

  constructor(private http: HttpClient) { 
  	super();
  }

  public getShifts() {
  	return this.http.get<Shift[]>(environment.rtServiceUrl + '/shift');
  }

  public changeShift(tag: string, shift: Shift): Observable<Shift> {
  	console.log("Changing shift: " + shift.name + " for resource " + tag);
    
  	const httpOptions = this.getHttpOptionsForPost();

    let url = environment.rtServiceUrl + "/resource/" + tag + "/shift/";

  	return this.http.post<Shift>(url, shift, httpOptions).
  		pipe(catchError(this.handleError));
  }  
}
