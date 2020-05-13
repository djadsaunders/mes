import { Injectable } from '@angular/core';
import { Resource } from './resource';
import { HttpClient } from '@angular/common/http';
import { environment } from '../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ResourceService {

  constructor(private http: HttpClient) { }

  getResources() {
  	return this.http.get<Resource[]>(environment.queryServiceUrl + '/query/allResources');
  }

  getSingleResource(tag: String) {
  	return this.http.get<Resource>(environment.queryServiceUrl + '/query/singleResource?tag=' + tag);
  }
}
