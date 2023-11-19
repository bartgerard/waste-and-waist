import { NgModule } from '@angular/core'
import { BrowserModule } from '@angular/platform-browser'
import { BrowserAnimationsModule } from '@angular/platform-browser/animations'
import { TableModule } from 'primeng/table'
import { AppRoutingModule } from './app-routing.module'
import { SelectButtonModule } from 'primeng/selectbutton'
import { AppComponent } from './app.component'
import { FormsModule } from '@angular/forms'
import { HouseholdModule } from '@/modules/household/household.module'
import { LoginModule } from '@/modules/login/login.module'
import { AppShellModule } from '@/modules/core/app-shell/app-shell.module'

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
    AppShellModule,
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
