import { Injectable } from '@angular/core'

@Injectable({
  providedIn: 'root',
})
export class StorageService {
  get(key: string) {
    return sessionStorage.getItem(key)
  }

  store(key: string, value: string) {
    sessionStorage.setItem(key, value)
  }
}
