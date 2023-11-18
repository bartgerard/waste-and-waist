/* eslint-disable prettier/prettier */

import { NgModule } from '@angular/core'
import { BrowserModule } from '@angular/platform-browser'
import { BrowserAnimationsModule } from '@angular/platform-browser/animations'
import { HouseholdModule } from 'src/modules/household/household.module'
import { LoginModule } from 'src/modules/login/overview/login.module'
import { AppRoutingModule } from './app-routing.module'
import { AppComponent } from './app.component'

@NgModule({
  declarations: [AppComponent],
  imports: [BrowserModule, BrowserAnimationsModule, AppRoutingModule, HouseholdModule, LoginModule],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule { }
