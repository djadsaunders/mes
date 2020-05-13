import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../environments/environment';
import { ProductionRun } from './production-run';

@Injectable({
  providedIn: 'root'
})
export class ProductionRunService {

  constructor(private http: HttpClient) { }

  getProductionRun(tag : String) {
  	return this.http.get<ProductionRun>(environment.queryServiceUrl + '/query/productionRun?tag=' + tag);
  }
}
