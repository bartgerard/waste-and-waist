import { Component, OnInit } from '@angular/core'
import { PrimeNGConfig } from 'primeng/api'
import { StorageService } from '@/modules/core/services/storage.service'
import { UserService } from '@/modules/user/services/user.service'

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
})
export class AppComponent implements OnInit {
  constructor(
    private primengConfig: PrimeNGConfig,
    private storageService: StorageService,
    private userService: UserService
  ) {}

  ngOnInit(): void {
    this.initApplication()
    this.getUserFromStorage()
  }

  initApplication() {
    this.primengConfig.ripple = true
    this.primengConfig.zIndex = {
      modal: 1100,
      overlay: 1000,
      menu: 1000,
      tooltip: 1100,
    }
  }

  getUserFromStorage(): void {
    const serializedUser = this.storageService.get('USER')

    if (!serializedUser) {
      return
    }

    this.userService.setUser(JSON.parse(serializedUser))
  }
}
