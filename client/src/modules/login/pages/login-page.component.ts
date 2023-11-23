import { UserService } from '@/modules/user/services/user.service'
import { Component } from '@angular/core'
import { FormControl, FormGroup, Validators } from '@angular/forms'
import { Router } from '@angular/router'

@Component({
  selector: 'app-login',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.scss'],
})
export class LoginPageComponent {
  formGroup = new FormGroup({
    username: new FormControl('', Validators.required),
    password: new FormControl('', Validators.required),
  })

  constructor(
    private router: Router,
    private userService: UserService
  ) {}

  login() {
    if (
      !this.formGroup.controls.username.value ||
      !this.formGroup.controls.password.value
    ) {
      return
    }

    this.userService
      .login(this.formGroup.value as { username: string; password: string })
      .subscribe()

    this.router.navigate(['/households'])
  }
}
