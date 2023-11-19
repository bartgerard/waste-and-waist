import { Injectable } from '@angular/core'
import { HttpClient } from '@angular/common/http'
import { Observable } from 'rxjs'

@Injectable({
  providedIn: 'root',
})
export class BackendService {
  private baseUrl = 'http://localhost:8080'

  constructor(private http: HttpClient) {}

  getData(): Observable<any> {
    return this.http.get<any>(`${this.baseUrl}/endpoint`)
  }

  sendData(data: any): Observable<any> {
    return this.http.post<any>(`${this.baseUrl}/endpoint`, data)
  }
}
