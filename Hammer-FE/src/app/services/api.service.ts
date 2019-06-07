import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ApiService {
  private urlBase = 'http://localhost:8080';

  constructor(private http: HttpClient) { }

  updateParticipants(dateFrom: string, dateTo: string): Observable<number> {
    let params = new HttpParams().set("fromDate", dateFrom).set("toDate", dateTo);
    return this.http.get<number>(this.urlBase + '/updateParticipants', { params: params });
  }
}
