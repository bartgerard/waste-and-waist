import { Injectable } from '@angular/core'
import { HttpClient } from '@angular/common/http'
import { Observable } from 'rxjs'

@Injectable({
  providedIn: 'root',
})
export class BackendService {
  private baseUrl = 'http://localhost:8080'

  constructor(private http: HttpClient) {}

  // Function to perform the sequence of API calls
  performSequence(userId: string, password: string): Observable<any> {
    return new Observable(observer => {
      this.createUser(userId, password).subscribe(
        userCreationResponse => {
          this.userLogin(userId, password).subscribe(
            loginResponse => {
              const householdData = {
                houseHoldName: 'Example Household',
                userId: loginResponse.userId,
                memberName: 'Example Member',
                birthDate: '2023-11-19',
              }
              this.createHousehold(householdData).subscribe(
                householdCreationResponse => {
                  this.getHouseholdsByUserId(userId).subscribe(
                    householdResponse => {
                      const locationData = {
                        houseHoldId: householdResponse.id,
                        locationName: 'Location1',
                      }
                      this.createHouseholdLocations(locationData).subscribe(
                        locationCreationResponse => {
                          const houseHoldId =
                            householdCreationResponse.houseHoldId
                          this.getHouseholdLocations(houseHoldId).subscribe(
                            locationResponse => {
                              observer.next(locationResponse)
                              observer.complete()
                            },
                            locationError => {
                              observer.error(locationError)
                            }
                          )
                        },
                        locationCreateError => {
                          observer.error(locationCreateError)
                        }
                      )
                    },
                    householdError => {
                      observer.error(householdError)
                    }
                  )
                },
                householdCreateError => {
                  observer.error(householdCreateError)
                }
              )
            },
            loginError => {
              observer.error(loginError)
            }
          )
        },
        userCreationError => {
          observer.error(userCreationError)
        }
      )
    })
  }

  getData(): Observable<any> {
    return this.http.get<any>(`${this.baseUrl}/locations`)
  }

  sendData(data: any): Observable<any> {
    return this.http.post<any>(`${this.baseUrl}/endpoint`, data)
  }

  createUser(username: string, password: string): Observable<any> {
    const userData = { username, password }
    return this.http.post(`${this.baseUrl}/users/register`, userData)
  }

  userLogin(username: string, password: string): Observable<any> {
    const userData = { username, password }
    return this.http.post(`${this.baseUrl}/users/login`, userData)
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
