import { CommonModule } from '@angular/common'
import { NgModule } from '@angular/core'
import { LoginRoutingModule } from './overview/login-routing.module'
import { PasswordModule } from 'primeng/password'
import { ButtonModule } from 'primeng/button'
import { InputTextModule } from 'primeng/inputtext'
import { LoginPageComponent } from './pages/login-page.component'
import { ReactiveFormsModule } from '@angular/forms'

@NgModule({
  imports: [
    CommonModule,
    LoginRoutingModule,
    ButtonModule,
    PasswordModule,
    InputTextModule,
    ReactiveFormsModule,
  ],
  declarations: [LoginPageComponent],
  providers: [],
})
export class LoginModule {}
