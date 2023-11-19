import { CommonModule } from '@angular/common'
import { NgModule } from '@angular/core'
import { HouseholdRoutingModule } from './household-routing.module'
import { TableModule } from 'primeng/table'
import { ButtonModule } from 'primeng/button'
import { SelectButtonModule } from 'primeng/selectbutton'
import { InputTextModule } from 'primeng/inputtext'
import { FormsModule } from '@angular/forms'
import { CardModule } from 'primeng/card'
import { HttpClientModule } from '@angular/common/http'
import { HouseholdComponent } from '@/modules/household/components/household.component'

@NgModule({
  imports: [
    CommonModule,
    TableModule,
    HouseholdRoutingModule,
    ButtonModule,
    SelectButtonModule,
    InputTextModule,
    FormsModule,
    CardModule,
    HttpClientModule,
  ],
  declarations: [HouseholdComponent],
  providers: [],
})
export class HouseholdModule {}
