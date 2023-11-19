import { Inject, Injectable } from '@angular/core'
import { HttpClient } from '@angular/common/http'
import { BASE_URL } from '@/modules/core/base-url.token'
import { HouseHold } from '@/types/household'

@Injectable({
  providedIn: 'root',
})
export class HouseholdService {
  constructor(
    @Inject(BASE_URL) private baseUrl: string,
    private http: HttpClient
  ) {}

  get(userId: string) {
    return this.http.get<{ houseHolds: HouseHold[] }>(
      `${this.baseUrl}/house-holds/by-user/${userId}`
    )
  }

  add({
    houseHoldName,
    memberName,
    birthDate,
    userId,
  }: {
    houseHoldName: string
    memberName: string
    birthDate: string
    userId: string
  }) {
    return this.http.post(`${this.baseUrl}/house-holds`, {
      houseHoldName,
      memberName,
      birthDate,
      userId,
    })
  }
}
