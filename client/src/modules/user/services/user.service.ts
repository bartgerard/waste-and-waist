import { BASE_URL } from '@/modules/core/base-url.token'
import { HttpClient } from '@angular/common/http'
import { Inject, Injectable } from '@angular/core'
import { tap } from 'rxjs'
import { User } from '@/types/user'
import { StorageService } from '@/modules/core/services/storage.service'

@Injectable({ providedIn: 'root' })
export class UserService {
  user: User | null = null

  constructor(
    @Inject(BASE_URL) private baseUrl: string,
    private http: HttpClient,
    private storageService: StorageService
  ) {}

  login(data: { username: string; password: string }) {
    return this.http.post<User>(`${this.baseUrl}/users/login`, data).pipe(
      tap((user: User) => {
        this.user = user
        this.storageService.store('USER', JSON.stringify(user))
      })
    )
  }

  setUser(user: User) {
    this.user = user
  }
}
