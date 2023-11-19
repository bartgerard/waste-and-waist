import { CommonModule } from '@angular/common'
import { NgModule } from '@angular/core'
import { HouseholdRoutingModule } from './household-routing.module'
import { TableModule } from 'primeng/table'
import { SelectButtonModule } from 'primeng/selectbutton'
import { InputTextModule } from 'primeng/inputtext'
import { FormsModule } from '@angular/forms'
import { CardModule } from 'primeng/card'
import { HttpClientModule } from '@angular/common/http'
import { CalendarModule } from 'primeng/calendar'
import { SharedModule } from '@/modules/shared/shared.module'
import { HouseholdComponent } from './pages/household.component'
import { SelectedHouseHoldComponent } from '@/modules/household/components/selected-house-hold/selected-house-hold.component'
import { DropdownModule } from 'primeng/dropdown'
import { ProgressSpinnerModule } from 'primeng/progressspinner'

@NgModule({
  imports: [
    CardModule,
    CommonModule,
    FormsModule,
    HouseholdRoutingModule,
    HttpClientModule,
    InputTextModule,
    SelectButtonModule,
    TableModule,
    CalendarModule,
    SharedModule,
    DropdownModule,
    ProgressSpinnerModule,
  ],
  declarations: [HouseholdComponent, SelectedHouseHoldComponent],
  providers: [],
})
export class HouseholdModule {}
