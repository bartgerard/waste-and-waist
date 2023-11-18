/* eslint-disable prettier/prettier */

import { CommonModule } from '@angular/common'
import { NgModule } from '@angular/core'
import { LoginRoutingModule } from './login-routing.module'
import { PasswordModule } from 'primeng/password';
import { ButtonModule } from 'primeng/button';
import { InputTextModule } from 'primeng/inputtext';
import { LoginComponent } from './login.component';
import { ReactiveFormsModule } from '@angular/forms';

@NgModule({
  imports: [CommonModule, LoginRoutingModule, ButtonModule,
    PasswordModule, InputTextModule, ReactiveFormsModule],
  declarations: [LoginComponent],
  providers: [],
})
export class LoginModule { }
