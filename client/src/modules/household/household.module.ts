/* eslint-disable prettier/prettier */

import { CommonModule } from '@angular/common'
import { NgModule } from '@angular/core'
import { HouseholdRoutingModule } from './household-routing.module'
import { TableModule } from 'primeng/table';
import { HouseholdComponent } from './components/overview/household.component';
import { ButtonModule } from 'primeng/button';
import { SelectButtonModule } from 'primeng/selectbutton';
import { InputTextModule } from 'primeng/inputtext';

@NgModule({
  imports: [CommonModule, TableModule, HouseholdRoutingModule,
    ButtonModule, SelectButtonModule, InputTextModule],
  declarations: [HouseholdComponent],
  providers: [],
})
export class HouseholdModule { }
