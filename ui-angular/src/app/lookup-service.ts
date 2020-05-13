import { HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { throwError } from 'rxjs';

export class LookupService {
	protected handleError(error: HttpErrorResponse) {
		if (error.error instanceof ErrorEvent) {
			console.error('An error occurred:', error.error.message);
		} 
		else {
			console.error(
			  `Backend returned code ${error.status}, ` +
			  `body was: ${error.error}`);
		}
	    return throwError(
		  'Something bad happened; please try again later.');
	  }

	protected getHttpOptionsForPost() {
		return {
		  headers: new HttpHeaders({
		    'Content-Type': 'application/json'
		  })};
	}
}