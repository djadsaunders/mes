import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from '../environments/environment';
import { Crew } from './crew';
import { Observable } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { LookupService } from './lookup-service';

@Injectable({
  providedIn: 'root'
})
export class CrewService extends LookupService {

  constructor(private http: HttpClient) { 
  	super();
  }

  getCrews() {
  	return this.http.get<Crew[]>(environment.rtServiceUrl + '/crew');
  }

  changeCrew(tag: string, crew: Crew): Observable<Crew> {
  	console.log("Changing crew: " + crew.name + " for resource " + tag);
    
  	const httpOptions = this.getHttpOptionsForPost();

    let url = environment.rtServiceUrl + "/resource/" + tag + "/crew/";

  	return this.http.post<Crew>(url, crew, httpOptions).
  		pipe(catchError(this.handleError));
  }
}
