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
    return this.http.get<any>(`${this.baseUrl}/locations`)
  }

  sendData(data: any): Observable<any> {
    return this.http.post<any>(`${this.baseUrl}/endpoint`, data)
  }

  createUser(userData: any): Observable<any> {
    return this.http.post(`${this.baseUrl}/users/register`, userData)
  }

  userLogin(loginData: any): Observable<any> {
    return this.http.post(`${this.baseUrl}/users/login`, loginData)
  }

  getHouseholdsByUserId(userId: string): Observable<any> {
    return this.http.get(`${this.baseUrl}/house-holds/by-user/${userId}`)
  }

  createHousehold(householdData: any): Observable<any> {
    return this.http.post(`${this.baseUrl}/house-holds/`, householdData)
  }

  createHouseholdLocations(locationData: any): Observable<any> {
    return this.http.post(`${this.baseUrl}/locations`, locationData)
  }

  getHouseholdLocations(houseHoldId: string): Observable<any> {
    return this.http.get(`${this.baseUrl}/house-holds/${houseHoldId}/locations`)
  }
}
