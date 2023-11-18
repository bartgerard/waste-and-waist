import { NgModule } from '@angular/core'
import { BrowserModule } from '@angular/platform-browser'
import { BrowserAnimationsModule } from '@angular/platform-browser/animations'
import { HouseholdModule } from 'src/modules/household/household.module'
import { LoginModule } from 'src/modules/login/overview/login.module'
import { TableModule } from 'primeng/table'
import { AppRoutingModule } from './app-routing.module'
import { SelectButtonModule } from 'primeng/selectbutton'
import { AppComponent } from './app.component'
import { FormsModule } from '@angular/forms'

@NgModule({
  declarations: [AppComponent],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    AppRoutingModule,
    HouseholdModule,
    LoginModule,
    TableModule,
    SelectButtonModule,
    FormsModule,
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
